package model;

public class Employee {
public Employee(){
        employeeId = "";
        employeeName = "";
        employeeLastName = "";
        employeeRole = "";
        employeeDepartment = "";
}
public void setEmployeeId(String employeeId){
        this.employeeId = employeeId;
}
public String getEmployeeId(){
        return employeeId;
}
public void setEmployeeName(String employeeName){
        this.employeeName = employeeName;
}
public String getEmployeeName(){
        return employeeName;
}
public void setEmployeeLastName(String employeeLastName){
        this.employeeLastName = employeeLastName;
}
public String getEmployeeLastName(){
        return employeeLastName;
}
public void setEmployeeRole(String employeeRole){
        this.employeeRole = employeeRole;
}
public String getEmployeeRole(){
        return employeeRole;
}
public void setEmployeeDepartment(String employeeDepartment){
        this.employeeDepartment = employeeDepartment;
}
public String getEmployeeDepartment(){
        return employeeDepartment;
}
private String employeeId;
private String employeeName;
private String employeeLastName;
private String employeeRole;
private String employeeDepartment;
}
