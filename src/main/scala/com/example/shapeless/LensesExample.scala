package com.example.shapeless

import shapeless._

object LensesExample extends App {

  case class User(name : String, age : Int)

  val ageLens = lens[User] >> 'age

  val user = User("Mark", 35)

  // Read a field
  val userAge = ageLens.get(user) // Type inferred is Int
  println(userAge) //returns 35

  // Update the age
  val updatedUser = ageLens.set(user)(50)
  println(user.hashCode())
  println(updatedUser.hashCode())
  println(updatedUser.age)
}
