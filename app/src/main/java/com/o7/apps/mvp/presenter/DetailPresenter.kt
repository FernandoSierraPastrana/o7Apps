package com.o7.apps.mvp.presenter

import android.content.pm.ApplicationInfo
import com.o7.apps.base.Presenter
import com.o7.apps.mvp.view.DetailView

class DetailPresenter(view: DetailView) : Presenter<DetailView>(view) {

    fun init(appInfo: ApplicationInfo) {
        view.init(appInfo)
    }
}