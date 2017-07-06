package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoSubtracao, ValorBooleano, ValorInteiro}
import br.unb.cic.mhs.exception.TypeException
import org.scalatest.{FlatSpec, Matchers}
import br.unb.cic.mhs.visitors.VerificacaoTipo
import br.unb.cic.mhs.ast.TErro

class ExpressaoSubtracaoTeste extends FlatSpec with Matchers {

  behavior of "avaliar expressao soma"

  "uma soma entre os valores 4 e 3" should "levar ao valor 1" in {
    val subtracao = new ExpressaoSubtracao(new ValorInteiro(4), new ValorInteiro(3));
    subtracao.avaliar().asInstanceOf[ValorInteiro].valor should be (1);
  }

  "uma soma entre valores 3 e true" should "levar a um erro de tipos" in {
    val subtracao = new ExpressaoSubtracao(new ValorInteiro(3), new ValorBooleano(true));
    
    new VerificacaoTipo().visitar(subtracao) should be (TErro)
  }

}