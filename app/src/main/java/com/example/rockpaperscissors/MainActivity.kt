package com.example.rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import org.w3c.dom.Text
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(R.layout.activity_main), DialogCallback {

    lateinit var dialog: DialogFragment
    lateinit var buttonChoice: Button
    lateinit var imagePlayer1: ImageView
    lateinit var imagePlayer2: ImageView
    lateinit var textViewWeapon: TextView
    lateinit var buttonPlay: Button
    lateinit var buttonRestart: Button

    var player1choice = -1
    var player2choice = -1

    val weaponsList = listOf(R.drawable.rock, R.drawable.paper, R.drawable.scissor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buttonChoice = findViewById(R.id.btn_choice)
        imagePlayer1 = findViewById(R.id.iv_player1)
        imagePlayer2 = findViewById(R.id.iv_player2)

        buttonChoice.setOnClickListener(){
            dialog = ChoiceDialogFragment()
            dialog.show(supportFragmentManager, null)
        }

        textViewWeapon = findViewById<TextView>(R.id.tv_weapon_choice)

        buttonPlay = findViewById(R.id.btn_play)
        buttonRestart = findViewById(R.id.btn_restart)

        buttonRestart.setOnClickListener(){
            restartGame()
        }
        
        buttonPlay.setOnClickListener(){
            player1choice = (0..2).random()
            imagePlayer1.setImageResource(weaponsList[player1choice])
            if(player2choice == 0 && player1choice == 2 || player2choice == 1 && player1choice == 0){
                textViewWeapon.text = "Вы выиграли!"
                textViewWeapon.setTextColor(ContextCompat.getColor(this, R.color.green))
                playButtonSetup()
            }else if(player1choice == player2choice){
                textViewWeapon.text = "Ничья!"
                textViewWeapon.setTextColor(ContextCompat.getColor(this, R.color.black))
                playButtonSetup()
            }else {
                textViewWeapon.text = "Вы проиграли!"
                textViewWeapon.setTextColor(ContextCompat.getColor(this, R.color.red))
                playButtonSetup()
            }
        }

    }

    fun playButtonSetup(){
        buttonPlay.visibility = View.GONE
        buttonRestart.visibility = View.VISIBLE
    }

    fun restartGame(){
        buttonRestart.visibility = View.GONE
        imagePlayer1.setImageResource(R.drawable.question)
        imagePlayer2.visibility = View.INVISIBLE
        buttonChoice.visibility = View.VISIBLE
        textViewWeapon.text = "Выберите оружие"
        textViewWeapon.setTextColor(ContextCompat.getColor(this, R.color.black))
    }

    override fun onChoiceImageClicked(choice: Int) {
        buttonPlay.visibility = View.VISIBLE
        player2choice = choice
        when(choice){
            0 -> textViewWeapon.text = "Вы выбрали камень!"
            1 -> textViewWeapon.text = "Вы выбрали бумагу!"
            2 -> textViewWeapon.text = "Вы выбрали ножницы!"
        }
        buttonChoice.visibility = View.GONE
        imagePlayer2.setImageResource(weaponsList[choice])
        imagePlayer2.visibility = View.VISIBLE
        dialog.dismiss()
    }
}