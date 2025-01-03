package com.example.jumpstart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jumpstart.Screens.AddItem
import com.example.jumpstart.Screens.FilterScreen
import com.example.jumpstart.Screens.GridView
import com.example.jumpstart.Screens.ListView
import com.example.jumpstart.ui.theme.JumpStartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JumpStartTheme {
                App()
            }
        }
    }
}

@Composable
fun App(
    navController: NavHostController = rememberNavController()
) {
    val appViewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)
    NavHost(navController = navController, startDestination = "listView") {
    composable("listView") {
        ListView(appViewModel,navController)
    }
    composable("gridView") {
        GridView(appViewModel,navController)
    }
    composable("addItem") {
        AddItem(appViewModel,navController)
    }
    composable("filterScreen") {
        FilterScreen(appViewModel,navController)
    }
}

}
