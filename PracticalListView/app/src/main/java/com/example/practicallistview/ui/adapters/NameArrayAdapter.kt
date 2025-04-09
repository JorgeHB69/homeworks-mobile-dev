package com.example.practicallistview.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.practicallistview.R

class NameArrayAdapter(context: Context, resource: Int, objects: Array<out String>) :
    ArrayAdapter<String>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        if (row == null) {
            row = LayoutInflater.from(context)
                .inflate(R.layout.custom_item_list, parent, false)
        }
        val lblName = row?.findViewById<TextView>(R.id.lbl_name)
        lblName?.text = getItem(position)
        return row!!
    }
}