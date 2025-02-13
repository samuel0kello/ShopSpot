// package com.samuelokello.core.di
//
// import android.content.Context
// import androidx.datastore.core.DataStore
// import androidx.datastore.preferences.core.Preferences
// import androidx.datastore.preferences.preferencesDataStore
// import com.samuelokello.core.data.repository.CartRepository
// import com.samuelokello.core.data.repository.CartRepositoryImpl
// import com.samuelokello.core.data.repository.LoginRepositoryImpl
// import com.samuelokello.core.data.repository.ProductRepository
// import com.samuelokello.core.data.repository.ProductRepositoryImpl
// import com.samuelokello.core.preference.Constants.AUTH_PREFERENCES
// import com.samuelokello.database.mapper.ProductApiMapper
// import com.samuelokello.database.mapper.ProductEntityMapper
// import com.samuelokello.shopspot.data.network.auth.AuthApiService
// import com.samuelokello.shopspot.data.network.cart.CartApiService
// import com.samuelokello.shopspot.data.network.product.ProductApiService
// import com.samuelokello.shopspot.domain.repository.LoginRepository
// import kotlinx.serialization.json.Json
//
// interface ShopSpotContainer {
//    val productRepository: com.samuelokello.core.data.repository.ProductRepository
//    val cartRepository: com.samuelokello.core.data.repository.CartRepository
//    val loginRepository: LoginRepository
// }
//
// private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = AUTH_PREFERENCES)
//
// class DefaultAppContainer(
//    private val context: Context,
// ) : ShopSpotContainer {
//    private val baseUrl = "https://fakestoreapi.com/"
//
//    private val json =
//        Json {
//            ignoreUnknownKeys = true
//            coerceInputValues = true
//            isLenient = true
//        }
//
//    // API Services
//    private val productApiService: ProductApiService by lazy {
//        retrofit.create(ProductApiService::class.java)
//    }
//
//    private val cartApiService: CartApiService by lazy {
//        retrofit.create(CartApiService::class.java)
//    }
//
//    private val authApiService: AuthApiService by lazy {
//        retrofit.create(AuthApiService::class.java)
//    }
//
//    // Database
//    private val database: com.samuelokello.database.database.ShopSpotDatabase by lazy {
//        com.samuelokello.database.database.ShopSpotDatabase
//            .getDatabase(context)
//    }
//
//    // DAOs
//    private val productDao: com.samuelokello.database.database.dao.ProductDao by lazy {
//        database.productDao()
//    }
//
//    private val cartDao: com.samuelokello.database.database.dao.UserCartDao by lazy {
//        database.cartDao()
//    }
//
//    private val authPreferences: com.samuelokello.core.preference.AuthPreferences by lazy {
//        com.samuelokello.core.preference
//            .AuthPreferences(context.dataStore)
//    }
//
//    // Mappers
//    private val productApiMapper: com.samuelokello.database.mapper.ProductApiMapper by lazy {
//        com.samuelokello.database.mapper
//            .ProductApiMapper()
//    }
//
//    private val productEntityMapper: com.samuelokello.database.mapper.ProductEntityMapper by lazy {
//        com.samuelokello.database.mapper
//            .ProductEntityMapper()
//    }
//
//    // Repositories
//    override val productRepository: com.samuelokello.core.data.repository.ProductRepository by lazy {
//        com.samuelokello.core.data.repository.ProductRepositoryImpl(
//            productApiService = productApiService,
//            productDao = productDao,
//            productEntityMapper = productEntityMapper,
//            productApiMapper = productApiMapper,
//        )
//    }
//
//    override val cartRepository: com.samuelokello.core.data.repository.CartRepository by lazy {
//        com.samuelokello.core.data.repository.CartRepositoryImpl(
//            api = cartApiService,
//            dao = cartDao,
//        )
//    }
//
//    override val loginRepository: LoginRepository by lazy {
//        com.samuelokello.core.data.repository.LoginRepositoryImpl(
//            authPreferences = authPreferences,
//            authApiService = authApiService,
//        )
//    }
//
//    companion object {
//        @Volatile
//        private var Instance: ShopSpotContainer? = null
//
//        fun getInstance(context: Context): ShopSpotContainer =
//            Instance ?: synchronized(this) {
//                Instance ?: DefaultAppContainer(context).also { Instance = it }
//            }
//    }
// }
