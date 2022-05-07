package Appointment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class processes all the input txt files and then gives back the desired result.
 * 
 * @author HARMANVIR SINGH(40019114) 
 * @author SARABPREET SINGH(40154067)
 *
 */
public class ProcessRequests {
	private static Schedule one_scheduleList = new Schedule();
	//private static Schedule two_scheduleList = new Schedule();
	private static ArrayList<Appointment> appointments = null;
	private static ArrayList<Appointment> requestsArrayList = null;
	/**
	 * 
	 * This method works as the engine of the program. It processes all 
	 * the files and then gives back the desired result.
	 *
	 * @param args this method works as the engine of the program. It processes all 
	 * 				the files and then gives back the desired result.
	 */
	public static void main(String[] args) {
		System.out.println("\n-----------------------------------------------------------------------------------------------------------------\n"
				+ "\t\t\t     Starting the program\n"
				+ "\t\t\t     \t\t\t Written by: Harmanvir Singh & Sarabpreet Singh\n"
				+ "-----------------------------------------------------------------------------------------------------------------\n");
		//Reading from the Schedule file.
		openSchedule();
		//Reading from the Requests file.
		openRequests();
		//Processes all the requests according to the given task.
		processingRequests();
		//Prompts the user to enter couple of IDs and check their availability.
		promptUserForAppointmentIDs();
		//Testing all the objects and constructors of the classes and then testing them.
		testingAllMethods();
	}
	
	/**
	 * This method creates and tests all the methods created in previous classes.
	 */
	private static void testingAllMethods() {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(new Appointment("A1", "Harman", 10.00, 11.00));
		appointments.add(new Appointment("A2", "Sarab", 10.30, 11.30));
		appointments.add(new Appointment("A3", "Max", 12.00, 13.00));
		appointments.add(new Appointment("A4", "Chris", 11.30, 12.30));
		appointments.add(new Appointment("A5", "Amar", 15.00, 15.30));
		appointments.add(new Appointment("A6", "Robin", 13.30, 14.30));
		appointments.add(new Appointment("A7", "Sarah", 12.00, 13.00));
		appointments.add(new Appointment("A8", "Ammy", 11.30, 12.00));
		Schedule schedules = new Schedule();
		for(int i=appointments.size()-1; i>=0; i--) {
			schedules.addToStart(appointments.get(i));
		}
		System.out.println("\n------------------------------------------------------------------------");
		System.out.println("\tDisplaying all the appointments present in the list.");
		System.out.println("------------------------------------------------------------------------");
		schedules.display();
		schedules.insertAtIndex(appointments.get(1), 7);
		System.out.println("\n------------------------------------------------------------------------");
		System.out.println("Displaying all the appointments after inserting the value at index 7.");
		System.out.println("------------------------------------------------------------------------");
		schedules.display();
		schedules.replaceAtIndex(appointments.get(4), 2);
		System.out.println("\n------------------------------------------------------------------------");
		System.out.println("Displaying all the appointments after replacing the value at index 2.");
		System.out.println("------------------------------------------------------------------------");
		schedules.display();
		schedules.deleteFromStart();
		System.out.println("\n------------------------------------------------------------------------");
		System.out.println("Displaying all the appointments after deleting the value from the start.");
		System.out.println("------------------------------------------------------------------------");
		schedules.display();
		schedules.deleteFromIndex(4);;
		System.out.println("\n------------------------------------------------------------------------");
		System.out.println("Displaying all the appointments after deleting the value at index 4.");
		System.out.println("------------------------------------------------------------------------");
		schedules.display();
	}

