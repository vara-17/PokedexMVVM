package vara17.pokedexmvvm.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import vara17.pokedexmvvm.R

val PokemonFont = FontFamily(Font(R.font.font_pokemon))

@Composable
fun PokemonTextStyle(): TextStyle {
    return TextStyle(
        fontFamily = PokemonFont,
        color = Blue,
        fontWeight = FontWeight.Bold
    )
}