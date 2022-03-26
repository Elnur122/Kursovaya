package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "db.db";
    private static final int DB_VERSION = 2;
    private static String DB_PATH = "";
    private final Context mContext;
    SQLiteDatabase mDataBase;
    DatabaseHelper DB;
    private boolean mNeedUpdate = false;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }

    public Boolean checkloginpass(String log, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Пользователь where Логин =? AND Пароль =?", new String[]{log, pass});
        return cursor.getCount() > 0;
    }

    public Boolean insertData(String login, String password, String im, String Poc, String np) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Почта", Poc);
        values.put("Имя", im);
        values.put("Номер_Телефона", np);
        values.put("Логин", login);
        values.put("Пароль", password);

        long result = db.insert("Пользователь", null, values);
        return result != 1;

    }

    public Boolean insertDataCar(String Marka, String Model, String Gos) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Марка", Marka);
        values.put("Модель", Model);
        values.put("Гос_Номер", Gos);

        long result = db.insert("Автомобиль", null, values);
        return result != 1;

    }

    public Boolean checkuser(String login) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Cursor cursor = db.rawQuery("Select * from Пользователь where Логин =?", new String[]{login});
        return cursor.getCount() > 0;
    }

    public Boolean checkuserandpass(String login, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Cursor cursor = db.rawQuery("Select * from Пользователь where Логин =? AND Пароль =?", new String[]{login, password});
        return cursor.getCount() > 0;
    }

    public Boolean UpdateDataUser(String login, String im, String Poc, String np) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Почта", Poc);
        values.put("Имя", im);
        values.put("Номер_Телефона", np);
        values.put("Логин", login);
        Cursor cursor = db.rawQuery("Select * from Пользователь where Логин =?", new String[]{login});
        if (cursor.getCount() > 0) {
            long result = db.update("Пользователь", values, "Логин =?", new String[]{login});
            return result != -1;
        }
        return true;
    }
}