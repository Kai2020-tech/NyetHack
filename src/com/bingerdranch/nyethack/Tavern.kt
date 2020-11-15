package com.bingerdranch.nyethack

import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"

//var playerGold = 10
//var playerSilver = 10

//顧客列表,可使用 1.建立集合 List 2.限制裡面的資料類型<String>
val patronList = listOf("Eli", "Mordoc", "Sophie")  //類型推斷,同時不限制存放資料類型
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt").readText().split("\n")
val patronGold = mutableMapOf<String,Double>()


fun main() {

    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }

    uniquePatrons.forEach {
        patronGold[it]=6.0
    }
//    println(com.bingerdranch.nyethack.getPatronGold)

//    var orderCount = 0
//    while (orderCount <= 9) {
//        com.bingerdranch.nyethack.placeOrder(com.bingerdranch.nyethack.getUniquePatrons.shuffled().first(), com.bingerdranch.nyethack.getMenuList.shuffled().first())
//        orderCount++
//    }
    menuDisplay(menuList)  //10.11挑戰練習
//    com.bingerdranch.nyethack.displayPatronBalances()


}

/*fun com.bingerdranch.nyethack.performPurchase(price: Double) {
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)

    println("Total purse: $totalPurse")
    println("Purchasing item for $price")

    val remainingBalance = totalPurse - price
//    依照指定精度,對double做四捨五入
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")
//    將餘額轉回金幣&銀幣,將Double轉成Int,就是直接捨棄小數點之後的值
    val remainingGold = remainingBalance.toInt()
//    %取餘數,在此就是4.1899999999999995除1,整數4能被1整除,所以餘數是0.1899999999999995
//    0.1899999999999995乘以100,再呼叫roundToInt()做四捨五入,得到整數19
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
}

private fun displayBalance() {
    println("com.bingerdranch.nyethack.Player's purse balance: Gold: $playerGold , silver: $playerSilver")
}*/

fun performPurchase(price: Double, patronName: String){
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

private fun displayPatronBalances(){
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

//    解構語法()內的3個變數,代表指向menuData.split分成三段[0,1,2]的值
    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price."
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims: ${toDragonSpeak("Ah,delicious $name!")}"
    } else {
        "$patronName says: Thanks for the $name"
    }

    println(phrase)

}

// 7.6挑戰練習,處理大寫字母,用toLowerCase將字串都轉成小寫
private fun toDragonSpeak(phrase: String) =
        phrase.toLowerCase().replace(Regex("[aeiou]")) {
            when (it.value) {
                "a" -> "4"
                "e" -> "3"
                "i" -> "1"
                "o" -> "0"
                "u" -> "|_|"
                else -> it.value
            }
        }

// 10.11挑戰練習,輸出...使每行同長
// 10.12未完成
private fun menuDisplay(list: List<String>) {
    println("*** Welcome to $TAVERN_NAME *** \n")

/*//    enping解法
    val maxNameLength = list.map { it.split(",")[1].length }.max()?:0
    val maxPriceLength = list.map { it.split(",")[2].length }.max()?:0
    //aaa..... .....bb
    //aa......b
    list.map {
        val i = it.split(",")
        Triple<String,String,String>(i[0],i[1],i[2])
    }.map {
        //prefix + postfix
        val detail = it.second.padEnd(maxNameLength+5,'.') + it.third.padStart(maxPriceLength,'.')
        val title = " ".repeat((maxNameLength + maxPriceLength - it.first.length)/2) + "~[${it.first}]~"
        title to detail
    }
    .groupBy {
        it.first
    }
    .mapValues {
        it.value.map { it.second }.joinToString("\n")
    }
    .forEach {
//        println(it)
        println(it.key)
        println(it.value)
    }

    val p = Pair("key","vale")
    val map = mutableMapOf<String,MutableList<String>>()
    val list = listOf<Pair<String,String>>()
    list.forEach {
        val res = map.getOrDefault(it.first, mutableListOf())
        res.add(it.second)
        map.put(it.first, res)
    }*/

    /*
//  讀書會解法
fun fun1() {
    var menuList = listOf(
        "shandy,Dragon's Breath,5.91",
        "elixir,Shirley's Temple,4.12",
        "meal,goblet of LaCroix,1.22",
        "desert dessert,pickled camel hump,7.33",
        "elixir,iced boilermaker,11.22"
    )

    val welcome = "*** Welcome to $TAVERN_NAME ***"
    println(welcome)
    println()

    menuList.forEach {
        val (type, name, price) = it.split(',')

        val pad = welcome.length - price.length
        println("${name.capitalize().padEnd(pad, '.')}$price")
    }
}

fun fun2() {
    var menuList = listOf(
        "shandy,Dragon's Breath,5.91",
        "elixir,Shirley's Temple,4.12",
        "meal,goblet of LaCroix,1.22",
        "desert dessert,pickled camel hump,7.33",
        "elixir,iced boilermaker,11.22"
    )

    val welcome = "*** Welcome to $TAVERN_NAME ***"
    println(welcome)

    menuList.groupBy {
        val (type, name, price) = it.split(',')
        type
    }.forEach { (type, names) ->
        val typeName = "~[$type]~"
        val padding = (welcome.length - typeName.length) / 2
        println(" ".repeat(padding) + typeName)

        names.forEach {
            val (type, name, price) = it.split(',')
            val pad = welcome.length - price.length
            println("${name.capitalize().padEnd(pad, '.')}$price")
        }
    }
}
    */

    list.forEach { it ->
        val (title, itemName, price) = it.split(",")
        val upperOne = itemName[0].toUpperCase()    //取每個String第一爲大寫
//        println("~[$title]~")
        itemName.forEachIndexed() { index, char ->
            if (index == 0) {
                print(upperOne)
            } else {
                print("$char")
            }
        }
        var i = itemName.count() + price.count()
        while (i < 30) {
            print(".")
            i++
        }
        println("$price")
    }
}