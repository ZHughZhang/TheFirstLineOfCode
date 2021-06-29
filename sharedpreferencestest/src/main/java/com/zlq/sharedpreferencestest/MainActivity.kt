package com.zlq.sharedpreferencestest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val TAG: String = javaClass.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            editor.putString("name", "法外狂徒张三")
            editor.putInt("age", 23)
            editor.putBoolean("married", false)
            editor.apply()
        }
        val perfs = getSharedPreferences("data",Context.MODE_PRIVATE)

        if (perfs.getBoolean("remember-mine",false)){
            findViewById<EditText>(R.id.nick_input).setText(perfs.getString("nick",""))
            findViewById<EditText>(R.id.password_input).setText(perfs.getString("password",""))
            findViewById<CheckBox>(R.id.remember_mine).isChecked = true;
        }

        val readBottom = findViewById<Button>(R.id.button_reader)
        readBottom.setOnClickListener {
            Log.e(TAG, "onCreate:name--> ${perfs.getString("name", "")}")
            Log.e(TAG, "onCreate:age--> ${perfs.getInt("age", 0)}")
            Log.e(TAG, "onCreate: married-->${perfs.getBoolean("married", false)}")
        }

        findViewById<Button>(R.id.login).setOnClickListener {
            val editor = perfs.edit();
            val nick = findViewById<EditText>(R.id.nick_input).text.toString();
            val password = findViewById<EditText>(R.id.password_input).text.toString()
            if ((nick == "admin" && password == "123456")) {
                if (findViewById<CheckBox>(R.id.remember_mine).isChecked) {
                    editor.putString("nick", nick)
                    editor.putString("password",password)
                    editor.putBoolean("remember-mine",true);
                }else{
                    editor.clear()
                }
                editor.apply()
                startActivity(Intent(MainActivity@this,MainActivity::class.java))
                finish()
            }else{
                Snackbar.make(it,"account or password is invalid",Snackbar.LENGTH_SHORT).show()
            }

        }





    }


    fun SharedPreferences.open(block: SharedPreferences.Editor.()->Unit){
        val editor = edit()
        editor.block()
        editor.apply()
    }
}