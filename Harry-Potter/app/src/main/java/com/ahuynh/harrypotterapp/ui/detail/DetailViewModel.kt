package com.ahuynh.harrypotterapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ahuynh.harrypotterapp.data.model.Character
import com.ahuynh.harrypotterapp.data.repository.DetailRepository
import com.ahuynh.harrypotterapp.utils.Constant.KEY_HOUSE
import com.ahuynh.harrypotterapp.utils.HouseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle : SavedStateHandle,
    private val repository : DetailRepository
): ViewModel() {


    private val houseName = savedStateHandle.get<HouseType>(KEY_HOUSE)?.name
    var isLoading = MutableLiveData<Boolean>()

    val characterList : LiveData<Character> = liveData(Dispatchers.IO){
        isLoading.postValue(true)
        houseName?.let{
            emit(repository.getCharacters(it))
        }
        isLoading.postValue(false)
    }

}