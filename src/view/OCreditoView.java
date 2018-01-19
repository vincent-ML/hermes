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

public class OCreditoView extends JPanel {
private SendController tableData = new SendController();
private JTable documents;
private JScrollPane scrollPane;
private Color borderColor = new Color(99,99,99);
private Border border = BorderFactory.createLineBorder(borderColor,2,false);
private Image newButtonHoverImage,newButtonImage,searchButtonHoverImage,modifyButtonHoverImage,modifyButtonImage;
private Image invalidateButtonHoverImage,invalidateButtonImage,dispatchButtonHoverImage,dispatchButtonImage,searchButtonImage;
private Image newButtonClickImage,searchButtonClickImage,modifyButtonClickImage,invalidateButtonClickImage,dispatchButtonClickImage;
public OCreditoView(){
        setLayout(new BorderLayout(10, 30));
        setBorder(BorderFactory.createEmptyBorder(15, 30, 30, 30));
        setBackground(new Color(67,67,67));
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 4,10,100));

        JLabel myLabel;
        myLabel = new JLabel("Hermes Tracking System");
        myLabel.setFont(new Font("Futura Hv BT", Font.PLAIN, 48));
        myLabel.setForeground(new Color(216,218,218));
        myLabel.setHorizontalAlignment(JLabel.CENTER);
        add(myLabel,BorderLayout.PAGE_START);

        JButton jbNewDoc = new JButton();
        jbNewDoc.setToolTipText("Register new document");
        jbNewDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbNewDoc.setContentAreaFilled(false);
        jbNewDoc.setIcon(getNewButtonImage());
        jbNewDoc.setRolloverIcon(getNewButtonHoverImage());
        jbNewDoc.setPressedIcon(getNewButtonClickImage());
        jbNewDoc.setPreferredSize(new Dimension(45,40));
        JButton jbSearchDoc = new JButton();
        jbSearchDoc.setToolTipText("Search document");
        jbSearchDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbSearchDoc.setContentAreaFilled(false);
        jbSearchDoc.setIcon(getSearchButtonImage());
        jbSearchDoc.setRolloverIcon(getSearchButtonHoverImage());
        jbSearchDoc.setPressedIcon(getSearchButtonClickImage());
        jbSearchDoc.setPreferredSize(new Dimension(45,40));
        JButton jbModifyDoc = new JButton();
        jbModifyDoc.setToolTipText("Modify document");
        jbModifyDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbModifyDoc.setContentAreaFilled(false);
        jbModifyDoc.setIcon(getModifyButtonImage());
        jbModifyDoc.setRolloverIcon(getModifyButtonHoverImage());
        jbModifyDoc.setPressedIcon(getModifyButtonClickImage());
        jbModifyDoc.setPreferredSize(new Dimension(45,40));
        JButton jbInvalidateDoc = new JButton();
        jbInvalidateDoc.setToolTipText("Invalidate document");
        jbInvalidateDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbInvalidateDoc.setContentAreaFilled(false);
        jbInvalidateDoc.setIcon(getInvalidateButtonImage());
        jbInvalidateDoc.setRolloverIcon(getInvalidateButtonHoverImage());
        jbInvalidateDoc.setPressedIcon(getInvalidateButtonClickImage());
        jbInvalidateDoc.setPreferredSize(new Dimension(45,40));
        JButton jbDispatchDoc = new JButton();
        jbDispatchDoc.setToolTipText("Dispatch document");
        jbDispatchDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbDispatchDoc.setContentAreaFilled(false);
        jbDispatchDoc.setIcon(getDispatchButtonImage());
        jbDispatchDoc.setRolloverIcon(getDispatchButtonHoverImage());
        jbDispatchDoc.setPressedIcon(getDispatchButtonClickImage());
        jbDispatchDoc.setPreferredSize(new Dimension(45,40));

        buttonsPanel.add(jbNewDoc);
        buttonsPanel.add(jbSearchDoc);
        buttonsPanel.add(jbModifyDoc);
        buttonsPanel.add(jbInvalidateDoc);
        buttonsPanel.add(jbDispatchDoc);
        buttonsPanel.setBackground(new Color(67,67,67));
        add(buttonsPanel,BorderLayout.SOUTH);

        setTable(tableData.getArray());

        jbNewDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                MainFrame.getInstance().showNewDoc();
                        }
                });
        jbSearchDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                MainFrame.getInstance().showSearchView();
                        }
                });
        jbModifyDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                int row = documents.getSelectedRow();
                                if (row >= 0) {
                                        int docID = (Integer)documents.getValueAt(row,0);
                                        ModifyView modifyDocPanel = MainFrame.getInstance().showModifyDoc();
                                        ModifyController modifyController = new ModifyController(modifyDocPanel);
                                        modifyController.searchDocument(docID);
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Document Dispatcher",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                                }
                        }
                });
        jbInvalidateDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                int row = documents.getSelectedRow();
                                if (row >= 0) {
                                        Object[] options = {"Invalidate","Cancel"};
                                        int docID = (Integer) documents.getValueAt(row,0);
                                        String docType = (String) documents.getValueAt(row,2);
                                        int selection = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Are you sure you want to invalidate the document with ID: "+docID+" ?",
                                                                                     "Document Invalidation",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/QUESTION_ICON.png"),
                                                                                     options, options[0]);
                                        if (selection == JOptionPane.YES_OPTION) {
                                                InvalidateController invalidateController = new InvalidateController();
                                                invalidateController.invalidateDocument(docID,docType);
                                                MainFrame.getInstance().showOCreditoView();
                                        }
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Document Invalidation",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
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
                                                MainFrame.getInstance().showOCreditoView();
                                        }
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Document Dispatcher",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                                }
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
private ImageIcon getNewButtonImage(){
        File file = new File ("res/buttonNew.png");
        try {
                newButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(newButtonImage);
        return icon;
}
private ImageIcon getNewButtonHoverImage(){
        File file = new File ("res/buttonNewHover.png");
        try {
                newButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(newButtonHoverImage);
        return icon;
}
private ImageIcon getNewButtonClickImage(){
        File file = new File ("res/buttonNewClick.png");
        try {
                newButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(newButtonClickImage);
        return icon;
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
// Modify ImageIcon
private ImageIcon getModifyButtonImage(){
        File file = new File ("res/buttonModify.png");
        try {
                modifyButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(modifyButtonImage);
        return icon;
}
private ImageIcon getModifyButtonHoverImage(){
        File file = new File ("res/buttonModifyHover.png");
        try {
                modifyButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(modifyButtonHoverImage);
        return icon;
}
private ImageIcon getModifyButtonClickImage(){
        File file = new File ("res/buttonModifyClick.png");
        try {
                modifyButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(modifyButtonClickImage);
        return icon;
}
private ImageIcon getInvalidateButtonImage(){
        File file = new File ("res/buttonInvalidate.png");
        try {
                invalidateButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(invalidateButtonImage);
        return icon;
}
private ImageIcon getInvalidateButtonHoverImage(){
        File file = new File ("res/buttonInvalidateHover.png");
        try {
                invalidateButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(invalidateButtonHoverImage);
        return icon;
}
private ImageIcon getInvalidateButtonClickImage(){
        File file = new File ("res/buttonInvalidateClick.png");
        try {
                invalidateButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(invalidateButtonClickImage);
        return icon;
}
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
