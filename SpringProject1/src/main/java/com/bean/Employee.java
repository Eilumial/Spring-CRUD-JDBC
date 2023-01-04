package com.bean;

public class Employee {
	private String empID;
	private String empFName;
	private String empLName;
	private int empSalary;
	private String empAddr;
	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", empFName=" + empFName + ", empLName=" + empLName + ", empSalary="
				+ empSalary + ", empAddr=" + empAddr + "]";
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpFName() {
		return empFName;
	}
	public void setEmpFName(String empFName) {
		this.empFName = empFName;
	}
	public String getEmpLName() {
		return empLName;
	}
	public void setEmpLName(String empLName) {
		this.empLName = empLName;
	}
	public int getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}
	public String getEmpAddr() {
		return empAddr;
	}
	public void setEmpAddr(String empAddr) {
		this.empAddr = empAddr;
	}
}
