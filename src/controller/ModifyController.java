package controller;

import view.*;
import model.*;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class ModifyController {
Loan loan;
Withdrawal withdrawal;
State state;
ModifyView modifyDocPanel;
ArrayList<State> arrayListDocument;
HermesDAL DAL = HermesDAL.getInstance();
Object [][] arrayDocument;

public ModifyController(ModifyView modifyDocPanel){
        this.modifyDocPanel = modifyDocPanel;
}
public void searchDocument(int docID){
        arrayListDocument = DAL.searchDocumentsByID(docID);
        arrayDocument = documentConverter(arrayListDocument);
        modifyDocPanel.setInfo(arrayDocument);
}
private void updateDocument(String docID,String docType,double amountOfMoney,
                            String withdrawalType,String loanPaymentOption,String loanType){
        state = new State();

        if(docType.equalsIgnoreCase("Withdrawal")) {
                withdrawal = new Withdrawal();
                withdrawal.setDocumentId(Integer.parseInt(docID));
                withdrawal.setDocumentMoneyAmount(amountOfMoney);
                withdrawal.setdocumentType(docType);
                withdrawal.setWithdrawalType(withdrawalType);
                state.setWithdrawal(withdrawal);

        }else if(docType.equalsIgnoreCase("Loan")) {
                loan = new Loan();
                loan.setDocumentId(Integer.parseInt(docID));
                loan.setDocumentMoneyAmount(amountOfMoney);
                loan.setdocumentType(docType);
                loan.setLoanType(loanType);
                loan.setLoanPaymentOption(loanPaymentOption);
                state.setLoan(loan);
        }
        state.setStateStatus("MODIFIED");

        int completed  = DAL.updateDocument(state);
        if (completed == 3) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Update has been completed",
                        "Hermes Tracking System",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("res/INFORMATION_ICON.png")
                        );
        }else{
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Error updating the document...Please try again.",
                        "Hermes Tracking System",
                        JOptionPane.ERROR_MESSAGE,
                        new ImageIcon("res/ERROR_ICON.png")
                        );
        }
}

private double amountOfMoneyConverter(String amountOfMoney){
        double amountMoneyConverted = 0;
        try{
                amountMoneyConverted = Double.parseDouble(amountOfMoney);
        }catch (NumberFormatException numberInvalid) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "The amount field doesn't allow special characters, just \".\" for decimal numbers",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
        }
        return amountMoneyConverted;
}
public void infoVerifier(String docID,String docType,String amountOfMoney,
                         String withdrawalType,String loanPaymentOption,String loanType){
        if(amountOfMoney.isEmpty()) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "The amount of money field cannot be empty.",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
        }else{
                double amountOfMoneyConverted = amountOfMoneyConverter(amountOfMoney);
                if (amountOfMoneyConverted <= 0) {
                        JOptionPane.showMessageDialog(
                                MainFrame.getInstance(),
                                "The amount of money cannot be less or equal to 0.0$.",
                                "Hermes Tracking System",
                                JOptionPane.WARNING_MESSAGE,
                                new ImageIcon("res/WARNING_ICON.png")
                                );
                }else{
                        if(docType.equalsIgnoreCase("Withdrawal") && withdrawalType.isEmpty()) {
                                JOptionPane.showMessageDialog(
                                        MainFrame.getInstance(),
                                        "The type of withdrawal cannot be empty.",
                                        "Hermes Tracking System",
                                        JOptionPane.WARNING_MESSAGE,
                                        new ImageIcon("res/WARNING_ICON.png")
                                        );
                        }else if(docType.equalsIgnoreCase("Loan") && (loanPaymentOption.isEmpty() || loanType.isEmpty())) {
                                JOptionPane.showMessageDialog(
                                        MainFrame.getInstance(),
                                        "Neither the type of loan nor the type of payment can be empty.",
                                        "Hermes Tracking System",
                                        JOptionPane.WARNING_MESSAGE,
                                        new ImageIcon("res/WARNING_ICON.png")
                                        );
                        }else {
                                updateDocument(docID,docType,amountOfMoneyConverted,withdrawalType,loanPaymentOption,loanType);
                        }
                }
        }
}
public Object [][] documentConverter(ArrayList<State> documets){
        Object [][] row = new Object[1][13];
        if (documets.get(0).getLoan()==null) {
                row[0][0] = documets.get(0).getWithdrawal().getDocumentId();
                row[0][1] = documets.get(0).getWithdrawal().getDocumentMoneyAmount();
                row[0][2] = documets.get(0).getWithdrawal().getdocumentType();
                row[0][3] = documets.get(0).getWithdrawal().getMember().getMemberID();
                row[0][4] = documets.get(0).getWithdrawal().getMember().getMemberName();
                row[0][5] = documets.get(0).getWithdrawal().getMember().getMemberLastName();
                row[0][6] = documets.get(0).getWithdrawal().getMember().getMemberPhoneNumber();
                row[0][7] = documets.get(0).getWithdrawal().getMember().getMemberCedula();
                row[0][8] = documets.get(0).getWithdrawal().getWithdrawalType();
        }else if (documets.get(0).getWithdrawal()== null) {
                row[0][0] = documets.get(0).getLoan().getDocumentId();
                row[0][1] = documets.get(0).getLoan().getDocumentMoneyAmount();
                row[0][2] = documets.get(0).getLoan().getdocumentType();
                row[0][3] = documets.get(0).getLoan().getMember().getMemberID();
                row[0][4] = documets.get(0).getLoan().getMember().getMemberName();
                row[0][5] = documets.get(0).getLoan().getMember().getMemberLastName();
                row[0][6] = documets.get(0).getLoan().getMember().getMemberPhoneNumber();
                row[0][7] = documets.get(0).getLoan().getMember().getMemberCedula();
                row[0][8] = documets.get(0).getLoan().getLoanType();
                row[0][9] = documets.get(0).getLoan().getLoanPaymentOption();
        }
        return row;
}
}
