package br.unb.cic.mhs

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import br.unb.cic.mhs.ast.ExpressaoDivisao
import br.unb.cic.mhs.exception.TypeException
import br.unb.cic.mhs.ast.ValorBooleano
import br.unb.cic.mhs.ast.ValorDouble
import br.unb.cic.mhs.ast.TErro
import br.unb.cic.mhs.visitors.VerificacaoTipo

class ExpressaoDivisaoTeste extends FlatSpec with Matchers {

  behavior of "avaliar expressao divisao"

  "uma Divisao entre os valores 4 e 2" should "levar ao valor 2" in {
    val divisao = new ExpressaoDivisao(new ValorDouble(4.0), new ValorDouble(2.0));
    divisao.avaliar().asInstanceOf[ValorDouble].valor should be (2.0);
  }

  "uma Divisao entre valores 3 e true" should "levar a um erro de tipos" in {
    val divisao = new ExpressaoDivisao(new ValorDouble(3.0), new ValorBooleano(true));
   
    new VerificacaoTipo().visitar(divisao) should be (TErro)
  }

}