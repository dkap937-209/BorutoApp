package com.dk.boruto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dk.boruto.ui.theme.BorutoTheme
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        AppCenter.start(
            application, getString(R.string.app_center_key),
            Analytics::class.java, Crashes::class.java
        )
        super.onCreate(savedInstanceState)
        setContent {
            BorutoTheme {

            }
        }
    }
}