package com.example.tbcrevision8.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tbcrevision8.common.ResponseHandler
import com.example.tbcrevision8.data.model.Random
import com.example.tbcrevision8.domain.repository.RandomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(val randomRepository: RandomRepository): ViewModel() {

    private val _randomFlow = MutableSharedFlow<ResponseHandler<List<Random>>>()
    val randomFlow get() = _randomFlow.asSharedFlow()

    suspend fun getRandom(){
        randomRepository.getRandom().collect{
            _randomFlow.emit(it)
        }
    }
}