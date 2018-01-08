package com.o7.apps

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import java.text.SimpleDateFormat
import java.util.*

fun Context.getAppsInstalled(rootPackage: String? = null): List<ApplicationInfo> {
    var installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    if (rootPackage != null) {
        installedApps = installedApps.filter { it.packageName.startsWith(rootPackage) }
    }
    return installedApps
}

fun Long.format(): String {
    val formatter = SimpleDateFormat("dd MMMM yyyy hh:mm aaa", Locale.getDefault())
    return formatter.format(Date(this))
}