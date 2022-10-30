package com.panassevich.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.panassevich.shoppinglist.data.ShopListRepositoryImpl
import com.panassevich.shoppinglist.domain.EditShopItemUseCase
import com.panassevich.shoppinglist.domain.GetShopListUseCase
import com.panassevich.shoppinglist.domain.RemoveShopItemUseCase
import com.panassevich.shoppinglist.domain.ShopItem


class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem) {
        removeShopItemUseCase.removeShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}