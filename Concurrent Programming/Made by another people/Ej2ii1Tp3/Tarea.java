package Ej2ii1Tp3;

import Matrix.DenseMatrix;
import Matrix.IMatrix;
import Matrix.Utils;
import Mult.IMultiplication;
import Mult.SimpleMultiplication;

public class Tarea implements Runnable {
	private IMatrix Ma=new DenseMatrix(1,1);
	private IMatrix Mb=new DenseMatrix(1,1);
	private IMultiplication Mul= new SimpleMultiplication();
	public Tarea(int a, int b) {
		super();
		this.Ma= Utils.generateDenseSquareMatrix(a);
		this.Mb= Utils.generateDenseSquareMatrix(b);
		this.Mul.multiply(Ma, Mb);
	}
	public void run() {
			int threadId= (int) Thread.currentThread().getId();
			int valor= (int) Utils.measureTime(this.Ma, this.Mb,this.Mul);
			System.out.println("Hola soy el Thread: " + threadId + " el valor es: " + valor);
		
	}

}
