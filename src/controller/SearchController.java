package controller;

import model.*;

import view.*;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.util.ArrayList;

public class SearchController {
SearchView searchView;
Member member;
ArrayList <State> documents;
MainController helper = new MainController();
HermesDAL DAL = HermesDAL.getInstance();
Object [][] array;

public SearchController(SearchView searchView){
        this.searchView = searchView;
}

public boolean searcher(String documentID,String memberID,String membercedula,String docType){
        boolean documentFound = false;
        if (documentID.isEmpty() && memberID.isEmpty() && membercedula.isEmpty() && docType.isEmpty()) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "You need to complete one of the fields",
                        "Hermes Tracking System",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("res/INFORMATION_ICON.png")
                        );
        }else if (!membercedula.isEmpty()) {
                membercedula = cedulaVerifier(membercedula);
                if (!membercedula.equals("")) {
                        member = new Member();
                        member.setMemberCedula(membercedula);
                        documents = DAL.searchDocumentsByMember(member);
                        if (!documents.isEmpty()) {
                                documentFound = true;
                                array = helper.documentConverter(documents);
                                searchView.fillTable(array);
                        }else {
                                JOptionPane.showMessageDialog(
                                        MainFrame.getInstance(),
                                        "0 documents found",
                                        "Hermes Tracking System",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        new ImageIcon("res/INFORMATION_ICON.png")
                                        );
                        }
                }
        }else if(!documentID.isEmpty()) {
                int documentIDn = documentIdVerifier(documentID);
                if(documentIDn != 0) {
                        documents = DAL.searchDocumentsByID(documentIDn);
                        if (!documents.isEmpty()) {
                                documentFound = true;
                                array = helper.documentConverter(documents);
                                searchView.fillTable(array);
                        }else {
                                JOptionPane.showMessageDialog(
                                        MainFrame.getInstance(),
                                        "0 documents found",
                                        "Hermes Tracking System",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        new ImageIcon("res/INFORMATION_ICON.png")
                                        );
                        }
                }
        }else if(!memberID.isEmpty()) {

                memberID = memberIdVerifier(memberID.toUpperCase());
                if (!memberID.equals("")) {
                        member = new Member();
                        member.setMemberID(memberID);
                        documents = DAL.searchDocumentsByMember(member);
                        if (!documents.isEmpty()) {
                                documentFound = true;
                                array = helper.documentConverter(documents);
                                searchView.fillTable(array);
                        }else {
                                JOptionPane.showMessageDialog(
                                        MainFrame.getInstance(),
                                        "0 documents found",
                                        "Hermes Tracking System",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        new ImageIcon("res/INFORMATION_ICON.png")
                                        );
                        }
                }

        }else if(!docType.isEmpty()) {
                documents = DAL.searchDocumentsByType(docType);
                if (!documents.isEmpty()) {
                        documentFound = true;
                        array = helper.documentConverter(documents);
                        searchView.fillTable(array);
                }else {
                        JOptionPane.showMessageDialog(
                                MainFrame.getInstance(),
                                "0 documents found",
                                "Hermes Tracking System",
                                JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon("res/INFORMATION_ICON.png")
                                );
                }
        }
        return documentFound;
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
                        "The Cedula Cannot Have Spaces",
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

private String memberIdVerifier(String id){
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
                        "The ID Cannot Have Spaces.",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
                return idTrim = "";
        } if(!idTrim.startsWith("M")) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Member ID Format Invalid.",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
                return idTrim = "";
        }else{
                return idTrim;
        }
}

private int documentIdVerifier(String documentID){
        try{
                int docID = Integer.parseInt(documentID);
                return docID;
        }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Document ID Format Invalid.",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
        }
        return 0;
}
}
