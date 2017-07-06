package br.unb.cic.mhs.ast

import br.unb.cic.mhs.visitors.MHSVisitor

/**
 * Expressao If-Then-Else, permitindo algo como: 
 * 
 * 4 + (If cond Then 5 Else 10)
 */
class ExpressaoITE(val condicao: Expressao, val clausulaThen: Expressao, val clausulaElse: Expressao) extends Expressao{
  override def avaliar() : Valor = {
   if (condicao.avaliar().asInstanceOf[ValorBooleano].valor) 
      clausulaThen.avaliar()
   else clausulaElse.avaliar() 
  }
  
   override def aceitar[T](visitor : MHSVisitor[T]) : T =  visitor.visitar(this)
 
}