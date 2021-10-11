package game.tetrominos;

import game.Grid;

public class TetrominoI extends Tetromino{
	
	/**
	 * Crea un nuevo tetromino de tipo I con una grilla.
	 */
	public TetrominoI(Grid grilla) {
		super(grilla);
		bloques[0] = miGrilla.getBloque(1, 3);
		bloques[1] = miGrilla.getBloque(1, 4);
		bloques[2] = miGrilla.getBloque(1, 5);
		bloques[3] = miGrilla.getBloque(1, 6);
		miRepresentacion = new TetrominoGrafico("cyan");
	}
	
	@Override
	protected void correrA() {
		this.corrimientos[0].setFyC(-1, 2);
		this.corrimientos[1].setFyC(0, 1);
		this.corrimientos[2].setFyC(1, 0);
		this.corrimientos[3].setFyC(2, -1);
	}
	
	@Override
	protected void correrB() {
		this.corrimientos[0].setFyC(2, 1);
		this.corrimientos[1].setFyC(1, 0);
		this.corrimientos[2].setFyC(0, -1);
		this.corrimientos[3].setFyC(-1, -2);
	}
	
	@Override
	protected void correrC() {
		this.corrimientos[0].setFyC(1, -2);
		this.corrimientos[1].setFyC(0, -1);
		this.corrimientos[2].setFyC(-1, 0);
		this.corrimientos[3].setFyC(-2, 1);
	}
	
	@Override
	protected void correrD() {
		this.corrimientos[0].setFyC(-2, -1);
		this.corrimientos[1].setFyC(-1, 0);
		this.corrimientos[2].setFyC(0, 1);
		this.corrimientos[3].setFyC(1, 2);
	}
}