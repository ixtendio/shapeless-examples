package com.example.shapeless

import shapeless._
import shapeless.record._
import shapeless.syntax.singleton.mkSingletonOps

object LabelledGenericExample extends App {

  case class Book(author: String, title: String, id: Int, price: Double)

  case class ExtendedBook(author: String, title: String, id: Int, price: Double, inPrint: Boolean)

  val bookGen = LabelledGeneric[Book]
  val book = Book("Martin Odersky", "Programming in Scala", 23434324, 44.11)
  val bookHList = bookGen.to(book)

  val bookExtGen = LabelledGeneric[ExtendedBook]
  val extendedBook = bookExtGen.from(bookHList + ('inPrint ->> true))

  println(extendedBook) // prints ExtendedBook(Martin Odersky,Programming in Scala,23434324,44.11,true)

}
