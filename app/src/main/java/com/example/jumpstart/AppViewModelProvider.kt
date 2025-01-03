package com.example.jumpstart

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ContactViewModel
        initializer {
            AppViewModel(
                itemApplication().appContainer.itemRepository
            )
        }
    }
}



fun CreationExtras.itemApplication(): ItemApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ItemApplication)