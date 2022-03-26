package com.example.myapplication.DataBase;

import android.content.Context;
import android.os.AsyncTask;

public class DatabaseHelper2 {

    Context context;

    public DatabaseHelper2(Context context) {
        this.context = context;
    }

    public static DatabaseHelper2 getInstance(Context context) {
        return new DatabaseHelper2(context);
    }

    public void addNewUser(String Имя, String Номер_Телефона, String Почта, String Логин, String Пароль) {
        class NewUser extends AsyncTask<Void, Void, UsersTable> {

            @Override
            protected UsersTable doInBackground(Void... voids) {

                UsersTable usersTable = new UsersTable();
                usersTable.setИмя(Имя);
                usersTable.setНомер_Телефона(Номер_Телефона);
                usersTable.setПочта(Почта);
                usersTable.setЛогин(Логин);
                usersTable.setПароль(Пароль);

                DatabaseClient.getInstance(context)
                        .getUsersDatabase()
                        .userDAO()
                        .insertData(usersTable);
                return usersTable;
            }

            @Override
            protected void onPostExecute(UsersTable usersTable) {
                super.onPostExecute(usersTable);
                if (usersTable != null) {
                    int a = 0;
                }
            }
        }
        NewUser newUser = new NewUser();
        newUser.execute();
    }
}
