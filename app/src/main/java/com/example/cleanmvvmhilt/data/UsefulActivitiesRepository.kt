package com.example.cleanmvvmhilt.data

import com.example.cleanmvvmhilt.entity.UsefulActivity
import javax.inject.Inject

class UsefulActivitiesRepository @Inject constructor(
    private val mainRepositoryEventInfoDataSource: MainRepositoryEventInfoDataSource
) {

    suspend fun getUsefulActivity(): UsefulActivity {
        return mainRepositoryEventInfoDataSource.loadActivityInfo()
    }

}