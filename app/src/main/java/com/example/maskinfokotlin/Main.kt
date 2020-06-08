package com.example.maskinfokotlin

fun main() {
    val str = "Hello"

    val item = Item("항아리")

    print(item)
}


// 확장함수
fun String.myLength(): Int {
    return this.length
}

