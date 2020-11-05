package com.example.mytriviya

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_intro.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class IntroFragment : Fragment() {
    private val viewModel by viewModel<TriviaViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_intro, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        button.setOnClickListener {
            //closing keyboard
            val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            //naviagting to second fragment by passing name entered by use as an argumnet
            val action =
                IntroFragmentDirections.actionIntroFragmentToQuizScreen2(nameField.text.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        //adding tool bar menu item
        inflater.inflate(R.menu.history_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.history -> {
                //Navigating to the next activity to show the History of the game
                NavHostFragment.findNavController(this).navigate(R.id.historyActivity)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}