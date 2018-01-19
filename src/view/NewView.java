package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controller.*;

public class NewView extends JPanel {
private JLabel jlDocumentRegistration, jlcedula, jlphone, jlMemberId, jlMemberName, jlMemberLastName;
private JLabel jlPaymentMethod, jlTypeOfDocument, jlAmountMoney, jlTypeWithdrawal, jlTypeLoan;
private GridBagLayout gblMember, gblDocument;
private GridBagConstraints gridBagConstraintMember, gridBagConstraintDocument;
private JPanel jpMember, jpDocument, jpButtons;
private JButton jbtSave, jbtClear, jbtsearch, jbtHome;
private JTextField jtfMemberId, jtfMemberName, jtfMemberLastName, jtfAmountMoney,jtfcedula, jtfphone;
private JComboBox <String> jcbPaymentMethod, jcbTypeOfDocument, jcbTypeWithdrawal, jcbTypeLoan;
private Image searchButtonImage,searchButtonHoverImage,saveButtonHoverImage,saveButtonImage,clearButtonClickImage;
private Image homeButtonHoverImage,homeButtonImage,clearButtonHoverImage,clearButtonImage,saveButtonClickImage,homeButtonClickImage;
private Color borderColor = new Color(99,99,99);
private Border border = BorderFactory.createLineBorder(borderColor,2,false);
private RegisterController registration = new RegisterController(this);


public NewView(){
        setLayout(new BorderLayout(10, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 60, 40, 60));
        setBackground(new Color(67,67,67));

        jlDocumentRegistration = new JLabel("Document Registration");
        jlDocumentRegistration.setFont(new Font("Futura Hv BT", Font.PLAIN, 48));
        jlDocumentRegistration.setForeground(new Color(216,218,218));
        jlDocumentRegistration.setHorizontalAlignment(JLabel.CENTER);
        add(jlDocumentRegistration,BorderLayout.PAGE_START);
        /*-------------------MEMBER PANEL---------------------------------*/
        gblMember = new GridBagLayout();
        jpMember = new JPanel(gblMember);
        jpMember.setBackground(new Color(67,67,67));
        jpMember.setPreferredSize(new Dimension(450,550));
        jpMember.setBorder(BorderFactory.createTitledBorder(
                                   BorderFactory.createLineBorder(new Color(29,29,29), 3, true),
                                   "Member Information",
                                   TitledBorder.LEFT,
                                   TitledBorder.TOP,
                                   new Font("Roboto Mono Medium",Font.PLAIN,18),
                                   new Color(255,215,0)
                                   ));
        add(jpMember,BorderLayout.LINE_START);
        gridBagConstraintMember = new GridBagConstraints();

        //gridBagConstraintMember.insets = new Insets(-150, 0, -70, 0);
        gridBagConstraintMember.insets = new Insets(-5, 0, 30, 0);
        jlMemberId = new JLabel("Member ID:           ");
        jlMemberId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlMemberId.setForeground(new Color(216,218,218));
        gridBagConstraintMember.gridx = 0;
        gridBagConstraintMember.gridy = 1;
        jpMember.add(jlMemberId,gridBagConstraintMember);

        jtfMemberId = new JTextField(14);
        jtfMemberId.setToolTipText("Insert ID of the member");
        jtfMemberId.setBackground(new Color(85,85,85));
        jtfMemberId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfMemberId.setForeground(new Color(216,218,218));
        jtfMemberId.setBorder(border);
        gridBagConstraintMember.gridx = 1;
        gridBagConstraintMember.gridy = 1;
        jpMember.add(jtfMemberId,gridBagConstraintMember);

        jlcedula = new JLabel("Member Cedula:           ");
        jlcedula.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlcedula.setForeground(new Color(216,218,218));
        gridBagConstraintMember.gridx = 0;
        gridBagConstraintMember.gridy = 2;
        jpMember.add(jlcedula,gridBagConstraintMember);

        jtfcedula = new JTextField(14);
        jtfcedula.setToolTipText("Insert Cedula of the member");
        jtfcedula.setBackground(new Color(85,85,85));
        jtfcedula.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfcedula.setForeground(new Color(216,218,218));
        jtfcedula.setBorder(border);
        gridBagConstraintMember.gridx = 1;
        gridBagConstraintMember.gridy = 2;
        jpMember.add(jtfcedula,gridBagConstraintMember);

        jlMemberName = new JLabel("Name:           ");
        jlMemberName.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlMemberName.setForeground(new Color(216,218,218));
        gridBagConstraintMember.gridx = 0;
        gridBagConstraintMember.gridy = 3;
        jpMember.add(jlMemberName,gridBagConstraintMember);

        jtfMemberName = new JTextField(14);
        jtfMemberName.setToolTipText("Member name");
        jtfMemberName.setBackground(new Color(64,64,64));
        jtfMemberName.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfMemberName.setForeground(new Color(216,218,218));
        jtfMemberName.setBorder(border);
        jtfMemberName.setEditable(false);
        gridBagConstraintMember.gridx = 1;
        gridBagConstraintMember.gridy = 3;
        jpMember.add(jtfMemberName,gridBagConstraintMember);

        jlMemberLastName = new JLabel("Last Name:           ");
        jlMemberLastName.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlMemberLastName.setForeground(new Color(216,218,218));
        gridBagConstraintMember.gridx = 0;
        gridBagConstraintMember.gridy = 4;
        jpMember.add(jlMemberLastName,gridBagConstraintMember);

        jtfMemberLastName = new JTextField(14);
        jtfMemberLastName.setToolTipText("Member last name");
        jtfMemberLastName.setBackground(new Color(64,64,64));
        jtfMemberLastName.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfMemberLastName.setForeground(new Color(216,218,218));
        jtfMemberLastName.setBorder(border);
        jtfMemberLastName.setEditable(false);
        gridBagConstraintMember.gridx = 1;
        gridBagConstraintMember.gridy = 4;
        jpMember.add(jtfMemberLastName,gridBagConstraintMember);

        jlphone = new JLabel("Phone Number:           ");
        jlphone.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlphone.setForeground(new Color(216,218,218));
        gridBagConstraintMember.gridx = 0;
        gridBagConstraintMember.gridy = 5;
        jpMember.add(jlphone,gridBagConstraintMember);

        jtfphone = new JTextField(14);
        jtfphone.setToolTipText("Member phone number");
        jtfphone.setBackground(new Color(64,64,64));
        jtfphone.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfphone.setForeground(new Color(216,218,218));
        jtfphone.setBorder(border);
        jtfphone.setEditable(false);
        gridBagConstraintMember.gridx = 1;
        gridBagConstraintMember.gridy = 5;
        jpMember.add(jtfphone,gridBagConstraintMember);

        gridBagConstraintMember.insets = new Insets(50, 125, 30, 0);
        jbtsearch = new JButton();
        jbtsearch.setToolTipText("Search member");
        jbtsearch.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbtsearch.setContentAreaFilled(false);
        jbtsearch.setIcon(getSearchButtonImage());
        jbtsearch.setRolloverIcon(getSearchButtonHoverImage());
        jbtsearch.setPressedIcon(getSearchButtonHoverImage());
        gridBagConstraintMember.gridx = 0;
        gridBagConstraintMember.gridy = 6;
        jbtsearch.setPreferredSize(new Dimension (95,30));
        jpMember.add(jbtsearch, gridBagConstraintMember);
        search();

        /*-------------------DOCUMENT PANEL---------------------------------*/
        gblDocument = new GridBagLayout();
        jpDocument = new JPanel(gblDocument);
        jpDocument.setBackground(new Color(67,67,67));
        jpDocument.setPreferredSize(new Dimension(425,550));
        jpDocument.setBorder(BorderFactory.createTitledBorder(
                                     BorderFactory.createLineBorder(new Color(29,29,29), 3, true),
                                     "Document Information",
                                     TitledBorder.LEFT,
                                     TitledBorder.TOP,
                                     new Font("Roboto Mono Medium",Font.PLAIN,18),
                                     new Color(255,215,0)
                                     ));
        add(jpDocument, BorderLayout.LINE_END);
        gridBagConstraintDocument = new GridBagConstraints();

        gridBagConstraintDocument.insets = new Insets(-130, 0, -45, 0);
        jlTypeOfDocument = new JLabel("Type of Document:           ");
        jlTypeOfDocument.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlTypeOfDocument.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 1;
        jpDocument.add(jlTypeOfDocument,gridBagConstraintDocument);

        jcbTypeOfDocument = new JComboBox<String>(new String [] {"","Withdrawal","Loan"});
        jcbTypeOfDocument.setToolTipText("Type of Documents");
        jcbTypeOfDocument.setBackground(new Color(85,85,85));
        jcbTypeOfDocument.setBorder(border);
        jcbTypeOfDocument.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jcbTypeOfDocument.setForeground(new Color(216,218,218));
        jcbTypeOfDocument.setPreferredSize(new Dimension(160,23));
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 1;
        jpDocument.add(jcbTypeOfDocument,gridBagConstraintDocument);

        jcbTypeOfDocument.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0){
                                if(jcbTypeOfDocument.getSelectedIndex()==1) {
                                        jcbTypeWithdrawal.setEnabled(true);
                                        jcbTypeLoan.setEnabled(false);
                                        jcbPaymentMethod.setEnabled(false);
                                        jcbTypeLoan.setSelectedIndex(0);
                                        jcbPaymentMethod.setSelectedIndex(0);
                                }else if(jcbTypeOfDocument.getSelectedIndex()==2) {
                                        jcbTypeWithdrawal.setEnabled(false);
                                        jcbTypeWithdrawal.setSelectedIndex(0);
                                        jcbTypeLoan.setEnabled(true);
                                        jcbPaymentMethod.setEnabled(true);
                                }else if(jcbTypeOfDocument.getSelectedIndex()==0) {
                                        jcbTypeWithdrawal.setEnabled(false);
                                        jcbTypeWithdrawal.setSelectedIndex(0);
                                        jcbTypeLoan.setEnabled(false);
                                        jcbPaymentMethod.setEnabled(false);
                                }
                        }
                });

        gridBagConstraintDocument.insets = new Insets(0, 0, 30, 0);

        jlAmountMoney = new JLabel("Amount of Money:           ");
        jlAmountMoney.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlAmountMoney.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 2;
        jpDocument.add(jlAmountMoney,gridBagConstraintDocument);

        jtfAmountMoney = new JTextField(14);
        jtfAmountMoney.setToolTipText("Amount of Money of the document");
        jtfAmountMoney.setBackground(new Color(85,85,85));
        jtfAmountMoney.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfAmountMoney.setForeground(new Color(216,218,218));
        jtfAmountMoney.setBorder(border);
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 2;
        jpDocument.add(jtfAmountMoney,gridBagConstraintDocument);

        jlTypeWithdrawal = new JLabel("Category of the Withdrawal:       ");
        jlTypeWithdrawal.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlTypeWithdrawal.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 3;
        jpDocument.add(jlTypeWithdrawal,gridBagConstraintDocument);

        jcbTypeWithdrawal = new JComboBox<String>(new String [] {"","Partial","Total"});
        jcbTypeWithdrawal.setToolTipText("Withdrawal categories");
        jcbTypeWithdrawal.setBackground(new Color(85,85,85));
        jcbTypeWithdrawal.setBorder(border);
        jcbTypeWithdrawal.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jcbTypeWithdrawal.setForeground(new Color(216,218,218));
        jcbTypeWithdrawal.setPreferredSize(new Dimension(160,23));
        jcbTypeWithdrawal.setEnabled(false);
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 3;
        jpDocument.add(jcbTypeWithdrawal,gridBagConstraintDocument);

        jlTypeLoan = new JLabel("Category of the Loan       ");
        jlTypeLoan.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlTypeLoan.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 4;
        jpDocument.add(jlTypeLoan,gridBagConstraintDocument);

        jcbTypeLoan = new JComboBox<String>(new String [] {"","Emergency","Ordinary","Mortgage","Vehicle"});
        jcbTypeLoan.setToolTipText("Loan categories");
        jcbTypeLoan.setBackground(new Color(85,85,85));
        jcbTypeLoan.setBorder(border);
        jcbTypeLoan.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jcbTypeLoan.setForeground(new Color(216,218,218));
        jcbTypeLoan.setPreferredSize(new Dimension(160,23));
        jcbTypeLoan.setEnabled(false);
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 4;
        jpDocument.add(jcbTypeLoan,gridBagConstraintDocument);

        jlPaymentMethod = new JLabel("Payment Method:           ");
        jlPaymentMethod.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlPaymentMethod.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 5;
        jpDocument.add(jlPaymentMethod,gridBagConstraintDocument);

        jcbPaymentMethod = new JComboBox<String>(new String [] {"","Drop-Off","Discount"});
        jcbPaymentMethod.setToolTipText("Payment Methods");
        jcbPaymentMethod.setBackground(new Color(85,85,85));
        jcbPaymentMethod.setBorder(border);
        jcbPaymentMethod.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jcbPaymentMethod.setForeground(new Color(216,218,218));
        jcbPaymentMethod.setPreferredSize(new Dimension(160,23));
        jcbPaymentMethod.setEnabled(false);
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 5;
        jpDocument.add(jcbPaymentMethod,gridBagConstraintDocument);

        /* -----------------------Main Buttons-------------------*/
        jpButtons = new JPanel(new GridLayout(1,4,20,0));
        jpButtons.setBackground(new Color(67,67,67));
        add(jpButtons,BorderLayout.PAGE_END);
        jbtSave = new JButton();
        jbtSave.setToolTipText("Register document");
        jbtSave.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbtSave.setContentAreaFilled(false);
        jbtSave.setIcon(getSaveButtonImage());
        jbtSave.setRolloverIcon(getSaveButtonHoverImage());
        jbtSave.setPressedIcon(getSaveButtonClickImage());
        jbtSave.setPreferredSize(new Dimension(80,40));
        JPanel blankSpace1 = new JPanel();
        blankSpace1.setBackground(new Color(67,67,67));
        jpButtons.add(blankSpace1);
        jpButtons.add(blankSpace1);
        jpButtons.add(jbtSave);
        save();
        jbtClear = new JButton();
        jbtClear.setToolTipText("Clear fields");
        jbtClear.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbtClear.setContentAreaFilled(false);
        jbtClear.setIcon(getClearButtonImage());
        jbtClear.setRolloverIcon(getClearButtonHoverImage());
        jbtClear.setPressedIcon(getClearButtonClickImage());
        jbtClear.setPreferredSize(new Dimension(80,40));
        jpButtons.add(jbtClear);
        jbtClear.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0){
                                clean();
                        }
                });
        jbtHome = new JButton();
        jbtHome.setToolTipText("Back to main screen");
        jbtHome.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbtHome.setContentAreaFilled(false);
        jbtHome.setIcon(getHomeButtonImage());
        jbtHome.setRolloverIcon(getHomeButtonHoverImage());
        jbtHome.setPressedIcon(getHomeButtonClickImage());
        jbtHome.setPreferredSize(new Dimension(80,40));
        jpButtons.add(jbtHome);
        home();
}
/*---------------------------constructor ends---------------------------*/

