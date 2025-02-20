### Code:

**Link: https://github.com/Z-Siqi/JetpackCompose-Gadget/blob/main/codes/DialogWithMenu/DialogWithMenu.kt**


### How to Use:

````
data class TestData(val t: String, val v: Int)

val test = listOf(TestData("TEST A", 0), TestData("TEST B", 1)).toTypedArray()
DialogWithMenu(
    onDismissRequest = { /*TODO*/ },
    confirm = { /*TODO*/ },
    confirmText = "Confirm",
    dismissText = "Dismiss",
    title = "TITLE",
    menuListGetter = test,
    menuText = { if (it == test[1]) "TEST A" else "TEST" },
    functionalType = { false }
)
````

> Support any type of list, use `.toTypedArray()` to convert list to Array

**Example:**

````
@Composable
fun DialogWithMenuPreview() {
    var state by rememberSaveable { mutableStateOf(true) }
    data class TestData(val t: String, val v: Int)

    val test = listOf(TestData("TEST A", 0), TestData("TEST B", 1)).toTypedArray()
    if (state) DialogWithMenu(
        onDismissRequest = { state = false },
        confirm = { /*TODO*/ state = false },
        confirmText = "Confirm",
        dismissText = "Dismiss",
        title = "TITLE",
        menuListGetter = test,
        menuText = { if (it == test[1]) "TEST A" else "TEST" },
        functionalType = { false }
    )
}
````

### LICENSE

````
MIT License

Copyright (c) 2025 Siqi Zhao

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
