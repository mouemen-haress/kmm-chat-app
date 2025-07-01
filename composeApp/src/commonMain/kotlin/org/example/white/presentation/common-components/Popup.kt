package org.example.white.presentation.`common-components`

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun Popup(text: String?, hidePopupCallBack: () -> Unit) {
    AlertDialog(
        onDismissRequest = { hidePopupCallBack() },
        title = { Text("Confirm") },
        text = { Text(text ?: "....") },
        confirmButton = {
            TextButton(onClick = { hidePopupCallBack() }) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = { hidePopupCallBack() }) {
                Text("No")
            }
        }
    )
}