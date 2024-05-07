package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceLayout()
            }
        }
    }
}

@Composable
fun ArtImage(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 28.dp),
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = "https://buffer.com/library/content/images/2023/10/free-images.jpg")
                    .apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {}).build()
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

@Composable
fun ArtInfo() {
    Box(
        modifier = Modifier.background(Color.Blue.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp)
        ) {
            Text(
                text = "Connected to process 18789 on device 'Pixel_2_API_31 [emulator-5554]",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row() {
                Text(
                    text = "Truong Anim",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "(2021)",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun ControlButton() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(R.string.previous),
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(R.string.previous),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            ArtImage(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .graphicsLayer(
                        shadowElevation = 12f,
                        shape = RectangleShape
                    )
            )
            Spacer(modifier = Modifier.height(28.dp))
            ArtInfo()
            Spacer(modifier = Modifier.height(16.dp))
            ControlButton()

        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun ArtSpaceLayoutPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}