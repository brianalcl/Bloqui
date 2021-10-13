package game.tetrominos;

import javax.swing.JLabel;


public class BloqueGrafico extends JLabel{
	/**
	 * Serial pro defecto
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	public static final String EMPTY_IMAGE = "empty";
	
	/**
	 * Crea un bloque grafico.
	 * @param nombre el nombre del bloque.
	 */
	public BloqueGrafico(String nombre) {
		super();
		this.nombre = nombre;
		setImagen(this.nombre);
	}
	
	/**
	 * Setea la imagen del bloque grafico.
	 * @param nombre el nombre del bloque.
	 */
	public void setImagen(String nombre) {
		String rutaImagen = new StringBuilder("/assets/img/bloques/")
				.append(nombre)
				.append("0.png")
				.toString();
		setIcon(new javax.swing.ImageIcon(game.tetrominos.BloqueGrafico.class.getResource(rutaImagen)));
	}
	
}


