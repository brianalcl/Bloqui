package game.tetrominos;

import game.Grid;

public class Bloque {

	private int fila;
	private int columna;
	private boolean estaLibre;
	private Grid miGrilla;
	private BloqueGrafico miRepresentacion;
	
	/**
	 * Crea un nuevo bloque con coordanadas (fila, columna) y una grilla. 
	 * @param fila la fila a la que va a pertenecer el bloque.
	 * @param columna la columna a la que va a pertenecer el bloque.
	 * @param grilla la grilla a la que va a pertenecer el bloque.
	 */
	public Bloque(int fila, int columna, Grid grilla) {
		this.fila = fila;
		this.columna = columna;
		this.estaLibre = true;
		this.miGrilla = grilla;
	}
	
	/**
	 * Retorna la reprecentacion grafica del bloque.
	 * @return un bloque grafico
	 */
	public BloqueGrafico getBloqueGrafico() {
		return miRepresentacion;
	}
	
	/**
	 * Retorna true si el bloque esta libre y false en caso contrario.
	 * @return
	 */
	public boolean estaLibre() {
		return estaLibre;
	}
	
	/**
	 * Ocupa el bloque.
	 * @param bg reprecentacion para este bloque
	 */
	public void ocupar(BloqueGrafico bg) {
		estaLibre=false;
		miRepresentacion = bg;
		miGrilla.cambioBloque(this);
	}
	
	/**
	 * Desocupa el bloque.
	 */
	public void desocupar() {
		estaLibre = true;
		miRepresentacion = miGrilla.getBloqueLibre();
		miGrilla.cambioBloque(this);
	}
	
	/**
	 * Retorna la columna a la que pertenece el bloque.
	 * @return la columna a la que pertenece el bloque.
	 */
	public int getColumna() {
		return this.columna;
	}
	
	/**
	 * retorna la fila a la que pertenece el bloque.
	 * @return la fila a la que pertenece el bloque.
	 */
	public int getFila() {
		return this.fila;
	}

}
