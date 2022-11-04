package com.panassevich.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.panassevich.shoppinglist.R
import com.panassevich.shoppinglist.databinding.ItemShopDisabledBinding
import com.panassevich.shoppinglist.databinding.ItemShopEnabledBinding
import com.panassevich.shoppinglist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null

    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layoutId = when (viewType) {
            ACTIVE_LIST_POSITION -> R.layout.item_shop_enabled
            NON_ACTIVE_LIST_POSITION -> R.layout.item_shop_disabled
            else -> throw java.lang.RuntimeException("Unknown view type: $viewType")
        }

        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), layoutId, parent, false)
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding
        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        when(binding) {
            is ItemShopDisabledBinding -> {
                binding.shopItem = shopItem
            }
            is ItemShopEnabledBinding -> {
                binding.shopItem = shopItem
            }
        }


    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).enabled) {
            true -> ACTIVE_LIST_POSITION
            false -> NON_ACTIVE_LIST_POSITION
        }
    }

    companion object {
        const val ACTIVE_LIST_POSITION = 1
        const val NON_ACTIVE_LIST_POSITION = 2

        const val MAX_POOL_SIZE = 15
    }
}