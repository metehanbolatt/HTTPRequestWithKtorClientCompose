package com.metehanbolat.httprequestwithktorclientcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.metehanbolat.httprequestwithktorclientcompose.ui.theme.HTTPRequestWithKtorClientComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HTTPRequestWithKtorClientComposeTheme {

            }
        }
    }
}
