package br.unb.cic.mhs

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import br.unb.cic.mhs.ast.ExpressaoSoma
import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.ast.ValorBooleano
import br.unb.cic.mhs.exception.TypeException
import br.unb.cic.mhs.ast.TErro
import br.unb.cic.mhs.visitors.VerificacaoTipo
import br.unb.cic.mhs.ast.TInteiro

class ExpressaoSomaTeste extends FlatSpec with Matchers {
    
  "uma soma entre os valores 3 e 4" should "levar ao valor sete" in {
    val soma = new ExpressaoSoma(new ValorInteiro(3), new ValorInteiro(4));
    
    new VerificacaoTipo().visitar(soma) should be (TInteiro)
    soma.avaliar().asInstanceOf[ValorInteiro].valor should be (7);
  }
  
  "uma soma entre valores 3 e true" should "levar a um erro de tipos" in {
    val soma = new ExpressaoSoma(new ValorInteiro(3), new ValorBooleano(true));
    
    new VerificacaoTipo().visitar(soma) should be (TErro)
  }
  
}