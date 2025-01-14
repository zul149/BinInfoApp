package com.example.binlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.binlist.bininfo.presentation.screens.BinInfoScreen
import com.example.binlist.bininfo.presentation.screens.HistoryScreen
import com.example.binlist.ui.theme.BinlistTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BinInfoApp()
        }
    }
}

@Composable
fun BinInfoApp() {
    // Настройка навигации между экранами
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "binInfoScreen", modifier = Modifier.fillMaxSize()) {
        composable("binInfoScreen") {
            BinInfoScreen(navController)
        }
        composable("historyScreen") {
            HistoryScreen(navController)
        }
    }
}