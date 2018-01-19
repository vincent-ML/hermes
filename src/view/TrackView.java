package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

public class TrackView extends JPanel {
private JTextField jtfMemberId,jtfMemberName,jtfDocumentId,jtfDocumenType;
private JTable documents;
private JScrollPane scrollPane;
private JPanel componentsPanel;
private Image homeButtonImage,homeButtonHoverImage,homeButtonClickImage;
private Color borderColor = new Color(99,99,99);
private Border border = BorderFactory.createLineBorder(borderColor,2,false);

public TrackView(){
        setLayout(new BorderLayout(10, 30));
        setBackground(new Color(67,67,67));
        setBorder(BorderFactory.createEmptyBorder(15, 30, 30, 30));
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(67,67,67));
        buttonsPanel.setLayout(new GridLayout(1, 4,30,150));

        JLabel myLabel;
        myLabel = new JLabel("Hermes Tracking System");
        myLabel.setFont(new Font("Futura Hv BT", Font.PLAIN, 48));
        myLabel.setForeground(new Color(216,218,218));
        myLabel.setHorizontalAlignment(JLabel.CENTER);
        add(myLabel,BorderLayout.PAGE_START);

        componentsPanel = new JPanel(new BorderLayout());
        componentsPanel.setBackground(new Color(67,67,67));
        add(componentsPanel,BorderLayout.CENTER);

        GridLayout myGrid = new GridLayout(2,4,40,15);
        JPanel docInfoPanel = new JPanel(myGrid);
        docInfoPanel.setBackground(new Color(67,67,67));
        componentsPanel.add(docInfoPanel,BorderLayout.NORTH);
        myGrid.layoutContainer(componentsPanel);

        JLabel jlMemberId = new JLabel("Member ID:");
        jlMemberId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlMemberId.setForeground(new Color(216,218,218));
        jtfMemberId = new JTextField();
        jtfMemberId.setToolTipText("ID of the member");
        jtfMemberId.setBackground(new Color(64,64,64));
        jtfMemberId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfMemberId.setForeground(new Color(216,218,218));
        jtfMemberId.setBorder(border);
        jtfMemberId.setEditable(false);
        docInfoPanel.add(jlMemberId);
        docInfoPanel.add(jtfMemberId);
        JLabel jlMemberName = new JLabel("Member Name:");
        jlMemberName.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlMemberName.setForeground(new Color(216,218,218));
        jtfMemberName = new JTextField();
        jtfMemberName.setToolTipText("Last Name/Name of the member");
        jtfMemberName.setBackground(new Color(64,64,64));
        jtfMemberName.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfMemberName.setForeground(new Color(216,218,218));
        jtfMemberName.setBorder(border);
        jtfMemberName.setEditable(false);
        docInfoPanel.add(jlMemberName);
        docInfoPanel.add(jtfMemberName);
        JLabel jlDocumentId = new JLabel("Document ID:");
        jlDocumentId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlDocumentId.setForeground(new Color(216,218,218));
        jtfDocumentId = new JTextField();
        jtfDocumentId.setToolTipText("ID of the document");
        jtfDocumentId.setBackground(new Color(64,64,64));
        jtfDocumentId.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfDocumentId.setForeground(new Color(216,218,218));
        jtfDocumentId.setBorder(border);
        jtfDocumentId.setEditable(false);
        docInfoPanel.add(jlDocumentId);
        docInfoPanel.add(jtfDocumentId);
        JLabel jlDocumenType = new JLabel("Document Type:");
        jlDocumenType.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jlDocumenType.setForeground(new Color(216,218,218));
        jtfDocumenType = new JTextField();
        jtfDocumenType.setToolTipText("Type of document");
        jtfDocumenType.setBackground(new Color(64,64,64));
        jtfDocumenType.setFont(new Font("Futura Heavy", Font.BOLD, 15));
        jtfDocumenType.setForeground(new Color(216,218,218));
        jtfDocumenType.setBorder(border);
        jtfDocumenType.setEditable(false);
        docInfoPanel.add(jlDocumenType);
        docInfoPanel.add(jtfDocumenType);

        JButton jbtHome = new JButton();
        jbtHome.setToolTipText("Back to main screen");
        jbtHome.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbtHome.setContentAreaFilled(false);
        jbtHome.setIcon(getHomeButtonImage());
        jbtHome.setRolloverIcon(getHomeButtonHoverImage());
        jbtHome.setPressedIcon(getHomeButtonClickImage());
        jbtHome.setPreferredSize(new Dimension(80,40));

        JPanel blankPanel1 = new JPanel();
        blankPanel1.setBackground(new Color(67,67,67));
        JPanel blankPanel2 = new JPanel();
        blankPanel2.setBackground(new Color(67,67,67));
        JPanel blankPanel3 = new JPanel();
        blankPanel3.setBackground(new Color(67,67,67));
        buttonsPanel.add(blankPanel1);
        buttonsPanel.add(blankPanel2);
        buttonsPanel.add(blankPanel3);
        buttonsPanel.add(jbtHome);
        add(buttonsPanel,BorderLayout.SOUTH);

        jbtHome.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                MainFrame.getInstance().showMInternoView();
                        }
                });
}
public void setTable(Object [][] rows){
        if (rows!= null) {
                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(new Object[] {"Date","Time","Status","Location"});

                for (int i = 0; i < rows.length; i++) {
                        model.addRow(rows[i]);
                }
                documents = new JTable(model) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }
                };
                documents.getTableHeader().setReorderingAllowed(false);
                documents.setFillsViewportHeight(true);
                documents.setToolTipText("Document history");
                documents.setBorder(border);
                documents.setBackground(new Color(67,67,67));
                documents.setForeground(new Color(216,218,218));
                documents.setFont(new Font("Futura Heavy font", Font.BOLD, 13));
                documents.setGridColor(new Color(90,90,90));
                documents.setSelectionBackground(new Color(255,215,0));
                documents.setSelectionForeground(new Color(55,55,55));
                documents.getTableHeader().setFont(new Font("Futura Heavy font", Font.BOLD, 15));
                documents.getTableHeader().setBackground(new Color(216,218,218));
                documents.getTableHeader().setBorder(border);
                documents.getColumnModel().getColumn(3).setPreferredWidth(400);

                scrollPane = new JScrollPane(documents);
                scrollPane.setBorder(BorderFactory.createLineBorder(new Color(90,90,90), 1));
                scrollPane.getViewport().setBackground(new Color(216,218,218));
                scrollPane.setBackground(new Color(64,64,64));
                componentsPanel.add(scrollPane,BorderLayout.SOUTH);

        }
}

public void setInfo(int documentID,String documentType,String memberID,String memberFullName){
        jtfMemberId.setText(memberID);
        jtfMemberName.setText(memberFullName);
        jtfDocumentId.setText(documentID+"");
        jtfDocumenType.setText(documentType);
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
