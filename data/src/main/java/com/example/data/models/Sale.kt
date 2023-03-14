package com.example.data.models

import com.google.gson.annotations.SerializedName

data class Sale(
    val category: String,
    val name: String,
    val price: Double,
    val discount: Int,
    @SerializedName("image_url")
    val imageUrl: String
)