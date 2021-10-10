package game.tetrominos;

import game.Grid;

public class TetrominoL extends Tetromino{
	
	/**
	 * Crea un nuevo tetromino de tipo J con una grilla.
	 */
	public TetrominoL(Grid grilla) {
		super(grilla);
		this.nombre="orange";
		bloques[0] = miGrilla.getBloque(1, 4);
		bloques[1] = miGrilla.getBloque(1, 5);
		bloques[2] = miGrilla.getBloque(1, 3);
		bloques[3] = miGrilla.getBloque(0, 3);
		miRepresentacion = new TetrominoGrafico(nombre);
	}
	
	@Override
	public void rotarDerecha() {
		boolean roto = false;
		switch(rotacion) {
		case 0: 
			roto = rotar(0, 0, 1, -1, -1, 1, 0, 2); //a
			break;
		case 1: 
			roto = rotar(0, 0, -1, -1, 1, 1, 2, 0); //b
			break;
		case 2: 
			roto = rotar(0, 0, -1, 1, 1, -1, 0, -2); //c
			break;
		case 3:
			roto = rotar(0, 0, 1, 1, -1, -1, -2, 0); //d
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
			roto = rotar(0, 0, -1, -1, 1, 1, 2, 0); //b
			break;
		case 1: 
			roto = rotar(0, 0, -1, 1, 1, -1, 0, -2); //c
			break;
		case 2: 
			roto = rotar(0, 0, 1, 1, -1, -1, -2, 0); //d
			break;
		case 3:
			roto = rotar(0, 0, 1, -1, -1, 1, 0, 2); //a
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
