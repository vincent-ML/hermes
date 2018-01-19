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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controller.*;

public class LoanView extends JPanel {
private JLabel jlDocumentRegistration, jlcedula, jlphone, jlMemberId, jlMemberName, jlMemberLastName;
private JLabel jlDocumentID, jlPaymentMethod, jlTypeOfDocument, jlAmountMoney, jlTypeLoan,jlStatus,jlDate,jlTime;
private GridBagLayout gblMember, gblDocument;
private GridBagConstraints gridBagConstraintMember, gridBagConstraintDocument;
private JPanel jpMember, jpDocument, jpButtons;
private JButton jbtHome;
private JTextField jtfDocumentID,jtfMemberId, jtfMemberName, jtfMemberLastName, jtfAmountMoney,jtfcedula, jtfphone;
private JTextField jtfPaymentMethod, jtfTypeOfDocument, jtfTypeLoan,jtfStatus,jtfDate,jtfTime;
private Color borderColor = new Color(99,99,99);
private Border border = BorderFactory.createLineBorder(borderColor,2,false);
private Image homeButtonHoverImage,homeButtonImage,homeButtonClickImage;
private MainController mainController = new MainController();


public LoanView(){
        setLayout(new BorderLayout(10, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 60, 40, 60));
        setBackground(new Color(67,67,67));

        jlDocumentRegistration = new JLabel("Loan Information");
        jlDocumentRegistration.setFont(new Font("Futura Hv BT", Font.PLAIN, 48));
        jlDocumentRegistration.setForeground(new Color(216,218,218));
        jlDocumentRegistration.setHorizontalAlignment(JLabel.CENTER);
        add(jlDocumentRegistration,BorderLayout.PAGE_START);
        /*-------------------MEMBER PANEL---------------------------------*/
        gblMember = new GridBagLayout();
        jpMember = new JPanel(gblMember);
        jpMember.setPreferredSize(new Dimension(450,550));
        jpMember.setBackground(new Color(67,67,67));
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
        jtfMemberId.setToolTipText("ID of the member");
        jtfMemberId.setBackground(new Color(64,64,64));
        jtfMemberId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfMemberId.setForeground(new Color(216,218,218));
        jtfMemberId.setBorder(border);
        jtfMemberId.setEditable(false);
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
        jtfcedula.setToolTipText("Cedula of the member");
        jtfcedula.setBackground(new Color(64,64,64));
        jtfcedula.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfcedula.setForeground(new Color(216,218,218));
        jtfcedula.setBorder(border);
        jtfcedula.setEditable(false);
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
        jtfMemberName.setToolTipText("Name of the member");
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
        jtfMemberLastName.setToolTipText("Last name of the member");
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
        jtfphone.setToolTipText("Phone number of the member");
        jtfphone.setBackground(new Color(64,64,64));
        jtfphone.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfphone.setForeground(new Color(216,218,218));
        jtfphone.setBorder(border);
        jtfphone.setEditable(false);
        gridBagConstraintMember.gridx = 1;
        gridBagConstraintMember.gridy = 5;
        jpMember.add(jtfphone,gridBagConstraintMember);

        //gridBagConstraintMember.insets = new Insets(50, 125, 30, 0);

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

        gridBagConstraintDocument.insets = new Insets(-210, 0, -130, 0);
        jlDocumentID = new JLabel("Document ID:           ");
        jlDocumentID.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlDocumentID.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 1;
        jpDocument.add(jlDocumentID,gridBagConstraintDocument);

        jtfDocumentID = new JTextField(14);
        jtfDocumentID.setToolTipText("ID of the document");
        jtfDocumentID.setBackground(new Color(64,64,64));
        jtfDocumentID.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfDocumentID.setForeground(new Color(216,218,218));
        jtfDocumentID.setBorder(border);
        jtfDocumentID.setEditable(false);
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 1;
        jpDocument.add(jtfDocumentID,gridBagConstraintDocument);

        gridBagConstraintDocument.insets = new Insets(-5, 0, 30, 0);
        jlTypeOfDocument = new JLabel("Type of Document:           ");
        jlTypeOfDocument.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlTypeOfDocument.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 2;
        jpDocument.add(jlTypeOfDocument,gridBagConstraintDocument);

        jtfTypeOfDocument = new JTextField(14);
        jtfTypeOfDocument.setToolTipText("Type of Document");
        jtfTypeOfDocument.setBackground(new Color(64,64,64));
        jtfTypeOfDocument.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfTypeOfDocument.setForeground(new Color(216,218,218));
        jtfTypeOfDocument.setBorder(border);
        jtfTypeOfDocument.setEditable(false);
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 2;
        jpDocument.add(jtfTypeOfDocument,gridBagConstraintDocument);

        jlAmountMoney = new JLabel("Amount of Money:           ");
        jlAmountMoney.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlAmountMoney.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 3;
        jpDocument.add(jlAmountMoney,gridBagConstraintDocument);

        jtfAmountMoney = new JTextField(14);
        jtfAmountMoney.setToolTipText("Money amount of the document");
        jtfAmountMoney.setBackground(new Color(64,64,64));
        jtfAmountMoney.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfAmountMoney.setForeground(new Color(216,218,218));
        jtfAmountMoney.setBorder(border);
        jtfAmountMoney.setEditable(false);
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 3;
        jpDocument.add(jtfAmountMoney,gridBagConstraintDocument);

        jlTypeLoan = new JLabel("Category of the Loan:       ");
        jlTypeLoan.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlTypeLoan.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 4;
        jpDocument.add(jlTypeLoan,gridBagConstraintDocument);

        jtfTypeLoan = new JTextField(14);
        jtfTypeLoan.setToolTipText("Loan category");
        jtfTypeLoan.setBackground(new Color(64,64,64));
        jtfTypeLoan.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfTypeLoan.setForeground(new Color(216,218,218));
        jtfTypeLoan.setBorder(border);
        jtfTypeLoan.setEditable(false);
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 4;
        jpDocument.add(jtfTypeLoan,gridBagConstraintDocument);

        jlPaymentMethod = new JLabel("Payment Method:           ");
        jlPaymentMethod.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlPaymentMethod.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 5;
        jpDocument.add(jlPaymentMethod,gridBagConstraintDocument);

        jtfPaymentMethod = new JTextField(14);
        jtfPaymentMethod.setToolTipText("Payment Method");
        jtfPaymentMethod.setBackground(new Color(64,64,64));
        jtfPaymentMethod.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfPaymentMethod.setForeground(new Color(216,218,218));
        jtfPaymentMethod.setBorder(border);
        jtfPaymentMethod.setEditable(false);
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 5;
        jpDocument.add(jtfPaymentMethod,gridBagConstraintDocument);

        jlStatus = new JLabel("Document Status:           ");
        jlStatus.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlStatus.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 6;
        jpDocument.add(jlStatus,gridBagConstraintDocument);

        jtfStatus = new JTextField(14);
        jtfStatus.setToolTipText("Current status");
        jtfStatus.setBackground(new Color(64,64,64));
        jtfStatus.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfStatus.setForeground(new Color(216,218,218));
        jtfStatus.setBorder(border);
        jtfStatus.setEditable(false);
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 6;
        jpDocument.add(jtfStatus,gridBagConstraintDocument);

        jlDate = new JLabel("Date:           ");
        jlDate.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlDate.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 7;
        jpDocument.add(jlDate,gridBagConstraintDocument);

        jtfDate = new JTextField(14);
        jtfDate.setToolTipText("Date of the current status");
        jtfDate.setBackground(new Color(64,64,64));
        jtfDate.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfDate.setForeground(new Color(216,218,218));
        jtfDate.setBorder(border);
        jtfDate.setEditable(false);
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 7;
        jpDocument.add(jtfDate,gridBagConstraintDocument);

        jlTime = new JLabel("Time:           ");
        jlTime.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlTime.setForeground(new Color(216,218,218));
        gridBagConstraintDocument.gridx = 0;
        gridBagConstraintDocument.gridy = 8;
        jpDocument.add(jlTime,gridBagConstraintDocument);

        jtfTime = new JTextField(14);
        jtfTime.setToolTipText("Time of the current status");
        jtfTime.setBackground(new Color(64,64,64));
        jtfTime.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfTime.setForeground(new Color(216,218,218));
        jtfTime.setBorder(border);
        jtfTime.setEditable(false);
        gridBagConstraintDocument.gridx = 1;
        gridBagConstraintDocument.gridy = 8;
        jpDocument.add(jtfTime,gridBagConstraintDocument);

        /* -----------------------Main Buttons-------------------*/
        jpButtons = new JPanel(new GridLayout(1,4,20,0));
        jpButtons.setBackground(new Color(67,67,67));
        add(jpButtons,BorderLayout.PAGE_END);

        jbtHome = new JButton();
        jbtHome.setToolTipText("Back to main screen");
        jbtHome.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbtHome.setContentAreaFilled(false);
        jbtHome.setIcon(getHomeButtonImage());
        jbtHome.setRolloverIcon(getHomeButtonHoverImage());
        jbtHome.setPressedIcon(getHomeButtonClickImage());
        jbtHome.setPreferredSize(new Dimension(80,40));
        JPanel blankSpace = new JPanel();
        JPanel blankSpace1 = new JPanel();
        JPanel blankSpace2 = new JPanel();
        blankSpace.setBackground(new Color(67,67,67));
        blankSpace1.setBackground(new Color(67,67,67));
        blankSpace2.setBackground(new Color(67,67,67));
        jpButtons.add(blankSpace);
        jpButtons.add(blankSpace1);
        jpButtons.add(blankSpace2);
        jpButtons.add(jbtHome);
        home();
}
private void home(){
        jbtHome.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0){
                                mainController.showWindow();
                        }
                });
}
public void setDocumentInfo(int documentId,double documentMoneyAmount,String documentType,String memberID,
                            String memberName,String memberLastName,String stateStatus,String stateDate,String stateTime,
                            String memberPhoneNumber,String memberCedula,String loanPaymentOption,String loanType){
        jtfDocumentID.setText(documentId+"");
        jtfMemberId.setText(memberID);
        jtfMemberName.setText(memberName);
        jtfMemberLastName.setText(memberLastName);
        jtfAmountMoney.setText(documentMoneyAmount+"");
        jtfcedula.setText(memberCedula);
        jtfphone.setText(memberPhoneNumber);
        jtfTypeOfDocument.setText(documentType);
        jtfStatus.setText(stateStatus);
        jtfDate.setText(stateDate);
        jtfTime.setText(stateTime);
        jtfPaymentMethod.setText(loanPaymentOption);
        jtfTypeLoan.setText(loanType);
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
