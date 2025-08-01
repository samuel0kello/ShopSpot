package com.samuelokello.shopspot.data

import com.samuelokello.shopspot.data.network.product.dto.ProductDto
import com.samuelokello.shopspot.data.network.product.dto.RatingDto
import com.samuelokello.shopspot.domain.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

val rating = RatingDto(4.0, 234)

object FakeDataSource {
    val productApiList =
        listOf(
            ProductDto(
                id = 1,
                title = "item1",
                price = 30.00,
                description = "item1 description",
                category = "kitchen",
                image = "img1",
                rating = rating,
            ),
            ProductDto(
                id = 2,
                title = "item2",
                price = 30.00,
                description = "item2 description",
                category = "kitchen",
                image = "img2",
                rating = rating,
            ),
            ProductDto(
                id = 3,
                title = "item2",
                price = 330.00,
                description = "item3 description",
                category = "kitchen",
                image = "img3",
                rating = rating,
            ),
        )

    val fakeProductsList: Flow<List<Product>> =
        flowOf(
            listOf(
                Product(
                    id = 1,
                    title = "item1",
                    price = 30.00,
                    description = "item1 description",
                    category = "kitchen",
                    image = "img1",
                    rating = 4.5,
                    count = 43,
                ),
                Product(
                    id = 2,
                    title = "item2",
                    price = 30.00,
                    description = "item2 description",
                    category = "kitchen",
                    image = "img2",
                    rating = 3.4,
                    count = 232,
                ),
                Product(
                    id = 3,
                    title = "item2",
                    price = 330.00,
                    description = "item3 description",
                    category = "kitchen",
                    image = "img3",
                    rating = 3.4,
                    count = 42,
                ),
            ),
        )
}