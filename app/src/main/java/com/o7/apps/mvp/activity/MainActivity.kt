package com.o7.apps.mvp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.o7.apps.R
import com.o7.apps.mvp.presenter.MainPresenter
import com.o7.apps.mvp.view.MainView

class MainActivity : AppCompatActivity() {
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(MainView(this))
        presenter.init()
    }
}
