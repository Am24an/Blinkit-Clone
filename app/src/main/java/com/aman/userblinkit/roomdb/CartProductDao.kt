package com.aman.userblinkit.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartProductDao {
    @Insert
    fun insertCartProduct(products: CartProducts)

    @Update
    fun updateCartProducts(products: CartProducts)

    @Query("DELETE FROM CartProducts WHERE productId = :productId")
    fun deleteCartProduct(productId: String)




}