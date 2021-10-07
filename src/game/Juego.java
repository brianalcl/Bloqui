package game;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import game.tetrominos.Bloque;
import game.tetrominos.Tetromino;
import game.tetrominos.Tetromino_I;
import game.tetrominos.Tetromino_L;
import game.tetrominos.Tetromino_J;
import game.tetrominos.Tetromino_O;
import game.tetrominos.Tetromino_S;
import game.tetrominos.Tetromino_T;
import game.tetrominos.Tetromino_Z;
import gui.Ventana;
import threads.Reloj;

public class Juego {
	public static final int MOVER_DERECHA = 6;
	public static final int MOVER_IZQUIERDA = 4;
	public static final int MOVER_ABAJO = 2;
	public static final int ROTAR_IZQUIERDA = 7;
	public static final int ROTAR_DERECHA = 9;
	
	private Reloj miReloj;
	
	private Ventana miVentana;
	private Grid grillaPrincipal;
	
	private Queue<Tetromino> tetrominos;
	private Tetromino tetrominoActual;
	
	private int segundos;
	private int puntaje;
	private int nivel;
	
	private boolean terminoElJuego;

	/**
	 * Crea un juego nuevo.
	 */
	public Juego() {
		this.miVentana=new Ventana(this);
		grillaPrincipal=new Grid(this);
		this.segundos = 0;
		this.puntaje=0;
		nivel=1;
		
		miVentana.actualizarPuntaje(puntaje);
		miVentana.actualizarTiempo(segundos);
		miVentana.actualizarNivel(nivel);
		
		this.terminoElJuego = false;
		
		tetrominos = new LinkedList<Tetromino>();
		tetrominos.add(crearTetromino());
		tetrominos.add(crearTetromino());
		tetrominos.add(crearTetromino());
		tetrominos.add(crearTetromino());
		tetrominoActual = tetrominos.poll();
		tetrominoActual.aparecer();
		
		miReloj = new Reloj(this, 1000);
	}
	
	/**
	 * Crea un tetromino aleatirio.
	 * @return Un tetromino.
	 */
	private Tetromino crearTetromino() {
		Tetromino tet = null;

		Random rnd = new Random();		
		int tirada = rnd.nextInt(7); // Numero aleatorio en [0,7)
		switch ( tirada ) {
		case 0:	tet = new 
			Tetromino_I(grillaPrincipal);
			miVentana.agregarSiguiente(tet.getNombre());
			break;
		case 1:	
			tet = new Tetromino_L(grillaPrincipal);	
			miVentana.agregarSiguiente(tet.getNombre());
			break;
		case 2:	
			tet = new Tetromino_J(grillaPrincipal);
			miVentana.agregarSiguiente(tet.getNombre());
			break;
		case 3:	
			tet = new Tetromino_O(grillaPrincipal);	
			miVentana.agregarSiguiente(tet.getNombre());
			break;
		case 4:	
			tet = new Tetromino_S(grillaPrincipal);	
			miVentana.agregarSiguiente(tet.getNombre());
			break;
		case 5:	
			tet = new Tetromino_T(grillaPrincipal);	
			miVentana.agregarSiguiente(tet.getNombre());
			break;
		case 6:	
			tet = new Tetromino_Z(grillaPrincipal);	
			miVentana.agregarSiguiente(tet.getNombre());
			break;
		}
		
		return tet;
	}
	
	/**
	 * Inicia el juego
	 */
	public void start() {
		miReloj.start();
	}
	
	/**
	 * Le avisa a la ventana que cambio un bloque.
	 * @param bloque un bloque.
	 */
	public void cambioBloque(Bloque bloque) {
		miVentana.graficarBloque(bloque.getFila(), bloque.getColumna(), bloque.getBloqueGrafico());
	}
	
	/**
	 * Permite mover el tretromino, rotarlo y hacerlo caer.
	 * @param operacion
	 */
	public synchronized void operar(int operacion) {
		if(!terminoElJuego)
			switch (operacion) {
			case MOVER_DERECHA: tetrominoActual.moverDerecha(); break;
			case MOVER_IZQUIERDA: tetrominoActual.moverIzquierda(); break;
			case ROTAR_IZQUIERDA: tetrominoActual.rotarIzquierda(); break;
			case ROTAR_DERECHA: tetrominoActual.rotarDerecha(); break;
			case MOVER_ABAJO: if(tetrominoActual.bajar()) verificarLineasSeguir(); break;
			}
	}
	
	/**
	 * Le informa a la grilla que controle las lineas
	 */
	private void verificarLineasSeguir() {
		grillaPrincipal.verificarLineas();
		tetrominos.add(crearTetromino());
		tetrominoActual = tetrominos.poll();
		if(!tetrominoActual.aparecer()) {
			finDelJuego();
		}
	}
	
	/**
	 * Incrementa el puntaje con la cantidad pasada por parametro.
	 * @param p
	 */
	public void setPuntaje(int p) {
		this.puntaje += p;
		nivel = 1 + puntaje/1000;
		miVentana.actualizarPuntaje(puntaje);
		miVentana.actualizarNivel(nivel);
	}
	
	/**
	 * Agrega un segundo al juego y luego le pide a la ventana que lo actualize.
	 */
	public void addSegundo() {
		segundos++;
		miVentana.actualizarTiempo(segundos);
	}
	
	/**
	 * Cambia es estado del juego a terminado y le avisa a la ventana que lo actualice.
	 */
	private void finDelJuego() {
		terminoElJuego=true;
		miVentana.finDelJuego();
	}
	
	/**
	 * Retorna true si el juego termino, false en caso contrario.
	 * @return true si el juego termino, false en caso contrario.
	 */
	public boolean terminoElJuego() {
		return terminoElJuego;
	}
	
	/**
	 * Retorna la velocidad en la que cae el tetromino.
	 * @return la velocidad en la que cae el tetromino.
	 */
	public int velocidad() {
		return 1000/nivel;
	}
	
	/**
	 * Retorna el nivl del juego.
	 * @return el nivel dl jugo.
	 */
	public int getNivel() {
		return nivel;
	}
	
	/**
	 * Reinicia el juego, restaurando todos los valores al estado inicial.
	 */
	public void reinicio() {
		nivel = 1;
		segundos = 0;
		puntaje = 0;
		
		miVentana.actualizarPuntaje(puntaje);
		miVentana.actualizarTiempo(segundos);
		miVentana.actualizarNivel(nivel);
		
		grillaPrincipal.restaurar();
		tetrominos = new LinkedList<Tetromino>();
		tetrominos.add(crearTetromino());
		tetrominos.add(crearTetromino());
		tetrominos.add(crearTetromino());
		tetrominos.add(crearTetromino());
		tetrominoActual = tetrominos.poll();
		
		terminoElJuego = false;
		tetrominoActual.aparecer();
		
		miReloj.detener();
		
		miReloj = new Reloj(this, 1000);
		miReloj.start();
	}
}
