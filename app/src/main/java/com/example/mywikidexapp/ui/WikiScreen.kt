package com.example.mywikidexapp.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.mywikidexapp.ui.theme.MyWikiDexAppTheme
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mywikidexapp.R
import com.example.mywikidexapp.data.FavoritesViewModel
import com.example.mywikidexapp.data.FavoritesViewModelFactory
import com.example.mywikidexapp.data.HistoryViewModel
import com.example.mywikidexapp.data.HistoryViewModelFactory
import com.example.mywikidexapp.ui.components.LabeledSmallFab
import com.example.mywikidexapp.utils.MastodonDomain
import com.example.mywikidexapp.utils.WikiDexDomain
import com.example.mywikidexapp.utils.WikiDexLabel
import com.example.mywikidexapp.utils.WikiDexPortadaURL
import com.example.mywikidexapp.utils.WikiDexURL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WikiScreenComposable(
    favoritesViewModel: FavoritesViewModel = ViewModelProvider(
        LocalContext.current as ComponentActivity,
        FavoritesViewModelFactory(LocalContext.current)
    )[FavoritesViewModel::class.java], // ).get(FavoritesViewModel::class.java),
    historyViewModel: HistoryViewModel = ViewModelProvider(
        LocalContext.current as ComponentActivity,
        HistoryViewModelFactory(LocalContext.current)
    )[HistoryViewModel::class.java], // ).get(HistoryViewModel::class.java),
    url: String,
    resetTrigger: Int
) {
    val context = LocalContext.current

    val favorites by favoritesViewModel.favorites.collectAsState()

    var expanded by remember {
        mutableStateOf(false)
    }
    /*var isFavorite by remember {
        mutableStateOf(false)
    }*/
    var toDesktopMode by remember {
        mutableStateOf(false)
    }
    var toSkin by remember {
        mutableStateOf(false)
    }
    var blockedURL by remember {
        mutableStateOf<String?>(null)
    }
    val webViewRef = remember {
        mutableStateOf<WebView?>(null)
    }
    // Bundle que sobrevive a cambios de configuración (como la rotación)
    val webViewState = rememberSaveable {
        Bundle()
    }
    val lastResetTrigger = rememberSaveable {
        mutableStateOf(resetTrigger)
    }

    val currentURL = webViewRef.value?.url
    val currentTitle = webViewRef.value?.title

    val isFavorite = favorites.any {
        it.url == currentURL
    }

    // Cuando cambie resetTrigger, vamos a la portada de WikiDex.
    LaunchedEffect(resetTrigger) {
        // Solo hacemos algo si el valor cambió de verdad.
        if (resetTrigger != lastResetTrigger.value) {
            val url = webViewRef.value?.url

            // Si estamos ya en la portada, no volvemos a la portada.
            if (url != WikiDexPortadaURL) {
                webViewRef.value?.loadUrl(WikiDexURL)
            }

            lastResetTrigger.value = resetTrigger
        }
    }

    // Cuando este Composable salga de composición (por destrucción de la Activity),
    // guardamos el estado del WebView en el Bundle.
    DisposableEffect(Unit) {
        onDispose {
            webViewRef.value?.saveState(webViewState)
        }
    }

    // Botón atrás para volver hacia atrás en la navegación.
    BackHandler {
        val webView = webViewRef.value
        if (webView != null && webView.canGoBack()) {
            webView.goBack()
        } else {
            // Si no puede ir atrás, dejamos que Android cierre la pantalla.
            (context as? Activity)?.finish()
        }
    }

    // Mostramos un diálogo cuando una URL se sale de WikiDex.
    if (blockedURL != null) {
        AlertDialog(
            onDismissRequest = {
                blockedURL = null
            },
            title = {
                Text("Enlace externo")
            },
            text = {
                Text("¿Quieres abrir este enlace en el navegador?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, blockedURL?.toUri())
                        context.startActivity(intent)
                        blockedURL = null
                    }
                ) {
                    Text("SÍ")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        blockedURL = null
                    }
                ) {
                    Text("NO")
                }
            }
        )
    }

    if (toDesktopMode) {
        toDesktopMode = false
        Toast.makeText(context, "No compatible con el modo escritorio.", Toast.LENGTH_LONG).show()
    }
    if (toSkin) {
        toSkin = false
        Toast.makeText(context, "No compatible con el cambio de skin.", Toast.LENGTH_LONG).show()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        val isDarkTheme = isSystemInDarkTheme()

        AndroidView(
            factory = { context ->
                // Contenedor de pull-to-refresh
                val swipeRefreshLayout = SwipeRefreshLayout(context)

                val webView = WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true

                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            swipeRefreshLayout.isRefreshing = false

                            val title = view?.title

                            if (
                                url != null &&
                                title != null &&
                                url != WikiDexURL && // Para no incluir la página de la portada.
                                url != WikiDexPortadaURL && // Para no incluir la página de la portada.
                                !url.contains(WikiDexPortadaURL) && // Para no incluir la página de la portada.
                                !url.contains("?search") && // Para no incluir páginas de búsqueda.
                                !url.contains("&search") && // Para no incluir páginas de búsqueda.
                                !url.contains("/search") // Para no incluir páginas de búsqueda.
                            ) {
                                // Hacemos cosas con el historial si la página actual no es la portada.
                                /*val historyEntryAux = historyViewModel.getByURL(url).value

                                if (historyEntryAux != null) {
                                    // Si existe la entrada en el historial, la actualizamos.
                                    historyViewModel.updateTimeMillis(historyEntryAux)
                                } else {
                                    // Si no existe, la creamos.
                                    historyViewModel.insert(url, title)
                                }*/

                                // Hay una restricción de que no puede haber varias entradas con
                                // la misma URL, así que se reemplazan al ser insertadas.
                                historyViewModel.insert(url, title.removeSuffix(WikiDexLabel))
                            }
                        }

                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            val currentURL = request?.url ?: return true
                            val currentHost = currentURL.host ?: return true

                            val isAllowed = currentHost.endsWith(WikiDexDomain)
                            val isMastodon = currentHost.endsWith(MastodonDomain)
                            toDesktopMode = currentURL.toString().contains("mobileaction=toggle_view_desktop")
                            toSkin = currentURL.toString().contains("useskin=")

                            return if (isAllowed && !isMastodon && !toDesktopMode) {
                                false // Permitimos la navegación.
                            } else {
                                // No dejamos que se muestre el diálogo si es lo de cambiar al modo escritorio.
                                if (!toDesktopMode && !toSkin) {
                                    // Guardamos la URL bloqueada para mostrar el diálogo.
                                    blockedURL = currentURL.toString()
                                }
                                true // Bloqueamos la navegación.
                            }
                        }
                    }

                    //loadUrl(url)

                    // Restauramos el estado si existe.
                    // Si no, cargamos la URL inicial.
                    if (webViewState.isEmpty) {
                        loadUrl(url)
                    } else {
                        restoreState(webViewState)
                    }
                }

                webViewRef.value = webView

                swipeRefreshLayout.apply {
                    addView(webView)
                    setOnRefreshListener {
                        webView.reload()
                    }
                }
            },
            /*update = { webView ->
                webView.loadUrl(WikiDexURL)
            }*/
            /*update = { webView ->
                // Guardamos el estado para cuando el Composable se recomponga.
                webViewRef.value?.saveState(webViewState)
            }*/
            update = {}
        )

        // FAB expandible
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            // Opciones del Speed Dial
            AnimatedVisibility(visible = expanded) {
                Column(horizontalAlignment = Alignment.End) {
                    LabeledSmallFab(
                        text = "Ir arriba",
                        onClick = {
                            webViewRef.value?.scrollTo(0, 0)

                            expanded = false
                        },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.rounded_arrow_upward_24),
                                contentDescription = "Ir arriba"
                            )
                        }
                    )

                    if (
                        currentURL != null &&
                        currentTitle != null &&
                        !currentURL.contains("?search") && // Para no incluir páginas de búsqueda.
                        !currentURL.contains("&search") && // Para no incluir páginas de búsqueda.
                        !currentURL.contains("/search") // Para no incluir páginas de búsqueda.
                    ) {
                        LabeledSmallFab(
                            text =
                                if (isFavorite) "Quitar de favoritos"
                                else "Añadir a favoritos",
                            onClick = {
                                if (currentURL != null && currentTitle != null) {
                                    if (isFavorite) {
                                        val favorite = favorites.first {
                                            it.url == currentURL
                                        }
                                        favoritesViewModel.delete(favorite)
                                    } else {
                                        favoritesViewModel.insert(
                                            currentURL,
                                            currentTitle.removeSuffix(WikiDexLabel)
                                        )
                                    }
                                }

                                expanded = false
                            },
                            icon = {
                                Icon(
                                    painter =
                                        if (isFavorite) painterResource(R.drawable.rounded_heart_broken_24)
                                        else painterResource(R.drawable.rounded_favorite_24),
                                    contentDescription =
                                        if (isFavorite) "Quitar de favoritos"
                                        else "Añadir a favoritos"
                                )
                            }
                        )
                    }

                    LabeledSmallFab(
                        text = "Abrir en el navegador",
                        onClick = {
                            val url = webViewRef.value?.url

                            if (url != null) {
                                val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                                context.startActivity(intent)
                            }

                            expanded = false
                        },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.rounded_open_in_browser_24),
                                contentDescription = "Abrir en el navegador"
                            )
                        }
                    )

                    LabeledSmallFab(
                        text = "Compartir",
                        onClick = {
                            val url = webViewRef.value?.url

                            if (url != null) {
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, url)
                                    type = "text/plain"
                                }

                                val shareIntent = Intent.createChooser(sendIntent, "Compartir página")
                                context.startActivity(shareIntent)
                            }

                            expanded = false
                        },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.rounded_share_24),
                                contentDescription = "Compartir"
                            )
                        }
                    )
                }
            }

            // FAB principal
            FloatingActionButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(
                    painter =
                        if (expanded) painterResource(R.drawable.rounded_close_24)
                        else painterResource(R.drawable.rounded_menu_24),
                    contentDescription = if (expanded) "Cerrar menú" else "Abrir menú"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WikiScreenComposablePreview() {
    MyWikiDexAppTheme() {
        WikiScreenComposable(url = WikiDexURL, resetTrigger = 0)
    }
}