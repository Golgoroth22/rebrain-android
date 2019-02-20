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