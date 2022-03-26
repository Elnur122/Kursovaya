package com.example.myapplication.DataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface UserDAO {

    @Insert
    void insertData(UsersTable usersTable);

    @Query("SELECT * FROM userstable")
    List<UsersTable> selectAll();


}
