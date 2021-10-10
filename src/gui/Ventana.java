package gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.Juego;


public class Ventana extends JFrame{
	
	/**
	 * Serial por defecto
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String FUENTE = "SansSerif";
	
	private JPanel panelDeJuego;
	private JLabel lblInfoTime;
	private JLabel lblInfoScore;
	
	private transient Juego miJuego;
	private JLabel[][] matrizPrincipal;
	private JLabel[] siguientes;
	private JPanel panelSigsTet;
	private JLabel lblInfoLevel;
	private FinDelJuego panelFinDeJuego;
	
	
	public Ventana(Juego juego) {
		this.miJuego = juego;
		matrizPrincipal = new JLabel[21][10];
		siguientes = new JLabel[3];
		initialize();
		
	}
	
	/**
	 * Crea toda la ventana y su funcionalidad.
	 */
	private void initialize() {
		getContentPane().setLayout(null);
		setResizable(false);
		setBounds(100, 100, 565, 663);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setIconImage(new ImageIcon(getClass().getResource("/assets/img/icon/Icon.png")).getImage());
		setTitle("Bloqui");
		setVisible(true);
		
		crearInfoStats();
		crearInfoTetro();
		crearPanelJuego();
		crearFondoVentana();
		llenarPanelJuego();
		agregarControles();
		
		repaint();
	}
	
	/**
	 * Actualiza el puntaje en el label correspondiente.
	 * @param puntaje el puntaje.
	 */
	public void actualizarPuntaje(int puntaje) {
		lblInfoScore.setText(""+puntaje);
	}
	
	/**
	 * Actualiza el nivel en el label correspondiente.
	 * @param nivel el nivel.
	 */
	public void actualizarNivel(int nivel) {
		lblInfoLevel.setText(""+nivel);
	}
	
	/**
	 * Actualiza el tiempo en el label correspondiente.
	 * @param segundos el tiempo en segundos.
	 */
	public void actualizarTiempo(int segundos) {
		int seg = segundos;
		int min = seg / 60;
		seg = seg % 60;
		String rta = new StringBuilder(String.format("%02d", min))
				.append(" : ")
				.append(String.format("%02d", seg))
				.toString();
		lblInfoTime.setText(rta);
	}
	
	/**
	 * Agrega en el panel lateral una imagen con el tetromino siguiente quitando antes el anterior.
	 * @param nombre
	 */
	public void agregarSiguiente(String nombre) {
		for (int i=0; i<siguientes.length-1; i++) {
			siguientes[i].setIcon(siguientes[i+1].getIcon());
		}
		siguientes[siguientes.length-1].setIcon(new ImageIcon(Ventana.class.getResource("/assets/img/bloques/"+nombre+".png")));
		repaint();
	}
	
	/**
	 * Cambia la imagen de un bloque en la matriz principal (esto se refleja en el panel del juego).
	 * @param fila la fila del bloque a cambiar.
	 * @param columna la columna del bloque a cambiar.
	 * @param bg el bloque grafico a colocar.
	 */
	public void graficarBloque(int fila, int columna, JLabel bg) {
		matrizPrincipal[fila][columna].setIcon(bg.getIcon());
	}
	
	/**
	 * Reinicia el juego completamente
	 */
	public void restaurar() {
		getContentPane().remove(panelFinDeJuego);
		miJuego.reinicio();
		repaint();
	}
	
	/**
	 * Muestra un nuevo panel al frente de la ventana para decidir si continuar o no con el juego
	 */
	public void finDelJuego() {
		panelFinDeJuego = new FinDelJuego(this);
		getContentPane().add(panelFinDeJuego);
		getContentPane().setComponentZOrder(panelFinDeJuego, 2);
		setFocusable(true);
		repaint();
	}
	
	/**
	 * Crea el sistema de Listeners para dectectar preciones de las teclas
	 * arriba, abajo, izquierda, derecha, espacio, x, z y realiza distintas acciones dependiendo de la tecla precionada 
	 */
	private void agregarControles() {
		this.addKeyListener(new KeyAdapter() {								//Movimiento lateral

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				
				if (code == KeyEvent.VK_LEFT) {
					miJuego.operar(Juego.MOVER_IZQUIERDA); 
				} else if (code == KeyEvent.VK_RIGHT) {
					miJuego.operar(Juego.MOVER_DERECHA);
				}
				
			}

		});
		
