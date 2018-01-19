package controller;

import java.util.Date;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import model.*;

import view.*;

import javax.swing.JOptionPane;

public class ReportController {

HermesDAL DAL = HermesDAL.getInstance();
JasperPrint jasperPrint = null;
HashMap <String,Object> parameters;

public JasperPrint createReport(Date initialDate,Date finalDate){
        String date1 = String.format("%1$tY-%1$tm-%td",initialDate);
        String date2 = String.format("%1$tY-%1$tm-%td",finalDate);

        parameters = new HashMap <String,Object>();
        parameters.put("initialDate",date1);
        parameters.put("finalDate",date2);
        jasperPrint = DAL.createReport(parameters);

        return jasperPrint;
}
}
