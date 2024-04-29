### Code:

**Link: https://github.com/Z-Siqi/JetpackCompose-Gadget/blob/main/codes/TimeSelectDialog/TimeSelectDialog.kt**


### How to Use:

````
val context = LocalContext.current
TimeSelectDialog(
    onDismissRequest = { /*TODO*/ },
    onConfirmClick = { cal ->
        /*TODO*/
    },
    context = context
)
````

**Example:**

````
val context = LocalContext.current
var show by rememberSaveable { mutableStateOf(false) }
Button(onClick = { show = true }) {
    Text(text = "TEST")
}
if (show) {
    TimeSelectDialog(
        onDismissRequest = { show = false },
        onConfirmClick = { cal ->
		    // 'cal' is the number of milliseconds remaining between now and the selected time
            Toast.makeText(context, "$cal", Toast.LENGTH_SHORT).show()
        },
        context = context
    )
}
````

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
