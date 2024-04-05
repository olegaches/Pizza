package com.olegaches.pizza

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import com.olegaches.pizza.presentation.root.RootContent
import com.olegaches.pizza.ui.theme.PizzaTheme
import me.tatarka.inject.annotations.Component

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = retainedComponent { componentContext ->
            val activityComponent = MainActivityComponent::class
                .create(ApplicationComponent.getInstance(this))
            activityComponent.injectRootFactory.createRoot(componentContext)
        }
        setContent {
            val darkTheme = isSystemInDarkTheme()
            ChangeSystemBarsTheme(darkTheme)
            PizzaTheme(darkTheme = darkTheme) {
                RootContent(root)
            }
        }
    }

    @Composable
    private fun ChangeSystemBarsTheme(darkTheme: Boolean) {
        val barColor = Color.TRANSPARENT
        DisposableEffect(darkTheme) {
            if (darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        barColor,
                    ),
                    navigationBarStyle = SystemBarStyle.dark(
                        barColor,
                    ),
                )
            } else {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.light(
                        barColor, barColor,
                    ),
                    navigationBarStyle = SystemBarStyle.light(
                        barColor, barColor,
                    ),
                )
            }
            onDispose {}
        }
    }
}

@Component
abstract class MainActivityComponent(@Component val parent: ApplicationComponent) {
    abstract val injectRootFactory: InjectRootFactory
}