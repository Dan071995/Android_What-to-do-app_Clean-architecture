package com.example.cleanmvvmhilt.entity
//Сущность,хранящая JSON ответ от сервера
interface UsefulActivity {
    val activity:String
    val type:String
    val participants:String
    val price:String
    val link:String
    val key:String
    val accessibility:String
}