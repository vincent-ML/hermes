package view;

import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.SwingWorker;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.UIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.LoginController;

import java.net.URL;
import java.net.MalformedURLException;


public final class LoginView extends JPanel {

//declaration of Window components
private JLabel labelUserName, labelPassword,hermesTitle,hermesIcon,blankSpace1,blankSpace2,blankSpace3,loadinGif1;
private JTextField textFieldUserName;
private JPasswordField textFieldPassword;
private JButton loginButton;
private boolean doneLogin = true;
private Image wallPaper,hermesLogo,loginButtonImage,loginButtonHoverImage,loginButtonClickedImage,separatorImage;
private Icon icon;
private ProgressBar progressBar;
private JPanel jp;
private Thread t1;
private Border border;
private Color borderColor;
GridBagConstraints gConstraint = new GridBagConstraints();
LoginController loginController;

public LoginView(){

								setLayout(new GridBagLayout());

								// Labels settings
								labelUserName = new JLabel ("User:");
								labelUserName.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 15));
								labelUserName.setForeground(new Color(216,218,218));
								gConstraint.gridx = 0;
								gConstraint.gridy = 3;
								add(labelUserName,gConstraint);

								blankSpace1 = new JLabel(getSeparatorImage());
								gConstraint.gridx = 1;
								gConstraint.gridy = 2;
								add(blankSpace1,gConstraint);

								labelPassword = new JLabel("Password: ");
								labelPassword.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 15));
								labelPassword.setForeground(new Color(216,218,218));
								gConstraint.gridx = 0;
								gConstraint.gridy = 5;
								add(labelPassword,gConstraint);

								hermesIcon = new JLabel(getImage());
								gConstraint.gridx = 1;
								gConstraint.gridy = 0;
								gConstraint.gridwidth = 3;
								add(hermesIcon,gConstraint);

								hermesTitle = new JLabel("Hermes");
								hermesTitle.setForeground(new Color(216,218,218));
								hermesTitle.setFont(new Font("Roboto Bk", Font.PLAIN, 50));
								gConstraint.gridx = 1;
								gConstraint.gridy = 1;
								gConstraint.gridwidth = 3;
								add(hermesTitle,gConstraint);

								textFieldUserName = new JTextField(20);

								borderColor = new Color(99,99,99);
								border = BorderFactory.createLineBorder(borderColor,2,false);

								textFieldUserName.setToolTipText("Insert your user name E.g., 'Mgomez'");
								textFieldUserName.setBackground(new Color(64,64,64));
								textFieldUserName.setBorder(border);
								textFieldUserName.setFont(new Font("Futura Heavy font", Font.BOLD, 17));
								textFieldUserName.setForeground(new Color(216,218,218));
								gConstraint.gridx = 1;
								gConstraint.gridy = 3;
								add(textFieldUserName,gConstraint);

								gConstraint.gridx = 1;
								gConstraint.gridy = 4;
								add(new JLabel(" "),gConstraint);

								textFieldPassword = new JPasswordField(20);
								textFieldPassword.setToolTipText("Insert your password");
								textFieldPassword.setBorder(border);
								textFieldPassword.setBackground(new Color(64,64,64));
								textFieldPassword.setFont(new Font("Futura Heavy font", Font.BOLD, 17));
								textFieldPassword.setForeground(new Color(216,218,218));
								gConstraint.gridx = 1;
								gConstraint.gridy = 5;
								add(textFieldPassword,gConstraint);

								//Button settings
								blankSpace2 = new JLabel(getSeparatorImage());
								gConstraint.gridx = 1;
								gConstraint.gridy = 6;
								add(blankSpace2,gConstraint);

								loginButton = new JButton();
								loginButton.setToolTipText("Log into the system");
								loginButton.setBorderPainted(false);
								loginButton.setBorder(null);
								loginButton.setContentAreaFilled(false);
								loginButton.setIcon(getLoginButtonImage());
								loginButton.setRolloverIcon(getLoginButtonHoverImage());
								loginButton.setPressedIcon(getLoginButtonClickedImage());
								gConstraint.gridx = 1;
								gConstraint.gridy = 7;
								add(loginButton,gConstraint);

								gConstraint.gridx = 1;
								gConstraint.gridy = 8;
								add(new JLabel(" "),gConstraint);

								gConstraint.gridx = 1;
								gConstraint.gridy = 9;
								add(new JLabel(" "),gConstraint);

								jp = new JPanel();
								jp.setPreferredSize(new Dimension(110,110));
								jp.setOpaque(false);
								gConstraint.gridx = 1;
								gConstraint.gridy = 10;
								add(jp,gConstraint);

								progressBar = new ProgressBar(60,"Loading...",12);
								progressBar.setPreferredSize(new Dimension(110,110));
								progressBar.setVisible(false);
								gConstraint.gridx = 1;
								gConstraint.gridy = 10;
								add(progressBar,gConstraint);

								//JOptionPane options
								UIManager.put("OptionPane.background", new Color(67,67,67));
								UIManager.put("Panel.background", new Color(67,67,67));
								UIManager.put("OptionPane.messageForeground", new Color(238,238,238));
								UIManager.put("OptionPane.messageFont", new Font("Roboto Mono",Font.BOLD,13));

								ButtonLoginEvent();
								EnterLoginEvent();
}
public void paintComponent(Graphics g){
								super.paintComponent(g);
								File file = new File ("res/loginWallpaper.jpg");
								try {
																wallPaper = ImageIO.read(file);
								} catch (IOException e) {
																e.printStackTrace();
								}
								g.drawImage(wallPaper, 0, 0, null);
}
private ImageIcon getImage(){
								File file = new File ("res/HermesIcon150x150.png");
								try {
																hermesLogo = ImageIO.read(file);
								} catch (IOException e) {
																e.printStackTrace();
								}
								ImageIcon icon = new ImageIcon(hermesLogo);
								return icon;
}
private ImageIcon getLoginButtonImage(){
								File file = new File ("res/loginButton.png");
								try {
																loginButtonImage = ImageIO.read(file);
								} catch (IOException e) {
																e.printStackTrace();
								}
								ImageIcon icon = new ImageIcon(loginButtonImage);
								return icon;
}
private ImageIcon getLoginButtonHoverImage(){
								File file = new File ("res/buttonLoginHover.png");
								try {
																loginButtonHoverImage = ImageIO.read(file);
								} catch (IOException e) {
																e.printStackTrace();
								}
								ImageIcon icon = new ImageIcon(loginButtonHoverImage);
								return icon;
}
private ImageIcon getLoginButtonClickedImage(){
								File file = new File ("res/buttonLoginClicked.png");
								try {
																loginButtonClickedImage = ImageIO.read(file);
								} catch (IOException e) {
																e.printStackTrace();
								}
								ImageIcon icon = new ImageIcon(loginButtonClickedImage);
								return icon;
}
private ImageIcon getSeparatorImage(){
								File file = new File ("res/line.png");
								try {
																separatorImage = ImageIO.read(file);
								} catch (IOException e) {
																e.printStackTrace();
								}
								ImageIcon icon = new ImageIcon(separatorImage);
								return icon;
}

