package com.shinto.studentregistration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shinto.studentregistration.databinding.SearchRvItemBinding

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var oldData = emptyList<Note>()

    class MyViewHolder(val binding: SearchRvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SearchRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.itemTitle.text = oldData[position].noteTitle
        holder.binding.classId.text = oldData[position].noteSchool
        holder.binding.dob.text = oldData[position].noteDob
    }

    override fun getItemCount(): Int {
        return oldData.size
    }

    fun setData(newData: Unit) {
        //  oldData = newData
        notifyDataSetChanged()
    }

}
