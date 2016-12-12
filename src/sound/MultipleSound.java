package sound;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

import utils.Mapa;

public class MultipleSound {
	/** Maximum data buffers we will need. */
	public static final int NUM_BUFFERS = 3;

	/** Maximum emissions we will need. */
	public static final int NUM_SOURCES = 3;

	/** Index of BODYINT sound */
	public static final int BODYINT = 0;

	/** Index of gun 1 sound */
	public static final int STEP1 = 1;

	/** Index of gun 2 sound */
	public static final int STEP2 = 2;

	/** Buffers hold sound data. */
	IntBuffer buffer = BufferUtils.createIntBuffer(NUM_BUFFERS);

	/** Sources are points emitting sound. */
	IntBuffer source = BufferUtils.createIntBuffer(NUM_BUFFERS);

	/** Position of the source sound. */
	FloatBuffer sourcePos = (FloatBuffer) BufferUtils.createFloatBuffer(3*NUM_BUFFERS).rewind();

	/** Velocity of the source sound. */
	FloatBuffer sourceVel = (FloatBuffer) BufferUtils.createFloatBuffer(3*NUM_BUFFERS).rewind();

	/** Position of the listener. */
	FloatBuffer listenerPos = (FloatBuffer) BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();

	/** Velocity of the listener. */
	FloatBuffer listenerVel = (FloatBuffer) BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();

	/** Orientation of the listener. (first 3 elements are "at", second 3 are "up") */
	FloatBuffer listenerOri =
			(FloatBuffer) BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f,  0.0f, 1.0f, 0.0f }).rewind();
	/**
	 * boolean LoadALData()
	 *
	 *  This function will load our sample data from the disk using the Alut
	 *  utility and send the data into OpenAL as a buffer. A source is then
	 *  also created to play that buffer.
	 * @throws FileNotFoundException 
	 */
	int loadALData() throws FileNotFoundException {
		
		// Load wav data into a buffer.
		AL10.alGenBuffers(buffer);

		if(AL10.alGetError() != AL10.AL_NO_ERROR)
			return AL10.AL_FALSE;

		WaveData waveFile = WaveData.create(new BufferedInputStream(new FileInputStream("C:/Users/Walber Rodrigues/git/OpenAL/SoundMaze/p1.wav")));
		//		System.out.println(""+buffer.get(0));
		//	    System.out.println(""+waveFile.format+waveFile.data+waveFile.samplerate);

		AL10.alBufferData(buffer.get(STEP1), waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();

		waveFile = WaveData.create(new BufferedInputStream(new FileInputStream("C:/Users/Walber Rodrigues/git/OpenAL/SoundMaze/p2.wav")));
		AL10.alBufferData(buffer.get(STEP2), waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();

		waveFile = WaveData.create(new BufferedInputStream(new FileInputStream("C:/Users/Walber Rodrigues/git/OpenAL/SoundMaze/bi.wav")));
		AL10.alBufferData(buffer.get(BODYINT), waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();

		// Bind buffers into audio sources.
		AL10.alGenSources(source);
		
		
		
		
		if (AL10.alGetError() != AL10.AL_NO_ERROR)
			return AL10.AL_FALSE;

		AL10.alSourcei(source.get(BODYINT), AL10.AL_BUFFER,   buffer.get(BODYINT));
		AL10.alSourcef(source.get(BODYINT), AL10.AL_PITCH,    1.0f          );
		AL10.alSourcef(source.get(BODYINT), AL10.AL_GAIN,     1.0f          );
		AL10.alSource (source.get(BODYINT), AL10.AL_POSITION, (FloatBuffer) sourcePos.position(BODYINT*3));
		AL10.alSource (source.get(BODYINT), AL10.AL_VELOCITY, (FloatBuffer) sourceVel.position(BODYINT*3));
		AL10.alSourcei(source.get(BODYINT), AL10.AL_LOOPING,  AL10.AL_TRUE  );

		AL10.alSourcei(source.get(STEP1), AL10.AL_BUFFER,   buffer.get(STEP1));
		AL10.alSourcef(source.get(STEP1), AL10.AL_PITCH,    1.0f          );
		AL10.alSourcef(source.get(STEP1), AL10.AL_GAIN,     1.0f          );
		AL10.alSource (source.get(STEP1), AL10.AL_POSITION, (FloatBuffer) sourcePos.position(STEP1*3));
		AL10.alSource (source.get(STEP1), AL10.AL_VELOCITY, (FloatBuffer) sourceVel.position(STEP1*3));
		AL10.alSourcei(source.get(STEP1), AL10.AL_LOOPING,  AL10.AL_FALSE );

		AL10.alSourcei(source.get(STEP2), AL10.AL_BUFFER,   buffer.get(STEP2));
		AL10.alSourcef(source.get(STEP2), AL10.AL_PITCH,    1.0f          );
		AL10.alSourcef(source.get(STEP2), AL10.AL_GAIN,     1.0f          );
		AL10.alSource (source.get(STEP2), AL10.AL_POSITION, (FloatBuffer) sourcePos.position(STEP2*3));
		AL10.alSource (source.get(STEP2), AL10.AL_VELOCITY, (FloatBuffer) sourceVel.position(STEP2*3));
		AL10.alSourcei(source.get(STEP2), AL10.AL_LOOPING,  AL10.AL_FALSE );

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

	/**
	 * void killALData()
	 *
	 *  We have allocated memory for our buffers and sources which needs
	 *  to be returned to the system. This function frees that memory.
	 */
	void killALData() {
		AL10.alDeleteSources(source);
		AL10.alDeleteBuffers(buffer);
	}
//	public static void main(String[] args) throws FileNotFoundException {
//		new MultipleSound().execute();
//	}

	/**
	 *  Check for keyboard hit
	 */
	private boolean kbhit() {
		try {
			return (System.in.available() != 0);
		} catch (IOException ioe) {
		}
		return false;
	}

	public void execute(Float x, Float y, Float z) throws FileNotFoundException {
		// Initialize OpenAL and clear the error bit.
		try{
			AL.create();
		} catch (LWJGLException le) {
			le.printStackTrace();
			return;
		}
		AL10.alGetError();

		// Load the wav data.
		if(loadALData() == AL10.AL_FALSE) {
			System.out.println("Error loading data.");
			return;
		}

		setListenerValues();

		// Begin the BODYINT sample to play.
		AL10.alSourcePlay(source.get(BODYINT));

		// Go through all the sources and check that they are playing.
		// Skip the first source because it is looping anyway (will always be playing).
		int play = 0 ;
		Random random = new Random();


		
			for (int i=0; i<NUM_SOURCES; i++){
				play = AL10.alGetSourcei(source.get(i), AL10.AL_SOURCE_STATE);
				if (play != AL10.AL_PLAYING) {
					sourcePos.put(i*3+0, -(float) (x));
					sourcePos.put(i*3+1, -(float) (y));
					sourcePos.put(i*3+2, -(float) (z));
					System.out.println("opa");

					AL10.alSource(source.get(i), AL10.AL_POSITION, (FloatBuffer) sourcePos.position(i*3));
					AL10.alSourcePlay(source.get(i));
				}
			}
		killALData();
	}
	public void andar(Float x, Float y, Float z) throws FileNotFoundException{
		listenerPos = (FloatBuffer) BufferUtils.createFloatBuffer(3).put(new float[] { x, y, z }).rewind();
		setListenerValues();
		loadALData();
	}
	
}

