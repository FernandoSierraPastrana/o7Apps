package com.o7.apps.mvp.adapter

import android.content.pm.ApplicationInfo
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.ViewGroup
import io.reactivex.Observer

class AppsAdapter(observer: Observer<ApplicationInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val DEFAULT_TYPE = 0
    }

    private val dataSet = mutableListOf<ApplicationInfo>()
    private val delegates = SparseArray<RecyclerDelegate>()

    init {
        delegates.put(DEFAULT_TYPE, AppsDelegate(observer))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        delegates[getItemViewType(position)].onBindViewHolder(holder, dataSet[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            delegates[viewType].onCreateViewHolder(parent)

    override fun getItemViewType(position: Int): Int = DEFAULT_TYPE

    override fun getItemCount(): Int = dataSet.size

    fun addAll(items: List<ApplicationInfo>) {
        dataSet.clear()
        dataSet.addAll(items)
    }
}