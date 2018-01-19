package controller;

import view.*;

import model.*;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class DispatchController {
HermesDAL DAL = HermesDAL.getInstance();
State state = new State();
Loan loan = new Loan();
Withdrawal withdrawal = new Withdrawal();

public void verifyDocument(int docID,String docType){
        State getStateRole = null;
        String role = "";
        String state = "";
        getStateRole = DAL.getFinalState(docID);

        if (DAL.getRole().equals("GERENTEFINANCIERO") || DAL.getRole().equals("GERENTEGENERAL")) {
                if (!getStateRole.getStateStatus().equals("S005")) {
                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"The document must be in a approved state to dispatch it",
                                                      "Hermes Tracking System",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                }else {
                        dispatchDocument(docID,docType);
                }
        }else if (DAL.getRole().equals("ENCARGADOCREDITO")) {
                if (!getStateRole.getStateStatus().equals("S001")) {
                        JOptionPane.showMessageDialog(MainFrame.getInstance(),"The document must be in a checked state to dispatch it",
                                                      "Hermes Tracking System",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
                }else {
                        dispatchDocument(docID,docType);
                }
        }else if (DAL.getRole().equals("OFICIALCREDITO")) {
                dispatchDocument(docID,docType);
        }
}

private void dispatchDocument(int docID,String docType){
        if (docType.equalsIgnoreCase("Withdrawal")) {
                withdrawal.setdocumentType(docType);
                withdrawal.setDocumentId(docID);
                state.setWithdrawal(withdrawal);
                state.setStateStatus("S003");
        }else if(docType.equalsIgnoreCase("Loan")) {
                loan.setdocumentType(docType);
                loan.setDocumentId(docID);
                state.setLoan(loan);
                state.setStateStatus("S003");
        }
        DAL.registerState(state);
}
}
