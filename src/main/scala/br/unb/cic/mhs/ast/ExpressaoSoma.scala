package br.unb.cic.mhs.ast

import br.unb.cic.mhs.visitors.MHSVisitor
import br.unb.cic.mhs.exception.TypeException
import br.unb.cic.mhs.visitors.VerificacaoTipo
import br.unb.cic.mhs.visitors.VerificacaoTipo


class ExpressaoSoma(lhs : Expressao, rhs : Expressao) extends ExpressaoBinaria(lhs, rhs) {
  
  override def avaliar() : Valor = {    
    val v1 = lhs.avaliar().asInstanceOf[ValorInteiro]
    val v2 = rhs.avaliar().asInstanceOf[ValorInteiro]
      
    return new ValorInteiro(v1.valor + v2.valor); 
  }
  
  override def aceitar[T](visitor : MHSVisitor[T]) : T =  visitor.visitar(this)

  override def equals(other: Any) = {
    other match {
      case that: br.unb.cic.mhs.ast.ExpressaoSoma => { 
        if(this.lhs.equals(that.lhs) && this.rhs.equals(that.rhs)) true 
        else false
        }
      case _ => false
    }
  }
  
}