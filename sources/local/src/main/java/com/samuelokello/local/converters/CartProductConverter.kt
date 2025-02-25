package com.samuelokello.local.converters

import androidx.room.TypeConverter
import com.samuelokello.local.entity.CartProductEntity
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class CartProductConverter {
    @TypeConverter
    fun fromString(value: String): List<CartProductEntity> = Json.decodeFromString(ListSerializer(CartProductEntity.serializer()), value)

    @TypeConverter
    fun fromList(list: List<CartProductEntity>): String = Json.encodeToString(ListSerializer(CartProductEntity.serializer()), list)
}
