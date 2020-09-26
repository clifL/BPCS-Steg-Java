import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import BPCS.Hider;
import BPCS.Extractor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;

public class HidePage {

	
	private String coverImagePath;
	private String secretFilePath;
	private JFrame frmStego;
	private JTextField txtCoverFilePath;
	private JTextField txtSecretFilePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HidePage window = new HidePage();
					window.frmStego.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HidePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStego = new JFrame();
		frmStego.setTitle("Stego");
		frmStego.setBounds(100, 100, 776, 484);
		frmStego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStego.getContentPane().setLayout(null);
		
		JLabel lblCoverImage = new JLabel("Upload Cover Image");
		lblCoverImage.setBounds(132, 27, 164, 14);
		frmStego.getContentPane().add(lblCoverImage);
		
		txtCoverFilePath = new JTextField();
		txtCoverFilePath.setText("No file chosen");
		txtCoverFilePath.setBounds(132, 42, 162, 20);
		frmStego.getContentPane().add(txtCoverFilePath);
		txtCoverFilePath.setColumns(10);
		
		JLabel coverImage = new JLabel("Cover Preview");
		coverImage.setForeground(Color.WHITE);
		coverImage.setOpaque(true);
		coverImage.setBackground(Color.LIGHT_GRAY);
		coverImage.setHorizontalAlignment(SwingConstants.CENTER);
		coverImage.setBounds(132, 73, 269, 236);
		frmStego.getContentPane().add(coverImage);
		
		JButton btnChooseCover = new JButton("Choose File");
		btnChooseCover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coverImagePath = HelperFunctions.SelectFile();
				txtCoverFilePath.setText(coverImagePath);
				ImageIcon img = new ImageIcon(new ImageIcon(coverImagePath).getImage().getScaledInstance(coverImage.getWidth(), coverImage.getHeight(), Image.SCALE_SMOOTH));
				coverImage.setIcon(img);
			}
		});
		btnChooseCover.setBounds(298, 41, 103, 23);
		frmStego.getContentPane().add(btnChooseCover);
		
		JLabel lblSecretFile = new JLabel("Upload Secret File");
		lblSecretFile.setBounds(468, 27, 164, 14);
		frmStego.getContentPane().add(lblSecretFile);
		
		txtSecretFilePath = new JTextField();
		txtSecretFilePath.setColumns(10);
		txtSecretFilePath.setBounds(468, 42, 162, 20);
		frmStego.getContentPane().add(txtSecretFilePath);
		
		
		JLabel secretImage = new JLabel("Secret Preview (If applicable)");
		secretImage.setOpaque(true);
		secretImage.setHorizontalAlignment(SwingConstants.CENTER);
		secretImage.setForeground(Color.WHITE);
		secretImage.setBackground(Color.LIGHT_GRAY);
		secretImage.setBounds(468, 73, 269, 236);
		frmStego.getContentPane().add(secretImage);
		
		JButton btnChooseSecret = new JButton("Choose File");
		btnChooseSecret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secretFilePath = HelperFunctions.SelectFile();
				txtSecretFilePath.setText(secretFilePath);
				ImageIcon img = new ImageIcon(new ImageIcon(secretFilePath).getImage().getScaledInstance(coverImage.getWidth(), coverImage.getHeight(), Image.SCALE_SMOOTH));
				secretImage.setIcon(img);
			}
		});
		btnChooseSecret.setBounds(634, 41, 103, 23);
		frmStego.getContentPane().add(btnChooseSecret);
		
		
		
		JButton btnGetEncodePage = new JButton("Encode");
		btnGetEncodePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGetEncodePage.setBounds(10, 41, 89, 23);
		frmStego.getContentPane().add(btnGetEncodePage);
		
		JButton btnGetDecodePage = new JButton("Decode");
		btnGetDecodePage.setBounds(10, 73, 89, 23);
		frmStego.getContentPane().add(btnGetDecodePage);
		
		JButton btnGetPreviewPage = new JButton("Preview");
		btnGetPreviewPage.setBounds(10, 107, 89, 23);
		frmStego.getContentPane().add(btnGetPreviewPage);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(228, 486, 68, -55);
		frmStego.getContentPane().add(verticalGlue);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setForeground(Color.DARK_GRAY);
		btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGenerate.setBounds(132, 357, 409, 46);
		frmStego.getContentPane().add(btnGenerate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coverImagePath = "";
				secretFilePath = "";
				lblCoverImage.setText("");
				lblSecretFile.setText("");
			}
		});
		btnClear.setForeground(Color.DARK_GRAY);
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear.setBounds(535, 357, 188, 46);
		frmStego.getContentPane().add(btnClear);
		
		
		

	}
}
