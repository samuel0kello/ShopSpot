package com.samuelokello.datasource.local.db.product

import androidx.room.*
import com.samuelokello.datasource.local.entity.DimensionsEntity

@Dao
interface DimensionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDimensions(dimensions: DimensionsEntity)

    @Query("SELECT * FROM dimensions WHERE productId = :productId LIMIT 1")
    suspend fun getDimensionsForProduct(productId: Int): DimensionsEntity?

    @Query("DELETE FROM dimensions WHERE productId = :productId")
    suspend fun deleteDimensionsForProduct(productId: Int)
}