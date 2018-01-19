package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JOptionPane;

import controller.*;

public class ECreditoView extends JPanel {
private SendController tableData = new SendController();
private JTable documents;
private JScrollPane scrollPane;
private JButton jbSearchDoc,jbReceiveDoc,jbCheckDoc,jbReturnDoc,jbDenyDoc,jbDispatchDoc;
private JLabel myLabel;
private JPanel buttonsPanel;
private Color borderColor = new Color(99,99,99);
private Border border = BorderFactory.createLineBorder(borderColor,2,false);
private Image searchButtonImage,searchButtonHoverImage,searchButtonClickImage,receiveButtonImage,receiveButtonHoverImage,receiveButtonClickImage;
private Image checkButtonImage,checkButtonHoverImage,checkButtonClickImage,returnButtonImage,returnButtonHoverImage,returnButtonClickImage;
private Image denyButtonImage,denyButtonHoverImage,denyButtonClickImage,dispatchButtonImage,dispatchButtonHoverImage,dispatchButtonClickImage;

public ECreditoView(){
        setLayout(new BorderLayout(10, 30));
        setBackground(new Color(67,67,67));
        setBorder(BorderFactory.createEmptyBorder(15, 30, 30, 30));
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(67,67,67));
        buttonsPanel.setLayout(new GridLayout(1, 4,10,100));

        myLabel = new JLabel("Hermes Tracking System");
        myLabel.setFont(new Font("Futura Hv BT", Font.PLAIN, 48));
        myLabel.setForeground(new Color(216,218,218));
        myLabel.setHorizontalAlignment(JLabel.CENTER);
        add(myLabel,BorderLayout.PAGE_START);

        jbSearchDoc = new JButton();
        jbSearchDoc.setToolTipText("Search document");
        jbSearchDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbSearchDoc.setContentAreaFilled(false);
        jbSearchDoc.setIcon(getSearchButtonImage());
        jbSearchDoc.setRolloverIcon(getSearchButtonHoverImage());
        jbSearchDoc.setPressedIcon(getSearchButtonClickImage());
        jbSearchDoc.setPreferredSize(new Dimension(45,40));
        jbReceiveDoc = new JButton();
        jbReceiveDoc.setToolTipText("Set document as received");
        jbReceiveDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbReceiveDoc.setContentAreaFilled(false);
        jbReceiveDoc.setIcon(getReceiveButtonImage());
        jbReceiveDoc.setRolloverIcon(getReceiveButtonHoverImage());
        jbReceiveDoc.setPressedIcon(getReceiveButtonClickImage());
        jbReceiveDoc.setPreferredSize(new Dimension(45,40));
        jbCheckDoc = new JButton();
        jbCheckDoc.setToolTipText("Set document as checked");
        jbCheckDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbCheckDoc.setContentAreaFilled(false);
        jbCheckDoc.setIcon(getCheckButtonImage());
        jbCheckDoc.setRolloverIcon(getCheckButtonHoverImage());
        jbCheckDoc.setPressedIcon(getCheckButtonClickImage());
        jbCheckDoc.setPreferredSize(new Dimension(45,40));
        jbReturnDoc = new JButton();
        jbReturnDoc.setToolTipText("Return document");
        jbReturnDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbReturnDoc.setContentAreaFilled(false);
        jbReturnDoc.setIcon(getReturnButtonImage());
        jbReturnDoc.setRolloverIcon(getReturnButtonHoverImage());
        jbReturnDoc.setPressedIcon(getReturnButtonClickImage());
        jbReturnDoc.setPreferredSize(new Dimension(45,40));
        jbDenyDoc = new JButton();
        jbDenyDoc.setToolTipText("Deny document");
        jbDenyDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbDenyDoc.setContentAreaFilled(false);
        jbDenyDoc.setIcon(getDenyButtonImage());
        jbDenyDoc.setRolloverIcon(getDenyButtonHoverImage());
        jbDenyDoc.setPressedIcon(getDenyButtonClickImage());
        jbDenyDoc.setPreferredSize(new Dimension(45,40));
        jbDispatchDoc = new JButton();
        jbDispatchDoc.setToolTipText("Dispatch document");
        jbDispatchDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbDispatchDoc.setContentAreaFilled(false);
        jbDispatchDoc.setIcon(getDispatchButtonImage());
        jbDispatchDoc.setRolloverIcon(getDispatchButtonHoverImage());
        jbDispatchDoc.setPressedIcon(getDispatchButtonClickImage());
        jbDispatchDoc.setPreferredSize(new Dimension(45,40));

        buttonsPanel.add(jbReceiveDoc);
        buttonsPanel.add(jbSearchDoc);
        buttonsPanel.add(jbCheckDoc);
        buttonsPanel.add(jbReturnDoc);
        buttonsPanel.add(jbDenyDoc);
        buttonsPanel.add(jbDispatchDoc);
        add(buttonsPanel,BorderLayout.SOUTH);

