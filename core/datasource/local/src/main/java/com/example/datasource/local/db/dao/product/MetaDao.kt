package com.example.datasource.local.db.dao.product

import androidx.room.*
import com.example.datasource.local.entity.MetaEntity

@Dao
interface MetaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeta(meta: MetaEntity)

    @Query("SELECT * FROM meta WHERE productId = :productId LIMIT 1")
    suspend fun getMetaForProduct(productId: Int): MetaEntity?

    @Query("DELETE FROM meta WHERE productId = :productId")
    suspend fun deleteMetaForProduct(productId: Int)
}