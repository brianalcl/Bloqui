package game.tetrominos;

import game.Grid;

public class TetrominoJ extends Tetromino{
	
	/**
	 * Crea un nuevo tetromino de tipo J con una grilla.
	 */
	public TetrominoJ(Grid grilla) {
		super(grilla);
		bloques[0] = miGrilla.getBloque(1, 4);
		bloques[1] = miGrilla.getBloque(1, 5);
		bloques[2] = miGrilla.getBloque(1, 3);
		bloques[3] = miGrilla.getBloque(0, 3);
		miRepresentacion = new TetrominoGrafico("blue");
	}
	
	@Override
	protected void correrA() {
		this.corrimientos[0].setFyC(0, 0);
		this.corrimientos[1].setFyC(1, -1);
		this.corrimientos[2].setFyC(-1, 1);
		this.corrimientos[3].setFyC(0, 2);
	}
	
	@Override
	protected void correrB() {
		this.corrimientos[0].setFyC(0, 0);
		this.corrimientos[1].setFyC(-1, -1);
		this.corrimientos[2].setFyC(1, 1);
		this.corrimientos[3].setFyC(2, 0);
	}
	
	@Override
	protected void correrC() {
		this.corrimientos[0].setFyC(0, 0);
		this.corrimientos[1].setFyC(-1, 1);
		this.corrimientos[2].setFyC(1, -1);
		this.corrimientos[3].setFyC(0, -2);
	}
	
	@Override
	protected void correrD() {
		this.corrimientos[0].setFyC(0, 0);
		this.corrimientos[1].setFyC(1, 1);
		this.corrimientos[2].setFyC(-1, -1);
		this.corrimientos[3].setFyC(-2, 0);
	}
	
}
