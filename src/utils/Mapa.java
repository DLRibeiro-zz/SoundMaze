package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import mapas.*;


public class Mapa {
	private String path;
	private ArrayList<Reta> retas;
	private int alturaMapa;
	private int larguraMapa;
	private Objetos[][] mapa;
	private Boneco boneco;
	private Fonte fonte;
	private Radio radio;
	private Chave chave;
	private Boneco refem;
	private int visao;
	private int[][] andar = {{-1,0,1,0}, {0,1,0,-1}, {1,0,-1,0}, {0,-1,0,1}};
	private Fase1 f1 = new Fase1();
	private Fase2 f2 = new Fase2();
	private Fase3 f3 = new Fase3();
	private String[][] mapaF;
	
	public Mapa (){
		//this.path = path;
		this.retas = new ArrayList<Reta>();
		this.visao = 0;
	}
	
	public void setRetas (ArrayList<Reta> r){
		this.retas = r;
	}
	
	public void criarMapa(int fase){
		
		if(fase == 1){
			mapaF = f1.mapa;
		}else if(fase == 2){
			mapaF = f2.mapa;
		}else if(fase == 3){
			mapaF = f3.mapa;
		}else{
			mapaF = null;
		}
		int len = mapaF.length;
		this.mapa = new Objetos[len][len];
		montarTabuleiro(fase);
	}
	
	public void montarTabuleiro(int fase){
		
		if(mapaF == null){
			System.err.println("numero da fase errado");
			return;
		}
		
		int len = mapaF.length;
		for(int i = 0; i < len; i++){
			for(int j = 0; j < len; j++){
				if(mapaF[i][j].equals("X")) mapa[i][j] = new Parede(new Ponto(i,j));
				else if(mapaF[i][j].equals("_")) mapa[i][j] = new Chao(new Ponto(i,j));
				else if(mapaF[i][j].equals("P")) {
					this.boneco = new Boneco(new Ponto(i,j), "N");
					mapa[i][j] = this.boneco;
				}		
				else if(mapaF[i][j].equals("R") || mapaF[i][j].equals("1") || mapaF[i][j].equals("2") || mapaF[i][j].equals("3")){
					this.refem = new Boneco(new Ponto(i,j), "R");
					mapa[i][j] = this.refem;
				}
				else if(mapaF[i][j].equals("F")){
					this.fonte = new Fonte(new Ponto(i,j));
					mapa[i][j] = this.fonte;
				}
				else if(mapaF[i][j].equals("A")) {
					this.radio = new Radio(new Ponto(i,j));
					mapa[i][j] = this.radio;
				}
				else if(mapaF[i][j].equals("C")){
					this.chave = new Chave(new Ponto(i,j));
					mapa[i][j] = this.chave;
				}
				else{
					System.err.println("problema no mapa");
					return;
				}
			}
		}
			
	}
	
	public Ponto achaBoneco(){
		return this.boneco.p;
	}
	
	public Ponto achaFonte(){
		return this.fonte.p;
		
	}
	
	public Ponto achaRefem(){
		return this.refem.p;
		
	}
	
	public Ponto achaRadio(){
		return this.radio.p;
		
	}
	public Ponto achaChave(){
		return this.chave.p;
	}
	
	public void setFonte(Fonte fonte, String direcao){
		this.fonte = fonte;
		mapa[fonte.p.getX()][fonte.p.getY()] = this.fonte;
		mapaF[fonte.p.getX()][fonte.p.getY()] = "F";
		Ponto boneco = achaBoneco();
		if(direcao.equals("w")){
			int x = andar[visao][0];
			int y = andar[visao][1];
			
			if (mapa[boneco.getX()+x][boneco.getY()+y] instanceof Boneco){
				mapa[boneco.getX()+x][boneco.getY()+y] = new Chao(new Ponto(boneco.getX()+x, boneco.getY()+y));
				mapaF[boneco.getX()+x][boneco.getY()+y] = "_";
			}
		}else if(direcao.equals("s")){
			int x = andar[visao][2];
			int y = andar[visao][3];
			
			if (mapa[boneco.getX()+x][boneco.getY()+y] instanceof Boneco){
				mapa[boneco.getX()+x][boneco.getY()+y] = new Chao(new Ponto(boneco.getX()+x, boneco.getY()+y));
				mapaF[boneco.getX()+x][boneco.getY()+y] = "_";
			}
		}
	}
	
	public String moveBoneco(String direcao){
		Ponto boneco = achaBoneco();
		if(direcao.equals("w")){
			int x = andar[visao][0];
			int y = andar[visao][1];
			if(mapa[boneco.getX()+x][boneco.getY()+y] instanceof Parede){
				System.out.println("Bateu na parede!");
				return "true";
			} else if (mapa[boneco.getX()+x][boneco.getY()+y] instanceof Fonte){
				System.out.println("Cabosse!");
				return "hit";
			} else if (mapa[boneco.getX()+x][boneco.getY()+y] instanceof Boneco){
				System.out.println("Refem!");
				return "refem";
			} else if (mapa[boneco.getX()+x][boneco.getY()+y] instanceof Chave){
				System.out.println("Pegou a chave!");
				return "chave";
			} else {
				mapa[boneco.getX()][(boneco.getY())] = new Chao(new Ponto(boneco.getX(), boneco.getY()));
				this.boneco = new Boneco(new Ponto(boneco.getX()+x, boneco.getY()+y), "P");
				this.boneco.setVisao(visao);
				mapa[boneco.getX()+x][boneco.getY()+y] = this.boneco;
			}
		} else if(direcao.equals("s")){
			int x = andar[visao][2];
			int y = andar[visao][3];
			if(mapa[boneco.getX()+x][boneco.getY()+y] instanceof Parede){
				System.out.println("Bateu na parede!");
				return "true";
			} else if (mapa[boneco.getX()+x][boneco.getY()+y] instanceof Fonte){
				System.out.println("Cabosse!");
				return "hit";
			} else if (mapa[boneco.getX()+x][boneco.getY()+y] instanceof Boneco){
				System.out.println("Refem!");
				return "refem";
			} else if (mapa[boneco.getX()+x][boneco.getY()+y] instanceof Chave){
				System.out.println("Pegou a chave!");
				return "chave";
			} else {
				mapa[boneco.getX()][(boneco.getY())] = new Chao(new Ponto(boneco.getX(), boneco.getY()));
				this.boneco = new Boneco(new Ponto(boneco.getX()+x, boneco.getY()+y), "P");
				this.boneco.setVisao(visao);
				mapa[boneco.getX()+x][boneco.getY()+y] = this.boneco;
			}
		} else if(direcao.equals("a")){
			if(visao == 0) visao = 3;
			else visao--;
			this.boneco.setVisao(visao);
		} else if(direcao.equals("d")){
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

	
	public void printaTudo(){
		int len = mapaF.length;
		for(int i = 0; i < len; i++){
			for(int j = 0; j < len; j++){
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
				} else if(mapa[i][j] instanceof Radio){
					System.out.print(((Radio)mapa[i][j]).toString());
					System.out.print(" ");
				} else if(mapa[i][j] instanceof Chave){
					System.out.print(((Chave)mapa[i][j]).toString());
					System.out.print(" ");
				}
				
			}
			System.out.print("\n");
		}	
	}

}
