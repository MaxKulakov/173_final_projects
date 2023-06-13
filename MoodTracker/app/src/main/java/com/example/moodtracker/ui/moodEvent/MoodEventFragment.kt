package com.example.moodtracker.ui.moodEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.moodtracker.Item
import com.example.moodtracker.MainDB
import com.example.moodtracker.databinding.FragmentMoodEventBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MoodEventFragment : Fragment() {

    private var _binding: FragmentMoodEventBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val dashboardViewModel =
            ViewModelProvider(this).get(MoodEventViewModel::class.java)

        _binding = FragmentMoodEventBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSettings
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // not hehe

        binding.addActivityBtn.setOnClickListener {
            var describe = binding.textInput1.text.toString()
            if (describe == "") describe = "All ok!"
            val mood = when (binding.radioGroup.checkedRadioButtonId) {
                2131231072 -> 2
                2131231071 -> 3
                2131231070 -> 1
                2131231069 -> 4
                2131231068 -> 5
                else -> 0
            }
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val dateCurrent = LocalDateTime.now().format(dateFormatter)


            val item = Item(null, mood, describe, dateCurrent)

            val db = MainDB.getDB(requireActivity())
            Thread {
                db.getDao().insertItem(item)
            }.start()

            println("Mood: $mood, describe: $describe, date: $dateCurrent")
        }

        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}