package schoolProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class School
{
	 private static String conn;
	 private  static Connection con;
	 protected static Statement stat=null;
	 Scanner sc=new Scanner(System.in); 
	 protected static String Name;
	 protected static int Paidfee;
	 protected static int Sid;
	 protected static String Saddress;
	 protected static String Password;
	 protected static String UserId;
	 protected static String Std;
	 protected static int Tid;
	 protected static String TName;
	 protected static String MailId;
	 protected static String TPassword;
	 protected static String Subject;
	 protected static String TAddress;
	 protected static double Salary;
	 private static double School_Fund=200000;
 
	 
	 static
	 {
	 conn="com.mysql.cj.jdbc.Driver";
	  @SuppressWarnings("unused")
	Connection con=null;
	}
	 
	 
	static Connection connect() throws ClassNotFoundException, SQLException 
	{
	
		Class.forName(conn);
		con=DriverManager.getConnection("JDBC:MYSQL://localhost:3306/SchoolManagement","root","root1234");
		
		
		return con;
		
	}
	
	
	
	void displayStudentRecord() throws ClassNotFoundException, SQLException
	{
			Connection con= connect();
			stat=con.createStatement();
			ResultSet rs=stat.executeQuery("select * from Student");
			System.out.println("Id \t Name\t Class\t UserName\t Adress\t PaidFee");
			while(rs.next())
			{
				System.out.println(rs.getInt("Sid")+"\t"+rs.getString("Name")+"\t"+rs.getString("Class")+"\t"+rs.getString("UserId")+"\t"+"\t"+rs.getString("Saddress")+"\t"+rs.getInt("Paidfee"));
				
			}
		}
	

	
	static void displayTeacherRecord() throws ClassNotFoundException, SQLException
	{
			Connection con= connect();
			stat=con.createStatement();
			ResultSet rs=stat.executeQuery("select * from Teacher");
			System.out.println("Id \t Name\t MailId\t\t Adress \t Subject \tSalary");
			while(rs.next())
			{
				System.out.println(rs.getInt("Tid")+"\t"+rs.getString("Name")+"\t"+rs.getString("MailId")+"\t"+rs.getString("TAddress")+"\t"+rs.getString("Subject")+"\t\t"+rs.getDouble("Salary"));
				
			}
		}
	
	void feeStatus() throws SQLException, ClassNotFoundException 
	{
		Connection con= connect();
		stat=con.createStatement();
		ResultSet rs=stat.executeQuery("select Name, Class, PaidFee from Student where PaidFee< 20000");
		System.out.println("Name of the Student Whose Fee is Remaining Are:");
		System.out.println("Name \t Class \t PaidFee");
		while(rs.next())
		{
			System.out.println(rs.getString("Name")+"\t"+rs.getString("Class")+"\t"+rs.getInt("PaidFee"));
			
		}
	}
	
	
	
	
	
	
	
	
	
	void DeleteRecord() throws ClassNotFoundException, SQLException {
		Connection con= connect();
		stat=con.createStatement();
		System.out.println("Enter student  id to delete Record \n");
		 Sid=sc.nextInt();
		 String str="select * from Student where Sid="+Sid;
		 ResultSet rs=stat.executeQuery(str);
		 
		 if(rs.next()) {
			 System.out.println("value is persent");
			 String del="Delete from Student where Sid="+Sid;
			 int i=stat.executeUpdate(del);
			 if(i>0) {
				 System.out.println("Record is Deleted");
			 }
			 
		 }else {
			 System.out.println(Sid+" not Exits");
		 }
	}
	
	 void teachersalary() throws ClassNotFoundException, SQLException
		 {
				Connection con= connect();
				stat=con.createStatement();
				System.out.println("Enter Teacher  id to which has pay Salary \n");
				 Tid=sc.nextInt();
				 String str="select * from Teacher where Tid="+Tid;
				 ResultSet rs=stat.executeQuery(str);
				 
				 if(rs.next()) {
					 System.out.println("value is persent");
					 System.out.println("Enter salary Amount");
					 int Salary=sc.nextInt();
					 
					 String updated="update Teacher set Salary='"+ Salary +"'where Tid="+Tid;
					 int i=stat.executeUpdate(updated);
					 if(i>0) 
					 {
						 System.out.println("Salary is Done");
					 }
					 
				 }
				 else 
				 {
					 System.out.println(Tid+" not Exits");
				 }
			}
	
		 
		 void expence() throws ClassNotFoundException, SQLException
		 {
			 Connection con= connect();
			 double amount = 0;
			 PreparedStatement ps=con.prepareStatement("select SUM(Salary) from Teacher");
				ResultSet rs=ps.executeQuery();
				System.out.println("Total Salary That we pay To All Teacher");
				while(rs.next())
				{
					 amount=(rs.getDouble(1));
					 System.out.println(amount);
				}
				
				System.out.println("School Fund Amount Is:");
				System.out.println(School_Fund);
				System.out.println("After pay to all teacher Salary and Stationary Amount remaining AMOINT IS:");
				double expence=School_Fund-amount;
				System.out.println(expence);
		 }
	
		 void revenue() throws ClassNotFoundException, SQLException
		 {
			 Connection con= connect();
			 int amount1=0;
			 double amount2=0;
			 double revenue=0;
			 
			 PreparedStatement ps1=con.prepareStatement("select SUM(paidfee) from Student");
				ResultSet rs1=ps1.executeQuery();
				System.out.println("Amount School Received from Student Fee");
				while(rs1.next())

				{
					 amount1=(rs1.getInt(1));
					 System.out.println(amount1);
				}
				
			
						System.out.println("School Fund Amount Is:");
						System.out.println(School_Fund);
						System.out.println("After Adding all Above Fee AMOINT IS:");
						revenue=School_Fund+amount1;
						System.out.println(revenue);
 
		 }
		 
		
	
	}


