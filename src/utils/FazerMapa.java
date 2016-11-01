package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FazerMapa {
	String content;
	StringTokenizer st;
	ArrayList<Reta> retas;
	int qtdRetas;
	int alturaMapa;
	int larguraMapa;
	public FazerMapa(String caminho) throws IOException{
		this.retas = new ArrayList<Reta>();
		this.content = readFile(caminho);
		this.st = new StringTokenizer(this.content);
		this.qtdRetas = Integer.parseInt(st.nextToken());
		this.alturaMapa = Integer.parseInt(st.nextToken());
		this.larguraMapa = Integer.parseInt(st.nextToken());
		for(int i = 0; i < qtdRetas; i++){
			int p1x = Integer.parseInt(st.nextToken());
			int p1y = Integer.parseInt(st.nextToken());
			Ponto p1 = new Ponto(p1x, p1y);
			int p2x = Integer.parseInt(st.nextToken());
			int p2y = Integer.parseInt(st.nextToken());
			Ponto p2 = new Ponto(p2x,p2y);
			Reta r = new Reta(p1, p2);
			this.retas.add(r);
		}
	}
	
	String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}

	public ArrayList<Reta> getRetas() {
		return retas;
	}

	public void setRetas(ArrayList<Reta> retas) {
		this.retas = retas;
	}
	
}
