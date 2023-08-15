package com.example.dz1_6m


import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dz1_6m.databinding.ActivityMainBinding
import java.security.acl.Owner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    companion object {
        private const val REQUEST_CODE_ADD_TASK = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskAdapter = TaskAdapter(emptyList(), this::onLongClick) { position, isChecked ->
            viewModel.markTaskAsDone(position)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }

        viewModel.taskList.observe(this) { taskList ->
            if (taskList != null) {
                taskAdapter.taskList = taskList
            }
            binding.recyclerView.post {
                taskAdapter.notifyDataSetChanged()
            }
        }

        binding.btnNewPlus.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_TASK)
        }
    }

    private fun onLongClick(task: Task){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Вы точно хотите удалить ?")
        alertDialog.setNegativeButton("Нет"
        ) { dialog, _ -> dialog?.cancel() }

        alertDialog.setPositiveButton("Да"
        ) { _, _ -> viewModel.deleteTask(0) }
        alertDialog.create().show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_TASK && resultCode == Activity.RESULT_OK) {
            val taskTitle = data?.getStringExtra("title")
            if (!taskTitle.isNullOrEmpty()) {
                viewModel.addTask(taskTitle)
            }
        }
    }

}



