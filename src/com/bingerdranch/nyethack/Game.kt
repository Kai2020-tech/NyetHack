package com.bingerdranch.nyethack

fun main(args: Array<String>) {

    Game.play()
}


object Game {

    val player = Player("madrigal")
    var currentRoom: Room = TownSquare()

    var isQuit = false

    private var worldMap = listOf(
            listOf(currentRoom, Room("Tavern"), Room("Back Room")),
            listOf(Room("Long Corridor"), Room("Generic Room"))
    )

    init {
        println("Welcome, adventurer.")
        player.castFireball()
    }

    fun play() {
        while (true) {

            println(currentRoom.description())
            println(currentRoom.load())

            //      Player status
            printPlayerStatus(player)

            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())

            if (isQuit) {   //15.9練習
                println("bye bye")
                break
            }
        }
    }

    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()}) " + "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private fun move(directionInput: String) =
            try {
                val direction = Direction.valueOf(directionInput.toUpperCase())
                val newPosition = direction.updateCoordinate(player.currentPosition)
                if (!newPosition.isInBounds) {
                    throw IllegalStateException("$direction is out of bounds.")
                }

                val newRoom = worldMap[newPosition.y][newPosition.x]
                player.currentPosition = newPosition
                currentRoom = newRoom
                "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
            } catch (e: Exception) {
                "Invalid direction: $directionInput."
            }

    //    15.10挑戰魔力地圖
    private fun map() {
        val townSquare = Coordinate(0, 0)
        val tavern = Coordinate(1, 0)
        val backRoom = Coordinate(2, 0)
        val longCorridor = Coordinate(0, 1)
        val genericRoom = Coordinate(1, 1)

        println(player.currentPosition)
    }


    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        fun processCommand() = when (command.toLowerCase()) {
            "move" -> move(argument)
            "map" -> map()
            "quit" -> {
                isQuit = true
            }
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }
}

/*//練習4.14
private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean) =
        if (isBlessed && healthPoints > 50 || isImmortal) "GREEN" else "NONE"

private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean) =
        when (healthPoints) {
            100 -> "is in excellent condition!"
            in 90..99 -> "has a few scratches."
            in 75..89 ->
                when (isBlessed) {
                    true -> "has some minor wounds but is healing quite quickly!"
                    false -> "has some minor wounds."
                }
            in 15..74 -> "looks pretty hurt."
            else -> "is in awful condition!"
        }*/



