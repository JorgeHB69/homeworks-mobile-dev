package com.example.reciclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reciclerview.databinding.PersonItemViewBinding
import com.example.reciclerview.models.Person
import com.example.reciclerview.ui.adapters.PersonAdapter.ViewHolder

class PersonAdapter(
    private var data: MutableList<Person>
) : RecyclerView.Adapter<ViewHolder>() {
    private var listener: OnPersonClickListener? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PersonItemViewBinding.inflate(inflater, parent, false)
        return PersonAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(people: MutableList<Person>) {
        this.data = people
        notifyDataSetChanged()
    }

    fun setOnPersonClickListener(listener: OnPersonClickListener) {
        this.listener = listener
    }

    fun removeItem(index: Int) {
        notifyItemRemoved(index)
    }

    fun updateById(i: Int) {

    }

    class ViewHolder(private val binding: PersonItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Person, listener: OnPersonClickListener?) {
            binding.lblName.text = "${item.name} ${item.lastName}"
            binding.lblNumber.text = item.phone
            binding.root.setOnClickListener {
                listener?.onPersonClick(item)
            }
            binding.btnDelete.setOnClickListener {
                listener?.onDeletePerson(item)
            }
        }
    }

    interface OnPersonClickListener {
        fun onPersonClick(person: Person)
        fun onDeletePerson(person: Person)
    }
}