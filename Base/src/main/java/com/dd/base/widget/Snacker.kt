package com.dd.base.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SnackerView(
    hint: String,//toast提示文本
    icon: ImageVector? = null,//toastIcon
    snackbarHostState: SnackbarHostState,//输入框提示文本
    onClick: (() -> Unit)? = null, //返回文本框的内容)
) {
    SnackbarHost(hostState = snackbarHostState) { data ->
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Snackbar(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    if (icon != null) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = hint,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .clickable {
                                onClick?.invoke()
                            },
                        style = TextStyle(color = Color.White, fontSize = 20.sp),
                    )
                }
            }
        }

    }
}