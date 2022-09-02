package com.example.shoppinglist.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.DeleteShopItemUseCase
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopListUseCase
import com.example.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel() {

    private val shopListRepository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(shopListRepository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(shopListRepository)
    private val editShopItemUseCase = EditShopItemUseCase(shopListRepository)

    var shopList = getShopListUseCase.getShopList()


    fun deleteShopItem(item: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(item)

    }

    fun changeEnableState(item: ShopItem) {
       val newShopItem = item.copy(enabled = !item.enabled)
        editShopItemUseCase.editShopItem(newShopItem)
    }





}