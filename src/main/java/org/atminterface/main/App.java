package org.atminterface.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.atminterface.main.dao.AtmOperation;

public class App {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String args[])
			throws NumberFormatException, IOException, ClassNotFoundException, SQLException {

		System.out.println("************************************************************************************");
		System.out.println("****************************    WELCOME TO ATM   ***********************************");
		System.out.println("************************************************************************************");

		boolean status = false;
		do {

			System.out.print("\t\t  Username   : ");
			String userName = bufferedReader.readLine();
			System.out.println();
			System.out.print("\t\t  Password   : ");
			String password = bufferedReader.readLine();
			System.out.println("************************************************************************************");
			status = AtmOperation.login(userName, password);
			
			

			if (status) {
				do {
					System.out.println(
							    "********************************************************************************");
					System.out.println
					           ("************************** CHOOSE A TRANSACTION ********************************");
					System.out.println(
							    "********************************************************************************");
					System.out.println("\t\t  Choose 1-> View Account Balance");
					System.out.println("\t\t  Choose 2-> Cash Withdraw");
					System.out.println("\t\t  Choose 3-> Cash Deposit");
					System.out.println("\t\t  Choose 4-> Generate Report");
					System.out.println("\t\t  Choose 5-> Change pin");
					System.out.println("\t\t  Choose 6-> Logout Account");

					System.out.println(
							     "*******************************************************************************");
					System.out.println("\t\t  Enter a valid input between 1 to 6:");
					int choice = Integer.parseInt(bufferedReader.readLine());

					switch (choice) {
					case 1:
						System.out.println("Enter your account number    :");
						long accountId = Integer.parseInt(bufferedReader.readLine());
						System.out.println("Enter your 4 digit pin number:");
						String pin_number =bufferedReader.readLine();
						System.out.println("Current available balance is :" + AtmOperation.balanceCheck(accountId, pin_number));
						break;

					case 2:
						System.out.println("Enter your account number:");
						accountId = Integer.parseInt(bufferedReader.readLine());
						System.out.println("Enter withdraw amount:");
						double withdrawalAmount = Double.parseDouble(bufferedReader.readLine());
						double result = AtmOperation.withdraw(accountId, withdrawalAmount);

						if (result == 0) {
							System.out.println("Insufficient Balance!!");
							System.out.println("Transaction is unsuccessfull!!");
						} else {
							System.out.println("Transaction successfull!!");
							System.out.println("Remaining balance is:" + result);
						}
						break;

					case 3:
						System.out.println("Enter your account number:");
						accountId = Integer.parseInt(bufferedReader.readLine());
						System.out.println("Enter deposit amount:");
						double depositAmount = Double.parseDouble(bufferedReader.readLine());
						result = AtmOperation.deposit(accountId, depositAmount);

						if (result == 0) {

							System.out.println("Transaction is unsuccessfull!!");
						} else {
							System.out.println("Transaction successfull!!");
							System.out.println("New balance is:" + result);
						}
						break;

					case 4:
						System.out.println("Enter your account number:");
						accountId = Integer.parseInt(bufferedReader.readLine());
						System.out.println(
								"********************************************************************************");
						System.out.println
						       ("***************************  ACCOUNT DETAILS  **********************************");

						System.out.println(
								"********************************************************************************");

						ResultSet accountInfo = AtmOperation.checkAccountInfo(accountId);
						System.out.println("\t\t  Account Id :" + accountInfo.getLong("accid"));
						System.out.println("\t\t  Name :" + accountInfo.getString("accname"));
						System.out.println("\t\t  Bank Name :" + accountInfo.getString("bank_name"));
						System.out.println("\t\t  Phone :" + accountInfo.getLong("phone"));
						System.out.println("\t\t  Email :" + accountInfo.getString("email"));
						System.out.println("\t\t  Available Balance :" + accountInfo.getString("balance"));
						System.out.println(
								"---------------------------------------------------------------------------------");
						break;

					case 5:
						System.out.println("Enter your account number:");
						accountId = Integer.parseInt(bufferedReader.readLine());
						System.out.println("Enter your 4 digit pin number:");
						pin_number =bufferedReader.readLine();
						System.out.println("Enter your new 4 digit pin number:");
						String pin_number1 =bufferedReader.readLine();
						System.out.println("New pin :"+ AtmOperation.pinchange(accountId, pin_number, pin_number1));
						break;
						
					
					
					case 6:
						status = false;
						System.out.println("---------------------------------------------------------------------------------");
						System.out.println("Exiting...");
						System.out.println("You have been logged out!! \nThank you!!");
						System.out.println(
								"---------------------------------------------------------------------------------");
						break;

					default:
						System.out.println(
								"---------------------------------------------------------------------------------");
						System.out.println("Wrong Choice!!");
						System.out.println(
								"---------------------------------------------------------------------------------");
					}

				} while (status);
			}

			else {
				System.out.println(
						"---------------------------------------------------------------------------------");
				System.out.println("Incorrect username or password!!");
				System.out.println(
						"---------------------------------------------------------------------------------");
			}
		} while (status);
	}

}
