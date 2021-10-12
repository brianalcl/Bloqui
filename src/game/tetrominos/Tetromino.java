package game.tetrominos;

import game.Grid;
import utilidades.Pair;

public abstract class Tetromino {
	
	protected TetrominoGrafico miRepresentacion;
	protected int rotacion;
	protected Bloque[] bloques;
	protected Grid miGrilla;
	protected Pair<Integer, Integer>[] corrimientos;
	
	protected Tetromino(Grid grilla) {
		this.rotacion = 0;
		this.miGrilla = grilla;
		this.bloques = new Bloque[4];
		this.corrimientos = new Pair[4];
		for(int i = 0; i < corrimientos.length; i++) {
			corrimientos[i] = new Pair<Integer, Integer>();
		}
	}
	
	/**
	 * Permite que el tetromino se genere, si este no tiene espacio retornara false de lo contrario retornara true y se generara.
	 * @return true si puede aparecer, false caso contrario.
	 */
	public boolean aparecer() {
		
		boolean sePuedeGenerar = true;
		
		for(int i=0; i<bloques.length && sePuedeGenerar; i++) {
			sePuedeGenerar = bloques[i].estaLibre();
		}
		
		if (sePuedeGenerar)
			for (int i = 0; i < bloques.length; i++) 
				bloques[i].ocupar(miRepresentacion.getBloqueGrafico(i));
		
		return sePuedeGenerar;
	}
	
	/**
	 * Retorna un tetromino grafico
	 * @return un tetromino grafico
	 */
	public TetrominoGrafico getTetrominoGrafico() {
		return miRepresentacion;
	}
	
	/**
	 * Mueve el tetromino a la izquierda.
	 * 
	 */
	public void moverIzquierda() {
		boolean sePuede = true;
		Pair<Integer, Integer> corrimiento = new Pair<Integer, Integer>(0, -1);
		
		for(int i = 0; sePuede && i < bloques.length; i++) {
			sePuede = verificarMovimiento(bloques[i], corrimiento);
		}
		
		if(sePuede) {
			desocuparTodos();
			for(int i = 0; i < bloques.length; i++) {
				mover(bloques[i], corrimiento, i);
			}
		}
	}

	/**
	 * Mueve el tetromino a la derecha
	 * 
	 */
	public void moverDerecha() {
		boolean sePuede = true;
		Pair<Integer, Integer> corrimiento = new Pair<Integer, Integer>(0, 1);
		
		for(int i = 0; sePuede && i < bloques.length; i++) {
			sePuede = verificarMovimiento(bloques[i], corrimiento);
		}
		
		if(sePuede) {
			desocuparTodos();
			for(int i = 0; i < bloques.length; i++) {
				mover(bloques[i], corrimiento, i);
			}
		}
	}
	
	/**
	 * Baja el tetromino en una posicion y retorna false si bajo con exito,
	 * si choco retorna true.
	 * @return false si no choco, true si choco.
	 */
	public boolean bajar() {
		boolean choco = false;
		boolean sePuede = true;
		Pair<Integer, Integer> corrimiento = new Pair<Integer, Integer>(1, 0);
		
		for(int i = 0; sePuede && i < bloques.length; i++) {
			sePuede = verificarMovimiento(bloques[i], corrimiento);
		}
		
		if(sePuede) {
			desocuparTodos();
			for(int i = 0; i < bloques.length; i++) {
				mover(bloques[i], corrimiento, i);
			}
		}
		else {
			choco = true;
		}
		return choco;
	}
	
	/**
	 * Rota el tetromino a la izquierda.
	 */
	public void rotarIzquierda() {
		rotacion = (rotacion + 1) % 4;
		
		boolean roto = correrYRotar();
		
		rotacion = (rotacion + 2) % 4;
		
		if(roto) {
			miRepresentacion.rotarIzquierda();
		}
		else
			rotacion = (rotacion + 1) % 4;
	}
	
