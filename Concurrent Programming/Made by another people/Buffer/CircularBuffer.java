package Buffer;

public class CircularBuffer<T> implements IBuffer<T> {

	private volatile Object[] elements;
	private volatile int posNext;
	private volatile int lastElem;
	/**
	 * Crea un buffer de 10 elementos
	 */
	public CircularBuffer(){
		this(10);
	}
	/**
	 * Crea un buffer de size elementos
	 * @param size
	 */
	public CircularBuffer(int size){
		this.elements = new Object[size];
		this.lastElem = 0;
		this.posNext = 0;
	}
	@Override
	public T next() {
		while(this.posNext==this.lastElem){
			//Hago nada... esperar
		}
		@SuppressWarnings("unchecked")
		T e = (T) this.elements[this.posNext];
		this.posNext=(this.posNext+1)%this.elements.length;
		return e;
	}

	@Override
	public void add(T data) {
		while(((this.posNext+1)%this.elements.length)==this.lastElem){
			//Hago nada...esperar
		}
		int p = this.lastElem;
		this.lastElem=(this.lastElem+1)%this.elements.length;
		this.elements[p] = data;
	}

	@Override
	public int size() {
		return (this.lastElem-this.posNext)%this.elements.length;
	}

	@Override
	public int maxElements() {
		return this.elements.length;
	}

}
