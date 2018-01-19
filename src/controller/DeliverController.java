package controller;

import view.*;

import model.*;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class DeliverController {
HermesDAL DAL = HermesDAL.getInstance();
State state = new State();
Loan loan = new Loan();
Withdrawal withdrawal = new Withdrawal();

public void verifyDocument(int docID,String docType){
        State getStateRole = null;
        String role = "";
        String state = "";
        getStateRole = DAL.getFinalState(docID);

        if ((getStateRole.getStateStatus().equals("S003")&&(getStateRole.getEmployee().getEmployeeRole().equals("R004") ||
                                                            getStateRole.getEmployee().getEmployeeRole().equals("R005"))) ||
            (getStateRole.getStateStatus().equals("S006"))) {
                deliverDocument(docID,docType);
        }else {
                JOptionPane.showMessageDialog(MainFrame.getInstance(),"The document must be dispatched by the \"Gerente General\" or \"Gerente Financiero\"",
                                              "Hermes Tracking System",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
        }
}
private void deliverDocument(int docID,String docType){
        if (docType.equalsIgnoreCase("Withdrawal")) {
                withdrawal.setdocumentType(docType);
                withdrawal.setDocumentId(docID);
                state.setWithdrawal(withdrawal);
                state.setStateStatus("S009");
        }else if(docType.equalsIgnoreCase("Loan")) {
                loan.setdocumentType(docType);
                loan.setDocumentId(docID);
                state.setLoan(loan);
                state.setStateStatus("S009");
        }
        DAL.registerState(state);
}
}
