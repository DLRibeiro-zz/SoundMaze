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
	private Boneco boneco;
	private Fonte fonte;
	private int visao;
	private int[][] andar = {{-1,0,1,0}, {0,1,0,-1}, {1,0,-1,0}, {0,-1,0,1}};
	
	public Mapa (String path){
		this.path = path;
		this.retas = new ArrayList<Reta>();
		this.visao = 0;
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
				} 
				
			}
		}
		
	}
	
	public Ponto achaBoneco(){
//		Ponto p = null;
//		for(int i = 0; i < alturaMapa; i++){
//			for(int j = 0; j < larguraMapa; j++){
//				if(mapa[i][j] instanceof Boneco){
//					p = new Ponto(((Boneco)mapa[i][j]).p.getX(),((Boneco)mapa[i][j]).p.getY());
//				}
//			}
//		}
		return this.boneco.p;
	}
	
	public Ponto achaFonte(){
//		Ponto p = null;
//		for(int i = 0; i < alturaMapa; i++){
//			for(int j = 0; j < larguraMapa; j++){
//				if(mapa[i][j] instanceof Fonte){
//					p = new Ponto(((Fonte)mapa[i][j]).p.getX(),((Fonte)mapa[i][j]).p.getY());
//				}
//				
//			}
//		}
		return this.fonte.p;
		
	}
	//movimentação apenas da posição do boneco
	public String moveBoneco(String direcao){
		Ponto boneco = achaBoneco();
		if(direcao.equals("w")){
//			if(mapa[boneco.getX()-1][(boneco.getY())] instanceof Parede){
//				System.out.println("Bateu na parede!");
//				return "true";
//			} else if (mapa[boneco.getX()-1][(boneco.getY())] instanceof Fonte){
//				System.out.println("Cabosse!");
//				return "hit";
//			} else {
//				mapa[boneco.getX()][(boneco.getY())] = new Chao(new Ponto(boneco.getX(), boneco.getY()));
//
//				this.boneco = new Boneco(new Ponto(boneco.getX()-1, boneco.getY()));
//				mapa[boneco.getX()-1][(boneco.getY())] = this.boneco;
//			}
			int x = andar[visao][0];
			int y = andar[visao][1];
			if(mapa[boneco.getX()+x][boneco.getY()+y] instanceof Parede){
				System.out.println("Bateu na parede!");
				return "true";
			} else if (mapa[boneco.getX()+x][boneco.getY()+y] instanceof Fonte){
				System.out.println("Cabosse!");
				return "hit";
			} else {
				mapa[boneco.getX()][(boneco.getY())] = new Chao(new Ponto(boneco.getX(), boneco.getY()));
				this.boneco = new Boneco(new Ponto(boneco.getX()+x, boneco.getY()+y));
				this.boneco.setVisao(visao);
				mapa[boneco.getX()+x][boneco.getY()+y] = this.boneco;
			}
		} else if(direcao.equals("s")){
//			if(mapa[boneco.getX()+1][(boneco.getY())] instanceof Parede){
//				System.out.println("Bateu na parede!");
//				return "true";
//			} else if (mapa[boneco.getX()+1][(boneco.getY())] instanceof Fonte){
//				System.out.println("Cabosse!");
//				return "hit";
//			} else {
//				mapa[boneco.getX()][(boneco.getY())] = new Chao(new Ponto(boneco.getX(), boneco.getY()));
//				this.boneco = new Boneco(new Ponto(boneco.getX()+1, boneco.getY()));
//				mapa[boneco.getX()+1][(boneco.getY())] = this.boneco;
//
//			}
			int x = andar[visao][2];
			int y = andar[visao][3];
			if(mapa[boneco.getX()+x][boneco.getY()+y] instanceof Parede){
				System.out.println("Bateu na parede!");
				return "true";
			} else if (mapa[boneco.getX()+x][boneco.getY()+y] instanceof Fonte){
				System.out.println("Cabosse!");
				return "hit";
			} else {
				mapa[boneco.getX()][(boneco.getY())] = new Chao(new Ponto(boneco.getX(), boneco.getY()));
				this.boneco = new Boneco(new Ponto(boneco.getX()+x, boneco.getY()+y));
				this.boneco.setVisao(visao);
				mapa[boneco.getX()+x][boneco.getY()+y] = this.boneco;
			}
		} else if(direcao.equals("a")){
//			if(mapa[boneco.getX()][(boneco.getY())-1] instanceof Parede){
//				System.out.println("Bateu na parede!");
//				return "true";
//			} else if (mapa[boneco.getX()][(boneco.getY())-1] instanceof Fonte){
//				System.out.println("Cabosse!");
//				return "hit";
//			} else {
//				mapa[boneco.getX()][(boneco.getY())] = new Chao(new Ponto(boneco.getX(), boneco.getY()));
//
//				this.boneco = new Boneco(new Ponto(boneco.getX(), boneco.getY()-1));
//				mapa[boneco.getX()][(boneco.getY())-1] = this.boneco;
//
//			}
			if(visao == 0) visao = 3;
			else visao--;
			this.boneco.setVisao(visao);
		} else if(direcao.equals("d")){
//			if(mapa[boneco.getX()][(boneco.getY())+1] instanceof Parede){
//				System.out.println("Bateu na parede!");
//				return "true";
//			} else if (mapa[boneco.getX()][(boneco.getY())+1] instanceof Fonte){
//				System.out.println("Cabosse!");
//				return "hit";
//			} else {
//				mapa[boneco.getX()][(boneco.getY())] = new Chao(new Ponto(boneco.getX(), boneco.getY()));
//				this.boneco = new Boneco(new Ponto(boneco.getX(), boneco.getY()+1));
//				mapa[boneco.getX()][(boneco.getY())+1] = this.boneco;
//			}
			this.visao = (visao+1)%4;
			this.boneco.setVisao(visao);
		} else {
				System.out.println("Entrada inválida!");
		}
		return "false";
	}
	
	public String acharVisao(){
		return this.boneco.getVisao();
	}

	
	public void iniciaJogo(){
		Random random = new Random();
		Boolean b = false;
		while(!b){
			int xBoneco = random.nextInt(this.alturaMapa);
			int yBoneco = random.nextInt(this.larguraMapa);
			if(mapa[xBoneco][yBoneco] instanceof Parede == false){
				boneco = new Boneco (new Ponto(xBoneco, yBoneco));
				mapa[xBoneco][yBoneco] = boneco;
				b = true;
			}
		}
		b = false;
		while(!b){
			int xFonte = random.nextInt(this.alturaMapa);
			int yFonte = random.nextInt(this.larguraMapa);
			if(mapa[xFonte][yFonte] instanceof Parede == false && 
					mapa[xFonte][yFonte] instanceof Boneco == false){
				fonte = new Fonte (new Ponto(xFonte, yFonte));
				mapa[xFonte][yFonte] = fonte;
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
					System.out.print(((Boneco)mapa[i][j]).getVisao());
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
