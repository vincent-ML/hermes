package controller;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import view.*;
import model.*;

public class RegisterController {
MainController myGeneral = new MainController();
HermesDAL DAL = HermesDAL.getInstance();
NewView newDoc;

public RegisterController(NewView newDocPanel){
        newDoc = newDocPanel;
}

public void searchMember(String cedula, String id){
        if(!cedula.isEmpty()) {
                String cedulaVerified = cedulaVerifier(cedula);
                if (!cedulaVerified.equals("")) {
                        Member aMember = DAL.getMember(cedulaVerified);
                        if(aMember == null) {
                                JOptionPane.showMessageDialog(
                                        MainFrame.getInstance(),
                                        "Member Not Found.",
                                        "Hermes Tracking System",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        new ImageIcon("res/INFORMATION_ICON.png")
                                        );
                        }else{
                                newDoc.setMember(aMember.getMemberID(), aMember.getMemberCedula(),
                                                 aMember.getMemberName(), aMember.getMemberLastName(), aMember.getMemberPhoneNumber());
                        }
                }
        }else if (!id.isEmpty()) {
                String idVerified = idVerifier(id);
                if (!idVerified.equals("")) {
                        Member aMember = DAL.getMember(idVerified);
                        if(aMember == null) {
                                JOptionPane.showMessageDialog(
                                        MainFrame.getInstance(),
                                        "Member Not Found.",
                                        "Hermes Tracking System",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        new ImageIcon("res/INFORMATION_ICON.png")
                                        );
                        }else{
                                newDoc.setMember(aMember.getMemberID(), aMember.getMemberCedula(),
                                                 aMember.getMemberName(), aMember.getMemberLastName(), aMember.getMemberPhoneNumber());
                        }
                }
        }else if(cedula.isEmpty() && id.isEmpty()) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Need to Introduce an ID or a Cedula.",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
        }

}

public String cedulaVerifier(String cedula){
        String cedulaTrim = cedula.trim();
        int counter = 0;
        for(int i=0; i<cedulaTrim.length(); i++) {
                if(cedulaTrim.charAt(i) == ' ') {
                        counter++;
                }
        }
        if (counter > 0) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "The Cedula Cannot Have Spaces.",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
                return cedulaTrim = "";
        } if(cedulaTrim.length() != 13) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "The cedula must have 13 character including the \"-\"",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
                return cedulaTrim = "";
        } if (!Character.toString(cedulaTrim.charAt(3)).equals("-") ||
              !Character.toString(cedulaTrim.charAt(11)).equals("-") ) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "The Cedula Format is 000-0000000-0",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
                return cedulaTrim = "";
        } if (!cedulaVerifierInt(cedulaTrim)) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "The Cedula Format is 000-0000000-0,special characters not accepted",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
                return cedulaTrim = "";
        }else{
                return cedulaTrim;
        }
}

public boolean cedulaVerifierInt(String cedula){
        int cedulaChar = 0;
        int conteo = 0;

        for (int i=0; i < cedula.length(); i++ ) {
                if(i != 3 && i != 11) {
                        cedulaChar = Character.getNumericValue(cedula.charAt(i));
                        if(cedulaChar < 0 || cedulaChar > 9) {
                                conteo++;
                        }
                }
        }
        if (conteo > 0) {
                return false;
        }else {
                return true;
        }
}

