package com.example.mytriviya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.mytriviya.model.ModelClass
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_preview.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PreviewFrag : Fragment() {
    private val viewModel by viewModel<TriviaViewModel>()
    lateinit var model: ModelClass

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       //converting the given string jsonobject to model to insert to db using entity class
        val gson = Gson()
         model  = gson.fromJson(
             PreviewFragArgs.fromBundle(requireArguments()).model,
             ModelClass::class.java
         )

        return inflater.inflate(R.layout.fragment_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //displying the data selscted by the user
      greet.text="Hello ${model.name}"
        try {
            question1.text = model.questions[0]
            question2.text = model.questions[1]
            answer1.text = model.answers[0]
            answer2.text = model.answers[1]
        }catch (e: Exception){
            e.printStackTrace()
        }

        button3.setOnClickListener {
            //inserting the model to DB using ROOM
            viewModel.insertToDb(model)
            val action = PreviewFragDirections.actionPreviewFragToIntroFragment()
            NavHostFragment.findNavController(this).navigate(action)

        }
    }
}