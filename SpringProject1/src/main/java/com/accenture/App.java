package com.accenture;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bean.Employee;
import com.service.EmployeeService;
import com.service.EmployeeServiceImp;

public class App {

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

		// launchStudent(context);
		launchEmployee(context);

//	
	}

	public static void launchEmployee(ApplicationContext context) {
		EmployeeService empService = context.getBean("empservice", EmployeeServiceImp.class);
		empService.info();
		Scanner sc = new Scanner(System.in);
		System.out.println("*****Welcome to Employee Application Service*****");
		System.out.println();

		while (true) {
			System.out.println("===================================");
			System.out.println("press 1->To Add Employee Information");
			System.out.println("press 2->To Search Employee Details");
			System.out.println("press 3->To Update Employee Details");
			System.out.println("press 4->To Delete Employee Record");
			System.out.println("press 5->To List All Employee Records");
			System.out.println("press 6->To List Employees with Salary Between Range");
			System.out.println("press 9->To Exit from Employee Application");
			System.out.println();
			int expression = Integer.parseInt(sc.nextLine());
			String empid = "", empFName = "", empLName = "", empAddr = "";
			int empSalary = -1;
			String status = "";
			List<Employee> empList = null;
			Employee emp;
			switch (expression) {
			case 1:
				System.out.print("Employee ID:");
				empid = sc.nextLine();
//				System.out.println();
				System.out.print("Employee FIRST NAME:");
				empFName = sc.nextLine();
//				System.out.println();
				System.out.print("Employee LAST NAME:");
				empLName = sc.nextLine();
				System.out.print("Employee SALARY:");
				empSalary = Integer.parseInt(sc.nextLine());
//				System.out.println();
				System.out.print("Employee ADDRESS:");
				empAddr = sc.nextLine();
//				System.out.println();

				emp = new Employee();
				emp.setEmpID(empid);
				emp.setEmpFName(empFName);
				emp.setEmpLName(empLName);
				emp.setEmpSalary(empSalary);
				emp.setEmpAddr(empAddr);
				status = empService.addEmployee(emp);
				System.out.println(status);
				System.out.println("===================================");
				break;
			case 2:
				System.out.println("Employee EmpID:");
				empid = sc.nextLine();
				emp = empService.searchDetail(empid);
				if (emp == null) {
					System.out.println("Employee Does Not Exist");
				} else {
					System.out.println("Employee Details...");
					System.out.println(" ********************");
					System.out.println("|| Employee EmpID:" + empid);
					System.out.println("|| Employee FIRST NAME:" + emp.getEmpFName());
					System.out.println("|| Employee LAST NAME:" + emp.getEmpLName());
					System.out.println("|| Employee SALARY:" + emp.getEmpSalary());
					System.out.println("|| Employee ADDRESS:" + emp.getEmpAddr());
					System.out.println(" ********************");
				}
				System.out.println("===================================");
				break;
			case 3:
				System.out.println("Employee EmpID:");
				empid = sc.nextLine();// Existing Employee EmpID
				emp = empService.searchDetail(empid);
				BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
				try {
					// FName
					System.out.print("Employee FIRST NAME[old first name:" + emp.getEmpFName() + "]:");
					empFName = b.readLine();
					// empFName += empFName;
					System.out.println(empFName);
					if (empFName == null || empFName == "") {
						System.out.println("No Action for Name Update");
						System.out.println("Existing Name=" + emp.getEmpFName());
						empFName = emp.getEmpFName();
					}

					// LName
					System.out.print("Employee LAST NAME[old last name:" + emp.getEmpLName() + "]:");
					empLName = b.readLine();
					// empLName += empLName;
					System.out.println(empLName);
					if (empLName == null || empLName == "") {
						System.out.println("No Action for Name Update");
						System.out.println("Existing Name=" + emp.getEmpLName());
						empLName = emp.getEmpLName();
					}

					// Salary
					System.out.print("Employee SALARY[old salary:" + emp.getEmpSalary() + "]:");
					String tempSal = b.readLine();

					if (tempSal != "" || tempSal != null) {
						empSalary = Integer.parseInt(tempSal);
						System.out.println(empSalary);
					} else {
						System.out.println("No Action for Name Update");
						System.out.println("Existing Salary=" + emp.getEmpSalary());
						empSalary = emp.getEmpSalary();
					}

					// Address
					System.out.print("Employee Address[old last name:" + emp.getEmpAddr() + "]:");
					empAddr = b.readLine();
					// empAddr += empAddr;
					System.out.println(empAddr);
					if (empAddr == null || empAddr == "") {
						System.out.println("No Action for Name Update");
						System.out.println("Existing Address=" + emp.getEmpAddr());
						empAddr = emp.getEmpAddr();
					}

					emp = new Employee();
					emp.setEmpID(empid);
					emp.setEmpFName(empFName);
					emp.setEmpLName(empLName);
					emp.setEmpSalary(empSalary);
					emp.setEmpAddr(empAddr);

					status = empService.updateDetails(emp);
					System.out.println(status);
					System.out.println("===================================");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				break;
			case 4:
				System.out.print("Employee EmpID:");
				empid = sc.nextLine();
				status = empService.deleteRecord(empid);
				System.out.println(status);
				System.out.println("===================================");
				break;
			case 5:
				empList = empService.searchAll();
				if (empList.isEmpty()) {
					System.out.println("No Employee Exist");
				} else {
					System.out.println("Employee Details...");
					System.out.println(" ********************");
					for (Employee e : empList) {
						System.out.println("|| Employee EmpID:" + e.getEmpID());
						System.out.println("|| Employee FIRST NAME:" + e.getEmpFName());
						System.out.println("|| Employee LAST NAME:" + e.getEmpLName());
						System.out.println("|| Employee SALARY:" + e.getEmpSalary());
						System.out.println("|| Employee ADDRESS:" + e.getEmpAddr());
						System.out.println(" ********************");
					}
				}
				System.out.println("===================================");
				break;
			case 6:
				System.out.print("Please enter MIN Salary of range:");
				int salaryMin = Integer.parseInt(sc.nextLine());
				System.out.print("Please enter MAX Salary of range:");
				int salaryMax = Integer.parseInt(sc.nextLine());
				empList = empService.searchBySalaryRange(salaryMin, salaryMax);
				if (empList.isEmpty()) {
					System.out.println("No Employee Exist Within Range of $" + salaryMin + " to $" + salaryMax);
				} else {
					System.out.println("Employee Details...");
					System.out.println(" ********************");
					for (Employee e : empList) {
						System.out.println("|| Employee EmpID:" + e.getEmpID());
						System.out.println("|| Employee FIRST NAME:" + e.getEmpFName());
						System.out.println("|| Employee LAST NAME:" + e.getEmpLName());
						System.out.println("|| Employee SALARY:" + e.getEmpSalary());
						System.out.println("|| Employee ADDRESS:" + e.getEmpAddr());
						System.out.println(" ********************");
					}
				}
				System.out.println("===================================");

				break;
			case 9:
				System.out.println("*******Thank You for using Employee Application*******");
				System.exit(0);
				break;
			default:
				System.out.println("Service not Available\nPlease Enter the options from the given List ");
				break;
			}
		}
	}

	public static void launchStudent(ApplicationContext context) {
//		StudentService studentService= context.getBean("service",StudentServiceImp.class);
//		studentService.info();
//		Scanner scan=new Scanner(System.in);
//		System.out.println("*****Welcome to Student Application Service*****");
//		System.out.println();
//
//		while(true) {
//			System.out.println("===================================");
//			System.out.println("press 1->To Add Student Information");
//			System.out.println("press 2->To search Student Details");
//			System.out.println("press 3->To Update Student Details");
//			System.out.println("press 4->To Delete Student Record");
//			System.out.println("press 5->To Exit from Student Application");
//			System.out.println();
//			int expression=scan.nextInt();
//			String sid="",sname="",saddr="";
//			String status="";
//			Student std;
//			switch(expression) {
//			case 1:
//				System.out.print("Student SID:");
//				sid=scan.next();
//				System.out.println();
//				System.out.print("Student NAME:");
//				sname=scan.next();
//				System.out.println();
//				System.out.print("Student Address:");
//				saddr=scan.next();
//				
//				std=new Student();
//				std.setSid(sid);
//				std.setSname(sname);
//				std.setSaddr(saddr);
//				status=studentService.addStudent(std);
//				System.out.println(status);
//				System.out.println("===================================");
//				break;
//			case 2:
//				System.out.println("Student SID:");
//				sid=scan.next();
//				std=studentService.searchDetail(sid);
//				if(std==null) {
//					System.out.println("Student Not Exists");
//				}
//				else {
//					System.out.println("Student Details...");
//					System.out.println(" ********************");
//					System.out.println("|| Student SID:"+sid);
//					System.out.println("|| Student SNAME:"+std.getSname());
//					System.out.println("|| Student SADDR:"+std.getSaddr());
//					System.out.println(" ********************");
//				}
//				System.out.println("===================================");
//				break;
//			case 3:
//				System.out.println("Student SID:");
//				sid=scan.next();//Existing Student SID
//				std=studentService.searchDetail(sid);
//				BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
//				System.out.print("Student Name[old name:"+std.getSname()+"]:");
//				sname=b.readLine();
//				sname+=sname;
//				System.out.println(sname);
//				if(sname==null||sname=="") {
//					System.out.println("No Action for Name Update");
//					System.out.println("Existing Name="+std.getSname());
//					sname=std.getSname();
//				}
//				System.out.print("Student Address[old address:"+std.getSaddr()+"]:");
//				saddr=b.readLine();
//				if(saddr==null||saddr=="") {
//					saddr=std.getSaddr();
//				}
//				std=new Student();
//				std.setSid(sid);
//				std.setSname(sname);
//				std.setSaddr(saddr);
//				
//				status=studentService.updateDetails(std);
//				System.out.println(status);
//				System.out.println("===================================");
//				break;
//			case 4:
//				System.out.print("Sudent SID:");
//				sid=scan.next();
//				status=studentService.deleteRecord(sid);
//				System.out.println(status);
//				System.out.println("===================================");
//				break;
//			case 5:
//				System.out.println("*******Thank You for using Student Application*******");
//				System.exit(0);
//				break;
//			default:
//				System.out.println("Service not Available\nPlease Enter the options from the given List ");
//					break;
//			}
//		}
	}
}
