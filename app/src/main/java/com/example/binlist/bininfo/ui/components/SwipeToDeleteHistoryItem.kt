package com.example.binlist.bininfo.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.binlist.bininfo.data.BinHistory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDeleteHistoryItem(binHistory: BinHistory, onDelete: (BinHistory) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val swipeToDismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { state ->
            if (state == SwipeToDismissBoxValue.StartToEnd) {
                coroutineScope.launch {
                    onDelete(binHistory) // Удаление записи
                }
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = swipeToDismissState,
        backgroundContent = {
            val backgroundColor = animateColorAsState(
                targetValue = when (swipeToDismissState.targetValue) {
                    SwipeToDismissBoxValue.StartToEnd -> Color.Red
                    else -> MaterialTheme.colorScheme.background
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .background(backgroundColor.value)
                    .padding(start = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        content = {
            HistoryItem(binHistory = binHistory) // Контент записи
        },
        enableDismissFromStartToEnd = true,
        enableDismissFromEndToStart= false,
    )
}