package utils;

public class Mapa {
	String path;
	private ArrayList<Reta> retas;
	
	public Mapa (String path){
		this.path = path;
		this.retas = new ArrayList<Reta>();
	}
	public void setRetas (ArrayList<Reta> r){
		this.retas = r;
	}
	public void criarMapa(){
		FazerMapa fm = new FazerMapa(path);
		this.retas = fm.retas;
	}
}
