package com.example.binlist.bininfo.presentation.screens

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.binlist.bininfo.presentation.viewmodel.BinViewModel
import com.example.binlist.bininfo.ui.components.BinInfoCard
import com.example.binlist.bininfo.ui.components.BinInputField
import androidx.compose.ui.Alignment
import com.example.binlist.bininfo.ui.components.StyledButton

// Экран для ввода BIN и отображения информации
@Composable
fun BinInfoScreen(navController: NavController, viewModel: BinViewModel = hiltViewModel()) {
    var bin by remember { mutableStateOf("") }

    val binInfo by viewModel.binInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) { // Сбиваем фокус при клике
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Enter BIN",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))

            BinInputField(
                value = bin,
                onValueChange = { bin = it },
                label = "Введите BIN",
                placeholder = "Введите номер BIN из 6-8 цифр",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            StyledButton(
                onClick = {
                    if (bin.isNotBlank()) {
                        viewModel.getBinInfo(bin)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Поиск", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.Black
                )
            } else {
                binInfo?.let {
                    BinInfoCard(binInfo = it)
                }
            }

            error?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            StyledButton(
                onClick = {
                    navController.navigate("historyScreen")
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("История BIN", color = Color.White)
            }
        }
    }
}