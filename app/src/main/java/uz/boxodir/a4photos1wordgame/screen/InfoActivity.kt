package uz.boxodir.a4photos1wordgame.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import uz.boxodir.a4photos1wordgame.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private var clickItem: ImageView? = null
    private lateinit var binding: ActivityInfoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickItem = null


        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        funtionbuttons()

    }

    private fun funtionbuttons() {
        binding.backMenu.setOnClickListener {
            if (clickItem == null) {
                clickItem = binding.backMenu
                binding.backMenu.animate()
                    .scaleX(0.7f)
                    .setDuration(200)
                    .scaleY(0.7f)
                    .withEndAction {
                        binding.backMenu.animate()
                            .setDuration(90)
                            .scaleY(1f)
                            .scaleX(1f)
                            .withEndAction {
                                clickItem = null
                                finish()
                            }.start()
                    }.start()
            }
        }

    }

}
