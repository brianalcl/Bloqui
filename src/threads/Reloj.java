package threads;

import game.Juego;

public class Reloj extends Thread{
	protected Juego miJuego;
	protected boolean activo;
	protected int step;
	
	/**
	 * Crea un reloj con el juego y step(tiempo de pausa entre caidas).
	 * @param juego Un juego.
	 * @param step El tiempo entre caidas.
	 */
	public Reloj(Juego juego, int step) {
		this.miJuego = juego;
		this.activo = !miJuego.terminoElJuego();
		this.step = miJuego.velocidad();
	}
	
	/**
	 * Setea el tiempo entre caidas.
	 * @param step Tiempo entre caidas.
	 */
	public void setStep(int step) {
		this.step=step;
	}
	
	/**
	 * Inicia el reloj.
	 */
	public void run() {
		int cant=0;
		int nivel=0;
		while(this.activo) { 
			try {
				Thread.sleep(step);
				nivel = miJuego.getNivel();
				cant++;
				if(cant == nivel) {
					cant=0;
					miJuego.addSegundo();
				}
				
				miJuego.operar(Juego.MOVER_ABAJO);
				this.activo = !miJuego.terminoElJuego();
				this.step = miJuego.velocidad();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Detiene el reloj.
	 */
	public void detener() {
		this.activo = false;
	}
}
