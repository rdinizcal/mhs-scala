package br.unb.cic.mhs

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import br.unb.cic.mhs.ast.ExpressaoLambda
import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.ast.ExpressaoSoma
import br.unb.cic.mhs.ast.Referencia
import br.unb.cic.mhs.memoria.AmbienteExpressao

class ExpressaoLambdaTeste extends FlatSpec with Matchers {
  
  "uma expressao let x = 2 in \\x -> x + 1" should "levar ao valor 3" in {
    val refX = new Referencia("x")
    val lambda = new ExpressaoLambda(refX, new ExpressaoSoma(refX, ValorInteiro(1)))
    
    AmbienteExpressao.associar("x", ValorInteiro(2))  
    
    lambda.avaliar().asInstanceOf[ValorInteiro].valor should be (3)
  }
}