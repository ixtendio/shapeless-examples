package com.example.shapeless

import io.circe.parser.decode
import io.circe.shapes._
import io.circe.generic.auto._
import shapeless.{:+:, CNil, Poly1}

object JsonDecodingExample extends App {

  val jsonIban = """{"iban": "NL85INGB0673110978"}"""
  val jsonPersonalDetails = """{"lastName": "Doe", "dateOfBirth": "20-08-1980"}"""
  val jsonKvkNumber = """{"kvkNumber": "956475988"}"""
  val jsonClientNumber = """{"clientNumber": "5463"}"""

  trait SearchCriteria

  case class Iban(iban: String) extends SearchCriteria

  case class PersonalDetails(lastName: String, dateOfBirth: String) extends SearchCriteria

  case class KvkNumber(kvkNumber: String) extends SearchCriteria

  case class ClientNumber(clientNumber: String) extends SearchCriteria

  type SearchRequest = Iban :+: PersonalDetails :+: KvkNumber :+: ClientNumber :+: CNil

  val searchRequest: SearchRequest = decode[SearchRequest](jsonClientNumber).right.get

  object folder extends Poly1 {
    implicit def atOut[T <: SearchCriteria]: Case.Aux[T, SearchCriteria] = at(p => p)
  }

  val searchCriteria = searchRequest.fold(folder)

  println(searchRequest)
  println(searchCriteria)

}
