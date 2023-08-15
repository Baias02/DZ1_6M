package com.example.dz1_6m

import java.io.Serializable

data class Task(
    val title: String,
    var isDone: Boolean = false){

    fun toggleDone(){
        isDone = !isDone
    }
}

