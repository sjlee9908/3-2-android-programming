package com.example.coroutinetest

import kotlinx.coroutines.*


fun main() {
    println("Main program starts: ${Thread.currentThread().name}") // main 스레드
    GlobalScope.launch { // 기본 Dispatcher를 사용하여 Global Scope에서 코루틴 시작
        println("Fake work starts: ${Thread.currentThread().name}")
        delay(1000) // suspend 함수
        println("Fake work finished: ${Thread.currentThread().name}")
    }
    runBlocking { // 새로운 코루틴을 시작하고 완료할때까지 main 스레드 차단
        delay(1000)
    }
    println("Main program ends: ${Thread.currentThread().name}") // main 스레드
}
