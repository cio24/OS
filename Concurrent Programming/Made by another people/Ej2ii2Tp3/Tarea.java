package Ej2ii2Tp3;

import Matrix.DenseMatrix;
import Matrix.IMatrix;


public class Tarea implements Runnable{
	private int fil;
	private IMatrix res = new DenseMatrix(1,1);
	private IMatrix a= new DenseMatrix(1,1);
	private IMatrix b= new DenseMatrix(1,1);


	
	public Tarea(int f, IMatrix res, IMatrix a, IMatrix b) {
		this.fil=f;
		this.res=res;
		this.a=a;
		this.b=b;
	}
	public void run() {
		// TODO Auto-generated method stub
		double mult;
		for(int i=0; i<b.getColumns();i++) {
			mult=0.0;
			for(int j=0; j<b.getRows();j++) {
				mult+= (double) this.a.get(fil, j)*this.b.get(j, i);
			}
			res.set(fil, i, mult);
		
		}
		
	}
	
}
