import java.io.File

fun main() {
    val name = bigLetters(getString("Enter name and surname: "))
    val status = getString("Enter person's status: ")

    printNameTag(name, status)
}

fun bigLetters(name: String): Array<String> {
    val file = File("medium.txt")
    val letters = file.readText().split("\n")
    val big = Array(3) { "" }

    for (char in name) {
        if (char != ' ') {
            val start = letters.indexOfFirst { it.contains("$char ") }
            for (i in 1..3) big[i - 1] += letters[start + i]
        } else {
            for (i in 0..2) big[i] += "     "
        }
    }
    return big.map { "  $it " }.toTypedArray()
}

fun printNameTag(name: Array<String>, status: String) {
    val nameL = name[0].length
    val statL = status.length + 4
    val bothOdd = nameL % 2 != 0 && statL % 2 != 0
    val padName = if (nameL >= statL) (name.map { "*$it*" }).toTypedArray() else {
        (name.map {
            "*" + " ".repeat((statL / 2) - (if (bothOdd) 0 else nameL % 2) - (nameL / 2)) + it +
                    " ".repeat((statL / 2) + (if (bothOdd) 0 else statL % 2) - (nameL / 2)) + "*"
        }).toTypedArray()
    }
    val padStatus = if (statL >= nameL) "*  $status  *" else {
        "*" + " ".repeat(((nameL) / 2) - (if (bothOdd) 0 else statL % 2) - (status.length / 2)) + status +
                " ".repeat(((nameL) / 2) + (if (bothOdd) 0 else nameL % 2) - (status.length / 2)) + "*"
    }
    val topBottom = "*".repeat(padStatus.length)

    println(topBottom)
    for (string in padName) println(string)
    println(padStatus)
    println(topBottom)
}

fun getString(text: String): String {
    print(text)
    return readLine()!!
}