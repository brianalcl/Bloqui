package game.tetrominos;

import game.Grid;

public class TetrominoS extends Tetromino{
	
	/**
	 * Crea un nuevo tetromino de tipo S con una grilla.
	 */
	public TetrominoS(Grid grilla) {
		super(grilla);
		bloques[0] = miGrilla.getBloque(1, 4);
		bloques[1] = miGrilla.getBloque(1, 3);
		bloques[2] = miGrilla.getBloque(0, 4);
		bloques[3] = miGrilla.getBloque(0, 5);
		miRepresentacion = new TetrominoGrafico("green");
	}
	
	@Override
	protected void correrA() {
		this.corrimientos[0].setFyC(0, 0);
		this.corrimientos[1].setFyC(-1, 1);
		this.corrimientos[2].setFyC(1, 1);
		this.corrimientos[3].setFyC(2, 0);
	}
	
	@Override
	protected void correrB() {
		this.corrimientos[0].setFyC(0, 0);
		this.corrimientos[1].setFyC(1, 1);
		this.corrimientos[2].setFyC(1, -1);
		this.corrimientos[3].setFyC(0, -2);
	}
	
	@Override
	protected void correrC() {
		this.corrimientos[0].setFyC(0, 0);
		this.corrimientos[1].setFyC(1, -1);
		this.corrimientos[2].setFyC(-1, -1);
		this.corrimientos[3].setFyC(-2, 0);
	}
	
	@Override
	protected void correrD() {
		this.corrimientos[0].setFyC(0, 0);
		this.corrimientos[1].setFyC(-1, -1);
		this.corrimientos[2].setFyC(-1, 1);
		this.corrimientos[3].setFyC(0, 2);
	}
	
}
