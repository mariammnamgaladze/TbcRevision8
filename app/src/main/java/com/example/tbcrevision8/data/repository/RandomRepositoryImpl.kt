package com.example.tbcrevision8.data.repository

import com.example.tbcrevision8.common.ResponseHandler
import com.example.tbcrevision8.data.model.Random
import com.example.tbcrevision8.data.network.RandomApi
import com.example.tbcrevision8.domain.repository.RandomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RandomRepositoryImpl @Inject constructor(private val random: RandomApi) : RandomRepository {
    override suspend fun getRandom(): Flow<ResponseHandler<List<Random>>>{
        return flow {
            emit(ResponseHandler.InProcess())
            val response = random.getRandoms()
            if (response.isSuccessful && response.body() != null) {
                emit(ResponseHandler.Success(response.body()!!))
            } else
                emit(ResponseHandler.Error("Error"))
        }
    }
}
