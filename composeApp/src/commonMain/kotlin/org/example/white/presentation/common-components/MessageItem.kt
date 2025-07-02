package org.example.white.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.zIndex
import org.example.white.ui.theme.AppTheme

@Composable
fun MessageItem(
    isOwnMessage: Boolean,
    messageSenderName: String?,
    messageText: String?,
    messageDate: String?,
    onToggleSelect: () -> Unit

) {

    var isSelected by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = if (isOwnMessage) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    onToggleSelect()
                    isSelected = !isSelected
                }
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = if (isOwnMessage) Color(0xFF4CAF50) else Color(0xFFE0E0E0),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(12.dp)
                    .widthIn(max = 280.dp)
            ) {
                if (!isOwnMessage) {
                    Text(
                        text = messageSenderName ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

                Text(
                    text = messageText ?: "",
                    color = if (isOwnMessage) Color.White else Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = messageDate ?: "",
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = if (isOwnMessage) Color.White.copy(alpha = 0.7f) else Color.DarkGray,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Selected",
                    tint = if (!isOwnMessage) AppTheme.colorScheme.third else AppTheme.colorScheme.secondary,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(16.dp)
                        .zIndex(1f)
                )
            }
        }
    }
}
