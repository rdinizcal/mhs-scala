package br.unb.cic.mhs



import org.scalatest.FlatSpec
import org.scalatest.Matchers

import br.unb.cic.mhs.ast.Aplicacao
import br.unb.cic.mhs.ast.DecFuncao
import br.unb.cic.mhs.ast.ExpressaoLet
import br.unb.cic.mhs.ast.ExpressaoSoma
import br.unb.cic.mhs.ast.Referencia
import br.unb.cic.mhs.ast.TErro
import br.unb.cic.mhs.ast.TInteiro
import br.unb.cic.mhs.ast.ValorBooleano
import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.memoria.AmbienteDecFuncao
import br.unb.cic.mhs.visitors.VerificacaoTipo

class ExpressaoAplicacaoTest extends FlatSpec with Matchers {
  
  "uma aplicacao inc 4 (onde existe int inc x = x + 1) " should " levar ao valor 5" in {
    val inc = new DecFuncao(TInteiro, "inc", List("x"), new ExpressaoSoma(new Referencia("x"), new ValorInteiro(1)))  
    val app = new Aplicacao("inc", new ValorInteiro(4))
    
    AmbienteDecFuncao.associar("inc", inc)
    
    app.avaliar().asInstanceOf[ValorInteiro].valor should be (5)
  }
  
  //TODO: isso eh uma aberracao, pois tem a semantica de scopo dinamico. 
  "supondo (int def f y = x + y), e avaliamos let x = 10 in f 5 " should " levar ao 15" in {
    val refX = new Referencia("x") 
    val f    = new DecFuncao(TInteiro, "f", List("y"), new ExpressaoSoma(refX, new Referencia("y")))
    val let  = new ExpressaoLet("x", new ValorInteiro(10), new Aplicacao("f", new ValorInteiro(5)))    
 
    AmbienteDecFuncao.associar("f", f)
    
    let.avaliar().asInstanceOf[ValorInteiro].valor should be (15)
  }
  
  "uma aplicacao inc true (onde existe inc x = x + 1) " should " levar a um erro de tipo" in {
    val inc = new DecFuncao(TInteiro, "inc", List("x"), new ExpressaoSoma(new Referencia("x"), new ValorInteiro(1)))  
    val app = new Aplicacao("inc", new ValorBooleano(true))
    
    AmbienteDecFuncao.associar("inc", inc)
    
    new VerificacaoTipo().visitar(app) should be (TErro)
  }
}