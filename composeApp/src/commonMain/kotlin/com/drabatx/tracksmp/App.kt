package com.drabatx.tracksmp

import androidx.compose.runtime.Composable
import com.drabatx.tracksmp.presentation.navigation.AppNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        AppNavigation()
    }
}