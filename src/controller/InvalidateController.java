package controller;

import view.*;

import model.*;

import javax.swing.JOptionPane;

public class InvalidateController {
HermesDAL DAL = HermesDAL.getInstance();
State state = new State();
Loan loan = new Loan();
Withdrawal withdrawal = new Withdrawal();

public void invalidateDocument(int docID,String docType){
        if (docType.equalsIgnoreCase("Withdrawal")) {
                withdrawal.setdocumentType(docType);
                withdrawal.setDocumentId(docID);
                state.setWithdrawal(withdrawal);
                state.setStateStatus("S002");
        }else if(docType.equalsIgnoreCase("Loan")) {
                loan.setdocumentType(docType);
                loan.setDocumentId(docID);
                state.setLoan(loan);
                state.setStateStatus("S002");
        }
        DAL.registerState(state);
}
}
