package com.example.datanermobile.building

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.datanermobile.building.network.Building
import com.example.datanermobile.databinding.ListItemBuildingBinding

class BuildingAdapter(
    private val clickListener: BuildingListener
) : ListAdapter<Building, BuildingAdapter.ViewHolder>(BuildingDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
//        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(
        private val binding: ListItemBuildingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Building,
            clickListener: BuildingListener
        ) {
            binding.building = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemBuildingBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class BuildingDiffCallback : DiffUtil.ItemCallback<Building>() {

    override fun areItemsTheSame(oldItem: Building, newItem: Building): Boolean {
        return oldItem.buildingId == newItem.buildingId
    }

    override fun areContentsTheSame(oldItem: Building, newItem: Building): Boolean {
        return oldItem == newItem
    }

}

class BuildingListener(
    val clickListener: (sleepId: Int) -> Unit
) {
    fun onClick(building: Building) = clickListener(building.buildingId)
}

