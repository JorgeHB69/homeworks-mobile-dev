package com.example.reciclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reciclerview.databinding.ListItemViewBinding

class NameAdapter(
    private var names: Array<String>
) : RecyclerView.Adapter<NameAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ListItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.lblName.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = names[position]
        holder.bind(item)
    }

    fun setData(names: Array<String>) {
        this.names = names
    }
}