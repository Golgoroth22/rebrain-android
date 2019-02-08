package com.falin.valentin.foodapp

fun main() {
    val s1 = Star();
    s1.toString().also(::println)
    val s2 = Star(name = "Dagoba", age = 1396);
    s2.toString().also(::println)

    val p1 = Planet("Alderaan", true, age = 4678)
    p1.toString().also(::println)
    val p2 = Planet(name = "Tatooine", size = 3, isAlive = true, sputnikName = "Naboo")
    p2.toString().also(::println)

    val g1 = Galaxy()
    g1 + s1

    val g2 = s1 + s2
    g2.starsList.size.also(::println)
    val u1 = Universe.instance
}


open class Star {
    var name: String? = null
    var age: Int? = null
    var size: Int? = null

    constructor() {
        println("Empty object Star is created.")
    }

    constructor(name: String? = null, age: Int? = null, size: Int? = null) {
        this.name = name
        this.age = age
        this.size = size
    }

    override fun toString(): String {
        return "name=$name, age=$age, size=$size"
    }

    operator fun plus(star: Star) = Galaxy(star, this)
}

class Planet(
    val sputnikName: String? = null,
    var isAlive: Boolean? = null,
    name: String? = null,
    age: Int? = null,
    size: Int? = null
) : Star(name, age, size) {

    override fun toString(): String {
        return "Planet(${super.toString()}, sputnikName='$sputnikName', isAlive=$isAlive)"
    }
}

class Galaxy() {
    val starsList: MutableList<Star> = mutableListOf()

    constructor(star1: Star, star2: Star) : this() {
        starsList.addAll(listOf(star1, star2))
    }

    operator fun plus(star: Star) {
        starsList.add(star)
    }
}

class Universe private constructor() {
    companion object {
        val instance = Universe()
    }
}