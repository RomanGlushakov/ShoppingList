package com.example.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository
import kotlin.random.Random

class ShopListRepositoryImpl(
    application: Application
): ShopListRepository {

    private val shopListDao =  AppDataBase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()



    override suspend fun addShopItem(shopItem: ShopItem) {
        val item = mapper.mapEntityToDbModel(shopItem)
        shopListDao.addShopItem(item)
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
       shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        val item = mapper.mapEntityToDbModel(shopItem)
        shopListDao.addShopItem(item)
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }


    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.
    map(shopListDao.getShopList()){
        mapper.mapListDbModelToListEntity(it)
    }


}