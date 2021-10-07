package game.tetrominos;

import javax.swing.JLabel;


public class BloqueGrafico extends JLabel{
	/**
	 * Serial pro defecto
	 */
	private static final long serialVersionUID = 1L;
	
	private int rotacionImagen;
	private String nombre;
	public static final String EMPTY_IMAGE = "/assets/img/bloques/empty0.png";
	
	/**
	 * Crea un bloque grafico.
	 * @param nombre el nombre del bloque.
	 * @param rotacion la rotacion del bloque.
	 */
	public BloqueGrafico(String nombre, int rotacion) {
		
		super();
		this.rotacionImagen = rotacion;
		this.nombre = nombre;
		
		setImagen(this.nombre);
	}
	
	/**
	 * Setea la imagen del bloque grafico.
	 * @param nombre el nombre del bloque.
	 */
	public void setImagen(String nombre) {
		if(!this.nombre.equals(EMPTY_IMAGE)) {
			String rutaImagen = "/assets/img/bloques/" + nombre + rotacionImagen + ".png";
			this.setIcon(new javax.swing.ImageIcon(game.tetrominos.BloqueGrafico.class.getResource(rutaImagen)));
		}
		else {
			limpiar();
		}
	}
	
	/**
	 * Limpia el bloque grafico, es decir le coloca una imagen vacia y restaura la rotacion.
	 */
	private void limpiar() {
		this.setIcon(new javax.swing.ImageIcon(game.tetrominos.BloqueGrafico.class.getResource(EMPTY_IMAGE)));
			this.rotacionImagen = 0;
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


