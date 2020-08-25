fun main() {
    val name = readLine()!!

    println(nameTag(name))
}

fun nameTag(name: String): String {
    val topBottom = "*".repeat(name.length + 4)
    val middle = "* $name *\n"

    return topBottom + "\n" + middle + topBottom
}