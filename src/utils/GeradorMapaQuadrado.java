package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeradorMapaQuadrado {
	
	private int altura;
	private int largura;
	
	public GeradorMapaQuadrado(int a, int l) throws IOException{
		this.altura	= a;
		this.largura = l;
		gerador();
		
	}
	
	public void gerador() throws IOException{
		FileWriter arq = new FileWriter("mapaQuadrado.txt");
	    PrintWriter gravarArq = new PrintWriter(arq);
	    
	    gravarArq.print("4\n");
	    gravarArq.print(altura + "\n");
	    gravarArq.print(largura + "\n");
	    gravarArq.print("0 0 0 "+ (altura-1) + "\n");
	    gravarArq.print((largura-1) + " 0 0 0\n");
	    gravarArq.print((largura-1) + " " + (altura-1) + " 0 " + (altura-1) +"\n");
	    gravarArq.print((largura-1) + " " + (altura-1) + " " + (largura-1) +" 0\n");
	    
	    arq.close();
	    
	}
	

}
