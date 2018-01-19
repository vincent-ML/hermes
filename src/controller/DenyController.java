package controller;

import view.*;

import model.*;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class DenyController {
HermesDAL DAL = HermesDAL.getInstance();
State state = new State();
Loan loan = new Loan();
Withdrawal withdrawal = new Withdrawal();

public void verifyDocument(int docID,String docType){
        State getStateRole = DAL.getFinalState(docID);
        String role = "";
        String state = "";

        if(getStateRole.getStateStatus().equals("S004") || getStateRole.getStateStatus().equals("S005")) {
                denyDocument(docID,docType);
        }else{
                JOptionPane.showMessageDialog(MainFrame.getInstance(),"Due to the status of the document, it cannot be deny",
                                              "Hermes Tracking System",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
        }
}

private void denyDocument(int docID,String docType){
        if (docType.equalsIgnoreCase("Withdrawal")) {
                withdrawal.setdocumentType(docType);
                withdrawal.setDocumentId(docID);
                state.setWithdrawal(withdrawal);
                state.setStateStatus("S006");
        }else if(docType.equalsIgnoreCase("Loan")) {
                loan.setdocumentType(docType);
                loan.setDocumentId(docID);
                state.setLoan(loan);
                state.setStateStatus("S006");
        }
        DAL.registerState(state);
}
}
