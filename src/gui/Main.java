package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;

import javax.swing.SwingConstants;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

import javax.swing.BoxLayout;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import sound.ObjectiveSound;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	protected JFrame framePrincipal;
	private JPanel contentPane;
	
	private ObjectiveSound teste;
	private Thread chuva;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.framePrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Main() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
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
		
		teste = new ObjectiveSound(0.0f, 0.0f, 20.0f, listenerPos, listenerVel, listenerOri, "rain-01-cut.wav", 0, 0.15f);
		chuva = new Thread(teste);
		chuva.start();
		
		framePrincipal = new JFrame();
		Font font = new Font("Black Asylum", Font.PLAIN, 25);
		framePrincipal.setFont(font);
		framePrincipal.getContentPane().setForeground(Color.WHITE);
		framePrincipal.setBounds(100, 100, 480, 540);
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.setTitle("SoundMaze");
//		frame.setResizable(false);
		framePrincipal.repaint();
		contentPane = (JPanel) framePrincipal.getContentPane();
		try {
		    final Image backgroundImage = javax.imageio.ImageIO.read(new File("textura2.jpg"));
		    framePrincipal.setContentPane(new JPanel() {
		        @Override public void paintComponent(Graphics g) {
		            g.drawImage(backgroundImage, 0, 0, null);
		        }
		    });
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}
	
		
		Color corBotaoIniciar = new Color(255,184,96);	
		
		framePrincipal.getContentPane().setLayout(new MigLayout("", "[346px,grow,right][][]", "[129.00,grow][50.00px][50.00][50.00][42.00]"));
		
		JLabel lblSoundmaze = new JLabel("SoundMaze");
		lblSoundmaze.setForeground(Color.WHITE);
		lblSoundmaze.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoundmaze.setFont(new Font("Black Asylum", Font.PLAIN, 46));
		framePrincipal.getContentPane().add(lblSoundmaze, "cell 0 0,alignx center,aligny center");
		

		int r, g, b;
		r =  255;
		g =  255;
		b =  255;
		Color backGroundColor = new Color(r, g, b);
		JButton btnIniciar = new JButton("   Jogar agora   ");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Jogo frame = new Jogo();
					frame.setVisible(true);
					setInvisible();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnIniciar.setBorderPainted(false);
		btnIniciar.setBackground(corBotaoIniciar);	
		btnIniciar.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		framePrincipal.getContentPane().add(btnIniciar, "flowx,cell 0 1,alignx center,aligny center");
		
		
		JButton btnFases = new JButton("  Fases  ");
		btnFases.setBorderPainted(false);
		
		btnFases.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnFases.setBackground(new Color(65,171,197));
		btnFases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Fases frame = new Fases();
					frame.setVisible(true);
					setInvisible();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		framePrincipal.getContentPane().add(btnFases, "flowx,cell 0 2,alignx center,aligny center");
				
		JButton btnCreditos = new JButton("  Sobre  ");
		btnCreditos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Sobre frame = new Sobre();
					frame.setVisible(true);
					setInvisible();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnCreditos.setBorderPainted(false);
		btnCreditos.setBackground(new Color(65,171,197));
		btnCreditos.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		framePrincipal.getContentPane().add(btnCreditos, "flowx,cell 0 3,alignx center,aligny center");
		
	}
	protected void setInvisible(){
		try {
			teste.setJogando(false);
			chuva.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AL.destroy();
		this.framePrincipal.setVisible(false);
	}
	
}
