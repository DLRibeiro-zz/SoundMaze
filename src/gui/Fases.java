package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

import net.miginfocom.swing.MigLayout;
import sound.ObjectiveSound;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Label;



public class Fases extends JFrame implements Runnable {

	/**
	 * Launch the application.
	 */
	private ObjectiveSound teste;
	private Thread chuva;

	public void run() {
		try {
			Fases frame = new Fases();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the frame.
	 */
	public Fases() {
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
		
		teste = new ObjectiveSound(0.0f, 0.0f, 20.0f, listenerPos, listenerVel, listenerOri, "fases-mus.wav", 0, 0.3f);
		chuva = new Thread(teste);
		chuva.start();
		
		this.setTitle("Fases");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 540);
		try {
			final Image backgroundImage = javax.imageio.ImageIO.read(new File("fases.png"));
			setContentPane(new JPanel() {
				@Override public void paintComponent(Graphics g) {
					g.drawImage(backgroundImage, 0, 0, null);
				}
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.setResizable(false);
		getContentPane().setLayout(new MigLayout("", "[32.00][389.00px,right][]", "[][57.00][31.00][50.00,grow][42.00]"));

		JLabel label = new JLabel("Fases");
		label.setFont(new Font("Black Asylum", Font.PLAIN, 46));
		label.setForeground(Color.WHITE);
		getContentPane().add(label, "cell 1 1,alignx center,aligny center");

		String[] fases = {"   Escolha sua fase   ","   1   ","   2   ","   3   "};
		JList<String> list = new JList(fases);
		list.setForeground(new Color(255,255,255));
		list.setBackground(new Color(255,255,255,75));
		System.out.println("oxente: " + list.getSelectedIndex());
		list.addListSelectionListener(new ListSelectionListener() {
		 	public void valueChanged(ListSelectionEvent arg0) {
		 		System.out.println("dota > lol : " + list.getSelectedValue()/*list.getSelectedIndex()*/+"");
		 		
		 		if(list.getSelectedIndex() > 0){
		 		try {
		 			teste.setJogando(false);
					AL.destroy();
					Jogo frame = new Jogo(list.getSelectedIndex());
					frame.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
		 		}
		 	}
		 });
//		ListSelectionListener teste = 
//		list.addListSelectionListener(new ListSelectionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					Main window = new Main();					
//					window.framePrincipal.setVisible(true);
//					dispose();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});

		JButton btnVoltar = new JButton("   Voltar   ");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					teste.setJogando(false);
					AL.destroy();
					Main window = new Main();					
					window.framePrincipal.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnVoltar.setBorderPainted(false);
		btnVoltar.setBackground(new Color(255,184,96));	
		btnVoltar.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		getContentPane().add(btnVoltar, "flowx,cell 1 4,alignx center,aligny center");

		getContentPane().add(list, "cell 1 3,grow");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				try {
					teste.setJogando(false);
					AL.destroy();
					Main window = new Main();					
					window.framePrincipal.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}
}
