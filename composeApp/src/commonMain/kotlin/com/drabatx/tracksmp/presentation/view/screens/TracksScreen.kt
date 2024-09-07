package com.drabatx.tracksmp.presentation.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.drabatx.tracksmp.data.model.response.Track
import com.drabatx.tracksmp.data.model.response.TracksResponse
import com.drabatx.tracksmp.presentation.navigation.AppScreens
import com.drabatx.tracksmp.presentation.view.common.MainTopBar
import com.drabatx.tracksmp.presentation.view.common.Screen
import com.drabatx.tracksmp.presentation.view.common.margin_small
import com.drabatx.tracksmp.presentation.view.dialogs.LoadingDialog
import com.drabatx.tracksmp.presentation.view.dialogs.MessageDialog
import com.drabatx.tracksmp.presentation.view.item_list.TrackItem
import com.drabatx.tracksmp.presentation.view.viewmodels.TrackViewModel
import com.drabatx.tracksmp.utils.Result
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tracksmp.composeapp.generated.resources.Res
import tracksmp.composeapp.generated.resources.no_results
import tracksmp.composeapp.generated.resources.no_results_message

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
@Composable
fun TracksScreen(viewModel: TrackViewModel = koinViewModel(), navController: NavController) {
    val state = viewModel.uiState.collectAsState()
    val user = viewModel.nameUser.collectAsState()
    val logoutState = viewModel.logoutState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCurrentUser()
    }

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        if (logoutState.value) {
            navController.navigate(AppScreens.Login.route) {
                popUpTo(AppScreens.TrackList.route) {
                    inclusive = true
                }
            }
        }
        Scaffold(
            topBar = {
                MainTopBar("Bienvenido: ${user.value}", navController = navController, action = {
                    println(".......---------------------------------------.....................")
                    viewModel.logout()

                })
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddingValues ->
            when (state.value) {
                is Result.Loading -> { /* Mostrar indicador de carga */
                    LoadingDialog(isLoading = true)
                }

                is Result.Success -> {
                    val response = (state.value as Result.Success<TracksResponse>).data

                    response.tracks.let {
                        TrackList(
                            modifier = Modifier.padding(paddingValues),
                            tracks = it,
                            navController = navController
                        )
                    }
                }

                is Result.Error -> {
                    MessageDialog(
                        title = stringResource(Res.string.no_results),
                        text = stringResource(Res.string.no_results_message),
                        showDialog = true,
                    )
                }

                else -> {}
            }
        }
    }
}

@Composable
fun TrackList(tracks: List<Track>, modifier: Modifier, navController: NavController) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(margin_small)) {
        items(tracks.size) {
            TrackItem(tracks[it], navController)
        }
    }
}