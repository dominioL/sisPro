package br.dominioL.sisPro.mapeadores.json;

public interface TransformadorDeMapeamentoJson<T> {
	public T transformar(T valor);
}
