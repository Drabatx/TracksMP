package com.drabatx.tracksmp.presentation.view.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import tracksmp.composeapp.generated.resources.Res
import tracksmp.composeapp.generated.resources.app_name

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String = "", navController: NavController, action: () -> Unit = {}, isMain: Boolean = true) {
    TopAppBar(
        title = {
            Text(
                text = if (title == "") stringResource(Res.string.app_name) else title,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1
            )
        },
        actions = {
            if (isMain){
                IconButton(onClick = action) {
                    Icon(
                        tint = MaterialTheme.colorScheme.onPrimary,
                        imageVector = Icons.Filled.Logout,
                        contentDescription = "logout"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

    )
}