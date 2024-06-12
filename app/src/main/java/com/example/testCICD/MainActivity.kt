package com.example.testCICD

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testCICD.ui.theme.TestCICDTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel = MainViewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestCICDTheme {
                val greeting = viewModel.getGreeting(stringResource(id = R.string.hello_world))
                // A surface container using the 'background' color from the theme
                var targetValue by remember { mutableStateOf(SheetValue.Expanded) }
                val scope = rememberCoroutineScope()
                val scaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = SheetState(
                        skipPartiallyExpanded = false,
                        density = LocalDensity.current,
                        initialValue = targetValue,
                        confirmValueChange = {
                            targetValue = it
                            true
                        },
                        skipHiddenState = false,
                    )
                )

//                val anchoredDraggableState = scaffoldState.bottomSheetState.anchoredDraggableState

                val configurationHeight = LocalConfiguration.current.screenHeightDp
                val halfHeight = configurationHeight / 6

                BottomSheetScaffold(
                    sheetContent = {
                        SheetContent(greeting, targetValue)
                    },
                    scaffoldState = scaffoldState,
                    sheetPeekHeight = halfHeight.dp,
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        Greeting(
                            greeting = greeting,
                            modifier = Modifier.clickable {
                                scope.launch {
                                    scaffoldState.bottomSheetState.show()
                                    targetValue = scaffoldState.bottomSheetState.targetValue
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

enum class SheetState {
    Hidden,
    Expanded,
    PartiallyExpanded,
    Collapsed,
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun Greeting(
    greeting: String,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            text = greeting,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetContent(
    greeting: String,
    targetValue: SheetValue,
    modifier: Modifier = Modifier,
) {
    Column {
        for(i in 0..100) {
            Text(
                text = when(targetValue) {
                    SheetValue.Hidden -> "Hidden"
                    SheetValue.Expanded -> "expanded"
                    SheetValue.PartiallyExpanded -> "partial"
                    else -> "else"
                },
                fontSize = when(targetValue) {
                    SheetValue.Hidden -> 0.sp
                    SheetValue.Expanded -> 32.sp
                    SheetValue.PartiallyExpanded -> 24.sp
                    else -> 12.sp
                },
                modifier = modifier
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestCICDTheme {
        Greeting("Hello, World!")
    }
}
