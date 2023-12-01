package vara17.pokedexmvp.model.data

sealed class ScreenState {
    object Loading : ScreenState()
    object Success : ScreenState()
    object Error : ScreenState()
}

sealed class Screen {
    object Pokedex : Screen()
    object Pokemon : Screen()
}