package com.zlq.sqldemo

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.zlq.sqldemo.utils.MyDatabaseHelper
import java.lang.Exception
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {
    private val TAG: String = javaClass.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbhelper = MyDatabaseHelper(this,"BooksStore.db",3)

        val db  = dbhelper.writableDatabase


        findViewById<TextView>(R.id.button).setOnClickListener {
            dbhelper.writableDatabase
        }
        findViewById<Button>(R.id.add_data).setOnClickListener {
            val db  = dbhelper.writableDatabase
            val values = ContentValues().apply {
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 19.95)

            }
            db.insert("Books",null,values)
            val values2 = ContentValues().apply {
                put("name", "The Lost Sysmbol")
                put("author","Dan Brown")
                put("pages",510)
                put("price",19.95)
            }
            db.insert("Books",null,values2)
        }
        findViewById<Button>(R.id.update_data).setOnClickListener {
            val db  = dbhelper.writableDatabase
            val values = ContentValues().apply {

                put("price", 45)

            }
            db.update("Books",values,"name =?", arrayOf("The Da Vinci Code"))
        }
        findViewById<Button>(R.id.del_data).setOnClickListener {
            val db  = dbhelper.writableDatabase

            db.delete("Books","pages > ?", arrayOf("500"))
        }
        findViewById<Button>(R.id.query_data).setOnClickListener {
            val db  = dbhelper.writableDatabase
            val cursor = db.query("Books",null,null,null,null,null,null)
            if (cursor.moveToFirst()) {
                do {

                    Log.e(TAG, "onCreate:${cursor.getString(cursor.getColumnIndex("name"))} ")
                    Log.e(TAG, "onCreate: ${cursor.getString(cursor.getColumnIndex("author"))}")
                    Log.e(TAG, "onCreate: ${cursor.getInt(cursor.getColumnIndex("pages"))}")
                    Log.e(TAG, "onCreate:${cursor.getDouble(cursor.getColumnIndex("price"))} ")
                    Log.e(TAG, "onCreate: ")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        findViewById<Button>(R.id.replace_data).setOnClickListener {
            val db = dbhelper.writableDatabase
            db.beginTransaction() //开启事务
            try {
                db.delete("Books", null, null)
                val values = ContentValues().apply {
                    put("name", "第一行代码")
                    put("author", "郭霖")
                    put("pages", 500)
                    put("price", 90.00)
                }
                db.insert("Books", null, values)
                db.setTransactionSuccessful()//事物已经执行成功
            } catch (e: Exception) {
                e.printStackTrace()
            }finally {
                db.endTransaction()
            }
        }


    }

    fun SQLGrammar(db:SQLiteDatabase){
        //        增
        db.execSQL("insert into Books(name,author,pages,price) values(?,?,?,?)", arrayOf("第一行代码","郭霖","500","51.98"))
        db.execSQL("insert into Books(name,author,pages,price) values(?,?,?,?)", arrayOf("Android 进阶之光","刘望舒","600","79"))
//        改
        db.execSQL("update Books set price = ? where name = ?", arrayOf("100","第一行代码"))
//        删
        db.execSQL("delete from Books where pages > ?", arrayOf("500"))
//        查
        db.rawQuery("select * from Books",null)
    }
}