package utils;

public class Boneco extends Objetos{

	private String visao;
	
	public Boneco(Ponto p) {
		super(p);
		this.visao = "N";
		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
		return "B";
	}
	
	public String getVisao(){
		return visao;
	}
	
	public void setVisao(int visao){
		switch (visao) {
		case 0:
			this.visao = "N";
			break;
		case 1:
			this.visao = "L";
			break;
		case 2:
			this.visao = "S";
			break;
		case 3:
			this.visao = "O";
			break;
		default:
			break;
		}
	}

}
