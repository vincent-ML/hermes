package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.*;

public class SearchView extends JPanel {
private DefaultTableModel model;
private JTextField jtfMemberId,jtfMemberCedula,jtfDocumentId;
private JTable documents;
private JScrollPane scrollPane;
private JPanel componentsPanel,buttonsPanel,docInfoPanel;
private JLabel myLabel,jlMemberId,jlMemberCedula,jlDocumentId,jlDocumenType;
private JComboBox<String> jcbDocumenType;
private GridLayout myGrid;
private JButton jbtHome,jbSearchDoc;
private Color borderColor = new Color(99,99,99);
private Border border = BorderFactory.createLineBorder(borderColor,2,false);
private Image searchButtonImage, searchButtonHoverImage,searchButtonClickImage,homeButtonImage,homeButtonHoverImage,homeButtonClickImage;
private SearchController searchController = new SearchController(this);
private MainController mainController = new MainController();

public SearchView(){
        setLayout(new BorderLayout(10, 25));
        setBackground(new Color(67,67,67));
        setBorder(BorderFactory.createEmptyBorder(15, 30, 20, 30));
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(67,67,67));
        buttonsPanel.setLayout(new GridLayout(1,5,20,0));


        myLabel = new JLabel("Search Document");
        myLabel.setFont(new Font("Futura Hv BT", Font.PLAIN, 48));
        myLabel.setForeground(new Color(216,218,218));
        myLabel.setHorizontalAlignment(JLabel.CENTER);
        add(myLabel,BorderLayout.PAGE_START);

        componentsPanel = new JPanel(new BorderLayout(0,15));
        componentsPanel.setBackground(new Color(67,67,67));
        add(componentsPanel,BorderLayout.CENTER);

        myGrid = new GridLayout(2,4,40,15);
        docInfoPanel = new JPanel(myGrid);
        docInfoPanel.setBackground(new Color(67,67,67));
        componentsPanel.add(docInfoPanel,BorderLayout.NORTH);
        //myGrid.layoutContainer(componentsPanel);

        jlMemberId = new JLabel("Member ID:");
        jlMemberId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlMemberId.setForeground(new Color(216,218,218));
        jtfMemberId = new JTextField();
        jtfMemberId.setToolTipText("ID of the member");
        jtfMemberId.setBackground(new Color(85,85,85));
        jtfMemberId.setFont(new Font("Futura Heavy", Font.BOLD, 16));
        jtfMemberId.setForeground(new Color(216,218,218));
        jtfMemberId.setBorder(border);
        docInfoPanel.add(jlMemberId);
        docInfoPanel.add(jtfMemberId);

        jlMemberCedula = new JLabel("Member Cedula:");
        jlMemberCedula.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlMemberCedula.setForeground(new Color(216,218,218));
        jtfMemberCedula = new JTextField();
        jtfMemberCedula.setToolTipText("Cedula of the member");
        jtfMemberCedula.setBackground(new Color(85,85,85));
        jtfMemberCedula.setFont(new Font("Futura Heavy", Font.BOLD, 16));
        jtfMemberCedula.setForeground(new Color(216,218,218));
        jtfMemberCedula.setBorder(border);
        docInfoPanel.add(jlMemberCedula);
        docInfoPanel.add(jtfMemberCedula);

        setTable();

        jlDocumentId = new JLabel("Document ID:");
        jlDocumentId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlDocumentId.setForeground(new Color(216,218,218));
        jtfDocumentId = new JTextField();
        jtfDocumentId.setToolTipText("ID of the document");
        jtfDocumentId.setBackground(new Color(85,85,85));
        jtfDocumentId.setFont(new Font("Futura Heavy", Font.BOLD, 16));
        jtfDocumentId.setForeground(new Color(216,218,218));
        jtfDocumentId.setBorder(border);
        docInfoPanel.add(jlDocumentId);
        docInfoPanel.add(jtfDocumentId);

