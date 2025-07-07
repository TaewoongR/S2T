package com.ryu.s2t.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ryu.s2t.feature.record.navigateToRecord

class MainNavigator(
    private val onFinishApp: () -> Unit,
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = NavRoute.Record

    private fun popBackStack() {
        navController.popBackStack()
    }

    fun navigateToRecord() {
        navController.navigateToRecord()
    }

    fun finishApp() {
        onFinishApp()
    }

    private inline fun <reified T : NavRoute> isSameCurrentDestination(): Boolean =
        navController.currentDestination?.hasRoute<T>() == true
}

@Composable
fun rememberMainNavigator(
    onFinishApp: () -> Unit,
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(onFinishApp, navController)
}
