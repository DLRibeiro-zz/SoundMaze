package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JList;

public class Sobre extends JFrame implements Runnable
{

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			Sobre frame = new Sobre();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the frame.
	 */
	public Sobre() {
		this.setTitle("Fases");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 540);
		try {
			final Image backgroundImage = javax.imageio.ImageIO.read(new File("sobre.jpg"));
			setContentPane(new JPanel() {
				@Override public void paintComponent(Graphics g) {
					g.drawImage(backgroundImage, 0, 0, null);
				}
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.setResizable(false);
		getContentPane().setLayout(new MigLayout("", "[32.00][389.00px,right][]", "[40.00][57.00][31.00][234.00][42.00][69.00]"));

		JLabel label = new JLabel("Sobre");
		label.setFont(new Font("Black Asylum", Font.PLAIN, 46));
		label.setForeground(Color.WHITE);
		getContentPane().add(label, "cell 1 1,alignx center,aligny center");
		String[] data = {"                          Realização: "," ","      wro@cin.ufpe.br","      tmb2@cin.ufpe.br","      ajew@cin.ufpe.br","      rcac@cin.ufpe.br", "      dlr4@cin.ufpe.br"};	
		JList<String> list = new JList(data);
		
		list.setFont(new Font("Times", Font.BOLD, 15));
		list.setForeground(Color.WHITE);
		list.setBackground(new Color(255,255,255,90));

		getContentPane().add(list, "cell 1 3,grow");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JButton btnVoltar = new JButton("   Voltar   ");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
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
		btnVoltar.setFont(new Font("Black Asylum", Font.PLAIN, 15));
		getContentPane().add(btnVoltar, "flowx,cell 1 4,alignx center,aligny center");


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
	}

}
