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

import java.util.Date;

import controller.*;

public class MInternoView extends JPanel {
private SendController tableData = new SendController();
private JTable documents;
private JScrollPane scrollPane;
private Image searchButtonImage,searchButtonHoverImage,searchButtonClickImage,deliverButtonImage,deliverButtonHoverImage;
private Image deliverButtonClickImage,trackButtonImage,trackButtonHoverImage,trackButtonClickImage,reportButtonImage;
private Image reportButtonHoverImage,reportButtonClickImage;
private Color borderColor = new Color(99,99,99);
private Border border = BorderFactory.createLineBorder(borderColor,2,false);

public MInternoView(){
        setLayout(new BorderLayout(10, 30));
        setBackground(new Color(67,67,67));
        setBorder(BorderFactory.createEmptyBorder(15, 30, 30, 30));
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(67,67,67));
        buttonsPanel.setLayout(new GridLayout(1, 4,10,100));

        JLabel myLabel;
        myLabel = new JLabel("Hermes Tracking System");
        myLabel.setFont(new Font("Futura Hv BT", Font.PLAIN, 48));
        myLabel.setForeground(new Color(216,218,218));
        myLabel.setHorizontalAlignment(JLabel.CENTER);
        add(myLabel,BorderLayout.PAGE_START);

        setTable(tableData.getArray());

        JButton jbSearchDoc = new JButton();
        jbSearchDoc.setToolTipText("Search document");
        jbSearchDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbSearchDoc.setContentAreaFilled(false);
        jbSearchDoc.setIcon(getSearchButtonImage());
        jbSearchDoc.setRolloverIcon(getSearchButtonHoverImage());
        jbSearchDoc.setPressedIcon(getSearchButtonClickImage());
        jbSearchDoc.setPreferredSize(new Dimension(45,40));
        JButton jbTrackDoc = new JButton();
        jbTrackDoc.setToolTipText("Check document history");
        jbTrackDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbTrackDoc.setContentAreaFilled(false);
        jbTrackDoc.setIcon(getTrackButtonImage());
        jbTrackDoc.setRolloverIcon(getTrackButtonHoverImage());
        jbTrackDoc.setPressedIcon(getTrackButtonClickImage());
        jbTrackDoc.setPreferredSize(new Dimension(45,40));
        JButton jbDeliverDoc = new JButton();
        jbDeliverDoc.setToolTipText("Set document as delivered");
        jbDeliverDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbDeliverDoc.setContentAreaFilled(false);
        jbDeliverDoc.setIcon(getDeliverButtonImage());
        jbDeliverDoc.setRolloverIcon(getDeliverButtonHoverImage());
        jbDeliverDoc.setPressedIcon(getDeliverButtonClickImage());
        jbDeliverDoc.setPreferredSize(new Dimension(45,40));
        JButton jbReport = new JButton();
        jbReport.setToolTipText("Create Report");
        jbReport.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbReport.setContentAreaFilled(false);
        jbReport.setIcon(getReportButtonImage());
        jbReport.setRolloverIcon(getReportButtonHoverImage());
        jbReport.setPressedIcon(getReportButtonClickImage());
        jbReport.setPreferredSize(new Dimension(45,40));

        buttonsPanel.add(jbSearchDoc);
        buttonsPanel.add(jbTrackDoc);
        buttonsPanel.add(jbDeliverDoc);
        buttonsPanel.add(jbReport);
        add(buttonsPanel,BorderLayout.SOUTH);

        jbSearchDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                MainFrame.getInstance().showSearchView();
                        }
                });
        jbReport.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                //ReportView reportView = new ReportView();
                                //reportView.setVisible(true);
                                MainFrame.getInstance().showJasperReportView();
                        }
                });
        jbTrackDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                int row = documents.getSelectedRow();
                                if (row >= 0) {
                                        int docID = (Integer) documents.getValueAt(row,0);
                                        TrackController trackController = new TrackController();
                                        trackController.trackDocument(docID);
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Deliver Document",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                                }
                        }
                });
        jbDeliverDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                int row = documents.getSelectedRow();
                                if (row >= 0) {
                                        Object[] options = {"deliver","Cancel"};
                                        int docID = (Integer) documents.getValueAt(row,0);
                                        String docType = (String) documents.getValueAt(row,2);
                                        int selection = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Are you sure you want to mark as delivered this Document with ID: "+docID+" ?",
                                                                                     "Deliver Document",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon ("res/QUESTION_ICON.png"),
                                                                                     options, options[0]);
                                        if (selection == JOptionPane.YES_OPTION) {
                                                DeliverController deliverController = new DeliverController();
                                                deliverController.verifyDocument(docID,docType);
                                                MainFrame.getInstance().showMInternoView();
                                        }
                                }else{
                                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to Select a document.",
                                                                      "Deliver Document",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
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
                documents.getTableHeader().setReorderingAllowed(false);
                documents.setFillsViewportHeight(true);
                documents.setAutoCreateRowSorter(true);
                documents.setToolTipText("Available Documents");
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
//Track ImageIcon
private ImageIcon getTrackButtonImage(){
        File file = new File ("res/buttonTrack.png");
        try {
                trackButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(trackButtonImage);
        return icon;
}
private ImageIcon getTrackButtonHoverImage(){
        File file = new File ("res/buttonTrackHover.png");
        try {
                trackButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(trackButtonHoverImage);
        return icon;
}
private ImageIcon getTrackButtonClickImage(){
        File file = new File ("res/buttonTrackClick.png");
        try {
                trackButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(trackButtonClickImage);
        return icon;
}
//Deliver ImageIcon
private ImageIcon getDeliverButtonImage(){
        File file = new File ("res/buttonDeliver.png");
        try {
                deliverButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(deliverButtonImage);
        return icon;
}
private ImageIcon getDeliverButtonHoverImage(){
        File file = new File ("res/buttonDeliverHover.png");
        try {
                deliverButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(deliverButtonHoverImage);
        return icon;
}
private ImageIcon getDeliverButtonClickImage(){
        File file = new File ("res/buttonDeliverClick.png");
        try {
                deliverButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(deliverButtonClickImage);
        return icon;
}
//Deliver ImageIcon
private ImageIcon getReportButtonImage(){
        File file = new File ("res/buttonReport.png");
        try {
                reportButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(reportButtonImage);
        return icon;
}
private ImageIcon getReportButtonHoverImage(){
        File file = new File ("res/buttonReportHover.png");
        try {
                reportButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(reportButtonHoverImage);
        return icon;
}
private ImageIcon getReportButtonClickImage(){
        File file = new File ("res/buttonReportClick.png");
        try {
                reportButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(reportButtonClickImage);
        return icon;
}
}
