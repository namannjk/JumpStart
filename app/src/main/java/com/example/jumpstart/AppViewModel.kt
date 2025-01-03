package com.example.jumpstart

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpstart.database.ItemRapository
import com.example.jumpstart.models.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


data class HomeUiState(val itemList: List<Item> = listOf())

class AppViewModel(val itemRapository: ItemRapository) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val _homeUiState: StateFlow<HomeUiState> = itemRapository.getAllItems()
        .map { HomeUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    var fullItems = mutableStateOf(listOf<Item>())
    var items = mutableStateOf(listOf<Item>())
    var currentScreen = mutableIntStateOf(1)
    var isGoBack = mutableStateOf(false)
    var currentSearch = mutableStateOf("")
    val priceRange = mutableStateOf(0f..1000f)
    val shippingMethods = listOf("Same day shipping", "Other")
    val selectedMethod = mutableStateOf<Boolean?>(null)

    init {
        viewModelScope.launch {
            _homeUiState.collect { homeUiState ->
                fullItems.value = homeUiState.itemList
                items.value = homeUiState.itemList.toMutableList()
            }
        }
    }

    fun changeScren(screen: Int) {
        currentScreen.value = screen
    }

    fun addItem(item: Item) {
        viewModelScope.launch {
            itemRapository.insertItem(item)
        }
    }

    fun updateSearch(search: String) {
        currentSearch.value = search
        val filteredItems = if (search.isEmpty()) {
            fullItems.value
        } else {
            fullItems.value.filter { it.name.contains(search, ignoreCase = true) }
        }
        items.value = filteredItems.toMutableList()
    }

    fun applyFilters() {
        val filteredItems = fullItems.value.filter { item ->
            (selectedMethod.value == null || (item.shippingTime==selectedMethod.value) &&
                    (item.price in priceRange.value))
        }
        items.value = filteredItems.toMutableList()
        isGoBack.value = true
    }

    fun resetFilters() {
        selectedMethod.value = null
        priceRange.value = 0f..1000f
        items.value = fullItems.value.toMutableList()
        isGoBack.value = true
    }
}

//class AppViewModel(val itemRapository: ItemRapository) : ViewModel() {
//
//    companion object {
//        private const val TIMEOUT_MILLIS = 5_000L
//    }
//    val _homeUiState : StateFlow<HomeUiState> =  itemRapository.getAllItems().map { HomeUiState(it) }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//            initialValue = HomeUiState()
//        )
//
//
//    var fullItems  = mutableStateOf(_homeUiState.value.itemList)
//
//
//    var items = mutableStateOf(fullItems.value.toMutableList())
//
//    var currentScreen = mutableIntStateOf(1)
//    var isGoBack = mutableStateOf(false)
//    fun changeScren(screen : Int){
//        currentScreen.value = screen
//    }
//    var currentSearch = mutableStateOf("")
//
//
//    fun addItem(item: Item) {
//        viewModelScope.launch {
//            itemRapository.insertItem(item)
//        }
//    }
//
//    fun updateSearch(search: String) {
//        currentSearch.value = search
//
//        // Filter the fullItems list based on the search query
//        var filteredItems = if (search.isEmpty()) {
//            fullItems.value // Show all items if search query is empty
//        } else {
//            fullItems.value.filter { it.name.contains(search, ignoreCase = true) }
//        }
//
//        // Update the items list with the filtered results
//        items.value = filteredItems.toMutableList()
//    }
//
//    val priceRange =   mutableStateOf(0f..1000f)
//    val shippingMethods = listOf("Same day", "One day", "Two day")
//    val selectedMethod =   mutableStateOf<String?>(null)
//
//    fun applyFilters() {
//        // Filter the fullItems list based on the selected shipping method and price range
//        var filteredItems = fullItems.value.filter { item ->
//            (selectedMethod.value == null || item.shippingTime) && (item.price in priceRange.value)
//        }
//
//        // Update the items list with the filtered results
//        items.value = filteredItems.toMutableList()
//        isGoBack.value = true
//    }
//
//    fun resetFilters() {
//        // Reset the selected shipping method and price range
//        selectedMethod.value = null
//        priceRange.value = 0f..1000f
//
//        // Update the items list with the full list of items
//        items.value = fullItems.value.toMutableList()
//        isGoBack.value = true
//    }
//}