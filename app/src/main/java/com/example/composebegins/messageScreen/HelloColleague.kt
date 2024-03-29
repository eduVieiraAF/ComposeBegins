package com.example.composebegins.messageScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebegins.ui.theme.ComposeBeginsTheme


data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(com.example.composebegins.R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(0.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.onSecondary else MaterialTheme.colors.surface
        )

        Column(modifier = Modifier
            .clickable { isExpanded = !isExpanded }
            .border(0.2.dp, MaterialTheme.colors.secondary, RectangleShape)
            .padding(all = 8.dp)) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle2,
            )

            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                color = surfaceColor,
                elevation = 1.dp,
                modifier = Modifier
                    .animateContentSize()
                    .padding(all = 4.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview(name = "Message Screen", showBackground = true, showSystemUi = true)
@Composable
fun PreviewMessageCard() {
    ComposeBeginsTheme {
        Conversation(SampleData.conversationSample)
    }
}