class Student extends School
{
	//ADD NEW STUDENT
	void addStudentRecord() throws SQLException, ClassNotFoundException 
	{
		 int Sid;
		 String Name;
		 String Std;
		 String UserId;
		 String Password;
		 int Paidfee;
		 String Saddress;

		Connection con= connect();
		stat=con.createStatement();
		System.out.println("Enter Student Id");
		 Sid=sc.nextInt();
		System.out.println("Enter name of Student");
		Name=sc.next();
		System.out.println("Enter Class");
		Std=sc.next();
		System.out.println("Enter Student UserName:");
		UserId=sc.next();
		System.out.println("Enter Student Paasword:");
		Password=sc.next();
		System.out.println("Enter Student Adress");
		Saddress=sc.next();
		System.out.println("Enter paidfee:");
		Paidfee=sc.nextInt();
		 String str="select * from Student where sid="+Sid;
		 ResultSet rs=stat.executeQuery(str);
		 if(!rs.next())
		 {
		 String ins="insert into Student values('"+Sid+"','"+Name+"','"+Std+"','"+UserId+"','"+Password+"','"+Saddress+"','"+Paidfee+"')";
		 int i=stat.executeUpdate(ins);
		 if(i>0) 
		 {
			 System.out.println("student Record is Added ");
		 }
		 
		 }
		 else
		 {
			 System.out.println(Sid+ " This Id is Already exits:");
		 }
	}
	 
	//DISPLAY STUDENT RECORD
	 void viewStudentProfile() throws ClassNotFoundException, SQLException
	 {
		 Connection con= connect();
			stat=con.createStatement();
			System.out.println("Enter Student Id");
			Sid=sc.nextInt();
			ResultSet rs=stat.executeQuery("select * from Student where Sid="+Sid);
			System.out.println("Here is Your Profile inforamtion   "+Name);
			System.out.println("Id \t Name\t Class\t UserName\t Adress\t PaidFee");
			while(rs.next())
			{
				System.out.println(rs.getInt("Sid")+"\t"+rs.getString("Name")+"\t"+rs.getString("Class")+"\t"+rs.getString("UserId")+"\t"+"\t"+rs.getString("Saddress")+"\t"+rs.getInt("Paidfee"));
				
			}
	 }
	 
	
}

