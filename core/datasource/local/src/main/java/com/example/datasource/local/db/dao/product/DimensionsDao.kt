package com.example.datasource.local.db.dao.product

import androidx.room.*
import com.example.datasource.local.entity.DimensionsEntity

@Dao
interface DimensionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDimensions(dimensions: DimensionsEntity)

    @Query("SELECT * FROM dimensions WHERE productId = :productId LIMIT 1")
    suspend fun getDimensionsForProduct(productId: Int): DimensionsEntity?

    @Query("DELETE FROM dimensions WHERE productId = :productId")
    suspend fun deleteDimensionsForProduct(productId: Int)
}