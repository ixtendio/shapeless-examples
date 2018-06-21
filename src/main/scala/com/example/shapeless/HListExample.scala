package com.example.shapeless

import shapeless.HNil

object HListExample extends App {

  case class User(name: String, age: Int)

  val hlist = 35 :: "Hello" :: User("Mark", 50):: User("Mark", 51) :: HNil

  val user = hlist.select[User]

  println(user) //prints User(Mark,50)
}
