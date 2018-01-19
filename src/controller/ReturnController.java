package controller;

import view.*;

import model.*;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class ReturnController {
HermesDAL DAL = HermesDAL.getInstance();
State state = new State();
Loan loan = new Loan();
Withdrawal withdrawal = new Withdrawal();

public void verifyDocument(int docID,String docType){
        State getStateRole = null;
        String role = "";
        String state = "";
        getStateRole = DAL.getFinalState(docID);

        if(!getStateRole.getStateStatus().equals("S004") || !getStateRole.getEmployee().getEmployeeRole().equals("R002")) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "The document must be in receive status",
                        "Hermes Tracking System",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("res/INFORMATION_ICON.png")
                        );
        }else{
                returnDocument(docID,docType);
        }

}
private void returnDocument(int docID,String docType){
        if (docType.equalsIgnoreCase("Withdrawal")) {
                withdrawal.setdocumentType(docType);
                withdrawal.setDocumentId(docID);
                state.setWithdrawal(withdrawal);
                state.setStateStatus("S007");
        }else if(docType.equalsIgnoreCase("Loan")) {
                loan.setdocumentType(docType);
                loan.setDocumentId(docID);
                state.setLoan(loan);
                state.setStateStatus("S007");
        }
        DAL.registerState(state);
}
}
