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

import java.io.File;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;

import controller.*;

public class ModifyView extends JPanel {
private JLabel jlDocumentModification, jlcedula, jlphone, jlMemberId, jlMemberName, jlMemberLastName;
private JLabel jlDocId, jlPaymentMethod, jlTypeOfDocument, jlAmountMoney, jlTypeWithdrawal, jlTypeLoan;
private GridBagLayout gblMember, gblDocument;
private GridBagConstraints gridBagConstraintMember, gridBagConstraintDocument;
private JPanel jpMember, jpDocument, jpButtons;
private JButton jbtSave, jbtHome;
private JTextField jtfDocId, jtfMemberId, jtfMemberName, jtfMemberLastName, jtfAmountMoney,jtfcedula, jtfphone;
private JComboBox <String> jcbPaymentMethod, jcbTypeOfDocument, jcbTypeWithdrawal, jcbTypeLoan;
private Color borderColor = new Color(99,99,99);
private Border border = BorderFactory.createLineBorder(borderColor,2,false);
private Image homeButtonImage,homeButtonHoverImage,homeButtonClickImage,saveButtonImage,saveButtonHoverImage,saveButtonClickImage;

public ModifyView(){
        setLayout(new BorderLayout(10, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 60, 40, 60));
        setBackground(new Color(67,67,67));

        jlDocumentModification = new JLabel("Document Modification");
        jlDocumentModification.setFont(new Font("Futura Hv BT", Font.PLAIN, 48));
        jlDocumentModification.setForeground(new Color(216,218,218));
        jlDocumentModification.setHorizontalAlignment(JLabel.CENTER);
        add(jlDocumentModification,BorderLayout.PAGE_START);

        /*
         *
         *
         * MEMBER PANEL
         */
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

        gridBagConstraintMember.insets = new Insets(-5, 0, 30, 0);

        jlDocId = new JLabel("Document ID:           ");
        jlDocId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlDocId.setForeground(new Color(216,218,218));
        gridBagConstraintMember.gridx = 0;
        gridBagConstraintMember.gridy = 1;
        jpMember.add(jlDocId,gridBagConstraintMember);

        jtfDocId = new JTextField(14);
        jtfDocId.setToolTipText("ID of the document");
        jtfDocId.setBackground(new Color(64,64,64));
        jtfDocId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfDocId.setForeground(new Color(216,218,218));
        jtfDocId.setBorder(border);
        jtfDocId.setEditable(false);
        gridBagConstraintMember.gridx = 1;
        gridBagConstraintMember.gridy = 1;
        jpMember.add(jtfDocId,gridBagConstraintMember);

        jlMemberId = new JLabel("Member ID:           ");
        jlMemberId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlMemberId.setForeground(new Color(216,218,218));
        gridBagConstraintMember.gridx = 0;
        gridBagConstraintMember.gridy = 2;
        jpMember.add(jlMemberId,gridBagConstraintMember);

        jtfMemberId = new JTextField(14);
        jtfMemberId.setToolTipText("ID of the member");
        jtfMemberId.setBackground(new Color(64,64,64));
        jtfMemberId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfMemberId.setForeground(new Color(216,218,218));
        jtfMemberId.setBorder(border);
        jtfMemberId.setEditable(false);
        gridBagConstraintMember.gridx = 1;
        gridBagConstraintMember.gridy = 2;
        jpMember.add(jtfMemberId,gridBagConstraintMember);

        jlcedula = new JLabel("Member Cedula:           ");
        jlcedula.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlcedula.setForeground(new Color(216,218,218));
        gridBagConstraintMember.gridx = 0;
        gridBagConstraintMember.gridy = 3;
        jpMember.add(jlcedula,gridBagConstraintMember);

        jtfcedula = new JTextField(14);
        jtfcedula.setToolTipText("Cedula of the member");
        jtfcedula.setBackground(new Color(64,64,64));
        jtfcedula.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfcedula.setForeground(new Color(216,218,218));
        jtfcedula.setBorder(border);
        jtfcedula.setEditable(false);
        gridBagConstraintMember.gridx = 1;
        gridBagConstraintMember.gridy = 3;
        jpMember.add(jtfcedula,gridBagConstraintMember);

        jlMemberName = new JLabel("Name:           ");
        jlMemberName.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlMemberName.setForeground(new Color(216,218,218));
        gridBagConstraintMember.gridx = 0;
        gridBagConstraintMember.gridy = 4;
        jpMember.add(jlMemberName,gridBagConstraintMember);

        jtfMemberName = new JTextField(14);
        jtfMemberName.setToolTipText("Name of the member");
        jtfMemberName.setBackground(new Color(64,64,64));
        jtfMemberName.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfMemberName.setForeground(new Color(216,218,218));
        jtfMemberName.setBorder(border);
        jtfMemberName.setEditable(false);
        gridBagConstraintMember.gridx = 1;
        gridBagConstraintMember.gridy = 4;
        jpMember.add(jtfMemberName,gridBagConstraintMember);

        jlMemberLastName = new JLabel("Last Name:           ");
        jlMemberLastName.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlMemberLastName.setForeground(new Color(216,218,218));
        gridBagConstraintMember.gridx = 0;
        gridBagConstraintMember.gridy = 5;
        jpMember.add(jlMemberLastName,gridBagConstraintMember);

        jtfMemberLastName = new JTextField(14);
        jtfMemberLastName.setToolTipText("Last name of the member");
        jtfMemberLastName.setBackground(new Color(64,64,64));
        jtfMemberLastName.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfMemberLastName.setForeground(new Color(216,218,218));
        jtfMemberLastName.setBorder(border);
        jtfMemberLastName.setEditable(false);
        gridBagConstraintMember.gridx = 1;
        gridBagConstraintMember.gridy = 5;
        jpMember.add(jtfMemberLastName,gridBagConstraintMember);

        jlphone = new JLabel("Phone Number:           ");
        jlphone.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlphone.setForeground(new Color(216,218,218));
        gridBagConstraintMember.gridx = 0;
        gridBagConstraintMember.gridy = 6;
        jpMember.add(jlphone,gridBagConstraintMember);

        jtfphone = new JTextField(14);
        jtfphone.setToolTipText("Phone number of the member");
        jtfphone.setBackground(new Color(64,64,64));
        jtfphone.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfphone.setForeground(new Color(216,218,218));
        jtfphone.setBorder(border);
        jtfphone.setEditable(false);
        gridBagConstraintMember.gridx = 1;
        gridBagConstraintMember.gridy = 6;
        jpMember.add(jtfphone,gridBagConstraintMember);

        /*
         *
         *
         * DOCUMENT PANEL
         */
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

        //gridBagConstraintMember.insets = new Insets(-5, 0, 30, 0);
        gridBagConstraintDocument.insets = new Insets(-5, 0, 30, 0);
        jlTypeOfDocument = new JLabel("Category:           ");
        jlTypeOfDocument.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlTypeOfDocument.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 1;
        jpDocument.add(jlTypeOfDocument,gridBagConstraintDocument);

        jcbTypeOfDocument = new JComboBox<String>(new String [] {"","Withdrawal","Loan"});
        jcbTypeOfDocument.setToolTipText("Type of Document");
        jcbTypeOfDocument.setBackground(new Color(85,85,85));
        jcbTypeOfDocument.setBorder(BorderFactory.createEmptyBorder());
        jcbTypeOfDocument.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jcbTypeOfDocument.setForeground(new Color(216,218,218));
        jcbTypeOfDocument.setEnabled(false);
        jcbTypeOfDocument.setPreferredSize(new Dimension(160,23));
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 1;
        jpDocument.add(jcbTypeOfDocument,gridBagConstraintDocument);

        jlAmountMoney = new JLabel("Amount of Money:           ");
        jlAmountMoney.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlAmountMoney.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 2;
        jpDocument.add(jlAmountMoney,gridBagConstraintDocument);

        jtfAmountMoney = new JTextField(14);
        jtfAmountMoney.setToolTipText("Money amount of the document");
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
        jcbTypeWithdrawal.setBackground(new Color(85,85,85));
        jcbTypeWithdrawal.setBorder(BorderFactory.createEmptyBorder());
        jcbTypeWithdrawal.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jcbTypeWithdrawal.setForeground(new Color(216,218,218));
        jcbTypeWithdrawal.setPreferredSize(new Dimension(160,23));
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
        jcbTypeLoan.setBackground(new Color(85,85,85));
        jcbTypeLoan.setBorder(BorderFactory.createEmptyBorder());
        jcbTypeLoan.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jcbTypeLoan.setForeground(new Color(216,218,218));
        jcbTypeLoan.setPreferredSize(new Dimension(160,23));
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
        jcbPaymentMethod.setBackground(new Color(85,85,85));
        jcbPaymentMethod.setBorder(BorderFactory.createEmptyBorder());
        jcbPaymentMethod.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jcbPaymentMethod.setForeground(new Color(216,218,218));
        jcbPaymentMethod.setPreferredSize(new Dimension(160,23));
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 5;
        jpDocument.add(jcbPaymentMethod,gridBagConstraintDocument);
        /**
         *
         *
         * JButton Panel
         */
        jpButtons = new JPanel(new GridLayout(1,4,20,0));
        jpButtons.setBackground(new Color(67,67,67));
        add(jpButtons,BorderLayout.PAGE_END);
        JPanel blankSpace = new JPanel();
        blankSpace.setBackground(new Color(67,67,67));
        jpButtons.add(blankSpace);
        jpButtons.add(blankSpace);
        jbtSave = new JButton();
        jbtSave.setToolTipText("Modify document");
        jbtSave.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbtSave.setContentAreaFilled(false);
        jbtSave.setIcon(getSaveButtonImage());
        jbtSave.setRolloverIcon(getSaveButtonHoverImage());
        jbtSave.setPressedIcon(getSaveButtonClickImage());
        jbtSave.setPreferredSize(new Dimension(80,40));
        jpButtons.add(jbtSave);
        jbtHome = new JButton();
        jbtHome.setToolTipText("Back to main screen");
        jbtHome.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbtHome.setContentAreaFilled(false);
        jbtHome.setIcon(getHomeButtonImage());
        jbtHome.setRolloverIcon(getHomeButtonHoverImage());
        jbtHome.setPressedIcon(getHomeButtonClickImage());
        jbtHome.setPreferredSize(new Dimension(80,40));
        jpButtons.add(jbtHome);

        jbtHome.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0){
                                MainFrame.getInstance().showOCreditoView();
                        }
                });
        jbtSave.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0){
                                getInfo();
                        }
                });
}
public void setInfo(Object [][] arrayDocument){
        jtfDocId.setText(arrayDocument [0][0]+"");
        jtfAmountMoney.setText(arrayDocument [0][1]+"");
        jcbTypeOfDocument.setSelectedItem(arrayDocument [0][2]+"");
        jtfMemberId.setText(arrayDocument [0][3]+"");
        jtfMemberName.setText(arrayDocument [0][4]+"");
        jtfMemberLastName.setText(arrayDocument [0][5]+"");
        jtfphone.setText(arrayDocument [0][6]+"");
        jtfcedula.setText(arrayDocument [0][7]+"");

        if (((String)arrayDocument [0][2]).equals("Withdrawal")) {
                jcbTypeWithdrawal.setSelectedItem(arrayDocument [0][8]+"");
                jcbTypeLoan.setEnabled(false);
                jcbPaymentMethod.setEnabled(false);
                jcbTypeWithdrawal.setToolTipText("Withdrawal category");
        }else if(((String)arrayDocument [0][2]).equals("Loan")) {
                jcbTypeWithdrawal.setEnabled(false);
                jcbTypeLoan.setToolTipText("Loan categories");
                jcbPaymentMethod.setToolTipText("Payment Methods");
                jcbTypeLoan.setSelectedItem(arrayDocument [0][8]+"");
                jcbPaymentMethod.setSelectedItem(arrayDocument [0][9]+"");
        }
}

public void getInfo(){

        ModifyController modifyController = new ModifyController(this);
        modifyController.infoVerifier(
                jtfDocId.getText(),
                (String)jcbTypeOfDocument.getSelectedItem(),
                jtfAmountMoney.getText(),
                (String)jcbTypeWithdrawal.getSelectedItem(),
                (String)jcbPaymentMethod.getSelectedItem(),
                (String)jcbTypeLoan.getSelectedItem()
                );
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
}
