package com.ahmadnaser.kotlinhrsystem
///Developed By Ahmad Naser Turnkey Solutions LLC - Greenbackend.com
///http://greenbackend.com/developer-hosting
///www.greenbackend.com
// www.khottah.com
///greenbackend@ahmadnaser.com
///copyright © 2018 ANTS LLC. All rights reserved.
import android.app.VoiceInteractor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_employee.*
import org.json.JSONObject

class AddEmployeeActivity : AppCompatActivity() {

    private val URL = "http://greenapis.net/api/employee/create.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        prgsEmployeeAdd.visibility = View.GONE

        btnEmployeeSave.setOnClickListener({
//call method
            saveData()
        })

    }

    private fun saveData() {

        prgsEmployeeAdd.visibility = View.VISIBLE


        val stringRequest = object : StringRequest(Request.Method.POST,URL, Response.Listener<String>{response ->

            prgsEmployeeAdd.visibility = View.GONE
            val obj = JSONObject(response)

            Toast.makeText(applicationContext,obj.get("name").toString(),Toast.LENGTH_LONG).show()
            editEmployeeName.text.clear()
            editEmployeeSalary.text.clear()
            editEmployeeExp.text.clear()
            editEmployeePos.text.clear()

        },object: Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                prgsEmployeeAdd.visibility = View.GONE
                Toast.makeText(applicationContext,error?.message,Toast.LENGTH_LONG).show()

            }

        }) {

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String,String>()
                params.put("name",editEmployeeName.text.toString())
                params.put("salary",editEmployeeSalary.text.toString())
                params.put("experience",editEmployeeExp.text.toString())
                params.put("position",editEmployeePos.text.toString())
                return params
            }
        }

        ANHRSystem.instance?.addToRequestQueue(stringRequest)

    }//end of save data
}
