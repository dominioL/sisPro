package br.dominioL.sisPro.dados;

public interface BancoDeDados<R, Q> {
	public R inserir(Q requisicao);

	public R colocar(Q requisicao);

	public R remover(Q requisicao);

	public R obter(Q requisicao);

	public void popular();
}
