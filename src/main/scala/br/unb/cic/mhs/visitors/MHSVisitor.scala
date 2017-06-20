package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.ast.ValorBooleano
import br.unb.cic.mhs.ast.ExpressaoITE
import br.unb.cic.mhs.ast.ExpressaoSoma
import br.unb.cic.mhs.ast.Aplicacao
import br.unb.cic.mhs.ast.ExpressaoLet
import br.unb.cic.mhs.ast.Referencia

/**
 * Define a hierarquia de classes visitors. 
 * Precisa ter um metodo aceitar para cada 
 * classe concreta. 
 */
trait MHSVisitor {
  def visitar(e : ValorInteiro)  : Unit
  def visitar(e : ValorBooleano) : Unit
  def visitar(e : ExpressaoSoma) : Unit
  def visitar(e : ExpressaoITE)  : Unit
  def visitar(e : Aplicacao)     : Unit
  def visitar(e : ExpressaoLet)  : Unit
  def visitar(e : Referencia)    : Unit
}