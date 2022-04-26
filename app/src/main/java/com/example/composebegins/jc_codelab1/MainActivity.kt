package com.example.composebegins.jc_codelab1

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebegins.ui.theme.ComposeBeginsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBeginsTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnBoarding) {
        OnBoardingScreen(onContinueClicked = { shouldShowOnBoarding = false })
    } else {
        Greetings()
    }
}

@Composable
fun Greetings(names: List<String> = List(1000) { "$it" }) {
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(14.dp)) {
            LazyColumn {
                items(names) { name -> Greeting(name) }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        targetValue = if (expanded) 72.dp else 0.dp,
        animationSpec = tween(durationMillis = 1500)
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "LazyColumn\n(instead of RecyclerView)")
                Text(text = " → Slot #$name")

            }

            OutlinedButton(onClick = { expanded = !expanded }) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit) {
    // TODO: This state should be hoisted

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Codelab #1 COMPLETE!")

            Button(
                modifier = Modifier.padding(vertical = 21.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue to see")
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun ShowMyAPP() {
    ComposeBeginsTheme {
        MyApp()
    }
}

@Preview(showBackground = true)
@Composable
fun ShowGreetings() {
    ComposeBeginsTheme {
        Greetings()
    }
}

@Preview(showBackground = true)
@Composable
fun ShowOnBoarding() {
    ComposeBeginsTheme {
        OnBoardingScreen {

        }
    }
}