public String idVerifier(String id){
        String idTrim = id.trim();
        int counter = 0;
        for(int i=0; i<idTrim.length(); i++) {
                if(idTrim.charAt(i) == ' ') {
                        counter++;
                }
        }
        if (counter > 0) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "The ID Cannot Have Spaces",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
                return idTrim = "";
        } if(!idTrim.startsWith("M")) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "ID Format Invalid",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
                return idTrim = "";
        }else{
                return idTrim;
        }
}
public void documentValidation(String name,String lastName,String phone,String memberID,String memberCedula,String typeofDoc,
                               String amountMoney,String typeWithdrawal,String typeLoan,String paymentMethod){
        if (memberID.isEmpty() || memberCedula.isEmpty() || name.isEmpty() || lastName.isEmpty()) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "You must search for the member first",
                        "Hermes Tracking System",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("res/INFORMATION_ICON.png")
                        );
        }else if(typeofDoc.equals("")) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Select the document type",
                        "Hermes Tracking System",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("res/INFORMATION_ICON.png")
                        );
        }else if(amountMoney.isEmpty()) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "You must introduce the amount of money.",
                        "Hermes Tracking System",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("res/INFORMATION_ICON.png")
                        );
        }else if (typeofDoc.equalsIgnoreCase("Withdrawal") && typeWithdrawal.isEmpty()) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "If you want to register a withdrawal need to provide the category.",
                        "Hermes Tracking System",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("res/INFORMATION_ICON.png")
                        );
        }else if (typeofDoc.equalsIgnoreCase("Loan") && (typeLoan.isEmpty() || paymentMethod.isEmpty())) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "If you want to register a loan need to provide the category and the payment method",
                        "Hermes Tracking System",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("res/INFORMATION_ICON.png")
                        );
        }else {
                try{
                        double amountMoneyConverted = Double.parseDouble(amountMoney);
                        if (amountMoneyConverted <= 0) {
                                JOptionPane.showMessageDialog(
                                        MainFrame.getInstance(),
                                        "The amount of money cannot be less or equal to 0.0$",
                                        "Hermes Tracking System",
                                        JOptionPane.WARNING_MESSAGE,
                                        new ImageIcon("res/WARNING_ICON.png")
                                        );
                        }else{
                                saveDocument(memberID,memberCedula,typeofDoc,amountMoneyConverted,typeWithdrawal,typeLoan,paymentMethod);
                        }
                }catch (NumberFormatException numberInvalid) {
                        JOptionPane.showMessageDialog(
                                MainFrame.getInstance(),
                                "The amount field doesn't allow special characters, just \".\" for decimal numbers",
                                "Hermes Tracking System",
                                JOptionPane.WARNING_MESSAGE,
                                new ImageIcon("res/WARNING_ICON.png")
                                );
                }
        }
}

private void saveDocument(String memberID,String memberCedula,String typeofDoc,double amountMoney,
                          String typeWithdrawal,String typeLoan,String paymentMethod){
        if (typeofDoc.equalsIgnoreCase("Withdrawal")) {
                Withdrawal newWithdrawal = new Withdrawal();
                newWithdrawal.getMember().setMemberID(memberID);
                newWithdrawal.getMember().setMemberCedula(memberCedula);
                newWithdrawal.setDocumentMoneyAmount(amountMoney);
                newWithdrawal.setdocumentType(typeofDoc);
                newWithdrawal.setWithdrawalType(typeWithdrawal);
                int documentID = DAL.registerWithdrawal(newWithdrawal);
                startState(documentID,typeofDoc);
                if (documentID > 0) {
                        JOptionPane.showMessageDialog(
                                MainFrame.getInstance(),
                                "The Document ID generated is: " + documentID,
                                "Hermes Tracking System",
                                JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon("res/INFORMATION_ICON.png")
                                );
                        newDoc.clean();
                }
        }else if (typeofDoc.equalsIgnoreCase("Loan")) {
                Loan newLoan = new Loan();
                newLoan.getMember().setMemberID(memberID);
                newLoan.getMember().setMemberCedula(memberCedula);
                newLoan.setDocumentMoneyAmount(amountMoney);
                newLoan.setdocumentType(typeofDoc);
                newLoan.setLoanPaymentOption(paymentMethod);
                newLoan.setLoanType(typeLoan);
                int documentID = DAL.registerLoan(newLoan);
                startState(documentID,typeofDoc);
                if (documentID > 0) {
                        JOptionPane.showMessageDialog(
                                MainFrame.getInstance(),
                                "The Document ID generated is: " + documentID,
                                "Hermes Tracking System",
                                JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon("res/INFORMATION_ICON.png")
                                );
                        newDoc.clean();
                }
        }else{
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Error. Option not found",
                        "Hermes Tracking System",
                        JOptionPane.ERROR_MESSAGE,
                        new ImageIcon("res/ERROR_ICON.png")
                        );
        }

}

private void startState(int documentID,String typeofDoc){
        State newState = new State();

        if (typeofDoc.equalsIgnoreCase("Withdrawal")) {
                Withdrawal changeWithdrawalState = new Withdrawal();
                changeWithdrawalState.setDocumentId(documentID);
                newState.setWithdrawal(changeWithdrawalState);
                newState.setStateStatus("S000");
                DAL.registerState(newState);
        }else if (typeofDoc.equalsIgnoreCase("Loan")) {
                Loan changeLoanState = new Loan();
                changeLoanState.setDocumentId(documentID);
                newState.setLoan(changeLoanState);
                newState.setStateStatus("S000");
                DAL.registerState(newState);
        }
}
}
