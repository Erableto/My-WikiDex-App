package com.erableto.mywikidexapp.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.data.FavoritesViewModel
import com.erableto.mywikidexapp.data.FavoritesViewModelFactory
import com.erableto.mywikidexapp.data.HistoryViewModel
import com.erableto.mywikidexapp.data.HistoryViewModelFactory
import com.erableto.mywikidexapp.ui.components.LabeledSmallFab
import com.erableto.mywikidexapp.util.WIKIDEX_AC_DOMAIN
import com.erableto.mywikidexapp.util.WIKIDEX_MASTODON_DOMAIN
import com.erableto.mywikidexapp.util.WIKIDEX_MAIN_DOMAIN
import com.erableto.mywikidexapp.util.WIKIDEX_LABEL
import com.erableto.mywikidexapp.util.WIKIDEX_PORTADA_URL
import com.erableto.mywikidexapp.util.WIKIDEX_URL
import com.erableto.mywikidexapp.util.getReadableTitleFromURL
import com.erableto.mywikidexapp.util.vibrateError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WikiScreen(
    favoritesViewModel: FavoritesViewModel = ViewModelProvider(
        LocalActivity.current as ComponentActivity,
        FavoritesViewModelFactory(LocalContext.current)
    )[FavoritesViewModel::class.java], // ).get(FavoritesViewModel::class.java),
    historyViewModel: HistoryViewModel = ViewModelProvider(
        LocalActivity.current as ComponentActivity,
        HistoryViewModelFactory(LocalContext.current)
    )[HistoryViewModel::class.java], // ).get(HistoryViewModel::class.java),
    url: String,
    resetTrigger: Int
) {
    val context = LocalContext.current

    val favorites by favoritesViewModel.favorites.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current
    var focusRequester = remember {
        FocusRequester()
    }
    var searchQuery by remember {
        mutableStateOf<String?>(null)
    }
    var currentResultNumber by remember {
        mutableStateOf(0)
    }
    var numberOfResults by remember {
        mutableStateOf(0)
    }
    var isSearching by remember {
        mutableStateOf(false)
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
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
    val currentTitle = /*webViewRef.value?.title*/getReadableTitleFromURL(webViewRef.value?.url)

    val isFavorite = favorites.any {
        it.url == currentURL || it.title == currentTitle
    }

    val backgroundColor = MaterialTheme.colorScheme.background.toArgb()
    val primaryColor = MaterialTheme.colorScheme.primary.toArgb()
    val surfaceColor = MaterialTheme.colorScheme.surface.toArgb()

    fun closeSearch() {
        isSearching = false
        searchQuery = ""
        currentResultNumber = 0
        numberOfResults = 0
        webViewRef.value?.clearMatches() // Limpiamos los resaltados.
        keyboardController?.hide() // Ocultamos el teclado manualmente.
    }

    // Cuando cambie resetTrigger, vamos a la portada de WikiDex.
    LaunchedEffect(resetTrigger) {
        // Solo hacemos algo si el valor cambió de verdad.
        if (resetTrigger != lastResetTrigger.value) {
            val url = webViewRef.value?.url

            // Si estamos ya en la portada, no volvemos a la portada.
            if (url != WIKIDEX_PORTADA_URL) {
                webViewRef.value?.loadUrl(WIKIDEX_URL)
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
        if (isSearching) {
            closeSearch()
        } else {
            val webView = webViewRef.value
            if (webView != null && webView.canGoBack()) {
                webView.goBack()
            } else {
                // Si no puede ir atrás, dejamos que Android cierre la pantalla.
                (context as? Activity)?.finish()
            }
        }
    }

    // Mostramos un diálogo cuando una URL se sale de WikiDex.
    if (blockedURL != null) {
        AlertDialog(
            onDismissRequest = {
                blockedURL = null
                toDesktopMode = false
                toSkin = false
            },
            title = {
                if (toDesktopMode) Text("Modo escritorio")
                else if (toSkin) Text("Cambio de skin")
                else Text("Enlace externo")
            },
            text = {
                if (toDesktopMode) Text("Para usar el modo escritorio debe abrirse el navegador. ¿Quieres hacerlo?")
                else if (toSkin) Text("Para cambiar de skin debe abrirse el navegador. ¿Quieres hacerlo?")
                else Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        )) {
                            append(blockedURL)
                        }
                        append("\n\n¿Quieres abrir este enlace en el navegador?")
                    }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, blockedURL?.toUri())
                        context.startActivity(intent)
                        blockedURL = null
                        toDesktopMode = false
                        toSkin = false
                    }
                ) {
                    Text("SÍ")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        blockedURL = null
                        toDesktopMode = false
                        toSkin = false
                    }
                ) {
                    Text("NO")
                }
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            // Barra de búsqueda (solo visible cuando isSearching es true)
            AnimatedVisibility(visible = isSearching) {
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }

                TextField(
                    value = searchQuery ?: "",
                    onValueChange = {
                        searchQuery = it
                        webViewRef.value?.findAllAsync(it) // Permite buscar mientras se escribe.
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .focusRequester(focusRequester),
                    isError = !searchQuery.isNullOrEmpty() && numberOfResults <= 0,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide() // Ocultamos el teclado manualmente.
                        }
                    ),
                    placeholder = {
                        Text("Buscar en la página")
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.rounded_search_24),
                            contentDescription = "Buscar"
                        )
                    },
                    trailingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (!searchQuery.isNullOrEmpty()) {
                                val text = buildAnnotatedString {
                                    if (numberOfResults > 0) {
                                        append("${currentResultNumber + 1} / $numberOfResults")
                                    } else {
                                        withStyle(
                                            style = SpanStyle(color = MaterialTheme.colorScheme.error)
                                        ) {
                                            append("${currentResultNumber} / $numberOfResults")
                                        }
                                    }
                                }
                                Text(text)

                                Spacer(modifier = Modifier.width(8.dp))

                                IconButton(
                                    onClick = {
                                        webViewRef.value?.findNext(false)
                                    },
                                    enabled = numberOfResults > 0 // Para que se desactive y se ponga gris el botón.
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.rounded_arrow_upward_24),
                                        contentDescription = "Anterior"
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        webViewRef.value?.findNext(true)
                                    },
                                    enabled = numberOfResults > 0 // Para que se desactive y se ponga gris el botón.
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.rounded_arrow_downward_24),
                                        contentDescription = "Siguiente"
                                    )
                                }
                            }

                            IconButton(
                                onClick = {
                                    closeSearch()
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.rounded_close_24),
                                    contentDescription = "Cerrar"
                                )
                            }
                        }
                    },
                    singleLine = true
                )
            }

            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    // Contenedor de pull-to-refresh
                    val swipeRefreshLayout = SwipeRefreshLayout(context)

                    val webView = WebView(context).apply {
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                        settings.setSupportZoom(true)
                        settings.builtInZoomControls = true
                        settings.displayZoomControls = false

                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(
                                view: WebView?,
                                url: String?,
                                favicon: Bitmap?
                            ) {
                                super.onPageStarted(view, url, favicon)

                                isLoading = true
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                swipeRefreshLayout.isRefreshing = false

                                //// HISTORIAL ////
                                val title = /*view?.title*/getReadableTitleFromURL(url)

                                if (
                                    url != null &&
                                    title != null &&
                                    url != WIKIDEX_URL && // Para no incluir la página de la portada.
                                    url != WIKIDEX_PORTADA_URL && // Para no incluir la página de la portada.
                                    (
                                            url.contains("$WIKIDEX_PORTADA_URL:") || // Para permitir páginas del espacio de nombres WikiDex.
                                            !url.contains(WIKIDEX_PORTADA_URL) // Para no incluir la página de la portada.
                                    ) &&
                                    !url.contains("?search") && // Para no incluir páginas de búsqueda.
                                    !url.contains("&search") && // Para no incluir páginas de búsqueda.
                                    !url.contains("/search") && // Para no incluir páginas de búsqueda.
                                    !url.contains("?redirect") && // Para no incluir páginas de redirección.
                                    !url.contains("&redirect") && // Para no incluir páginas de redirección.
                                    !url.endsWith("#") && // Para no incluir páginas que terminan en "#".
                                    !url.contains("/index.php") && // Para no incluir páginas con "/index.php".
                                    !url.contains("/editor") && // Para no incluir páginas de edición.
                                    !url.contains("action=") && // Para no incluir páginas de edición.
                                    !url.contains("/media") && // Para no incluir vistas de imágenes dentro de páginas.
                                    !url.contains("?mfnotify") && // Para no incluir páginas que salen tras guardar una edición.
                                    !url.contains("&mfnotify") // Para no incluir páginas que salen tras guardar una edición.
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
                                    historyViewModel.insert(
                                        url,
                                        title/*.removeSuffix(WikiDexLabel)*/
                                    )
                                }
                                //// ////

                                isLoading = false
                            }

                            override fun shouldOverrideUrlLoading(
                                view: WebView?,
                                request: WebResourceRequest?
                            ): Boolean {
                                val currentURL = request?.url ?: return true
                                val currentHost = currentURL.host ?: return true

                                val isAllowed = currentHost.endsWith(WIKIDEX_MAIN_DOMAIN)
                                val isMastodon = currentHost.endsWith(WIKIDEX_MASTODON_DOMAIN)
                                val isAC = currentHost.endsWith(WIKIDEX_AC_DOMAIN)
                                toDesktopMode = currentURL.toString()
                                    .contains("mobileaction=toggle_view_desktop")
                                toSkin = currentURL.toString().contains("useskin=")

                                expanded = false
                                //isSearching = false

                                return if (isAllowed && !isMastodon && !isAC && !toDesktopMode && !toSkin) {
                                    false // Permitimos la navegación.
                                } else {
                                    /*
                                    // No dejamos que se muestre el diálogo si es lo de cambiar al modo escritorio.
                                    if (!toDesktopMode && !toSkin) {
                                    */
                                        // Guardamos la URL bloqueada para mostrar el diálogo.
                                        blockedURL = currentURL.toString()
                                    //}
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

                        expanded = false
                    }

                    webView.setFindListener { activeMatchOrdinal, numberOfMatches, isDoneCounting ->
                        currentResultNumber = activeMatchOrdinal
                        numberOfResults = numberOfMatches

                        // Hacemos que vibre cuando la búsqueda no devuelva ningún resultado.
                        if (!searchQuery.isNullOrEmpty() && isDoneCounting && numberOfResults <= 0) {
                            context.vibrateError()
                        }
                    }

                    webViewRef.value = webView

                    swipeRefreshLayout.apply {
                        addView(webView)
                        setOnRefreshListener {
                            webView.reload()
                            expanded = false
                        }
                        setColorSchemeColors(primaryColor)
                        setProgressBackgroundColorSchemeColor(surfaceColor)
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
        }

        AnimatedVisibility(
            visible = !isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // FAB expandible
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Opciones del Speed Dial
                    AnimatedVisibility(visible = expanded) {
                        LazyColumn(horizontalAlignment = Alignment.End) {
                            item {
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

                                LabeledSmallFab(
                                    text = "Buscar en la página",
                                    onClick = {
                                        expanded = false
                                        isSearching = true
                                    },
                                    icon = {
                                        Icon(
                                            painter = painterResource(R.drawable.rounded_find_in_page_24),
                                            contentDescription = "Buscar en la página"
                                        )
                                    }
                                )

                                //// FAVORITOS ////
                                if (
                                    currentURL != null &&
                                    currentTitle != null &&
                                    !currentURL.contains("?search") && // Para no incluir páginas de búsqueda.
                                    !currentURL.contains("&search") && // Para no incluir páginas de búsqueda.
                                    !currentURL.contains("/search") && // Para no incluir páginas de búsqueda.
                                    !currentURL.contains("?redirect") && // Para no incluir páginas de redirección.
                                    !currentURL.contains("&redirect") && // Para no incluir páginas de redirección.
                                    !currentURL.endsWith("#") && // Para no incluir páginas que terminan en "#".
                                    !currentURL.contains("/index.php") && // Para no incluir páginas con "/index.php".
                                    !currentURL.contains("/editor") && // Para no incluir páginas de edición.
                                    !currentURL.contains("action=") && // Para no incluir páginas de edición.
                                    !currentURL.contains("/media") && // Para no incluir vistas de imágenes dentro de páginas.
                                    !currentURL.contains("?mfnotify") && // Para no incluir páginas que salen tras guardar una edición.
                                    !currentURL.contains("&mfnotify") // Para no incluir páginas que salen tras guardar una edición.
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
                                                        currentTitle.removeSuffix(WIKIDEX_LABEL)
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
                                //// ////

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

                                            val shareIntent =
                                                Intent.createChooser(sendIntent, "Compartir página")
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

        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
                //PacmanIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WikiScreenPreview() {
    MyWikiDexAppTheme() {
        WikiScreen(url = WIKIDEX_URL, resetTrigger = 0)
    }
}