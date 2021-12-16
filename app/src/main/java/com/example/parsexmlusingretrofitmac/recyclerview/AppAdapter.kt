package com.example.parsexmlusingretrofitmac.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parsexmlusingretrofitmac.view.CustomAlert
import com.example.parsexmlusingretrofitmac.databinding.AppRowBinding
import com.example.parsexmlusingretrofitmac.model.App
import com.squareup.picasso.Picasso

class AppAdapter(private var apps: ArrayList<App>, val context: Context): RecyclerView.Adapter<AppAdapter.AppViewHolder>() {
    class AppViewHolder(val binding: AppRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        return AppViewHolder(
            AppRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = apps[position]
        holder.binding.apply {
            tvTitle.text = app.name
            Picasso.get().load(app.image).into(imageViewMemberPhoto)

            cvApp.setOnClickListener {
                CustomAlert(context, app.title, app.summary)
            }
        }
    }

    override fun getItemCount() = apps.size

    fun update(apps: ArrayList<App>) {
        this.apps = apps
        notifyDataSetChanged()
    }

}