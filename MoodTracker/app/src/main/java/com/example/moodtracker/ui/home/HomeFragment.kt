package com.example.moodtracker.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtracker.databinding.FragmentHomeBinding
import android.widget.Button
import androidx.lifecycle.asLiveData
import com.example.moodtracker.MainDB

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

//        homeButton.setOnClickListener { view ->
//            Log.d("btnSetup", "Selected")
//        }

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // hehe
        binding.homeButton.setOnClickListener {
            println("hehe")
        }

//        binding.textMoodList.text = "Mood: 5, describe: All ok!, date: 2023-06-13 21:04"

        val db = MainDB.getDB(requireActivity())
        db.getDao().getAllItems().asLiveData().observe(this){list ->
            binding.textMoodList.text = ""
            list.forEach {
                val item = "Mood: ${it.mood}, describe: ${it.describe}, date: ${it.date}\n"
                binding.textMoodList.append(item)
            }
        }


        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



//    fun buttonClickViewEvents(view: View?){
//        println("hi")
//    }
}