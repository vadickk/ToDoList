package com.dikabateingpps.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoDataItem::class], version = 2)
abstract class TodoAppDatabase: RoomDatabase() {

    abstract val dao: TodoDao
}