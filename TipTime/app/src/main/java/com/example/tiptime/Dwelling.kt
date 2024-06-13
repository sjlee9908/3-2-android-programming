package com.example.tiptime

abstract class Dwelling(private var resident: Int) {
    abstract val buildingMaterial:String
    abstract val capacity:Int

    fun hasRoom():Boolean{
        return resident < capacity
    }
    abstract fun floorArea(): Double
}