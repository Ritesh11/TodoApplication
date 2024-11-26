package com.rks.todoapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rks.todoapplication.ui.theme.TodoApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApplicationTheme {
                    Greeting(modifier = Modifier)

            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        var nameList by remember {
            mutableStateOf(emptyList<String>())
        }

        AddNameView(onAddName = {
            nameList = nameList + it
        }, modifier)

        NameListContainer(nameList, modifier)
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoApplicationTheme {
        Greeting()
    }
}

@Composable
fun AddNameView(
    onAddName: (String) -> Unit,
    modifier: Modifier
) {
    var name by remember {
        mutableStateOf("")
    }

        Row(
            modifier = modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {

            TextField(
                value = name,
                onValueChange = {
                    name = it;
                }, modifier = modifier
                    .weight(1f)
                    .height(55.dp)
            )

            Button(
                onClick = {
                    if (name.isNotEmpty()) {
                        onAddName(name)
                        name = ""
                    }
                }, modifier = modifier
                    .align(Alignment.CenterVertically)
                    .height(55.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Submit")
            }
    }
}

@Composable
fun NameListContainer(nameList: List<String>, modifier: Modifier) {
    LazyColumn {
        items(nameList) { name ->
            ItemList(name, modifier)
        }

    }
}


@Composable
fun ItemList(
    name: String,
    modifier: Modifier
) {
    Text(
        text = name,
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    )
    HorizontalDivider()

}

