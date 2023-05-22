package uz.larslion.testapp.presentation.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import uz.larslion.navoiyqorakol.presentation.extensions.collectAsStateWithLifecycle
import uz.larslion.testapp.presentation.home.composable.Offers

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenView(uiState, viewModel)

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is HomeScreenNavigationCommands.ShowToast -> {
                    context.showToast(event.attrs.toString())
                }
            }
        }
    }
}

@Composable
fun HomeScreenView(
    uiState: HomeScreenUiState,
    viewModel: HomeScreenViewModel,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Offers(uiState = uiState, viewModel = viewModel)
        when (uiState.screenState) {
            ScreenState.Loading -> CircularProgressIndicator(modifier = Modifier.size(50.dp))
            ScreenState.Error -> {
            // TODO your error logic
             }
            else -> {}
        }
    }
}

fun Context.showToast(message: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, length).show()
}