        setTable(tableData.getArray());

        jbReceiveDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                int row = documents.getSelectedRow();
                                if (row >= 0) {
                                        Object[] options = {"Receive","Cancel"};
                                        int docID = (Integer) documents.getValueAt(row,0);
                                        String docType = (String) documents.getValueAt(row,2);
                                        int selection = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Are you sure you want to receive the document with ID: "+docID+" ?",
                                                                                     "Document Receiver",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/QUESTION_ICON.png"),
                                                                                     options, options[0]);
                                        if (selection == JOptionPane.YES_OPTION) {
                                                ReceiveController receiveController = new ReceiveController();
                                                receiveController.verifyDocument(docID,docType);
                                                MainFrame.getInstance().showMainEncCredView();
                                        }
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Document Receiver",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                                }
                        }
                });
        jbCheckDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                int row = documents.getSelectedRow();
                                if (row >= 0) {
                                        Object[] options = {"Check","Cancel"};
                                        int docID = (Integer) documents.getValueAt(row,0);
                                        String docType = (String) documents.getValueAt(row,2);
                                        int selection = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Have you verified the Document with ID: "+docID+" ?",
                                                                                     "Document Checker",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/QUESTION_ICON.png"),
                                                                                     options, options[0]);
                                        if (selection == JOptionPane.YES_OPTION) {
                                                CheckController checkController = new CheckController();
                                                checkController.verifyDocument(docID,docType);
                                                MainFrame.getInstance().showMainEncCredView();
                                        }
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Document Checker",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                                }
                        }
                });
        jbReturnDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                int row = documents.getSelectedRow();
                                if (row >= 0) {
                                        Object[] options = {"Return","Cancel"};
                                        int docID = (Integer) documents.getValueAt(row,0);
                                        String docType = (String) documents.getValueAt(row,2);
                                        int selection = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Are you sure you want to mark as return this Document with ID: "+docID+" ?",
                                                                                     "Document Returner",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/QUESTION_ICON.png"),
                                                                                     options, options[0]);
                                        if (selection == JOptionPane.YES_OPTION) {
                                                ReturnController returnController = new ReturnController();
                                                returnController.verifyDocument(docID,docType);
                                                MainFrame.getInstance().showMainEncCredView();
                                        }
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Document Returner",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                                }
                        }
                });
        jbDenyDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                int row = documents.getSelectedRow();
                                if (row >= 0) {
                                        Object[] options = {"Deny","Cancel"};
                                        int docID = (Integer) documents.getValueAt(row,0);
                                        String docType = (String) documents.getValueAt(row,2);
                                        int selection = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Are you sure you want to deny this Document with ID: "+docID+" ?",
                                                                                     "Deny Document",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/QUESTION_ICON.png"),
                                                                                     options, options[0]);
                                        if (selection == JOptionPane.YES_OPTION) {
                                                DenyController denyController = new DenyController();
                                                denyController.verifyDocument(docID,docType);
                                                MainFrame.getInstance().showMainEncCredView();
                                        }
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Deny Document",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                                }
                        }
                });
        jbDispatchDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                int row = documents.getSelectedRow();
                                if (row >= 0) {
                                        Object[] options = {"Dispatch","Cancel"};
                                        int docID = (Integer) documents.getValueAt(row,0);
                                        String docType = (String) documents.getValueAt(row,2);
                                        int selection = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Are you sure you want to dispatch the document with ID: "+docID+" ?",
                                                                                     "Document Dispatcher",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/QUESTION_ICON.png"),
                                                                                     options, options[0]);
                                        if (selection == JOptionPane.YES_OPTION) {
                                                DispatchController dispatchController = new DispatchController();
                                                dispatchController.verifyDocument(docID,docType);
                                                MainFrame.getInstance().showMainEncCredView();
                                        }
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Document Dispatcher",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                                }
                        }
                });

        jbSearchDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                MainFrame.getInstance().showSearchView();
                        }
                });
        documents.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent me) {
                                JTable table =(JTable) me.getSource();
                                Point p = me.getPoint();
                                int row = table.rowAtPoint(p);

                                if (me.getClickCount() == 2 && row!=-1 && me.getButton() == MouseEvent.BUTTON1) {
                                        int docID = (Integer) documents.getValueAt(row,0);
                                        MoreInfoController moreInfoController = new MoreInfoController();
                                        moreInfoController.seachInfo(docID);
                                }
                        }
                });
}
/*
 * creating table
 *
 */
