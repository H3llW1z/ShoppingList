package com.panassevich.shoppinglist.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItem(id: Int) = shopListRepository.getShopItem(id)
}