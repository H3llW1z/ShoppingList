package com.panassevich.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.panassevich.shoppinglist.domain.ShopItem
import com.panassevich.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private var autoIncrementId = 0
    private var shopList = MutableLiveData<List<ShopItem>>()

    init {
        shopList.value = listOf()
        for (i in 0..10) {
            val item = ShopItem("Name $i", i, true)
            addShopItem(item)
        }

    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        val list = shopList.value
        shopList.value = list?.plus(shopItem)
    }

    override fun removeShopItem(shopItem: ShopItem) {
        val list = shopList.value
        shopList.value = list?.filter { it.id != shopItem.id }
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList.value?.find { it.id == id }
            ?: throw java.lang.RuntimeException("Element with id $id not found")

    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopList
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        removeShopItem(oldElement)
        addShopItem(shopItem)
    }
}