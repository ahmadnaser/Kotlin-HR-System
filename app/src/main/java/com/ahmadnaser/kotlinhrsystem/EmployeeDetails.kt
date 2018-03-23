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
import com.ahmadnaser.kotlinhrsystem.entities.Employee
import kotlinx.android.synthetic.main.activity_employee_details.*

class EmployeeDetails : AppCompatActivity() {

    var tempEmployee:Employee? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        val intent = intent
         tempEmployee = Employee()
        tempEmployee?.id = intent.getIntExtra("id",0)
        tempEmployee?.name = intent.getStringExtra("name")
        tempEmployee?.position = intent.getStringExtra("position")
        tempEmployee?.salary = intent.getStringExtra("salary")
        tempEmployee?.experience = intent.getStringExtra("experience")

        txtEmployeeName.text = tempEmployee?.name
        txtEmployeeExp.text = tempEmployee?.experience
        txtEmployeeSalary.text = tempEmployee?.salary
        txtEmployeePos.text = tempEmployee?.position
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    fun OpenEdit(view: View){

        val intent: Intent = Intent(this,EditEmployeeActivity::class.java)

        intent.putExtra("id",tempEmployee?.id )
        intent.putExtra("name",tempEmployee?.name )
        intent.putExtra("position",tempEmployee?.position )
        intent.putExtra("salary",tempEmployee?.salary )
        intent.putExtra("experience",tempEmployee?.experience )

        startActivity(intent)

    }



}
