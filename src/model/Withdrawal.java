package model;

public class Withdrawal extends Document {

public Withdrawal(){
        withdrawalType = "";
        setMember(new Member());
}
public void setWithdrawalType(String withdrawalType){
        this.withdrawalType = withdrawalType;
}
public String getWithdrawalType(){
        return withdrawalType;
}

private String withdrawalType;
}
