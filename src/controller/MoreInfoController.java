package controller;

import view.*;

import model.*;

import java.util.ArrayList;

public class MoreInfoController {
private HermesDAL DAL = HermesDAL.getInstance();
private ArrayList<State> document;
private String documentType = "";
private int documentId = 0;
private double documentMoneyAmount = 0;
private String memberPhoneNumber = "";
private String memberLastName = "";
private String memberName = "";
private String memberCedula = "";
private String memberID = "";
private String stateDate = "";
private String stateTime = "";
private String stateStatus = "";
private String loanPaymentOption = "";
private String loanType = "";
private String withdrawalType = "";


public void seachInfo(int docID){
        document = DAL.searchDocumentsByID(docID);
        if (document.get(0).getLoan() == null) {
                documentId = document.get(0).getWithdrawal().getDocumentId();
                documentMoneyAmount = document.get(0).getWithdrawal().getDocumentMoneyAmount();
                documentType = document.get(0).getWithdrawal().getdocumentType();
                memberID = document.get(0).getWithdrawal().getMember().getMemberID();
                memberName = document.get(0).getWithdrawal().getMember().getMemberName();
                memberLastName = document.get(0).getWithdrawal().getMember().getMemberLastName();
                stateStatus = document.get(0).getStateStatus();
                stateDate = document.get(0).getStateDate();
                stateTime = document.get(0).getStateTime();
                memberPhoneNumber = document.get(0).getWithdrawal().getMember().getMemberPhoneNumber();
                memberCedula = document.get(0).getWithdrawal().getMember().getMemberCedula();
                withdrawalType = document.get(0).getWithdrawal().getWithdrawalType();
        }else if (document.get(0).getWithdrawal() == null) {
                documentId = document.get(0).getLoan().getDocumentId();
                documentMoneyAmount = document.get(0).getLoan().getDocumentMoneyAmount();
                documentType = document.get(0).getLoan().getdocumentType();
                memberID = document.get(0).getLoan().getMember().getMemberID();
                memberName = document.get(0).getLoan().getMember().getMemberName();
                memberLastName = document.get(0).getLoan().getMember().getMemberLastName();
                stateStatus = document.get(0).getStateStatus();
                stateDate = document.get(0).getStateDate();
                stateTime = document.get(0).getStateTime();
                memberPhoneNumber = document.get(0).getLoan().getMember().getMemberPhoneNumber();
                memberCedula = document.get(0).getLoan().getMember().getMemberCedula();
                loanPaymentOption = document.get(0).getLoan().getLoanPaymentOption();
                loanType = document.get(0).getLoan().getLoanType();
        }
        showInfo();
}

public void showInfo(){
        if (documentType.equalsIgnoreCase("Withdrawal")) {
                WithdrawalView withdrawalView = MainFrame.getInstance().showWithdrawalView();
                withdrawalView.setDocumentInfo(documentId,documentMoneyAmount,documentType,memberID,
                                               memberName,memberLastName,stateStatus,stateDate,stateTime,
                                               memberPhoneNumber,memberCedula,withdrawalType);
        }else if (documentType.equalsIgnoreCase("Loan")) {
                LoanView loanView = MainFrame.getInstance().showLoanView();
                loanView.setDocumentInfo(documentId,documentMoneyAmount,documentType,memberID,
                                         memberName,memberLastName,stateStatus,stateDate,stateTime,
                                         memberPhoneNumber,memberCedula,loanPaymentOption,loanType);
        }
}
}
