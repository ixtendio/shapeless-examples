package com.example.shapeless

import io.circe.parser.decode
import io.circe.shapes._
import io.circe.generic.auto._
import shapeless.{:+:, CNil, Poly1}

object JsonTransformationExample extends App {

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

  val searchRequest: SearchRequest = decode[SearchRequest](jsonKvkNumber).right.get


  class DownStreamAPIRequest(val accessToken: String, val payload: String) {

    override def toString = s"DownStreamAPIRequest($accessToken, $payload)"
  }

  val accessToken = "ACCESS_TOKEN"

  object transformer extends Poly1 {
    implicit def atOut[T <: SearchCriteria]: Case.Aux[(T, String), DownStreamAPIRequest] = at(p => p._1 match {
      case Iban(iban)               => new DownStreamAPIRequest(p._2, s"""{"iban": "$iban"}""")
      case PersonalDetails(ln, dob) => new DownStreamAPIRequest(p._2, s"""{"name": "$ln", "birthDate":"$dob"}""")
      case KvkNumber(kvk)           => new DownStreamAPIRequest(p._2, s"""{"kvk": "$kvk"}""")
      case ClientNumber(cn)         => new DownStreamAPIRequest(p._2, s"""{"clientNo": "$cn"}""")
    })
  }

  println(searchRequest.zipConst(accessToken).fold(transformer))


}
