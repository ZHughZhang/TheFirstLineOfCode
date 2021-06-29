package com.zlq.partone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.View.inflate
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.zlq.partone.databinding.ActivityPartOneMainBinding
import kotlinx.android.synthetic.main.activity_part_one_main.*

class PartOneMainActivity : AppCompatActivity() {
    var activityPartOneMainBinding : ActivityPartOneMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPartOneMainBinding =  ActivityPartOneMainBinding.inflate(getLayoutInflater());
        setContentView(activityPartOneMainBinding?.root)
        hello_world.setOnClickListener{
            activityPartOneMainBinding?.root?.let { it1 -> Snackbar.make(it1,"点击了 hello-world",Snackbar.LENGTH_LONG).show() }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item->{
                Snackbar.make(activityPartOneMainBinding!!.root,"点击了 add-item",Snackbar.LENGTH_LONG).show()
            }
            R.id.remove_item->{
                Snackbar.make(activityPartOneMainBinding!!.root,"点击了 remove-item",Snackbar.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}