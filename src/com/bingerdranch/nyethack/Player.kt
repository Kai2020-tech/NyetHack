package com.bingerdranch.nyethack

import java.io.File
import java.sql.DriverManager.println

class Player(_name: String,
             var healthPoints: Int = 100,
             val isBlessed: Boolean,
             private val isImmortal: Boolean) {
    var name = _name
        get() = "${field.capitalize()} of $hometown"
        private set(value) {
            field = value.trim()
        }

    //    val hometown = selectHometown()
//    改爲by lazy,表示需要時才初始化,程式比較有效率
    val hometown by lazy { selectHometown() }
    var currentPosition = Coordinate(0, 0)

    init {
        require(healthPoints > 0, { "healthPoints must be greater than zero." })
        require(name.isNotBlank(), { "Player must have a name." })
    }


    //次建構函數,可定義初始化邏輯,但不能在此新增屬性
    constructor(name: String) : this(name,
            isBlessed = true,
            isImmortal = false) {
        if (name.toLowerCase() == "kar") healthPoints = 40
    }

    private fun selectHometown() = File("data/towns.txt")
            .readText()
            .split("\n")
            .shuffled()
            .first()

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }

    fun formatHealthStatus() =
            when (healthPoints) {
                100 -> "is in excellent condition!"
                in 90..99 -> "has a few scratches"
                in 75..89 ->
                    when (isBlessed) {
                        true -> "has some minor wounds but is healing quite quickly!"
                        false -> "has some minor wounds."
                    }
                in 15..74 -> "looks pretty hurt."
                else -> "is in awful condition!"
            }

    fun castFireball(numFireballs: Int = 2) =
            println("A glass of Fireball springs into existence.(x$numFireballs)")
}