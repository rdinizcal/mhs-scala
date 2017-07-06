package br.unb.cic.mhs

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import br.unb.cic.mhs.ast.ExpressaoITE
import br.unb.cic.mhs.ast.TErro
import br.unb.cic.mhs.ast.ValorBooleano
import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.visitors.VerificacaoTipo

class ExpressaoITETeste extends FlatSpec with Matchers {
  
  "avaliar a expressao if(true) then 5 else 4" should "levar ao valor 5" in {
    val ite = new ExpressaoITE(new ValorBooleano(true), new ValorInteiro(5), new ValorInteiro(0))
    
    ite.avaliar().asInstanceOf[ValorInteiro].valor should be (5)
  }
  
  "avaliar a expressao if(false) then 5 else 4" should "levar ao valor 0" in {
    val ite = new ExpressaoITE(new ValorBooleano(false), new ValorInteiro(5), new ValorInteiro(0))
    
    ite.avaliar().asInstanceOf[ValorInteiro].valor should be (0)
  }
  
  "a avaliacao if(5) then true else false" should "levar a um erro de tipo" in {
      val ite = new ExpressaoITE(new ValorInteiro(5), new ValorBooleano(true), new ValorBooleano(false))
      
      new VerificacaoTipo().visitar(ite) should be (TErro)
  }
  
  "a avaliacao if(true) then 5 else false" should "levar a um erro de tipo" in {
      val ite = new ExpressaoITE(new ValorBooleano(true), new ValorInteiro(5), new ValorBooleano(false))
      
      new VerificacaoTipo().visitar(ite) should be (TErro)
  }
}