package br.unb.cic.mhs.ast

import br.unb.cic.mhs.memoria.AmbienteExpressao
import br.unb.cic.mhs.visitors.MHSVisitor

class Referencia(val id: String) extends Expressao {
   override def avaliar() : Valor = AmbienteExpressao.pesquisar(id).avaliar()
   
   override def aceitar[T](visitor : MHSVisitor[T]) : T =  visitor.visitar(this)
   
   override def equals(other: Any) = {
    other match {
      case that: br.unb.cic.mhs.ast.Referencia => true
      case _ => false
    }
  }
}