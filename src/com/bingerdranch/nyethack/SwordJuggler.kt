package com.bingerdranch.nyethack

import java.lang.Exception

fun main() {
    var swordsJuggling: Int? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if (isJugglingProficient) {
        swordsJuggling = 2
        juggleSwords(swordsJuggling)
    }

    try {
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(1)

    } catch (e: Exception) {
        println(e)
    }

    println("You juggle $swordsJuggling swords!")


}

fun proficiencyCheck(swordsJuggling: Int?) {
    checkNotNull(swordsJuggling, {"com.bingerdranch.nyethack.Player cannot juggle swords"})
}

//if swordsJuggling <3,throw IllegalArgumentException: Juggle at least 3 swords to be exciting.
fun juggleSwords(swordsJuggling: Int){
    require(swordsJuggling >= 3,{"Juggle at least 3 swords to be exciting."})
    //Juggle
}