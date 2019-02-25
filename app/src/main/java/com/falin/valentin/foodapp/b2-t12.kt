package com.falin.valentin.foodapp

fun main() {
    val map = mapOf(
        "age" to "25a",
        "name" to "Bob",
        "gender" to "fa"
    )

    val p = Person.from(map)
    println(p.age)

//    House().getAdults.also { it.forEach { println(it.name) } }
    print(p.gender)
}

enum class Gender(val id: String) {
    MALE("m"),
    FEMALE("f");

    companion object {
        fun getById(id: String): Gender? {
            return values().firstOrNull { it.id == id }
        }
    }
}

class House {
    private val children = mutableListOf(
        Person("Anna", 2, Gender.FEMALE),
        Person("Boris", 15, Gender.MALE)
    )
    private val adultWomen = mutableListOf(Person("Maria", 45, Gender.FEMALE))
    private val adultMen = mutableListOf(
        Person("Mike", 45, Gender.MALE),
        Person("Jordan", 21, Gender.MALE)
    )

    private val persons = mutableListOf<Person>()

    init {
        persons.addAll(children)
        persons.addAll(adultMen)
        persons.addAll(adultWomen)
    }

    val getAdults: List<Person>
        get() {
            return persons.filter { p -> p.age > 18 }
        }

    fun addPerson(p: Person) {
        if (p.age > 18) {
            if (p.gender === Gender.MALE) {
                adultMen.add(p)
            } else {
                adultWomen.add(p)
            }
        } else {
            children.add(p)
        }

        persons.add(p)
    }

    fun sayHelloToPerson(p: Person?) {

        p?.let {
            print(
                when {
                    p.age < 14 -> "Hello, little " + p.name
                    p.age in 14..17 -> when (p.gender) {
                        Gender.MALE -> "Hello boy!"
                        Gender.FEMALE -> "Hello girl!"
                        null -> "Hello stranger!"
                    }
                    else -> when (p.gender) {
                        Gender.MALE -> "Hello  man!"
                        Gender.FEMALE -> "Hello  woman!"
                        null -> "Hello stranger!"
                    }
                }
            )
        }
    }
}

open class Person(
    val name: String?,
    var age: Int,
    val gender: Gender?
) {
    companion object {
        fun from(personMap: Map<String, String>): Person {
            return Person(
                personMap["name"],
                personMap["age"]?.toIntOrNull() ?: 0,
                Gender.getById(personMap.getValue("gender"))
            )
        }
    }
}