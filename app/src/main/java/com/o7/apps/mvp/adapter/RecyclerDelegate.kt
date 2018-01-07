package com.o7.apps.mvp.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

interface RecyclerDelegate {

    fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: Any)
}