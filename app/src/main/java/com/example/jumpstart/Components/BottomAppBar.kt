package com.example.jumpstart.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jumpstart.AppViewModel

@Composable
fun BottomAppBar(appViewModel: AppViewModel, navController: NavHostController) {
    Row(
        modifier = Modifier
            .background(Color(0xFFFAFAFA))
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { appViewModel.changeScren(1)
                navController.navigate("listView")},
            colors   = ButtonDefaults.buttonColors(if (appViewModel.currentScreen.value == 1) Color(0xFF599c6b) else Color(0xffe8e8e8)),
            modifier = Modifier
                .size(40.dp) // Ensures the button is circular
                .clip(CircleShape), // Clips the button to a circular shape
            shape = CircleShape ,

        ) {
        }
        Button(
            onClick = {
                appViewModel.changeScren(2)
                navController.navigate("gridView") },
            colors   = ButtonDefaults.buttonColors(if (appViewModel.currentScreen.value == 2) Color(0xFF599c6b) else Color(0xffe8e8e8)),

            modifier = Modifier
                .size(40.dp) // Ensures the button is circular
                .clip(CircleShape), // Clips the button to a circular shape
            shape = CircleShape,

        ) {
        }
        Button(
            onClick = {
                appViewModel.changeScren(3)
                navController.navigate("addItem")},
            colors   = ButtonDefaults.buttonColors(if (appViewModel.currentScreen.value == 3) Color(0xFF599c6b) else Color(0xffe8e8e8)),
            modifier = Modifier
                .size(40.dp) // Ensures the button is circular
                .clip(CircleShape), // Clips the button to a circular shape
            shape = CircleShape
        ) {
        }
    }
}
