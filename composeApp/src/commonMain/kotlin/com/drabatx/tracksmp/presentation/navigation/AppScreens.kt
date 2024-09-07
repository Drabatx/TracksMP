package com.drabatx.tracksmp.presentation.navigation

sealed class AppScreens(val route: String) {
    data object Login : AppScreens("login_screen")
    data object TrackList : AppScreens("tracks_screen")
    data object TrackDetail: AppScreens("tracks_detail/{track_id}") {
        fun setTrackId(trackID: Int) = "tracks_detail/$trackID"
    }
}