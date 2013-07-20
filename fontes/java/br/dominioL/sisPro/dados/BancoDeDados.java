package br.dominioL.sisPro.dados;

public interface BancoDeDados<R, Q> {
	public R adicionar(Q requisicao);

	public R colocar(Q requisicao);

	public R remover(Q requisicao);

	public R buscar(Q requisicao);

	public void popular();
}
