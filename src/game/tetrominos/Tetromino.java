package game.tetrominos;

import game.Grid;

public abstract class Tetromino {
	
	protected TetrominoGrafico miRepresentacion;
	protected int rotacion;
	protected Bloque[] bloques;
	protected Grid miGrilla;
	protected String nombre;
	
	protected Tetromino(Grid grilla) {
		this.rotacion = 0;
		this.miGrilla = grilla;
		this.bloques = new Bloque[4];
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
	public abstract void rotarIzquierda();
	
	/**
	 * Rota el tetromino a la derecha.
	 */
	public abstract void rotarDerecha();
	
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
	 * Permite rotar el tetromino cambiando la posicion de cada bloque por otra.
	 * Si no se puede rotar retorna false, si se puede rotar, rota y retorna, true.
	 * @param f0 la fila del bloque 0.
	 * @param c0 la columna del bloque 0.
	 * @param f1 la fila del bloque 1.
	 * @param c1 la columna del bloque 1.
	 * @param f2 la fila del bloque 2.
	 * @param c2 la columna del bloque 2.
	 * @param f3 la fila del bloque 3.
	 * @param c3 la columna del bloque 3.
	 * @return true si pudo rotar, false si no pudo rotar.
	 */
	protected boolean rotar(int f0, int c0, int f1, int c1, int f2, int c2, int f3, int c3) { //son corrimientos
		//TODO añadir metodos auxiliares, reducir cantidad de parametros del metodo (usar pares)
		boolean libre = true;
		int f=0;	
		int c=0;
		Bloque aux = null;
		
		f=bloques[0].getFila()+f0;	
		c=bloques[0].getColumna()+c0;
		libre = libre && f>=0 && f<=20 && c>=0 && c<=9;
		if(libre)
			aux = miGrilla.getBloque(f, c);
		libre = libre && ((!aux.estaLibre() && estaEnBloques(aux, bloques)) || aux.estaLibre());
		
		f=bloques[1].getFila()+f1;	
		c=bloques[1].getColumna()+c1;
		libre = libre && f>=0 && f<=20 && c>=0 && c<=9;
		if(libre)
			aux = miGrilla.getBloque(f, c);
		libre = libre && ((!aux.estaLibre() && estaEnBloques(aux, bloques)) || aux.estaLibre());
		
		f=bloques[2].getFila()+f2;	
		c=bloques[2].getColumna()+c2;
		libre = libre && f>=0 && f<=20 && c>=0 && c<=9;
		if(libre)
			aux = miGrilla.getBloque(f, c);
		libre = libre && ((!aux.estaLibre() && estaEnBloques(aux, bloques)) || aux.estaLibre());
		
		f=bloques[3].getFila()+f3;	
		c=bloques[3].getColumna()+c3;
		libre = libre && f>=0 && f<=20 && c>=0 && c<=9;
		if(libre)
			aux = miGrilla.getBloque(f, c);
		libre = libre && ((!aux.estaLibre() && estaEnBloques(aux, bloques)) || aux.estaLibre());
		
		if(libre) {
			bloques[0].desocupar();
			bloques[1].desocupar();
			bloques[2].desocupar();
			bloques[3].desocupar();
			
			bloques[0] = miGrilla.getBloque(bloques[0].getFila()+f0, bloques[0].getColumna()+c0);
			bloques[1] = miGrilla.getBloque(bloques[1].getFila()+f1, bloques[1].getColumna()+c1);
			bloques[2] = miGrilla.getBloque(bloques[2].getFila()+f2, bloques[2].getColumna()+c2);
			bloques[3] = miGrilla.getBloque(bloques[3].getFila()+f3, bloques[3].getColumna()+c3);
			
			bloques[0].ocupar(miRepresentacion.getBloqueGrafico(0));
			bloques[1].ocupar(miRepresentacion.getBloqueGrafico(1));
			bloques[2].ocupar(miRepresentacion.getBloqueGrafico(2));
			bloques[3].ocupar(miRepresentacion.getBloqueGrafico(3));
		}
		return libre;
	}
	
//	private boolean estaLibre() {
//		int f=0;	
//		int c=0;
//		boolean libre=true;
//		for(int i=0; i<bloques.length; i++) {
//			f=bloques[i].getFila()+f0;	
//			c=bloques[i].getColumna()+c0;
//			libre = libre && f>=0 && f<=20 && c>=0 && c<=9;
//			if(libre)
//				aux = miGrilla.getBloque(f, c);
//			libre = libre && ((!aux.estaLibre() && estaEnBloques(aux, bloques)) || aux.estaLibre());
//		}
//		return libre;
//	}
	
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
