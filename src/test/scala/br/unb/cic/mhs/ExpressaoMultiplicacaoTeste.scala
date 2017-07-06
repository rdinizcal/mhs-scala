package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoMultiplicacao, ValorBooleano, ValorInteiro}
import br.unb.cic.mhs.exception.TypeException
import org.scalatest.{FlatSpec, Matchers}
import br.unb.cic.mhs.visitors.VerificacaoTipo
import br.unb.cic.mhs.ast.TErro

class ExpressaoMultiplicacaoTeste extends FlatSpec with Matchers {

  behavior of "avaliar expressao multiplicacao"

  "uma multiplicacao entre os valores 3 e 4" should "levar ao valor sete" in {
    val multiplicacao = new ExpressaoMultiplicacao(new ValorInteiro(3), new ValorInteiro(4));
    multiplicacao.avaliar().asInstanceOf[ValorInteiro].valor should be (12);
  }

  "uma multiplicacao entre valores 3 e true" should "levar a um erro de tipos" in {
    val multiplicacao = new ExpressaoMultiplicacao(new ValorInteiro(3), new ValorBooleano(true));
    
    new VerificacaoTipo().visitar(multiplicacao) should be (TErro)
  }

}