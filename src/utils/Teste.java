package utils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

import sound.MultipleSound;
import sound.ObjectiveSound;
import sound.SoundAlter;
import sound.SoundSource;
import utils.Mapa;
import utils.Ponto;

public class Teste {
	
	private Ponto bonecao;
	private ArrayList<SoundSource> soundSources;
	private ArrayList<ObjectiveSound> objs;
	private SoundAlter soundAlter;
	public void MainVerdadeiro() throws IOException{
		Mapa map = new Mapa("fase2.txt");
		this.soundSources = new ArrayList<SoundSource>();
		this.objs = new ArrayList<ObjectiveSound>();
		this.soundAlter = new SoundAlter(objs);
		map.criarMapa(2);
		System.out.println("Boneco: " + map.achaBoneco().toString());
		//System.out.println("Fonte: " + map.achaFonte().toString());
		//MultipleSound sound = new MultipleSound();
		
		bonecao = new Ponto(map.achaBoneco().getX(), map.achaBoneco().getY(), map.achaBoneco().getZ());
		
		//MODIFICACAO
		try{
			AL.create();
		} catch (LWJGLException le) {
			le.printStackTrace();
			return;
		}
		AL10.alGetError();
		
		//posicao do listener
		FloatBuffer listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f/*1.0f, 1.0f, 1.0f*/ }).rewind();
		//velocidade do listener
		FloatBuffer listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
		/** Orientation of the listener. (first 3 elements are "at", second 3 are "up") */
		FloatBuffer listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f,  0.0f, 1.0f, 0.0f }).rewind();
		
		
		//sound.execute(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());
		SoundSource step1 = new SoundSource();
		step1.execute("p1.wav", 0.0f, 0.0f, 0.0f, listenerPos, listenerVel, listenerOri);
		SoundSource step2 = new SoundSource();
		step2.execute("p2.wav", 0.0f, 0.0f, 0.0f, listenerPos, listenerVel, listenerOri);
		SoundSource hitwall = new SoundSource();
		hitwall.execute("bodyhit.wav", 0.0f, 0.0f, 0.0f, listenerPos, listenerVel, listenerOri);
		SoundSource win = new SoundSource();
		win.execute("win.wav", 0.0f, 0.0f, 0.0f, listenerPos, listenerVel, listenerOri);
		//bugado
		//ObjectiveSound obj = new ObjectiveSound(0.0f+map.achaFonte().getX(), 0.0f+map.achaFonte().getY(),0.0f+ map.achaFonte().getZ(), bonecao);
		//(new Thread(obj)).start();
		//SoundSource objective = new SoundSource();
		System.out.println("X : " + map.achaBoneco().getX() + " - " + map.achaFonte().getX() + " = " + (map.achaBoneco().getX()-map.achaFonte().getX()));
		System.out.println("Y : " + map.achaFonte().getY() + " - " + map.achaBoneco().getY() + " = " + (map.achaFonte().getY()-map.achaBoneco().getY()));
		ObjectiveSound obj = new ObjectiveSound(0.0f+map.achaBoneco().getX()-map.achaFonte().getX(), 0.0f+map.achaFonte().getY()-map.achaBoneco().getY(),0.0f+ map.achaFonte().getZ(), listenerPos, listenerVel, listenerOri, "latido.wav", 5000);
		//ObjectiveSound obj = new ObjectiveSound(0.0f, -2.0f,0.0f+ map.achaFonte().getZ(), listenerPos, listenerVel, listenerOri);
		objs.add(obj);
		(new Thread(obj)).start();
		map.printaTudo();
		boolean exit = false;
		Scanner in = new Scanner(System.in);
		while (!exit){
			String aux = in.nextLine();
			System.out.println("\n");
			switch (aux){ 
				case "w": //map.moveBoneco("w"); /*sound.andar(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());*/ map.printaTudo();  break;
				case "a": //map.moveBoneco("a"); /*sound.andar(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());*/ map.printaTudo();  break;
				case "s": //map.moveBoneco("s"); w/*sound.andar(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());*/ map.printaTudo();  break;
				case "d": 
					String  ret = map.moveBoneco(aux);
					if(ret.equals("false")){
//						10f, 0f, 0f, 0f, 0f, 0f
						//step1.execute("p1.wav", 0f, 0f, 0f, 0f, 0f, 0f);
						//step2.execute("p2.wav", 0f, 0f, 0f, 0f, 0f, 0f);
						step1.playSound();
						step2.playSound();
						//AQUI DANILO
						String s = map.acharVisao();
						this.soundAlter.alter(aux, s);
						//nova posicao da fonte
						//obj.setObjectPosition(obj_x, obj_y, obj_z);
					}else if (ret.equals("true")){
						//hitwall.execute("bodyhit.wav", 0f, 0f, 0f, 0f, 0f, 0f);
						hitwall.playSound();
					} else {
						//win.execute("win.wav", 0f, 0f, 0f, 0f, 0f, 0f);
						obj.setJogando(false);
						win.playSound();
					}
					/*sound.andar(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());*/ 
					
					bonecao.setX(map.achaBoneco().getX());
					bonecao.setY(map.achaBoneco().getY());
					bonecao.setZ(map.achaBoneco().getZ());
					map.printaTudo();  
					break;
				case "e": exit = !exit;  break;
				case " ": 
					/*objective.execute 
					("latido.wav", 0.0f+map.achaFonte().getY(), 0.0f+map.achaFonte().getX(), 0.0f+map.achaFonte().getZ(), 0.0f+map.achaBoneco().getY(), 0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getZ());
					*/break;
			}
		}
		in.close();
	}
	
	public static void main(String[] args) throws IOException {
		(new Teste()).MainVerdadeiro();
	}

}
