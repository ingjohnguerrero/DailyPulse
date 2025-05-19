package com.petros.efthumiou.dailypulse.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.petros.efthumiou.dailypulse.sources.application.Source
import com.petros.efthumiou.dailypulse.sources.presentation.SourcesState
import com.petros.efthumiou.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SourcesScreen(
    sourcesViewModel: SourcesViewModel = getViewModel(),
    onUpButtonClick: () -> Unit,
) {
    val sourceState = sourcesViewModel.state.collectAsState()
    Column {
        AppBar(onUpButtonClick)
        if (sourceState.value.loading)
            Loader()
        if (sourceState.value.error != null)
            ErrorMessage(message = sourceState.value.error ?: "I've got an error")
        if (sourceState.value.sources.isNotEmpty())
            SourcesListView(sourcesViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onUpButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Sources") },
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Up button",
                )
            }
        }
    )
}

@Composable
fun SourcesListView(
    viewModel: SourcesViewModel
) {
    SwipeRefresh(
        state = SwipeRefreshState(viewModel.state.value.loading),
        onRefresh = { viewModel.getSources(true) }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.state.value.sources) { source ->
                SourceItemView(source)
            }
        }
    }
}

@Composable
fun SourceItemView(
    source: Source,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Display source details
        Text(
            text = source.name,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.description,
            style = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.locale,
            style = TextStyle(fontSize = 14.sp, color = Color.Gray),
            modifier = Modifier.fillMaxWidth().align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}


