package ar.edu.ort.parcial_tp3.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class ExampleViewModel @Inject constructor(
//    private val getUseCase : GetService
//): ViewModel(){
//    var example = mutableStateOf("Cargando...")
//
//    fun loadExample(){
//        //Async
//        viewModelScope.launch {
//
//        }
//    }
//}
