package controller;

import javax.swing.JOptionPane;

import java.text.SimpleDateFormat;

import java.util.ArrayList;


import model.*;

import view.*;

public class MainController {
HermesDAL DAL = HermesDAL.getInstance();

public void initComponents(){
        MainFrame frame = MainFrame.getInstance();
        frame.showLogin();
        frame.setVisible(true);
}

public void logOff(){
        DAL.disconnectDB();
        MainFrame.getInstance().showLogin();
}

public void close(){
        DAL.disconnectDB();
        System.exit(0);
}
public void showWindow(){
        String role = DAL.getRole();

        if (role.equalsIgnoreCase("OficialCredito")) {
                MainFrame.getInstance().showOCreditoView();
        }else if (role.equalsIgnoreCase("GerenteFinanciero")) {
                MainFrame.getInstance().showGGeneralFinancieroView();
        }else if(role.equalsIgnoreCase("GerenteGeneral")) {
                MainFrame.getInstance().showGGeneralFinancieroView();
        }else if(role.equalsIgnoreCase("EncargadoCredito")) {
                MainFrame.getInstance().showMainEncCredView();
        }else if(role.equalsIgnoreCase("MensajeroInterno")) {
                MainFrame.getInstance().showMInternoView();
        }
}

public Object [][] documentConverter(ArrayList<State> documets){
        Object [][] row = new Object[documets.size()][11];
        for(int i = 0; i < documets.size(); i++)
        {
                if (documets.get(i).getLoan()==null) {
                        row[i][0] = documets.get(i).getWithdrawal().getDocumentId();
                        row[i][1] = documets.get(i).getWithdrawal().getDocumentMoneyAmount();
                        row[i][2] = documets.get(i).getWithdrawal().getdocumentType();
                        row[i][3] = documets.get(i).getWithdrawal().getMember().getMemberID();
                        row[i][4] = documets.get(i).getWithdrawal().getMember().getMemberName();
                        row[i][5] = documets.get(i).getWithdrawal().getMember().getMemberLastName();
                        row[i][6] = documets.get(i).getStateStatus();
                        row[i][7] = documets.get(i).getStateDate();
                }else if (documets.get(i).getWithdrawal()==null) {
                        row[i][0] = documets.get(i).getLoan().getDocumentId();
                        row[i][1] = documets.get(i).getLoan().getDocumentMoneyAmount();
                        row[i][2] = documets.get(i).getLoan().getdocumentType();
                        row[i][3] = documets.get(i).getLoan().getMember().getMemberID();
                        row[i][4] = documets.get(i).getLoan().getMember().getMemberName();
                        row[i][5] = documets.get(i).getLoan().getMember().getMemberLastName();
                        row[i][6] = documets.get(i).getStateStatus();
                        row[i][7] = documets.get(i).getStateDate();
                }
        }
        return row;
}
}
