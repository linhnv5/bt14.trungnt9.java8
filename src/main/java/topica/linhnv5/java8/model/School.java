package topica.linhnv5.java8.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class School
 * @author ljnk975
 */
@Entity
@Table(name = "school")
public class School implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2938210518792052974L;

	@Id
	@Column(name = "id")
	private Integer schoolId;

	@Column(name = "name", length = 100, nullable = false)
	private String  schoolName;

	@Column(name = "schoolDesc", length = 1000, nullable = true)
	private String  schoolDesc;

	/**
	 * Default constructor
	 */
	public School() {
	}

	public School(int id, String name, String desc) {
		this.schoolId   = id;
		this.schoolName = name;
		this.schoolDesc = desc;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolDesc() {
		return schoolDesc;
	}

	public void setSchoolDesc(String schoolDesc) {
		this.schoolDesc = schoolDesc;
	}

	@Override
	public boolean equals(Object obj) {
		return ((School)obj).schoolId == this.schoolId;
	}

}
