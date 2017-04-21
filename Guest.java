

import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Guest {

	/**
	 * @author Sipo
	 */

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String idNumber;
	private String sex;
	private int age;
	private int roomNumber;
	 private String roomType;
	private Date timeCheckedIn;

	public Guest(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.userName = builder.userName;
		this.password = builder.password;
		this.idNumber = builder.idNumber;
		this.sex = builder.sex;
		this.age = builder.age;
		this.roomNumber = builder.roomNumber;
		this.roomType = builder.roomType;
		this.timeCheckedIn = builder.timeCheckedIn;
	}

	public static class Builder {

		private String firstName;
		private String lastName;
		private String userName;
		private String password;
		private String idNumber;
		private String sex;
		private String roomType;
		private int age;
		private int roomNumber;
		private Date timeCheckedIn;

		public Builder() {

		}

		private String getFirstName() {
			return firstName;
		}

		private String getLastName() {
			return lastName;
		}

		private String getUserName() {
			return userName;
		}

		private String getPassword() {
			return password;
		}

		private String getIdNumber() {
			return idNumber;
		}

		private String getSex() {
			return sex;
		}

		private String getRoomType() {
			return roomType;
		}

		private int getAge() {
			return age;
		}

		private int getRoomNumber() {
			return roomNumber;
		}

		private Date getTimeCheckedIn() {
			return timeCheckedIn;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder idNumber(String idNumber) {
			this.idNumber = idNumber;
			return this;
		}

		public Builder sex(String sex) {
			this.sex = sex;
			return this;
		}

		public Builder roomNumber(int room) {
			this.roomNumber = room;
			return this;
		}
		public Builder roomType(String type) {
			this.roomType = type;
			return this;
		}

		public Builder timeCheckedIn(Date checkedIn) {
			this.timeCheckedIn = checkedIn;
			return this;

		}

		public Guest build() {
			if(firstName==null){
				throw new IllegalStateException( " No first name!");
			}
			if(lastName==null){
				throw new IllegalStateException( " No last name!");
			}
			if(userName==null){
				throw new IllegalStateException( " No user name!");
			}
			if(password==null){
				throw new IllegalStateException( " No password!");
			}
			if(idNumber==null){
				throw new IllegalStateException( " No ID number!");
			}
			if(sex==null){
				throw new IllegalStateException( " No sex!");
			}
			
			/**missing age and roomNumber*/
			
			if(roomType==null){
				throw new IllegalStateException( " No room type!");
			}
			
			if(timeCheckedIn==null){
				throw new IllegalStateException( " No time checked in!");
			}
			
			
			
			return new Guest(this);
		}

	}
}
