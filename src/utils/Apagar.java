package utils;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

import mapas.Fase1;
import mapas.Fase2;

public class Apagar {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try{
			AL.create();
		} catch (LWJGLException le) {
			le.printStackTrace();
			return;
		}
		AL10.alGetError();
		
		Fase1 fase1 = new Fase1(in);
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
		
		
		in.close();
		//AL.destroy();
	}

}
