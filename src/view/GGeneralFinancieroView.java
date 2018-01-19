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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JOptionPane;

import controller.*;

public class GGeneralFinancieroView extends JPanel {
private SendController tableData = new SendController();
private JTable documents;
private JScrollPane scrollPane;
private Color borderColor = new Color(99,99,99);
private Border border = BorderFactory.createLineBorder(borderColor,2,false);
private Image searchButtonImage,searchButtonHoverImage,searchButtonClickImage,receiveButtonImage,receiveButtonHoverImage,receiveButtonClickImage;
private Image denyButtonImage,denyButtonHoverImage,denyButtonClickImage,dispatchButtonImage,dispatchButtonHoverImage,dispatchButtonClickImage;
private Image approvedButtonImage,approvedButtonHoverImage,approvedButtonClickImage;
public GGeneralFinancieroView(){
        setLayout(new BorderLayout(10, 30));
        setBackground(new Color(67,67,67));
        setBorder(BorderFactory.createEmptyBorder(15, 30, 30, 30));
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(67,67,67));
        buttonsPanel.setLayout(new GridLayout(1, 5,10,100));

        JLabel myLabel;
        myLabel = new JLabel("Hermes Tracking System");
        myLabel.setFont(new Font("Futura Hv BT", Font.PLAIN, 48));
        myLabel.setForeground(new Color(216,218,218));
        myLabel.setHorizontalAlignment(JLabel.CENTER);
        add(myLabel,BorderLayout.PAGE_START);

        setTable(tableData.getArray());

        JButton jbReceiveDoc = new JButton();
        jbReceiveDoc.setToolTipText("Set document as received");
        jbReceiveDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbReceiveDoc.setContentAreaFilled(false);
        jbReceiveDoc.setIcon(getReceiveButtonImage());
        jbReceiveDoc.setRolloverIcon(getReceiveButtonHoverImage());
        jbReceiveDoc.setPressedIcon(getReceiveButtonClickImage());
        jbReceiveDoc.setPreferredSize(new Dimension(45,40));
        JButton jbSearchDoc = new JButton();
        jbSearchDoc.setToolTipText("Search document");
        jbSearchDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbSearchDoc.setContentAreaFilled(false);
        jbSearchDoc.setIcon(getSearchButtonImage());
        jbSearchDoc.setRolloverIcon(getSearchButtonHoverImage());
        jbSearchDoc.setPressedIcon(getSearchButtonClickImage());
        jbSearchDoc.setPreferredSize(new Dimension(45,40));
        JButton jbApproveDoc = new JButton();
        jbApproveDoc.setToolTipText("Approve document");
        jbApproveDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbApproveDoc.setContentAreaFilled(false);
        jbApproveDoc.setIcon(getApproveButtonImage());
        jbApproveDoc.setRolloverIcon(getApproveButtonHoverImage());
        jbApproveDoc.setPressedIcon(getApproveButtonClickImage());
        jbApproveDoc.setPreferredSize(new Dimension(45,40));
        JButton jbDenyDoc = new JButton();
        jbDenyDoc.setToolTipText("Deny document");
        jbDenyDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbDenyDoc.setContentAreaFilled(false);
        jbDenyDoc.setIcon(getDenyButtonImage());
        jbDenyDoc.setRolloverIcon(getDenyButtonHoverImage());
        jbDenyDoc.setPressedIcon(getDenyButtonClickImage());
        jbDenyDoc.setPreferredSize(new Dimension(45,40));
        JButton jbDispatchDoc = new JButton();
        jbDispatchDoc.setToolTipText("Dispatch document");
        jbDispatchDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbDispatchDoc.setContentAreaFilled(false);
        jbDispatchDoc.setIcon(getDispatchButtonImage());
        jbDispatchDoc.setRolloverIcon(getDispatchButtonHoverImage());
        jbDispatchDoc.setPressedIcon(getDispatchButtonClickImage());
        jbDispatchDoc.setPreferredSize(new Dimension(45,40));

        buttonsPanel.add(jbReceiveDoc);
        buttonsPanel.add(jbSearchDoc);
        buttonsPanel.add(jbApproveDoc);
        buttonsPanel.add(jbDenyDoc);
        buttonsPanel.add(jbDispatchDoc);
        add(buttonsPanel,BorderLayout.SOUTH);
        /*
         * Receive button
         *
         *
         */
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
                                                MainFrame.getInstance().showGGeneralFinancieroView();
                                        }
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Document Receiver",JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                                }
                        }
                });
        /*
         * Approve button
         *
         *
         */
        jbApproveDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                int row = documents.getSelectedRow();
                                if (row >= 0) {
                                        Object[] options = {"Approve","Cancel"};
                                        int docID = (Integer) documents.getValueAt(row,0);
                                        String docType = (String) documents.getValueAt(row,2);
                                        int selection = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Are you sure you want to approve the document with ID: "+docID+" ?",
                                                                                     "Approve Document",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/QUESTION_ICON.png"),
                                                                                     options, options[0]);
                                        if (selection == JOptionPane.YES_OPTION) {
                                                ApproveController approveController = new ApproveController();
                                                approveController.verifyDocument(docID,docType);
                                                MainFrame.getInstance().showGGeneralFinancieroView();
                                        }
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Approve Document",JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                                }
                        }
                });
        /*
         * Deny button
         *
         *
         */
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
                                                MainFrame.getInstance().showGGeneralFinancieroView();
                                        }
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Deny Document",JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                                }
                        }
                });
        /*
         * Dispatch button
         *
         *
         */
        jbDispatchDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                int row = documents.getSelectedRow();
                                if (row >= 0) {
                                        Object[] options = {"Dispatch","Cancel"};
                                        int docID = (Integer) documents.getValueAt(row,0);
                                        String docType = (String) documents.getValueAt(row,2);
                                        int selection = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Are you sure you want to dispatch this Document with ID: "+docID+" ?",
                                                                                     "Dispatch Document",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/QUESTION_ICON.png"),
                                                                                     options, options[0]);
                                        if (selection == JOptionPane.YES_OPTION) {
                                                DispatchController dispatchController = new DispatchController();
                                                dispatchController.verifyDocument(docID,docType);
                                                MainFrame.getInstance().showGGeneralFinancieroView();
                                        }
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Dispatch Document",JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
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
                documents.getTableHeader().setReorderingAllowed(false);
                documents.setToolTipText("Available Documents");
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
//Approve ImageIcon
private ImageIcon getApproveButtonImage(){
        File file = new File ("res/buttonApprove.png");
        try {
                approvedButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(approvedButtonImage);
        return icon;
}
private ImageIcon getApproveButtonHoverImage(){
        File file = new File ("res/buttonApproveHover.png");
        try {
                approvedButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(approvedButtonHoverImage);
        return icon;
}
private ImageIcon getApproveButtonClickImage(){
        File file = new File ("res/buttonApproveClick.png");
        try {
                approvedButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(approvedButtonClickImage);
        return icon;
}
//Deny ImageIcon
private ImageIcon getDenyButtonImage(){
        File file = new File ("res/buttonDenyG.png");
        try {
                denyButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(denyButtonImage);
        return icon;
}
private ImageIcon getDenyButtonHoverImage(){
        File file = new File ("res/buttonDenyGHover.png");
        try {
                denyButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(denyButtonHoverImage);
        return icon;
}
private ImageIcon getDenyButtonClickImage(){
        File file = new File ("res/buttonDenyGClick.png");
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
