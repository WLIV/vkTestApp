package com.example.vktestapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vktestapp.entities.Friend
import com.example.vktestapp.repository.VkApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    val appState : MutableStateFlow<VkAppState> = MutableStateFlow(
        VkAppState(
            requestState = VkRequestState.Loading,
            screenState = VkAppScreenMode.FriendList
        )
    )
    private val client = VkApiClient.getVkApiClientInstance()

    fun getFriends(id: String, apiToken: String){
        viewModelScope.launch {
            val response = try {
                client.getFriendsList(userId = id, accessToken = apiToken)

            } catch (e : Exception){
                e.printStackTrace()
                null
            }
            println(response?.response?.items)
            if (response?.response?.items != null){

                appState.value = appState.value.copy(requestState = VkRequestState.Success(
                    response.response.items
                ))
                return@launch
            }
            appState.value = appState.value.copy(requestState = VkRequestState.Error)

        }
    }
}
data class VkAppState(
    val requestState: VkRequestState,
    val screenState: VkAppScreenMode
)

sealed class VkAppScreenMode {
    object FriendList : VkAppScreenMode()
}

sealed class VkRequestState{
    object Error: VkRequestState()
    object Loading : VkRequestState()
    data class Success(val friendList: List<Friend>): VkRequestState()
}