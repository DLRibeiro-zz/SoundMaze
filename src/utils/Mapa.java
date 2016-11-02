package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Mapa {
	private String path;
	private ArrayList<Reta> retas;
	private int alturaMapa;
	private int larguraMapa;
	private Objetos[][] mapa;
	
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
		this.alturaMapa = fm.alturaMapa;
		this.larguraMapa = fm.larguraMapa;
		this.mapa = new Objetos[alturaMapa][larguraMapa];
		montarTabuleiro(retas);
		iniciaJogo();
	}
	
	public void montarTabuleiro(ArrayList<Reta> retas){
		for (Reta r : retas) {
			
			for(int i = 0; i < alturaMapa; i++){
				for(int j = 0; j < larguraMapa; j++){
					if(r.estaNaReta(new Ponto(i,j))){
						mapa[i][j] = new Parede(new Ponto(i,j));
					} else {
						//tabuleiro [i][j] = new Chao(new Ponto(i,j));
					}
				}
			}	
		}
		
		for(int i = 0; i < alturaMapa; i++){
			for(int j = 0; j < larguraMapa; j++){
				if(mapa[i][j] instanceof Parede == false){
					mapa[i][j] = new Chao(new Ponto(i,j));
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
			int xBoneco = random.nextInt(this.alturaMapa);
			int yBoneco = random.nextInt(this.larguraMapa);
			if(mapa[xBoneco][yBoneco] instanceof Parede == false){
				mapa[xBoneco][yBoneco] = new Boneco (new Ponto(xBoneco, yBoneco));
				b = true;
			}
		}
		b = false;
		while(!b){
			int xFonte = random.nextInt(this.alturaMapa);
			int yFonte = random.nextInt(this.larguraMapa);
			if(mapa[xFonte][yFonte] instanceof Parede == false && 
					mapa[xFonte][yFonte] instanceof Boneco == false){
				mapa[xFonte][yFonte] = new Fonte (new Ponto(xFonte, yFonte));
				b = true;
			}
		}
	}
	

	
	public void printaTudo(){
		for(int i = 0; i < alturaMapa; i++){
			for(int j = 0; j < larguraMapa; j++){
				if(mapa[i][j] instanceof Parede){
					System.out.print(((Parede)mapa[i][j]).toString());
					System.out.print(" ");
				} else if(mapa[i][j] instanceof Chao) {
					System.out.print(((Chao)mapa[i][j]).toString());
					System.out.print(" ");
				} else if(mapa[i][j] instanceof Boneco){
					System.out.print(((Boneco)mapa[i][j]).toString());
					System.out.print(" ");
				} else if(mapa[i][j] instanceof Fonte){
					System.out.print(((Fonte)mapa[i][j]).toString());
					System.out.print(" ");
				}
				
				}
			System.out.print("\n");
		}	
	}

}
