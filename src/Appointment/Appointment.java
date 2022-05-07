package Appointment;

import java.util.Scanner;

/**
 * This Class will determine whether a patient can book an appointment at a medical 
 * clinic based on the availability of doctors working there
 * 
 * @author HARMANVIR SINGH(40019114) 
 * @author SARABPREET SINGH(40154067)
 *
 */

public class Appointment implements Bookable, Cloneable {
	private String appointmentID;
	private String doctorName;
	private double startTime;
	private double endTime;
	
	/**
	 * Parameterized Constructor with four parameters.
	 * @param appointmentID	Appointment Id 
	 * @param doctorName	Doctor Name
	 * @param startTime	Start Time	
	 * @param endTime	End Time
	 */
	public Appointment(String appointmentID, String doctorName, double startTime, double endTime) {
		this.appointmentID = appointmentID;
		this.doctorName = doctorName;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Parameterized Constructor with two parameters
	 * @param appointment	Appointment Object	
	 * @param appointmentID	Appointment ID
	 */
	public Appointment(Appointment appointment, String appointmentID) {
		this.appointmentID = appointmentID;
		this.doctorName = appointment.doctorName;
		this.startTime = appointment.startTime;
		this.endTime = appointment.endTime;
	}
	
	/**
	 * Method returns the appointment ID.
	 * @return the appointmentID
	 */
	public String getAppointmentID() {
		return appointmentID;
	}
	
	/**
	 * This method sets the appointment ID.
	 * @param appointmentID the appointmentID to set
	 */
	public void setAppointmentID(String appointmentID) {
		this.appointmentID = appointmentID;
	}
	
	/**
	 * This method returns the doctor name.
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}
	
	/**This method sets the Doctor name.
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	/**
	 * This method returns the start time.
	 * @return the startTime
	 */
	public double getStartTime() {
		return startTime;
	}
	
	/**
	 * This method sets the start time.
	 * @param startTime the startTime to set
	 */
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * This method gets the end time.
	 * @return the endTime
	 */
	public double getEndTime() {
		return endTime;
	}
	
	/**
	 * This method sets the end time.
	 * @param endTime the endTime to set
	 */
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

	@Override
	/**
	 * This method returns the String which is used to 
	 * verify is the Appointment is at same time or different 
	 * time or there is some overlap.
	 * @param Appoitnment object a to be verified.
	 * @return String with Same name, Different time or Some overlap.
	 */
	public String isOnSameTime(Appointment a) {
		if(a == null) {
			return "Different time";
		}
		if(this.startTime == a.startTime && this.endTime == a.endTime) {
			return "Same time";
		}
		if(this.startTime == a.startTime || this.endTime == a.endTime) {
			return "Some Overlap";
		}
		if(this.startTime - a.startTime == 0.3) {
			return "Some Overlap";
		}
		return "Different time";
	}
	
	@Override
	/**
	 * Overriding clone method from the object class.
	 * @throws CloneNotSupportedException.
	 * @return Cloned Object 
	 */
	public Object clone() throws CloneNotSupportedException{
		Scanner keyIn = new Scanner(System.in);
		System.out.print("Enter the Appointment ID : for the clone method: ");
		String appointmentID = keyIn.next();
		keyIn.close();
		Appointment appointment = new Appointment(appointmentID, this.doctorName, this.startTime, this.endTime);
		return appointment;
	}

	@Override
	/**
	 * Overriding toString method from the Object class.
	 * @return String.
	 */
	public String toString() {
		return "[appointmentID = " + appointmentID + ", doctorName = " + doctorName + ", startTime = " + startTime
				+ ", endTime = " + endTime + "]";
	}

	@Override
	/**
	 * Overriding equals method from the Object class.
	 * @param Object to be verified. 
	 * @return boolean true if equal, false if not equal.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (doctorName == null) {
			if (other.doctorName != null)
				return false;
		} else if (!doctorName.equals(other.doctorName))
			return false;
		if (Double.doubleToLongBits(endTime) != Double.doubleToLongBits(other.endTime))
			return false;
		if (Double.doubleToLongBits(startTime) != Double.doubleToLongBits(other.startTime))
			return false;
		return true;
	}
}
