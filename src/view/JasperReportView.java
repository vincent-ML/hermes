package view;

import java.awt.BorderLayout;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Locale;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.SwingWorker;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.swing.JRViewerController;
import net.sf.jasperreports.engine.JasperPrint;

import controller.*;

public class JasperReportView extends JPanel {
private JasperPrint jrPrint;
private JRViewer jrViewer;
private JDateChooser jdcInitialDate, jdcFinalDate;
private JPanel componentsPanel,buttonsPanel,datesPanel,progressBarBlankPanel,reportProgressPanel;
private ProgressBar progressBar;
private JLabel myLabel,jlInitialDate,jlFinalDate;
private GridLayout myGrid;
private boolean doneReport = true;
private JButton jbCreateReport,jbtHome;
private Color borderColor = new Color(99,99,99);
private Border border = BorderFactory.createLineBorder(borderColor,2,false);
private ReportController reportController;
private Image homeButtonImage,homeButtonHoverImage,homeButtonClickImage,createReportButtonImage,createReportButtonHoverImage,createReportButtonClickImage;
private GridBagConstraints gConstraint = new GridBagConstraints();

public JasperReportView (){
        setLayout(new BorderLayout(10, 15));
        setBackground(new Color(67,67,67));
        setBorder(BorderFactory.createEmptyBorder(15, 30, 30, 30));
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(67,67,67));
        buttonsPanel.setLayout(new GridLayout(1,4,20,0));


        myLabel = new JLabel("Create Report");
        myLabel.setFont(new Font("Futura Hv BT", Font.PLAIN, 48));
        myLabel.setForeground(new Color(216,218,218));
        myLabel.setHorizontalAlignment(JLabel.CENTER);
        add(myLabel,BorderLayout.PAGE_START);

        componentsPanel = new JPanel(new BorderLayout(0,10));
        componentsPanel.setBackground(new Color(67,67,67));
        add(componentsPanel,BorderLayout.CENTER);

        myGrid = new GridLayout(1,4,30,25);
        datesPanel = new JPanel(myGrid);
        datesPanel.setBackground(new Color(67,67,67));
        componentsPanel.add(datesPanel,BorderLayout.NORTH);
        myGrid.layoutContainer(componentsPanel);

        jlInitialDate = new JLabel("Initial Date:");
        jlInitialDate.setFont(new Font("Futura Heavy", Font.BOLD, 16));
        jlInitialDate.setForeground(new Color(216,218,218));

        jdcInitialDate = new JDateChooser();
        jdcInitialDate.setBackground(new Color(85,85,85));
        jdcInitialDate.setFont(new Font("Futura Heavy", Font.BOLD, 11));
        jdcInitialDate.setForeground(new Color(216,218,218));
        jdcInitialDate.setBorder(border);
        datesPanel.add(jlInitialDate);
        datesPanel.add(jdcInitialDate);

        jlFinalDate = new JLabel("Final Date");
        jlFinalDate.setFont(new Font("Futura Heavy", Font.BOLD, 16));
        jlFinalDate.setForeground(new Color(216,218,218));

        jdcFinalDate = new JDateChooser();
        jdcFinalDate.setBackground(new Color(85,85,85));
        jdcFinalDate.setFont(new Font("Futura Heavy", Font.BOLD, 11));
        jdcFinalDate.setForeground(new Color(216,218,218));
        jdcFinalDate.setBorder(border);
        datesPanel.add(jlFinalDate);
        datesPanel.add(jdcFinalDate);

        /*
         *
         * ProgressBar
         *
         */

        reportProgressPanel = new JPanel(new GridBagLayout());
        reportProgressPanel.setOpaque(false);
        //componentsPanel.add(reportProgressPanel,BorderLayout.CENTER);

        progressBar = new ProgressBar(110,"Creating Report...",15);
        progressBar.setPreferredSize(new Dimension(200,200));
        //progressBar.setVisible(false);
        gConstraint.gridx = 0;
        gConstraint.gridy = 0;
        //reportProgressPanel.add(progressBar,gConstraint);

