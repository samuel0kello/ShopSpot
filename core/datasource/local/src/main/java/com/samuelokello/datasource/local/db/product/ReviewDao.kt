package com.samuelokello.datasource.local.db.product

import androidx.room.*
import com.samuelokello.datasource.local.entity.ReviewEntity

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<ReviewEntity>)

    @Query("SELECT * FROM reviews WHERE productId = :productId")
    suspend fun getReviewsForProduct(productId: Int): List<ReviewEntity>

    @Query("DELETE FROM reviews WHERE productId = :productId")
    suspend fun deleteReviewsForProduct(productId: Int)
}