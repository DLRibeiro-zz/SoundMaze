package utils;

import java.io.IOException;
import java.util.ArrayList;


public class Mapa {
	String path;
	private ArrayList<Reta> retas;
	int alturaTabuleiro;
	int larguraTabuleiro;
	Objetos[][] tabuleiro;
	public Mapa (String path){
		this.path = path;
		this.retas = new ArrayList<Reta>();
	}
	public void setRetas (ArrayList<Reta> r){
		this.retas = r;
	}
	public void criarMapa() throws IOException{
		FazerMapa fm = new FazerMapa(path);
		this.retas = fm.retas;
		this.alturaTabuleiro = fm.alturaMapa;
		this.larguraTabuleiro = fm.larguraMapa;
		this.tabuleiro = new Objetos[alturaTabuleiro][larguraTabuleiro];
		montarTabuleiro(retas);
	}
	
	public void montarTabuleiro(ArrayList<Reta> retas){
		for (Reta r : retas) {
			
			for(int i = 0; i < alturaTabuleiro; i++){
				for(int j = 0; j < larguraTabuleiro; j++){
					if(r.estaNaReta(new Ponto(i,j))){
						tabuleiro[i][j] = new Parede(new Ponto(i,j));
					} else {
						//tabuleiro [i][j] = new Chao(new Ponto(i,j));
					}
				}
			}	
		}
		
		for(int i = 0; i < alturaTabuleiro; i++){
			for(int j = 0; j < larguraTabuleiro; j++){
				if(tabuleiro[i][j] instanceof Parede == false){
					tabuleiro[i][j] = new Chao(new Ponto(i,j));
				} else {
					//tabuleiro [i][j] = new Chao(new Ponto(i,j));
				}
			}
		}	
	}
	
	public void printaRetas(){
		for (Reta reta : retas) {
			System.out.println(reta.toString());
		}
	}

	
	public void printaTudo(){
		for(int i = 0; i < alturaTabuleiro; i++){
			for(int j = 0; j < larguraTabuleiro; j++){
				if(tabuleiro[i][j] instanceof Parede){
					System.out.print(((Parede)tabuleiro[i][j]).toString());
					System.out.print(" ");
				} else {
					System.out.print(((Chao)tabuleiro[i][j]).toString());
					System.out.print(" ");
				}
				
				}
			System.out.print("\n");
		}	
	}

}
