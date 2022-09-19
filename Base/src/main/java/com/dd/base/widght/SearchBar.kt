package com.dd.base.widght

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd.base.ui.theme.AppTheme


@Composable
fun SearchBar(
    hint: String,//输入框提示文本
    onClick:(()->Unit)?=null//输入框点击事件，提供点击跳转到搜索页等定制功能,默认为空
) {
    var text by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.transparent)
            .padding(10.dp)
            .clickable {
                onClick?.invoke()
            },
        contentAlignment = Alignment.Center
    )
    {
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "搜索", modifier = Modifier.padding(horizontal = 4.dp).size(16.dp))
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp).weight(1f), Alignment.CenterStart
                    ) {
                        if (text.isEmpty()) {
                            Text(
                                text = hint,
                                style = TextStyle(color = AppTheme.colors.textSecondary)
                            )
                        }
                        innerTextField()
                    }
                    if (text.isNotEmpty()) {
                        IconButton(onClick = { text = "" }, modifier = Modifier.padding(horizontal = 4.dp).size(16.dp)) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "清空搜索框")
                        }
                    }
                }
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .background(AppTheme.colors.divider, CircleShape)
                .height(30.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun testSearchBar() {
    SearchBar(hint = "天气之子")
}
