package com.drabatx.tracksmp.presentation.view.item_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.drabatx.tracksmp.data.model.response.Track
import com.drabatx.tracksmp.presentation.navigation.AppScreens
import com.drabatx.tracksmp.presentation.view.common.margin_medium

@Composable
fun TrackItem(track: Track, navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(MaterialTheme.shapes.small).background(MaterialTheme.colorScheme.primaryContainer).clickable {
            navController.navigate(AppScreens.TrackDetail.setTrackId(track.phonogramId))
        }
            .padding(margin_medium)
    ) {
        AsyncImage(
            model = track.featuredPosterUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(90.dp)
                .aspectRatio(1f)
        )
        Spacer(Modifier.padding(margin_medium))
        Column() {
            Text(text = track.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
            Text(text = track.name, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}