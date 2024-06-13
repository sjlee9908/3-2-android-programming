package com.example.tiptime

fun main() {
    // val dwelling = Dwelling()

//    println("\nSquare Cabin\n============")
//    println("Capacity: ${squareCabin.capacity}")
//    println("Material: ${squareCabin.buildingMaterial}")
//    println("Has room? ${squareCabin.hasRoom()}")

    val squareCabin = SqareCabin(6, 50.0)
    with(squareCabin){
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Has room? ${floorArea()}")
    }


    val roundHut = RoundHut(3, 10.0)
    with(roundHut){
        println("\nRoundHut\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Has room? ${floorArea()}")
    }

    val roundTower = RoundTower(3, 10.0)
    with(roundTower){
        println("\nroundTower\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Has room? ${floorArea()}")
    }

}