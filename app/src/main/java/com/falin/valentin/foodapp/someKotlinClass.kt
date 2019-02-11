package com.falin.valentin.foodapp

fun main() {
    val a = Animal.createAnimalByNoise(Animal.Mouse().noise)
    val b = Animal.Mouse(name = "Mickey")
    a?.makeNoise()
    a.hashCode().also(::println)
    a?.equals(b).also(::println)

    val c = Animal.Dog()
    checkAnimal(c)
}

sealed class Animal {
    companion object {
        fun createAnimalByNoise(noise: String): Animal? {
            return when (noise) {
                Cat().noise -> Cat()
                Dog().noise -> Dog()
                Mouse().noise -> Mouse()
                else -> null
            }
        }
    }

    abstract val noise: String
    abstract val name: String?
    abstract var age: Int?

    fun makeNoise() = println(noise)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Animal) return false

        if (noise != other.noise) return false
        if (name != other.name) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        var result = noise.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (age ?: 0)
        return result
    }


    data class Cat(
        override val noise: String = "Meow!!!",
        override val name: String? = null,
        override var age: Int? = null
    ) : Animal()

    data class Dog(
        override val noise: String = "Woof!! Woof!!!",
        override var age: Int? = null,
        override val name: String? = null
    ) : Animal()

    data class Mouse(
        override val noise: String = "Squeak!!!",
        override val name: String? = null,
        override var age: Int? = null
    ) : Animal()
}

fun checkAnimal(animal: Animal) {
    when (animal) {
        is Animal.Cat -> {
            print("It is Cat! Cat says - ")
            animal.makeNoise()
        }
        is Animal.Dog -> {
            print("It is Dog! Dog says - ")
            animal.makeNoise()
        }
        else -> print("Animal is undefined")
    }
}



