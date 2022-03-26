package com.example.myapplication.DataBase;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    static DatabaseClient client;
    Context context;
    UsersDatabase usersDatabase;

    public DatabaseClient(Context context) {
        this.context = context;

        usersDatabase = Room.databaseBuilder(context, UsersDatabase.class, "nedb.db").build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (client == null) {
            client = new DatabaseClient(context);
        }
        return client;
    }

    public UsersDatabase getUsersDatabase() {
        return usersDatabase;
    }
}
