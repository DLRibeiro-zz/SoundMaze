package sound;

import java.io.FileNotFoundException;

import utils.Ponto;

public class ObjectiveSound implements Runnable{
	private float obj_x;
	private float obj_y;
	private float obj_z;
	
	private Ponto boneco;
	
	public ObjectiveSound(Float obj_x, Float obj_y, Float obj_z, Ponto bonecao){
		this.obj_x = obj_x;
		this.obj_y = obj_y;
		this.obj_z = obj_z;
		this.boneco = bonecao;
	}
	
	public void run(){
		while(true){
			SoundSource objective = new SoundSource();
			try {
				//System.out.println("( " + boneco.getX() + " , " + boneco.getY() + " ) - ( " + (boneco.getX()+0.0f) + " , " + (boneco.getY()+0.0f) + " )");
				objective.execute("objective.wav", obj_y, obj_x, obj_z, (boneco.getY()+0.0f), (boneco.getX()+0.0f), (boneco.getZ()+0.0f));
				Thread.sleep(5000);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
