package com.example.myapp16cloud.data

data class Task(
    var id: String = "",
    var name: String = "",
    var isCompleted: Boolean = false,
    var assignedTo: String = ""
)