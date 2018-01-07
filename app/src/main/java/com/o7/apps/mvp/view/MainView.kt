package com.o7.apps.mvp.view

import android.content.pm.ApplicationInfo
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewStub
import com.o7.apps.R
import com.o7.apps.getAppsInstalled
import com.o7.apps.mvp.activity.MainActivity
import com.o7.apps.mvp.adapter.AppsAdapter
import com.o7.apps.mvp.fragment.AppDetailFragment
import io.reactivex.Observer
import java.lang.ref.WeakReference

class MainView(activity: MainActivity) {
    private val activityRef = WeakReference(activity)
    private lateinit var emptyView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AppsAdapter

    fun init(observer: Observer<ApplicationInfo>) {
        val activity = activityRef.get()
        if (activity != null) {
            emptyView = activity.findViewById(R.id.stub_main_empty)
            recyclerView = activity.findViewById(R.id.recycler_main_apps)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

            adapter = AppsAdapter(observer)
            recyclerView.adapter = adapter
        }
    }

    fun getInstalledApps(rootPackage: String? = null): List<ApplicationInfo> {
        return activityRef.get()?.getAppsInstalled(rootPackage) ?: listOf()
    }

    fun drawEmptyUI() {
        emptyView = (emptyView as ViewStub).inflate()
        recyclerView.visibility = View.GONE
        emptyView.visibility = View.VISIBLE
    }

    fun drawInstalledApps(installedApps: List<ApplicationInfo>) {
        emptyView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        adapter.addAll(installedApps)
        adapter.notifyDataSetChanged()
    }

    fun openAppDetail(appInfo: ApplicationInfo) {
        AppDetailFragment.newInstance(appInfo).show(activityRef.get()?.supportFragmentManager, AppDetailFragment.TAG)
    }
}