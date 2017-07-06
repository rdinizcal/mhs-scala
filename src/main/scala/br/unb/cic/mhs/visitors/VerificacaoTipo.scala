package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast.Tipo
import br.unb.cic.mhs.ast.ExpressaoLet
import br.unb.cic.mhs.ast.Referencia
import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.ast.TInteiro
import br.unb.cic.mhs.ast.Aplicacao
import br.unb.cic.mhs.ast.TBooleano
import br.unb.cic.mhs.ast.ValorBooleano
import br.unb.cic.mhs.ast.ExpressaoSoma
import br.unb.cic.mhs.ast.ExpressaoITE
import br.unb.cic.mhs.ast.TErro
import br.unb.cic.mhs.memoria.AmbienteExpressao
import br.unb.cic.mhs.memoria.AmbienteDecFuncao
import br.unb.cic.mhs.ast.ValorDouble
import br.unb.cic.mhs.ast.TDouble
import br.unb.cic.mhs.ast.ExpressaoDivisao
import br.unb.cic.mhs.ast.ExpressaoSubtracao
import br.unb.cic.mhs.ast.ExpressaoMultiplicacao

class VerificacaoTipo extends MHSVisitor[Tipo] {
	
  override def visitar(vb : ValorBooleano)  : Tipo = TBooleano
	override def visitar(vi : ValorInteiro)   : Tipo = TInteiro
	override def visitar(vd : ValorDouble)    : Tipo = TDouble
	override def visitar(e : ExpressaoDivisao): Tipo = if(e.lhs.aceitar(this) == e.rhs.aceitar(this)) e.rhs.aceitar(this) else TErro
  override def visitar(e : ExpressaoMultiplicacao) : Tipo = if(e.lhs.aceitar(this) == e.rhs.aceitar(this)) e.rhs.aceitar(this) else TErro
	override def visitar(e : ExpressaoSoma)   : Tipo = if(e.lhs.aceitar(this) == e.rhs.aceitar(this)) e.rhs.aceitar(this) else TErro
	override def visitar(e : ExpressaoSubtracao)  : Tipo = if(e.lhs.aceitar(this) == e.rhs.aceitar(this)) e.rhs.aceitar(this) else TErro
	override def visitar(e : ExpressaoITE)    : Tipo = if(e.condicao.aceitar(this).equals(TBooleano) && e.clausulaThen.aceitar(this) == e.clausulaElse.aceitar(this)) e.clausulaThen.aceitar(this) else TErro
	override def visitar(e : Aplicacao)       : Tipo = if(e.args.apply(0).aceitar(this) != AmbienteDecFuncao.pesquisar(e.nome).tipo) TErro else AmbienteDecFuncao.pesquisar(e.nome).corpo.aceitar(this)
	override def visitar(e : ExpressaoLet)    : Tipo = if(e.expNomeada.aceitar(this).equals(TErro)) TErro else e.corpo.aceitar(this)
	override def visitar(e : Referencia)      : Tipo = if(AmbienteExpressao.pesquisar(e.id) != null) AmbienteExpressao.pesquisar(e.id).aceitar(this) else TErro
	
}
