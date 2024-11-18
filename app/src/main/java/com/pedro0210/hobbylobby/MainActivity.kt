package com.pedro0210.hobbylobby

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.pedro0210.hobbylobby.presentation.navigation.SetupNavGraph
import com.pedro0210.hobbylobby.ui.theme.HobbyLobbyTheme

import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            HobbyLobbyTheme {
                val context = LocalContext.current
                val packageManager = context.packageManager
                val componentName = context.packageName + ".MainActivity"
                SetupNavGraph(navHostController = rememberNavController())
                DarkThemeSideEffect(context, packageManager, componentName)
            }



        }
    }
}

@Composable
fun DarkThemeSideEffect(context: Context, packageManager: PackageManager, componentName: String) {
    val isDarkTheme = isSystemInDarkTheme()
    SideEffect {
        if (isDarkTheme) {
            packageManager.setComponentEnabledSetting(
                ComponentName(context, "$componentName.DarkIcon"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
            packageManager.setComponentEnabledSetting(
                ComponentName(context, "$componentName.LightIcon"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        } else {
            packageManager.setComponentEnabledSetting(
                ComponentName(context, "$componentName.LightIcon"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
            packageManager.setComponentEnabledSetting(
                ComponentName(context, "$componentName.DarkIcon"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        }
    }
}