public void loginStart() {
								borderColor = new Color(194,24,91);
								border = BorderFactory.createLineBorder(borderColor,2,false);
								String userName = textFieldUserName.getText();
								String password = String.valueOf(textFieldPassword.getPassword());

								if (userName.isEmpty() || password.isEmpty()) {
																textFieldPassword.setBorder(border);
																textFieldUserName.setBorder(border);
																JOptionPane.showMessageDialog(MainFrame.getInstance(),"Username or Password fields cannot be empty.",
																																														"Hermes Tracking System",JOptionPane.WARNING_MESSAGE,new ImageIcon("res/WARNING_ICON.png"));
																doneLogin = true;
								} else {
																SwingWorker worker = new SwingWorker<String, Void>() {
																								protected String doInBackground() {
																																loginController = new LoginController(userName,password);

																																return loginController.validateUser();
																								}
																};
																SwingWorker progressWorker = new SwingWorker<Void, Void>() {
																								String userWindow = "";

																								protected Void doInBackground() {
																																progressBar.renderProgressBar();

																																return null;
																								}

																								protected void done() {
																																jp.setVisible(true);
																																progressBar.setVisible(false);
																																try {
																																								userWindow = loginController.showErrorMessage((String)worker.get());
																																								if (userWindow.isEmpty()) {
																																																textFieldPassword.setBorder(border);
																																																textFieldUserName.setBorder(border);
																																																textFieldPassword.setText("");
																																																textFieldUserName.setText("");
																																								}else{
																																																loginController.showWindow(userWindow);
																																								}
																																								doneLogin = true;
																																} catch (InterruptedException e) {
																																								e.printStackTrace();
																																} catch (ExecutionException e) {
																																								e.printStackTrace();
																																}
																								}
																};
																worker.execute();
																progressWorker.execute();

																jp.setVisible(false);
																progressBar.setVisible(true);
								}
}

public void ButtonLoginEvent(){
								loginButton.addActionListener(new ActionListener() {
																								public void actionPerformed(ActionEvent evt) {
																																if (doneLogin) {
																																								doneLogin = false;
																																								loginStart();
																																}
																								}
																});
}
public void EnterLoginEvent(){
								textFieldPassword.addKeyListener(new KeyAdapter() {
																								public void keyPressed(KeyEvent evt) {
																																if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
																																								if (doneLogin) {
																																																doneLogin = false;
																																																loginStart();
																																								}
																																}
																								}
																});
}
}
