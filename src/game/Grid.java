package game;

import game.tetrominos.*;

public class Grid {
	
	private Juego miJuego;
	private Bloque[][] matriz;
	private BloqueGrafico bloqueLibre;
	
	private int cantFilas;
	private int cantColumnas;

	/**
	 * Crea una grilla, de 21 filas por 10 columnas y la carga de bloques libres.
	 * @param juego El juego con el que se relaciona la grilla.
	 */
	public Grid(Juego juego) {
		this.miJuego = juego;
		this.cantFilas = 21;
		this.cantColumnas = 10;
		this.bloqueLibre = new BloqueGrafico(BloqueGrafico.EMPTY_IMAGE);
		this.matriz = new Bloque[cantFilas][cantColumnas];
		
		for(int filas = 0; filas < cantFilas; filas++) {
			for(int columnas = 0; columnas < cantColumnas; columnas++) {
				
				matriz[filas][columnas] = new Bloque(filas, columnas, this);
				
				matriz[filas][columnas].desocupar();
			}
		}
	}
	
	/**
	 * Retorna el bloque con posición (f,c). Asume que las coordenadas pasadas con correctas.
	 * @param f la fila del elemento.
	 * @param c la columna del elemento.
	 * @return el bloque en la posición (f,c).
	 */
	public Bloque getBloque(int f, int c) {
		return matriz[f][c];
	}
	
	/**
	 * Retorna un bloque grafico el cual se encuentra libre.
	 * @return un bloque grafico libre.
	 */
	public BloqueGrafico getBloqueLibre() {
		return bloqueLibre;
	}
	
	/**
	 * Informa al juego que cambio el estado de un bloque.
	 * @param bloque un bloque.
	 */
	public void cambioBloque(Bloque bloque) {
		miJuego.cambioBloque(bloque);
	}
	
	/**
	 * Controla si las filas se llenaron y de ser así baja de linea y le avisa al juego que incremente el puntaje
	 */
	public void verificarLineas() {
		boolean lineaLlena = true;
		int cant = 0;
		int fila = cantFilas-1;
		while(fila >= 0) { 
			lineaLlena = true;
			for(int columna = cantColumnas-1; columna >= 0 && lineaLlena; columna--) {
				lineaLlena =! matriz[fila][columna].estaLibre();
			}
			if(lineaLlena) {
				cant++;
				borrarFila(fila);
			}
			else {
				fila--;
			}
		}
		switch (cant) {
		case 1: miJuego.setPuntaje(100); break;
		case 2: miJuego.setPuntaje(300); break;
		case 3: miJuego.setPuntaje(600); break;
		case 4: miJuego.setPuntaje(400); break;
		}
	}
	
	/**
	 * Borra la fila correspondiente a la posición pasada por parametro
	 * @param fila
	 */
	private void borrarFila(int fila) {
		for(int f = fila; f >= 0; f--) {
			for(int c = cantColumnas-1; c >= 0; c--){
				matriz[f][c].desocupar();
				if(f > 0) {
					if(!matriz[f-1][c].estaLibre())
						matriz[f][c].ocupar(matriz[f-1][c].getBloqueGrafico());
					matriz[f-1][c].desocupar();
				}
			}
		}
	}
	
	/**
	 * Restaura la grilla a su estado inicial.
	 */
	public void restaurar() {
		for(int filas = 0; filas < cantFilas; filas++) {
			for(int columnas = 0; columnas < cantColumnas; columnas++) {
				
				matriz[filas][columnas] = new Bloque(filas, columnas, this);
				
				matriz[filas][columnas].desocupar();
			}
		}
	}
	
}