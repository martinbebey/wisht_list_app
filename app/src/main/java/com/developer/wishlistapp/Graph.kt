package com.developer.wishlistapp

import android.content.Context
import androidx.room.Room
import com.developer.wishlistapp.data.WishDatabase
import com.developer.wishlistapp.data.WishRepository

//to init the db
object Graph {
    private lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.db").build()
    }
}