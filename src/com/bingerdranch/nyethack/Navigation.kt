package com.bingerdranch.nyethack

enum class Direction(private val coordinate: Coordinate) {
    NORTH(Coordinate(0, -1)),   //上
    EAST(Coordinate(1, 0)),     //右
    SOUTH(Coordinate(0, 1)),    //下
    WEST(Coordinate(-1, 0));    //左

    fun updateCoordinate(playerCoordinate: Coordinate) =
            coordinate + playerCoordinate
}

data class Coordinate(val x: Int, val y: Int) {
    val isInBounds: Boolean = x >= 0 && y >= 0

    operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)
}
