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
	
	public TetrominoGrafico getTetrominoGrafico() {
		return miRepresentacion;
	}
	
	/**
	 * Mueve el tetromino a la izquierda.
	 * 
	 */
	public void moverIzquierda() {
		mover(0, -1);
	}

	/**
	 * Mueve el tetromino a la derecha
	 * 
	 */
	public void moverDerecha() {
		mover(0, 1);
	}
	
	/**
	 * Baja el tetromino en una posicion y retorna false si bajo con exito,
	 * si choco retorna true.
	 * @return false si no choco, true si choco.
	 */
	public boolean bajar() {
		boolean choco = false;
		Bloque bloqueAct = null;
		Bloque bloqueAdya = null;
		int columna = 0;
		int fila = 0;
		boolean sePuede = true;
		
		for(int i=0; i<bloques.length && sePuede; i++) {
			bloqueAct = bloques[i];
			columna = bloqueAct.getColumna();
			fila = bloqueAct.getFila()+1;
			if(fila <= 20) {
				bloqueAdya = miGrilla.getBloque(fila, columna);
				sePuede = (!bloqueAdya.estaLibre() && estaEnBloques(bloqueAdya, bloques)) || bloqueAdya.estaLibre();
			}
			else {
				sePuede = false;
			}
		}
		
		if(sePuede) {
			for(int i=0; i<bloques.length; i++) {
				bloqueAct = bloques[i];
				bloqueAct.desocupar();
			}
			for(int i=0; i<bloques.length; i++) {
				bloqueAct = bloques[i];
				bloqueAct = miGrilla.getBloque(bloqueAct.getFila()+1, bloqueAct.getColumna());
				BloqueGrafico bg= miRepresentacion.getBloqueGrafico(i);
				bloqueAct.ocupar(bg);
				bloques[i] = bloqueAct;
			}
		}
		else {
			choco = true;
		}
		return choco;
	}

	private void mover(int f, int c) {
		Bloque bloqueAct = null;
		Bloque bloqueAdya = null;
		int columna = 0;
		int fila = 0;
		boolean sePuede = true;
		
		for(int i=0; i<bloques.length && sePuede; i++) {
			bloqueAct = bloques[i];
			fila = bloqueAct.getFila()+f;
			columna = bloqueAct.getColumna()+c;
			if(columna >= 0 && columna <=9 && fila>=0 && fila<=20) {
				bloqueAdya = miGrilla.getBloque(fila, columna);
				sePuede = (!bloqueAdya.estaLibre() && estaEnBloques(bloqueAdya, bloques)) || bloqueAdya.estaLibre();
			}
			else
				sePuede = false;
		}
		
		if(sePuede) {
			for(int i=0; i<bloques.length; i++) {
				bloqueAct = bloques[i];
				bloqueAct.desocupar();
				
			}
			for(int i=0; i<bloques.length; i++) {
				bloqueAct = bloques[i];
				bloqueAct = miGrilla.getBloque(bloqueAct.getFila()+f, bloqueAct.getColumna()+c);
				BloqueGrafico bg= miRepresentacion.getBloqueGrafico(i);
				bloqueAct.ocupar(bg);
				bloques[i] = bloqueAct;
			}
		}
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
	 * Establece los corrimientos de los bloques en la primer fase de rotacion
	 */
	protected abstract void correrA();
	
	/**
	 * Establece los corrimientos de los bloques en la segunda fase de rotacion
	 */
	protected abstract void correrB();
	
	/**
	 * Establece los corrimientos de los bloques en la tercer fase de rotacion
	 */
	protected abstract void correrC();
	
	/**
	 * Establece los corrimientos de los bloques en la cuarta fase de rotacion
	 */
	protected abstract void correrD();
	
	/**
	 * Permite que el tetromino se genere, si este no tiene espacio retornara false de lo contrario retornara true y se generara.
	 * @return
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
	 * Permite rotar el tetromino.
	 * Si no se puede rotar retorna false, si se puede rotar, rota y retorna, true.
	 */
	protected boolean rotar() {
		boolean libre = true;
		int f=0;	
		int c=0;
		
		libre = verificarRotacion();
		
		for(int i = 0; libre && i < bloques.length; i++) {
			bloques[i].desocupar();
		}
		
		for(int i = 0; libre && i < bloques.length; i++) {
			f = corrimientos[i].getF();
			c = corrimientos[i].getC();
			bloques[i] = miGrilla.getBloque(bloques[i].getFila() + f, bloques[i].getColumna() + c);
			bloques[i].ocupar(miRepresentacion.getBloqueGrafico(i));
		}
		
		return libre;
	}
	
	/**
	 * Controla si es posible o no rotar el tetromino
	 * @return true si se puede rotar false si no se puede rotar
	 */
	private boolean verificarRotacion() {
		boolean libre = true;
		int f = 0;
		int c = 0;
		Bloque aux = null;
		for(int i = 0; libre && i < bloques.length; i++) {
			f = bloques[i].getFila() + corrimientos[i].getF();	
			c = bloques[i].getColumna() + corrimientos[i].getC();
			libre = libre && f>=0 && f<=20 && c>=0 && c<=9;
			if(libre)
				aux = miGrilla.getBloque(f, c);
			libre = libre && ((!aux.estaLibre() && estaEnBloques(aux, bloques)) || aux.estaLibre());
		}
		return libre;
	}
	
	/**
	 * Retorna true si el bloque b esta dentro de la estructura de bloques
	 * @param b un bloque
	 * @param bloques un arreglo de bloques
	 * @return true si b esta dentro de la estructura de bloques o false si no lo esta.
	 */
	protected boolean estaEnBloques(Bloque b, Bloque[] bloques) {
		boolean esta = false;
		for(int i=0; i<bloques.length && !esta; i++) {
			esta = b.equals(bloques[i]);
		}
		return esta;
	}
	
}
