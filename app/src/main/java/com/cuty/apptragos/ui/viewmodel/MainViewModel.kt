package com.cuty.apptragos.ui.viewmodel

import androidx.lifecycle.*
import com.cuty.apptragos.data.data.DrinkEntity
import com.cuty.apptragos.domain.Repo
import com.cuty.apptragos.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo) : ViewModel() {
    private val tragosData = MutableLiveData<String>()

    fun setTrago(tragoName:String){
        tragosData.value = tragoName
    }
    init {
        setTrago("margarita")
    }

    val fetchTragosList = tragosData.distinctUntilChanged().switchMap { nombreDelTrago ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getTragosList(nombreDelTrago))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }
    fun guardarTrago(trago:DrinkEntity){
        viewModelScope.launch {
            repo.insertTrago(trago)

        }
    }
    fun getTragosFavoritos() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getTragosFavoritos())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}