package com.falin.valentin.foodapp

fun main() {
//    val s1 = Star();
//    s1.toString().also(::println)
//    val s2 = Star(name = "Dagoba", age = 1396);
//    s2.toString().also(::println)
//
//    val p1 = Planet("Alderaan", true, age = 4678)
//    p1.toString().also(::println)
//    val p2 = Planet(name = "Tatooine", size = 3, isAlive = true, sputnikName = "Naboo")
//    p2.toString().also(::println)
//
//    val g1 = Galaxy()
//    g1 + s1
//
//    val g2 = s1 + s2
//
//    val u1 = Universe.instance
}


open class Star(
    val name: String? = null,
    var age: Int? = null,
    val size: Int? = null
) {
    constructor() : this(null, null, null) {
        println("Empty object Star is created.")
    }

    override fun toString(): String {
        return "name=$name, age=$age, size=$size"
    }

    operator fun plus(star: Star): Galaxy {
        val galaxy = Galaxy()
        galaxy + star
        galaxy + this
        return galaxy
    }
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

class Galaxy {
    val starsList: MutableList<Star> = mutableListOf()
}

operator fun Galaxy.plus(star: Star) {
    starsList.add(star)
}

class Universe private constructor() {
    companion object {
        val instance = Universe()
    }
}