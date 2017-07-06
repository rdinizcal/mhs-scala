package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast.{Aplicacao, Expressao, ExpressaoDivisao, ExpressaoITE, ExpressaoLet, ExpressaoMultiplicacao, ExpressaoSoma, ExpressaoSubtracao, Referencia, ValorBooleano, ValorDouble, ValorInteiro}

/**
 * Classe abstrata com a implementacao de um MHSVisitor 
 * para transformacoes que preservam a AST. Classes concretas 
 * desta classe abstrata prcisam redefinir apenas os metodos 
 * de interesse. 
 */
trait TransformacaoG extends MHSVisitor[Expressao] {
  override def visitar(vb : ValorBooleano)  : Expressao =  vb
  override def visitar(vi : ValorInteiro)   : Expressao =  vi
  override def visitar(vd : ValorDouble)   : Expressao =  vd
  override def visitar(e : ExpressaoSoma)   : Expressao = new ExpressaoSoma(e.lhs.aceitar(this),e.rhs.aceitar(this))
  override def visitar(e : ExpressaoSubtracao)   : Expressao = new ExpressaoSubtracao(e.lhs.aceitar(this),e.rhs.aceitar(this))
  override def visitar(e : ExpressaoDivisao) : Expressao = new ExpressaoDivisao(e.lhs.aceitar(this),e.rhs.aceitar(this))
  override def visitar(e : ExpressaoMultiplicacao)   : Expressao = new ExpressaoMultiplicacao(e.lhs.aceitar(this),e.rhs.aceitar(this))
  override def visitar(e : ExpressaoITE)    : Expressao = new ExpressaoITE(e.condicao.aceitar(this), e.clausulaThen.aceitar(this), e.clausulaElse.aceitar(this))
  override def visitar(e : Aplicacao)       : Expressao = new Aplicacao(e.nome, e.args.map(exp => exp.aceitar(this)) : _*)
  override def visitar(e : ExpressaoLet)    : Expressao = new ExpressaoLet(e.id, e.expNomeada.aceitar(this), e.corpo.aceitar(this))
  override def visitar(e : Referencia)      : Expressao = e
}