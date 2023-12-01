package vara17.pokedexmvvm.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import vara17.pokedexmvp.model.data.Screen
import vara17.pokedexmvvm.model.data.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView(PokemonViewModel())
        }
    }
}

@Composable
fun MainView(vm: PokemonViewModel){
    val screenToShow: State<Screen> = vm.screen.collectAsState()
    val pokedexLoaded = remember { mutableStateOf(false) }

    BackHandler(screenToShow.value.equals(Screen.Pokemon)) {
        vm.showPokedex()
    }

    when(screenToShow.value){
        Screen.Pokedex -> {
            PokedexScreen(vm, pokedexLoaded)
        }
        Screen.Pokemon -> {
            PokemonScreen(vm)
        }
    }
}
