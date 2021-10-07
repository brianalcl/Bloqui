package game.tetrominos;

import game.Grid;

public class Tetromino_S extends Tetromino{
	
	/**
	 * Crea un nuevo tetromino de tipo S con una grilla.
	 */
	public Tetromino_S(Grid grilla) {
		super(grilla);
		this.nombre="green";
	}
	
	@Override
	public boolean aparecer() {
		bloques[0] = miGrilla.getBloque(1, 4);
		bloques[1] = miGrilla.getBloque(1, 3);
		bloques[2] = miGrilla.getBloque(0, 4);
		bloques[3] = miGrilla.getBloque(0, 5);
		
		boolean sePuedeGenerar = true;
		
		miRepresentacion = new TetrominoGrafico(nombre, 0);
		
		for(int i=0; i<bloques.length && sePuedeGenerar; i++) {
			sePuedeGenerar = bloques[i].estaLibre();
		}
		
		if (sePuedeGenerar)
			for (int i = 0; i < bloques.length; i++) 
				bloques[i].ocupar(miRepresentacion.getBloqueGrafico(i));
		
		return sePuedeGenerar;
	}
	
	@Override
	public void rotarDerecha() {
		boolean roto = false;
		switch(rotacion) {
		case 0: 
			roto = rotar(0, 0, -1, 1, 1, 1, 2, 0); //a
			break;
		case 1: 
			roto = rotar(0, 0, 1, 1, 1, -1, 0, -2); //b
			break;
		case 2: 
			roto = rotar(0, 0, 1, -1, -1, -1, -2, 0); //c
			break;
		case 3:
			roto = rotar(0, 0, -1, -1, -1, 1, 0, 2); //d
			break;
		}
		if(roto) {
			rotacion = (rotacion +1)%4;
			miRepresentacion.rotarDerecha();
		}
	}
	
	@Override
	public void rotarIzquierda() {
		boolean roto = false;
		switch(rotacion) {
		case 0: //
			roto = rotar(0, 0, 1, 1, 1, -1, 0, -2); //b
			break;
		case 1: 
			roto = rotar(0, 0, 1, -1, -1, -1, -2, 0); //c
			break;
		case 2: 
			roto = rotar(0, 0, -1, -1, -1, 1, 0, 2); //d
			break;
		case 3:
			roto = rotar(0, 0, -1, 1, 1, 1, 2, 0); //a
			break;
		}
		if(roto) {
			if(rotacion != 0)
				rotacion = (rotacion - 1) % 4;
			else
				rotacion = 3;
			miRepresentacion.rotarIzquierda();
		}
	}
	
}
