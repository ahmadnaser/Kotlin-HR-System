package com.ahmadnaser.kotlinhrsystem
///Developed By Ahmad Naser Turnkey Solutions LLC - Greenbackend.com
///http://greenbackend.com/developer-hosting
///www.greenbackend.com
// www.khottah.com
///greenbackend@ahmadnaser.com
///copyright © 2018 ANTS LLC. All rights reserved.
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Toast
import com.ahmadnaser.kotlinhrsystem.adapters.EmployeeAdapter
import com.ahmadnaser.kotlinhrsystem.entities.Employee
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_add_employee.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), AdapterView.OnItemLongClickListener {

    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {

        val dialog = AlertDialog.Builder(this).apply {

            setTitle("Delete Confirmation")
            setMessage("Are you sure you want to delete the item?")


            setPositiveButton("OK"){dialog, i ->

DeleteItem(position,"http://greenapis.net/api/employee/delete.php")


            }

            setNegativeButton("Cancel"){dialog, which ->


            }

            show()

        }



        return true
    }

    private fun  DeleteItem(index: Int, url: String) {




        val stringRequest = object : StringRequest(Request.Method.POST,url, Response.Listener<String>{ response ->


            val obj = JSONObject(response)

            Toast.makeText(applicationContext,obj.get("deleted").toString(), Toast.LENGTH_LONG).show()

            arrEmployee.removeAt(index)
            employeeAdapter?.notifyDataSetChanged()

        },object: Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {

                Toast.makeText(applicationContext,error?.message, Toast.LENGTH_LONG).show()

            }

        }) {

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String,String>()
                params.put("id",arrEmployee.get(index).id!!.toString())
                return params
            }
        }

        ANHRSystem.instance?.addToRequestQueue(stringRequest)

    }

    var arrEmployee:ArrayList<Employee> = ArrayList()
    var employeeAdapter:EmployeeAdapter? = null
    var URL_LISTALLEMPLOYEES = "http://greenapis.net/api/employee/allemployees.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


employeeAdapter = EmployeeAdapter(this,arrEmployee)
employee_list.adapter = employeeAdapter


        employee_list.onItemLongClickListener = this


        employee_list.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->

            val intent:Intent = Intent(this,EmployeeDetails::class.java)
            var tempEmployee = Employee()

            tempEmployee = arrEmployee.get(position)


            intent.putExtra("id",tempEmployee.id )
            intent.putExtra("name",tempEmployee.name )
            intent.putExtra("position",tempEmployee.position )
            intent.putExtra("salary",tempEmployee.salary )
            intent.putExtra("experience",tempEmployee.experience )

            startActivity(intent)

        }


LoadData()


        fab.setOnClickListener { view ->
         //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
             //       .setAction("Action", null).show()

            var myintent = Intent(this,AddEmployeeActivity::class.java)
            startActivity(myintent)
        }
    }

    private fun LoadData() {
        arrEmployee.clear()


        val req = JsonArrayRequest(Request.Method.GET,URL_LISTALLEMPLOYEES,null, Response.Listener{response ->

            try {


                for(i in 0..response.length()-1){
                    var row = response.getJSONObject(i)
                    var employee = Employee()


                    employee.id = row.getInt("id")
                    employee.name = row.getString("name")
                    employee.position = row.getString("position")
                    employee.salary = row.getString("salary")
                    employee.experience = row.getString("experience")+""

                    arrEmployee.add(employee)

                    Log.d("item","" + row.getString("name"))

                }

                employeeAdapter?.notifyDataSetChanged()


            }catch (e:Exception){
                Log.d("Exception", e.message)
                e.printStackTrace()
            }

        }, Response.ErrorListener { error ->
            Log.d("Exception in json reading operation", error.message)
        })

        ANHRSystem.instance?.addToRequestQueue(req)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if(item.title == "Refresh"){

            LoadData()

        }


        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
