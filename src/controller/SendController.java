package controller;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.util.ArrayList;

import model.*;

import view.*;

public class SendController {
HermesDAL DAL = HermesDAL.getInstance();
MainController helper = new MainController();

public Object [][] getArray() {
        ArrayList<State> loans = null;
        Object [][] row = null;
        loans = DAL.loadTable();

        if (loans == null) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "No documents found",
                        "Hermes Tracking System",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("res/INFORMATION_ICON.png")
                        );
        }else{
                row = helper.documentConverter(loans);
        }
        return row;
}
}
