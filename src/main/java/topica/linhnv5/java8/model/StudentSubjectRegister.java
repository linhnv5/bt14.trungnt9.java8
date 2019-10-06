package topica.linhnv5.java8.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Student register subject relative
 * @author ljnk975
 */
@Entity
@Table(name = "register")
public class StudentSubjectRegister implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5936185744933210739L;

	@Id
	@Column(name = "studentId")
	private Integer studentId;

	@Id
	@Column(name = "subjectId")
	private Integer subjectId;

	@Column(name = "score")
	private Double  score;

	/**
	 * Deafult constructor
	 */
	public StudentSubjectRegister() {
	}

	public StudentSubjectRegister(int studentId, int subjectId, double score) {
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.score     = score;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public boolean equals(Object obj) {
		return ((StudentSubjectRegister)obj).studentId == this.studentId && ((StudentSubjectRegister)obj).subjectId == this.subjectId;
	}

}
