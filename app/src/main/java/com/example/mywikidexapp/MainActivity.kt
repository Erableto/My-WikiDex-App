package com.example.mywikidexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mywikidexapp.ui.FavoritesScreenComposable
import com.example.mywikidexapp.ui.HistoryScreenComposable
import com.example.mywikidexapp.ui.WikiScreenComposable
import com.example.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.example.mywikidexapp.utils.WikiDexURL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyWikiDexAppTheme {
                MyWikiDexAppApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun MyWikiDexAppApp() {
    var currentDestination by rememberSaveable {
        mutableStateOf(AppDestinations.WIKI)
    }
    var wikiResetTrigger by rememberSaveable {
        mutableStateOf(0)
    }
    var currentWikiURL by rememberSaveable {
        mutableStateOf(WikiDexURL)
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        val iconRes =
                            if (it == currentDestination) it.selectedIcon
                            else it.icon
                        Icon(
                            painterResource(iconRes),
                            contentDescription = it.label
                        )
                    },
                    label = {
                        Text(it.label)
                    },
                    selected = it == currentDestination,
                    onClick = {
                        if (it == AppDestinations.WIKI) {
                            if (currentDestination == AppDestinations.WIKI) {
                                // Ya estamos en Inicio, así que reseteamos el WebView.
                                wikiResetTrigger++
                            } else {
                                currentWikiURL = WikiDexURL
                            }
                        }
                        currentDestination = it
                    }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (currentDestination) {
                    AppDestinations.WIKI -> WikiScreenComposable(
                        url = currentWikiURL,
                        resetTrigger = wikiResetTrigger
                    )
                    AppDestinations.FAVORITES -> FavoritesScreenComposable(
                        onNavigateToWiki = { url ->
                            currentWikiURL = url
                            currentDestination = AppDestinations.WIKI
                        }
                    )
                    AppDestinations.HISTORY -> HistoryScreenComposable(
                        onNavigateToWiki = { url ->
                            currentWikiURL = url
                            currentDestination = AppDestinations.WIKI
                        }
                    )
                }
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
    val selectedIcon: Int
) {
    WIKI(
        "Inicio",
        R.drawable.rounded_home_24,
        R.drawable.rounded_filled_home_24
    ),
    FAVORITES(
        "Favoritos",
        R.drawable.rounded_favorite_24,
        R.drawable.rounded_filled_favorite_24
    ),
    HISTORY(
        "Historial",
        R.drawable.rounded_history_24,
        R.drawable.rounded_filled_history_24
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MyWikiDexAppTheme {
        Greeting("Android")
    }
}