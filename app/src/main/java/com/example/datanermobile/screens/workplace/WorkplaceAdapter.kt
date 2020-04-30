package com.example.datanermobile.screens.workplace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datanermobile.R
import com.example.datanermobile.screens.workplace.data.Workplace


class WorkplaceAdapter: RecyclerView.Adapter<ViewHolder>() {

    val data = listOf(
        Workplace(1, 1, 1, "Biblioteca", 1, "20/22"),
        Workplace(1, 1, 2, "SALA 1", 2, "5/5"),
        Workplace(1, 2, 3, "SALA 2", 3, "0/22"),
        Workplace(1, 2, 4, "SALA 3", 4, "12/20"),
        Workplace(1, 3, 5, "SALA 4", 5, "21/22"),
        Workplace(1, 3, 6, "SALA 5", 6, "2/22"),
        Workplace(1, 3, 7, "SALA 6", 7, "20/22"),
        Workplace(1, 4, 8, "SALA 7", 8, "5/5"),
        Workplace(1, 4, 9, "SALA 8", 9, "0/22"),
        Workplace(1, 4, 10, "SALA 9", 10, "12/20"),
        Workplace(1, 4, 11, "SALA 10", 11, "21/22"),
        Workplace(1, 5, 12, "SALA 11", 12, "2/22"),
        Workplace(1, 5, 13, "SALA 12", 13, "20/22"),
        Workplace(1, 5, 14, "SALA 13", 14, "5/5"),
        Workplace(1, 6, 15, "SALA 14", 15, "0/22"),
        Workplace(1, 6, 16, "SALA 15", 16, "12/20"),
        Workplace(1, 6, 17, "SALA 16", 17, "21/22"),
        Workplace(1, 6, 18, "SALA 17", 18, "2/22")

    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.floorNumber.text = item.number.toString()
        holder.workplaceName.text = item.description
        holder.devices.text = item.deviceState
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_workplace, parent, false)

        return ViewHolder(view)
    }
}

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val floorNumber: TextView = itemView.findViewById(R.id.floor_number_tv)
    val workplaceName: TextView = itemView.findViewById(R.id.workplace_name_tv)
    val devices: TextView = itemView.findViewById(R.id.devicesState_tv)
}