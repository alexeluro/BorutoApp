package com.inspiredcoda.boruto.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.inspiredcoda.boruto.R
import com.inspiredcoda.boruto.ui.theme.Purple500
import com.inspiredcoda.boruto.ui.theme.Purple700

@Composable
fun SplashScreen(navController: NavHostController) {
    /* Remember to import this androidx.compose.animation.core.Animatable NOT
    *  androidx.compose.animation.Animatable
    * */
    val degreeOfRotation = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(key1 = true) {
        degreeOfRotation.animateTo(
            targetValue = 360f,
            animationSpec = tween(durationMillis = 1000, delayMillis = 200)
        )
    }

    Splash(degreeOfRotation.value)
}

@Composable
fun Splash(degree: Float) {
    if (isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.rotate(degree),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "App Logo"
            )
        }
    } else {
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(Purple700, Purple500)))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.rotate(degree),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "App Logo"
            )
        }
    }

}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(degree = 0f)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Splash(degree = 0f)
}