        jlDocumenType = new JLabel("Document Type:");
        jlDocumenType.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlDocumenType.setForeground(new Color(216,218,218));
        jcbDocumenType = new JComboBox<String>(new String [] {"","Withdrawal","Loan"});
        jcbDocumenType.setToolTipText("Type of Documents");
        jcbDocumenType.setBackground(new Color(85,85,85));
        jcbDocumenType.setBorder(border);
        jcbDocumenType.setFont(new Font("Futura Heavy", Font.BOLD, 16));
        jcbDocumenType.setForeground(new Color(216,218,218));
        docInfoPanel.add(jlDocumenType);
        docInfoPanel.add(jcbDocumenType);

        jbtHome = new JButton();
        jbtHome.setToolTipText("Back to main screen");
        jbtHome.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbtHome.setContentAreaFilled(false);
        jbtHome.setIcon(getHomeButtonImage());
        jbtHome.setRolloverIcon(getHomeButtonHoverImage());
        jbtHome.setPressedIcon(getHomeButtonClickImage());
        jbtHome.setPreferredSize(new Dimension(80,40));
        jbSearchDoc = new JButton();
        jbSearchDoc.setToolTipText("Search documents");
        jbSearchDoc.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbSearchDoc.setContentAreaFilled(false);
        jbSearchDoc.setIcon(getSearchButtonImage());
        jbSearchDoc.setRolloverIcon(getSearchButtonHoverImage());
        jbSearchDoc.setPressedIcon(getSearchButtonClickImage());
        jbSearchDoc.setPreferredSize(new Dimension(80,40));
        JPanel blankPanel = new JPanel();
        blankPanel.setBackground(new Color(67,67,67));
        buttonsPanel.add(blankPanel);
        buttonsPanel.add(blankPanel);
        buttonsPanel.add(jbSearchDoc);
        buttonsPanel.add(jbtHome);
        add(buttonsPanel,BorderLayout.SOUTH);

        jbtHome.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                mainController.showWindow();
                        }
                });
        jbSearchDoc.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                model.setRowCount(0);
                                searchController.searcher(jtfDocumentId.getText(),jtfMemberId.getText(),
                                                          jtfMemberCedula.getText(),(String)jcbDocumenType.getSelectedItem());
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
public void setTable(){
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"Document ID","Money Amount","Document Type","Member ID","Member Name",
                                                 "Member Last Name","Current Status","Date"});


        documents = new JTable(model) {
                @Override
                public boolean isCellEditable(int row, int column) {
                        return false;
                }
        };
        documents.getTableHeader().setReorderingAllowed(false);
        documents.setFillsViewportHeight(true);
        documents.setToolTipText("Documents found");
        documents.setBorder(border);
        documents.setBackground(new Color(67,67,67));
        documents.setForeground(new Color(216,218,218));
        documents.setFont(new Font("Futura Heavy font", Font.BOLD, 14));
        documents.setGridColor(new Color(90,90,90));
        documents.setSelectionBackground(new Color(255,215,0));
        documents.setSelectionForeground(new Color(55,55,55));
        documents.getTableHeader().setFont(new Font("Futura Heavy font", Font.BOLD, 13));
        documents.getTableHeader().setBackground(new Color(216,218,218));
        documents.getTableHeader().setBorder(border);

        scrollPane = new JScrollPane(documents);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(90,90,90), 1));
        scrollPane.getViewport().setBackground(new Color(216,218,218));
        scrollPane.setBackground(new Color(64,64,64));
        componentsPanel.add(scrollPane,BorderLayout.SOUTH);
}
public void fillTable(Object [][] rows){
        DefaultTableModel model = (DefaultTableModel)documents.getModel();
        model.setRowCount(0);
        for (int i = 0; i < rows.length; i++) {
                model.addRow(rows[i]);
        }
}
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
        File file = new File ("res/buttonSearchHoverSearch.png");
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
