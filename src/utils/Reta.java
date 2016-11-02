package utils;


import java.awt.geom.Line2D;


public class Reta {
	private Ponto p1;
	private Ponto p2;
	private double a;
	private double b;
	private double c;
	
	public Reta(Ponto p1, Ponto p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Ponto getP1() {
		return p1;
	}

	public void setP1(Ponto p1) {
		this.p1 = p1;
	}

	public Ponto getP2() {
		return p2;
	}

	public void setP2(Ponto p2) {
		this.p2 = p2;
	}

	
	public Boolean estaNaReta(Ponto teste){
		if(Line2D.Double.ptLineDist(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY(), 
				teste.getX(), teste.getY()) == 0){
			return true;
		} else{
			return false;
		}
		
	}
	
	public boolean pontoReta(Ponto p){
		return ((p.getX()*a) + (p.getY()*b) + c == 0);
	}
	
	public String toString(){
		return "p1 = " + p1.toString() + ", p2 = " + p2.toString();
	}
}
