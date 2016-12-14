package sound;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Scanner;

import javax.print.DocFlavor.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

public class SoundSource {
	public static final int STEP1 = 0;
	public static final int STEP2 = 1;
	public static final int COLISION = 2;
	public Float x,y,z;
	//criacao dos buffers de audio eles guardam os dados do som
	private IntBuffer buffer = BufferUtils.createIntBuffer(3);

	//sources sao os pontos emitindo som
	private IntBuffer source = BufferUtils.createIntBuffer(3);

	//local da fonte de som
	private FloatBuffer sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();

	//velocidade da mesma
	private FloatBuffer sourceVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();

	//posicao do listener
	private FloatBuffer listenerPos;
	//private FloatBuffer listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 1.0f, 1.0f, 1.0f }).rewind();

	//velocidade do listener
	private FloatBuffer listenerVel;
	//private FloatBuffer listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();

	/** Orientation of the listener. (first 3 elements are "at", second 3 are "up") */
	private FloatBuffer listenerOri;
	//private FloatBuffer listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f,  0.0f, 1.0f, 0.0f }).rewind();

	/**
	 * boolean LoadALData()
	 *
	 *  This function will load our sample data from the disk using the Alut
	 *  utility and send the data into OpenAL as a buffer. A source is then
	 *  also created to play that buffer.
	 * @throws FileNotFoundException 
	 */
	int loadALData(String arquivo, Float volume) throws FileNotFoundException {
		// Load wav data into a buffer.
		AL10.alGenBuffers(buffer);

		if(AL10.alGetError() != AL10.AL_NO_ERROR)
			return AL10.AL_FALSE;

		//Loads the wave file from your file system
		/*java.io.FileInputStream fin = null;
    try {
      fin = new java.io.FileInputStream("FancyPants.wav");
    } catch (java.io.FileNotFoundException ex) {
      ex.printStackTrace();
      return AL10.AL_FALSE;
    }
    WaveData waveFile = WaveData.create(fin);
    try {
      fin.close();
    } catch (java.io.IOException ex) {
    }*/

		//Loads the wave file from this class's package in your classpath

		WaveData waveFile = WaveData.create(new BufferedInputStream(new FileInputStream(arquivo)));
//		System.out.println(""+buffer.get(0));
		//System.out.println("Teste: " + waveFile.format + " , " + waveFile.data + " , " + waveFile.samplerate);
		
		AL10.alBufferData(buffer.get(0), waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();
		//WaveData waveFile1 = WaveData.create(new BufferedInputStream(new FileInputStream("p2.wav")));
		// Bind the buffer with the source.
		AL10.alGenSources(source);
//		System.out.println("Loadou");

		if (AL10.alGetError() != AL10.AL_NO_ERROR)
			return AL10.AL_FALSE;

		AL10.alSourcei(source.get(0), AL10.AL_BUFFER,   buffer.get(0) );
		AL10.alSourcef(source.get(0), AL10.AL_PITCH,    1.0f          );
		AL10.alSourcef(source.get(0), AL10.AL_GAIN,     /*1.0f*/volume          );
		AL10.alSource (source.get(0), AL10.AL_POSITION, sourcePos     );
		AL10.alSource (source.get(0), AL10.AL_VELOCITY, sourceVel     );

		// Do another error check and return.
		if (AL10.alGetError() == AL10.AL_NO_ERROR)
			return AL10.AL_TRUE;

		return AL10.AL_FALSE;
	}

	/**
	 * void setListenerValues()
	 *
	 *  We already defined certain values for the Listener, but we need
	 *  to tell OpenAL to use that data. This function does just that.
	 */
	void setListenerValues() {
		AL10.alListener(AL10.AL_POSITION,    listenerPos);
		AL10.alListener(AL10.AL_VELOCITY,    listenerVel);
		AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
	}
	
	//DEPRECATED
	void setSourceValues(Float obj_x, Float obj_y, Float obj_z) {
		System.out.println("atualizou o valor");
		x = obj_x;
		y = obj_y;
		z = obj_z;
		sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { obj_x, obj_y, obj_z }).rewind();
	}
	
	

