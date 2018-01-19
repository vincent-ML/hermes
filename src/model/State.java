package model;

import java.sql.Date;
import java.sql.Time;

public class State {
public State(){
        employee = new Employee();
}
public void setStateDate(String date){
        this.date = date;
}
public String getStateDate(){
        return date;
}
public void setStateTime(String time){
        this.time = time;
}
public String getStateTime(){
        return time;
}

public void setStateStatus(String stateStatus){
        this.stateStatus = stateStatus;
}
public String getStateStatus(){
        return stateStatus;
}
public void setEmployee(Employee employee){
        this.employee = employee;
}
public Employee getEmployee(){
        return employee;
}
public void setLoan(Loan loan){
        this.loan = loan;
}
public Loan getLoan(){
        return loan;
}
public void setWithdrawal(Withdrawal withdrawal){
        this.withdrawal = withdrawal;
}
public Withdrawal getWithdrawal(){
        return withdrawal;
}

private Withdrawal withdrawal;
private Loan loan;
private Employee employee;
private String date;
private String time;
private String stateStatus;
}
