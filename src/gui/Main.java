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
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
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
		
		frame = new JFrame();
		Font font = new Font("Black Asylum", Font.PLAIN, 25);
		frame.setFont(font);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 301, 388);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("SoundMaze");
//		frame.setResizable(false);
		frame.repaint();
		contentPane = (JPanel) frame.getContentPane();
		try {
		    final Image backgroundImage = javax.imageio.ImageIO.read(new File("textura2.jpg"));
		    frame.setContentPane(new JPanel() {
		        @Override public void paintComponent(Graphics g) {
		            g.drawImage(backgroundImage, 0, 0, null);
		        }
		    });
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}
	
		
		Color corBotaoIniciar = new Color(255,184,96);	
		
		frame.getContentPane().setLayout(new MigLayout("", "[346px,grow,right][][]", "[129.00,grow][50.00px][50.00][50.00][42.00]"));
		
		JLabel lblSoundmaze = new JLabel("SoundMaze");
		lblSoundmaze.setForeground(Color.WHITE);
		lblSoundmaze.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoundmaze.setFont(new Font("Black Asylum", Font.PLAIN, 46));
		frame.getContentPane().add(lblSoundmaze, "cell 0 0,alignx center,aligny center");
		

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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnIniciar.setBorderPainted(false);
		btnIniciar.setBackground(corBotaoIniciar);	
		btnIniciar.setFont(new Font("Black Asylum", Font.PLAIN, 15));
		frame.getContentPane().add(btnIniciar, "flowx,cell 0 1,alignx center,aligny center");
		
		
		JButton btnFases = new JButton("  Fases  ");
		btnFases.setBorderPainted(false);
		
		btnFases.setFont(new Font("Black Asylum", Font.PLAIN, 15));
		btnFases.setBackground(new Color(65,171,197));
		btnFases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Fases frame = new Fases();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnFases, "flowx,cell 0 2,alignx center,aligny center");
				
		JButton btnCreditos = new JButton("  Sobre  ");
		btnCreditos.setBorderPainted(false);
		btnCreditos.setBackground(new Color(65,171,197));
		btnCreditos.setFont(new Font("Black Asylum", Font.PLAIN, 15));
		frame.getContentPane().add(btnCreditos, "flowx,cell 0 3,alignx center,aligny center");
		
	}
}
