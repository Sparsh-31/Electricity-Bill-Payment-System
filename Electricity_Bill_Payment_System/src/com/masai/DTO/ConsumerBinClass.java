package com.masai.DTO;

public class ConsumerBinClass {

	private int consumer_id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private String address;
	private String mobile_number;
	private String email;
	private int is_active;
	
	public ConsumerBinClass(int consumer_id, String first_name, String last_name, String username, String password,
			String address, String mobile_number, String email, int is_active) {
		this.consumer_id = consumer_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.mobile_number = mobile_number;
		this.email = email;
		this.is_active = is_active;
	}

	@Override
	public String toString() {
		return "Consumer_id: " + consumer_id + " Name: " + first_name +" "+ last_name + " username: " + username + " address: " + address+ "  mobile_number: " + mobile_number + " email: " + email + " is_active: " + is_active ;
	}

	public int getConsumer_id() {
		return consumer_id;
	}

	public void setConsumer_id(int consumer_id) {
		this.consumer_id = consumer_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	
	
	
	
}


