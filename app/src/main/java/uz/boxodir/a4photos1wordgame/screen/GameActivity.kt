package uz.boxodir.a4photos1wordgame.screen

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.*
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import androidx.core.view.isVisible
import uz.boxodir.a4photos1wordgame.R
import uz.boxodir.a4photos1wordgame.data.QuestionData
import uz.boxodir.a4photos1wordgame.repositoriya.Repositoriya
import uz.boxodir.a4photos1wordgame.databinding.ActivityGameBinding

import kotlin.random.Random


class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val questions = Repositoriya.getQuestions()
    private lateinit var currentQuestion: QuestionData
    private var index = -1
    private var repeatedCount = 0
    private lateinit var sharedPreferences: SharedPreferences
    private var answersList = mutableListOf<TextView>()
    private var optionList = mutableListOf<TextView>()
    private var userAnswer = mutableListOf<Pair<String, TextView>>()
    private var coins_count = 0
    private var last_que: Int = 0
    private var isSoundShaprefpreferense: Boolean = true
    private var clickItem: ImageView? = null
    private var clickItemText: TextView? = null

    private var click_variyant: MediaPlayer? = null
    private var truemedi: MediaPlayer? = null
    private var isSound = true


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickItem = null
        clickItemText = null
        sharedPreferences = getSharedPreferences("SHARE_PREF", MODE_PRIVATE)


        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        mediaInit()
        isSoundIcon()

        isSound = sharedPreferences.getBoolean("sound",true)

        if (questions.isNotEmpty()) {
            last_que = sharedPreferences.getInt("LAST_QUE", 0)
            coins_count = sharedPreferences.getInt("COIN_COUNT", 0)
            repeatedCount = sharedPreferences.getInt("REPEATED_COUNT", 0)
            isSoundShaprefpreferense = sharedPreferences.getBoolean("sound",true)
            index = last_que
            currentQuestion = questions[index]
        }
        binding.tvLevelNumber.text = "${currentQuestion.id + repeatedCount * questions.size}"
        binding.coinCount.text = coins_count.toString()

        binding.clearBtn.setOnClickListener {
            if (clickItem == null) {
                clickItem = binding.clearBtn
                binding.clearBtn.animate()
                    .scaleX(0.7f)
                    .setDuration(200)
                    .scaleY(0.7f)
                    .withEndAction {
                        clickItem = null
                        binding.clearBtn.animate()
                            .setDuration(90)
                            .scaleY(1f)
                            .scaleX(1f)
                            .withEndAction {
                                removeAllLetters()
                            }
                            .start()
                    }
                    .start()
            }
        }



        binding.apply {
            btnBack.setOnClickListener {
                if (clickItem == null) {
                    clickItem = binding.btnBack
                    binding.btnBack.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.btnBack.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItem = null
                                    finish()
                                }
                                .start()
                        }
                        .start()
                }
            }
            fillAnswersList()
            fillOptionsList()




            tvOption1.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvOption1
                    binding.tvOption1.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvOption1.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    setLetter(tvOption1)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvOption2.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvOption2
                    binding.tvOption2.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvOption2.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    setLetter(tvOption2)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvOption3.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvOption3
                    binding.tvOption3.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvOption3.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    setLetter(tvOption3)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvOption4.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvOption4
                    binding.tvOption4.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvOption4.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    setLetter(tvOption4)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvOption5.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvOption5
                    binding.tvOption5.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvOption5.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    setLetter(tvOption5)
                                }
                                .start()
                        }
                        .start()
                }
            }


            tvOption6.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvOption6
                    binding.tvOption6.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvOption6.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    setLetter(tvOption6)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvOption7.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvOption7
                    binding.tvOption7.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvOption7.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    setLetter(tvOption7)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvOption8.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvOption8
                    binding.tvOption8.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvOption8.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    setLetter(tvOption8)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvOption9.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvOption9
                    binding.tvOption9.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvOption9.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    setLetter(tvOption9)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvOption10.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvOption10
                    binding.tvOption10.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvOption10.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    setLetter(tvOption10)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvOption11.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvOption11
                    binding.tvOption11.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvOption11.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    setLetter(tvOption11)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvOption12.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvOption12
                    binding.tvOption12.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvOption12.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    setLetter(tvOption12)
                                }
                                .start()
                        }
                        .start()
                }
            }


            tvAnswer1.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvAnswer1
                    binding.tvAnswer1.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvAnswer1.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    removeLetter(tvAnswer1)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvAnswer2.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvAnswer2
                    binding.tvAnswer2.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvAnswer2.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    removeLetter(tvAnswer2)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvAnswer3.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvAnswer3
                    binding.tvAnswer3.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvAnswer3.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    removeLetter(tvAnswer3)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvAnswer4.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvAnswer4
                    binding.tvAnswer4.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvAnswer4.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    removeLetter(tvAnswer4)
                                    clickItemText = null
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvAnswer5.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvAnswer5
                    binding.tvAnswer5.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvAnswer5.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    removeLetter(tvAnswer5)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvAnswer6.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvAnswer6
                    binding.tvAnswer6.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvAnswer6.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    removeLetter(tvAnswer6)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvAnswer7.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvAnswer7
                    binding.tvAnswer7.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvAnswer7.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    removeLetter(tvAnswer7)
                                }
                                .start()
                        }
                        .start()
                }
            }

            tvAnswer8.setOnClickListener {
                if (clickItemText == null) {
                    clickItemText = binding.tvAnswer8
                    binding.tvAnswer8.animate()
                        .scaleX(0.7f)
                        .setDuration(200)
                        .scaleY(0.7f)
                        .withEndAction {
                            binding.tvAnswer8.animate()
                                .setDuration(90)
                                .scaleY(1f)
                                .scaleX(1f)
                                .withEndAction {
                                    clickItemText = null
                                    removeLetter(tvAnswer8)
                                }
                                .start()
                        }
                        .start()
                }
            }

            setQuestion()
        }

    }

    private fun mediaInit() {
        click_variyant = MediaPlayer.create(this, R.raw.click_variyant)
        truemedi = MediaPlayer.create(this, R.raw.win_sound)
    }

    @SuppressLint("CommitPrefEdits")
    fun isSoundIcon() {
        binding.sound.setOnClickListener {
            if (clickItem == null) {
                clickItem = binding.sound
                binding.sound.animate()
                    .scaleX(0.7f)
                    .setDuration(200)
                    .scaleY(0.7f)
                    .withEndAction {
                        binding.sound.animate()
                            .setDuration(90)
                            .scaleY(1f)
                            .scaleX(1f)
                            .withEndAction {
                                Log.d("TTT", "$isSoundShaprefpreferense")
                                if (isSound) {
                                    binding.sound.setImageResource(R.drawable.nosound)
                                    sharedPreferences.edit().putBoolean("sound",isSound)
                                    Log.d("TTT", "$isSoundShaprefpreferense")
                                    isSound = false
                                    clickItem = null
                                } else {
                                    Log.d("TTT", "$isSoundShaprefpreferense")
                                    binding.sound.setImageResource(R.drawable.sound)
                                    sharedPreferences.edit().putBoolean("sound",isSound)
                                    Log.d("TTT", "$isSoundShaprefpreferense")
                                    isSound = true
                                    clickItem = null
                                }
                            }
                            .start()
                    }.start()
            }
        }
    }

    fun click_variyant() {
        if (isSound) {
            click_variyant?.start()
        } else {
            click_variyant?.pause()
        }
    }

    fun trueanswerSound() {
        if (isSound) {
            truemedi?.start()
        } else {
            truemedi?.pause()
        }
    }


    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        binding.apply {
            tvLevelNumber.text = "${index + 1 + repeatedCount * questions.size}"

            ivOne.setImageResource(currentQuestion.images[0])
            ivTwo.setImageResource(currentQuestion.images[1])
            ivThree.setImageResource(currentQuestion.images[2])
            ivFour.setImageResource(currentQuestion.images[3])

            repeat(8) {
                answersList[it].text = ""
                answersList[it].visibility = View.GONE
            }


            repeat(currentQuestion.answer.length) {
                answersList[it].isVisible = true
            }

            setOptionLetters()
        }
    }


    private fun setOptionLetters() {
        binding.apply {
            val optionLetters = mutableListOf<Char>()
            optionLetters.addAll(currentQuestion.answer.toList())

            repeat(12 - optionLetters.size) {
                optionLetters.add(Random.nextInt(65, 90).toChar())
            }
            optionLetters.shuffle()

            tvOption1.text = optionLetters[0].toString()
            tvOption2.text = optionLetters[1].toString()
            tvOption3.text = optionLetters[2].toString()
            tvOption4.text = optionLetters[3].toString()
            tvOption5.text = optionLetters[4].toString()
            tvOption6.text = optionLetters[5].toString()
            tvOption7.text = optionLetters[6].toString()
            tvOption8.text = optionLetters[7].toString()
            tvOption9.text = optionLetters[8].toString()
            tvOption10.text = optionLetters[9].toString()
            tvOption11.text = optionLetters[10].toString()
            tvOption12.text = optionLetters[11].toString()
        }
    }

    private fun fillAnswersList() {
        binding.apply {
            answersList.add(tvAnswer1)
            answersList.add(tvAnswer2)
            answersList.add(tvAnswer3)
            answersList.add(tvAnswer4)
            answersList.add(tvAnswer5)
            answersList.add(tvAnswer6)
            answersList.add(tvAnswer7)
            answersList.add(tvAnswer8)
        }
    }

    private fun fillOptionsList() {
        binding.apply {
            optionList.add(tvOption1)
            optionList.add(tvOption2)
            optionList.add(tvOption3)
            optionList.add(tvOption4)
            optionList.add(tvOption5)
            optionList.add(tvOption6)
            optionList.add(tvOption7)
            optionList.add(tvOption8)
            optionList.add(tvOption9)
            optionList.add(tvOption10)
            optionList.add(tvOption11)
            optionList.add(tvOption12)
        }
    }

    private fun setLetter(textView: TextView) {
        val letter = textView.text.toString()
        if (letter.isNotEmpty() && userAnswer.filter { it.first != "" }.size != currentQuestion.answer.length) {
            val pair = Pair(letter, textView)
            val emptyIndex = userAnswer.indexOf(Pair("", binding.tvAnswer1))
            if (emptyIndex == -1) {
                userAnswer.add(pair)
                click_variyant()
            } else {
                click_variyant()
                userAnswer[emptyIndex] = pair
            }
            textView.text = ""
            answersList[userAnswer.indexOf(pair)].text = letter
        }

        if (userAnswer.filter { it.first != "" }.size == currentQuestion.answer.length) {
            var answer = ""
            userAnswer.forEach {
                answer += it.first
            }
            if (answer == currentQuestion.answer) {
                answersList.forEach {
                    it.isClickable = false
                }
                binding.apply {
                    nextScreen.hideOrShow(true)
                    lightAnim.hideOrShow(true)

                    trueanswerSound()

                    submitBtn.setOnClickListener {
                        if (clickItemText == null) {
                            clickItemText = binding.submitBtn
                            binding.submitBtn.animate()
                                .scaleX(0.7f)
                                .setDuration(200)
                                .scaleY(0.7f)
                                .withEndAction {
                                    binding.submitBtn.animate()
                                        .setDuration(90)
                                        .scaleY(1f)
                                        .scaleX(1f)
                                        .withEndAction {
                                            clickItemText = null
                                            it.isClickable = false
                                            alphaAnimation(binding.nextScreen)
                                        }
                                        .start()
                                }
                                .start()
                        }
                    }
                }
            } else {
                vibratePhone(200L)
            }
        }
    }

    private fun removeLetter(textView: TextView) {
        val letter = textView.text.toString()
        if (letter.isNotEmpty()) {
            click_variyant()
            val index = answersList.indexOf(textView)
            val pair = userAnswer[index]
            textView.text = ""
            pair.second.text = pair.first


            userAnswer[index] = Pair("", binding.tvAnswer1)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun submitFunc() {
        binding.nextScreen.hideOrShow(false)
        binding.lightAnim.hideOrShow(false)
        when {
            index < questions.size - 1 -> {
                index++
            }

            else -> {
                index = 0
                repeatedCount++
                addIntToSharePrefs("REPEATED_COUNT", repeatedCount)
            }
        }
        answersList.forEach {
            it.isClickable = true
        }

        coins_count = (coins_count + 4)

        addIntToSharePrefs("LAST_QUE", index)
        addIntToSharePrefs("COIN_COUNT", coins_count)

        val editor = sharedPreferences.edit()
        editor.putBoolean("sound", isSoundShaprefpreferense)
        editor.apply()

        currentQuestion = questions[index]
        userAnswer.clear()
        setQuestion()
        fillAnswersList()
        binding.tvLevelNumber.text = "${currentQuestion.id + repeatedCount * questions.size}"
        binding.coinCount.text = coins_count.toString()
    }

    private fun removeAllLetters() {
        answersList.forEach {
            val letter = it.text.toString()
            if (letter.isNotEmpty()) {
                val index = answersList.indexOf(it)
                val pair = userAnswer[index]
                it.text = ""
                pair.second.text = pair.first


                userAnswer[index] = Pair("", binding.tvAnswer1)
            }
        }
    }

    fun View.hideOrShow(boolean: Boolean) {
        if (boolean) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }


    fun vibratePhone(duration: Long) {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        duration,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                vibrator.vibrate(duration)
            }
        }
    }

    private fun alphaAnimation(layoutt: View) {
        val animation1 = AlphaAnimation(1.0f, 0f)
        animation1.duration = 1000
        layoutt.startAnimation(animation1)
        submitFunc()

        Handler().postDelayed({
            binding.submitBtn.isClickable = true
        }, 5000)
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

    private fun addIntToSharePrefs(name: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(name, value)
        editor.apply()
    }
}
