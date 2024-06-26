package com.example.testCICD

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testCICD.ui.theme.TestCICDTheme
import com.skydoves.flexible.bottomsheet.material3.FlexibleBottomSheet
import com.skydoves.flexible.core.FlexibleSheetSize
import com.skydoves.flexible.core.FlexibleSheetValue
import com.skydoves.flexible.core.rememberFlexibleBottomSheetState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestCICDTheme {
                val greeting = viewModel.getGreeting(stringResource(id = R.string.hello_world))
                val scope = rememberCoroutineScope()
                val configurationHeight = LocalConfiguration.current.screenHeightDp
                val density = LocalDensity.current
                var cardHeight by remember { mutableStateOf(100.dp) }
                val minimalCardHeightRatio = cardHeight.value / configurationHeight.dp.value

                val sheetState = rememberFlexibleBottomSheetState(
                    flexibleSheetSize = FlexibleSheetSize(
                        fullyExpanded = 1f,
                        intermediatelyExpanded = 0.5f,
                        slightlyExpanded = minimalCardHeightRatio,
                    ),
                    isModal = false,
                    skipSlightlyExpanded = false,
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Greeting(
                        greeting = greeting,
                        modifier = Modifier.clickable {
                            scope.launch {
                                sheetState.show()
                            }
                        }
                    )
                }

                FlexibleBottomSheet(
                    sheetState = sheetState,
                    containerColor = Color.Black,
                    onDismissRequest = { scope.launch { sheetState.hide() } },
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .onGloballyPositioned {
                                cardHeight = with(density) { it.size.height.toDp() }
                        },
                    ) {
                        Button(
                            onClick = {
                                scope.launch {
                                    when (sheetState.swipeableState.currentValue) {
                                        FlexibleSheetValue.SlightlyExpanded -> sheetState.intermediatelyExpand()
                                        FlexibleSheetValue.IntermediatelyExpanded -> sheetState.fullyExpand()
                                        else -> sheetState.hide()
                                    }
                                }
                            },
                        ) {
                            Text(text = "Expand Or Hide")
                        }

                        var count by remember { mutableStateOf(0) }
                        this@Column.AnimatedVisibility(
                            visible = sheetState.swipeableState.targetValue == FlexibleSheetValue.IntermediatelyExpanded
                            || sheetState.swipeableState.targetValue == FlexibleSheetValue.FullyExpanded,
                        ) {
                            Column {
                                for(i in 0..count) {
                                    Text(
                                        text = "Hello, World!",
                                        fontSize = 24.sp,
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(vertical = 16.dp)
                                    )
                                }

                                Button(
                                    onClick = {
                                        count += 1
                                    },
                                ) {
                                    Text(text = "Add More Text")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
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

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestCICDTheme {
        Greeting("Hello, World!")
    }
}
