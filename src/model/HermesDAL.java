package model;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.HashMap;
import java.util.ArrayList;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperPrint;

import model.*;

public class HermesDAL {
private static Connection hermesConnection = null;
private static HermesDAL instance = null;
private Statement stm = null;
private PreparedStatement pstm = null;
private CallableStatement cstm = null;
private ResultSet rs = null;
private String userRole = "";
private long sessionID = 0;

private HermesDAL(){

}
public static HermesDAL getInstance(){
        if(instance == null) {
                instance = new HermesDAL();
        }
        return instance;
}

static {
        try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
        }catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error 404: Connection Driver Not Found\n"+
                        "Please contact the administrator",
                        "Hermes Tracking System",
                        JOptionPane.ERROR_MESSAGE,
                        new ImageIcon("res/ERROR_ICON.png")
                        );
                System.exit(1);
        }
}

public String authenticateUser(String user, String password){
        String connectionCode = "00000";
        try{
                hermesConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/hermesDB;bootPassword=hermesadmin123;ssl=basic;",user, password);
        }catch (java.sql.SQLException error) {
                connectionCode = error.getSQLState();
        }
        return connectionCode;
}

public String getRoleDB(String user){
        String userRole = "";
        try{
                pstm = hermesConnection.prepareStatement("SELECT roleid FROM sys.sysroles WHERE grantee = ?");
                pstm.setString(1, user.toUpperCase());
                rs = pstm.executeQuery();

                if (rs.next()) {
                        userRole = rs.getString("roleid");
                }

                rs.close();
                pstm.close();
        }catch(Exception error) {
                error.printStackTrace();
        }
        return userRole;
}
public String getRole(){
        return userRole;
}
public void setRoleSchemaDB(String userRole){
        this.userRole = userRole;
        try{
                pstm = hermesConnection.prepareStatement("SET ROLE ?");
                pstm.setString(1,userRole);
                pstm.execute();
                pstm.close();

                stm = hermesConnection.createStatement();
                stm.execute("SET SCHEMA HERMESDBA");
                stm.close();
        }catch(SQLException error) {
                error.printStackTrace();
        }
}

public void registerLogin(){
        String currentUser = "";
        try {
                stm = hermesConnection.createStatement();
                rs = stm.executeQuery("VALUES CURRENT_USER");
                if (rs.next()) {
                        currentUser = rs.getString(1);
                }
                cstm = hermesConnection.prepareCall("{CALL registerLogin(?)}");
                cstm.setString(1,currentUser);
                cstm.execute();

                cstm.close();
                rs.close();
                stm.close();
        }catch (SQLException e) {
                e.printStackTrace();
        }
}

public void disconnectDB() {
        String currentUser = "";
        try {
                if (hermesConnection != null && !hermesConnection.isClosed()) {
                        stm = hermesConnection.createStatement();
                        rs = stm.executeQuery("VALUES CURRENT_USER");
                        if (rs.next()) {
                                currentUser = rs.getString(1);
                        }
                        cstm = hermesConnection.prepareCall("{CALL registerLogout(?)}");
                        cstm.setString(1,currentUser);
                        cstm.execute();

                        cstm.close();
                        rs.close();
                        stm.close();

                        hermesConnection.close();
                }
        }catch (SQLException e) {
                e.printStackTrace();
        }
}

public Member getMember(String ID) {
        String queryCedula = "SELECT * FROM MEMBER WHERE membercedula = ?";
        String queryID = "SELECT * FROM MEMBER WHERE memberid = ?";
        Member aMember = null;
        try {
                if (ID.startsWith("M")) {
                        pstm = hermesConnection.prepareStatement(queryID);
                        pstm.setString(1,ID);
                        rs = pstm.executeQuery();
                        if(rs.next()) {
                                aMember = new Member();
                                aMember.setMemberID(rs.getString("memberId"));
                                aMember.setMemberCedula(rs.getString("membercedula"));
                                aMember.setMemberName(rs.getString("memberName"));
                                aMember.setMemberLastName(rs.getString("memberLastName"));
                                aMember.setMemberPhoneNumber(rs.getString("memberPhoneNumber"));
                        }
                        rs.close();
                        pstm.close();
                }else{
                        pstm = hermesConnection.prepareStatement(queryCedula);
                        pstm.setString(1,ID);
                        rs = pstm.executeQuery();
                        if(rs.next()) {
                                aMember = new Member();
                                aMember.setMemberID(rs.getString("memberId"));
                                aMember.setMemberCedula(rs.getString("membercedula"));
                                aMember.setMemberName(rs.getString("memberName"));
                                aMember.setMemberLastName(rs.getString("memberLastName"));
                                aMember.setMemberPhoneNumber(rs.getString("memberPhoneNumber"));
                        }
                        rs.close();
                        pstm.close();
                }
        }catch (SQLException e) {
                e.printStackTrace();
        }catch (Exception e) {
                e.printStackTrace();
        }
        return aMember;
}

public int registerWithdrawal(Withdrawal newWithdrawal){
        String registerDocument = "INSERT INTO document (documentMoneyAmount,memberID,documentType) "+
                                  "VALUES (?,?,?)";
        String registerWithdrawal = "INSERT INTO withdrawal (withdrawalType,documentID) "+
                                    "VALUES (?,?)";
        try {
                pstm = hermesConnection.prepareStatement(registerDocument,Statement.RETURN_GENERATED_KEYS);
                pstm.setDouble(1,newWithdrawal.getDocumentMoneyAmount());
                pstm.setString(2,newWithdrawal.getMember().getMemberID());
                pstm.setString(3,newWithdrawal.getdocumentType());
                pstm.executeUpdate();
                rs = pstm.getGeneratedKeys();

                if (rs.next()) {
                        newWithdrawal.setDocumentId(rs.getInt(1));
                }
                rs.close();
                pstm.close();

                pstm = hermesConnection.prepareStatement(registerWithdrawal);
                pstm.setString(1,newWithdrawal.getWithdrawalType());
                pstm.setInt(2,newWithdrawal.getDocumentId());
                pstm.executeUpdate();
                pstm.close();

        }catch (SQLException e) {
                e.printStackTrace();
        }catch (Exception e) {
                e.printStackTrace();
        }
        return newWithdrawal.getDocumentId();
}

