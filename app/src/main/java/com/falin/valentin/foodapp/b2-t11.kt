package com.falin.valentin.foodapp

fun main() {
    val animals1 = listOf(
        Animal.Cat(name = "Anna"),
        Animal.Dog(name = "Mike"),
        Animal.Mouse(name = "Mickey")
    )
    val animals2 = listOf(
        Animal.Dog(name = "Sharik"),
        Animal.Dog(name = "Ruffus"),
        Animal.Mouse(name = "Minney")
    )
    val animals3 = listOf(
        Animal.Cat(name = "Chow"),
        Animal.Mouse(name = "Mike"),
        Animal.Mouse(name = "Harley")
    )
    val animals4 = listOf(
        Animal.Dog(name = "Gonchy"),
        Animal.Dog(name = "Woofy"),
        Animal.Mouse(name = "Zo")
    )
    val planet1 = Planet(name = "Dagoba")
    val planet2 = Planet()
    val planet3 = Planet(name = "Oa")
    val planet4 = Planet(name = "Bzzy")

    val map = hashMapOf(
        planet1 to animals1,
        planet2 to animals2,
        planet3 to animals3,
        planet4 to animals4
    )

    print(getDogs(map))
}

fun getDogs(map: Map<Planet, List<Animal>>): List<Animal> {
    return map
        .filter { it.key.name?.length ?: 0 > 5 }
        .flatMap { it.value }
        .filter { it is Animal.Dog }
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

object Universe