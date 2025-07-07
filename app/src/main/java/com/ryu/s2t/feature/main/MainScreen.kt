package com.ryu.s2t.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ryu.s2t.feature.navigation.MainNavHost
import com.ryu.s2t.feature.navigation.MainNavigator
import com.ryu.s2t.feature.navigation.rememberMainNavigator

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainNavigator: MainNavigator
) {
    MainContent(
        modifier = modifier,
        mainNavigator = mainNavigator
    )
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    mainNavigator: MainNavigator
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { paddingValues ->
        MainNavHost(
            modifier = modifier.padding(paddingValues),
            mainNavigator = mainNavigator
        )
    }
}
