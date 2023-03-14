package com.example.data.models

import com.google.gson.annotations.SerializedName

data class Latest(
    val category: String,
    val name: String,
    val price: Int,
    @SerializedName("image_url")
    val imageUrl: String
)