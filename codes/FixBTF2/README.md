### About

As of now (24, Jan, 2024), the problem of BasicTextField2 not being able to delete texts after click select all text still exists. 
(androidx.compose.foundation:foundation-android:1.6.0-rc01)

Here's a way to fix it.

**Example code Link: https://github.com/Z-Siqi/JetpackCompose-Gadget/blob/main/codes/FixBTF2/ExampleCode.kt**

### How to Use:

Keep these codes below

````
/***/
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
    //Your codes
)
/***/
````

[TextFieldState() usage tips](https://developer.android.com/reference/kotlin/androidx/compose/foundation/text2/input/TextFieldState)

### LICENSE

````
MIT License

Copyright (c) 2024 Siqi Zhao

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
````