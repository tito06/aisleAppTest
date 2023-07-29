package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DashBoardViewModel: ViewModel() {

    var usersPhoti:List<Photo> by mutableStateOf(listOf())
    var photi:List<String> by mutableStateOf(listOf())
    //var photoUrl:List<String> by mutableStateOf(listOf())
    var photoUrl:MutableList<String>  = mutableListOf()



    fun getUserPhoto(context: Context){
        var sessionManager: SessionManager = SessionManager(context)

        var token : String? = sessionManager.fetchAuthToken()


        viewModelScope.launch {
            val services = RetrofitHelper.getInstance().create(ApiServices::class.java)
            try {
                val photoResponse = services.getData(token)
                usersPhoti = photoResponse.body()?.invites!!.profiles[0].photos

                for (i in 0..usersPhoti.size){

                    photoUrl.add(usersPhoti[i].photo)
                    photi = listOf(usersPhoti[i].photo)
                    Log.d("PhotoResponse", photoUrl.toString())

                }

                /*for (i in 0..photi.size){
                    photoUrl = listOf(photi[i].photo)
                    Log.d("PhotoURL", photoUrl.toString())
                }*/
            }catch (e:Exception){

            }

        }
    }
}