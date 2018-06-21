package com.example.shapeless

import shapeless.{HNil, Poly1}

object PolymorphicFunctionsExamples extends App {

  case class User(name: String, age: Int)

  val hlist = 35 :: "Hello" :: User("Mark", 50) :: HNil

  object mapHList extends Poly1 {
    implicit def caseInt = at[Int](_ * 2)

    implicit def caseString = at[String](_ + " World")


    implicit def caseUser = at[User] {
      case User(name, age) => User(name, age + 1)
    }
  }

  val result = hlist.map(mapHList)

  println(result) //prints: 70 :: "Hello World" :: User("Mark", 51) :: HNil
}
