package work.racka.thinkrchive.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import timber.log.Timber
import work.racka.thinkrchive.ui.main.viewModel.ThinkpadListViewModel

@ExperimentalAnimationApi
@Composable
fun TestThinkpadList(viewModel: ThinkpadListViewModel = hiltViewModel()) {
    val thinkpadList by  remember {
        derivedStateOf {
            viewModel.thinkpadList
        }
    }
    val loadError by remember {
        viewModel.loadError
    }
    val isLoading by remember {
        viewModel.isLoading
    }

    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(thinkpadList) {
            Timber.d("items called")
            Text(text = it.model, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "Release Data: ${it.releaseDate}")
            Text(text = "Processors: ${it.processors}")
            Text(text = "Platform: ${it.processorPlatforms}")
            Text(text = loadError)
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            AnimatedVisibility(isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}