public void setMember(String memberID,String memberCedula,String memberName,String memberLastName,String memberPhoneNumber){
        jtfMemberId.setText(memberID);
        jtfcedula.setText(memberCedula);
        jtfMemberName.setText(memberName);
        jtfMemberLastName.setText(memberLastName);
        jtfphone.setText(memberPhoneNumber);
        jtfMemberId.setBackground(new Color(64,64,64));
        jtfcedula.setBackground(new Color(64,64,64));
        jtfMemberId.setEditable(false);
        jtfcedula.setEditable(false);
}
private void search(){
        jbtsearch.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0){
                                try {
                                        registration.searchMember(jtfcedula.getText(), jtfMemberId.getText());
                                }catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
}
private void save(){
        jbtSave.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0) {
                                try {
                                        registration.documentValidation(jtfMemberName.getText(),jtfMemberLastName.getText(),jtfphone.getText(),
                                                                        jtfMemberId.getText(), jtfcedula.getText(),(String)jcbTypeOfDocument.getSelectedItem(),jtfAmountMoney.getText(),
                                                                        (String)jcbTypeWithdrawal.getSelectedItem(),(String)jcbTypeLoan.getSelectedItem(),
                                                                        (String)jcbPaymentMethod.getSelectedItem());
                                }catch (Exception e) {
                                        e.printStackTrace();
                                }

                        }
                });
}
private void home(){
        jbtHome.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0){
                                MainFrame.getInstance().showOCreditoView();
                        }
                });
}
public void clean(){
        jtfMemberId.setText("");
        jtfcedula.setText("");
        jtfMemberName.setText("");
        jtfMemberLastName.setText("");
        jtfphone.setText("");
        jtfMemberId.setBackground(new Color(85,85,85));
        jtfcedula.setBackground(new Color(85,85,85));
        jtfMemberId.setEditable(true);
        jtfcedula.setEditable(true);

        jtfAmountMoney.setText("");
        jcbTypeOfDocument.setSelectedIndex(0);
        jcbTypeWithdrawal.setEnabled(false);
        jcbTypeLoan.setEnabled(false);
        jcbPaymentMethod.setEnabled(false);
        jcbTypeWithdrawal.setSelectedIndex(0);
        jcbTypeLoan.setSelectedIndex(0);
        jcbPaymentMethod.setSelectedIndex(0);
}

