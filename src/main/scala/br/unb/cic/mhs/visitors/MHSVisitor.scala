package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast.{Aplicacao, ExpressaoITE, ExpressaoLet, ExpressaoMultiplicacao, ExpressaoSoma, Referencia, ValorBooleano, ValorInteiro}

/**
 * Define a hierarquia de classes visitors. 
 * Precisa ter um metodo aceitar para cada 
 * classe concreta. 
 */
trait MHSVisitor[+T] {
  def visitar(e : ValorInteiro)  : T
  def visitar(e : ValorBooleano) : T
  def visitar(e : ExpressaoSoma) : T
  def visitar(e : ExpressaoMultiplicacao) : T
  def visitar(e : ExpressaoITE)  : T
  def visitar(e : Aplicacao)     : T
  def visitar(e : ExpressaoLet)  : T
  def visitar(e : Referencia)    : T
}