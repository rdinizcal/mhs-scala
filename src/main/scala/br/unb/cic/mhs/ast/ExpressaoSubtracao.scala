package br.unb.cic.mhs.ast

import br.unb.cic.mhs.visitors.MHSVisitor
import br.unb.cic.mhs.exception.TypeException


class ExpressaoSubtracao(lhs : Expressao, rhs : Expressao) extends ExpressaoBinaria(lhs, rhs) {
  
  override def avaliar() : Valor = {
    if(this.verificarTipo() == TErro) throw new TypeException("Tipos incompatíveis.")
    
    val v1 = lhs.avaliar().asInstanceOf[ValorInteiro]
    val v2 = rhs.avaliar().asInstanceOf[ValorInteiro]
      
    return new ValorInteiro(v1.valor - v2.valor); 
  }
  
  override def verificarTipo : Tipo = {
    val t1 = lhs.verificarTipo()
    val t2 = rhs.verificarTipo()
    
    return if(t1.equals(TInteiro) && t2.equals(TInteiro)) TInteiro else TErro      
  }
  
  override def aceitar[T](visitor : MHSVisitor[T]) : T =  visitor.visitar(this)

  override def equals(other: Any) = {
    other match {
      case that: br.unb.cic.mhs.ast.ExpressaoSubtracao => { 
        if(this.lhs.equals(that.lhs) && this.rhs.equals(that.rhs)) true 
        else false
        }
      case _ => false
    }
  }
  
}