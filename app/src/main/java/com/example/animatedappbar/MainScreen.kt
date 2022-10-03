package com.example.animatedappbar

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

val TOP_APPBAR_HEIGHT = 56.dp
val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0

@Composable
fun MainScreen() {
    val lazyListState = rememberLazyListState()
    Scaffold(
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                MyAppBar(lazyListState)
                MainContent(lazyListState)
            }
        }
    )
}

@Composable
fun MyAppBar(
    lazyListState: LazyListState,
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .animateContentSize(tween(300))
            .height(height = if (lazyListState.isScrolled) 0.dp else TOP_APPBAR_HEIGHT),
        contentPadding = PaddingValues(start = 16.dp)
    ) {
        Text(
            text = "Title",
            style = TextStyle(
                fontSize = MaterialTheme.typography.h6.fontSize,
                color = MaterialTheme.colors.surface
            )
        )
    }
}

@Composable
fun MainContent(
    lazyListState: LazyListState,
) {
    val number = remember { List(size = 25) { it } }
    val padding by animateDpAsState(
        targetValue = if (lazyListState.isScrolled) 0.dp else TOP_APPBAR_HEIGHT,
        animationSpec = tween(300)
    )
    LazyColumn(
        modifier = Modifier.padding(top = padding),
        state = lazyListState
    ) {
        items(
            items = number,
            key = { it }
        ) {
            NumberHolder(it)
        }
    }
}

@Composable
fun NumberHolder(number: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = number.toString(),
            style = TextStyle(
                fontSize = MaterialTheme.typography.h1.fontSize,
                fontWeight = FontWeight.Bold
            )
        )
    }
}













