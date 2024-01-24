/*
 * MIT License
 *
 * Copyright (c) 2024 Siqi Zhao
 *
 * Details: https://github.com/Z-Siqi/JetpackCompose-Gadget/blob/master/LICENCE
 */

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.placeCursorAtEnd
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.text2.input.selectAll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Layout(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        val state = rememberTextFieldState()
        var fixChooseAll by remember { mutableStateOf(false) }
        if (
            (!fixChooseAll) &&
            (state.text.selectionInChars.length == state.text.length) &&
            (state.text.isNotEmpty())
        ) {
            state.edit { placeCursorAtEnd() }
            state.edit { selectAll() }
            fixChooseAll = true
        } else if (state.text.selectionInChars.length < state.text.length) {
            fixChooseAll = false
        }
        BasicTextField2(
            state = state,
            modifier = modifier.fillMaxSize()
        )
    }
}