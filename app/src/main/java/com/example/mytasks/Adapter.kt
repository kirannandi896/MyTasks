package com.example.mytasks

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.viewHolder>() {
    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Title = itemView.findViewById<TextView>(R.id.title)
        var Priority = itemView.findViewById<TextView>(R.id.priority)
        var Layout = itemView.findViewById<LinearLayout>(R.id.mylayout)
        var Checked = itemView.findViewById<CheckBox>(R.id.isdone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        when (data[position].priority.toLowerCase()) {
            "high" -> holder.Layout.setBackgroundColor(Color.parseColor("#F16D6D"))
            "medium" -> holder.Layout.setBackgroundColor(Color.parseColor("#E8C47C"))
            else -> holder.Layout.setBackgroundColor(Color.parseColor("#7AD393"))
        }

        holder.Checked.setOnClickListener() {
            if (holder.Checked.isChecked) {
                holder.Layout.setBackgroundColor(Color.parseColor("#8A8585"))
                holder.Title.paintFlags = holder.Title.paintFlags or STRIKE_THRU_TEXT_FLAG
                data[position].done = true
            }
            else{
                holder.Title.paintFlags = holder.Title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
                data[position].done = false
                when (data[position].priority.toLowerCase()) {
                    "high" -> holder.Layout.setBackgroundColor(Color.parseColor("#F16D6D"))
                    "medium" -> holder.Layout.setBackgroundColor(Color.parseColor("#E8C47C"))
                    else -> holder.Layout.setBackgroundColor(Color.parseColor("#7AD393"))
                }
            }
        }

        holder.Title.text = data[position].title
        holder.Priority.text = data[position].priority
        holder.Checked.isChecked = data[position].done
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,UpdateCard::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int {
        return data.size
    }
}