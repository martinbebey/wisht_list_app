package com.developer.wishlistapp.data

data class Wish(
    val id: Long = 0L,
    val title: String = "",
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
