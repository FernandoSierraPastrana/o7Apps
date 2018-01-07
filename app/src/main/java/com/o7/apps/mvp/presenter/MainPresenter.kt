package com.o7.apps.mvp.presenter

import android.content.pm.ApplicationInfo
import com.o7.apps.base.DisposableObserverAdapter
import com.o7.apps.base.Presenter
import com.o7.apps.mvp.view.MainView

class MainPresenter(view: MainView) : Presenter<MainView>(view) {
    companion object {
        private const val PACKAGE_ROOT = "com.outfit7"
    }

    fun init() {
        view.init(object : DisposableObserverAdapter<ApplicationInfo>() {
            override fun onNext(item: ApplicationInfo) {
                view.openAppDetail(item)
            }
        })
        val appsInstalled = view.getInstalledApps(PACKAGE_ROOT)
        if (appsInstalled.isEmpty()) {
            view.drawEmptyUI()
        } else {
            view.drawInstalledApps(appsInstalled)
        }
    }
}