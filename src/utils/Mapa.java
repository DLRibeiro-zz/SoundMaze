package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


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
		iniciaJogo();
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
	
	public void iniciaJogo(){
		Random random = new Random();
		Boolean b = false;
		while(!b){
			int xBoneco = random.nextInt(this.alturaTabuleiro);
			int yBoneco = random.nextInt(this.larguraTabuleiro);
			if(tabuleiro[xBoneco][yBoneco] instanceof Parede == false){
				tabuleiro[xBoneco][yBoneco] = new Boneco (new Ponto(xBoneco, yBoneco));
				b = true;
			}
		}
		b = false;
		while(!b){
			int xFonte = random.nextInt(this.alturaTabuleiro);
			int yFonte = random.nextInt(this.larguraTabuleiro);
			if(tabuleiro[xFonte][yFonte] instanceof Parede == false && 
					tabuleiro[xFonte][yFonte] instanceof Boneco == false){
				tabuleiro[xFonte][yFonte] = new Fonte (new Ponto(xFonte, yFonte));
				b = true;
			}
		}
	}
	

	
	public void printaTudo(){
		for(int i = 0; i < alturaTabuleiro; i++){
			for(int j = 0; j < larguraTabuleiro; j++){
				if(tabuleiro[i][j] instanceof Parede){
					System.out.print(((Parede)tabuleiro[i][j]).toString());
					System.out.print(" ");
				} else if(tabuleiro[i][j] instanceof Chao) {
					System.out.print(((Chao)tabuleiro[i][j]).toString());
					System.out.print(" ");
				} else if(tabuleiro[i][j] instanceof Boneco){
					System.out.print(((Boneco)tabuleiro[i][j]).toString());
					System.out.print(" ");
				} else if(tabuleiro[i][j] instanceof Fonte){
					System.out.print(((Fonte)tabuleiro[i][j]).toString());
					System.out.print(" ");
				}
				
				}
			System.out.print("\n");
		}	
	}

}
