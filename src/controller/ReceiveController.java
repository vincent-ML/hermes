package controller;

import view.*;

import model.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ReceiveController {
String role;
String state;
State getStateRole = null;
HermesDAL DAL = HermesDAL.getInstance();
State document = new State();
Loan loan = new Loan();
Withdrawal withdrawal = new Withdrawal();

public void verifyDocument(int docID,String docType){
        getStateRole = DAL.getFinalState(docID);
        state = getStateRole.getStateStatus();
        role = getStateRole.getEmployee().getEmployeeRole();

        if(state.equals("S004")) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "The document was already received.",
                        "Hermes Tracking System",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("res/INFORMATION_ICON.png")
                        );
        }else if(!state.equals("S003")) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "The document must be in dispatch status by the \"oficial de credito\".",
                        "Hermes Tracking System",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("res/INFORMATION_ICON.png")
                        );
        }else{
                receiveDocument(docID,docType);
        }
}

private void receiveDocument(int docID,String docType){
        if (docType.equalsIgnoreCase("Withdrawal")) {
                withdrawal.setdocumentType(docType);
                withdrawal.setDocumentId(docID);
                document.setWithdrawal(withdrawal);
                document.setStateStatus("S004");
                DAL.registerState(document);
        }else if(docType.equalsIgnoreCase("Loan")) {
                loan.setdocumentType(docType);
                loan.setDocumentId(docID);
                document.setLoan(loan);
                document.setStateStatus("S004");
                DAL.registerState(document);
        }
}
}
