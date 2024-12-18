package com.example.newsapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.AuthService

@Composable
fun SettingsView(navController: NavController) {
    var isNotificationsEnabled by remember { mutableStateOf(true) }
    var isDarkModeEnabled by remember { mutableStateOf(false) }
    val currentUser = AuthService.getCurrentUser()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(75.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Settings", style = MaterialTheme.typography.headlineSmall)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Notifications")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isNotificationsEnabled,
                onCheckedChange = { isNotificationsEnabled = it }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Dark Mode")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isDarkModeEnabled,
                onCheckedChange = { isDarkModeEnabled = it }
            )
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        if (currentUser == null) {
            Button(
                onClick = { navController.navigate("login") }, // Navigate to a login screen
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login / Register")
            }
        } else {
            Button(
                onClick = {
                    AuthService.logout()
                    navController.navigate("home") { // Navigate back to home after logout
                        popUpTo("settings") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Logout")
            }
        }
    }
}
