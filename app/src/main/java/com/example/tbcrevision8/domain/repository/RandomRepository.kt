package com.example.tbcrevision8.domain.repository

import com.example.tbcrevision8.common.ResponseHandler
import com.example.tbcrevision8.data.model.Random
import kotlinx.coroutines.flow.Flow

interface RandomRepository {
    suspend fun getRandom(): Flow<ResponseHandler<List<Random>>>

}