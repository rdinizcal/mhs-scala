package br.unb.cic.mhs.ast

import br.unb.cic.mhs.visitors.MHSVisitor

class ExpressaoLambda (val arg : Expressao, val corpo : Expressao) extends Expressao {
  
   override def avaliar() : Valor = {
       corpo.avaliar()
   }
  
   override def aceitar[T](visitor : MHSVisitor[T]) : T =  visitor.visitar(this)
}