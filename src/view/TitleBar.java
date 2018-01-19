package view;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.Spring;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.Color;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.MainController;

public class TitleBar extends JPanel {
private JLabel hermesLabel,hermesIcon;
private Spring spring;
private JButton iconify, maximize, close,logOff,help;
private MainController generalController = new MainController();
private Image logOffImage, logOffClickedImage,logOffHoverImage,minimizeImage, minimizeClickedImage,minimizeHoverImage;
private Image restoreImage, restoreClickedImage,restoreHoverImage,closeImage,closeChangeImage,hermesLogo;
private Image maximizeImage,maximizeClickedImage,maximizeHoverImage,helpImage,helpClickedImage,helpHoverImage;
private Process p = null;

public TitleBar(){
        super();
        setBackground(new Color(47,47,47));
        FlowLayout myLayout = new FlowLayout();
        myLayout.setAlignment(FlowLayout.RIGHT);
        setLayout(myLayout);

        hermesLabel = new JLabel("Hermes Tracking System");
        hermesLabel.setForeground(new Color(238,238,238));
        hermesLabel.setFont(new Font("Roboto Mono Medium",Font.PLAIN,13));
        add(hermesLabel);

        hermesIcon = new JLabel(getImage());
        add(hermesIcon);

        JLabel space = new JLabel("   ");
        add(space);
        help = new JButton();
        help.setBorderPainted(false);
        help.setBorder(null);
        help.setToolTipText("Help");
        help.setContentAreaFilled(false);
        help.setIcon(getHelpImage());
        help.setRolloverIcon(getHelpHoverImage());
        help.setPressedIcon(getHelpClickedImage());
        add(help);
        logOff = new JButton();
        logOff.setBorderPainted(false);
        logOff.setBorder(null);
        logOff.setToolTipText("Back to the login screen");
        logOff.setContentAreaFilled(false);
        logOff.setIcon(getLogOffImage());
        logOff.setRolloverIcon(getLogOffHoverImage());
        logOff.setPressedIcon(getLogOffClickedImage());
        add(logOff);
        iconify = new JButton();
        iconify.setToolTipText("Minimize");
        iconify.setBorderPainted(false);
        iconify.setBorder(null);
        iconify.setContentAreaFilled(false);
        iconify.setIcon(getMinimizeImage());
        iconify.setRolloverIcon(getMinimizeHoverImage());
        iconify.setPressedIcon(getMinimizeClickedImage());
        add(iconify);
        maximize = new JButton();
        maximize.setToolTipText("Maximize");
        maximize.setBorderPainted(false);
        maximize.setBorder(null);
        maximize.setContentAreaFilled(false);
        maximize.setIcon(getMaximizeImage());
        maximize.setRolloverIcon(getMaximizeHoverImage());
        maximize.setPressedIcon(getMaximizeClickedImage());
        add(maximize);
        close = new JButton();
        close.setToolTipText("Close");
        close.setBorderPainted(false);
        close.setBorder(null);
        close.setContentAreaFilled(false);
        close.setIcon(getCloseImage());
        close.setRolloverIcon(getCloseChangeImage());
        close.setPressedIcon(getCloseChangeImage());
        add(close);

        iconifyWindow();
        maximizeWindow();
        closeWindow();
        logOff();
        help();
}
private void logOff(){
        logOff.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0) {
                                int option = JOptionPane.showConfirmDialog(MainFrame.getInstance(),"Are you sure you want to log off?", "Hermes System",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,new ImageIcon("res/QUESTION_ICON.png"));
                                if (option == JOptionPane.OK_OPTION) {
                                        generalController.logOff();
                                }
                        }
                });
}
private void iconifyWindow(){
        iconify.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0) {
                                setMaximizeIcon();
                                MainFrame.getInstance().setExtendedState(MainFrame.getInstance().getExtendedState() | JFrame.ICONIFIED);
                        }
                });
}

private void maximizeWindow(){
        maximize.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0) {
                                if(MainFrame.getInstance().getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                                        MainFrame.getInstance().setExtendedState(JFrame.NORMAL);
                                        setMaximizeIcon();
                                }else{
                                        MainFrame.getInstance().setExtendedState(MainFrame.getInstance().getExtendedState() | JFrame.MAXIMIZED_BOTH);
                                        setMaximizeIcon();
                                }
                        }
                });
}
private void setMaximizeIcon(){
        if(MainFrame.getInstance().getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                maximize.setIcon(getRestoreImage());
                maximize.setRolloverIcon(getRestoreHoverImage());
                maximize.setPressedIcon(getRestoreClickedImage());
                maximize.setToolTipText("Restore Down");
        }else{
                maximize.setIcon(getMaximizeImage());
                maximize.setRolloverIcon(getMaximizeHoverImage());
                maximize.setPressedIcon(getMaximizeClickedImage());
                maximize.setToolTipText("Maximize");
        }
}

