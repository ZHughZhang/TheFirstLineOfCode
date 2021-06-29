package com.zlq.sqldemo.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 *   @Auther:  asad
 *   @Date:  2021/06/28 13:36
 *   @Description:  TODO
 *   @ClassName:  com.zlq.sqldemo.utils
 *   @PackageName:  MyDatabaseHelper
 *   @Version:  1.0
 *   @ProgectName:  TheFirstLineOfCode
 */
class MyDatabaseHelper(val context: Context,name:String,version:Int) : SQLiteOpenHelper(context,name,null,version){
    private val TAG: String = javaClass.canonicalName
    private val cteateBooks = """
       create table Books(
       id integer primary key autoincrement,
       author text,
       price real,
       pages integer,
       name text
       )
   """
    private val createCategory = """
       create table Category(
       id integer primary key autoincrement,
       category_name text,
       category_code integer
       ) 
    """
   
   
   
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(cteateBooks)
        db?.execSQL(createCategory)
        Log.e(TAG, "onCreate: Create Successed " )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        //版本没升级一次 这里的逻辑就要添加一次，防止越级升级时 错过某些升级设置
      if (oldVersion <=1){
          db?.execSQL(createCategory)
      }
        if (oldVersion <= 2) {
            db?.execSQL("alter table Books add column category_id integer")
        }

//       db?.let {
//           it.execSQL("drop table if exists Books")
//           it.execSQL("drop table if exists Category")
//       }
//        onCreate(db)
    }
}