package com.o7.apps.mvp.view

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.o7.apps.R
import com.o7.apps.format
import com.o7.apps.mvp.fragment.AppDetailFragment
import java.lang.ref.WeakReference

class DetailView(fragment: AppDetailFragment) {
    val fragmentRef = WeakReference(fragment)

    fun init(appInfo: ApplicationInfo) {
        val fragment = fragmentRef.get()
        if (fragment != null) {
            val imageIcon = fragment.view?.findViewById(R.id.image_detail_app) as ImageView
            val textName = fragment.view?.findViewById(R.id.text_detail_name) as TextView
            val textPackage = fragment.view?.findViewById(R.id.text_detail_package) as TextView
            val textOther = fragment.view?.findViewById(R.id.text_detail_others) as TextView
            val buttonOpen = fragment.view?.findViewById(R.id.button_detail_open) as Button

            val packageManager = fragment.context.packageManager
            imageIcon.setImageDrawable(packageManager.getApplicationIcon(appInfo))
            textName.text = packageManager.getApplicationLabel(appInfo)
            textPackage.text = appInfo.packageName
            textOther.text = buildOtherInfoString(fragment, packageManager, appInfo)
            buttonOpen.setOnClickListener {
                fragment.startActivity(packageManager.getLaunchIntentForPackage(appInfo.packageName))
            }
        }
    }

    private fun buildOtherInfoString(fragment: AppDetailFragment, packageManager: PackageManager, appInfo: ApplicationInfo): String {
        val packageInfo = packageManager.getPackageInfo(appInfo.packageName, PackageManager.GET_PERMISSIONS)
        val stringBuilder = StringBuilder()
        stringBuilder.append(fragment.getString(R.string.version_code_template).format(packageInfo.versionCode))
        stringBuilder.append(fragment.getString(R.string.version_name_template).format(packageInfo.versionName))
        stringBuilder.append(fragment.getString(R.string.last_update_template).format(packageInfo.lastUpdateTime.format()))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stringBuilder.append(fragment.getString(R.string.min_sdk_template).format(appInfo.minSdkVersion))
        }
        stringBuilder.append(fragment.getString(R.string.target_sdk_template).format(appInfo.targetSdkVersion))
        return stringBuilder.toString()
    }
}