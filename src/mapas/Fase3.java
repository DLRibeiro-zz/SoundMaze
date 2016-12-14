package mapas;

import java.io.FileNotFoundException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.BufferUtils;

import sound.ObjectiveSound;
import sound.SoundAlter;
import sound.SoundSource;
import utils.Boneco;
import utils.Fonte;
import utils.Mapa;
import utils.Ponto;

public class Fase3 {
	
	public int[][] pos2 = {{4,1},{1,10}};
	
	/*public String[][] mapa = {
			{"X","X","X","X","X","X","X","X","X","X","X","X"},
			{"X","_","_","_","_","_","_","_","_","_","3","X"},
			{"X","_","_","_","_","_","_","_","_","_","_","X"},
			{"X","X","X","X","X","X","X","X","_","X","X","X"},
			{"X","2","_","_","_","_","_","X","_","_","_","X"},
			{"X","_","X","X","X","X","_","X","_","X","_","X"},
			{"X","_","_","_","_","X","_","_","_","X","_","X"},
			{"X","_","X","X","_","X","X","X","X","X","X","X"},
			{"X","_","_","X","_","X","1","_","_","_","_","X"},
			{"X","X","_","X","_","X","_","X","X","X","_","X"},
			{"X","_","_","X","_","_","_","X","P","_","_","X"},
			{"X","X","X","X","X","X","X","X","X","X","X","X"}
	};*/
	public String[][] mapa = {
			{"X","X","X","X","X","X","X","X","X","X","X","X"},
			{"X","_","_","_","_","_","_","_","_","_","_","X"},
			{"X","_","_","_","_","_","_","_","_","_","_","X"},
			{"X","X","X","X","X","X","X","X","_","X","X","X"},
			{"X","_","_","_","_","_","_","X","_","_","_","X"},
			{"X","_","X","X","X","X","_","X","_","X","_","X"},
			{"X","_","_","_","_","X","_","_","_","X","_","X"},
			{"X","_","X","X","_","X","X","X","X","X","X","X"},
			{"X","_","_","X","_","X","1","_","_","_","_","X"},
			{"X","X","_","X","_","X","_","X","X","X","_","X"},
			{"X","_","_","X","_","_","_","X","P","_","_","X"},
			{"X","X","X","X","X","X","X","X","X","X","X","X"}
	};
	
	private Ponto bonecao;
	private ArrayList<ObjectiveSound> objs;
	private SoundAlter soundAlter;
	private Scanner in;
	
	public Fase3(Scanner in){
		this.in = in;
	}
	public Fase3(){
		
	}
	
	public void rodar() throws FileNotFoundException{
		Mapa map = new Mapa();
		map.criarMapa(3);
		
		this.objs = new ArrayList<ObjectiveSound>();
		this.soundAlter = new SoundAlter(objs);
		//map.criarMapa(2);
		System.out.println("Boneco: " + map.achaBoneco().toString());
		//System.out.println("Fonte: " + map.achaChave().toString());
		
		bonecao = new Ponto(map.achaBoneco().getX(), map.achaBoneco().getY(), map.achaBoneco().getZ());
		
		//MODIFICACAO
//		try{
//			AL.create();
//		} catch (LWJGLException le) {
//			le.printStackTrace();
//			return;
//		}
//		AL10.alGetError();
		
		//posicao do listener
		FloatBuffer listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f/*1.0f, 1.0f, 1.0f*/ }).rewind();
		//velocidade do listener
		FloatBuffer listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
		/** Orientation of the listener. (first 3 elements are "at", second 3 are "up") */
		FloatBuffer listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f,  0.0f, 1.0f, 0.0f }).rewind();
		
		
		SoundSource step1 = new SoundSource();
		step1.execute("p1.wav", 0.0f, 0.0f, 0.0f, listenerPos, listenerVel, listenerOri, 1.0f);
		SoundSource step2 = new SoundSource();
		step2.execute("p2.wav", 0.0f, 0.0f, 0.0f, listenerPos, listenerVel, listenerOri, 1.0f);
		SoundSource hitwall = new SoundSource();
		hitwall.execute("latido.wav", 0.0f, 0.0f, 0.0f, listenerPos, listenerVel, listenerOri, 1.0f);
		SoundSource win = new SoundSource();
		win.execute("open_door.wav", 0.0f, 0.0f, 0.0f, listenerPos, listenerVel, listenerOri, 1.0f);
