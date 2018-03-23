package com.ahmadnaser.kotlinhrsystem
///Developed By Ahmad Naser Turnkey Solutions LLC - Greenbackend.com
///http://greenbackend.com/developer-hosting
///www.greenbackend.com
// www.khottah.com
///greenbackend@ahmadnaser.com
///copyright © 2018 ANTS LLC. All rights reserved.
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ahmadnaser.kotlinhrsystem.entities.Employee
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_add_employee.*
import kotlinx.android.synthetic.main.activity_edit_employee.*
import kotlinx.android.synthetic.main.activity_employee_details.*
import org.json.JSONObject

class EditEmployeeActivity : AppCompatActivity() {
    var tempEmployee:Employee? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_employee)

        val intent = intent
        tempEmployee = Employee()
        tempEmployee?.id = intent.getIntExtra("id",0)
        tempEmployee?.name = intent.getStringExtra("name")
        tempEmployee?.position = intent.getStringExtra("position")
        tempEmployee?.salary = intent.getStringExtra("salary")
        tempEmployee?.experience = intent.getStringExtra("experience")

        updateEmployeeName.setText( tempEmployee?.name)
        updateEmployeeExp.setText(  tempEmployee?.experience)
        updateEmployeeSalary.setText( tempEmployee?.salary)
        updateEmployeePos.setText(  tempEmployee?.position)

        prgsEmployeeUpdate.visibility = View.GONE

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    fun EditEmployee(view:View){



        tempEmployee?.name = updateEmployeeName.text.toString()
        tempEmployee?.position = updateEmployeePos.text.toString()
        tempEmployee?.salary = updateEmployeeSalary.text.toString()
        tempEmployee?.experience =updateEmployeeExp.text.toString()


     SaveUpdate(tempEmployee,"http://greenapis.net/api/employee/edit.php")

    }



     fun  SaveUpdate(tempEmployee: Employee?,URL:String) {


         prgsEmployeeUpdate.visibility = View.VISIBLE


         val stringRequest = object : StringRequest(Request.Method.POST,URL, Response.Listener<String>{ response ->


             try {


                 prgsEmployeeUpdate.visibility = View.GONE
                 val obj = JSONObject(response)

                 Toast.makeText(applicationContext,obj.get("updated").toString(), Toast.LENGTH_LONG).show()

                 val intent: Intent = Intent(this,MainActivity::class.java)
                 startActivity(intent)

             }catch (ex:Exception){

                 Toast.makeText(applicationContext,ex.message, Toast.LENGTH_LONG).show()
             }




         },object: Response.ErrorListener{
             override fun onErrorResponse(error: VolleyError?) {
                 prgsEmployeeUpdate.visibility = View.GONE
                 Toast.makeText(applicationContext,error?.message, Toast.LENGTH_LONG).show()

             }

         }) {

             override fun getParams(): MutableMap<String, String> {
                 val params = HashMap<String,String>()
                 params.put("id",tempEmployee?.id.toString())
                 params.put("name",tempEmployee?.name.toString())
                 params.put("salary",tempEmployee?.salary.toString())
                 params.put("experience",tempEmployee?.experience.toString())
                 params.put("position",tempEmployee?.position.toString())
                 return params
             }
         }

         ANHRSystem.instance?.addToRequestQueue(stringRequest)


    }

}
