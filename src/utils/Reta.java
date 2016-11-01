package utils;

public class Reta {
	
	private Ponto p1;
	private Ponto p2;
	private double a;
	private double b;
	private double c;
	
	public Reta(Ponto p1, Ponto p2){
		this.p1 = p1;
		this.p2 = p2;
		paramEquaReta();
	
	}
	
	public void paramEquaReta(){
		int x1 = p1.getX(); int y1 = p1.getY();
		int x2 = p2.getX(); int y2 = p2.getY();
		
		this.a = y1 - y2;
		this.b = x2 - x1;
		this.c = (x1*y2) - (x2*y1);
	}
	
	public boolean pontoReta(Ponto p){
		return ((p.getX()*a) + (p.getY()*b) + c == 0);
	}
	
}
