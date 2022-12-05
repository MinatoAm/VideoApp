package com.example.videoapp.presentation.video


import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.videoapp.R
import com.example.videoapp.domain.model.Video
import com.example.videoapp.presentation.ui.components.EditTextColors
import com.example.videoapp.presentation.ui.theme.Typography
import com.example.videoapp.presentation.video.components.VideoListItem
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.REPEAT_MODE_ONE
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt


@Composable
fun VideoListScreen(onCloseClick: () -> Unit) {

    val vm = koinViewModel<VideoListViewModel>()
    val state = vm.state.value

    var selectedItem: Video? by remember(state.videos) {
        mutableStateOf(state.videos.firstOrNull())
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var showLoading by remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                },
                navigationIcon = {
                    IconButton(
                        onClick = onCloseClick
                    ) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.primary,
                elevation = 0.dp
            )
        }, content = {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .padding(top = 107.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    selectedItem?.let {
                        VideoPlayer(
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(RoundedCornerShape(15.dp)),
                            showDialog,
                            selectedItem = it
                        ) {
                            showLoading = false
                        }
                    }

                    Button(
                        modifier = Modifier
                            .padding(top = 44.dp)
                            .width(144.dp)
                            .height(46.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        onClick = { showDialog = !showDialog },
                        shape = RoundedCornerShape(5), colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colors.onBackground
                        )
                    ) {
                        Text(
                            stringResource(id = R.string.button_text),
                            style = Typography.button,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    VideoListItem(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp),
                        videos = state.videos,
                        selectedItem?.id ?: ""
                    ) {
                        selectedItem = it
                    }

                }

                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }

                if (state.isLoading || showLoading) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .fillMaxSize()
                    ) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        })
}

@Composable
private fun draggableText(maxX: Float, maxY: Float, isVisible: Boolean) {

    var text by rememberSaveable { mutableStateOf("") }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var textWidth by remember { mutableStateOf(0f) }
    var textHeight by remember { mutableStateOf(0f) }

    val editTextColors: TextFieldColors = EditTextColors()

    if (isVisible) {
        TextField(
            modifier = Modifier
                .onGloballyPositioned {
                    textWidth = it.size.width.toFloat()
                    textHeight = it.size.height.toFloat()
                }
                .offset {
                    IntOffset(
                        offsetX.roundToInt(),
                        offsetY.roundToInt()
                    )
                }
                .pointerInput(Unit) {
                    detectDragGestures(
                    ) { _, dragAmount ->
                        val xPosition = dragAmount.x + offsetX
                        val yPosition = dragAmount.y + offsetY
                        offsetX = xPosition.coerceIn(0f, maxX - textWidth)
                        offsetY = yPosition.coerceIn(0f, maxY - textHeight)

                    }
                },
            value = text,
            onValueChange = {
                text = it
            }, colors = editTextColors, textStyle = Typography.h1
        )
    }
}

@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier, showDialog: Boolean,
    selectedItem: Video,
    onReady: () -> Unit
) {
    val context = LocalContext.current
    var maxX by remember { mutableStateOf(0f) }
    var maxY by remember { mutableStateOf(0f) }

    Box(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                setMediaItem(
                    MediaItem.fromUri(
                        selectedItem.fileUrl
                    )
                )
                repeatMode = REPEAT_MODE_ONE
            }
        }

        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                if (playbackState == ExoPlayer.STATE_READY) {
                    onReady.invoke()
                }
            }
        })

        LaunchedEffect(key1 = selectedItem.fileUrl, block = {
            exoPlayer.run {
                setMediaSource(buildMediaSource(selectedItem.fileUrl, context))
                playWhenReady = true
                prepare()
            }
        })

        DisposableEffect(key1 = Unit) { onDispose { exoPlayer.release() } }

        AndroidView(modifier = Modifier
            .onGloballyPositioned {
                maxX = it.size.width.toFloat()
                maxY = it.size.height.toFloat()
            },
            factory = {
                StyledPlayerView(context).apply {
                    player = exoPlayer
                    layoutParams =
                        FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    useController = false
                }
            }

        )

//        if (showDialog) {
        draggableText(maxX, maxY, showDialog)
//        }
    }
}

private fun buildMediaSource(url: String, context: Context): ProgressiveMediaSource {

    val mUri: Uri = Uri.parse(url)
    val dataSourceFactory = DefaultDataSourceFactory(
        context
    )
    return ProgressiveMediaSource.Factory(dataSourceFactory)
        .createMediaSource(MediaItem.Builder().setUri(mUri).build())
}