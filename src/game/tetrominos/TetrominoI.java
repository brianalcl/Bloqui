package game.tetrominos;

import game.Grid;

public class TetrominoI extends Tetromino{
	
	/**
	 * Crea un nuevo tetromino de tipo I con una grilla.
	 */
	public TetrominoI(Grid grilla) {
		super(grilla);
		this.nombre="cyan";
		bloques[0] = miGrilla.getBloque(1, 3);
		bloques[1] = miGrilla.getBloque(1, 4);
		bloques[2] = miGrilla.getBloque(1, 5);
		bloques[3] = miGrilla.getBloque(1, 6);
	}
	
	@Override
	public void rotarDerecha() {
		boolean roto = false;
		switch(rotacion) {
		case 0: 
			roto = rotar(-1, 2, 0, 1, 1, 0, 2, -1); //a
			break;
		case 1: 
			roto = rotar(2, 1, 1, 0, 0, -1, -1, -2); //b
			break;
		case 2: 
			roto = rotar(1, -2, 0, -1, -1, 0, -2, 1); //c
			break;
		case 3:
			roto = rotar(-2, -1, -1, 0, 0, 1, 1, 2); //d
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
			roto = rotar(2, 1, 1, 0, 0, -1, -1, -2); //b
			break;
		case 1: 
			roto = rotar(1, -2, 0, -1, -1, 0, -2, 1); //c
			break;
		case 2: 
			roto = rotar(-2, -1, -1, 0, 0, 1, 1, 2); //d
			break;
		case 3:
			roto = rotar(-1, 2, 0, 1, 1, 0, 2, -1); //a
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