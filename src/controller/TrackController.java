package controller;

import view.*;

import model.*;

import java.util.ArrayList;

public class TrackController {
HermesDAL dal = HermesDAL.getInstance();

public void trackDocument(int docID){
        TrackView trackView = MainFrame.getInstance().showTrackDocumentView();
        ArrayList<State> states = dal.trackDocument(docID);
        Object [][] rows = convertArray(states);
        int documentID = 0;
        String documentType = "";
        String memberID = "";
        String memberFullName = "";
        if (states.get(0).getLoan() == null) {
                documentID = states.get(0).getWithdrawal().getDocumentId();
                documentType = states.get(0).getWithdrawal().getdocumentType();
                memberID = states.get(0).getWithdrawal().getMember().getMemberID();
                memberFullName = states.get(0).getWithdrawal().getMember().getMemberLastName()+", "+states.get(0).getWithdrawal().getMember().getMemberName();
        }else if (states.get(0).getWithdrawal() == null) {
                documentID = states.get(0).getLoan().getDocumentId();
                documentType = states.get(0).getLoan().getdocumentType();
                memberID = states.get(0).getLoan().getMember().getMemberID();
                memberFullName = states.get(0).getLoan().getMember().getMemberLastName()+", "+states.get(0).getLoan().getMember().getMemberName();
        }
        trackView.setInfo(documentID,documentType,memberID,memberFullName);
        trackView.setTable(rows);
}

public Object[][] convertArray(ArrayList<State> states){
        Object [][] row = new Object[states.size()][4];
        for(int i = 0; i < states.size(); i++)
        {
                row[i][0] = states.get(i).getStateDate();
                row[i][1] = states.get(i).getStateTime();
                row[i][2] = states.get(i).getStateStatus();
                row[i][3] = states.get(i).getEmployee().getEmployeeDepartment()+", "+ states.get(i).getEmployee().getEmployeeRole()+", "+states.get(i).getEmployee().getEmployeeName()+" "+states.get(i).getEmployee().getEmployeeLastName();
        }
        return row;
}
}
