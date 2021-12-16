package com.example.parsexmlusingretrofitmac

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parsexmlusingretrofitmac.databinding.AppRowBinding

class AppAdapter(private var apps: ArrayList<String>): RecyclerView.Adapter<AppAdapter.AppViewHolder>() {
    class AppViewHolder(val binding: AppRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        return AppViewHolder(
            AppRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = apps[position]
        holder.binding.apply {
            tvnumber.text = "${position+1}"
            tvTitle.text = app
        }
    }

    override fun getItemCount() = apps.size

    fun update(apps: ArrayList<String>) {
        this.apps = apps
        notifyDataSetChanged()
    }

}