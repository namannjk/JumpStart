package com.example.jumpstart

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jumpstart.models.Item

class AppViewModel : ViewModel() {
    var fullItems = mutableListOf<Item>()
    init {
        fullItems.add(Item(R.drawable.item1, "Item 1", 100.0, "Same day"))
        fullItems.add(Item(R.drawable.item1, "Item 2", 200.0, "Same day"))
        fullItems.add(Item(R.drawable.item1, "Item 3", 300.0, "Same day"))
        fullItems.add(Item(R.drawable.item1, "Item 4", 400.0, "Same day"))
        fullItems.add(Item(R.drawable.item1, "Item 5", 500.0, "Same day"))
        fullItems.add(Item(R.drawable.item1, "Item 6", 600.0, "Same day"))
        fullItems.add(Item(R.drawable.item1, "Item 7", 700.0, "Same day"))
        fullItems.add(Item(R.drawable.item1, "Item 8", 800.0, "Same day"))
        fullItems.add(Item(R.drawable.item1, "Item 9", 900.0, "One day"))
        fullItems.add(Item(R.drawable.item1, "Item 10", 1000.0, "One day"))
        fullItems.add(Item(R.drawable.item1, "Item 10", 1000.0, "One day"))
        fullItems.add(Item(R.drawable.item1, "Item 10", 1000.0, "One day"))
        fullItems.add(Item(R.drawable.item1, "Item 10", 1000.0, "One day"))
        fullItems.add(Item(R.drawable.item1, "Item 10", 1000.0, "One day"))
        fullItems.add(Item(R.drawable.item1, "Item 10", 1000.0, "Two day"))
        fullItems.add(Item(R.drawable.item1, "Item 10", 1000.0, "Two day"))
        fullItems.add(Item(R.drawable.item1, "Item 10", 1000.0, "Two day"))
        fullItems.add(Item(R.drawable.item1, "Item 10", 1000.0, "Two day"))
        fullItems.add(Item(R.drawable.item1, "Item 10", 1000.0, "Two day"))
        fullItems.add(Item(R.drawable.item1, "Item 10", 1000.0, "Two day"))
        fullItems.add(Item(R.drawable.item1, "Item 10", 1000.0, "Two day"))
    }

    var items = mutableStateOf(fullItems.toMutableList())

    var currentScreen = mutableIntStateOf(1)
    var isGoBack = mutableStateOf(false)
    fun changeScren(screen : Int){
        currentScreen.value = screen
    }
    var currentSearch = mutableStateOf("")


    fun updateSearch(search: String) {
        currentSearch.value = search

        // Filter the fullItems list based on the search query
        var filteredItems = if (search.isEmpty()) {
            fullItems // Show all items if search query is empty
        } else {
            fullItems.filter { it.title.contains(search, ignoreCase = true) }
        }

        // Update the items list with the filtered results
        items.value = filteredItems.toMutableList()
    }

    val priceRange =   mutableStateOf(0f..1000f)
    val shippingMethods = listOf("Same day", "One day", "Two day")
    val selectedMethod =   mutableStateOf<String?>(null)

    fun applyFilters() {
        // Filter the fullItems list based on the selected shipping method and price range
        var filteredItems = fullItems.filter { item ->
            (selectedMethod.value == null || item.shippingTime == selectedMethod.value) &&
                    (item.price in priceRange.value)
        }

        // Update the items list with the filtered results
        items.value = filteredItems.toMutableList()
        isGoBack.value = true
    }

    fun resetFilters() {
        // Reset the selected shipping method and price range
        selectedMethod.value = null
        priceRange.value = 0f..1000f

        // Update the items list with the full list of items
        items.value = fullItems.toMutableList()
        isGoBack.value = true
    }
}