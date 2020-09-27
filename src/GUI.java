import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import BPCS.Extractor;
import BPCS.Hider;
import BPCS.PayloadFileProcessor;
import BPCS.ExtractedPayload;
import javax.swing.JSplitPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class GUI {

	//Reference to encodedResult arraylist when doing view page or decode page. encodedResult contains all the past encoded result paths.
	public ArrayList<EncodedResult> encodedResult = new ArrayList<EncodedResult>();
	public ArrayList<DecodedResult> decodeResult = new ArrayList<DecodedResult>();
	private String coverImagePath; //p1
	private String coverImageExtensionType; //jpg
	private String secretFilePath; //p2	
	private String saveAsPath;//p3
	private JFrame frame;
	private JTextField txtDecodePathFile;
	
	private String DecrptFilePath; 

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
				coverImageExtensionType = HelperFunctions.GetFileExtension(coverImagePath);
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
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coverImagePath = "";
				secretFilePath = "";
				coverImageExtensionType = "";
				saveAsPath = "";
				secretImage.setIcon(null);
				coverImage.setIcon(null);
				txtCoverFilePath.setText("No file chosen");
				txtSecretFilePath.setText("No file chosen");

			}
		});
		btnClear.setForeground(Color.DARK_GRAY);
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear.setBounds(497, 590, 506, 46);
		panel.add(btnClear);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Validation
				if ((coverImagePath == "") || (coverImagePath == null))
				{
					JOptionPane.showMessageDialog(null, "Please input a cover image!", "Missing input", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if((secretFilePath == "") || (secretFilePath == null))
				{
					JOptionPane.showMessageDialog(null, "Please input a secret file (payload)!", "Missing input", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				System.out.println(coverImageExtensionType);
				saveAsPath = HelperFunctions.SaveFile(coverImageExtensionType); 
				if (saveAsPath != "")
				{
					saveAsPath = saveAsPath + "." + coverImageExtensionType;
					Path p1 = Paths.get(coverImagePath);
					Path p2 = Paths.get(secretFilePath);
					Path p3 = Paths.get(saveAsPath);
					//  public static void HidePayload(Path vesselPath, Path payloadPath, Path outputPath) throws Exception{     
					try {
						Hider.HidePayload(p1, p2, p3);
						encodedResult.add(new EncodedResult(p1, p2, p3));
						JOptionPane.showMessageDialog(null, "Operation Success", "Completed", JOptionPane.INFORMATION_MESSAGE);
						btnClear.doClick();
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Operation Failed", "Please retry", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Operation cancelled", "Cancel", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		btnGenerate.setForeground(Color.DARK_GRAY);
		btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGenerate.setBounds(0, 590, 497, 46);
		panel.add(btnGenerate);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Result", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(171, 52, 46, 14);
		panel_2.add(lblNewLabel_1);
		
		JPanel panel_decode = new JPanel();
		tabbedPane.addTab("Decode", null, panel_decode, null);
		panel_decode.setLayout(null);
		
		JLabel LabelTitle = new JLabel("Decryption ");
		LabelTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelTitle.setBounds(25, 10, 120, 42);
		panel_decode.add(LabelTitle);
		
		txtDecodePathFile = new JTextField();
		txtDecodePathFile.setText("No file chosen");
		txtDecodePathFile.setColumns(10);
		txtDecodePathFile.setBounds(25, 62, 359, 20);
		panel_decode.add(txtDecodePathFile);
		
		
		JLabel OriginalPicPreview = new JLabel("Original Preview");
		OriginalPicPreview.setOpaque(true);
		OriginalPicPreview.setHorizontalAlignment(SwingConstants.CENTER);
		OriginalPicPreview.setForeground(Color.WHITE);
		OriginalPicPreview.setBackground(Color.LIGHT_GRAY);
		OriginalPicPreview.setBounds(502, 94, 472, 486);
		panel_decode.add(OriginalPicPreview);
		
		JButton btnDecode = new JButton("Decrypt ");
		btnDecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((DecrptFilePath == "") || (DecrptFilePath == null))
				{
					JOptionPane.showMessageDialog(null, "Please input a Decrypt image!", "Missing input", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			
				{
				
					Path p1 = Paths.get(DecrptFilePath);
					Path outputOfExtract = Paths.get("");
					    
					try {
						List<Path> listOfOutputs = Extractor.ExtractPayload(p1,outputOfExtract );
						decodeResult.add(new DecodedResult(p1,outputOfExtract ));
						JOptionPane.showMessageDialog(null, "Operation Success", "Completed", JOptionPane.INFORMATION_MESSAGE);
						btnClear.doClick();
	
						for(int i =  0; i < listOfOutputs.size(); i++) {
							System.out.println("The image name is " + listOfOutputs.get(i).toString());
							System.out.println("So the path of the hidden image is: " +  listOfOutputs.get(i).toAbsolutePath().toString());
						}
						
						DecrptFilePath = listOfOutputs.get(0).toAbsolutePath().toString();
		
						txtDecodePathFile.setText(DecrptFilePath);
						ImageIcon img = new ImageIcon(new ImageIcon(DecrptFilePath).getImage().getScaledInstance(OriginalPicPreview.getWidth(), OriginalPicPreview.getHeight(), Image.SCALE_SMOOTH));
						OriginalPicPreview.setIcon(img);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Operation Failed", "Please retry", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				
				
			}
			
		});
		btnDecode.setForeground(Color.DARK_GRAY);
		btnDecode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDecode.setBounds(48, 588, 193, 33);
		panel_decode.add(btnDecode);
		
	
		JLabel DecodePicPreview = new JLabel("Decode Preview");
		DecodePicPreview.setOpaque(true);
		DecodePicPreview.setHorizontalAlignment(SwingConstants.CENTER);
		DecodePicPreview.setForeground(Color.WHITE);
		DecodePicPreview.setBackground(Color.LIGHT_GRAY);
		DecodePicPreview.setBounds(20, 94, 472, 486);
		panel_decode.add(DecodePicPreview);
		
		JButton btnDecodePic = new JButton("Choose File");
		btnDecodePic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DecrptFilePath = HelperFunctions.SelectFile();
				txtDecodePathFile.setText(DecrptFilePath);
				ImageIcon img = new ImageIcon(new ImageIcon(DecrptFilePath).getImage().getScaledInstance(DecodePicPreview.getWidth(), DecodePicPreview.getHeight(), Image.SCALE_SMOOTH));
				DecodePicPreview.setIcon(img);
			}
		});
		btnDecodePic.setBounds(406, 61, 103, 23);
		panel_decode.add(btnDecodePic);
		
		JButton btnClear_1 = new JButton("Clear");
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecrptFilePath = "";
				OriginalPicPreview.setIcon(null);
				DecodePicPreview.setIcon(null);
				txtDecodePathFile.setText("No file chosen");
			}
		});
		btnClear_1.setForeground(Color.DARK_GRAY);
		btnClear_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear_1.setBounds(270, 588, 199, 33);
		panel_decode.add(btnClear_1);
	}
	
	
}
