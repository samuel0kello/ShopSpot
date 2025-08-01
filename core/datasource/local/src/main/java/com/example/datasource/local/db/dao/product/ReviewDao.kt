package com.example.datasource.local.db.dao.product

import androidx.room.*
import com.example.datasource.local.entity.ReviewEntity

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<ReviewEntity>)

    @Query("SELECT * FROM reviews WHERE productId = :productId")
    suspend fun getReviewsForProduct(productId: Int): List<ReviewEntity>

    @Query("DELETE FROM reviews WHERE productId = :productId")
    suspend fun deleteReviewsForProduct(productId: Int)
}