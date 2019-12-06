package Ej2ii2Tp3;


import Matrix.DenseMatrix;
import Matrix.IMatrix;
import Matrix.Utils;
import Ej2ii2Tp3.Multiplication;

public class Main {
	public static void main(String[] args) {
		
		IMatrix Ma= Utils.generateDenseSquareMatrix(3);
		IMatrix Mb=Utils.generateDenseSquareMatrix(3);
		IMatrix res= new DenseMatrix(1,1);
		Multiplication M = new Multiplication();
		res=M.multiply(Ma, Mb);
		Utils U=new Utils() ;
		boolean mult= U.verifyMultiplication(Ma, Mb, M, 1);
		System.out.println("verifica: "+mult);
		

	}
}
