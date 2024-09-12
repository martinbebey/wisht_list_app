package com.developer.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(

    @PrimaryKey(autoGenerate = true)//auto increment on add
    val id: Long = 0L,

    @ColumnInfo(name="wish-title")
    val title: String = "",

    @ColumnInfo(name = "wish-desc")
    val description: String = ""
)
object DummyWish{
    val wishList = listOf(
        Wish(title = "Goole Watch 2", description = "Android Watch"),
        Wish(title = "dog", description = "a dog"),
        Wish(title = "Fitness goals", description = "Olympia"),
        Wish(title = "Marathon", description = "26.2")
    )
}
