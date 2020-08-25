fun main() {
    val hyper = "Hyper Skill"

    println(nameTag(hyper))
}

fun nameTag(name: String): String {
    val top = " " + "_".repeat(name.length + 2) + "\n"
    val middle = "| $name |\n"
    val bottom = " " + "•".repeat(name.length + 2)

    return top + middle + bottom
}