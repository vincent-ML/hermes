package model;

public class Member {
public Member(){
        memberPhoneNumber = "";
        memberLastName = "";
        memberName = "";
        memberCedula = "";
        memberID = "";
}
public void setMemberID(String memberID){
        this.memberID = memberID;
}
public String  getMemberID(){
        return memberID;
}
public void setMemberCedula(String memberCedula){
        this.memberCedula = memberCedula;
}
public String  getMemberCedula(){
        return memberCedula;
}
public void setMemberName(String memberName){
        this.memberName = memberName;
}
public String  getMemberName(){
        return memberName;
}
public void setMemberLastName(String memberLastName){
        this.memberLastName = memberLastName;
}
public String  getMemberLastName(){
        return memberLastName;
}
public void setMemberPhoneNumber(String memberPhoneNumber){
        this.memberPhoneNumber = memberPhoneNumber;
}
public String  getMemberPhoneNumber(){
        return memberPhoneNumber;
}
private String memberPhoneNumber;
private String memberLastName;
private String memberName;
private String memberCedula;
private String memberID;
}
