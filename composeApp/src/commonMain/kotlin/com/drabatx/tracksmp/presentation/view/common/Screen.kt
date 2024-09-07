package com.drabatx.tracksmp.presentation.view.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Screen(content: @Composable () -> Unit) {
    val isDarkMode = isSystemInDarkTheme()
    val colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
    setSingletonImageLoaderFactory {
        ImageLoader.Builder(it)
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }
    MaterialTheme(colorScheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
}