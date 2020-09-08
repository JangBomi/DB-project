package db1876343;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MyDriver {

	static Connection myConn = null;
	static Statement myState = null;
	static ResultSet myResSet = null;
	static PreparedStatement pstmt = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//mysql과의 연결을 위해
		String userId = "testuser";
		String userPw = "testpw";
		String dbName = "pr1";
		String url = "jdbc:mysql://localhost:3306/"+dbName+"?&serverTimezone=UTC";
		
		int num = 0;
		
		
		java.util.Scanner input = null;	//키보드 입력을 받기 위한 scanner
		
		
		String sql ="";
		input = new Scanner(System.in);
		try {
			
			myConn = DriverManager.getConnection(url, userId, userPw);
			System.out.println("**********database connecting...**************");
			
			myState = myConn.createStatement();
			
			int select = -1;	//숫자 초기화
			int person = -1;
			
			System.out.println("=================================DB's Bakery Cafe================================");
			
			System.out.println("||  1. 손님 접속    ||   2. 직원 접속      ||");
			person = input.nextInt();
			
			if (person == 1) {
				System.out.println("******************************************************************");
				System.out.println("*****************Welcome!! DB's Bakery Cafe!!!*******************");
				System.out.println("*******************************************************************\n");
				System.out.println();
				while(true) {
				System.out.println("Select the action you want: 1. viewing products   ||    2.viewing seasonal set menus      ||    3.나가기        ");
				int a = input.nextInt();
				if(a==1) {
					System.out.println("Would you like to see all the products? Or would you like to see only certain types of products??");
					System.out.println("1. see all products      ||    2. see certain type of products");
					int b = input.nextInt();
					if(b==1) {
						System.out.println("\n=====================PRODUCT ITEM=======================\n");
						select("products");
					}
					else if (b==2) {
						System.out.println("Please enter the type name of the product you want!");
						select_input();
					}
					else {
						System.out.println("Invalid Number.");
					}
				}
				else if(a==2) {
					System.out.println("★★★★ The set menu is 500 won off the single price.★★★★");
					select_view();
				
				}
				else if(a==3) {
					System.out.println("*******************Thank you! Good bye~:)********************");
					break;
				}
				else {
					System.out.println("Invalid Number. Please re-enter");
				}
				}
			}
			
			else if(person==2) {
				System.out.println("Access to staff. Please enter employee password.");
				String passwd = input.next();
				if(passwd.equals("dbdb")) {
					System.out.println("\nSuccessfully loggen in");
					while(true) {
						System.out.println("Select the action you want :\'In table,\'  1.Select  2.Insert 3.Update  4.Delete  5.Pay  0.END ");
						select = input.nextInt();
						
						//정보를 선택해서 읽어오기(select 구문)
						if (select == 1) {
							System.out.println("SELECTING VALUES.");
							System.out.println("Select the action you want: 1.See all table information 2.See table information you choose 3.See sesasonal set menus  4.See specific type of products  0.End");
							int b=input.nextInt();
							
							//모든 테이블의 정보 한꺼번에 출력
							if(b==1) {
								System.out.println("********************Print ALL Table Information**********************");

							    select("products");
								select("ptypes");
								select("customers");
								select("jobs");
								select("employees");
								select("orders");
								
							}
							//보고싶은 테이블만 입력받아 출력
							else if (b==2) {
								System.out.println("Enter a table name you want to see: ");
								String tableName = input.next();
								select(tableName);
							}
							//만든 view(세트 메뉴 정보) 출력
							else if(b==3) {
								System.out.println("*********************Print Set Menu Information**************************");
								select_view();
							}
							//제품의 타입만 받아서 정보 출력
							else if(b==4) {
								select_input();
							}
							else {
								System.out.println("Invalid number.");
							}
						}
						
						//테이블을 선택하여 값 넣기(insert 구문)
						else if(select == 2) {
							System.out.println("Enter the table name to which you want to add information..");
							String a = input.next();
							insert(a);
							System.out.println("Do you want to see the results after the modification? y/n");
							String b = input.next();
							if (b.equalsIgnoreCase("y")) {
								select(a);
							}
						}
						
						
						//정보 수정하기 (update 구문)
						else if(select == 3) {
							System.out.println("Select the action you want: 1.Change employee password     2.Change product type code");
							int b = input.nextInt();
							if(b == 1) {
								Update("employees");
								System.out.println("Do you want to see the results after the modification? y/n");
								String c = input.next();
								if (c.equalsIgnoreCase("y")) {
									select("employees");
								}
							}
							else if (b==2) {
								update_trans();
								System.out.println("Do you want to see the results after the modification? y/n");
								String c = input.next();
								if (c.equalsIgnoreCase("y")) {
									select("products");
								}
							}
							else {
								System.out.println("Invalid number.");
							}
						}
						
						else if(select == 4) {
							System.out.println("Select the action you want: 1.Delete product by productCode   2.Delete unsold product ");
							int b = input.nextInt();
							if(b==1) {
								System.out.println("Delete the product");
								delete("products");
								System.out.println("Do you want to see the results after the deletion? y/n");
								String c = input.next();
								if (c.equalsIgnoreCase("y")) {
									select("products");
								}
							}
							else if (b==2) {
								deleteNotSellNow("products");
								System.out.println("Do you want to see the results after the deletion? y/n");
								String c = input.next();
								if (c.equalsIgnoreCase("y")) {
									select("products");
								}
							}
							else {
								System.out.println("Invalid number.");
							}
						}
						else if(select ==5 ) {
							System.out.println("pay.");
							insert("orders");
							System.out.println("Do you want to see the results after the payment? y/n");
							String c = input.next();
							if (c.equalsIgnoreCase("y")) {
								select("customers");
							}
						}
						
						else if(select == 0) {
							System.out.println("End the program");
							break;
						}
						
						else {
							System.out.println("Invalid number.");
						}
					}
				}
				else {
					System.out.println("Invalid password. Exit the program.");
				}
			}
			else {
				System.out.println("Invalid number");
			}
			

			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (myResSet != null) {
				try {
					myResSet.close();
					System.out.println("************close resultset***********");
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (myState != null) {
				try {
					myState.close();
					System.out.println("*************close statement ************");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if (myConn != null) {
				try {
					myConn.close();
					System.out.println("***********close connection*************");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		

	}
	
	//파일의 이름을 매개변수로 받아 select 하는 함수
	public static void select (String str) throws SQLException {
		String selectsql ="";
		selectsql = "SELECT * FROM "+str;
		pstmt = myConn.prepareStatement(selectsql);
		myResSet = pstmt.executeQuery(selectsql);
		
		System.out.printf("<%s table>\n", str);
		
		//"products" table 선택
		if (str.equals("products")) {
			int productCode = -1;
			int type = -1;
			String productName = "";
			int price = -1;
			String sellnow = "";
			
			System.out.println(String.format("productCode %6s | type %5s | productName %14s | price %6s | sellNow %4s ",  "", "", "", "", "" ));
			System.out.println(String.format("%93s","" ).replace(' ', '-'));
			
			while(myResSet.next()) {
				productCode = myResSet.getInt("productCode");
				type = myResSet.getInt("type");
				productName = myResSet.getString("productName");
				price = myResSet.getInt("price");
				sellnow = myResSet.getString("sellnow");
				
				System.out.println(String.format("productCode %5d | type %5d | productName %15s | price %6d | sellNow %3s ",productCode, type, productName, price, sellnow));
			
			}
		
		}
		
		//"ptypes" table 선택
		else if (str.equals("ptypes")) {
			int type = -1;
			String typeName = "";

			System.out.println(String.format(" type %5s | typeName %12s ", "", "" ));
			System.out.println(String.format("%38s","" ).replace(' ', '-'));
			
			while(myResSet.next()) {
				type = myResSet.getInt("type");
				typeName = myResSet.getString("typeName");

				
				System.out.println(String.format(" type %5s | typeName %12s ", type, typeName));
			}
		}
		//"customers" table 선택
		else if (str.equals("customers")) {
			int customerCode = -1;
			String customerName = "";
			int money = -1;		//누적 판매액
			int point = -1;		//누적 포인트
			String visited = "";	//최근 방문 일자
			
			System.out.println(String.format("customerCode %8s | customerName %14s | money %6s | point %6s | visited %4s ",  "", "", "", "", "" ));
			System.out.println(String.format("%93s","" ).replace(' ', '-'));
			
			while(myResSet.next()) {
				customerCode = myResSet.getInt("customerCode");
				customerName = myResSet.getString("customerName");
				money = myResSet.getInt("money");
				point = myResSet.getInt("point");
				visited = myResSet.getString("visited");
				
				System.out.println(String.format("customerCode %6s | customerName %14s | money %6s | point %6s | visited %4s ",customerCode, customerName, money, point, visited));
			
			}
		}
		//"jobs" table 선택
		else if (str.equals("jobs")) {
			int jobCode = -1;
			String jobName = "";

			System.out.println(String.format(" jobCode %5s | jobName %10s ", "", "" ));
			System.out.println(String.format("%33s","" ).replace(' ', '-'));
			
			while(myResSet.next()) {
				jobCode = myResSet.getInt("jobCode");
				jobName = myResSet.getString("jobName");

				
				System.out.println(String.format(" jobCode %5s | jobName %10s ", jobCode, jobName));
			}
		}
		
		
		
			
		//"employees" table 선택
		else if (str.equals("employees")) {
			//근무 직원 정보
			//password attribute 추가.
			
			String employeeCode = "";
			String passWord = "";
			String employeeName = "";
			String phoneNumber ="";		
			String startDate ="";
			int jobCode = -1;
			
			System.out.println(String.format("employeeCode %10s | passWord %10s | employeeName %14s | phoneNumber %10s | startDate %10s | jobCode %4s ",  "", "", "", "", "", "" ));
			System.out.println(String.format("%132s","" ).replace(' ', '-'));
			
			while(myResSet.next()) {
				employeeCode = myResSet.getString("employeeCode");
				passWord = myResSet.getString("passWord");
				employeeName = myResSet.getString("employeeName");
				phoneNumber = myResSet.getString("phoneNumber");
				startDate = myResSet.getString("startDate");
				jobCode = myResSet.getInt("jobCode");
				
				System.out.println(String.format("employeeCode %10s | passWord %10s | employeeName %14s | phoneNumber %6s | startDate %6s | jobCode %4s ", employeeCode, passWord, employeeName, phoneNumber, startDate, jobCode));
			
			}
		}
		
		//"orders" table 선택
		else if (str.equals("orders")) {
			int orderCode = -1;
			int productCode = -1;
			int customerCode = -1;
			String employeeCode = "";
			String orderDate = "";
			
			System.out.println(String.format("orderCode %5s | productCode %5s | customerCode %10s |  employeeCode %10s | orderDate %8s ",  "", "", "", "", "" ));
			System.out.println(String.format("%120s","" ).replace(' ', '-'));
			
			while(myResSet.next()) {
				orderCode = myResSet.getInt("orderCode");
				productCode = myResSet.getInt("productCode");
				customerCode = myResSet.getInt("customerCode");
				employeeCode = myResSet.getString("employeeCode");
				orderDate = myResSet.getString("orderDate");
				
				System.out.println(String.format("orderCode %5s | productCode %5s | customerCode %10s |  employeeCode %10s | orderDate %8s ", orderCode, productCode, customerCode, employeeCode, orderDate));
			
			}
		}
		
		else {
			System.out.println("Wrong Table Name. Can't print");
		}
		
	}
	
	//table에 value를 insert함.
	public static void insert(String str) throws SQLException {
		String insertsql = "";
		
		Scanner input = new Scanner(System.in);
		
		if (str.equals("products")) {
			System.out.println("::Insert in the products table::");
			
			insertsql = "INSERT INTO products VALUES (?, ?, ?, ?, ?)";
			
			System.out.print("product code:");
			int productCode = input.nextInt();
			System.out.print("type: ");
			int type = input.nextInt();
			System.out.print("product name: ");
			String productName = input.next();
			input.nextLine();		//공백문자 먹어주기
			System.out.print("price: ");
			int price = input.nextInt();
			System.out.print("sell now?: ");
			String sellnow = input.next();
			
			
			pstmt = myConn.prepareStatement(insertsql);
			
			pstmt.setInt(1, productCode);
			pstmt.setInt(2, type);
			pstmt.setString(3, productName);
			pstmt.setInt(4, price);
			pstmt.setString(5, sellnow);
			
			pstmt.executeUpdate();
			
			System.out.println("Insert complete");
			
			
		}
		
		else if (str.equals("ptypes")) {
			insertsql = "INSERT INTO ptypes VALUES (?, ?)";
			
		
			System.out.print("type: ");
			int type = input.nextInt();
			System.out.print("type name: ");
			String typeName = input.next();
			
			
			pstmt = myConn.prepareStatement(insertsql);
			
			
			pstmt.setInt(1, type);
			pstmt.setString(2, typeName);

			pstmt.executeUpdate();
			
			System.out.println("Insert complete");
			
			
		}
		

		else if(str.equals("customers")) {
			insertsql = "INSERT INTO customers VALUES(?, ?, ?, ?, ?)";
			
			System.out.print("customer code:");
			int customerCode = input.nextInt();
			System.out.print("customer name: ");
			String customerName = input.next();
			input.nextLine();		//공백문자 먹어주기
			System.out.print("money: ");
			int money = input.nextInt();
			System.out.print("point: ");
			int point = input.nextInt();
			System.out.print("Recent visit date: (format: YYYY-MM-DD)");
			String visited= input.next();
			
			
			pstmt = myConn.prepareStatement(insertsql);
			
			pstmt.setInt(1, customerCode);
			pstmt.setString(2, customerName);
			pstmt.setInt(3, money);
			pstmt.setInt(4, point);
			pstmt.setString(5, visited);
			
			pstmt.executeUpdate();
			
			System.out.println("Insert complete");
			
		}
		
		else if(str.equals("employees")) {
			insertsql = "INSERT INTO employees VALUES(?, ?, ?, ?, ?, ?)";
			
			System.out.print("employee code:");
			String employeeCode = input.next();
			System.out.print("employee password: ");
			String passWord = input.next();
			System.out.print("employeeName: ");
			String employeeName = input.next();
			System.out.print("phone Number: ");
			String phoneNumber = input.next();
			System.out.print("start date of service: (format:YYYY-MM-DD)");
			String startDate = input.next();
			input.nextLine();		//공백문자 먹어주기
			System.out.print("job code: ");
			int jobCode = input.nextInt();
			
			
			pstmt = myConn.prepareStatement(insertsql);
			
			pstmt.setString(1, employeeCode);
			pstmt.setString(2, passWord);
			pstmt.setString(3, employeeName);
			pstmt.setString(4, phoneNumber);
			pstmt.setString(5, startDate);
			pstmt.setInt(6, jobCode);
			
			
			pstmt.executeUpdate();
			
			System.out.println("Insert complete");
			
		}
		
		else if(str.equals("orders")) {
			insertsql = "INSERT INTO orders(productCode, customerCode, employeeCode, orderDate) VALUES( ?, ?, ?, ?)";
			
			//System.out.print("order code 입력:");
			//int orderCode = input.nextInt();
			System.out.print("product code:");
			int productCode = input.nextInt();
			System.out.print("customer code:");
			int customerCode = input.nextInt();
			System.out.print("employee code:");
			String employeeCode = input.next();
			System.out.print("order Date: ");
			String orderDate = input.next();
			
			
			pstmt = myConn.prepareStatement(insertsql);
			
			//pstmt.setInt(1, orderCode);
			pstmt.setInt(1, productCode);
			pstmt.setInt(2, customerCode);
			pstmt.setString(3, employeeCode);
			pstmt.setString(4, orderDate);
			
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
	
			update_customers(customerCode, productCode, orderDate);
			
			
			
			System.out.println("Insert complete");
			
		}

		else{
			System.out.println("Wrong table name.");
		}
		
		
	}
	
	
	/*createdb.sql에서 만든 view를 사용
	 * table과 마찬가지로 select 구문을 사용
	 * scanner를 사용해 n원 이하의 제품을 select하는 쿼리
	 */
	public static void select_view() throws SQLException {
		String selectviewsql = "SELECT * FROM setMenu WHERE setPrice <= ?";
		
		Scanner input = new Scanner(System.in);
		System.out.println("얼마 이하의 메뉴를 찾으세요??");
		int pricebound = input.nextInt();
		System.out.printf("==================Set menu under %dwon===================\n", pricebound);
		
		pstmt = myConn.prepareStatement(selectviewsql);
		
		pstmt.setInt(1, pricebound);
		
		
		myResSet = pstmt.executeQuery();
		
		
		System.out.println(String.format(" setName %35s | setPrice %10s ", "", "" ));
		System.out.println(String.format("%63s","" ).replace(' ', '-'));
		
		String setName = "";
		int setPrice = -1;
		
		while(myResSet.next()) {
			setName = myResSet.getString("setName");
			setPrice = myResSet.getInt("setPrice");

			
			System.out.println(String.format(" setName %35s | setPrice %10s ", setName, setPrice));
		
		
		
		}
		
		
		
	}
	public static void select_input() throws SQLException {
		
		String selectsql = "";
		
		Scanner input = new Scanner(System.in);
		
		selectsql = "SELECT * FROM products ,ptypes WHERE products.type = ptypes.type AND typeName = ? ";
		
		System.out.println("Enter product Type name: ");
		String typeN = input.next();
		
		pstmt = myConn.prepareStatement(selectsql);
		pstmt.setString(1, typeN);
		
		int ptype = -1;
		int productCode = -1;
		String productName = "";
		int price = -1;
		String sellnow = "";
		String typeName = "";
		
		myResSet = pstmt.executeQuery();
		System.out.println(String.format("productCode %6s | type %5s | productName %14s | price %6s | sellNow %4s | typeName %8s ","",  "", "", "", "", "" ));
		System.out.println(String.format("%103s","" ).replace(' ', '-'));
		
		while(myResSet.next()) {
			productCode = myResSet.getInt("productCode");
			ptype = myResSet.getInt("type");
			productName = myResSet.getString("productName");
			price = myResSet.getInt("price");
			sellnow = myResSet.getString("sellnow");
			typeName = myResSet.getString("typeName");
			
			System.out.println(String.format("productCode %5s | type %5s | productName %15s | price %6s | sellNow %3s | typeName %8s",productCode, ptype, productName, price, sellnow, typeName));
		}
		
		
		
		
	}
	
	//직원의 정보 중 비밀번호를 업데이트 하는 함수
	public static void Update(String str) throws SQLException {
		String updatesql = "";
		
		Scanner input = new Scanner(System.in);
		
		if(str.equals("employees")) {
			System.out.println("Change employee's password!!!");
			System.out.println("employee Code: ");
			String employeeCode = input.nextLine();
			System.out.println("New password: ");
			String passWord = input.nextLine();
			System.out.println("Confirm password: ");
			String passWord2 = input.nextLine();
			
			//비밀번호를 두번 입력받아, 확인하는 절차를 가짐
			
			if (passWord.equals(passWord2)) {
				
				
				
				updatesql="Update employees Set passWord = ? WHERE employeeCode = ?";
				
				pstmt = myConn.prepareStatement(updatesql);
				
				pstmt.setString(1, passWord);
				pstmt.setString(2, employeeCode);
				
				pstmt.executeUpdate();
			}
			
			else {
				System.out.println("Wrong password.");
			}
		}	
		else {
			System.out.println("Wrong.");
		}
	}
	
	public static void update_trans() throws SQLException {
		try {
			Scanner input = new Scanner(System.in);
			myConn.setAutoCommit(false);
			
			String updatesql = "Update ptypes SET type = ? where typeName = ?";
			
			System.out.println("Enter type name to change: ");
			String typeName = input.next();
			System.out.println("Enter new type number: ");
			int type = input.nextInt();
			
			pstmt = myConn.prepareStatement(updatesql);
			
			pstmt.setInt(1, type);
			pstmt.setString(2, typeName);
			
			pstmt.executeUpdate();
			
			
			myConn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(myConn!=null) myConn.rollback();
			
			e.printStackTrace();
		}
		
		myConn.setAutoCommit(true);

		
	}
	
	
	
	public static void update_customers(int customerCode, int productCode, String sellDate) throws SQLException {
		
		System.out.println("customers' point, recent visit date, purchase record update");
		
		Scanner input = new Scanner(System.in);
		
		String sql1 = "SELECT price FROM products WHERE productCode = ?";
		pstmt = myConn.prepareStatement(sql1);
		pstmt.setInt(1, productCode);
		
		myResSet = pstmt.executeQuery();
		int aprice = 0;
		
		myResSet.beforeFirst();
		while(myResSet.next()) {
		
			aprice = myResSet.getInt("price");		//받은 price 
			;
		}
		pstmt.close();
		
		double apoint = (double)aprice*0.05;
		int appoint = (int)apoint;
		String updatesql = "UPDATE customers SET money = money + ?, visited= ?, point = point + ? WHERE customerCode = ? ";
		
		pstmt = myConn.prepareStatement(updatesql);
		pstmt.setInt(1, aprice);
		pstmt.setString(2, sellDate);
		pstmt.setInt(3, appoint);
		pstmt.setInt(4, customerCode);
		
		
		pstmt.executeUpdate();
		pstmt.close();
		
	}
	
	public static void delete(String str) throws SQLException {
		str = "products";
		String deletesql ="";
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter product Code of products table: ");
		String num = input.next();
		deletesql = "DELETE FROM products WHERE productCode = ?";
		pstmt = myConn.prepareStatement(deletesql);
		
		pstmt.setString(1, num);
		
		pstmt.executeUpdate();
		
		System.out.println("Delete complete");
	}
	
	public static void deleteNotSellNow(String str) throws SQLException {
		str = "products";
		String deletesql = "";
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Delete all the unsold products. agree? (y/n)");
		String agree = input.next();
		if (agree.equalsIgnoreCase("y")) {
			String a = "n";
			deletesql =  "DELETE FROM products WHERE sellnow = ?";
			pstmt = myConn.prepareStatement(deletesql);
			
			pstmt.setString(1, a);
			
			pstmt.executeUpdate();
			
			System.out.println("Delete Complete");
		}
		
		else {
			System.out.println("Do not delete.");
		}
		
	}
	
	

}
