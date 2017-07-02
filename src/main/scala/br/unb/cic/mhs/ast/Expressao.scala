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
  
  /**
   * retorna o tipo da expressão caso a expressão 
   * esteja de acordo com os tipos ou um tipo erro
   * para indicar que a expressão está com erro de tipos
   */
  def verificarTipo() : Tipo
  
  def aceitar[T](visitor : MHSVisitor[T]) : T
}