import java.io.File

fun main() {
    val name = bigLetters(getString("Enter name and surname: "), import("roman.txt"))
    val status = bigLetters(getString("Enter person's status: "), import("medium.txt"))

    printNameTag(name, status)
}

fun bigLetters(word: String, bigLetters: Map<Char, Array<String>>): Array<String> {
    val size = bigLetters.values.toList()[0].size
    val space = bigLetters.values.toList()[0][0].length
    val big = Array(size) { "" }

    for (char in word) {
        for (i in 0 until size) big[i] += if (char == ' ') " ".repeat(space) else bigLetters[char]?.get(i) ?: ""
    }

    return big.map { "  $it  " }.toTypedArray()
}

fun import(fileName: String): Map<Char, Array<String>> {
    val file = File(fileName)
    return mapArray(file.readText().split("\n").toTypedArray())
}

fun mapArray(mapThis: Array<String>): Map<Char, Array<String>> {
    val size = mapThis[0].split(" ")[0].toInt()
    var count = 0
    val letterMap = mutableMapOf<Char, Array<String>>()
    var charHold = ' '

    for (i in 1..mapThis.lastIndex) {
        when (count) {
            0 -> {
                charHold = mapThis[i][0]
                letterMap[charHold] = Array(size) { "" }
            }
            in 1..size -> {
                letterMap[charHold]?.set(count - 1, (mapThis[i]))
                if (count == size) count = -1
            }
        }
        count++
    }
    return letterMap
}

fun printNameTag(name: Array<String>, status: Array<String>) {
    val nameL = name[0].length
    val statL = status[0].length
    val sidesPad = { array: Array<String> -> (array.map { "88${it}88" }).toTypedArray() }
    val bothOdd = nameL % 2 != 0 && statL % 2 != 0
    val padName = if (nameL >= statL) sidesPad(name) else padArray(name, bothOdd, statL, nameL)
    val padStatus = if (statL >= nameL) sidesPad(status) else padArray(status, bothOdd, nameL, statL)
    val topBottom = "8".repeat(padStatus[0].length)

    println(topBottom)
    for (string in padName) println(string)
    for (string in padStatus) println(string)
    println(topBottom)
}

fun padArray(padThis: Array<String>, bothOdd: Boolean, large: Int, small: Int): Array<String> {
    return (padThis.map {
        "88" + " ".repeat(((large) / 2) - (if (bothOdd) 0 else small % 2) - (small / 2)) + it +
                " ".repeat(((large) / 2) + (if (bothOdd) 0 else large % 2) - (small / 2)) + "88"
    }).toTypedArray()
}

fun getString(text: String): String {
    print(text)
    return readLine()!!
}