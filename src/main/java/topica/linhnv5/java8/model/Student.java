package topica.linhnv5.java8.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Student class
 * @author ljnk975
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1203512626238085803L;

	@Id
	@Column(name = "id")
	private Integer studentId;

	@Column(name = "name", length = 100, nullable = false)
	private String  studentName;

	@Column(name = "mobile", length = 100, nullable = true)
	private String  studentMobile;

	@Column(name = "classId")
	private Integer classId;

	/**
	 * Default constructor
	 */
	public Student() {
	}

	public Student(int id, String name, String mobile, int classId) {
		this.studentId     = id;
		this.studentName   = name;
		this.studentMobile = mobile;
		this.classId       = classId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentMobile() {
		return studentMobile;
	}

	public void setStudentMobile(String studentMobile) {
		this.studentMobile = studentMobile;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Student)obj).studentId == this.studentId;
	}

}
