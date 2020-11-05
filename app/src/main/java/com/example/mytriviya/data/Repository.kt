package com.example.mytriviya.data


import com.example.mytriviya.model.ModelClass
import com.example.mytriviya.data.local.TriviyaDataBase


class Repository(private val db: TriviyaDataBase) {

 //coroutines suspend functioni to insert data in the background
    suspend fun insertTrivia(model: ModelClass) =
        db.getTriviaDao().insert(model)


    fun getNewsFromLocal() =
        db.getTriviaDao().getAllTrivia()


}