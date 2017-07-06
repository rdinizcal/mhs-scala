package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast.{Aplicacao, ExpressaoDivisao, ExpressaoITE, ExpressaoLet, ExpressaoMultiplicacao, ExpressaoSoma, ExpressaoSubtracao, Referencia, ValorBooleano, ValorDouble, ValorInteiro}
import br.unb.cic.mhs.ast.ValorFuncao
import br.unb.cic.mhs.ast.ExpressaoLambda

class Metricas extends MHSVisitor[Int] {
  def visitar(e : ValorFuncao)   = 1
  def visitar(e : ValorInteiro)  = 1
  def visitar(e : ValorBooleano) = 1
  def visitar(e : ValorDouble)   = 1
  def visitar(e : ExpressaoDivisao) = 1 + e.lhs.aceitar(this) + e.rhs.aceitar(this)
  def visitar(e : ExpressaoMultiplicacao) = 1 + e.lhs.aceitar(this) + e.rhs.aceitar(this)
  def visitar(e : ExpressaoSoma) = 1 + e.lhs.aceitar(this) + e.rhs.aceitar(this)
  def visitar(e : ExpressaoSubtracao) = 1 + e.lhs.aceitar(this) + e.rhs.aceitar(this)
  def visitar(e : ExpressaoITE)  = 1 + e.condicao.aceitar(this) + e.clausulaThen.aceitar(this)  + e.clausulaElse.aceitar(this)
  def visitar(e : ExpressaoLambda) = 1 + e.corpo.avaliar.aceitar(this)
  def visitar(e : Aplicacao)     = 1 + e.args.map(e => e.aceitar(this)).sum
  def visitar(e : ExpressaoLet)  = 1 + e.expNomeada.aceitar(this) + e.corpo.aceitar(this)
  def visitar(e : Referencia)    = 1
}