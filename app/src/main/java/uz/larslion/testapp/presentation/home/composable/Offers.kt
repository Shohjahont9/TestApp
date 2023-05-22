package uz.larslion.testapp.presentation.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import uz.larslion.testapp.domain.offers.OfferDomainModel
import uz.larslion.testapp.presentation.home.HomeScreenNavigationCommands
import uz.larslion.testapp.presentation.home.HomeScreenUiState
import uz.larslion.testapp.presentation.home.HomeScreenViewModel
import uz.larslion.testapp.presentation.home.ScreenState

@Composable
fun Offers(uiState: HomeScreenUiState, viewModel: HomeScreenViewModel) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 50.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "\uD83C\uDF3F  Test Project",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
        items(uiState.data){offer ->
            Offer(offer = offer, viewModel)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Offer(offer: OfferDomainModel, viewModel: HomeScreenViewModel) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = MaterialTheme.colorScheme.surface),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        onClick = {
             viewModel.setOneTimeEvent(event = HomeScreenNavigationCommands.ShowToast(offer.attr))
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberAsyncImagePainter(offer.image.url),
                contentDescription = null,
                modifier = Modifier
                    .size(width = offer.image.width.dp, height = offer.image.height.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )
        }
        Column(Modifier.padding(8.dp)) {
            Text(
                text = offer.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = offer.brand,
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = offer.category,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = offer.merchant,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}