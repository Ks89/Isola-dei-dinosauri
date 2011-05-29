package isoladinosauri;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Classe richiamata dal main per simulare il gioco in Locale con grafica
 */
public class Main {


	private final static JFrame window = new JFrame("Isola Dinosauri BETA1");
	private static Container contentPane = window.getContentPane();
	private static CardLayout cardLayout = new CardLayout();

	public static void main (String[] args) {
		//chiama interfaccia grafica per scegliere il tipo di gioco da lanciare
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setMinimumSize(new Dimension(300,250));
		window.setResizable(false);

		JPanel homePanel = new JPanel(new GridLayout(4,1));
		JLabel titolo = new JLabel("Isola dei dinosauri");
		titolo.setHorizontalAlignment(SwingConstants.CENTER);
		homePanel.add(titolo);
		homePanel.add(createButton("Locale"));
		homePanel.add(createButton("Socket"));
		homePanel.add(createButton("Rmi"));

		JPanel localePanel = new JPanel(new GridLayout(4,1));
		JLabel locale = new JLabel("LOCALE");
		locale.setHorizontalAlignment(SwingConstants.CENTER);
		localePanel.add(locale);

		JButton lineaComando = new JButton("Linea di comando");
		lineaComando.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event){
						LocaleConsole cl = new LocaleConsole();
						cl.avviaLineaDiComando();
						window.setVisible(false);
					}
				}
		);

		localePanel.add(lineaComando);
		localePanel.add(createButton("GUI Locale"));
		localePanel.add(createButton("Home"));

		JPanel localeGUIPanel = new JPanel(new GridLayout(4,1));
		JLabel localeGUI = new JLabel("Modalita' caricamento mappa");
		localeGUI.setHorizontalAlignment(SwingConstants.CENTER);
		localeGUIPanel.add(localeGUI);
		
		
		JButton caricaDaFile = new JButton("Carica da file");
		caricaDaFile.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event){
//						LocaleGrafica cl = new LocaleGrafica();
//						cl.grafica();
//						window.setVisible(false);
					}
				}
		);
		localeGUIPanel.add(caricaDaFile);

		
		localeGUIPanel.add(createButton("Generazione casuale"));
		localeGUIPanel.add(createButton("Home"));

		
		JPanel socketPanel = new JPanel(new GridLayout(4,1));
		JLabel socket = new JLabel("SOCKET");
		socket.setHorizontalAlignment(SwingConstants.CENTER);
		socketPanel.add(socket);
		socketPanel.add(createButton("Linea di comando"));
		socketPanel.add(createButton("GUI"));
		socketPanel.add(createButton("Home"));

		JPanel rmiPanel = new JPanel(new GridLayout(4,1));
		JLabel rmi = new JLabel("RMI");
		rmi.setHorizontalAlignment(SwingConstants.CENTER);
		rmiPanel.add(rmi);
		rmiPanel.add(createButton("Linea di comando"));
		rmiPanel.add(createButton("GUI"));
		rmiPanel.add(createButton("Home"));

		contentPane.setLayout(cardLayout);
		contentPane.add(homePanel, "Home");
		contentPane.add(localePanel, "Locale");
		contentPane.add(socketPanel, "Socket");
		contentPane.add(rmiPanel, "Rmi");
		contentPane.add(localeGUIPanel, "GUI Locale");
		
		window.pack();
		window.setVisible(true);
	}

//	class ButtonHandler implements ActionListener
//	{
//		public void actionPerformed(ActionEvent event)
//		{
//			JButton pulsante = (JButton)event.getSource();
//			pulsante.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//			window.setBackground(Color.BLACK);
//			//			ConsoleLocale cl = new ConsoleLocale();
//			//			cl.avviaLineaDiComando();
//		}
//	}

	private static JButton createButton(String action) {
		JButton b = new JButton(action);
		b.setActionCommand(action);
		b.addActionListener(actionHandler);
		return b;
	}

	private static ActionListener actionHandler = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			cardLayout.show(contentPane, e.getActionCommand());
		}
	};
}
