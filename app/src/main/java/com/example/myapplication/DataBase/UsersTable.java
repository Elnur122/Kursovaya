package com.example.myapplication.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class UsersTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Имя")
    private String Имя;

    @ColumnInfo(name = "Номер_Телефона")
    private String Номер_Телефона;

    @ColumnInfo(name = "Почта")
    private String Почта;

    @ColumnInfo(name = "Логин")
    private String Логин;

    @ColumnInfo(name = "Пароль")
    private String Пароль;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getИмя() {
        return Имя;
    }

    public void setИмя(String имя) {
        Имя = имя;
    }

    public String getНомер_Телефона() {
        return Номер_Телефона;
    }

    public void setНомер_Телефона(String номер_Телефона) {
        Номер_Телефона = номер_Телефона;
    }

    public String getПочта() {
        return Почта;
    }

    public void setПочта(String почта) {
        Почта = почта;
    }

    public String getЛогин() {
        return Логин;
    }

    public void setЛогин(String логин) {
        Логин = логин;
    }

    public String getПароль() {
        return Пароль;
    }

    public void setПароль(String пароль) {
        Пароль = пароль;
    }

    @Override
    public String toString() {
        return "UsersTable{" +
                "id=" + id +
                ", Имя='" + Имя + '\'' +
                ", Номер_Телефона='" + Номер_Телефона + '\'' +
                ", Почта='" + Почта + '\'' +
                ", Логин='" + Логин + '\'' +
                ", Пароль='" + Пароль + '\'' +
                '}';
    }
}