//		SoundSource pegouChave = new SoundSource();
//		pegouChave.execute("pegando_chave_novo.wav", 0.0f, 0.0f, 0.0f, listenerPos, listenerVel, listenerOri, 1.0f);
//		SoundSource abrirCorrentes = new SoundSource();
//		abrirCorrentes.execute("unlocking.wav", 0.0f, 0.0f, 0.0f, listenerPos, listenerVel, listenerOri, 1.0f);
//		System.out.println("X : " + map.achaBoneco().getX() + " - " + map.achaFonte().getX() + " = " + (map.achaBoneco().getX()-map.achaFonte().getX()));
//		System.out.println("Y : " + map.achaFonte().getY() + " - " + map.achaBoneco().getY() + " = " + (map.achaFonte().getY()-map.achaBoneco().getY()));
		ObjectiveSound refem = new ObjectiveSound(0.0f+map.achaBoneco().getX()-map.achaRefem().getX(), 0.0f+map.achaRefem().getY()-map.achaBoneco().getY(),0.0f+ map.achaRefem().getZ(), listenerPos, listenerVel, listenerOri, "come on lets go.wav", 5000, 1.0f);
		objs.add(refem);
		(new Thread(refem)).start();
		ObjectiveSound refemPt2 = null;
		ObjectiveSound refemPt3 = null;
		int contador = 1;
//		ObjectiveSound chave = new ObjectiveSound(0.0f+map.achaBoneco().getX()-map.achaChave().getX(), 0.0f+map.achaChave().getY()-map.achaBoneco().getY(),0.0f+ map.achaChave().getZ(), listenerPos, listenerVel, listenerOri, "key-estante.wav", 5000, 1.0f);
//		objs.add(chave);
//		(new Thread(chave)).start();
//		ObjectiveSound radio = new ObjectiveSound(0.0f+map.achaBoneco().getX()-map.achaRadio().getX(), 0.0f+map.achaRadio().getY()-map.achaBoneco().getY(),0.0f+ map.achaRadio().getZ(), listenerPos, listenerVel, listenerOri, "radio_inter.wav", 0, 0.05f);
//		objs.add(radio);
//		(new Thread(radio)).start();
		ObjectiveSound chuva = new ObjectiveSound(0.0f, 0.0f, 20.0f, listenerPos, listenerVel, listenerOri, "rain-01-cut.wav", 0, 0.15f);
		(new Thread(chuva)).start();
		map.printaTudo();
		boolean exit = false;
