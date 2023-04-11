package com.example.cleanmvvmhilt.data

import com.example.cleanmvvmhilt.entity.UsefulActivity
import com.google.gson.annotations.SerializedName

//Данный клас серриализует данные из MainRepositoryEventInfoDataSource
//Таким образом body() callback-а будет содержать 2 поля из этого класса, а не весь JSON файл
data class UsefulActivityDto(
    @SerializedName("activity") override val activity: String,
    @SerializedName("type") override val type: String,
    @SerializedName("participants") override val participants: String,
    @SerializedName("price") override val price: String,
    @SerializedName("link") override val link: String,
    @SerializedName("key") override val key: String,
    @SerializedName("accessibility") override val accessibility: String
) :UsefulActivity