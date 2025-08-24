package com.samuelokello.datasource.local.db.product

import androidx.room.*
import com.samuelokello.datasource.local.entity.product.ProductImageEntity

@Dao
interface ProductImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<ProductImageEntity>)

    @Query("SELECT * FROM product_images WHERE productId = :productId")
    suspend fun getImagesForProduct(productId: Int): List<ProductImageEntity>

    @Query("DELETE FROM product_images WHERE productId = :productId")
    suspend fun deleteImagesForProduct(productId: Int)
}