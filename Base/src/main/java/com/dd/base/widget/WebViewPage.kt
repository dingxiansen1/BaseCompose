package com.dd.base.widget

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dd.base.theme.AppTheme
import com.dd.base.theme.ComposeAppTheme
import com.dd.base.theme.Themem

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewPage(
    webUrl: String,
    title: String?,
    navCtrl: NavHostController
) {
    var url by remember { mutableStateOf(webUrl) }
    val state = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()
    ComposeAppTheme(themeType = Themem.themeTypeState.value) {
        Column {
            TopAppBar(
                backgroundColor = AppTheme.colors.background,
                title = {
                    Text(
                        text = title ?: "",
                        style = TextStyle(fontSize = 14.sp, color = AppTheme.colors.textPrimary),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (navigator.canGoBack) {
                            navigator.navigateBack()
                        } else {
                            navCtrl.navigateUp()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "返回",
                            tint = AppTheme.colors.textPrimary
                        )
                    }
                }
            )


            val loadingState = state.loadingState
            if (loadingState is LoadingState.Loading) {
                LinearProgressIndicator(
                    progress = loadingState.progress,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            val webClient = remember {
                object : AccompanistWebViewClient() {
                    override fun onPageStarted(
                        view: WebView?,
                        url: String?,
                        favicon: Bitmap?
                    ) {
                        super.onPageStarted(view, url, favicon)
                        Log.d("Accompanist WebView", "Page started loading for $url")
                    }
                }
            }

            WebView(
                state = state,
                modifier = Modifier.weight(1f),
                navigator = navigator,
                onCreated = { webView ->
                    webView.settings.javaScriptEnabled = true
                },
                client = webClient
            )
        }
    }
}