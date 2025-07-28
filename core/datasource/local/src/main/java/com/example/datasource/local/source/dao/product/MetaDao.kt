package com.example.datasource.local.source.dao.product

import androidx.room.*
import com.example.datasource.local.source.entity.MetaEntity

@Dao
interface MetaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeta(meta: MetaEntity)

    @Query("SELECT * FROM meta WHERE productId = :productId LIMIT 1")
    suspend fun getMetaForProduct(productId: Int): MetaEntity?

    @Query("DELETE FROM meta WHERE productId = :productId")
    suspend fun deleteMetaForProduct(productId: Int)
}