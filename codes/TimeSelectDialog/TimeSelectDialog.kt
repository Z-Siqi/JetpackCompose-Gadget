/*
 * MIT License
 *
 * Copyright (c) 2024 Siqi Zhao
 *
 * Details: https://github.com/Z-Siqi/JetpackCompose-Gadget/blob/master/LICENCE
 */

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.SoundEffectConstants
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSelectDialog(
    onDismissRequest: () -> Unit,
    onConfirmClick: (cal: Long) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    val view = LocalView.current

    var isTimeInput by rememberSaveable { mutableStateOf(false) }
    var datePickDialog by rememberSaveable { mutableStateOf(false) }
    var isDatePick by rememberSaveable { mutableStateOf(false) }
    var rememberDays by rememberSaveable { mutableIntStateOf(0) }

    val timePickLimit = if (!isLandscape() &&
        LocalConfiguration.current.screenHeightDp < 580 || isLandscape() &&
        LocalConfiguration.current.screenWidthDp < 595
    ) {
        Log.d("Limit", "Disabled some UI due to screen is too small!")
        true
    } else false

    val timePickerState = rememberTimePickerState()
    val now = Calendar.getInstance()
    val cal = Calendar.getInstance()
    cal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
    cal.set(Calendar.MINUTE, timePickerState.minute)
    if (cal.before(now) && !isDatePick) {
        cal.add(Calendar.DATE, 1)
    }
    val width = (LocalConfiguration.current.screenWidthDp * 0.8).toInt()
    val widthIn = if (width > 730) width else 630
    AlertDialog(
        modifier = modifier.widthIn(min = widthIn.dp),
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Row(modifier = modifier.fillMaxWidth()) {
                if (!timePickLimit) IconButton(onClick = {
                    isTimeInput = !isTimeInput
                    view.playSoundEffect(SoundEffectConstants.CLICK)
                }) {
                    fun icon(): Pair<ImageVector, String> = if (isTimeInput)
                        Pair(Icons.Default.DateRange, "Pick")
                    else Pair(Icons.Default.Edit, "Input")
                    val (icon, type) = icon()
                    Icon(
                        imageVector = icon, contentDescription = type,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = modifier.weight(1f))
                TextButton(onClick = onDismissRequest) {
                    Text(text = "Dismiss")
                }
                Spacer(modifier = modifier.width(10.dp))
                TextButton(onClick = {
                    onConfirmClick((cal.timeInMillis - now.timeInMillis))
                }) {
                    Text(text = "Confirm")
                }
            }
        },
        text = {
            Column(
                modifier = modifier.verticalScroll(rememberScrollState()) then if (isLandscape()) {
                    modifier.border(
                        2.dp, MaterialTheme.colorScheme.outlineVariant, ShapeDefaults.Medium
                    )
                } else modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = if (isLandscape()) modifier.padding(
                        start = 16.dp, end = 16.dp, top = 8.dp
                    ) else modifier,
                    propagateMinConstraints = false
                ) {
                    if (timePickLimit || isTimeInput
                    ) TimeInput(state = timePickerState) else {
                        TimePicker(
                            state = timePickerState,
                            layoutType = if (isLandscape()) TimePickerLayoutType.Horizontal else {
                                TimePickerLayoutType.Vertical
                            }
                        )
						var oldH by remember { mutableIntStateOf(timePickerState.hour) }
                        var oldM by remember { mutableIntStateOf(timePickerState.minute) }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && oldH != timePickerState.hour) {
                            oldH = timePickerState.hour
                            ContextCompat.getSystemService(context, Vibrator::class.java)?.vibrate(
                                VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK)
                            )
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && oldM != timePickerState.minute) {
                            oldM = timePickerState.minute
                            ContextCompat.getSystemService(context, Vibrator::class.java)?.vibrate(
                                VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK)
                            )
                        }
                    }
                }
                Spacer(modifier = modifier.fillMaxWidth())
                OutlinedButton(
                    onClick = {
                        if (!isDatePick) {
                            datePickDialog = true
                        } else {
                            cal.clear()
                            rememberDays = 0
                            isDatePick = false
                        }
                        view.playSoundEffect(SoundEffectConstants.CLICK)
                    },
                    colors = if (isDatePick) ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.primary
                    ) else ButtonDefaults.outlinedButtonColors(),
                    shape = ShapeDefaults.Medium,
                    modifier = modifier.fillMaxWidth(0.7f)
                ) {
                    Text(text = "Select a date")
                }
                Spacer(modifier = modifier.height(5.dp))

                val time = cal.timeInMillis - now.timeInMillis
                val (hour, minutes) = convertTime(time)
                Text(text = "$hour hour $minutes minutes later")

                val remindTime = now.timeInMillis + time
                val fullDateShort = "yyyy dd MMM hh:mm a"
                val formatter = remember { SimpleDateFormat(fullDateShort, Locale.getDefault()) }
                Text(
                    text = "Remind at ${formatter.format(remindTime)}"
                )
                Spacer(modifier = if (isLandscape()) modifier.height(8.dp) else modifier)
            }
        },
        properties = DialogProperties(usePlatformDefaultWidth = !isLandscape())
    )
    if (datePickDialog) {
        DatePickDialog(
            onDismissRequest = {
                datePickDialog = false
                view.playSoundEffect(SoundEffectConstants.CLICK)
            },
            onConfirm = {
                isDatePick = true
                datePickDialog = false
                view.playSoundEffect(SoundEffectConstants.CLICK)
            },
            selectedDate = {
                rememberDays = it
            },
            context = context,
            view = view
        )
    }
    if (isDatePick && rememberDays > 0) {
        cal.add(Calendar.DATE, rememberDays)
    } else {
        isDatePick = false
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    selectedDate: (day: Int) -> Unit,
    context: Context,
    view: View,
    modifier: Modifier = Modifier
) {
    var invalid by rememberSaveable { mutableStateOf(false) }
    var tooLarge by rememberSaveable { mutableStateOf(false) }
    var dialog by rememberSaveable { mutableStateOf(false) }
    DatePickerDialog(
        modifier = modifier.verticalScroll(rememberScrollState()),
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    if (!invalid) onConfirm()
                    if (tooLarge) dialog = true
                    if (invalid && !tooLarge) Toast.makeText(
                        context,
                        "Invalid action",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                colors = if (invalid) {
                    ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.outlineVariant)
                } else ButtonDefaults.textButtonColors()
            ) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "Dismiss")
            }
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        val now = Calendar.getInstance()
        val datePickerState = rememberDatePickerState()
        if (datePickerState.selectedDateMillis != null) {
            val calculateTime = datePickerState.selectedDateMillis!! - now.timeInMillis
            if (calculateTime < -86400000) {
                invalid = true
                tooLarge = false
            } else if (calculateTime < 0) {
                invalid = false
                tooLarge = false
                selectedDate((0))
            } else {
                invalid = false
                val day = (1 + calculateTime / 1000 / 60 / 60 / 24).toInt()
                if (day > 30) {
                    invalid = true
                    tooLarge = true
                    selectedDate((day))
                } else selectedDate((day))
            }
			view.playSoundEffect(SoundEffectConstants.CLICK)
        }
        if (dialog) {
            AlertDialog(
                onDismissRequest = { dialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        dialog = false
                        onConfirm()
                    }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { dialog = false }) {
                        Text(text = "Cancel")
                    }
                },
                text = {
                    Text(text = "It is not recommended to set it for too long")
                }
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = if (LocalConfiguration.current.screenHeightDp < 565) {
                    datePickerState.displayMode = DisplayMode.Input
                    false
                } else {
                    datePickerState.displayMode = DisplayMode.Picker
                    true
                }
            )
        }
    }
}

private fun convertTime(milliseconds: Long): Pair<Int, Int> {
    val seconds = milliseconds / 1000L
    val minutes = seconds / 60L
    val hours = (minutes / 60L).toInt()
    val remainingMinutes = (minutes % 60).toInt()
    return Pair(hours, remainingMinutes)
}

@Composable
private fun isLandscape(): Boolean {
    val config = LocalConfiguration.current
    return config.screenWidthDp > (config.screenHeightDp * 1.2)
}

@Preview
@Composable
private fun TimePreview() {
    TimeSelectDialog(
        onDismissRequest = {}, onConfirmClick = {},
        context = LocalContext.current
    )
}

@Preview
@Composable
private fun DatePreview() {
    DatePickDialog(
        onDismissRequest = {}, onConfirm = {}, selectedDate = {},
        context = LocalContext.current, view = LocalView.current
    )
}
