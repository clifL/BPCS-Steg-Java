import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.JSplitPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

public class GUI {

	
	public String coverImagePath;
	public String secretFilePath;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		UIManager.put("TabbedPane.selected", new java.awt.Color(235, 184, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setBackground(new java.awt.Color(41, 57, 86));
		tabbedPane.setBounds(0, 0, 1008, 675);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		tabbedPane.addTab("Encode", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblCoverImage = new JLabel("Upload Cover Image");
		lblCoverImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCoverImage.setForeground(new Color(255, 255, 255));
		lblCoverImage.setBounds(10, 24, 164, 14);
		panel.add(lblCoverImage);
		
		JTextField txtCoverFilePath = new JTextField();
		txtCoverFilePath.setText("No file chosen");
		txtCoverFilePath.setColumns(10);
		txtCoverFilePath.setBounds(10, 49, 359, 20);
		panel.add(txtCoverFilePath);
		
		JLabel coverImage = new JLabel("Cover Preview");
		coverImage.setOpaque(true);
		coverImage.setHorizontalAlignment(SwingConstants.CENTER);
		coverImage.setForeground(Color.WHITE);
		coverImage.setBackground(Color.LIGHT_GRAY);
		coverImage.setBounds(10, 80, 472, 486);
		panel.add(coverImage);
		
		JButton btnChooseCover = new JButton("Choose File");
		btnChooseCover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coverImagePath = HelperFunctions.SelectFile();
				txtCoverFilePath.setText(coverImagePath);
				ImageIcon img = new ImageIcon(new ImageIcon(coverImagePath).getImage().getScaledInstance(coverImage.getWidth(), coverImage.getHeight(), Image.SCALE_SMOOTH));
				coverImage.setIcon(img);
			}
		});
		btnChooseCover.setBounds(379, 48, 103, 23);
		panel.add(btnChooseCover);
		
		
		
		JLabel lblSecretFile = new JLabel("Upload Secret File");
		lblSecretFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSecretFile.setForeground(new Color(255, 255, 255));
		lblSecretFile.setBounds(510, 24, 164, 14);
		panel.add(lblSecretFile);
		
		JTextField txtSecretFilePath = new JTextField();
		txtSecretFilePath.setColumns(10);
		txtSecretFilePath.setBounds(509, 49, 371, 20);
		panel.add(txtSecretFilePath);
		
		JLabel secretImage = new JLabel("Secret Preview (If applicable)");
		secretImage.setOpaque(true);
		secretImage.setHorizontalAlignment(SwingConstants.CENTER);
		secretImage.setForeground(Color.WHITE);
		secretImage.setBackground(Color.LIGHT_GRAY);
		secretImage.setBounds(509, 80, 484, 486);
		panel.add(secretImage);
		
		JButton btnChooseSecret = new JButton("Choose File");
		btnChooseSecret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secretFilePath = HelperFunctions.SelectFile();
				txtSecretFilePath.setText(secretFilePath);
				ImageIcon img = new ImageIcon(new ImageIcon(secretFilePath).getImage().getScaledInstance(coverImage.getWidth(), coverImage.getHeight(), Image.SCALE_SMOOTH));
				secretImage.setIcon(img);
			}
		});
		btnChooseSecret.setBounds(890, 48, 103, 23);
		panel.add(btnChooseSecret);
		
		
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGenerate.setForeground(Color.DARK_GRAY);
		btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGenerate.setBounds(0, 590, 497, 46);
		panel.add(btnGenerate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coverImagePath = "";
				secretFilePath = "";
				secretImage.setIcon(null);
				coverImage.setIcon(null);
				txtCoverFilePath.setText("");
				txtSecretFilePath.setText("");

			}
		});
		btnClear.setForeground(Color.DARK_GRAY);
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear.setBounds(497, 590, 506, 46);
		panel.add(btnClear);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Decode", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(126, 58, 46, 14);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Result", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(171, 52, 46, 14);
		panel_2.add(lblNewLabel_1);
	}
}
