package com.developer.wishlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {

    suspend fun addAWish(wish:Wish){
        wishDao.addWish(wish)
    }

    fun getWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getAWish(id: Long): Flow<Wish>{
        return wishDao.getAWishById(id)
    }

    suspend fun updateAWish(wish: Wish){
        wishDao.updateAWish(wish)
    }

    suspend fun deleteAWish(wish: Wish){
        wishDao.deleteAWish(wish)
    }
}