		this.addKeyListener(new KeyAdapter() {								//Bajar

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					miJuego.operar(Juego.MOVER_ABAJO); 
			}

		});

		this.addKeyListener(new KeyAdapter() {								//Rotacion

			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_Z:
					miJuego.operar(Juego.ROTAR_IZQUIERDA);
					break;
				case KeyEvent.VK_X:
					miJuego.operar(Juego.ROTAR_DERECHA);
					break;
				case KeyEvent.VK_UP:
					miJuego.operar(Juego.ROTAR_DERECHA);
					break;
				}

			}

		});
	}
	
	/**
	 * Llena todo el panel del juego con labels
	 */
	private void llenarPanelJuego() {
		for(int i=0; i<21; i++) {
			for(int j=0; j<10; j++) {
				matrizPrincipal[i][j] = new JLabel();
				panelDeJuego.add(matrizPrincipal[i][j]);
			}
		}
	}
	
	/**
	 * Coloca la imagen de fondo de la ventana.
	 */
	private void crearFondoVentana() {
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(Ventana.class.getResource("/assets/img/backgrounds/bg.png")));
		background.setBounds(0, 0, 550, 625);
		getContentPane().add(background);
	}
	
	/**
	 * Crea el panel por el cual caeran los tetrominos.
	 */
	private void crearPanelJuego() {
		panelDeJuego = new JPanel();
		panelDeJuego.setBackground(new java.awt.Color(0, 0, 25));
		panelDeJuego.setBounds(150, 50, 250, 525);
		panelDeJuego.setLayout(new GridLayout(21, 10, 0, 0));
		getContentPane().add(panelDeJuego);
	}
	
	/**
	 * Crea un label para indicar los tetrominos siguientes
	 * y tambien crea un panel cargado de labels para mostrar
	 * los tetrominos siguientes.
	 */
	private void crearInfoTetro() {
		
		JLabel lblNext = new JLabel("NEXT");
		lblNext.setBounds(425, 408, 100, 25);
		lblNext.setForeground(new java.awt.Color(0, 0, 25));
		lblNext.setHorizontalAlignment(SwingConstants.CENTER);
		lblNext.setFont(new Font(FUENTE, Font.BOLD, 20));
		
		getContentPane().add(lblNext);
		
		panelSigsTet = new JPanel();
		panelSigsTet.setBounds(425, 100, 100, 300);
		panelSigsTet.setLayout(new GridLayout(3, 1, 0, 0));
		panelSigsTet.setBackground(new java.awt.Color(0, 0, 25));
		getContentPane().add(panelSigsTet);
		
		
		for(int i=0; i<siguientes.length; i++) {
			siguientes[i] = new JLabel();
			siguientes[i].setIcon(new ImageIcon(Ventana.class.getResource("/assets/img/bloques/empty.png")));
			panelSigsTet.add(siguientes[i]);
		}
	}
	
	/**
	 * Crea los distintos labels que indican los stats del juego.
	 */
	private void crearInfoStats() {
		
		JLabel lblScore = new JLabel("SCORE");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(new java.awt.Color(0, 0, 25));
		lblScore.setFont(new Font(FUENTE, Font.BOLD, 20));
		lblScore.setBounds(25, 450, 100, 25);
		getContentPane().add(lblScore);
		
		lblInfoScore = new JLabel("0");
		lblInfoScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoScore.setForeground(new java.awt.Color(0,0,25));
		lblInfoScore.setFont(new Font(FUENTE, Font.BOLD, 22));
		lblInfoScore.setBounds(25, 479, 100, 50);
		getContentPane().add(lblInfoScore);
		
		JLabel lblLevel = new JLabel("LEVEL");
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setForeground(new java.awt.Color(0, 0, 25));
		lblLevel.setFont(new Font(FUENTE, Font.BOLD, 20));
		lblLevel.setBounds(25, 200, 100, 25);
		getContentPane().add(lblLevel);
		
		lblInfoLevel = new JLabel("0");
		lblInfoLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoLevel.setForeground(new java.awt.Color(0, 0, 25));
		lblInfoLevel.setFont(new Font(FUENTE, Font.BOLD, 22));
		lblInfoLevel.setBounds(25, 228, 100, 50);
		getContentPane().add(lblInfoLevel);
		
		JLabel lblTime = new JLabel("TIME");
		lblTime.setBackground(new java.awt.Color(255,255,255));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(new java.awt.Color(0, 0, 25));
		lblTime.setFont(new Font(FUENTE, Font.BOLD, 20));
		lblTime.setBounds(25, 325, 100, 25);
		getContentPane().add(lblTime);
		
		lblInfoTime = new JLabel("00:00");
		lblInfoTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoTime.setForeground(new java.awt.Color(0, 0, 25));
		lblInfoTime.setFont(new Font(FUENTE, Font.BOLD, 22));
		lblInfoTime.setBounds(25, 353, 100, 50);
		getContentPane().add(lblInfoTime);
	}
}
