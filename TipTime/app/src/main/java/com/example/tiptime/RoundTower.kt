package com.example.tiptime

class RoundTower(resident : Int, radius: Double, val floors: Int = 2) : RoundHut(resident, radius) {
    override val buildingMaterial: String  = "wood"
    override val capacity: Int = 6 * floors
    override fun floorArea(): Double {
        return super.floorArea()* floors
    }
}