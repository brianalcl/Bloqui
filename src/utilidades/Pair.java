package utilidades;

public class Pair<F, C> {
	private F f;
	private C c;
	
	public Pair() {
		f = null;
		c = null;
	}
	
	public Pair(F f, C c) {
		this.f = f;
		this.c = c;
	}
	
	public void setFyC(F f, C c) {
		this.f = f;
		this.c = c;
	}
	
	public void setF(F f) {
		this.f = f;
	}
	
	public void setC(C c) {
		this.c = c;
	}
	
	public F getF() {
		return f;
	}
	
	public C getC() {
		return c;
	}
}
