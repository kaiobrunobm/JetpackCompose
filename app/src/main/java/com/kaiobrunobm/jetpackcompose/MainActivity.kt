package com.kaiobrunobm.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaiobrunobm.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {

                val focusManager = LocalFocusManager.current

                var inputUser by remember {
                    mutableStateOf("")
                }

                var listNames by remember {
                    mutableStateOf(listOf<String>())
                }


                Column {
                    Header()

                    HorizontalDivider(
                        thickness = 1.dp
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,

                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = inputUser,
                            onValueChange = { text ->
                                inputUser = text
                            },
                            label = { Text(text = "Username") },
                        )

                        Button(
                            onClick = {
                                if (inputUser.isNotBlank()) listNames = listNames + inputUser
                                focusManager.clearFocus()
                                inputUser = ""

                            }, modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Icon(Icons.Default.Send, null)
                        }
                    }
                    List(listNames)
                }
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .padding(end = 24.dp, start = 24.dp, bottom = 10.dp, top = 60.dp)
    ) {
        Text(
            text = "Hello Kaio Bruno",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

    }
}


@Composable
fun List(users: List<String>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 20.dp)
            .fillMaxSize()
    ) {
        items(users) { currentUser ->
            ItemList(currentUser)
        }

    }
}

@Composable

fun ItemList(userName: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, color = Color(0xFF808080).copy(0.3f), shape = RoundedCornerShape(14))
            .padding(top = 20.dp, bottom = 20.dp, end = 14.dp, start = 14.dp)
    ) {
        Row {
            Icon(Icons.Default.AccountCircle, null, modifier = Modifier.size(50.dp))
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = userName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,

                    )
                Text(
                    text = "Post content"
                )
            }
        }

        Text(
            text = "8 min ago",
            fontWeight = FontWeight.Light
        )
    }
}