public void setTable(Object [][] rows){
        if (rows!= null) {
                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(new Object[] {"Document ID","Money Amount","Document Type","Member ID","Member Name",
                                                         "Member Last Name","Current Status","Date"});

                for (int i = 0; i < rows.length; i++) {
                        model.addRow(rows[i]);
                }
                documents = new JTable(model) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }
                        @Override
                        public Class<?> getColumnClass(int columnIndex) {
                                return getValueAt(0, columnIndex).getClass();
                        }
                };
                documents.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
                documents.setToolTipText("Available Documents");
                documents.getTableHeader().setReorderingAllowed(false);
                documents.setFillsViewportHeight(true);
                documents.setAutoCreateRowSorter(true);
                documents.setBorder(border);
                documents.setBackground(new Color(67,67,67));
                documents.setForeground(new Color(216,218,218));
                documents.setFont(new Font("Futura Heavy font", Font.BOLD, 14));
                documents.setGridColor(new Color(90,90,90));
                documents.setSelectionBackground(new Color(76,175,80));
                documents.setSelectionForeground(new Color(55,55,55));
                documents.getTableHeader().setFont(new Font("Futura Heavy font", Font.BOLD, 13));
                documents.getTableHeader().setBackground(new Color(216,218,218));
                documents.getTableHeader().setBorder(border);

                scrollPane = new JScrollPane(documents);
                scrollPane.setBorder(BorderFactory.createLineBorder(new Color(90,90,90), 1));
                scrollPane.getViewport().setBackground(new Color(216,218,218));
                scrollPane.setBackground(new Color(64,64,64));
                add(scrollPane,BorderLayout.CENTER);
        }
}
//Search ImageIcon
private ImageIcon getSearchButtonImage(){
        File file = new File ("res/buttonSearch.png");
        try {
                searchButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(searchButtonImage);
        return icon;
}
private ImageIcon getSearchButtonHoverImage(){
        File file = new File ("res/buttonSearchHover.png");
        try {
                searchButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(searchButtonHoverImage);
        return icon;
}
private ImageIcon getSearchButtonClickImage(){
        File file = new File ("res/buttonSearchClick.png");
        try {
                searchButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(searchButtonClickImage);
        return icon;
}
//Receive ImageIcon
private ImageIcon getReceiveButtonImage(){
        File file = new File ("res/buttonReceive.png");
        try {
                receiveButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(receiveButtonImage);
        return icon;
}
private ImageIcon getReceiveButtonHoverImage(){
        File file = new File ("res/buttonReceiveHover.png");
        try {
                receiveButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(receiveButtonHoverImage);
        return icon;
}
private ImageIcon getReceiveButtonClickImage(){
        File file = new File ("res/buttonReceiveClick.png");
        try {
                receiveButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(receiveButtonClickImage);
        return icon;
}
//Check ImageIcon
private ImageIcon getCheckButtonImage(){
        File file = new File ("res/buttonCheck.png");
        try {
                checkButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(checkButtonImage);
        return icon;
}
private ImageIcon getCheckButtonHoverImage(){
        File file = new File ("res/buttonCheckHover.png");
        try {
                checkButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(checkButtonHoverImage);
        return icon;
}
private ImageIcon getCheckButtonClickImage(){
        File file = new File ("res/buttonCheckClick.png");
        try {
                checkButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(checkButtonClickImage);
        return icon;
}
//Return ImageIcon
private ImageIcon getReturnButtonImage(){
        File file = new File ("res/buttonReturn.png");
        try {
                returnButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(returnButtonImage);
        return icon;
}
private ImageIcon getReturnButtonHoverImage(){
        File file = new File ("res/buttonReturnHover.png");
        try {
                returnButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(returnButtonHoverImage);
        return icon;
}
private ImageIcon getReturnButtonClickImage(){
        File file = new File ("res/buttonReturnClick.png");
        try {
                returnButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(returnButtonClickImage);
        return icon;
}
//Deny ImageIcon
private ImageIcon getDenyButtonImage(){
        File file = new File ("res/buttonDeny.png");
        try {
                denyButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(denyButtonImage);
        return icon;
}
private ImageIcon getDenyButtonHoverImage(){
        File file = new File ("res/buttonDenyHover.png");
        try {
                denyButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(denyButtonHoverImage);
        return icon;
}
private ImageIcon getDenyButtonClickImage(){
        File file = new File ("res/buttonDenyClick.png");
        try {
                denyButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(denyButtonClickImage);
        return icon;
}
//Dispatch ImageIcon
private ImageIcon getDispatchButtonImage(){
        File file = new File ("res/buttonDispatch.png");
        try {
                dispatchButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(dispatchButtonImage);
        return icon;
}
private ImageIcon getDispatchButtonHoverImage(){
        File file = new File ("res/buttonDispatchHover.png");
        try {
                dispatchButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(dispatchButtonHoverImage);
        return icon;
}
private ImageIcon getDispatchButtonClickImage(){
        File file = new File ("res/buttonDispatchClick.png");
        try {
                dispatchButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(dispatchButtonClickImage);
        return icon;
}
}
