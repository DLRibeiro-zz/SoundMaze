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
				} 
			}
		}	
	}
	
	public Ponto achaBoneco(){
		Ponto p = null;
		for(int i = 0; i < alturaTabuleiro; i++){
			for(int j = 0; j < larguraTabuleiro; j++){
				if(tabuleiro[i][j] instanceof Boneco){
					p = new Ponto(((Boneco)tabuleiro[i][j]).p.getX(),((Boneco)tabuleiro[i][j]).p.getY());
				}
				
			}
		}	
		return p;
		
	}
	
	public void moveBoneco(String direcao){
		Ponto boneco = achaBoneco();
		if(direcao.equals("w")){
			if(tabuleiro[boneco.getX()-1][(boneco.getY())] instanceof Parede){
				System.out.println("Bateu na parede!");
			} else if (tabuleiro[boneco.getX()-1][(boneco.getY())] instanceof Fonte){
				System.out.println("Cabosse!");
			} else {
				tabuleiro[boneco.getX()][(boneco.getY())] = new Chao(new Ponto(boneco.getX(), boneco.getY()));
				tabuleiro[boneco.getX()-1][(boneco.getY())] = new Boneco(new Ponto(boneco.getX()-1, boneco.getY()));
			}
		} else if(direcao.equals("s")){
			if(tabuleiro[boneco.getX()+1][(boneco.getY())] instanceof Parede){
				System.out.println("Bateu na parede!");
			} else if (tabuleiro[boneco.getX()+1][(boneco.getY())] instanceof Fonte){
				System.out.println("Cabosse!");
			} else {
				tabuleiro[boneco.getX()][(boneco.getY())] = new Chao(new Ponto(boneco.getX(), boneco.getY()));
				tabuleiro[boneco.getX()+1][(boneco.getY())] = new Boneco(new Ponto(boneco.getX()+1, boneco.getY()));
			}
		} else if(direcao.equals("a")){
			if(tabuleiro[boneco.getX()][(boneco.getY())-1] instanceof Parede){
				System.out.println("Bateu na parede!");
			} else if (tabuleiro[boneco.getX()][(boneco.getY())-1] instanceof Fonte){
				System.out.println("Cabosse!");
			} else {
				tabuleiro[boneco.getX()][(boneco.getY())] = new Chao(new Ponto(boneco.getX(), boneco.getY()));
				tabuleiro[boneco.getX()][(boneco.getY())-1] = new Boneco(new Ponto(boneco.getX(), boneco.getY()-1));
			}
		} else if(direcao.equals("d")){
			if(tabuleiro[boneco.getX()][(boneco.getY())+1] instanceof Parede){
				System.out.println("Bateu na parede!");
			} else if (tabuleiro[boneco.getX()][(boneco.getY())+1] instanceof Fonte){
				System.out.println("Cabosse!");
			} else {
				tabuleiro[boneco.getX()][(boneco.getY())] = new Chao(new Ponto(boneco.getX(), boneco.getY()));
				tabuleiro[boneco.getX()][(boneco.getY())+1] = new Boneco(new Ponto(boneco.getX(), boneco.getY()+1));
			}
		} else {
			System.out.println("Entrada inválida!");
		}
	}
	
	public Ponto achaFonte(){
		Ponto p = null;
		for(int i = 0; i < alturaTabuleiro; i++){
			for(int j = 0; j < larguraTabuleiro; j++){
				if(tabuleiro[i][j] instanceof Fonte){
					p = new Ponto(((Fonte)tabuleiro[i][j]).p.getX(),((Fonte)tabuleiro[i][j]).p.getY());
				}
				
			}
		}
		return p;
		
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
