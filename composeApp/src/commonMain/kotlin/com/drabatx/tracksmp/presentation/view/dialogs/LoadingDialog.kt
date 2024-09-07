package com.drabatx.tracksmp.presentation.view.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.drabatx.tracksmp.presentation.view.common.margin_big
import com.drabatx.tracksmp.presentation.view.common.margin_medium
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tracksmp.composeapp.generated.resources.Res
import tracksmp.composeapp.generated.resources.loading

@Preview
@Composable
fun LoadingDialogPreview() {
    LoadingDialog(true)
}

@Composable
fun LoadingDialog(isLoading: Boolean) {
    var showDialog by remember { mutableStateOf(isLoading) }
    Dialog(
        onDismissRequest = { showDialog = false },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment = Center,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(White, shape = RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier.padding(vertical = margin_big, horizontal = margin_medium)
            ) {
                CircularProgressIndicator()
                Text(
                    text = stringResource(Res.string.loading),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}


