package com.example.myapplication.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UsersTable.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
}
