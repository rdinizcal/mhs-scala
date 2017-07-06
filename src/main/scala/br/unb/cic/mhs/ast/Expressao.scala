package br.unb.cic.mhs.ast

import br.unb.cic.mhs.visitors.MHSVisitor

class Tipo 

case object TInteiro extends Tipo 
case object TBooleano extends Tipo 
case object TErro extends Tipo

trait Expressao {
  
  /**
   * retorna o valor da avaliação da expressão
   */
  def avaliar() : Valor
  
  def aceitar[T](visitor : MHSVisitor[T]) : T
  
  override def equals(other: Any) : Boolean
}