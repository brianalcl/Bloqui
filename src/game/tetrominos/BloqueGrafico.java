package game.tetrominos;

import javax.swing.JLabel;


public class BloqueGrafico extends JLabel{
	/**
	 * Serial pro defecto
	 */
	private static final long serialVersionUID = 1L;
	
	private int rotacionImagen;
	private String nombre;
	public static final String EMPTY_IMAGE = "empty";
	
	/**
	 * Crea un bloque grafico.
	 * @param nombre el nombre del bloque.
	 */
	public BloqueGrafico(String nombre) {
		super();
		this.rotacionImagen = 0;
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
				.append(rotacionImagen)
				.append(".png")
				.toString();
		this.setIcon(new javax.swing.ImageIcon(game.tetrominos.BloqueGrafico.class.getResource(rutaImagen)));
	}
	
	/**
	 * Rota la imagen del bloque grafico a la derecha.
	 */
	public void rotarDerecha() {
		rotacionImagen = (rotacionImagen + 1) % 4;
		setImagen(nombre);
	}
	
	/**
	 * Rota la imagen del bloque grafico a la izquierda.
	 */
	public void rotarIzquierda() {
		if(rotacionImagen != 0)
			rotacionImagen = (rotacionImagen - 1) % 4;
		else
			rotacionImagen = 3;
		setImagen(nombre);
	}
	
}


