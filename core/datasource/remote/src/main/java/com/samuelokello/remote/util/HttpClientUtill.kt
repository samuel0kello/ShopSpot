package com.samuelokello.remote.util

import android.util.Log
import com.samuelokello.core.domain.util.DataError
import com.samuelokello.core.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

internal suspend inline fun <reified Res : Any> HttpClient.get(
    path: String,
    queryParams: Map<String, Any?> = emptyMap(),
): Result<Res, DataError.Network> =
    safeApiCall {
        get {
            url(createRoute(path))
            queryParams.map { (key, value) ->
                parameter(key, value)
            }
        }
    }

internal suspend inline fun <reified Req : Any, reified Res : Any> HttpClient.post(
    path: String,
    body: Req,
): Result<Res, DataError.Network> =
    safeApiCall {
        post {
            url(createRoute(path))
            setBody(body)
        }
    }

internal suspend inline fun <reified Res : Any> HttpClient.delete(
    path: String,
    queryParams: Map<String, Any?> = emptyMap(),
): Result<Res, DataError.Network> =
    safeApiCall {
        delete {
            url(createRoute(path))
            queryParams.map { (key, value) ->
                parameter(key, value)
            }
        }
    }

internal suspend inline fun <reified Res : Any> safeApiCall(func: () -> HttpResponse): Result<Res, DataError.Network> =
    try {
        mapResponseCodes(func())
    } catch (e: Exception) {
        Log.e("error", "$e")
        handleApiException(e)
    }

internal suspend fun handleApiException(e: Exception): Result.Error<DataError.Network> =
    when (e) {
        is UnresolvedAddressException -> Result.Error(DataError.Network.NO_INTERNET)
        is SerializationException -> Result.Error(DataError.Network.SERIALIZATION_ERROR)
        else -> {
            coroutineContext.ensureActive()
            Result.Error(DataError.Network.UNKNOWN)
        }
    }

internal suspend inline fun <reified Res : Any> mapResponseCodes(response: HttpResponse): Result<Res, DataError.Network> =
    when (response.status.value) {
        in 200..299 -> Result.Success(response.body())
        402 -> Result.Error(DataError.Network.UNAUTHORIZED)
        408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
        409 -> Result.Error(DataError.Network.CONFLICT)
        413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
        429 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Network.SERVER_ERROR)
        else -> Result.Error(DataError.Network.UNKNOWN)
    }

private const val BASE_URL = "https://dummyjson.com/"

internal fun createRoute(path: String): String = BASE_URL + path