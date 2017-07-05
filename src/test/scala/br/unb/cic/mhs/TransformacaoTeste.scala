package br.unb.cic.mhs

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import br.unb.cic.mhs.ast.ExpressaoITE
import br.unb.cic.mhs.ast.ValorBooleano
import br.unb.cic.mhs.ast.ExpressaoSoma
import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.visitors.TransformaITE

class TransformacaoTeste extends FlatSpec with Matchers {
  
  "uma expressao if(2 + 1) then true else false" should "transformar em 2 + 1" in {
    val val2 = new ValorInteiro(2)
    val val1 = new ValorInteiro(1)
    val soma = new ExpressaoSoma(val2, val1)
    val ite = new ExpressaoITE(soma, new ValorBooleano(true), new ValorBooleano(false))
    
    val t = new TransformaITE()
    ite.aceitar(t) should be (soma)
  }
  
}