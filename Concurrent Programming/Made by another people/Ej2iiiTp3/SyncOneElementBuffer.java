package Ej2iiiTp3;

import Buffer.IBuffer;

public class SyncOneElementBuffer implements IBuffer<T> {

	private T element;
	
	@Override
	public T next() {
		while(this.element==null){
			//Esperar
		}
		T res = this.element;
		this.element = null;
		return res;
	}

	@Override
	public void add(T data) {
		while(this.element!=null){
			//Esperar
		}
		this.element = data;
	}

	@Override
	public int size() {
		return this.element==null ? 0:1;
	}

	@Override
	public int maxElements() {
		return 1;
	}

}
