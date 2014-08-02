package br.dominioL.sisPro.mapeadores.json;

public interface TransformadorDeMapeamento<T> {
	public T transformar(T valor);
}
