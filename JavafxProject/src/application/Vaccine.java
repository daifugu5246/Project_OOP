package application;

import java.time.LocalDate;

public class Vaccine {
	private Integer number;
	private String name;
	private String company;
	private LocalDate date;
	private String Hospital;
	
	public Vaccine() {
		
	}
	public Vaccine(Integer number, String name, String company, LocalDate date, String hospital) {
		super();
		this.number = number;
		this.name = name;
		this.company = company;
		this.date = date;
		Hospital = hospital;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getHospital() {
		return Hospital;
	}
	public void setHospital(String hospital) {
		Hospital = hospital;
	}
	@Override
	public String toString() {
		return "Vaccine [number=" + number + ", name=" + name + ", company=" + company + ", date=" + date
				+ ", Hospital=" + Hospital + "]";
	}
}
