/**
 * 
 */
package com.signify.service;

import java.util.*;

import com.signify.bean.Admin;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.client.CRSAdminMenu;
import com.signify.client.CRSApplicationMenu;
import com.signify.client.CRSProfessorMenu;
import com.signify.client.CRSStudentMenu;
import com.signify.collection.DatabaseCollection;
import com.signify.dao.StudentDAO;

public class UserService implements UserInterface {
	StudentDAO student = new StudentDAO();

	Scanner in = new Scanner(System.in);

	public void login() {

		System.out.print("Enter user name: ");
		String username = in.next();
		System.out.print("Enter your Password: ");
		String password = in.next();

		System.out.print("choose user role: \n");
		System.out.println("\t1.ADMIN\n" + "\t2.STUDENT\n" + "\t3.PROFESSOR\n");

		System.out.print("choose user role: ");
		int role = in.nextInt();

		switch (role) {
			case 1:
				if (adminLogin(username, password)) {
					System.out.println("Logged in as ADMIN");
					CRSAdminMenu.adminMenu();
				}
				else
					System.out.println("Invalid admin username or password");
				break;
				
			case 2:
				student.studentLogin(username, password);
				break;
				
			case 3:
				if (professorLogin(username, password)) {
					System.out.println("Logged in as PROFESSOR");
					CRSProfessorMenu.professorMenu();
				}
				else
					System.out.println("Invalid professor username or password");
				break;
				
			default:
				System.out.println("Invalid Option please try again..");
				CRSApplicationMenu.Menu();
		}
	}
	
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/crsdatabase";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "123456";

	/*public void studentLogin(String username, String password) {
		
		
		student.studentLogin(username, password);
		
	}*/

	public boolean adminLogin(String username, String password) {
		for (Admin user : DatabaseCollection.admins) {
			if (user.getUsername().equals(username)) {
				if (user.getPassword().equals(password)) {
					return true;
				}
			}
		}
		
		return false;

	}

	public boolean professorLogin(String username, String password) {
		
		for (Professor user : DatabaseCollection.professors) {
			if (user.getUsername().equals(username)) {
				if (user.getPassword().equals(password)) {
					return true;
				}
			}
		}
		
		return false;

	}


	public void updateDetails(int userId) {
		System.out.println("Details updated");
	}

	public void updatePassword() {
		System.out.println("+++++UPDATE PASSWORD+++++");
		//System.out.println("Enter user name: ");
		String username = "root";
		String password = "abcd@123";

		System.out.print("Enter your username: ");
		String uname = in.next();
		System.out.print("Enter your old password: ");
		String oldPassword = in.next();

		System.out.print("Enter your new password: ");
		String newPassword = in.next();

		System.out.println("Enter confirm new password: ");
		String confpassword = in.next();

		if (!username.equals(uname) || !password.equals(oldPassword) || !newPassword.equals(confpassword)) {
			System.out.println("Password does not match");
			return;
		}

		System.out.println("Password updated Successfully!");
	}

}
