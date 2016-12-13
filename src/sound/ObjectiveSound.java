package sound;

import java.io.FileNotFoundException;
import java.nio.FloatBuffer;

import utils.Ponto;

public class ObjectiveSound implements Runnable{
	private float obj_x;
	private float obj_y;
	private float obj_z;
	
	//private Ponto boneco;
	
	private FloatBuffer listenerPos;
	private FloatBuffer listenerVel;
	private FloatBuffer listenerOri;
	
	public ObjectiveSound(Float obj_x, Float obj_y, Float obj_z, FloatBuffer listenerPosA, FloatBuffer listenerVelA, FloatBuffer listenerOriA/*Ponto bonecao*/){
		this.obj_x = obj_x;
		this.obj_y = obj_y;
		this.obj_z = obj_z;
		//this.boneco = bonecao;
		this.listenerPos = listenerPosA;
		this.listenerVel = listenerVelA;
		this.listenerOri = listenerOriA;
	}
	
	public void setObjectPosition(Float obj_x, Float obj_y, Float obj_z){
		this.obj_x = obj_x;
		this.obj_y = obj_y;
		this.obj_z = obj_z;
	}
	
	public void run(){
		SoundSource objective = new SoundSource();
		try {
			objective.execute("latido.wav", obj_y, obj_x, obj_z, listenerPos, listenerVel, listenerOri);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		while(true){
			//SoundSource objective = new SoundSource();
			try {
				//System.out.println("( " + boneco.getX() + " , " + boneco.getY() + " ) - ( " + (boneco.getX()+0.0f) + " , " + (boneco.getY()+0.0f) + " )");
				//objective.execute("objective.wav", obj_y, obj_x, obj_z, (boneco.getY()+0.0f), (boneco.getX()+0.0f), (boneco.getZ()+0.0f));
				objective.setSourceValues(obj_y, obj_x, obj_z);
				objective.playSound();
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
