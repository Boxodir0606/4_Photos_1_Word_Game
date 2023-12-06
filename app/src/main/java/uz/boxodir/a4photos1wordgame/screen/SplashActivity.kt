package uz.boxodir.a4photos1wordgame.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import uz.boxodir.a4photos1wordgame.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)



        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
//            textAnimation()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

//    private fun textAnimation(){
//        val a = AnimationUtils.loadAnimation(this,R.anim.text_animation)
//
//        binding.image.clearAnimation()
//        binding.image.startAnimation(a)
//
//    }

}