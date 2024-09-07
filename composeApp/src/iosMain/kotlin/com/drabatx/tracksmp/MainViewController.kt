package com.drabatx.tracksmp

import androidx.compose.ui.window.ComposeUIViewController
import com.drabatx.tracksmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin {  }
    }
) { App() }