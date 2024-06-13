package com.example.tiptime

import kotlin.math.PI

open class RoundHut(residents: Int, val radius : Double) : Dwelling(residents){
    override val buildingMaterial: String  = "straw"
    override val capacity: Int = 4
    override fun floorArea(): Double {
        return radius * radius * PI
    }
}