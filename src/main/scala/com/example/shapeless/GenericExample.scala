package com.example.shapeless

import shapeless.Generic

object GenericExample extends App {

  case class User(name: String, age: Int)

  case class Product(name: String, price: Int)

  val genUser = Generic[User]
  val genProduct = Generic[Product]
  val user = User("Ken", 50)

  val hlist = genUser.to(user)
  val product = genProduct.from(hlist)

  println(product)
}
