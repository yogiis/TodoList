package com.zazuba.todolist.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.zazuba.todolist.model.Task

class ToDoAppViewModel: ViewModel(){
    private  val _tasks = mutableStateListOf(
        Task("1", "Task 1"),
        Task("2", "Task Rumah", "Mengerjakan pekerjaan rumah"),
        Task("3", "Task Tugas", "Mengerjakan tugas"),
    )

    val tasks: List<Task> get() = _tasks

    fun addTask(task: Task){
        _tasks.add(task)
    }

    fun deleteTask(taskId: String?){
        _tasks.removeAll{it.id == taskId}
    }

    fun getTaskById(taskId: String?): Task?{
        return _tasks.find { it.id == taskId }
    }

}