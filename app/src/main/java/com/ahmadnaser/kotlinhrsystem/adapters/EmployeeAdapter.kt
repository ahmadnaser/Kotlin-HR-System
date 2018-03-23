package com.ahmadnaser.kotlinhrsystem.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ahmadnaser.kotlinhrsystem.R
import com.ahmadnaser.kotlinhrsystem.entities.Employee

///Developed By Ahmad Naser Turnkey Solutions LLC - Greenbackend.com
///http://greenbackend.com/developer-hosting
///www.greenbackend.com
// www.khottah.com
///greenbackend@ahmadnaser.com
///copyright © 2018 ANTS LLC. All rights reserved.

class EmployeeAdapter(var context: Context,var arrEmployees:ArrayList<Employee>) : BaseAdapter() {

    class ViewHolder(row:View){

        var employeeName:TextView
        init{
            employeeName = row.findViewById(R.id.employeeName)
        }
    }

    override fun getView(index: Int, convertView: View?, parent: ViewGroup?): View {
       var view:View?
        var viewholder:ViewHolder

        if(convertView == null){
            var layoutinflater:LayoutInflater = LayoutInflater.from(context)
            view = layoutinflater.inflate(R.layout.employee_row,null)
            viewholder = ViewHolder(view)
            view.tag = viewholder
        }else {
            view = convertView
            viewholder = convertView.tag as ViewHolder
        }

        var employee:Employee = getItem(index) as Employee
        viewholder.employeeName.text = employee.name

        return view as View
    }

    override fun getItem(i: Int): Any {
       return arrEmployees.get(i)
    }

    override fun getItemId(p0: Int): Long {
        if(p0 == 0)
       return 0
        else
        return  p0.toLong()
    }


    override fun getCount(): Int {
       return arrEmployees.size
    }


}