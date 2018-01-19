package controller;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import model.*;

import view.*;

public class LoginController {
private HermesDAL connectUser = null;
private String user = "";
private String password = "";

public LoginController(String user, String password){
        this.user = user;
        this.password = password;
}
public String validateUser() {
        String connectionCode = "";

        connectUser = HermesDAL.getInstance();
        connectionCode = connectUser.authenticateUser(user, password);
        if (connectionCode.equals("00000") && user.equals("hermesDBA")) {
                connectionCode = "DBA000";
        }

        return connectionCode;
}

public String showErrorMessage(String connectionCode) {
        String role = "";
        switch (connectionCode) {
        case "08004":
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "The Username or Password is incorrect",
                        "Hermes Tracking System",
                        JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("res/WARNING_ICON.png")
                        );
                break;
        case "08001":
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Error: Server error occurred.\nPlease contact the administrator",
                        "Hermes Tracking System",
                        JOptionPane.ERROR_MESSAGE,
                        new ImageIcon("res/ERROR_ICON.png")
                        );
                break;
        case "DBA000":
                int op = JOptionPane.showOptionDialog(
                        MainFrame.getInstance(),
                        "Welcome DBA"+
                        "\nRemember that Your access is through the terminal"+
                        "\nPress OK to redirect",
                        "Hermes Tracking System",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon("res/QUESTION_ICON.png"),
                        null,
                        null
                        );
                if (op == JOptionPane.OK_OPTION) {
                        openConsole();
                        MainController mc = new MainController();
                        mc.close();
                }
                break;
        case "00000":
                role = connectUser.getRoleDB(user);
                if (!role.isEmpty()) {
                        connectUser.setRoleSchemaDB(role);
                        connectUser.registerLogin();
                }else {
                        JOptionPane.showMessageDialog(
                                MainFrame.getInstance(),
                                "Role unidentify please contact the administrator",
                                "Hermes Tracking System",
                                JOptionPane.ERROR_MESSAGE,
                                new ImageIcon("res/ERROR_ICON.png")
                                );
                }
                break;
        default:
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Connection error "+connectionCode+". Please contact the administrator",
                        "Hermes Tracking System",
                        JOptionPane.ERROR_MESSAGE,
                        new ImageIcon("res/ERROR_ICON.png")
                        );
        }
        return role;
}
public void showWindow(String role) {
        switch (role) {
        case "OFICIALCREDITO":
                MainFrame.getInstance().showOCreditoView();
                break;
        case "GERENTEFINANCIERO":
                MainFrame.getInstance().showGGeneralFinancieroView();
                break;
        case "GERENTEGENERAL":
                MainFrame.getInstance().showGGeneralFinancieroView();
                break;
        case "ENCARGADOCREDITO":
                MainFrame.getInstance().showMainEncCredView();
                break;
        case "MENSAJEROINTERNO":
                MainFrame.getInstance().showMInternoView();
                break;
        default:
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Role unidentify please contact the administrator",
                        "Hermes Tracking System",
                        JOptionPane.ERROR_MESSAGE,
                        new ImageIcon("res/ERROR_ICON.png")
                        );
        }
}

public void openConsole(){
        try {
                String command = "cmd /c start ij";
                Runtime.getRuntime().exec(command);
        }catch (IOException e) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Error starting the CMD",
                        "Hermes Tracking System",
                        JOptionPane.ERROR_MESSAGE,
                        new ImageIcon("res/ERROR_ICON.png")
                        );
        }
}
}
