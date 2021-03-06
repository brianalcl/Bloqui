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
		this.misBloquesGraficos = new BloqueGrafico[4];
		
		String rutaImagen = new StringBuilder("/assets/img/bloques/")
				.append(nombre)
				.append(".png")
				.toString();
		
		setIcon(new javax.swing.ImageIcon(game.tetrominos.TetrominoGrafico.class.getResource(rutaImagen)));
		
		for (int i = 0; i < 4; i++) {
			misBloquesGraficos[i] = new BloqueGrafico(nombre);
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
