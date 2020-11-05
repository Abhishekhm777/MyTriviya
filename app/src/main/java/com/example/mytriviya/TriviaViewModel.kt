package com.example.mytriviya

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytriviya.data.Repository
import com.example.mytriviya.model.ModelClass
import kotlinx.coroutines.launch

class TriviaViewModel(private val repo: Repository) : ViewModel() {

    private val _question = MutableLiveData<String>()

    var dataFromDb: LiveData<List<ModelClass>> = MutableLiveData()

    val question: LiveData<String>
        get() = _question


    private val _options = MutableLiveData<List<String>>()
    val options: LiveData<List<String>>
        get() = _options

    private var count = -1;

    init {
        prepareOptions()
        prepareQuestions()

    }

    lateinit var questions_list: List<String>
    lateinit var optionsList: MutableList<List<String>>

    fun getQuestion(n: Int) {
        count++
        _question.value = questions_list[n]
        _options.value = optionsList[n]
    }

    fun getCount(): Int {
        return count
    }
//hardcoded questionis
    private fun prepareQuestions() {
        questions_list = listOf(
            "1. Who is the best cricketer in the world?",
            "2. What are the colors in the Indian national flag?"
        )
    }
 //hardcoded answer options
    private fun prepareOptions() {
        optionsList = mutableListOf(
            listOf(
                "Sachin Tendulkar",
                "Virat Kohli",
                "Adam Gilchrist",
                "Jackues Kalis"
            ),
            listOf(
                "White",
                "Yellow",
                "Orange",
                "Green"
            )
        )

    }

   //functioin to call repository to insert data to DB
    fun insertToDb(model: ModelClass) {
       //using coroutines to louch and insert the data to DB asysncronously
       //without blocking the UI
        viewModelScope.launch {
            try {
                repo.insertTrivia(model)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    //getting all the data from the DB in repository
    fun getAllFromDb() {
        try {
            dataFromDb  = repo.getNewsFromLocal()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}