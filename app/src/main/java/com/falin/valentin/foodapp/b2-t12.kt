package com.falin.valentin.foodapp

fun main() {
//    val map = mapOf(
//        "age" to "25",
//        "name" to "Bob",
//        "gender" to "m"
//    )
//
//    val p = Person.from(map)
//    print(p.age)
}

enum class Gender(s: String) {
    MALE("m"),
    FEMALE("f");

    var id: String? = null

    companion object {
        fun getById(id: String?): Gender {
            return when (id) {
                "m" -> MALE
                "f" -> FEMALE
                else -> throw IllegalStateException()
            }
        }
    }
}

class House {
    private val persons: MutableList<Person> = mutableListOf()
    private val children: MutableList<Person> = mutableListOf()
    private val adultWomen: MutableList<Person> = mutableListOf()
    private val adultMen: MutableList<Person> = mutableListOf()

    val getAdults: List<Person>
        get() {
            val adults: MutableList<Person> = mutableListOf()
            for (p in persons) {
                if (p.age ?: 0 > 18) {
                    adults.add(p)
                }
            }
            return adults
        }

    init {
        children.add(Person("Anna", 2, Gender.FEMALE))
        adultMen.add(Person("Mike", 45, Gender.MALE))
        adultWomen.add(Person("Maria", 45, Gender.FEMALE))
        children.add(Person("Boris", 15, Gender.MALE))
        adultMen.add(Person("Jordan", 21, Gender.MALE))

        persons.addAll(children)
        persons.addAll(adultWomen)
        persons.addAll(adultMen)
    }

    fun addPerson(p: Person) {
        if (p.age ?: 0 > 18) {
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
        var greet: String? = null

        if (p != null) {
            greet = when {
                p.age ?: 0 < 14 -> "Hello, little " + p.name!!
                p.age ?: 0 in 14..17 -> when (p.gender) {
                    Gender.MALE -> "Hello boy!"
                    Gender.FEMALE -> "Hello girl!"
                }
                else -> when (p.gender) {
                    Gender.MALE -> "Hello  man!"
                    Gender.FEMALE -> "Hello  woman!"
                }
            }
        }

        if (greet != null) {
            print(greet)
        }
    }
}

open class Person(
    val name: String?,
    var age: Int? = 0,
    val gender: Gender
) {
    companion object {
        fun from(personMap: Map<String, String>): Person {
            return Person(personMap["name"], personMap["age"]?.toIntOrNull(), Gender.getById(personMap["gender"]))
        }
    }
}