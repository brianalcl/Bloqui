package game.tetrominos;

import javax.swing.JLabel;

public class TetrominoGrafico extends JLabel{
	/**
	 * Serial por defecto
	 */
	private static final long serialVersionUID = 1L;
	
	private BloqueGrafico[] misBloquesGraficos;
	
	/**
	 * Crea un tetromino grafico.
	 * @param nombre el nombre del tetromino.
	 * @param rotacion la rotacion del tetromino.
	 */
	public TetrominoGrafico(String nombre, int rotacion) {
		misBloquesGraficos = new BloqueGrafico[4];
		
		for (int i = 0; i < 4; i++) {
			misBloquesGraficos[i] = new BloqueGrafico(nombre, 0);
		}
		
	}
	
	/**
	 * Rota el tetromino grafico a la derecha.
	 */
	public void rotarDerecha() {
		for(BloqueGrafico bg: misBloquesGraficos) {
			bg.rotarDerecha();
		}
	}
	
	/**
	 * Rota el tetromino grafico a la izquierda.
	 */
	public void rotarIzquierda() {
		for(BloqueGrafico bg: misBloquesGraficos) {
			bg.rotarIzquierda();
		}
	}
	
	/**
	 * Retorna el bloque grafico en la posicion i.
	 * @param i la posicion del bloque grafico.
	 * @return un bloque grafico.
	 */
	public BloqueGrafico getBloqueGrafico(int i) {
		return misBloquesGraficos[i];
	}
}
