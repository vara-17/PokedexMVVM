package vara17.pokedexmvvm.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vara17.pokedexmvp.model.data.ScreenState
import vara17.pokedexmvvm.model.data.PokemonViewModel
import vara17.pokedexmvvm.ui.theme.PokemonTextStyle
import vara17.pokedexmvvm.ui.theme.Red
import vara17.pokedexmvvm.ui.theme.Yellow

@Composable
fun PokedexScreen(
    vm: PokemonViewModel,
    pokedexLoaded: MutableState<Boolean>
) {
    val screenState: State<ScreenState> = vm.screenState.collectAsState()
    LaunchedEffect(true) {
        if (!pokedexLoaded.value) {
            vm.loadPokedex()
            pokedexLoaded.value = true
        }
    }

    when (screenState.value) {
        ScreenState.Loading -> {
            LoadingScreen()
        }
        ScreenState.Success -> {
            PokedexSuccesScreen(vm)
        }
        ScreenState.Error -> {
            ErrorScreen()
        }
    }
}


@Composable
fun PokedexSuccesScreen(vm: PokemonViewModel) {
    val pokedex = vm.pokedex.collectAsState().value.pokedex

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Red)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "POKEDEX",
                style = PokemonTextStyle(),
                color = Yellow,
                fontSize = 60.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Red)
        ) {
            items(pokedex) { pokemon ->
                PokemonCard(name = pokemon.name, number = pokemon.id) {
                    vm.loadPokemon(pokemon.id)
                }
                Spacer(modifier = Modifier.size(8.dp))
            }
        }

    }
}

@Composable
fun PokemonCard(name: String, number: Int, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onItemClick() },
        backgroundColor = Yellow,
        shape = RoundedCornerShape(30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "#" + number,
                fontSize = 36.sp,
                style = PokemonTextStyle(),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = name.uppercase(),
                fontSize = 36.sp,
                style = PokemonTextStyle(),
                modifier = Modifier.weight(2f)
            )
        }
    }
}
