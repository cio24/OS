package Buffer;

public interface IBuffer<T> {
	/**
	 * Retorna el siguiente elemento
	 * se bloquea si no hay elementos
	 * @return
	 */
	public T next();
	/**
	 * Agrega un elemento
	 * se bloquea si no hay espacio para agregarlo
	 * @return
	 */
	public void add(T data);
	/**
	 * Retorna la cantidad de elementos
	 * @return
	 */
	public int size();
	/**
	 * Retorna la cantidad maxima de elementos
	 * @return
	 */
	public int maxElements();

}
