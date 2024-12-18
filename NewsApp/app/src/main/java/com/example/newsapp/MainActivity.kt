package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.HomeView
import com.example.newsapp.theme.NewsAppTheme
import com.example.newsapp.ui.ArticleDetail
import com.example.newsapp.ui.HomeView
import com.example.newsapp.ui.HomeViewPreview
import com.example.newsapp.ui.LoginScreen
import com.example.newsapp.ui.RegisterScreen
import com.example.newsapp.ui.SettingsView

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                var navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),         topBar = {
                    TopAppBar(
                        colors = topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text("Banger News")
                        },
                        navigationIcon = {

                                Icon(Icons.Filled.Home, contentDescription = "Home")
                        }
                    )
                },
                    bottomBar = {
                        BottomAppBar(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ) {
                            Row(modifier = Modifier.align(Alignment.CenterVertically).fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround){
                                IconButton(onClick = {navController.navigate(Screen.Home.route)})
                                {
                                 Icon(Icons.AutoMirrored.Filled.List, contentDescription = "News")
                                }
                                IconButton(onClick = {navController.navigate(Screen.Settings.route)}) {
                                    Icon(Icons.Filled.Person, contentDescription = "Menu")
                                }

                            }
                        }
                    },
                ) { innerPadding ->
                    NavHost(navController = navController,
                        startDestination = Screen.Home.route ) {
                        composable(route = Screen.Home.route) {
                            HomeView(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable(route = Screen.ArticleDetail.route)
                        {
                            val url = it.arguments?.getString("articleUrl")
                            ArticleDetail(
                                modifier = Modifier.padding(innerPadding),
                                url = url ?: ""
                            )
                        }
                        composable(route = Screen.Settings.route) {
                            SettingsView(navController)
                        }
                        composable(route = "login") {
                            LoginScreen(navController)
                        }
                        composable(route = "register") {
                            RegisterScreen(navController)
                        }
                    }

                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ArticleDetail : Screen("article_Detail/{articleUrl}")
    object Settings : Screen("settings")

}



