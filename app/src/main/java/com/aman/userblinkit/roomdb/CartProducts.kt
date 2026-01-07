package com.aman.userblinkit.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CartProducts")
data class CartProducts(
    @PrimaryKey
    val productId: String = "random",
    val productName: String? = null,
    val productQuantity: String? = null,
    val productPrice: String? = null,
    val productCount: Int? = null,
    val productImage: String? = null,
    val productStock: Int? = null,
    val productCategory: String? = null,
    val adminUid: String? = null
)