//		Scanner in = new Scanner(System.in);
		while (!exit){
			String aux = in.nextLine();
			System.out.println("\n");
			switch (aux){ 
				case "w": 
				case "a": 
				case "s": 
				case "d": 
					String  ret = map.moveBoneco(aux);
					if(ret.equals("false")){
						step1.playSound();
						step2.playSound();
						
						String s = map.acharVisao();
						this.soundAlter.alter(aux, s);
					} else if (ret.equals("true")){
						hitwall.playSound();
					} else if (ret.equals("chave")){
						/*if(!estaChave){
							chave.setJogando(false);
							pegouChave.playSound();
							estaChave = true;
						}*/
					} else if (ret.equals("refem")){
						if(contador == 1){
							System.out.println(" ( " + pos2[0][0] + " , " + pos2[0][1] + " ) ");
							//Fonte novoLugar = new Fonte(new Ponto(pos2[0][0], pos2[0][1]));
							//map.setFonte(novoLugar, aux);
							Boneco novoLugar = new Boneco(new Ponto(pos2[0][0], pos2[0][1]), "R");
							map.setRefem(novoLugar, aux);
							System.out.println("X : " + map.achaBoneco().getX() + " - " + map.achaRefem().getX() + " = " + (map.achaBoneco().getX()-map.achaRefem().getX()));
							System.out.println("Y : " + map.achaRefem().getY() + " - " + map.achaBoneco().getY() + " = " + (map.achaRefem().getY()-map.achaBoneco().getY()));
							refem.setJogando(false);
							objs.remove(0);
							if(map.acharVisao().equals("N") || map.acharVisao().equals("S")){
								refemPt2 = new ObjectiveSound(0.0f+map.achaBoneco().getX()-map.achaRefem().getX(), 0.0f+map.achaRefem().getY()-map.achaBoneco().getY(),0.0f+ map.achaRefem().getZ(), listenerPos, listenerVel, listenerOri, "come on lets go.wav", 5000, 1.0f);
								//refem.setObj_x(0.0f+map.achaBoneco().getX()-map.achaFonte().getX());
								//refem.setObj_y(0.0f+map.achaFonte().getY()-map.achaBoneco().getY());
							}else if(map.acharVisao().equals("O")){
								refemPt2 = new ObjectiveSound(0.0f+map.achaBoneco().getY()-map.achaRefem().getY(), 0.0f+map.achaBoneco().getX()-map.achaRefem().getX(), 0.0f+ map.achaRefem().getZ(), listenerPos, listenerVel, listenerOri, "come on lets go.wav", 5000, 1.0f);
								//refem.setObj_y(0.0f+map.achaBoneco().getX()-map.achaFonte().getX());
								//refem.setObj_x(0.0f+map.achaFonte().getY()-map.achaBoneco().getY());
							}else if(map.acharVisao().equals("L")){
								refemPt2 = new ObjectiveSound(0.0f+map.achaRefem().getY()-map.achaBoneco().getY(), 0.0f+map.achaRefem().getX()-map.achaBoneco().getX(), 0.0f+ map.achaRefem().getZ(), listenerPos, listenerVel, listenerOri, "come on lets go.wav", 5000, 1.0f);
							}
							objs.add(refemPt2);
							(new Thread(refemPt2)).start();
							contador++;
							//System.out.println("Ja passou");
							//refem.setObj_z(0.0f+ map.achaFonte().getZ());
							//refem.setArquivo("come on lets go.wav");
						}else{
							System.out.println(" ( " + pos2[1][0] + " , " + pos2[1][1] + " ) ");
							Fonte novoLugar = new Fonte(new Ponto(pos2[1][0], pos2[1][1]));
							map.setFonte(novoLugar, aux);
							//Boneco novoLugar = new Boneco(new Ponto(pos2[0][0], pos2[0][1]), "R");
							//map.setRefem(novoLugar, aux);
							System.out.println("X : " + map.achaBoneco().getX() + " - " + map.achaFonte().getX() + " = " + (map.achaBoneco().getX()-map.achaFonte().getX()));
							System.out.println("Y : " + map.achaFonte().getY() + " - " + map.achaBoneco().getY() + " = " + (map.achaFonte().getY()-map.achaBoneco().getY()));
							refemPt2.setJogando(false);
							objs.remove(0);
							if(map.acharVisao().equals("N")){
								refemPt3 = new ObjectiveSound(0.0f+map.achaBoneco().getX()-map.achaFonte().getX(), 0.0f+map.achaFonte().getY()-map.achaBoneco().getY(),0.0f+ map.achaFonte().getZ(), listenerPos, listenerVel, listenerOri, "come on lets go.wav", 5000, 1.0f);
								//refem.setObj_x(0.0f+map.achaBoneco().getX()-map.achaFonte().getX());
								//refem.setObj_y(0.0f+map.achaFonte().getY()-map.achaBoneco().getY());
							}else if(map.acharVisao().equals("S")){
								refemPt3 = new ObjectiveSound(0.0f+map.achaFonte().getX()-map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY()-map.achaFonte().getY(),0.0f+ map.achaFonte().getZ(), listenerPos, listenerVel, listenerOri, "come on lets go.wav", 5000, 1.0f);
							}else if(map.acharVisao().equals("L") || map.acharVisao().equals("O")){
								refemPt3 = new ObjectiveSound(0.0f+map.achaBoneco().getY()-map.achaFonte().getY(), 0.0f+map.achaBoneco().getX()-map.achaFonte().getX(), 0.0f+ map.achaFonte().getZ(), listenerPos, listenerVel, listenerOri, "come on lets go.wav", 5000, 1.0f);
								//refem.setObj_y(0.0f+map.achaBoneco().getX()-map.achaFonte().getX());
								//refem.setObj_x(0.0f+map.achaFonte().getY()-map.achaBoneco().getY());
							}
							objs.add(refemPt3);
							(new Thread(refemPt3)).start();
							//contador++;
						}
					} else {
						//refem.setJogando(false);
						refemPt3.setJogando(false);
						win.playSound();
						chuva.setJogando(false);
						exit = !exit;
						
						step1.killALData();
						step2.killALData();
						hitwall.killALData();
						win.killALData();
						//pegouChave.killALData();
						//abrirCorrentes.killALData();
					}
					
					bonecao.setX(map.achaBoneco().getX());
					bonecao.setY(map.achaBoneco().getY());
					bonecao.setZ(map.achaBoneco().getZ());
					map.printaTudo();  
					break;
				case "e": exit = !exit;  break;
			}
		}
//		in.close();
	}
	
}
