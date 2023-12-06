package uz.boxodir.a4photos1wordgame.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import uz.boxodir.a4photos1wordgame.repositoriya.Repositoriya
import uz.boxodir.a4photos1wordgame.databinding.ActivityMainBinding


const val COUNTER_TIME = 10L
const val TAG = "TTTT"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var mCountDownTimer: CountDownTimer? = null
    private var mGameOver = false
    private var mGamePaused = false
    private var mIsLoading = false
    private var mTimeRemaining: Long = 0L

    private var clickItem: TextView? = null
    private var clickItemImage: ImageView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickItem = null
        clickItemImage = null

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)


        startGame()


        sharedPreferences = getSharedPreferences("SHARE_PREF", Context.MODE_PRIVATE)

        val coins_count = sharedPreferences.getInt("COIN_COUNT", 0)
        val last_que = sharedPreferences.getInt("LAST_QUE", 0)
        val repeated_count = sharedPreferences.getInt("REPEATED_COUNT", 0)
        val lastQueText = last_que + 1 + repeated_count * Repositoriya.getQuestions().size
        binding.lastQue.text = lastQueText.toString()

        binding.coinCount.text = coins_count.toString()
        val questions = Repositoriya.getQuestions()

        binding.imgOne.setImageResource(questions[last_que].images[0])
        binding.imgTwo.setImageResource(questions[last_que].images[1])
        binding.imgThree.setImageResource(questions[last_que].images[2])
        binding.imgFour.setImageResource(questions[last_que].images[3])



        binding.playBtn.setOnClickListener {
            if (clickItem == null) {
                clickItem = binding.playBtn
                binding.playBtn.animate()
                    .scaleX(0.7f)
                    .setDuration(200)
                    .scaleY(0.7f)
                    .withEndAction {
                        binding.playBtn.animate()
                            .setDuration(90)
                            .scaleY(1f)
                            .scaleX(1f)
                            .withEndAction {
                                clickItem = null
                                val intent = Intent(this, GameActivity::class.java)
                                intent.putExtra("LAST_QUE", last_que)
                                intent.putExtra("COIN_COUNT", coins_count)
                                intent.putExtra("REPEATED_COUNT", repeated_count)
                                startActivity(intent)
                            }
                            .start()
                    }
                    .start()
            }
        }
        funtionbuttons()
    }
    private fun funtionbuttons(){
        binding.infoIcon.setOnClickListener {
            if (clickItemImage == null) {
                clickItemImage = binding.infoIcon
                binding.infoIcon.animate()
                    .scaleX(0.7f)
                    .setDuration(200)
                    .scaleY(0.7f)
                    .withEndAction {
                        binding.infoIcon.animate()
                            .setDuration(90)
                            .scaleY(1f)
                            .scaleX(1f)
                            .withEndAction {
                                clickItemImage = null
                                val intent = Intent(this, InfoActivity::class.java)
                                startActivity(intent)
                            }.start()
                    }.start()
            }
        }

    }


    private fun alphaAnimationDownAdView(layoutt: View) {
        val animation1 = AlphaAnimation(1.0f, 0f)
        animation1.duration = 1000
        layoutt.startAnimation(animation1)
    }

    private fun alphaAnimationUp(layoutt: View) {
        val animation1 = AlphaAnimation(0.0f, 1f)
        animation1.duration = 400
        layoutt.startAnimation(animation1)


    }


    override fun onResume() {
        super.onResume()
        sharedPreferences = getSharedPreferences("SHARE_PREF", Context.MODE_PRIVATE)
        val coins_count = sharedPreferences.getInt("COIN_COUNT", 0)
        val last_que = sharedPreferences.getInt("LAST_QUE", 0)
        val repeated_count = sharedPreferences.getInt("REPEATED_COUNT", 0)

        binding.lastQue.text = "${last_que + 1 + repeated_count * Repositoriya.getQuestions().size}"
        binding.coinCount.text = coins_count.toString()
        val massiv = Repositoriya.getQuestions()

        binding.imgOne.setImageResource(massiv[last_que].images[0])
        binding.imgTwo.setImageResource(massiv[last_que].images[1])
        binding.imgThree.setImageResource(massiv[last_que].images[2])
        binding.imgFour.setImageResource(massiv[last_que].images[3])

    }

    private fun addCoins() {
        val currentCoin = sharedPreferences.getInt("COIN_COUNT", 0)
        addIntToSharePrefs("COIN_COUNT", currentCoin + 20)
    }

    private fun startGame() {

        createTimer(COUNTER_TIME)
        mGamePaused = false
        mGameOver = false
    }


    private fun createTimer(time: Long) {
        mCountDownTimer?.cancel()

        mCountDownTimer = object : CountDownTimer(time * 1000, 50) {
            override fun onTick(millisUnitFinished: Long) {
                mTimeRemaining = millisUnitFinished / 1000 + 1
            }

            override fun onFinish() {
                addCoins()
                mGameOver = true
                binding.coinCount.text = sharedPreferences.getInt("COIN_COUNT", 0).toString()
                binding.coinCountAdsView.text = sharedPreferences.getInt("COIN_COUNT", 0).toString()
            }
        }

        mCountDownTimer?.start()
    }

    private fun addIntToSharePrefs(name: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(name, value)
        editor.apply()
    }
}

