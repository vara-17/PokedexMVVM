package vara17.pokedexmvvm.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import vara17.pokedexmvvm.ui.theme.PokemonTextStyle
import vara17.pokedexmvvm.ui.theme.Red

@Preview
@Composable
fun ErrorScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Red),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error... try again later.",
            fontSize = 24.sp,
            style = PokemonTextStyle()
        )
    }
}
