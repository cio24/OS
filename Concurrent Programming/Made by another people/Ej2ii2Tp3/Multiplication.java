package Ej2ii2Tp3;

import java.util.ArrayList;

import java.util.List;
import Ej2ii2Tp3.Tarea;
import Matrix.IMatrix;
import Mult.IMultiplication;

public class Multiplication implements IMultiplication {

	public IMatrix multiply(IMatrix a, IMatrix b) {
		if (a.getColumns()!=b.getRows())
			throw new RuntimeException("La cantidad de columnas de la matriz a tiene que ser igual a la cantidad de filas de la matriz b");
		IMatrix res = a.createMatrix(a.getRows(), b.getColumns());
		List<Thread> Columnas= new ArrayList<Thread>(a.getRows());	//reserva espacio, no lo asigna: tamaño:0	
		//bucle de creacion de threads para agregarlos a la lista
		for(int i=0; i<a.getRows() ; i++) {
			
				Thread t = new Thread(new Tarea(i, res, a, b), "T"+i); //instancias creadas 
				Columnas.add(t);//agrega al final
				Columnas.get(i).start();
			//decirle que parte del calculo se va encargar
				//puntero de la matrix resultado donde va a meter ese thread
		//Por cada fila de A
		}
		try {
			for(int i=0;i<Columnas.size();i++) {
				Columnas.get(i).join();
			}}
			catch (InterruptedException e){
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return res;
	}
	
		
	
}
