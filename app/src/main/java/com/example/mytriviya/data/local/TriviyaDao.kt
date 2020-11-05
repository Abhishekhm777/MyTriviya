package com.example.mytriviya.data.local


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytriviya.model.ModelClass
//Data access object class used to fetch and insert data to DB Asyscronosly
@Dao
interface TriviaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: ModelClass)

    @Query(value = "SELECT * FROM trivia")
    fun getAllTrivia(): LiveData<List<ModelClass>>



}