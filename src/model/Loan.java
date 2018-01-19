package model;

public class Loan extends Document {
public Loan(){
        loanPaymentOption = "";
        loanType = "";
        setMember(new Member());
}

public void setLoanPaymentOption(String loanPaymentOption){
        this.loanPaymentOption = loanPaymentOption;
}
public String getLoanPaymentOption(){
        return loanPaymentOption;
}
public void setLoanType(String loanType){
        this.loanType = loanType;
}
public String getLoanType(){
        return loanType;
}

private String loanPaymentOption;
private String loanType;
}
