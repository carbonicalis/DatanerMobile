package com.example.datanermobile.device

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.datanermobile.databinding.ListItemDeviceBinding
import com.example.datanermobile.device.network.AllWorkplaceDevices

class DeviceAdapter(
    private val clickListener: DeviceListener
) : ListAdapter<AllWorkplaceDevices, DeviceAdapter.ViewHolder>(BuildingDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(
        private val binding: ListItemDeviceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: AllWorkplaceDevices,
            clickListener: DeviceListener
        ) {
            binding.device = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemDeviceBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class BuildingDiffCallback : DiffUtil.ItemCallback<AllWorkplaceDevices>() {

    override fun areItemsTheSame(oldItem: AllWorkplaceDevices, newItem: AllWorkplaceDevices): Boolean {
        return oldItem.deviceId == newItem.deviceId
    }

    override fun areContentsTheSame(oldItem: AllWorkplaceDevices, newItem: AllWorkplaceDevices): Boolean {
        return oldItem == newItem
    }

}

class DeviceListener(
    val clickListener: (device: AllWorkplaceDevices) -> Unit
) {
    fun onClick(device: AllWorkplaceDevices) = clickListener(device)
}