private ImageIcon getSearchButtonImage(){
        File file = new File ("res/buttonSearchMember.png");
        try {
                searchButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(searchButtonImage);
        return icon;
}
private ImageIcon getSearchButtonHoverImage(){
        File file = new File ("res/buttonSearchMemberHover.png");
        try {
                searchButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(searchButtonHoverImage);
        return icon;
}
private ImageIcon getSaveButtonImage(){
        File file = new File ("res/buttonSave.png");
        try {
                saveButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(saveButtonImage);
        return icon;
}
private ImageIcon getSaveButtonHoverImage(){
        File file = new File ("res/buttonSaveHover.png");
        try {
                saveButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(saveButtonHoverImage);
        return icon;
}
private ImageIcon getSaveButtonClickImage(){
        File file = new File ("res/buttonSaveClick.png");
        try {
                saveButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(saveButtonClickImage);
        return icon;
}
private ImageIcon getClearButtonImage(){
        File file = new File ("res/buttonClear.png");
        try {
                clearButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(clearButtonImage);
        return icon;
}
private ImageIcon getClearButtonHoverImage(){
        File file = new File ("res/buttonClearHover.png");
        try {
                clearButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(clearButtonHoverImage);
        return icon;
}
private ImageIcon getClearButtonClickImage(){
        File file = new File ("res/buttonClearClick.png");
        try {
                clearButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(clearButtonClickImage);
        return icon;
}
private ImageIcon getHomeButtonImage(){
        File file = new File ("res/buttonHome.png");
        try {
                homeButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(homeButtonImage);
        return icon;
}
private ImageIcon getHomeButtonHoverImage(){
        File file = new File ("res/buttonHomeHover.png");
        try {
                homeButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(homeButtonHoverImage);
        return icon;
}
private ImageIcon getHomeButtonClickImage(){
        File file = new File ("res/buttonHomeClick.png");
        try {
                homeButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(homeButtonClickImage);
        return icon;
}
}
