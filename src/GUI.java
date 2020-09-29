import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import BPCS.Extractor;
import BPCS.Hider;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
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

	// Reference to encodedResult arraylist when doing view page or decode page.
	// encodedResult contains all the past encoded result paths.
	public ArrayList<EncodedResult> encodedResult = new ArrayList<EncodedResult>();
	public ArrayList<DecodedResult> decodeResult = new ArrayList<DecodedResult>();
	private String coverImagePath; //p1
	private String coverImageExtensionType; //jpg
	private String secretFilePath; //p2	
	private String saveAsPath;//p3
	private JFrame frame;
	private JTextField txtDecodePathFile;
	
	private String DecrptFilePath; 
	private JPanel panelEncodeResults;
	private JTabbedPane tabbedPane;
	private JLabel encodeResultsCoverPreview;
	private JLabel encodeResultsStegoedPreview;

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
		panelEncodeResults = new JPanel();

		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		UIManager.put("TabbedPane.selected", new java.awt.Color(235, 184, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setBackground(new java.awt.Color(41, 57, 86));
		tabbedPane.setBounds(0, 0, 1008, 675);
		frame.getContentPane().add(tabbedPane);

		JPanel panelEncode = new JPanel();
		panelEncode.setBackground(new Color(112, 128, 144));
		tabbedPane.addTab("Encode", null, panelEncode, null);
		panelEncode.setLayout(null);

		JLabel lblCoverImage = new JLabel("Upload Cover Image");
		lblCoverImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCoverImage.setForeground(new Color(255, 255, 255));
		lblCoverImage.setBounds(10, 24, 164, 14);
		panelEncode.add(lblCoverImage);

		JTextField txtCoverFilePath = new JTextField();
		txtCoverFilePath.setText("No file chosen");
		txtCoverFilePath.setColumns(10);
		txtCoverFilePath.setBounds(10, 49, 359, 20);
		panelEncode.add(txtCoverFilePath);

		JLabel coverImage = new JLabel("Cover Preview");
		coverImage.setOpaque(true);
		coverImage.setHorizontalAlignment(SwingConstants.CENTER);
		coverImage.setForeground(Color.WHITE);
		coverImage.setBackground(Color.LIGHT_GRAY);
		coverImage.setBounds(10, 80, 472, 486);
		panelEncode.add(coverImage);

		JButton btnChooseCover = new JButton("Choose File");
		btnChooseCover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coverImagePath = HelperFunctions.SelectFile();
				coverImageExtensionType = HelperFunctions.GetFileExtension(coverImagePath);
				txtCoverFilePath.setText(coverImagePath);
				ImageIcon img = new ImageIcon(new ImageIcon(coverImagePath).getImage()
						.getScaledInstance(coverImage.getWidth(), coverImage.getHeight(), Image.SCALE_SMOOTH));
				coverImage.setIcon(img);
			}
		});
		btnChooseCover.setBounds(379, 48, 103, 23);
		panelEncode.add(btnChooseCover);

		JLabel lblSecretFile = new JLabel("Upload Secret File");
		lblSecretFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSecretFile.setForeground(new Color(255, 255, 255));
		lblSecretFile.setBounds(510, 24, 164, 14);
		panelEncode.add(lblSecretFile);

		JTextField txtSecretFilePath = new JTextField();
		txtSecretFilePath.setColumns(10);
		txtSecretFilePath.setBounds(509, 49, 371, 20);
		panelEncode.add(txtSecretFilePath);

		JLabel secretImage = new JLabel("Secret Preview (If applicable)");
		secretImage.setOpaque(true);
		secretImage.setHorizontalAlignment(SwingConstants.CENTER);
		secretImage.setForeground(Color.WHITE);
		secretImage.setBackground(Color.LIGHT_GRAY);
		secretImage.setBounds(509, 80, 484, 486);
		panelEncode.add(secretImage);

		JButton btnChooseSecret = new JButton("Choose File");
		btnChooseSecret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secretFilePath = HelperFunctions.SelectFile();
				txtSecretFilePath.setText(secretFilePath);
				ImageIcon img = new ImageIcon(new ImageIcon(secretFilePath).getImage()
						.getScaledInstance(coverImage.getWidth(), coverImage.getHeight(), Image.SCALE_SMOOTH));
				secretImage.setIcon(img);
			}
		});
		btnChooseSecret.setBounds(890, 48, 103, 23);
		panelEncode.add(btnChooseSecret);

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
		panelEncode.add(btnClear);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Validation
				if ((coverImagePath == "") || (coverImagePath == null)) {
					JOptionPane.showMessageDialog(null, "Please input a cover image!", "Missing input",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if ((secretFilePath == "") || (secretFilePath == null)) {
					JOptionPane.showMessageDialog(null, "Please input a secret file (payload)!", "Missing input",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				System.out.println(coverImageExtensionType);
				saveAsPath = HelperFunctions.SaveFile(coverImageExtensionType);
				if (saveAsPath != "") {
					saveAsPath = saveAsPath + "." + coverImageExtensionType;
					Path p1 = Paths.get(coverImagePath);
					Path p2 = Paths.get(secretFilePath);
					Path p3 = Paths.get(saveAsPath);
					// public static void HidePayload(Path vesselPath, Path payloadPath, Path
					// outputPath) throws Exception{
					try {
						Hider.HidePayload(p1, p2, p3);
						encodedResult.add(new EncodedResult(p1, p2, p3));
						JOptionPane.showMessageDialog(null, "Operation Success", "Completed",
								JOptionPane.INFORMATION_MESSAGE);
						btnClear.doClick();

						// trigger encode results
						triggerEncodeResults(encodedResult);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Operation Failed", "Please retry",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Operation cancelled", "Cancel",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnGenerate.setForeground(Color.DARK_GRAY);
		btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGenerate.setBounds(0, 590, 497, 46);
		panelEncode.add(btnGenerate);

		panelEncodeResults.setEnabled(false);
		tabbedPane.addTab("Encode Result", null, panelEncodeResults, null);
		tabbedPane.setEnabledAt(tabbedPane.indexOfComponent(panelEncodeResults), false);
		panelEncodeResults.setLayout(null);
		panelEncodeResults.setBackground(new Color(112, 128, 144));

		encodeResultsCoverPreview = new JLabel("Cover Preview");
		encodeResultsCoverPreview.setOpaque(true);
		encodeResultsCoverPreview.setHorizontalAlignment(SwingConstants.CENTER);
		encodeResultsCoverPreview.setForeground(Color.WHITE);
		encodeResultsCoverPreview.setBackground(Color.LIGHT_GRAY);
		encodeResultsCoverPreview.setBounds(10, 57, 472, 486);
		panelEncodeResults.add(encodeResultsCoverPreview);

		encodeResultsStegoedPreview = new JLabel("Secret Preview (If applicable)");
		encodeResultsStegoedPreview.setOpaque(true);
		encodeResultsStegoedPreview.setHorizontalAlignment(SwingConstants.CENTER);
		encodeResultsStegoedPreview.setForeground(Color.WHITE);
		encodeResultsStegoedPreview.setBackground(Color.LIGHT_GRAY);
		encodeResultsStegoedPreview.setBounds(509, 57, 484, 486);
		panelEncodeResults.add(encodeResultsStegoedPreview);

		JLabel lblEncodeResults1 = new JLabel("Cover Image");
		lblEncodeResults1.setForeground(Color.WHITE);
		lblEncodeResults1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEncodeResults1.setBounds(10, 32, 164, 14);
		panelEncodeResults.add(lblEncodeResults1);

		JLabel lblEncodeResults2 = new JLabel("Steganographed Image");
		lblEncodeResults2.setForeground(Color.WHITE);
		lblEncodeResults2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEncodeResults2.setBounds(511, 34, 164, 14);
		panelEncodeResults.add(lblEncodeResults2);
		
		
		JPanel panel_decode = new JPanel();
		panel_decode.setForeground(new Color(0, 0, 0));
		panel_decode.setBackground(Color.WHITE);
		tabbedPane.addTab("Decode", null, panel_decode, null);
		panel_decode.setLayout(null);
		
		txtDecodePathFile = new JTextField();
		txtDecodePathFile.setText("No file chosen");
		txtDecodePathFile.setColumns(10);
		txtDecodePathFile.setBounds(20, 50, 371, 23);
		panel_decode.add(txtDecodePathFile);
		
		
		JLabel OriginalPicPreview = new JLabel("Playload(Secret) Image");
		OriginalPicPreview.setOpaque(true);
		OriginalPicPreview.setHorizontalAlignment(SwingConstants.CENTER);
		OriginalPicPreview.setForeground(Color.WHITE);
		OriginalPicPreview.setBackground(SystemColor.activeCaption);
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
		btnDecode.setContentAreaFilled(false);
		btnDecode.setFocusPainted(false);
		btnDecode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDecode.setBounds(48, 588, 193, 33);
		panel_decode.add(btnDecode);
		
	
		JLabel DecodePicPreview = new JLabel("Encoded Image");
		DecodePicPreview.setOpaque(true);
		DecodePicPreview.setHorizontalAlignment(SwingConstants.CENTER);
		DecodePicPreview.setForeground(Color.WHITE);
		DecodePicPreview.setBackground(SystemColor.activeCaption);
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
		btnDecodePic.setBounds(412, 50, 103, 23);
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
		btnClear_1.setContentAreaFilled(false);
		btnClear_1.setFocusPainted(false);
		btnClear_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear_1.setBounds(271, 588, 199, 33);
		panel_decode.add(btnClear_1);
		
		JLabel lblUpload = new JLabel("Upload Encode Image");
		lblUpload.setForeground(Color.WHITE);
		lblUpload.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUpload.setBounds(20, 26, 164, 14);
		panel_decode.add(lblUpload);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Nilia\\OneDrive\\Pictures\\photo-1508796079212-a4b83cbf734d.jpg"));
		lblNewLabel.setBounds(0, 0, 1003, 638);
		panel_decode.add(lblNewLabel);
	}


	// roll ur own methods here
	private void triggerEncodeResults(ArrayList<EncodedResult> encodedResult) throws MalformedURLException {
		// grab encode result tab index
		int panelEncodeResultsIndex = tabbedPane.indexOfComponent(panelEncodeResults);

		// enable tab and switch to it
		tabbedPane.setEnabledAt(panelEncodeResultsIndex, true);
		tabbedPane.setSelectedIndex(panelEncodeResultsIndex);

		// grab images from encodedresult and slap them into the labels
		ImageIcon encodeResultsCoverImage = new ImageIcon(encodedResult.get(encodedResult.size()-1).vesselPath.toUri().toURL());
		ImageIcon encodeResultsStegoedImage = new ImageIcon(encodedResult.get(encodedResult.size()-1).outputPath.toUri().toURL());
		encodeResultsCoverPreview.setIcon(encodeResultsCoverImage);
		encodeResultsStegoedPreview.setIcon(encodeResultsStegoedImage);
	}

	// roll ur own getter setters here

	public void frameSetVisible(Boolean visible){
		this.frame.setVisible(visible);
	}

	public JPanel getPanelEncodeResults() {
		return panelEncodeResults;
	}
}
