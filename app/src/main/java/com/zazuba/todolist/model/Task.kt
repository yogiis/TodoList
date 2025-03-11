package com.zazuba.todolist.model

data class Task(
    val id: String,
    val title: String,
    val description: String ? = null,
)