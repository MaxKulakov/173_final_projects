package com.example.moodtracker.ui.moodEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
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

        val rg = RadioGroup(requireActivity())
        rg.orientation = RadioGroup.HORIZONTAL
        val options = arrayOf("1", "2", "3", "4", "5")
        for (i in options.indices) {
            val rb = RadioButton(requireActivity())
            rb.text = options[i]
            rb.id = View.generateViewId()
            rg.addView(rb)
        }
        binding.llMainLayout.addView(rg)


        binding.addActivityBtn.setOnClickListener {
            var describe = binding.textInput1.text.toString()
            if (describe == "") describe = "All ok!"
            println(binding.llMainLayout)
            val mood = rg.checkedRadioButtonId
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