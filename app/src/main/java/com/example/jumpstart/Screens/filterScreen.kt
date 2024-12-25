package com.example.jumpstart.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jumpstart.AppViewModel


@Composable
fun FilterScreen(
    appViewModel: AppViewModel,
    navController: NavHostController
)
{

    if (appViewModel.isGoBack.value) {
        navController.popBackStack()  // Navigate back to the previous screen
        appViewModel.isGoBack.value = false  // Reset go-back state
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ) {
        Text(
            text = "Filters",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Price Range Slider
        Text(text = "Price Range: ₹${appViewModel.priceRange.value.start.toInt()} - ₹${appViewModel.priceRange.value.endInclusive.toInt()}")
        RangeSlider(
            valueRange = 0f..1000f, // The range of values the slider can take
            value = appViewModel.priceRange.value, // The current selected range
            onValueChange = { appViewModel.priceRange.value = it }, // Updates the range when the slider changes
            modifier = Modifier.padding(vertical = 16.dp) // Padding for aesthetics
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Shipping Method Dropdown
        Text(text = "Shipping Method", fontSize = 16.sp, fontWeight = FontWeight.Medium)

        Column {
            appViewModel.shippingMethods.forEach { method ->
                DropdownMenuItem(
                    text = { Text(text = method,
                        fontWeight = if (appViewModel.selectedMethod.value == method) FontWeight.Bold else FontWeight.Normal) },
                    onClick = {
                        appViewModel.selectedMethod.value = method // Update the selected value
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp) // Add padding for better aesthetics
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Apply Filters Button

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                onClick = {
                    // Handle Apply Filters Logic
                    appViewModel.resetFilters()
                },
                modifier = Modifier
            ) {
                Text("Reset")
            }
            Button(
                onClick = {
                    // Handle Apply Filters Logic
                    appViewModel.applyFilters()
                },
                modifier = Modifier
            ) {
                Text("Apply")
            }
        }

    }
}



