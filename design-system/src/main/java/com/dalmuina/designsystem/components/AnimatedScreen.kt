package com.dalmuina.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AnimatedScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(tween(300)) + slideInHorizontally(
            initialOffsetX = { it / 6 },
            animationSpec = tween(300)
        ),
        exit = fadeOut(tween(300)) + slideOutHorizontally(
            targetOffsetX = { -it / 6 },
            animationSpec = tween(300)
        )
    ) {
        content()
    }
}