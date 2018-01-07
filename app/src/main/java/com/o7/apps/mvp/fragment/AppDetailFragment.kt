package com.o7.apps.mvp.fragment

import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.o7.apps.R
import com.o7.apps.mvp.presenter.DetailPresenter
import com.o7.apps.mvp.view.DetailView

class AppDetailFragment : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "app_detail_dialog"
        private const val ARGUMENT_APP_INFO = "app_info"

        fun newInstance(appInfo: ApplicationInfo): AppDetailFragment {
            val appDetailFragment = AppDetailFragment()
            val arguments = Bundle()
            arguments.putParcelable(ARGUMENT_APP_INFO, appInfo)
            appDetailFragment.arguments = arguments
            return appDetailFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_detail_dialog, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val appInfo = arguments.getParcelable(ARGUMENT_APP_INFO) as ApplicationInfo
        val presenter = DetailPresenter(DetailView(this))
        presenter.init(appInfo)
    }
}