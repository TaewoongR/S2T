package com.ryu.s2t.feature.record

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ryu.s2t.feature.navigation.NavRoute

fun NavController.navigateToRecord(navOptions: NavOptions? = null) {
    navigate(NavRoute.Record, navOptions)
}

fun NavGraphBuilder.addDiaryWritingGraph(
    finishApp: () -> Unit = { }
) {
    composable<NavRoute.Record> { backStackEntry ->
        DiaryWritingRoute(
            finishApp = finishApp
        )
    }
}
