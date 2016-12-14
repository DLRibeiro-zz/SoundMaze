package sound;

import java.io.FileNotFoundException;
import java.nio.FloatBuffer;

import utils.Ponto;

public class ObjectiveSound implements Runnable{
	private float obj_x;
	private float obj_y;
	private float obj_z;
	private SoundSource source;
	//private Ponto boneco;
	
	private FloatBuffer listenerPos;
	private FloatBuffer listenerVel;
	private FloatBuffer listenerOri;
	
	private boolean jogando;
	
	private String arquivo;
	private int intervalo;
	
	public ObjectiveSound(Float obj_x, Float obj_y, Float obj_z, FloatBuffer listenerPosA, FloatBuffer listenerVelA, FloatBuffer listenerOriA, String arquivo, int intervalo/*Ponto bonecao*/){
		this.obj_x = obj_x;
		this.obj_y = obj_y;
		this.obj_z = obj_z;
		//this.boneco = bonecao;
		this.listenerPos = listenerPosA;
		this.listenerVel = listenerVelA;
		this.listenerOri = listenerOriA;
		
		this.jogando = true;
		
		this.arquivo = arquivo;
		this.intervalo = intervalo;
	}
	
	
	public void setObjectPosition(Float obj_x, Float obj_y, Float obj_z){
		this.obj_x = obj_x;
		this.obj_y = obj_y;
		this.obj_z = obj_z;
	}
	
	public void setJogando(boolean jogando){
		this.jogando = jogando;
	}
	
	public float getObj_x() {
		return obj_x;
	}


	public void setObj_x(float obj_x) {
		this.obj_x = obj_x;
	}


	public float getObj_y() {
		return obj_y;
	}


	public void setObj_y(float obj_y) {
		this.obj_y = obj_y;
	}


	public float getObj_z() {
		return obj_z;
	}


	public void setObj_z(float obj_z) {
		this.obj_z = obj_z;
	}


	public SoundSource getSource() {
		return source;
	}


	public void setSource(SoundSource source) {
		this.source = source;
	}


	public FloatBuffer getListenerPos() {
		return listenerPos;
	}


	public void setListenerPos(FloatBuffer listenerPos) {
		this.listenerPos = listenerPos;
	}


	public FloatBuffer getListenerVel() {
		return listenerVel;
	}


	public void setListenerVel(FloatBuffer listenerVel) {
		this.listenerVel = listenerVel;
	}


	public FloatBuffer getListenerOri() {
		return listenerOri;
	}


	public void setListenerOri(FloatBuffer listenerOri) {
		this.listenerOri = listenerOri;
	}


	public boolean isJogando() {
		return jogando;
	}


	public void run(){
		SoundSource objective = new SoundSource();
		this.source = objective;
		/*try {
			objective.execute("latido.wav", obj_y, obj_x, obj_z, listenerPos, listenerVel, listenerOri);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}*/
		while(jogando){
			try {
				objective.execute(/*"latido.wav"*/arquivo, obj_y, obj_x, obj_z, listenerPos, listenerVel, listenerOri);
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			//SoundSource objective = new SoundSource();
			try {
				//System.out.println("( " + boneco.getX() + " , " + boneco.getY() + " ) - ( " + (boneco.getX()+0.0f) + " , " + (boneco.getY()+0.0f) + " )");
				//objective.execute("objective.wav", obj_y, obj_x, obj_z, (boneco.getY()+0.0f), (boneco.getX()+0.0f), (boneco.getZ()+0.0f));
				
				//objective.setSourceValues(obj_y, obj_x, obj_z);
				objective.playSound();
				System.out.println("x:" +obj_y+  " y :"+ obj_x + " z: "+ obj_z);
				Thread.sleep(intervalo/*5000*/);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