	/**
	 * Rota el tetromino a la derecha.
	 */
	public void rotarDerecha() {
		boolean roto = correrYRotar();
		
		rotacion = (rotacion + 1) % 4;
		
		if(roto) {
			miRepresentacion.rotarDerecha();
		}
		else
			rotacion = (rotacion + 3) % 4;
	}
	
	/**
	 * Mueve el tetromino en una direccion
	 * @param f la coordenada del corrimiento en filas
	 * @param c la coordenada del corrimiento en columnas
	 */
	private void mover(Bloque bloque, Pair<Integer, Integer> corrimiento, int pos) {
		int f = corrimiento.getF();
		int c = corrimiento.getC();
		bloque = miGrilla.getBloque(bloque.getFila() + f, bloque.getColumna() + c);
		bloque.ocupar(miRepresentacion.getBloqueGrafico(pos));
		bloques[pos] = bloque;
	}
	
	/**
	 * Desocupa todos los bloques del tetromino
	 */
	private void desocuparTodos() {
		for(int i = 0; i < bloques.length; i++)
			bloques[i].desocupar();
	}
	
	/**
	 * Permite rotar el tetromino.
	 * Si no se puede rotar retorna false, si se puede rotar, rota y retorna, true.
	 */
	private boolean rotar() {
		boolean libre = true;
		
		for(int i = 0; libre && i < bloques.length; i++) {
			libre = verificarMovimiento(bloques[i], corrimientos[i]);
		}
		
		if(libre) {
			desocuparTodos();
			for(int i = 0; i < bloques.length; i++) {
				mover(bloques[i], corrimientos[i], i);
			}
		}
		
		return libre;
	}
	
	/**
	 * Verifica si el movimiento en una direccion dada es posible
	 * @param f la coordenada de corrimiento en fila
	 * @param c la coordenada de movimiento en columna
	 * @return true si se puede mover falso en caso contrario
	 */
	private boolean verificarMovimiento(Bloque bloque, Pair<Integer, Integer> corrimiento) {
		Bloque bloqueAdya = null;
		int columna = 0;
		int fila = 0;
		boolean sePuede = true;
			
		fila = bloque.getFila() + corrimiento.getF();
		columna = bloque.getColumna() + corrimiento.getC();
		if(columna >= 0 && columna <=9 && fila>=0 && fila<=20) {
			bloqueAdya = miGrilla.getBloque(fila, columna);
			sePuede = (!bloqueAdya.estaLibre() && estaEnBloques(bloqueAdya, bloques)) || bloqueAdya.estaLibre();
		}
		else
			sePuede = false;
		return sePuede;
	}
	
	/**
	 * Retorna true si el bloque b esta dentro de la estructura de bloques
	 * @param b un bloque
	 * @param bloques un arreglo de bloques
	 * @return true si b esta dentro de la estructura de bloques o false si no lo esta.
	 */
	private boolean estaEnBloques(Bloque b, Bloque[] bloques) {
		boolean esta = false;
		for(int i = 0; i < bloques.length && !esta; i++) {
			esta = b.equals(bloques[i]);
		}
		return esta;
	}
	
	
	/**
	 * Rota el tetromino girandolo 90° dependiendo del nivel de rotacion que este tenga actualmente.
	 * @return
	 */
	private boolean correrYRotar() {
		boolean roto = false;
		switch(rotacion) {
		case 0:
			correrA();
			break;
		case 1: 
			correrB();
			break;
		case 2: 
			correrC();
			break;
		case 3:
			correrD();
			break;
		}
		roto = rotar();
		return roto;
	}
	
	/**
	 * Establece los corrimientos de los bloques en la primer fase de rotacion girando a derecha 90°.
	 */
	protected abstract void correrA();
	
	/**
	 * Establece los corrimientos de los bloques en la segunda fase de rotacion girando a derecha 90°.
	 */
	protected abstract void correrB();
	
	/**
	 * Establece los corrimientos de los bloques en la tercer fase de rotacion girando a derecha 90°.
	 */
	protected abstract void correrC();
	
	/**
	 * Establece los corrimientos de los bloques en la cuarta fase de rotacion girando a derecha 90°.
	 */
	protected abstract void correrD();
	
}
