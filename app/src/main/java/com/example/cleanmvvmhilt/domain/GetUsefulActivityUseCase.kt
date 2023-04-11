package com.example.cleanmvvmhilt.domain

import com.example.cleanmvvmhilt.data.UsefulActivitiesRepository
import com.example.cleanmvvmhilt.entity.UsefulActivity
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor(
    private val usefulActivitiesRepository: UsefulActivitiesRepository
) {
    suspend fun execute(): UsefulActivity {
        //Возвращаем String, которая хранит в себе информацию об задании (активнности)
        return usefulActivitiesRepository.getUsefulActivity()
    }
}