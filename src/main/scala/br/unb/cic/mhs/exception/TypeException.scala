package br.unb.cic.mhs.exception

case class TypeException (private val message : String = "", private val cause : Throwable = None.orNull ) extends Exception(message, cause)
  