	/**
	 * This method opens the Schedule.txt file to read the data and create all 
	 * the objects necessary for the further operations.
	 */
	private static void openSchedule(){
		appointments = new ArrayList<Appointment>();
		Scanner input = null;
		try {
			input = new Scanner(new FileInputStream("Schedule.txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found so terminating the program...");
			System.exit(0);
		}
		double startTime = 0.00;
		double endTime = 0.00 ;
		while(input.hasNextLine()) {
			String line = input.nextLine();
			String appointmentID = null;
			String doctorName = null;
			if(!line.trim().equals("") && !line.substring(0, 3).equals("Dr.")){
				String[] lineArray = line.split(" ");
				appointmentID = lineArray[0];
				doctorName = lineArray[1];
				line = input.nextLine();
				lineArray = line.split(" ");
				startTime = Double.parseDouble(lineArray[1]);
				line = input.nextLine();
				lineArray = line.split(" ");
				endTime = Double.parseDouble(lineArray[1]);
			}
			if(appointmentID != null && doctorName != null) {
				Appointment appObject = new Appointment(appointmentID, doctorName, startTime, endTime);
				appointments.add(appObject);
			}
		}
		boolean isEqual = false;
		for(int i = 0; i<appointments.size(); i++) {
			isEqual = false;
			for(int j=0; j<appointments.size(); j++) {
				if(i==j) {
					break;
				}
				if(appointments.get(i).equals(appointments.get(j))) {
					isEqual = true;
					break;
				}
			}
			if(!isEqual) {
				one_scheduleList.addToStart(appointments.get(i));
			}
		}
		input.close();
	}
	
	/**
	 * This method opens the Requests.txt file and then creates and save 
	 * the request objects and use then accordingly in the class.
	 */
	private static void openRequests() {
		Scanner inputRequests = null;
		requestsArrayList = new ArrayList<Appointment>();
		try {
			inputRequests = new Scanner(new FileInputStream("Requests.txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("Requests File not found, So terminating the program...!!");
			System.exit(0);
		}
		while(inputRequests.hasNext()) {
			String line = inputRequests.nextLine();
			String[] lineArray = line.split(" ",-1);
			requestsArrayList.add(new Appointment(lineArray[0], null,  Double.parseDouble(lineArray[1]), 
										Double.parseDouble(lineArray[2])));
		}
		inputRequests.close();
	}
	
	/**
	 * This method processes all the requests and displays the output.
	 */
	private static void processingRequests() {
		int counter = 0;
		String doctorName = null;
		for(int i=0; i<requestsArrayList.size(); i++) {
			counter = 0;
			for(int j=0; j<appointments.size(); j++) {
				String result = requestsArrayList.get(i).isOnSameTime(appointments.get(j));
				if(result.equalsIgnoreCase("Same time")) {
					doctorName = appointments.get(j).getDoctorName();
					counter++;
				}
				else if(result.equalsIgnoreCase("Some Overlap")) {
					doctorName = appointments.get(j).getDoctorName();
					counter++;
				}
			}
			if(counter==0) {
				System.out.println("Patient can't book appointment " + requestsArrayList.get(i).getAppointmentID() 
						+" from " + requestsArrayList.get(i).getStartTime() + " to " 
						+ requestsArrayList.get(i).getEndTime() + " as no doctor is available at this time.");
			}
			if(counter == 1) {
				System.out.println("Patient can book appointment " + requestsArrayList.get(i).getAppointmentID() 
						+" from " + requestsArrayList.get(i).getStartTime() + " to " 
						+ requestsArrayList.get(i).getEndTime() + " with " + doctorName + " as other doctors are "
						+ "not available at this time.");
			}
			if(counter > 1) {
				System.out.println("Patient can book appointment   " + requestsArrayList.get(i).getAppointmentID() 
						+" from " + requestsArrayList.get(i).getStartTime() + " to " 
						+ requestsArrayList.get(i).getEndTime() + " as nothing is schedule during that time for "
						+ "multiple doctors " );
			}
		}
	}
	
	/**
	 * This method prompt the user to enter few appointment Ids in order to check in the given file.
	 */
	private static void promptUserForAppointmentIDs() {
		Scanner keyIn = new Scanner(System.in);
		System.out.print("\nEnter the number of times you want to enter the IDs : ");
		int repeat = keyIn.nextInt();
		keyIn.nextLine();
		while(repeat>0) {
			System.out.print("Enter the appointment ID: ");
			String appointmentID = keyIn.nextLine();
			boolean hasID = one_scheduleList.contains(appointmentID);
			if(!hasID) {
				System.out.println("List does NOT contains the ID \"" + appointmentID + "\".");
			}
			repeat--;
		}
		keyIn.close();
	}
}
