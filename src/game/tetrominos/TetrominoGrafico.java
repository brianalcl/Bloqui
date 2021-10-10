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
	 */
	public TetrominoGrafico(String nombre) {
		misBloquesGraficos = new BloqueGrafico[4];
		
		this.setIcon(new javax.swing.ImageIcon(game.tetrominos.TetrominoGrafico.class.getResource("/assets/img/bloques/"+nombre+".png")));
		
		for (int i = 0; i < 4; i++) {
			misBloquesGraficos[i] = new BloqueGrafico(nombre);
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
