package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

import mapas.Fase1;
import mapas.Fase2;
import mapas.Fase3;

public class Apagar implements Runnable {
	private ArrayList<String> comando;
	
	public Apagar(ArrayList<String> comando){
		this.comando = comando;
	}
	
	public /*static*/ void /*main(String[] args)*/ run() {
		Scanner in = new Scanner(System.in);
		
		try{
			AL.create();
		} catch (LWJGLException le) {
			le.printStackTrace();
			return;
		}
		AL10.alGetError();
		
		//Fase1 fase1 = new Fase1(in);
//		boolean topzera = true;
//		while(topzera){
//			if(this.comando.size() > 0){
//				System.out.println(this.comando.get(0));
//			}else{
//				System.out.println("Nothing");
//			}
//		}
		Fase1 fase1 = new Fase1(this.comando);
		try {
			fase1.rodar();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Acabou a Fase 1");
		Fase2 fase2 = new Fase2(in);
		try {
			fase2.rodar();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Acabou a Fase 2");
		Fase3 fase3 = new Fase3(in);
		try {
			fase3.rodar();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Acabou a Fase 3");
		
		
		in.close();
		//AL.destroy();
	}

}
