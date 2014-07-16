package br.dominioL.sisPro.dominio.mapeadores;

public interface Mapeador<T> {
	public T mapear(T mapeado);

	public Mapeador<T> renomearCampoPara(String nome, String novoNome);

	public Mapeador<T> incluirSomenteOsCampos(String... campos);

	public Mapeador<T> comCampoColecao(String campo, Mapeador<T> mapeador);

	public Mapeador<T> comCampoElemento(String campo, Mapeador<T> mapeador);

	public Mapeador<T> adicionarCampo(String nome, T valor);
}
