package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import java.awt.Color;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.BorderLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.*;

public class MainFrame extends JFrame {
private ComponentMover cm;
private static MainFrame instance = null;
private JPanel contentPanel, layOutPanel;
private CardLayout mainCardLayout;
private BorderLayout borderLayout;
private TitleBar buttons;
private MainController mainController = new MainController();

private MainFrame(){
        super();
        setSize(1000,750);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setImage();

        contentPanel = new JPanel();
        borderLayout = new BorderLayout();
        contentPanel.setLayout(borderLayout);
        add(contentPanel);

        buttons = new TitleBar();
        contentPanel.add(buttons,BorderLayout.PAGE_START);
        cm = new ComponentMover(this, buttons);

        layOutPanel = new JPanel();
        mainCardLayout = new CardLayout();
        layOutPanel.setLayout(mainCardLayout);
        contentPanel.add(layOutPanel);
        showLogin();
        addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent arg0) {
                                mainController.close();
                        }
                });
}

public static MainFrame getInstance(){
        if (instance == null) {
                instance = new MainFrame();
        }
        return instance;
}

public void showLogin(){
        LoginView loginPanel = new LoginView();
        layOutPanel.add(loginPanel,"LoginPanel");
        mainCardLayout.show(layOutPanel,"LoginPanel");
}
public OCreditoView showOCreditoView() {
        OCreditoView oficialCreditoMainPanel = new OCreditoView();
        layOutPanel.add(oficialCreditoMainPanel,"OficialCreditoMain");
        mainCardLayout.show(layOutPanel,"OficialCreditoMain");

        return oficialCreditoMainPanel;
}
public NewView showNewDoc(){
        NewView newDocPanel = new NewView();
        layOutPanel.add(newDocPanel, "NewView");
        mainCardLayout.show(layOutPanel, "NewView");

        return newDocPanel;
}
public ModifyView showModifyDoc(){
        ModifyView modifyDocPanel = new ModifyView();
        layOutPanel.add(modifyDocPanel, "ModifyDocumentView");
        mainCardLayout.show(layOutPanel, "ModifyDocumentView");

        return modifyDocPanel;
}
public ECreditoView showMainEncCredView(){
        ECreditoView EncargadoCreditoMainPanel = new ECreditoView();
        layOutPanel.add(EncargadoCreditoMainPanel, "ECreditoView");
        mainCardLayout.show(layOutPanel, "ECreditoView");

        return EncargadoCreditoMainPanel;
}
public GGeneralFinancieroView showGGeneralFinancieroView(){
        GGeneralFinancieroView GerenteGeneralFinancieroMainPanel = new GGeneralFinancieroView();
        layOutPanel.add(GerenteGeneralFinancieroMainPanel, "GGeneralFinancieroView");
        mainCardLayout.show(layOutPanel, "GGeneralFinancieroView");

        return GerenteGeneralFinancieroMainPanel;
}
public MInternoView showMInternoView(){
        MInternoView MenInternoMainPanel = new MInternoView();
        layOutPanel.add(MenInternoMainPanel, "MInternoView");
        mainCardLayout.show(layOutPanel, "MInternoView");

        return MenInternoMainPanel;
}
public TrackView showTrackDocumentView(){
        TrackView trackMainPanel = new TrackView();
        layOutPanel.add(trackMainPanel, "TrackView");
        mainCardLayout.show(layOutPanel, "TrackView");

        return trackMainPanel;
}
public SearchView showSearchView(){
        SearchView searchView = new SearchView();
        layOutPanel.add(searchView, "searchView");
        mainCardLayout.show(layOutPanel, "searchView");

        return searchView;
}
public WithdrawalView showWithdrawalView(){
        WithdrawalView withdrawalView = new WithdrawalView();
        layOutPanel.add(withdrawalView, "withdrawalView");
        mainCardLayout.show(layOutPanel, "withdrawalView");

        return withdrawalView;
}
public LoanView showLoanView(){
        LoanView loanView = new LoanView();
        layOutPanel.add(loanView, "loanView");
        mainCardLayout.show(layOutPanel, "loanView");

        return loanView;
}
public void showJasperReportView(){
        JasperReportView jasperReportView = new JasperReportView();
        layOutPanel.add(jasperReportView, "jasperReportView");
        mainCardLayout.show(layOutPanel, "jasperReportView");

}
private void setImage(){
        try {
                setIconImage(ImageIO.read(new File("res/HermesIcon32x32.png")));
        }catch (IOException e) {
                e.printStackTrace();
        }
}
}