	/**
	 * void killALData()
	 *
	 *  We have allocated memory for our buffers and sources which needs
	 *  to be returned to the system. This function frees that memory.
	 */
	public void killALData() {
		AL10.alDeleteSources(source);
		AL10.alDeleteBuffers(buffer);
	}

//	public static void main(String[] args) throws FileNotFoundException {
//		new SoundSource().execute();
//	}

	public void playSound(){
		AL10.alSourcePlay(source.get(0));
		while(AL10.alGetSourcei(source.get(0), AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING){
			
		}
	}
	
	public void execute(String arquivo, Float obj_x, Float obj_y, Float obj_z, FloatBuffer listenerPosA, FloatBuffer listenerVelA, FloatBuffer listenerOriA, Float volume/*Float bon_x, Float bon_y, Float bon_z*/) throws FileNotFoundException {
		// Initialize OpenAL and clear the error bit.
		/*try{
			AL.create();
		} catch (LWJGLException le) {
			le.printStackTrace();
			return;
		}
		AL10.alGetError();*/
		/** Buffers hold sound data. */
		buffer = BufferUtils.createIntBuffer(1);

		/** Sources are points emitting sound. */
		source = BufferUtils.createIntBuffer(1);

		/** Position of the source sound. */
		//sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
		sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { obj_x, obj_y, obj_z }).rewind();
		x = obj_x;
		y = obj_y;
		z = obj_z;
		/** Velocity of the source sound. */
		sourceVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();

		/** Position of the listener. */
		//listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 1.0f, 1.0f, 1.0f }).rewind();
		//listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { bon_x, bon_y, bon_z }).rewind();
		listenerPos = listenerPosA;
		
		/** Velocity of the listener. */
		//listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
		listenerVel = listenerVelA;
		
		/** Orientation of the listener. (first 3 elements are "at", second 3 are "up") */
		//listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f,  0.0f, 1.0f, 0.0f }).rewind();
		listenerOri = listenerOriA;
		// Load the wav data.
		if(loadALData(arquivo, volume) == AL10.AL_FALSE) {
			System.out.println("Error loading data. " + arquivo);
			return;
		}


		setListenerValues();
//		System.out.println("Bora rodar?");
		
		//VAMO VER SE VAI FUNFAR
		/*AL10.alSourcePlay(source.get(0));
		
		while(AL10.alGetSourcei(source.get(0), AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING){
			
		}*/
		
		// Loop.
//		System.out.println("OpenAL Tutorial 1 - Single Static Source");
//		System.out.println("[Menu]");
//		System.out.println("p - Play the sample.");
//		System.out.println("s - Stop the sample.");
//		System.out.println("h - Pause the sample.");
//		System.out.println("q - Quit the program.");
//		char c = ' ';
//		Scanner stdin = new Scanner(System.in);
//		while(c != 'q') {
//			try {
//				System.out.print("Input: ");
//				c = (char)stdin.nextLine().charAt(0);
//			} catch (Exception ex) {
//				c = 'q';
//			}
//
//			switch(c) {
//			// Pressing 'p' will begin playing the sample.
//			case 'p': AL10.alSourcePlay(source.get(0)); break;
//
//			// Pressing 's' will stop the sample from playing.
//			case's': AL10.alSourceStop(source.get(0)); break;
//
//			// Pressing 'h' will pause the sample.
//			case 'h': AL10.alSourcePause(source.get(0)); break;
//
//			case 'w': 
//				listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 1000.0f, 1000.0f, 1000.0f }).rewind();
//				setListenerValues();
//				loadALData();
//				break;
//
//
//			};
//		}
		//VAMO VER SE VAI FUNCIONAR
		//killALData();
		//AL.destroy();
	}
}