package com.example.data.models

import com.google.gson.annotations.SerializedName

data class Flash(
    @SerializedName("flash_sale")
    val flashSale: List<Sale>
)
