package vara17.pokedexmvvm.model.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import vara17.pokedexmvp.model.data.*
import vara17.pokedexmvp.model.repository.PokemonRepository

class PokemonViewModel : ViewModel() {

    private val repository: PokemonRepository = PokemonRepository()

    private val _screen_state = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState: StateFlow<ScreenState> = _screen_state

    private val _screen = MutableStateFlow<Screen>(Screen.Pokedex)
    val screen: StateFlow<Screen> = _screen

    private val _pokedex = MutableStateFlow<PokedexModel>(PokedexModel(emptyList()))
    val pokedex: StateFlow<PokedexModel> = _pokedex

    private val _pokemon = MutableStateFlow<PokemonModel>(PokemonModel("", 0, 0, 0))
    val pokemon: StateFlow<PokemonModel> = _pokemon

    fun loadPokedex() {
        viewModelScope.launch {
            val response: PokedexResponse? = repository.getPokemonList()
            response?.let {
                it.error?.let {
                    _screen_state.value = ScreenState.Error
                } ?: run {
                    _pokedex.value = response.model
                    _screen_state.value = ScreenState.Success
                }
            } ?: run {
                _screen_state.value = ScreenState.Error
            }
        }
    }

    fun loadPokemon(id: Int) {
        viewModelScope.launch {
            _screen.value = Screen.Pokemon
            _screen_state.value = ScreenState.Loading
            val response: PokemonResponse? = repository.getPokemonInfo(id)
            response?.let {
                it.error?.let {
                    _screen_state.value = ScreenState.Error
                } ?: run {
                    _pokemon.value = response.model
                    _screen_state.value = ScreenState.Success
                }
            } ?: run {
                _screen_state.value = ScreenState.Error
            }
        }
    }

    fun showPokedex() {
        _screen_state.value = ScreenState.Success
        _screen.value = Screen.Pokedex
    }
}