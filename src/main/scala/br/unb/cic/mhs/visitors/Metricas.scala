package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.ast.ValorBooleano
import br.unb.cic.mhs.ast.ExpressaoSoma
import br.unb.cic.mhs.ast.ExpressaoITE
import br.unb.cic.mhs.ast.Aplicacao
import br.unb.cic.mhs.ast.ExpressaoLet
import br.unb.cic.mhs.ast.Referencia

class Metricas extends MHSVisitor {
  var total : Int = 0
  
  def visitar(e : ValorInteiro)  : Unit = total += 1
  
  def visitar(e : ValorBooleano) : Unit = total += 1
 
  def visitar(e : ExpressaoSoma) : Unit = {
    e.lhs.aceitar(this)
    e.rhs.aceitar(this)
    total += 1
  }
   
  def visitar(e : ExpressaoITE)  : Unit = {
    e.condicao.aceitar(this)
    e.clausulaThen.aceitar(this)
    e.clausulaElse.aceitar(this)
    total += 1
  }
  
  def visitar(e : Aplicacao) : Unit = {
    for(a <- e.args) {
      a.aceitar(this)
    }
    total += 1 
  }
  def visitar(e : ExpressaoLet)  : Unit = {
    e.expNomeada.aceitar(this)
    e.corpo.aceitar(this)
    total += 1
  }
  
  def visitar(e : Referencia)    : Unit = total += 1 
  
}