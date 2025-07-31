package com.example.datasource.local.source.dao.product

import androidx.room.*
import com.example.datasource.local.source.entity.product.ProductImageEntity

@Dao
interface ProductImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<ProductImageEntity>)

    @Query("SELECT * FROM product_images WHERE productId = :productId")
    suspend fun getImagesForProduct(productId: Int): List<ProductImageEntity>

    @Query("DELETE FROM product_images WHERE productId = :productId")
    suspend fun deleteImagesForProduct(productId: Int)
}