package com.zlq.filepersistencetest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.zlq.filepersistencetest.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    lateinit var  viewBinding:ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val reader  = load()
        if (reader.isNotEmpty()) {
            viewBinding.editText.setText(reader)
            viewBinding.editText.setSelection(reader.length)
            Snackbar.make(viewBinding.root,reader,Snackbar.LENGTH_SHORT).show()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        val inputtext = viewBinding.editText.text.toString();
        save(inputtext);
    }

    private fun save(inputtext: String) {
//        文件存储数据之保存
        val output = openFileOutput("data", Context.MODE_PRIVATE);
        val write = BufferedWriter(OutputStreamWriter(output));
        write.use {
            it.write(inputtext)
        }
    }
//读取文件
    private fun load(): String {
        val content = StringBuffer()
        val input = openFileInput("data")
        val reader = BufferedReader(InputStreamReader(input))
        reader.use {
            reader.forEachLine {
                content.append(it)
            }
        }
        return content.toString()
    }

}