package com.o7.apps.mvp.adapter

import android.content.pm.ApplicationInfo
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.o7.apps.R
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject

class AppsDelegate(observer: Observer<ApplicationInfo>) : RecyclerDelegate {
    private val clickSubject: PublishSubject<ApplicationInfo> = PublishSubject.create()

    init {
        clickSubject.subscribe(observer)
    }

    companion object {
        private class AppsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textName = itemView.findViewById(R.id.text_app_name) as TextView
            val imageIcon = itemView.findViewById(R.id.image_app) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder =
            AppsHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_apps, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: Any) {
        if (holder != null && holder is AppsHolder && item is ApplicationInfo) {
            val packageManager = holder.textName.context.packageManager
            holder.textName.text = packageManager.getApplicationLabel(item)
            holder.imageIcon.setImageDrawable(packageManager.getApplicationIcon(item))
            holder.itemView.setOnClickListener {
                clickSubject.onNext(item)
            }
        }
    }
}