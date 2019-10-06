package topica.linhnv5.java8.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class in school
 * @author ljnk975
 */
@Entity
@Table(name = "class")
public class ClassOfSchool implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7732753917471100891L;

	@Id
	@Column(name = "id")
	private Integer classId;

	@Column(name = "code", length = 100, nullable = false)
	private String  classCode;

	@Column(name = "classDesc", length = 1000, nullable = true)
	private String  classDesc;

	@Column(name = "schoolId")
	private Integer schoolId;

	/**
	 * Deafult constructor
	 */
	public ClassOfSchool() {
	}

	public ClassOfSchool(int id, String code, String desc, int schoolId) {
		this.classId   = id;
		this.classCode = code;
		this.classDesc = desc;
		this.schoolId  = schoolId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassDesc() {
		return classDesc;
	}

	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	@Override
	public boolean equals(Object obj) {
		return ((ClassOfSchool)obj).classId == this.classId;
	}

}
