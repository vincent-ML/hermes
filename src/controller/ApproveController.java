package controller;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.util.ArrayList;

import model.*;

import view.*;

public class ApproveController {
HermesDAL DAL = HermesDAL.getInstance();
State state = new State();
Loan loan = new Loan();
Withdrawal withdrawal = new Withdrawal();

public void verifyDocument(int docID,String docType){
        State getStateRole = null;
        String role = "";
        String state = "";
        getStateRole = DAL.getFinalState(docID);

        if(getStateRole.getStateStatus().equals("S005")) {
                JOptionPane.showMessageDialog(MainFrame.getInstance(),"The document was already approved.",
                                              "Hermes Tracking System",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
        }else if(!getStateRole.getStateStatus().equals("S004")) {
                JOptionPane.showMessageDialog(MainFrame.getInstance(),"The document must be in receive status.",
                                              "Hermes Tracking System",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/INFORMATION_ICON.png"));
        }else{
                approveDocument(docID,docType);
        }

}
private void approveDocument(int docID,String docType){
        if (docType.equalsIgnoreCase("Withdrawal")) {
                withdrawal.setdocumentType(docType);
                withdrawal.setDocumentId(docID);
                state.setWithdrawal(withdrawal);
                state.setStateStatus("S005");
        }else if(docType.equalsIgnoreCase("Loan")) {
                loan.setdocumentType(docType);
                loan.setDocumentId(docID);
                state.setLoan(loan);
                state.setStateStatus("S005");
        }
        DAL.registerState(state);
}
}
