package com.example.artspace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.artspace.data.ArtModel
import com.example.artspace.ui.theme.ArtSpaceTheme
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.math.min
import kotlin.math.max

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arts: List<ArtModel> =
            listOf(
                ArtModel(
                    name = "Elements of a MutableList can be added, removed, or changed after creation.",
                    author = "Truong Anim",
                    publicAt = 1715097718000,
                    url = "https://buffer.com/library/content/images/2023/10/free-images.jpg"
                ),
                ArtModel(
                    name = "You can perform both read and write operations on a MutableList, such as adding elements.",
                    author = "Truong Anim",
                    publicAt = 1715097718000,
                    url = "https://images.unsplash.com/photo-1544376798-89aa6b82c6cd?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8dmVydGljYWx8ZW58MHx8MHx8fDA%3D"
                ),
                ArtModel(
                    name = "Use MutableList when you need to modify the collection by adding, removing, or changing elements.",
                    author = "Truong Anim",
                    publicAt = 1715097718000,
                    url = "https://img.freepik.com/free-photo/painting-mountain-lake-with-mountain-background_188544-9126.jpg"
                ),
                ArtModel(
                    name = "A MutableList is a mutable (modifiable) ordered collection of elements.",
                    author = "Truong Anim",
                    publicAt = 1715097718000,
                    url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxHl0PiPoy5Ytd2AdNMKkzHTn6lc-d0jNdUrBNKi0w_A&s"
                )
            )
        setContent {
            ArtSpaceTheme {
                ArtSpaceLayout(arts = arts)
            }
        }
    }
}

fun millisecondsToDateTime(milliseconds: Long): LocalDateTime {
    Log.d("TAG", milliseconds.toString())
    return LocalDateTime.ofEpochSecond(milliseconds / 1000, 0, ZoneOffset.UTC)
}

@Composable
fun ArtImage(url: String, modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 28.dp),
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = url)
                    .apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {}).build()
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

@Composable
fun ArtInfo(art: ArtModel) {
    Box(
        modifier = Modifier.background(Color.Blue.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp)
        ) {
            Text(
                text = art.name,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row() {
                Text(
                    text = art.author,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "(${millisecondsToDateTime(art.publicAt).year})",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun ControlButton(onNext: () -> Unit, onPre: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        ElevatedButton(onClick = {
            onPre()
        }) {
            Text(
                text = stringResource(R.string.previous),
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        ElevatedButton(onClick = {
            onNext()
        }) {
            Text(
                text = stringResource(R.string.previous),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtSpaceLayout(arts: List<ArtModel>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val pagerState = rememberPagerState(pageCount = {
                arts.size
            })
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                HorizontalPager(state = pagerState) { page ->
                    ArtImage(
                        url = arts[page].url,
                        modifier = Modifier
                            .graphicsLayer(
                                shadowElevation = 12f,
                                shape = RectangleShape
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
            ArtInfo(art = arts[pagerState.currentPage])
            Spacer(modifier = Modifier.height(16.dp))
            val coroutineScope = rememberCoroutineScope()
            ControlButton(onNext = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(min(pagerState.currentPage + 1, arts.size - 1))
                }
            }, onPre = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(max(pagerState.currentPage - 1, 0))
                }
            })

        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun ArtSpaceLayoutPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout(listOf())
    }
}