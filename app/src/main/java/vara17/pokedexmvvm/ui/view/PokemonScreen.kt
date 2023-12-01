package vara17.pokedexmvvm.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import vara17.pokedexmvp.model.data.ScreenState
import vara17.pokedexmvvm.R
import vara17.pokedexmvvm.model.data.PokemonViewModel
import vara17.pokedexmvvm.ui.theme.PokemonTextStyle
import vara17.pokedexmvvm.ui.theme.Yellow

@Composable
fun PokemonScreen(vm: PokemonViewModel) {
    val screenState: State<ScreenState> = vm.screenState.collectAsState()

    when (screenState.value) {
        ScreenState.Loading -> {
            LoadingScreen()
        }
        ScreenState.Success -> {
            PokemonSuccesScreen(vm)
        }
        ScreenState.Error -> {
            ErrorScreen()
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonSuccesScreen(vm: PokemonViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Yellow)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "POKEMON",
                fontSize = 48.sp,
                style = PokemonTextStyle(),
                modifier = Modifier.align(Alignment.Center)
            )

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            GlideImage(
                model = vm.pokemon.value.imageUrl,
                loading = placeholder(R.drawable.placeholder),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.Center)
            )
        }
        PokemonDetailItem(label = "Pokedex", value = "#" + vm.pokemon.value.number)
        PokemonDetailItem(label = "Height", value = vm.pokemon.value.height.toString())
        PokemonDetailItem(label = "Weight", value = vm.pokemon.value.weight.toString())
        vm.pokemon.value.types?.let { PokemonDetailItem(label = "Types", value = it) }
    }
}

@Composable
fun PokemonDetailItem(label: String, value: String) {
    Text(
        text = "$label: $value",
        fontSize = 24.sp,
        style = PokemonTextStyle(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}
