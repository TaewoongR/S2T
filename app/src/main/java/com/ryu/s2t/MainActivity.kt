package com.ryu.s2t

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ryu.s2t.designsystem.theme.RecordAppTheme
import com.ryu.s2t.feature.main.MainScreen
import com.ryu.s2t.feature.navigation.MainNavigator
import com.ryu.s2t.feature.navigation.rememberMainNavigator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
        enableEdgeToEdge()
        setContent {
            val mainNavigator: MainNavigator =
                rememberMainNavigator(onFinishApp = {
                    Timber.d("MainActivity finished")
                    this.finish()
                })
            RecordAppTheme {
                MainScreen(mainNavigator = mainNavigator)
            }
        }
    }
}
