package game.tetrominos;

import game.Grid;

public class TetrominoZ extends Tetromino{
	
	/**
	 * Crea un nuevo tetromino de tipo Z con una grilla.
	 */
	public TetrominoZ(Grid grilla) {
		super(grilla);
		bloques[0] = miGrilla.getBloque(1, 4);
		bloques[1] = miGrilla.getBloque(0, 3);
		bloques[2] = miGrilla.getBloque(0, 4);
		bloques[3] = miGrilla.getBloque(1, 5);
		miRepresentacion = new TetrominoGrafico("red");
	}
	
	@Override
	protected void correrA() {
		this.corrimientos[0].setFyC(0, 0);
		this.corrimientos[1].setFyC(0, 2);
		this.corrimientos[2].setFyC(1, 1);
		this.corrimientos[3].setFyC(1, -1);
	}
	
	@Override
	protected void correrB() {
		this.corrimientos[0].setFyC(0, 0);
		this.corrimientos[1].setFyC(2, 0);
		this.corrimientos[2].setFyC(1, -1);
		this.corrimientos[3].setFyC(-1, -1);
	}
	
	@Override
	protected void correrC() {
		this.corrimientos[0].setFyC(0, 0);
		this.corrimientos[1].setFyC(0, -2);
		this.corrimientos[2].setFyC(-1, -1);
		this.corrimientos[3].setFyC(-1, 1);
	}
	
	@Override
	protected void correrD() {
		this.corrimientos[0].setFyC(0, 0);
		this.corrimientos[1].setFyC(-2, 0);
		this.corrimientos[2].setFyC(-1, 1);
		this.corrimientos[3].setFyC(1, 1);
	}
}
