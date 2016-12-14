package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import utils.Apagar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Jogo extends JFrame implements Runnable {
	private JFrame frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			this.frame = new Jogo();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the frame.
	 */
	public Jogo() {
		setResizable(false);
		ArrayList<String> comando = new ArrayList<String>();
		Apagar jogo = new Apagar(comando);
		(new Thread(jogo)).start();
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				char tecla = evt.getKeyChar();
				switch (tecla){
				case 'w' :
					if(comando.size() == 0){
						System.out.println("w");
						comando.add("w");
					}
					break; 
				case 'a' : 
					if(comando.size() == 0){
						System.out.println("a");
						comando.add("a");
					}
					break;
				case 's' : 
					if(comando.size() == 0){
						System.out.println("s");
						comando.add("s");
					}
					break;
				case 'd' : 
					if(comando.size() == 0){
						System.out.println("d");
						comando.add("d");
					}
					break;
				}
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				try {
					Main window = new Main();					
					window.framePrincipal.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		try {
			final Image backgroundImage = javax.imageio.ImageIO.read(new File("shackiho.png"));
			setContentPane(new JPanel() {
				@Override public void paintComponent(Graphics g) {
					g.drawImage(backgroundImage, 0, 0, null);
				}
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}



		getContentPane().setLayout(new MigLayout("", "[60.00][468.00][60.00]", "[30.00][145.00][38.00][134.00][37.00]"));

		JLabel lblNewLabel = new JLabel("SoundMaze");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		System.out.println("olha eu aqui");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Black Asylum", Font.PLAIN, 56));
		getContentPane().add(lblNewLabel, "cell 1 1,grow");

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("tutorial.png"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_1, "cell 1 3,alignx center,aligny center");


	}

}
