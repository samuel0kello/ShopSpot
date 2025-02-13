package com.samuelokello.network.auth.dto

import com.samuelokello.shopspot.data.network.auth.dto.Geolocation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerialName("city")
    val city: String,
    @SerialName("geolocation")
    val geolocation: Geolocation,
    @SerialName("number")
    val number: Int,
    @SerialName("street")
    val street: String,
    @SerialName("zipcode")
    val zipcode: String,
)
