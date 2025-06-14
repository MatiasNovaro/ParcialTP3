package ar.edu.ort.parcial_tp3.ui.screens.login

import androidx.lifecycle.ViewModel
import ar.edu.ort.parcial_tp3.domain.model.User
import ar.edu.ort.parcial_tp3.domain.repository.UserRepository
import ar.edu.ort.parcial_tp3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import ar.edu.ort.parcial_tp3.data.local.session.UserSession
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userSession: UserSession,
) : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<User>>(Resource.Loading())
    val loginState: StateFlow<Resource<User>> = _loginState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = Resource.Loading()
            try {
                val result = userRepository.loginUser(username, password)
                if (result is Resource.Success) {
                    val user = result.data
                    if (user != null) {
                        userSession.saveUserId(user.id)
                        userSession.saveAccessToken(user.accessToken)
                        userSession.setLoggedIn(true)
                    }

                }
                _loginState.value = result

            } catch (e: Exception) {
                _loginState.value = Resource.Error("Login fallido: ${e.message}")
            }

        }
    }
}
