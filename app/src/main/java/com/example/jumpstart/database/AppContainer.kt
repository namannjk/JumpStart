package com.example.jumpstart.database

import android.content.Context

interface AppContainer {
    val itemRepository: ItemRapository
}


class AppDataContainer(private val context: Context) : AppContainer {

    override val itemRepository: ItemRapository by lazy {
        ItemRapository(ItemDatabase.getDatabase(context).itemDAO())
    }
}
