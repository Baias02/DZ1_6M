package com.example.dz1_6m

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dz1_6m.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            if (title.isNotEmpty()) {
                val result = Intent()
                result.putExtra("title", title)
                setResult(Activity.RESULT_OK, result)
                finish()
            }
        }
    }
}



