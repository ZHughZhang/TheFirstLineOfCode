package com.zlq.sqldemo

import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import com.zlq.sqldemo.utils.build
import kotlinx.android.synthetic.main.activity_main.*

/**
 *   @Auther:  asad
 *   @Date:  2021/06/30 16:10
 *   @Description:  TODO
 *   @ClassName:  com.zlq.sqldemo
 *   @PackageName:  ProviderContentActivity
 *   @Version:  1.0
 *   @ProgectName:  TheFirstLineOfCode
 */
class ProviderContentActivity: AppCompatActivity() {


    private val TAG = javaClass.canonicalName
    var bookId:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_content)

        add_data.setOnClickListener {
            val uri = Uri.parse("content://com.zlq.sqldemo.app.provider/book")
            val values = contentValuesOf("name" to "A Clash of King",
                "author" to "George Marthin","pages" to  1024,"price" to 22.85
                )
            val newUri = contentResolver.insert(uri,values)
            Log.e(TAG, "onCreate: ${newUri?.pathSegments?.get(1)}" )
            bookId  = newUri?.pathSegments?.get(1)

        }
        query_data.setOnClickListener {
            val uri = Uri.parse("content://com.zlq.sqldemo.app.provider/book")
            contentResolver.query(uri,null,null,null,null)?.build {
                while (moveToNext()){
                    val name = getString(getColumnIndex("name"))
                    val author = getString(getColumnIndex("author"))
                    val pages = getInt(getColumnIndex("pages"))
                    val price = getFloat(getColumnIndex("price"))
                    Log.e(TAG, "name:-->$name--author:-->$author--pages:-->$pages--price:-->$price")
                }
                close()
            }
        }
        update_data.setOnClickListener {
            bookId?.let {
                val uri = Uri.parse("content://com.zlq.sqldemo.app.provider/book/$it")
                val values = contentValuesOf(
                    "name" to "A Storm od Swords",
                    "pages" to 2048,
                    "price" to 300.00

                )
                contentResolver.update(uri,values,null,null)

            }
        }
        del_data.setOnClickListener {
            bookId?.let {
                val uri = Uri.parse("content://com.zlq.sqldemo.app.provider/book/$it")
                contentResolver.delete(uri,null,null)
            }
        }
    }

}