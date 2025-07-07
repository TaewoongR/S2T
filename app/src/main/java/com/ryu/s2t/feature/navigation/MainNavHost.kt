package com.ryu.s2t.feature.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.ryu.s2t.feature.record.addDiaryWritingGraph

@SuppressLint("RestrictedApi")
@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    mainNavigator: MainNavigator
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        NavHost(
            navController = mainNavigator.navController,
            startDestination = mainNavigator.startDestination
        ) {
            addDiaryWritingGraph(
                finishApp = mainNavigator::finishApp
            )
        }
    }
}
