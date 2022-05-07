package Appointment;

import java.util.NoSuchElementException;

/**
 * Class Schedule with linked list implementation helping storing appointment information 
 * and moving/deleting them as per requirements.
 * 
 * @author HARMANVIR SINGH(40019114) 
 * @author SARABPREET SINGH(40154067)
 *
 */

public class Schedule {
	
	class AppointmentNode{
		private Appointment appointment;
		private AppointmentNode next;
		
		/**
		 * Default Constructor
		 */
		public AppointmentNode() {
			this.appointment = null;
			this.next = null;
		}
		
		/**
		 * Parameterized Constructor.
		 * @param appointment
		 * @param next
		 */
		public AppointmentNode(Appointment appointment, AppointmentNode next) {
			this.appointment = appointment;
			this.next = next;
		}
		
		/**
		 * Copy Constructor.
		 * @param appointmentNode
		 */
		public AppointmentNode(AppointmentNode appointmentNode) {
			this.appointment = appointmentNode.appointment;
			this.next = appointmentNode.next;
		}

		/**
		 * This method gets the appointment object.
		 * @return the appointment
		 */
		public Appointment getAppointment() {
			return appointment;
		}

		/**
		 * This method sets the appointment Object and some value.
		 * @param appointment the appointment to set
		 */
		public void setAppointment(Appointment appointment) {
			this.appointment = appointment;
		}

		/**
		 * This method gets the AppointmentNode. 
		 * @return the next
		 */
		public AppointmentNode getNext() {
			return next;
		}

		/**
		 * This method sets the next appointmentNode.
		 * @param next the next to set
		 */
		public void setNext(AppointmentNode next) {
			this.next = next;
		}
	}
	
	private AppointmentNode head;
	private int size;
	
	/**
	 * Default Constructor
	 */
	public Schedule() {
		this.size = 0;
	}
	
	/**
	 * Copy Constructor
	 * @param schedule	Schedule object
	 */
	public Schedule(Schedule schedule) {
		this.head = schedule.head;
		this.size = schedule.size;
	}
	
	/**
	 * This method adds an appointmentNode at the start of the list.
	 * @param appointment	Appointment object
	 */
	public void addToStart(Appointment appointment) {
		AppointmentNode node = new AppointmentNode();
		node.appointment = appointment;
		node.next = head;
		head = node;
		this.size++;
	}
	
	/**
	 * This method inserts the node at the index indicated. 
	 * @param appointment	Appointment object
	 * @param index	index object
	 */
	public void insertAtIndex(Appointment appointment, int index) {
		try {
			if(index>=0 && index<= this.size-1) {
				if(index == 0) {
					addToStart(appointment);
					this.size++;
				}
				else {
					AppointmentNode temp = head;
					for(int i=0; i<index-1; i++) {
						temp = temp.next;
					}
					AppointmentNode node = new AppointmentNode(appointment,temp.next);
					temp.next = node;
					this.size++;
				}
			}
			else {
				throw(new NoSuchElementException());
			}
		}
		catch(NoSuchElementException e) {
			System.out.println("The index where you want to insert "
					+ "the appointment object does not exists.");
			System.exit(0);
		}
	}
	
	/**
	 * This method deletes an appointment node from the index indicated. 
	 * @param index
	 */
	public void deleteFromIndex(int index) {
		try {
			if(index>=0 && index<= this.size-1) {
				if(index==0) {
					deleteFromIndex(0);
				}
				else {
					AppointmentNode temp = head;
					for(int i=0; i<index; i++) {
						temp = temp.next;
					}
					AppointmentNode tempIndex = temp.next;
					temp = tempIndex.next;
					this.size--;
				}
			}
			else {
				throw(new NoSuchElementException());
			}
		}
		catch(NoSuchElementException e) {
			System.out.println("The index where you want to delete "
					+ "the appointment object does not exists.");
			System.exit(0);
		}
	}
	
	/**
	 * This method deletes the first node from the list.
	 */
	public void deleteFromStart() {
		if(this.size == 0) {
			System.out.println("List does not have any node inserted in it.");
		}
		else {
			this.head = this.head.next;
			this.size--;
		}
	}
	/**
	 * Displays the list of Appointments in the list.
	 */
	public void display() {
		AppointmentNode temp = head;
		while(temp!=null) {
			System.out.print(temp.appointment );
			temp = temp.next;
			if(temp != null) {
				System.out.println(" -> ");
			}
		}
	}
	
	/**
	 * This method replaces the Appointment object at the index mentioned by the user.
	 * @param appointment	An appointment object
	 * @param index		an index at which object to be replaced
	 */
	public void replaceAtIndex(Appointment appointment, int index) {
		if(index < 0 || index > this.size-1) {
			return;
		}
		AppointmentNode temp = this.head;
		AppointmentNode node = new AppointmentNode();
		node.appointment = appointment;
		for(int i=0; i<=index-1; i++) {
			temp = temp.next;
		}
		node.next = temp.next;
		temp = head;
		for(int i=0; i<index-1; i++) {
			temp = temp.next;
		}
		temp.next = node;
		this.size++;
	}
	
	/**
	 * This method finds the appointmentNode with the same appointmentID and then 
	 * returns it back accordingly, if the no such object is found then a null object is returned.
	 * @param appointmentID		String appointmentID to be found in the appointment node.
	 */
	public AppointmentNode find(String appointmentID) {
		AppointmentNode temp = head;
		int i;
		for(i=0; i<size; i++) {
			if(temp.appointment.getAppointmentID().equals(appointmentID)) {
				//System.out.println("Total iterations before it found the object are : " + i+1);
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}
	
	/**
	 * This method returns true if it finds the object with the given appointmentID,
	 * and returns false if it is not found.
	 * @param appointmentID		String appointmentID to be found in the appointment node.
	 * @return
	 */
	public boolean contains(String appointmentID) {
		AppointmentNode temp = head;
		for(int i=0; i<size; i++) {
			if(temp.appointment.getAppointmentID().equals(appointmentID)) {
				System.out.println("List contains the ID and "
						+ "Total iterations before it found the object : " + (i+1));
				return true;
			}
			temp = temp.next;
		}
		return false;
	}
	
	/**
	 * This method compares to list of schedule, if the appointments in both of 
	 * them are equal then they are equal and return true, otherwise not equal 
	 * and returns false;
	 * @param schedule
	 * @return
	 */
	public boolean equals(Schedule schedule) {
		if(schedule == null) {
			return false;
		}
		if(this.size != schedule.size) {
			return false;
		}
		for(int i=0; i<this.size; i++) {
			AppointmentNode temp1 = this.head;
			AppointmentNode temp2 = schedule.head;
			if(!temp1.appointment.equals(temp2.appointment)) {
				return false;
			}
			temp1 = temp1.next;
			temp2 = temp2.next;
		}
		return false;
	}
	
	public int size() {
		return this.size;
	}
}
