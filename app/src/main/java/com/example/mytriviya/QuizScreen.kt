package com.example.mytriviya


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_quiz_screen.*
import org.json.JSONArray
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class QuizScreen : Fragment() {

    //Injecting shared  viewmodel by Koin dependecy injection framework
    private val viewModel by viewModel<TriviaViewModel>()
    private lateinit var jsonObject: JSONObject
    private lateinit var jsonArray: JSONArray
    private lateinit var answerArray: JSONArray
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        jsonObject = JSONObject()
        jsonArray = JSONArray()
        answerArray = JSONArray()
        return inflater.inflate(R.layout.fragment_quiz_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jsonObject.put("name", QuizScreenArgs.fromBundle(requireArguments()).name)
        viewModel.getQuestion(0)
        //observing to questions from the view model
        viewModel.question.observe(viewLifecycleOwner, {
            textView2.text = it
            jsonArray.put(it)

        })
        viewModel.options.observe(viewLifecycleOwner, {
            initListView(it)
        })


    }



    private fun initListView(it: List<String>) {
        val adapter = context?.let { it1 -> ArrayAdapter(it1, R.layout.list_item_quiz, it) }

        listView.adapter = adapter
        listView.onItemClickListener = OnItemClickListener { _, _, position, _ -> // selected item
            val selected = it[position]
            answerArray.put(selected)
            if (viewModel.getCount() != 1) {
                viewModel.getQuestion(1)
            } else {

                    prepareModel()

            }
        }


    }

//sending jsonobject as string to next screen so that we can add to db when click on finish in the next screen

    private fun prepareModel() {
        jsonObject.put("questions", jsonArray)
        jsonObject.put("answers", answerArray)

         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             val dateTime=  getCurrentDateAndTime()
            jsonObject.put("time",dateTime)
        } else {
            jsonObject.put("time","")
        }
        val action = QuizScreenDirections.actionQuizScreen2ToPreviewFrag(jsonObject.toString())
        NavHostFragment.findNavController(this).navigate(action)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentDateAndTime() :String{
        try {
            val currentDateTime = LocalDateTime.now()
            return currentDateTime.format(
                DateTimeFormatter.ofLocalizedDateTime(
                    FormatStyle.MEDIUM,
                    FormatStyle.SHORT
                )
            )
        }catch (e:Exception){
            e.printStackTrace()
        }
  return ""
    }
}