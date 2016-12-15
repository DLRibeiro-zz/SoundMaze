package mapas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

import sound.ObjectiveSound;
import sound.SoundAlter;
import sound.SoundSource;
import utils.Mapa;
import utils.Ponto;

public class Fase1 {
	
	public String[][] mapa = {
			{"X","X","X","X","X","X","X"},
			{"X","_","_","_","_","F","X"},
			{"X","_","_","_","_","_","X"},
			{"X","_","_","_","_","_","X"},
			{"X","_","_","_","_","_","X"},
			{"X","P","_","_","_","_","X"},
			{"X","X","X","X","X","X","X"}
	};
	
	private Ponto bonecao;
	private ArrayList<ObjectiveSound> objs;
	private SoundAlter soundAlter;
	private Scanner in;
	private ArrayList<String> comando;
	
	public Fase1(ArrayList<String> comando){
		this.comando = comando;
	}
	public Fase1(Scanner in){
		this.in = in;
	}
	public Fase1(){
		
	}
	
	public void rodar() throws FileNotFoundException{
		Mapa map = new Mapa();
		map.criarMapa(1);
		
		this.objs = new ArrayList<ObjectiveSound>();
		this.soundAlter = new SoundAlter(objs);
		//map.criarMapa(2);
		System.out.println("Boneco: " + map.achaBoneco().toString());
		System.out.println("Fonte: " + map.achaFonte().toString());
		
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
		hitwall.execute("bodyhit.wav", 0.0f, 0.0f, 0.0f, listenerPos, listenerVel, listenerOri, 1.0f);
		SoundSource win = new SoundSource();
		win.execute("open_door.wav", 0.0f, 0.0f, 0.0f, listenerPos, listenerVel, listenerOri, 1.0f);
		System.out.println("X : " + map.achaBoneco().getX() + " - " + map.achaFonte().getX() + " = " + (map.achaBoneco().getX()-map.achaFonte().getX()));
		System.out.println("Y : " + map.achaFonte().getY() + " - " + map.achaBoneco().getY() + " = " + (map.achaFonte().getY()-map.achaBoneco().getY()));
		ObjectiveSound obj = new ObjectiveSound(0.0f+map.achaBoneco().getX()-map.achaFonte().getX(), 0.0f+map.achaFonte().getY()-map.achaBoneco().getY(),0.0f+ map.achaFonte().getZ(), listenerPos, listenerVel, listenerOri, "latido.wav", 5000, 1.0f);
		objs.add(obj);
		(new Thread(obj)).start();
		ObjectiveSound obj2 = new ObjectiveSound(0.0f, 0.0f, 20.0f, listenerPos, listenerVel, listenerOri, "rain-01-cut.wav", 0, 0.15f);
		(new Thread(obj2)).start();
		map.printaTudo();
		boolean exit = false;
//		Scanner in = new Scanner(System.in);
		String aux = "";
		while (!exit){
			//String aux = in.nextLine();
			boolean topzera = true;
			while(topzera){
				if(this.comando.size() > 0){
					topzera = false;
					System.out.println(this.comando.get(0));
					aux = this.comando.get(0);
				}else{
					System.out.print("");
				}
			}
//			while(this.comando.size() < 1){}
			//System.out.println("saiu");
//			aux = this.comando.get(0);
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
					}else if (ret.equals("true")){
						hitwall.playSound();
					} else {
						obj.setJogando(false);
						obj2.setJogando(false);
						win.playSound();
						exit = !exit;
						
						step1.killALData();
						step2.killALData();
						hitwall.killALData();
						win.killALData();
					}
					
					bonecao.setX(map.achaBoneco().getX());
					bonecao.setY(map.achaBoneco().getY());
					bonecao.setZ(map.achaBoneco().getZ());
					map.printaTudo();  
					break;
				case "e": exit = !exit;  break;
			}
			this.comando.remove(0);
		}
//		in.close();
	}

}
