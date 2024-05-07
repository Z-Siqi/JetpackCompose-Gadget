### About

Temporarily fix some known issues from `androidx.compose.foundation:foundation-android:1.7.0-alpha08`

**Code Link: https://github.com/Z-Siqi/JetpackCompose-Gadget/blob/main/codes/FixBTF2/ExampleCode.kt**

### How to Use:

Uses just like the original BasicTextField.

````
BasicTextField2(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    inputTransformation: InputTransformation? = null,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
    cursorBrush: Brush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant),
    outputTransformation: OutputTransformation? = null,
    decorator: TextFieldDecorator? = null,
    scrollState: ScrollState = rememberScrollState(),
    // Fixes Control
    enableFixSelection: Boolean = true,
    enableFixNonEnglishKeyboard: Boolean = true,
    verticalScrollWhenCursorUnderKeyboard: Boolean = false,
    extraScrollValue: Int = 1
)
````

**enableFixSelection:**
Fixed selection will broken and delete text before selected text when second delete a line; In Chinese Simplified keyboard will be totally broken, cannot delete anything.

**enableFixNonEnglishKeyboard:**
Fixed non-english keyboard may unable to delete texts after selected.

**verticalScrollWhenCursorUnderKeyboard (Off by default):**
Fixed the text area won't scroll to visible when clicked area below keyboard (Only happend in androidx.compose.foundation:foundation-android:1.7.0-alpha08. If below will not work!!!).

**extraScrollValue (Only work when `verticalScrollWhenCursorUnderKeyboard = true`):**
Adjust additional rolling values as needed according to actual conditions.

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
