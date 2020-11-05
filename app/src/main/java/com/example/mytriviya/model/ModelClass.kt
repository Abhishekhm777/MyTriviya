package com.example.mytriviya.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "trivia"
)
data class ModelClass(
    val answers: ArrayList<String>,
    val name: String?,
    val time: String?,
    val questions: ArrayList<String>
){
    @PrimaryKey(autoGenerate = true)
    var pId:Int=0

}