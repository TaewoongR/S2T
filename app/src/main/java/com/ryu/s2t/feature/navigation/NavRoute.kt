package com.ryu.s2t.feature.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoute {
    @Serializable
    data object Record : NavRoute
}