class Teacher extends School
{
	//ADD NEW TEACHER
	void addTeacherRecord() throws SQLException, ClassNotFoundException 
	{
		
		Connection con= connect();
		stat=con.createStatement();
		System.out.println("Enter Teacher Id");
		Tid=sc.nextInt();
		System.out.println("Enter name of Teacher");
		 TName=sc.next();
		System.out.println("Enter MailID of Teacher");
		 MailId=sc.next();
		System.out.println("Enter Teacher Paasword:");
		TPassword=sc.next();
		System.out.println("Enter Teacher Adress");
		TAddress=sc.next();
		System.out.println("Enter Subject");
		 Subject=sc.next();
		System.out.println("Enter Salary:");
		Salary=sc.nextDouble();
		 String str="select * from Teacher where Tid="+Tid;
		 ResultSet rs=stat.executeQuery(str);
		 if(!rs.next())
		 {
		
		String ins="insert into Teacher values('"+Tid+"','"+Name+"','"+MailId+"','"+TPassword+"','"+TAddress+"','"+Subject+"','"+Salary+"')";
		 int i=stat.executeUpdate(ins);
		 if(i>0) 
		 {
			 System.out.println("Teacher Record is Added ");
		 }
		 
		 }
		 else
		 {
			 System.out.println(Tid+ " This Id is Already exits:");
		 }
	}
	
	
	//DISPALY TEACHER INFORMATION
	 void viewTeacherProfile() throws ClassNotFoundException, SQLException
	 {
					Connection con= connect();
					stat=con.createStatement();
					System.out.println("Enter Your Id");
					Tid=sc.nextInt();
					ResultSet rs=stat.executeQuery("select * from Teacher where Tid="+Tid);
					System.out.println("Id \t Name\t MailId\t\t Adress \t Subject \tSalary");
					while(rs.next())
					{
						System.out.println(rs.getInt("Tid")+"\t"+rs.getString("Name")+"\t"+rs.getString("MailId")+"\t"+rs.getString("TAddress")+"\t"+rs.getString("Subject")+"\t\t"+rs.getDouble("Salary"));
						
					}
	 }
}

public class SchoolManagement 
{
		
			public static void main(String[] args) throws ClassNotFoundException, SQLException
			{
				
				School obj1=new School();
				Teacher obj2=new Teacher();
				Student obj3=new Student();
				Scanner sc=new Scanner(System.in);
				System.out.println("Login as\n1.Admin\n2.Student\n3.Teacher");
				int c=sc.nextInt();
				if(c==1)
				{
					System.out.println("Joinning As School Admin");
					while(true)
					{
						System.out.println("1. Add New student");
						System.out.println("2. Display All Student Record");
						System.out.println("3. Check All Student Fee Info");
						System.out.println("4. Add New Teacher");
						System.out.println("5. Display Data Of All Teacher");
						System.out.println("6.Delete Student record ");
						System.out.println("7.Complete Teacher Salary Process");
						System.out.println("8.Trace Expence Details");
						System.out.println("9.Trace Revenue Details");
						System.out.println("10. Exit");
						int ch1=obj1.sc.nextInt();
						switch(ch1)
						{
						case 1:
							System.out.println("Add New Student:");
							obj3.addStudentRecord();
							break;
							
						case 2:
							System.out.println("Display All Student Record ");
							obj1.displayStudentRecord();
							break;
							
						case 3:
							obj1.feeStatus();
							break;
							
						case 4:
							obj2.addTeacherRecord();
							break;
							
						case 5:
							obj1.displayTeacherRecord();
							break;
							
					
						case 6:
							obj1.DeleteRecord();
							break;
							
						case 7:
							obj1.teachersalary();
							break;
						
						case 8:
							obj1.expence();
							break;
						
						case 9:
							obj1.revenue();
							break;
							
						case 10:
							System.out.println("exit");
							System.exit(0);
						
						default:
							System.out.println("You enter Wrong Choice");
							break;
					}
					
					}
				}	
					
				if(c==2)
				{
					System.out.println("Joining As Student:");
					while(true)
					{
						System.out.println("1.View Profile");
						System.out.println("2. Exit");
						int ch2=obj1.sc.nextInt();
						switch(ch2)
						{
						case 1:
							obj3.viewStudentProfile();
							break;
							
						case 2:
							System.out.println("exit");
							System.exit(0);
							break;
						
						default:
						System.out.println("You enter Wrong Choice");
						break;
						}}
				}
					
			if(c==3)
			{
					while(true)
					{
						System.out.println("1.View Profile");
						System.out.println("2.Display All Student Details");
						System.out.println("3. Exit");
						int ch3=obj1.sc.nextInt();
						switch(ch3)
						{
						case 1:
							System.out.println("Here Is Your Profile Information:");
							obj2.viewTeacherProfile();
							break;
							
						case 2:
							obj1.displayStudentRecord();
							break;
							
						case 3:
							System.out.println("exit");
							System.exit(0);
						
						default:
						System.out.println("You enter Wrong Choice");
						break;
						}
						
					}
			}


	}

}
