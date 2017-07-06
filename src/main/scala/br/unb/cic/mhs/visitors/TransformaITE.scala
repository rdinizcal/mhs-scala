package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast.{ExpressaoITE, ValorBooleano}

 class TransformaITE extends TransformacaoG {
  override def visitar(e : ExpressaoITE)  = {
    val condicao = e.condicao.aceitar(this)
    val tupla = (e.clausulaThen, e.clausulaElse)
    tupla match {
      case (ValorBooleano(true), ValorBooleano(false)) => condicao
      case _ => new ExpressaoITE(condicao, e.clausulaThen.aceitar(this), e.clausulaElse.aceitar(this))
    }
  }
}