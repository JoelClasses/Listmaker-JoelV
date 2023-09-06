package com.raywenderlich.listmaker

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class MainViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {

    // Callback to notify when a new list is added
    lateinit var onListAdded: (() -> Unit)

    // Current list being managed
    lateinit var list: TaskList
    // Callback to notify when a new task is added to the list
    lateinit var onTaskAdded: (() -> Unit)

    // Add a new task to the current list
    fun addTask(task: String) {
        list.tasks.add(task)
        onTaskAdded.invoke()
    }

    // Retrieve all the task lists from SharedPreferences
    val lists: MutableList<TaskList> by lazy {
        retrieveLists()
    }

    private fun retrieveLists(): MutableList<TaskList> {
        val sharedPreferencesContents = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()
        for (taskList in sharedPreferencesContents) {
            val itemsHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemsHashSet)
            taskLists.add(list)
        }
        return taskLists
    }

    // Update an existing list in SharedPreferences
    fun updateList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.name, list.tasks.toHashSet()).apply()
        lists.add(list)
    }

    // Refresh the list of task lists from SharedPreferences
    fun refreshLists() {
        lists.clear()
        lists.addAll(retrieveLists())
    }

    // Save a new task list to SharedPreferences
    fun saveList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.name, list.tasks.toHashSet()).apply()
        lists.add(list)
        onListAdded.invoke()
    }
}