        JPanel blankPanel1 = new JPanel();
        blankPanel1.setBackground(new Color(67,67,67));
        JPanel blankPanel2 = new JPanel();
        blankPanel2.setBackground(new Color(67,67,67));
        buttonsPanel.add(blankPanel1);
        buttonsPanel.add(blankPanel2);
        jbtHome = new JButton();
        jbtHome.setToolTipText("Back to main screen");
        jbtHome.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbtHome.setContentAreaFilled(false);
        jbtHome.setIcon(getHomeButtonImage());
        jbtHome.setRolloverIcon(getHomeButtonHoverImage());
        jbtHome.setPressedIcon(getHomeButtonClickImage());
        jbtHome.setPreferredSize(new Dimension(80,40));
        jbCreateReport = new JButton();
        jbCreateReport.setToolTipText("Process report");
        jbCreateReport.setBorder(BorderFactory.createLineBorder(new Color(29,29,29), 3));
        jbCreateReport.setContentAreaFilled(false);
        jbCreateReport.setIcon(getCreateReportButtonImage());
        jbCreateReport.setRolloverIcon(getCreateReportButtonHoverImage());
        jbCreateReport.setPressedIcon(getCreateReportButtonClickImage());
        jbCreateReport.setPreferredSize(new Dimension(80,40));
        buttonsPanel.add(jbCreateReport);
        buttonsPanel.add(jbtHome);
        add(buttonsPanel,BorderLayout.SOUTH);
        reportButton();
        goHome();
}
private void createReport(){
        Date initialDate = jdcInitialDate.getDate();
        Date finalDate = jdcFinalDate.getDate();
        if (initialDate == null || finalDate == null) {
                doneReport = true;
                JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to select the dates.",
                                              "Hermes Tracking System",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/WARNING_ICON.png"));
        }else if (initialDate.after(finalDate)) {
                doneReport = true;
                JOptionPane.showMessageDialog(MainFrame.getInstance(),"The initial date cannot be greater than the final date",
                                              "Hermes Tracking System",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/WARNING_ICON.png"));
        }else {
                SwingWorker worker = new SwingWorker<Object,Void>(){
                        protected Object doInBackground(){
                                reportController = new ReportController();
                                jrPrint = reportController.createReport(initialDate,finalDate);

                                return jrPrint;
                        }
                };
                SwingWorker progressWorker = new SwingWorker<Void,Void>(){
                        protected Void doInBackground(){
                                progressBar.renderProgressBar();

                                return null;
                        }

                        protected void done() {
                                try {
                                        jrViewer = new JRViewer((JasperPrint)worker.get());
                                        componentsPanel.removeAll();
                                        progressBar.setVisible(false);
                                        reportProgressPanel.setVisible(false);
                                        componentsPanel.add(datesPanel,BorderLayout.NORTH);
                                        componentsPanel.add(jrViewer,BorderLayout.CENTER);
                                        componentsPanel.revalidate();
                                        componentsPanel.repaint();
                                        doneReport = true;
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                } catch (ExecutionException e) {
                                        e.printStackTrace();
                                }
                        }
                };
                componentsPanel.removeAll();
                reportProgressPanel.setVisible(true);
                componentsPanel.add(datesPanel,BorderLayout.NORTH);
                componentsPanel.add(reportProgressPanel,BorderLayout.CENTER);
                reportProgressPanel.add(progressBar,gConstraint);
                componentsPanel.revalidate();
                componentsPanel.repaint();
                worker.execute();
                progressWorker.execute();
                progressBar.setVisible(true);
        }
}
private void reportButton(){
        jbCreateReport.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                if (doneReport) {
                                        doneReport = false;
                                        createReport();
                                }
                        }
                });
}

private void goHome(){
        jbtHome.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                MainFrame.getInstance().showMInternoView();
                        }
                });
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
//CreateReport
private ImageIcon getCreateReportButtonImage(){
        File file = new File ("res/buttonCreateReport.png");
        try {
                createReportButtonImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(createReportButtonImage);
        return icon;
}
private ImageIcon getCreateReportButtonHoverImage(){
        File file = new File ("res/buttonCreateReportHover.png");
        try {
                createReportButtonHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(createReportButtonHoverImage);
        return icon;
}
private ImageIcon getCreateReportButtonClickImage(){
        File file = new File ("res/buttonCreateReportClick.png");
        try {
                createReportButtonClickImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(createReportButtonClickImage);
        return icon;
}
}