public int registerLoan(Loan newLoan){
        String registerDocument = "INSERT INTO document (documentMoneyAmount,memberID,documentType) "+
                                  "VALUES (?,?,?)";
        String registerLoan = "INSERT INTO Loan (loanType,loanPaymentOption,documentID) "+
                              "VALUES (?,?,?)";
        try {
                pstm = hermesConnection.prepareStatement(registerDocument,Statement.RETURN_GENERATED_KEYS);
                pstm.setDouble(1,newLoan.getDocumentMoneyAmount());
                pstm.setString(2,newLoan.getMember().getMemberID());
                pstm.setString(3,newLoan.getdocumentType());
                pstm.executeUpdate();
                rs = pstm.getGeneratedKeys();

                if (rs.next()) {
                        newLoan.setDocumentId(rs.getInt(1));
                }

                rs.close();
                pstm.close();

                pstm = hermesConnection.prepareStatement(registerLoan);
                pstm.setString(1,newLoan.getLoanType());
                pstm.setString(2,newLoan.getLoanPaymentOption());
                pstm.setInt(3,newLoan.getDocumentId());
                pstm.executeUpdate();
                pstm.close();
        }catch (SQLException e) {
                e.printStackTrace();
        }catch (Exception e) {
                e.printStackTrace();
        }
        return newLoan.getDocumentId();
}

