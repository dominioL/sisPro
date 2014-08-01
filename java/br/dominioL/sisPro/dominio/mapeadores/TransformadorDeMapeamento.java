package br.dominioL.sisPro.dominio.mapeadores;

public interface TransformadorDeMapeamento<T> {
	public T transformar(T valor);
}
