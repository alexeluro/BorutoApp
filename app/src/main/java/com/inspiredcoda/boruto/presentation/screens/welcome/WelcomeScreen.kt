package com.inspiredcoda.boruto.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inspiredcoda.boruto.constants.Constants.DataStore.LAST_ONBOARDING_PAGE
import com.inspiredcoda.boruto.domain.model.OnBoardingPage
import com.inspiredcoda.boruto.ui.theme.EXTRA_LARGE_PADDING
import com.inspiredcoda.boruto.ui.theme.SMALL_PADDING
import com.inspiredcoda.boruto.ui.theme.buttonBackgroundColor
import com.inspiredcoda.boruto.ui.theme.descriptionColor
import com.inspiredcoda.boruto.ui.theme.pagerIndicatorActiveColor
import com.inspiredcoda.boruto.ui.theme.pagerIndicatorInactiveColor
import com.inspiredcoda.boruto.ui.theme.titleColor
import com.inspiredcoda.boruto.ui.theme.welcomeScreenBackgroundColor

@ExperimentalFoundationApi
@Composable
fun WelcomeScreen(navController: NavHostController) {

    val welcomeScreenPages = OnBoardingPage.pages

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        welcomeScreenPages.size
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.welcomeScreenBackgroundColor)
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f),
            state = pagerState,
            pageSpacing = 0.dp,
            verticalAlignment = Alignment.CenterVertically,
            userScrollEnabled = true,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),
            beyondBoundsPageCount = 0,
            pageSize = PageSize.Fill,
            flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
            key = null,
            pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                Orientation.Horizontal
            )
        ) { page ->
            PagerScreen(onBoardingPage = welcomeScreenPages[page])
        }

        PagerIndicator(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
                .weight(1f),
            pagerState = pagerState
        )

        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {

        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {

        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == LAST_ONBOARDING_PAGE
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.buttonBackgroundColor,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = { onClick() }) {
                Text(text = "Finish")
            }
        }

    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(modifier: Modifier, pagerState: PagerState? = null) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        pagerState?.let {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.pagerIndicatorActiveColor else MaterialTheme.colorScheme.pagerIndicatorInactiveColor
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }

    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "Onboarding image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
            color = MaterialTheme.colorScheme.titleColor,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.description,
            color = MaterialTheme.colorScheme.descriptionColor,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

    }

}