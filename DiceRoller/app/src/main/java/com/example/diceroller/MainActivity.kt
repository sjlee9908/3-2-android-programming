package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { //메인, 맨처음 액티비티에 화면을 그림
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener{
            rollDice()
        }
        rollDice()
    }

    private fun rollDice(){
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val diceImage : ImageView = findViewById(R.id.imageView)
        //diceImage.setImageResource(R.drawable.dice_2)

//        when(diceRoll){
//            1-> diceImage.setImageResource(R.drawable.dice_1)
//            2-> diceImage.setImageResource(R.drawable.dice_2)
//            3-> diceImage.setImageResource(R.drawable.dice_3)
//            4-> diceImage.setImageResource(R.drawable.dice_4)
//            5-> diceImage.setImageResource(R.drawable.dice_5)
//            6-> diceImage.setImageResource(R.drawable.dice_6)
//        }

        val drawableRes = when(diceRoll){
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        diceImage.setImageResource(drawableRes)
        diceImage.contentDescription = diceRoll.toString()
    }


}

class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}