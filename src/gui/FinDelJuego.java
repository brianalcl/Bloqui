package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinDelJuego extends JPanel {

	/**
	 * Serial por defecto
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String FUENTE = "SansSerif";
	
	private JButton btnFinDeJuegoRestart;
	private JButton btnFinDeJuegoExit;
	private JLabel lblFinDeJuego;

	/**
	 * Crea el panel.
	 */
	public FinDelJuego(Ventana v) {
		btnFinDeJuegoRestart = new JButton("RESTART");
		btnFinDeJuegoExit = new JButton("EXIT");
		lblFinDeJuego = new JLabel("");
		
		setBounds(130, 125, 290, 375);
		setLayout(null);
		
		btnFinDeJuegoRestart.addActionListener((actionEvent) -> { v.restaurar(); });

		btnFinDeJuegoRestart.setBackground(Color.CYAN);
		btnFinDeJuegoRestart.setFont(new Font(FUENTE, Font.BOLD, 20));
		btnFinDeJuegoRestart.setForeground(Color.BLACK);
		btnFinDeJuegoRestart.setBounds(32, 227, 226, 50);
		add(btnFinDeJuegoRestart);
		
		btnFinDeJuegoExit.addActionListener((actionEvent) -> { System.exit(0); });
		
		btnFinDeJuegoExit.setBackground(Color.CYAN);
		btnFinDeJuegoExit.setForeground(Color.BLACK);
		btnFinDeJuegoExit.setFont(new Font(FUENTE, Font.BOLD, 20));
		btnFinDeJuegoExit.setBounds(32, 295, 226, 50);
		add(btnFinDeJuegoExit);
		
		
		lblFinDeJuego.setIcon(new ImageIcon(FinDelJuego.class.getResource("/assets/img/backgrounds/bgGameOver.png")));
		lblFinDeJuego.setBounds(0, 0, 290, 375);
		add(lblFinDeJuego);
		
	}

}
