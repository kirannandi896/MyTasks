package com.example.mytasks

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.viewHolder>() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Title = itemView.findViewById<TextView>(R.id.title)
        var Priority = itemView.findViewById<TextView>(R.id.priority)
        var Layout = itemView.findViewById<LinearLayout>(R.id.mylayout)
        var Ftime = itemView.findViewById<TextView>(R.id.fr_time)
        var Ttime = itemView.findViewById<TextView>(R.id.to_time)
        var Fdate = itemView.findViewById<TextView>(R.id.fr_date)
        var Tdate = itemView.findViewById<TextView>(R.id.to_date)
        var done = itemView.findViewById<CheckBox>(R.id.checkdone)
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
        when (data[position].done) {
            true -> {
                holder.Layout.setBackgroundColor(Color.parseColor("#8A8585"))
                holder.Title.paintFlags = holder.Title.paintFlags or STRIKE_THRU_TEXT_FLAG

            }

            false -> {
                //do nothing
            }
        }


        holder.Title.text = data[position].title
        holder.Priority.text = data[position].priority
        holder.Ftime.text = data[position].ftime
        holder.Ttime.text = data[position].ttime
        holder.Fdate.text = data[position].fdate
        holder.Tdate.text = data[position].tdate
        holder.done.isChecked = data[position].done
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateCard::class.java)
            intent.putExtra("id", position)
            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

}