package com.example.tiptime

class SqareCabin(residents: Int, val length: Double) : Dwelling(residents){
    override val buildingMaterial: String  = "wood"
    override val capacity: Int = 6
    override fun floorArea(): Double {
        return length * length
    }
}