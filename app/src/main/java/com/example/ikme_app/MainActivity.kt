package com.example.ikme_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.ikme_app.ui.navigation.AppNavigationGraph
import com.example.ikme_app.ui.theme.MainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ApplicationLayout()
                }
            }
        }
    }
}

@Composable
fun ApplicationLayout(modifier: Modifier = Modifier) {
    val navController = rememberNavController();
    AppNavigationGraph(navController = navController, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun ApplicationLayoutPreview() {
    MainTheme {
        ApplicationLayout()
    }
}