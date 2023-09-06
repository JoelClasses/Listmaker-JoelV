package com.raywenderlich.listmaker

import android.os.Parcel
import android.os.Parcelable

class TaskList(val name: String, val tasks: ArrayList<String> = ArrayList()) : Parcelable {

        // Constructor for creating a TaskList from a Parcel.
        constructor(source: Parcel) : this(
                source.readString()!!,
                source.createStringArrayList()!!
        )

        // Describe the kinds of special objects contained in this Parcelable's marshalled representation.
        override fun describeContents() = 0

        // Flatten this object into a Parcel.
        override fun writeToParcel(dest: Parcel, flags: Int) {
                dest.writeString(name)
                dest.writeStringList(tasks)
        }

        companion object CREATOR: Parcelable.Creator<TaskList> {
                // Create a new instance of the Parcelable class from a Parcel.
                override fun createFromParcel(source: Parcel): TaskList = TaskList(source)
                // Create a new array of the Parcelable class.
                override fun newArray(size: Int): Array<TaskList?> = arrayOfNulls(size)
        }
}
