package com.drabatx.tracksmp.presentation.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.drabatx.tracksmp.presentation.view.common.MainTopBar
import com.drabatx.tracksmp.presentation.view.common.Screen
import com.drabatx.tracksmp.presentation.view.common.margin_medium
import com.drabatx.tracksmp.presentation.view.dialogs.LoadingDialog
import com.drabatx.tracksmp.presentation.view.viewmodels.TrackDetailViewModel
import com.drabatx.tracksmp.utils.Result
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    trackId: Int,
    trackViewModel: TrackDetailViewModel= koinViewModel()
) {

    val trackState = trackViewModel.trackState.collectAsState()
    var nameUser by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        trackViewModel.getTrackById(trackId)
    }

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            topBar = {
                MainTopBar(title = nameUser, navController = navController, isMain = false)
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddingValues ->

            when(trackState.value){
                is Result.Loading -> {
                    LoadingDialog(isLoading = true)
                }
                is Result.Success -> {
                    val track = (trackState.value as Result.Success).data
                    nameUser = track.name
                    Column(modifier = Modifier.padding(paddingValues)) {
                        AsyncImage(
                            model = track.posterUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                        )
                        Text(
                            text = track.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = track.albumName,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(Modifier.padding(margin_medium))

                    }
                }
                else -> {}
            }

        }
    }
}