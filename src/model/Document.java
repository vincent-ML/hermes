package model;

public abstract class Document {

public void setDocumentId(int documentId){
        this.documentId = documentId;
}
public int getDocumentId(){
        return documentId;
}
public void setDocumentMoneyAmount(double documentMoneyAmount){
        this.documentMoneyAmount = documentMoneyAmount;
}
public double getDocumentMoneyAmount(){
        return documentMoneyAmount;
}
public void setMember(Member member){
        this.member = member;
}
public Member getMember(){
        return member;
}
public String getdocumentType(){
        return documentType;
}
public void setdocumentType(String documentType){
        this.documentType = documentType;
}

private String documentType;
private int documentId;
private double documentMoneyAmount;
private Member member;
}