private void closeWindow(){
        close.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0) {
                                int option = JOptionPane.showConfirmDialog(MainFrame.getInstance(),"Are you sure you want to close the system?", "Hermes System",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,new ImageIcon("res/QUESTION_ICON.png"));
                                if (option == JOptionPane.OK_OPTION) {
                                        generalController.close();
                                }
                        }
                });
}

private void help(){
        help.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0) {
                                if (p == null) {
                                        try {
                                                p = Runtime.getRuntime().exec("hh.exe doc/Hermes_User_Guide.chm");
                                        }catch (IOException e) {
                                                JOptionPane.showMessageDialog(
                                                        MainFrame.getInstance(),
                                                        "Error opening help",
                                                        "Hermes Tracking System",
                                                        JOptionPane.ERROR_MESSAGE,
                                                        new ImageIcon("res/ERROR_ICON.png")
                                                        );
                                        }
                                }else {
                                        try {
                                                p.destroy();
                                                p = Runtime.getRuntime().exec("hh.exe doc/Hermes_User_Guide.chm");
                                        }catch (IOException e) {
                                                JOptionPane.showMessageDialog(
                                                        MainFrame.getInstance(),
                                                        "Error opening help",
                                                        "Hermes Tracking System",
                                                        JOptionPane.ERROR_MESSAGE,
                                                        new ImageIcon("res/ERROR_ICON.png")
                                                        );
                                        }
                                }
                        }
                });
}
private ImageIcon getLogOffImage(){
        File file = new File ("res/buttonLogoff.png");
        try {
                logOffImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(logOffImage);
        return icon;
}
private ImageIcon getLogOffClickedImage(){
        File file = new File ("res/buttonLogOffClicked.png");
        try {
                logOffClickedImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(logOffClickedImage);
        return icon;
}
private ImageIcon getLogOffHoverImage(){
        File file = new File ("res/buttonLogOffHover.png");
        try {
                logOffHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(logOffHoverImage);
        return icon;
}
private ImageIcon getMinimizeImage(){
        File file = new File ("res/buttonMinimize.png");
        try {
                minimizeImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(minimizeImage);
        return icon;
}
private ImageIcon getMinimizeClickedImage(){
        File file = new File ("res/buttonMinimizeClicked.png");
        try {
                minimizeClickedImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(minimizeClickedImage);
        return icon;
}
private ImageIcon getMinimizeHoverImage(){
        File file = new File ("res/buttonMinimizeHover.png");
        try {
                minimizeHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(minimizeHoverImage);
        return icon;
}
private ImageIcon getRestoreImage(){
        File file = new File ("res/buttonRestore.png");
        try {
                restoreImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(restoreImage);
        return icon;
}
private ImageIcon getRestoreClickedImage(){
        File file = new File ("res/buttonRestoreClicked.png");
        try {
                restoreClickedImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(restoreClickedImage);
        return icon;
}
private ImageIcon getRestoreHoverImage(){
        File file = new File ("res/buttonRestoreHover.png");
        try {
                restoreHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(restoreHoverImage);
        return icon;
}
private ImageIcon getMaximizeImage(){
        File file = new File ("res/buttonMaximize.png");
        try {
                maximizeImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(maximizeImage);
        return icon;
}
private ImageIcon getMaximizeClickedImage(){
        File file = new File ("res/buttonMaximizeClicked.png");
        try {
                maximizeClickedImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(maximizeClickedImage);
        return icon;
}
private ImageIcon getMaximizeHoverImage(){
        File file = new File ("res/buttonMaximizeHover.png");
        try {
                maximizeHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(maximizeHoverImage);
        return icon;
}
private ImageIcon getHelpImage(){
        File file = new File ("res/buttonHelp.png");
        try {
                helpImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(helpImage);
        return icon;
}
private ImageIcon getHelpClickedImage(){
        File file = new File ("res/buttonHelpClicked.png");
        try {
                helpClickedImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(helpClickedImage);
        return icon;
}
private ImageIcon getHelpHoverImage(){
        File file = new File ("res/buttonHelpHover.png");
        try {
                helpHoverImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(helpHoverImage);
        return icon;
}
private ImageIcon getCloseImage(){
        File file = new File ("res/buttonClose.png");
        try {
                closeImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(closeImage);
        return icon;
}
private ImageIcon getCloseChangeImage(){
        File file = new File ("res/buttonCloseChange.png");
        try {
                closeChangeImage = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(closeChangeImage);
        return icon;
}
private ImageIcon getImage(){
        File file = new File ("res/HermesIcon20x20.png");
        try {
                hermesLogo = ImageIO.read(file);
        } catch (IOException e) {
                e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(hermesLogo);
        return icon;
}
}
