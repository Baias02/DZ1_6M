package com.example.dz1_6m


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dz1_6m.databinding.ItemTaskBinding

class TaskAdapter(
    var taskList: List<Task>,
    var onLongClick:(Task) -> Unit,
    private val onTaskCheckChanged: (position: Int, isChecked: Boolean) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.onBind(task,onTaskCheckChanged)

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskViewHolder(var binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(task: Task,onTaskCheckChanged: (position: Int, isChecked: Boolean) -> Unit) {
            binding.titleTextView.text = task.title

            binding.checkBox.setOnCheckedChangeListener(null)
            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                onTaskCheckChanged(adapterPosition, isChecked)
            }
            itemView.setOnLongClickListener {
                onLongClick(task)
                false
            }
        }
    }
}
