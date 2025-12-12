package com.example.myapplication.models


data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    var quantity: Int = 0,
    val imageUrl: String
)