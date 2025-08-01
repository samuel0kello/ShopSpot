package com.samuelokello.shopspot

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.samuelokello.shopspot.ui.navigation.ShopSpotAppNavHost
import com.samuelokello.shopspot.ui.theme.ShopSpotTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopSpotTheme {
                ShopSpotAppNavHost()
            }
        }
    }
}
