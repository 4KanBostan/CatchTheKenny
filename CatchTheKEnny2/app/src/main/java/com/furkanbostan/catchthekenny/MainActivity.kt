package com.furkanbostan.catchthekenny

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.furkanbostan.catchthekenny.databinding.ActivityMainBinding
import java.util.*

class MainActivity() : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var score=0
    var imageArray= ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        imageArray.add(binding.imageView10)
        imageArray.add(binding.imageView11)
        imageArray.add(binding.imageView12)

        hideImages()

        object : CountDownTimer(15000, 1000){
            override fun onTick(p0: Long) {
                binding.timeText.text="Time:"+p0/1000
            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility= View.INVISIBLE
                }
                binding.timeText.text="Time:0"
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes") {dialog,which->
                    val intent = intent
                    finish()
                    startActivity(intent)

                }
                alert.setNegativeButton("No"){dialog,which->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                }
                alert.show()
            }

        }.start()

    }

    fun increaseScore(view : View) {
        score=score+1
        binding.scoreText.text= "Score: "+score.toString()

    }
    fun hideImages(){
        runnable = object :Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility= View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(12)
                imageArray[randomIndex].visibility= View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)

    }

}