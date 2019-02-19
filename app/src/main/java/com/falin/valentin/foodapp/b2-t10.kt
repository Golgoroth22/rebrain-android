package com.falin.valentin.foodapp


fun main() {
//    val animals = listOf(
//        Animal.Cat(name = "Anna"),
//        Animal.Dog(name = "Mike"),
//        Animal.Mouse(name = "Mickey")
//    )
//
//    animals.animalListToString(ShowBy.NOISE, " + ").also(::println)
//    animals.animalListToString({ this.noise }, " + ").also(::println)
}

fun List<Animal>.animalListToString(showBy: ShowBy? = null, separator: String = ""): String {
    val result = StringBuilder("")
    for (i in 0 until this.size) {
        when (showBy) {
            ShowBy.NAME -> result.append("${this[i].name}$separator")
            ShowBy.AGE -> result.append("${this[i].age}$separator")
            ShowBy.NOISE -> result.append("${this[i].noise}$separator")
            null -> result.append("${this[i]}$separator")
        }
    }
    return result.toString()
}

fun List<Animal>.animalListToString(showBy: Animal.() -> String, separator: String = ""): String {
    val result = StringBuilder("")
    for (i in 0 until this.size) {
        result.append("${showBy(this[i])}${if (i < this.size - 1) separator else ""}")
    }
    return result.toString()
}

enum class ShowBy {
    NAME,
    AGE,
    NOISE
}

