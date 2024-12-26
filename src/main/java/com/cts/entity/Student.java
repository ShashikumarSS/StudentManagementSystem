package com.cts.entity; 
import java.util.Set;

import jakarta.persistence.Column; 
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity 
@Table(name="student")
@AllArgsConstructor
@Data
@Builder
public class Student 
{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long rollNo; 
	
	@Column(name="student_name") 
	private String name; 
	
	@Column(name="student_percentage") 
	private float percentage;
	
	@Column(name="student_branch") 
	private String branch; 
	
	@ManyToMany 
	@JoinTable( name = "student_course", 
			joinColumns = @JoinColumn(name = "student_id"), 
			inverseJoinColumns = @JoinColumn(name = "course_id") ) 
	private Set<Course> courses;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="password",nullable = false)
	private String password;
	
	@Column(name="role", nullable = false)
	private String role;
	
	public long getRollNo() 
	{
		return rollNo;
	}
	
	public void setRollNo(int rollNo)
	{ 
		this.rollNo = rollNo; 
	} 
	
	public String getName() 
	{
		return name;
	} 
	
	public void setName(String name) 
	{
		this.name = name; 
	} 
	
	public float getPercentage() 
	{
		return percentage; 
	}
	
	public void setPercentage(float percentage) 
	{
		this.percentage = percentage;
	}
	
	public String getBranch() 
	{
		return branch;
	} 
	
	public void setBranch(String branch)
	{
		this.branch = branch;
	} 
	
	@Override public String toString() 
	{
		return "Student [rollNo=" + rollNo + ", name=" + name + ", percentage=" + percentage + ", branch=" + branch + "]"; 
	}
	
	public Student(String name, float percentage, String branch) 
	{
		super();
		this.name = name;
		this.percentage = percentage; 
		this.branch = branch;
	} 
	
	public Student() 
	{
		
	}
}
