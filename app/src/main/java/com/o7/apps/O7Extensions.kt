package com.o7.apps

import android.content.Context
import android.content.pm.ApplicationInfo

fun Context.getAppsInstalled(rootPackage: String? = null): List<ApplicationInfo> {
    var installedApps = packageManager.getInstalledApplications(0)
    if (rootPackage != null) {
        installedApps = installedApps.filter { it.packageName.startsWith(rootPackage) }
    }
    return installedApps
}