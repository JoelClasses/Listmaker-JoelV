package com.raywenderlich.listmaker

import androidx.lifecycle.ViewModel
// ViewModel for managing the details of a list.
class ListDetailViewModel() : ViewModel() {

    // Callback to notify when a task is added.
    lateinit var onTaskAdded: (() -> Unit)
    lateinit var list: TaskList

    // Add a task to the list.
    fun addTask(task: String) {
        list.tasks.add(task)
        onTaskAdded.invoke()
    }
}