public int registerState(State aState){
        String registerState = "INSERT INTO state (documentID,statusID,employeeID,stateDateTime) "+
                               "VALUES (?,?,(SELECT employeeID FROM employee "+
                               "WHERE employeeRoleID = CURRENT_USER),CURRENT_TIMESTAMP)";
        int inserted = 0;
        try {
                if(aState.getLoan() == null) {
                        pstm = hermesConnection.prepareStatement(registerState);
                        pstm.setInt(1,aState.getWithdrawal().getDocumentId());
                        pstm.setString(2,aState.getStateStatus());
                        inserted = pstm.executeUpdate();
                        pstm.close();
                }else if(aState.getWithdrawal() == null) {
                        pstm = hermesConnection.prepareStatement(registerState);
                        pstm.setInt(1,aState.getLoan().getDocumentId());
                        pstm.setString(2,aState.getStateStatus());
                        inserted = pstm.executeUpdate();
                        pstm.close();
                }
        }catch (SQLException e) {
                e.printStackTrace();
        }catch (Exception e) {
                e.printStackTrace();
        }
        return inserted;
}
public ArrayList<State> loadTable(){
        State state;
        Loan loan;
        Withdrawal withdrawal;
        ArrayList <State> documents = new ArrayList <State>();
        String oCredito = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                          "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                          "FROM ("+
                          "SELECT MAX(stateID) AS stateIDS "+
                          "FROM state "+
                          "GROUP BY documentID"+
                          ") AS s "+
                          "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                          "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                          "JOIN document AS d ON d.documentID = s1.documentID "+
                          "JOIN member AS m ON d.memberID = m.memberID "+
                          "WHERE s1.statusID = 'S000' "+
                          "OR s1.statusID = 'S007' "+
                          "OR s1.statusID = 'S008'";
        String eCredito = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                          "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                          "FROM ("+
                          "SELECT MAX(stateID) AS stateIDS "+
                          "FROM state "+
                          "GROUP BY documentID"+
                          ") AS s "+
                          "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                          "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                          "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                          "JOIN document AS d ON d.documentID = s1.documentID "+
                          "JOIN member AS m ON d.memberID = m.memberID "+
                          "WHERE (s1.statusID = 'S003' AND e.roleID = 'R001') "+
                          "OR (s1.statusID = 'S004' AND e.roleID = 'R002') "+
                          "OR (s1.statusID = 'S001')";
        String gGeneral = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                          "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                          "FROM ("+
                          "SELECT MAX(stateID) AS stateIDS "+
                          "FROM state "+
                          "GROUP BY documentID"+
                          ") AS s "+
                          "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                          "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                          "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                          "JOIN document AS d ON d.documentID = s1.documentID "+
                          "JOIN withdrawal AS w ON d.documentID = w.documentID "+
                          "JOIN member AS m ON d.memberID = m.memberID "+
                          "WHERE (s1.statusID = 'S003' AND e.roleID = 'R002') "+
                          "OR (s1.statusID = 'S004' AND e.roleID = 'R005') "+
                          "OR (s1.statusID = 'S005' AND e.roleID = 'R005')";
        String gFinanciero = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                             "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                             "FROM ("+
                             "SELECT MAX(stateID) AS stateIDS "+
                             "FROM state "+
                             "GROUP BY documentID"+
                             ") AS s "+
                             "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                             "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                             "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                             "JOIN document AS d ON d.documentID = s1.documentID "+
                             "JOIN loan AS l ON d.documentID = l.documentID "+
                             "JOIN member AS m ON d.memberID = m.memberID "+
                             "WHERE (s1.statusID = 'S003' AND e.roleID = 'R002') "+
                             "OR (s1.statusID = 'S004' AND e.roleID = 'R004') "+
                             "OR (s1.statusID = 'S005' AND e.roleID = 'R004')";
        String mInterno = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                          "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                          "FROM ("+
                          "SELECT MAX(stateID) AS stateIDS "+
                          "FROM state "+
                          "GROUP BY documentID"+
                          ") AS s "+
                          "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                          "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                          "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                          "JOIN document AS d ON d.documentID = s1.documentID "+
                          "JOIN member AS m ON d.memberID = m.memberID "+
                          "WHERE (s1.statusID <> 'S002' AND s1.statusID <> 'S009')";
        try {
                stm = hermesConnection.createStatement();
                if(userRole.equals("OFICIALCREDITO")) {
                        rs = stm.executeQuery(oCredito);
                }else if(userRole.equals("ENCARGADOCREDITO")) {
                        rs = stm.executeQuery(eCredito);
                }else if(userRole.equals("GERENTEFINANCIERO")) {
                        rs = stm.executeQuery(gFinanciero);
                }else if(userRole.equals("GERENTEGENERAL")) {
                        rs = stm.executeQuery(gGeneral);
                }else if(userRole.equals("MENSAJEROINTERNO")) {
                        rs = stm.executeQuery(mInterno);
                }
                while (rs.next()) {
                        state = new State();

                        if(rs.getString("documentType").equalsIgnoreCase("Withdrawal")) {
                                withdrawal = new Withdrawal();
                                withdrawal.setDocumentId(rs.getInt("documentID"));
                                withdrawal.setDocumentMoneyAmount(rs.getDouble("documentMoneyAmount"));
                                withdrawal.setdocumentType(rs.getString("documentType"));
                                withdrawal.getMember().setMemberID(rs.getString("memberID"));
                                withdrawal.getMember().setMemberCedula(rs.getString("memberCedula"));
                                withdrawal.getMember().setMemberName(rs.getString("memberName"));
                                withdrawal.getMember().setMemberLastName(rs.getString("memberLastname"));
                                state.setWithdrawal(withdrawal);

                        }else if(rs.getString("documentType").equalsIgnoreCase("Loan")) {
                                loan = new Loan();
                                loan.setDocumentId(rs.getInt("documentID"));
                                loan.setDocumentMoneyAmount(rs.getDouble("documentMoneyAmount"));
                                loan.setdocumentType(rs.getString("documentType"));
                                loan.getMember().setMemberID(rs.getString("memberID"));
                                loan.getMember().setMemberCedula(rs.getString("memberCedula"));
                                loan.getMember().setMemberName(rs.getString("memberName"));
                                loan.getMember().setMemberLastName(rs.getString("memberLastname"));
                                state.setLoan(loan);
                        }
                        state.setStateTime(String.format("%1$Tr", rs.getTimestamp("stateDatetime")));
                        state.setStateDate(String.format("%td/%1$tm/%1$tY", rs.getTimestamp("stateDatetime")));
                        state.setStateStatus(rs.getString("status"));

                        documents.add(state);
                }
                rs.close();
                stm.close();

                return documents;

        }catch (SQLException e) {
                e.printStackTrace();
        }catch (Exception e) {
                e.printStackTrace();
        }
        return documents;
}
public ArrayList<State> searchDocumentsByMember(Member member){
        State state;
        Loan loan;
        Withdrawal withdrawal;
        ArrayList <State> documents = new ArrayList <State>();
        String oCreditoID = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                            "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                            "FROM ("+
                            "SELECT MAX(stateID) AS stateIDS "+
                            "FROM state "+
                            "GROUP BY documentID"+
                            ") AS s "+
                            "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                            "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                            "JOIN document AS d ON d.documentID = s1.documentID "+
                            "JOIN member AS m ON d.memberID = m.memberID "+
                            "WHERE m.memberID = ? AND (s1.statusID = 'S000' OR s1.statusID = 'S007' OR s1.statusID = 'S008')";
        String oCreditoCedula = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                                "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                                "FROM ("+
                                "SELECT MAX(stateID) AS stateIDS "+
                                "FROM state "+
                                "GROUP BY documentID"+
                                ") AS s "+
                                "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                                "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                                "JOIN document AS d ON d.documentID = s1.documentID "+
                                "JOIN member AS m ON d.memberID = m.memberID "+
                                "WHERE m.memberCedula = ? "+
                                "AND (s1.statusID = 'S000' OR s1.statusID = 'S007' OR s1.statusID = 'S008')";
        String eCreditoID = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                            "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                            "FROM ("+
                            "SELECT MAX(stateID) AS stateIDS "+
                            "FROM state "+
                            "GROUP BY documentID"+
                            ") AS s "+
                            "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                            "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                            "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                            "JOIN document AS d ON d.documentID = s1.documentID "+
                            "JOIN member AS m ON d.memberID = m.memberID "+
                            "WHERE m.memberID = ? AND ((s1.statusID = 'S003' AND e.roleID = 'R001') "+
                            "OR (s1.statusID = 'S004' AND e.roleID = 'R002') OR (s1.statusID = 'S001'))";
        String eCreditoCedula = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                                "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                                "FROM ("+
                                "SELECT MAX(stateID) AS stateIDS "+
                                "FROM state "+
                                "GROUP BY documentID"+
                                ") AS s "+
                                "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                                "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                                "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                                "JOIN document AS d ON d.documentID = s1.documentID "+
                                "JOIN member AS m ON d.memberID = m.memberID "+
                                "WHERE m.memberCedula = ? AND ((s1.statusID = 'S003' AND e.roleID = 'R001') "+
                                "OR (s1.statusID = 'S004' AND e.roleID = 'R002') OR (s1.statusID = 'S001'))";
        String mInternoID = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                            "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                            "FROM ("+
                            "SELECT MAX(stateID) AS stateIDS "+
                            "FROM state "+
                            "GROUP BY documentID"+
                            ") AS s "+
                            "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                            "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                            "JOIN document AS d ON d.documentID = s1.documentID "+
                            "JOIN member AS m ON d.memberID = m.memberID "+
                            "WHERE m.memberID = ? "+
                            "AND (s1.statusID <> 'S002' AND s1.statusID <> 'S009')";
        String mInternoCedula = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                                "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                                "FROM ("+
                                "SELECT MAX(stateID) AS stateIDS "+
                                "FROM state "+
                                "GROUP BY documentID"+
                                ") AS s "+
                                "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                                "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                                "JOIN document AS d ON d.documentID = s1.documentID "+
                                "JOIN member AS m ON d.memberID = m.memberID "+
                                "WHERE m.memberCedula = ? "+
                                "AND (s1.statusID <> 'S002' AND s1.statusID <> 'S009')";
        String gGeneralID = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                            "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                            "FROM ("+
                            "SELECT MAX(stateID) AS stateIDS "+
                            "FROM state "+
                            "GROUP BY documentID"+
                            ") AS s "+
                            "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                            "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                            "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                            "JOIN document AS d ON d.documentID = s1.documentID "+
                            "JOIN withdrawal AS w ON w.documentID = d.documentID "+
                            "JOIN member AS m ON d.memberID = m.memberID "+
                            "WHERE m.memberID = ? "+
                            "AND ((s1.statusID = 'S003' AND e.roleID = 'R002') "+
                            "OR (s1.statusID = 'S004' AND e.roleID = 'R005') "+
                            "OR (s1.statusID = 'S005' AND e.roleID = 'R005'))";
        String gGeneralCedula = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                                "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                                "FROM ("+
                                "SELECT MAX(stateID) AS stateIDS "+
                                "FROM state "+
                                "GROUP BY documentID"+
                                ") AS s "+
                                "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                                "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                                "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                                "JOIN document AS d ON d.documentID = s1.documentID "+
                                "JOIN withdrawal AS w ON w.documentID = d.documentID "+
                                "JOIN member AS m ON d.memberID = m.memberID "+
                                "WHERE m.memberCedula = ? "+
                                "AND ((s1.statusID = 'S003' AND e.roleID = 'R002') "+
                                "OR (s1.statusID = 'S004' AND e.roleID = 'R005') "+
                                "OR (s1.statusID = 'S005' AND e.roleID = 'R005'))";
        String gFinancieroID = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                               "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                               "FROM ("+
                               "SELECT MAX(stateID) AS stateIDS "+
                               "FROM state "+
                               "GROUP BY documentID"+
                               ") AS s "+
                               "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                               "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                               "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                               "JOIN document AS d ON d.documentID = s1.documentID "+
                               "JOIN loan AS l ON l.documentID = d.documentID "+
                               "JOIN member AS m ON d.memberID = m.memberID "+
                               "WHERE m.memberID = ? "+
                               "AND ((s1.statusID = 'S003' AND e.roleID = 'R002') "+
                               "OR (s1.statusID = 'S004' AND e.roleID = 'R004') "+
                               "OR (s1.statusID = 'S005' AND e.roleID = 'R004'))";
        String gFinancieroCedula = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                                   "d.documentType,m.memberID,m.memberCedula,m.memberName,m.memberLastname "+
                                   "FROM ("+
                                   "SELECT MAX(stateID) AS stateIDS "+
                                   "FROM state "+
                                   "GROUP BY documentID"+
                                   ") AS s "+
                                   "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                                   "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                                   "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                                   "JOIN document AS d ON d.documentID = s1.documentID "+
                                   "JOIN loan AS l ON l.documentID = d.documentID "+
                                   "JOIN member AS m ON d.memberID = m.memberID "+
                                   "WHERE m.memberCedula = ? "+
                                   "AND ((s1.statusID = 'S003' AND e.roleID = 'R002') "+
                                   "OR (s1.statusID = 'S004' AND e.roleID = 'R004') "+
                                   "OR (s1.statusID = 'S005' AND e.roleID = 'R004'))";
        try {
                if(userRole.equals("OFICIALCREDITO") && !member.getMemberID().isEmpty()) {
                        pstm = hermesConnection.prepareStatement(oCreditoID);
                        pstm.setString(1,member.getMemberID());
                }else if(userRole.equals("OFICIALCREDITO") && !member.getMemberCedula().isEmpty()) {
                        pstm = hermesConnection.prepareStatement(oCreditoCedula);
                        pstm.setString(1,member.getMemberCedula());
                }else if(userRole.equals("ENCARGADOCREDITO") && !member.getMemberID().isEmpty()) {
                        pstm = hermesConnection.prepareStatement(eCreditoID);
                        pstm.setString(1,member.getMemberID());
                }else if(userRole.equals("ENCARGADOCREDITO") && !member.getMemberCedula().isEmpty()) {
                        pstm = hermesConnection.prepareStatement(eCreditoCedula);
                        pstm.setString(1,member.getMemberCedula());
                }else if(userRole.equals("GERENTEFINANCIERO") && !member.getMemberID().isEmpty()) {
                        pstm = hermesConnection.prepareStatement(gFinancieroID);
                        pstm.setString(1,member.getMemberID());
                }else if(userRole.equals("GERENTEFINANCIERO") && !member.getMemberCedula().isEmpty()) {
                        pstm = hermesConnection.prepareStatement(gFinancieroCedula);
                        pstm.setString(1,member.getMemberCedula());
                }else if(userRole.equals("GERENTEGENERAL") && !member.getMemberID().isEmpty()) {
                        pstm = hermesConnection.prepareStatement(gGeneralID);
                        pstm.setString(1,member.getMemberID());
                }else if(userRole.equals("GERENTEGENERAL") && !member.getMemberCedula().isEmpty()) {
                        pstm = hermesConnection.prepareStatement(gGeneralCedula);
                        pstm.setString(1,member.getMemberCedula());
                }else if(userRole.equals("MENSAJEROINTERNO") && !member.getMemberID().isEmpty()) {
                        pstm = hermesConnection.prepareStatement(mInternoID);
                        pstm.setString(1,member.getMemberID());
                }else if(userRole.equals("MENSAJEROINTERNO") && !member.getMemberCedula().isEmpty()) {
                        pstm = hermesConnection.prepareStatement(mInternoCedula);
                        pstm.setString(1,member.getMemberCedula());
                }
                rs = pstm.executeQuery();
                while (rs.next()) {
                        state = new State();

                        if(rs.getString("documentType").equalsIgnoreCase("Withdrawal")) {
                                withdrawal = new Withdrawal();
                                withdrawal.setDocumentId(rs.getInt("documentID"));
                                withdrawal.setDocumentMoneyAmount(rs.getDouble("documentMoneyAmount"));
                                withdrawal.setdocumentType(rs.getString("documentType"));
                                withdrawal.getMember().setMemberID(rs.getString("memberID"));
                                withdrawal.getMember().setMemberCedula(rs.getString("memberCedula"));
                                withdrawal.getMember().setMemberName(rs.getString("memberName"));
                                withdrawal.getMember().setMemberLastName(rs.getString("memberLastname"));
                                state.setWithdrawal(withdrawal);

                        }else if(rs.getString("documentType").equalsIgnoreCase("Loan")) {
                                loan = new Loan();
                                loan.setDocumentId(rs.getInt("documentID"));
                                loan.setDocumentMoneyAmount(rs.getDouble("documentMoneyAmount"));
                                loan.setdocumentType(rs.getString("documentType"));
                                loan.getMember().setMemberID(rs.getString("memberID"));
                                loan.getMember().setMemberCedula(rs.getString("memberCedula"));
                                loan.getMember().setMemberName(rs.getString("memberName"));
                                loan.getMember().setMemberLastName(rs.getString("memberLastname"));
                                state.setLoan(loan);
                        }
                        state.setStateTime(String.format("%1$Tr", rs.getTimestamp("stateDatetime")));
                        state.setStateDate(String.format("%td/%1$tm/%1$tY", rs.getTimestamp("stateDatetime")));
                        state.setStateStatus(rs.getString("status"));

                        documents.add(state);
                }
                rs.close();
                stm.close();
                return documents;
        }catch (SQLException e) {
                e.printStackTrace();
        }catch (Exception e) {
                e.printStackTrace();
        }
        return documents;
}

public ArrayList<State> searchDocumentsByID(int docID){
        State state;
        Loan loan;
        Withdrawal withdrawal;
        ArrayList <State> documents = new ArrayList <State>();
        String eCredito = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                          "d.documentType,l.loanType,l.loanPaymentOption,w.withdrawalType,"+
                          "m.memberID,m.memberCedula,m.memberName,m.memberLastname,m.memberPhoneNumber "+
                          "FROM ("+
                          "SELECT MAX(stateID) AS stateIDS "+
                          "FROM state "+
                          "GROUP BY documentID"+
                          ") AS s "+
                          "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                          "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                          "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                          "JOIN document AS d ON d.documentID = s1.documentID "+
                          "LEFT JOIN loan AS l ON d.documentID = l.documentID "+
                          "LEFT JOIN withdrawal AS w ON d.documentID = w.documentID "+
                          "JOIN member AS m ON d.memberID = m.memberID "+
                          "WHERE d.documentID =? AND ((s1.statusID = 'S003' AND e.roleID = 'R001') OR (s1.statusID = 'S004' AND e.roleID = 'R002') OR (s1.statusID = 'S001'))";
        String oCredito = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                          "d.documentType,l.loanType,l.loanPaymentOption,w.withdrawalType,m.memberID,"+
                          "m.memberCedula,m.memberName,m.memberLastname,m.memberPhoneNumber "+
                          "FROM ("+
                          "SELECT MAX(stateID) AS stateIDS "+
                          "FROM state "+
                          "GROUP BY documentID"+
                          ") AS s "+
                          "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                          "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                          "JOIN document AS d ON d.documentID = s1.documentID "+
                          "LEFT JOIN loan AS l ON d.documentID = l.documentID "+
                          "LEFT JOIN withdrawal AS w ON d.documentID = w.documentID "+
                          "JOIN member AS m ON d.memberID = m.memberID "+
                          "WHERE d.documentID =? AND (s1.statusID = 'S000' "+
                          "OR s1.statusID = 'S007' OR s1.statusID = 'S008')";
        String mInterno = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                          "d.documentType,l.loanType,l.loanPaymentOption,w.withdrawalType,m.memberID,"+
                          "m.memberCedula,m.memberName,m.memberLastname,m.memberPhoneNumber "+
                          "FROM ("+
                          "SELECT MAX(stateID) AS stateIDS "+
                          "FROM state "+
                          "GROUP BY documentID"+
                          ") AS s "+
                          "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                          "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                          "JOIN document AS d ON d.documentID = s1.documentID "+
                          "LEFT JOIN loan AS l ON d.documentID = l.documentID "+
                          "LEFT JOIN withdrawal AS w ON d.documentID = w.documentID "+
                          "JOIN member AS m ON d.memberID = m.memberID "+
                          "WHERE d.documentID =? AND (s1.statusID <> 'S002' AND s1.statusID <> 'S009')";
        String gGeneral = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                          "d.documentType,l.loanType,l.loanPaymentOption,w.withdrawalType,m.memberID,"+
                          "m.memberCedula,m.memberName,m.memberLastname,m.memberPhoneNumber "+
                          "FROM ("+
                          "SELECT MAX(stateID) AS stateIDS "+
                          "FROM state "+
                          "GROUP BY documentID"+
                          ") AS s "+
                          "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                          "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                          "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                          "JOIN document AS d ON d.documentID = s1.documentID "+
                          "LEFT JOIN loan AS l ON d.documentID = l.documentID "+
                          "LEFT JOIN withdrawal AS w ON d.documentID = w.documentID "+
                          "JOIN member AS m ON d.memberID = m.memberID "+
                          "WHERE d.documentID =? AND ((s1.statusID = 'S003' AND e.roleID = 'R002') OR (s1.statusID = 'S004' AND e.roleID = 'R005') OR (s1.statusID = 'S005' AND e.roleID = 'R005'))";
        String gFinanciero = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,"+
                             "d.documentType,l.loanType,l.loanPaymentOption,w.withdrawalType,"+
                             "m.memberID,m.memberCedula,m.memberName,m.memberLastname,m.memberPhoneNumber "+
                             "FROM ("+
                             "SELECT MAX(stateID) AS stateIDS "+
                             "FROM state "+
                             "GROUP BY documentID"+
                             ") AS s "+
                             "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                             "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                             "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                             "JOIN document AS d ON d.documentID = s1.documentID "+
                             "LEFT JOIN loan AS l ON d.documentID = l.documentID "+
                             "LEFT JOIN withdrawal AS w ON d.documentID = w.documentID "+
                             "JOIN member AS m ON d.memberID = m.memberID "+
                             "WHERE d.documentID =? AND ((s1.statusID = 'S003' AND e.roleID = 'R002') OR (s1.statusID = 'S004' AND e.roleID = 'R004') OR (s1.statusID = 'S005' AND e.roleID = 'R004'))";
        try {
                if(userRole.equals("OFICIALCREDITO")) {
                        pstm = hermesConnection.prepareStatement(oCredito);
                }else if(userRole.equals("ENCARGADOCREDITO")) {
                        pstm = hermesConnection.prepareStatement(eCredito);
                }else if(userRole.equals("GERENTEFINANCIERO")) {
                        pstm = hermesConnection.prepareStatement(gFinanciero);
                }else if(userRole.equals("GERENTEGENERAL")) {
                        pstm = hermesConnection.prepareStatement(gGeneral);
                }else if(userRole.equals("MENSAJEROINTERNO")) {
                        pstm = hermesConnection.prepareStatement(mInterno);
                }
                pstm.setInt(1,docID);
                rs = pstm.executeQuery();
                while(rs.next()) {
                        state = new State();

                        if(rs.getString("documentType").equalsIgnoreCase("Withdrawal")) {
                                withdrawal = new Withdrawal();
                                withdrawal.setDocumentId(rs.getInt("documentID"));
                                withdrawal.setDocumentMoneyAmount(rs.getDouble("documentMoneyAmount"));
                                withdrawal.setdocumentType(rs.getString("documentType"));
                                withdrawal.setWithdrawalType(rs.getString("withdrawalType"));
                                withdrawal.getMember().setMemberID(rs.getString("memberID"));
                                withdrawal.getMember().setMemberCedula(rs.getString("memberCedula"));
                                withdrawal.getMember().setMemberName(rs.getString("memberName"));
                                withdrawal.getMember().setMemberLastName(rs.getString("memberLastname"));
                                withdrawal.getMember().setMemberPhoneNumber(rs.getString("memberPhoneNumber"));
                                state.setWithdrawal(withdrawal);

                        }else if(rs.getString("documentType").equalsIgnoreCase("Loan")) {
                                loan = new Loan();
                                loan.setDocumentId(rs.getInt("documentID"));
                                loan.setDocumentMoneyAmount(rs.getDouble("documentMoneyAmount"));
                                loan.setdocumentType(rs.getString("documentType"));
                                loan.setLoanType(rs.getString("loanType"));
                                loan.setLoanPaymentOption(rs.getString("loanPaymentOption"));
                                loan.getMember().setMemberID(rs.getString("memberID"));
                                loan.getMember().setMemberCedula(rs.getString("memberCedula"));
                                loan.getMember().setMemberName(rs.getString("memberName"));
                                loan.getMember().setMemberLastName(rs.getString("memberLastname"));
                                loan.getMember().setMemberPhoneNumber(rs.getString("memberPhoneNumber"));
                                state.setLoan(loan);
                        }
                        state.setStateTime(String.format("%1$Tr", rs.getTimestamp("stateDatetime")));
                        state.setStateDate(String.format("%td/%1$tm/%1$tY", rs.getTimestamp("stateDatetime")));
                        state.setStateStatus(rs.getString("status"));

                        documents.add(state);
                }
                rs.close();
                stm.close();

                return documents;

        }catch (SQLException e) {
                e.printStackTrace();
        }catch (Exception e) {
                e.printStackTrace();
        }
        return documents;
}
public ArrayList<State> searchDocumentsByType(String docType){
        State state;
        Loan loan;
        Withdrawal withdrawal;
        ArrayList <State> documents = new ArrayList <State>();
        String oCredito = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,d.documentType,"+
                          "m.memberID,m.memberCedula,m.memberName,m.memberLastname,m.memberPhoneNumber "+
                          "FROM ("+
                          "SELECT MAX(stateID) AS stateIDS "+
                          "FROM state "+
                          "GROUP BY documentID"+
                          ") AS s "+
                          "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                          "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                          "JOIN document AS d ON d.documentID = s1.documentID "+
                          "JOIN member AS m ON d.memberID = m.memberID "+
                          "WHERE d.documentType = ? AND (s1.statusID = 'S000' OR s1.statusID = 'S007' OR s1.statusID = 'S008')";
        String eCredito = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,d.documentType,"+
                          "m.memberID,m.memberCedula,m.memberName,m.memberLastname,m.memberPhoneNumber "+
                          "FROM ("+
                          "SELECT MAX(stateID) AS stateIDS "+
                          "FROM state "+
                          "GROUP BY documentID"+
                          ") AS s "+
                          "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                          "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                          "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                          "JOIN document AS d ON d.documentID = s1.documentID "+
                          "JOIN member AS m ON d.memberID = m.memberID "+
                          "WHERE d.documentType = ? AND ((s1.statusID = 'S003' AND e.roleID = 'R001') OR (s1.statusID = 'S004' AND e.roleID = 'R002') OR (s1.statusID = 'S001'))";
        String gFinanciero = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,d.documentType,"+
                             "m.memberID,m.memberCedula,m.memberName,m.memberLastname,m.memberPhoneNumber "+
                             "FROM ("+
                             "SELECT MAX(stateID) AS stateIDS "+
                             "FROM state "+
                             "GROUP BY documentID"+
                             ") AS s "+
                             "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                             "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                             "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                             "JOIN document AS d ON d.documentID = s1.documentID "+
                             "JOIN loan AS l ON l.documentID = d.documentID "+
                             "JOIN member AS m ON d.memberID = m.memberID "+
                             "WHERE d.documentType = ? AND ((s1.statusID = 'S003' AND e.roleID = 'R002') OR (s1.statusID = 'S004' AND e.roleID = 'R004') OR (s1.statusID = 'S005' AND e.roleID = 'R004'))";
        String gGeneral = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,d.documentType,"+
                          "m.memberID,m.memberCedula,m.memberName,m.memberLastname,m.memberPhoneNumber "+
                          "FROM ("+
                          "SELECT MAX(stateID) AS stateIDS "+
                          "FROM state "+
                          "GROUP BY documentID"+
                          ") AS s "+
                          "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                          "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                          "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                          "JOIN document AS d ON d.documentID = s1.documentID "+
                          "JOIN withdrawal AS w ON w.documentID = d.documentID "+
                          "JOIN member AS m ON d.memberID = m.memberID "+
                          "WHERE d.documentType = ? AND ((s1.statusID = 'S003' AND e.roleID = 'R002') OR (s1.statusID = 'S004' AND e.roleID = 'R005') OR (s1.statusID = 'S005' AND e.roleID = 'R005'))";
        String mInterno = "SELECT d.documentID,s2.status,s1.stateDatetime,d.documentMoneyAmount,d.documentType,"+
                          "m.memberID,m.memberCedula,m.memberName,m.memberLastname,m.memberPhoneNumber "+
                          "FROM ("+
                          "SELECT MAX(stateID) AS stateIDS "+
                          "FROM state "+
                          "GROUP BY documentID"+
                          ") AS s "+
                          "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                          "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                          "JOIN document AS d ON d.documentID = s1.documentID "+
                          "JOIN member AS m ON d.memberID = m.memberID "+
                          "WHERE d.documentType = ? AND (s1.statusID <> 'S002' AND s1.statusID <> 'S009')";
        try {
                if(userRole.equals("OFICIALCREDITO")) {
                        pstm = hermesConnection.prepareStatement(oCredito);
                }else if(userRole.equals("ENCARGADOCREDITO")) {
                        pstm = hermesConnection.prepareStatement(eCredito);
                }else if(userRole.equals("GERENTEFINANCIERO")) {
                        pstm = hermesConnection.prepareStatement(gFinanciero);
                }else if(userRole.equals("GERENTEGENERAL")) {
                        pstm = hermesConnection.prepareStatement(gGeneral);
                }else if(userRole.equals("MENSAJEROINTERNO")) {
                        pstm = hermesConnection.prepareStatement(mInterno);
                }
                pstm.setString(1,docType);
                rs = pstm.executeQuery();
                while(rs.next()) {
                        state = new State();

                        if(rs.getString("documentType").equalsIgnoreCase("Withdrawal")) {
                                withdrawal = new Withdrawal();
                                withdrawal.setDocumentId(rs.getInt("documentID"));
                                withdrawal.setDocumentMoneyAmount(rs.getDouble("documentMoneyAmount"));
                                withdrawal.setdocumentType(rs.getString("documentType"));
                                withdrawal.getMember().setMemberID(rs.getString("memberID"));
                                withdrawal.getMember().setMemberCedula(rs.getString("memberCedula"));
                                withdrawal.getMember().setMemberName(rs.getString("memberName"));
                                withdrawal.getMember().setMemberLastName(rs.getString("memberLastname"));
                                state.setWithdrawal(withdrawal);

                        }else if(rs.getString("documentType").equalsIgnoreCase("Loan")) {
                                loan = new Loan();
                                loan.setDocumentId(rs.getInt("documentID"));
                                loan.setDocumentMoneyAmount(rs.getDouble("documentMoneyAmount"));
                                loan.setdocumentType(rs.getString("documentType"));
                                loan.getMember().setMemberID(rs.getString("memberID"));
                                loan.getMember().setMemberCedula(rs.getString("memberCedula"));
                                loan.getMember().setMemberName(rs.getString("memberName"));
                                loan.getMember().setMemberLastName(rs.getString("memberLastname"));
                                state.setLoan(loan);
                        }
                        state.setStateTime(String.format("%1$Tr", rs.getTimestamp("stateDatetime")));
                        state.setStateDate(String.format("%td/%1$tm/%1$tY", rs.getTimestamp("stateDatetime")));
                        state.setStateStatus(rs.getString("status"));

                        documents.add(state);
                }
                rs.close();
                stm.close();

                return documents;

        }catch (SQLException e) {
                e.printStackTrace();
        }catch (Exception e) {
                e.printStackTrace();
        }
        return documents;
}

public int updateDocument(State state){
        int completed = 0;
        String document = "UPDATE document SET documentMoneyAmount = ?, documentType = ? WHERE documentId = ?";
        String updateWithdrawal = "UPDATE withdrawal SET withdrawalType = ? WHERE documentId = ?";
        String updateLoan = "UPDATE loan SET loanType = ?, loanPaymentOption = ? WHERE documentId = ?";
        String insertState = "INSERT INTO state (documentId,statusId,employeeID,stateDateTime) "+
                             "VALUES (?,(SELECT statusId FROM status WHERE status = ?),"+
                             "(SELECT employeeID FROM employee WHERE employeeRoleID = CURRENT_USER)"+
                             ",CURRENT_TIMESTAMP)";

        try {
                if (state.getWithdrawal() == null) {
                        pstm = hermesConnection.prepareStatement(document);
                        pstm.setDouble(1,state.getLoan().getDocumentMoneyAmount());
                        pstm.setString(2,state.getLoan().getdocumentType());
                        pstm.setInt(3,state.getLoan().getDocumentId());
                        completed += pstm.executeUpdate();
                        pstm.close();

                        pstm = hermesConnection.prepareStatement(updateLoan);
                        pstm.setString(1,state.getLoan().getLoanType());
                        pstm.setString(2,state.getLoan().getLoanPaymentOption());
                        pstm.setInt(3,state.getLoan().getDocumentId());
                        completed += pstm.executeUpdate();
                        pstm.close();

                        pstm = hermesConnection.prepareStatement(insertState);
                        pstm.setInt(1,state.getLoan().getDocumentId());
                        pstm.setString(2,state.getStateStatus());
                        completed += pstm.executeUpdate();
                        pstm.close();
                }else if(state.getLoan() == null) {
                        pstm = hermesConnection.prepareStatement(document);
                        pstm.setDouble(1,state.getWithdrawal().getDocumentMoneyAmount());
                        pstm.setString(2,state.getWithdrawal().getdocumentType());
                        pstm.setInt(3,state.getWithdrawal().getDocumentId());
                        completed += pstm.executeUpdate();
                        pstm.close();

                        pstm = hermesConnection.prepareStatement(updateWithdrawal);
                        pstm.setString(1,state.getWithdrawal().getWithdrawalType());
                        pstm.setInt(2,state.getWithdrawal().getDocumentId());
                        completed += pstm.executeUpdate();
                        pstm.close();

                        pstm = hermesConnection.prepareStatement(insertState);
                        pstm.setInt(1,state.getWithdrawal().getDocumentId());
                        pstm.setString(2,state.getStateStatus());
                        completed += pstm.executeUpdate();
                        pstm.close();
                }

        }catch (SQLException e) {
                e.printStackTrace();
        }catch (Exception e) {
                e.printStackTrace();
        }

        return completed;
}
public State getFinalState(int docID){
        State state = new State();
        String finalStateQuery = "SELECT s1.statusID,e.roleID "+
                                 "FROM ("+
                                 "SELECT documentID,MAX(stateID) AS stateIDS "+
                                 "FROM state "+
                                 "GROUP BY documentID"+
                                 ") AS s "+
                                 "JOIN state AS s1 ON s.stateIDS = s1.stateID "+
                                 "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                                 "WHERE s.documentID = ?";
        try {
                pstm = hermesConnection.prepareStatement(finalStateQuery);
                pstm.setInt(1,docID);
                rs = pstm.executeQuery();
                if(rs.next()) {
                        state.setStateStatus(rs.getString("statusID"));
                        state.getEmployee().setEmployeeRole(rs.getString("roleID"));
                }
        }catch (SQLException e) {
                e.printStackTrace();
        }catch (Exception e) {
                e.printStackTrace();
        }
        return state;
}
public ArrayList<State> trackDocument(int docID){
        State state;
        Loan loan;
        Withdrawal withdrawal;
        ArrayList <State> documents = new ArrayList <State>();
        String trackDocument = "SELECT s2.status,s1.documentID,s1.stateDatetime,"+
                               "e.employeeDepartment,e.employeeName,e.employeeLastName,r.role,"+
                               "d.documentType, m.memberID, m.memberName, m.memberLastname "+
                               "FROM state AS s1 "+
                               "JOIN status AS s2 ON s1.statusID = s2.statusID "+
                               "JOIN employee AS e ON e.employeeID = s1.employeeID "+
                               "JOIN role AS r ON e.roleID = r.roleID "+
                               "JOIN document AS d ON d.documentID = s1.documentID "+
                               "JOIN member AS m ON d.memberID = m.memberID "+
                               "WHERE s1.documentID = ? "+
                               "ORDER BY s1.stateDatetime";
        try {
                pstm = hermesConnection.prepareStatement(trackDocument);
                pstm.setInt(1,docID);
                rs = pstm.executeQuery();
                while (rs.next()) {
                        state = new State();

                        if(rs.getString("documentType").equalsIgnoreCase("Withdrawal")) {
                                withdrawal = new Withdrawal();
                                withdrawal.setDocumentId(rs.getInt("documentID"));
                                withdrawal.setdocumentType(rs.getString("documentType"));
                                withdrawal.getMember().setMemberID(rs.getString("memberID"));
                                withdrawal.getMember().setMemberName(rs.getString("memberName"));
                                withdrawal.getMember().setMemberLastName(rs.getString("memberLastname"));
                                state.setWithdrawal(withdrawal);

                        }else if(rs.getString("documentType").equalsIgnoreCase("Loan")) {
                                loan = new Loan();
                                loan.setDocumentId(rs.getInt("documentID"));
                                loan.setdocumentType(rs.getString("documentType"));
                                loan.getMember().setMemberID(rs.getString("memberID"));
                                loan.getMember().setMemberName(rs.getString("memberName"));
                                loan.getMember().setMemberLastName(rs.getString("memberLastname"));
                                state.setLoan(loan);
                        }
                        state.getEmployee().setEmployeeLastName(rs.getString("employeeLastName"));
                        state.getEmployee().setEmployeeRole(rs.getString("role"));
                        state.getEmployee().setEmployeeName(rs.getString("employeeName"));
                        state.getEmployee().setEmployeeDepartment(rs.getString("employeeDepartment"));
                        state.setStateTime(String.format("%1$Tr", rs.getTimestamp("stateDatetime")));
                        state.setStateDate(String.format("%td/%1$tm/%1$tY", rs.getTimestamp("stateDatetime")));
                        state.setStateStatus(rs.getString("status"));

                        documents.add(state);
                }
                rs.close();
                stm.close();

                return documents;
        }catch (SQLException e) {
                e.printStackTrace();
        }catch (Exception e) {
                e.printStackTrace();
        }
        return documents;
}

public JasperPrint createReport(HashMap<String,Object> parameters){
        JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        try {
                jasperReport = JasperCompileManager.compileReport("res/XMLReports/hermesReport.jrxml");
                jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,hermesConnection);
        }catch (JRException e) {
                JOptionPane.showMessageDialog(null,"Error Creating the report");
                e.printStackTrace();
        }
        return jasperPrint;
}
}
