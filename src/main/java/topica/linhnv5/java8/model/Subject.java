package topica.linhnv5.java8.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Subject class
 * @author ljnk975
 *
 */
@Entity
@Table(name = "subject")
public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 203060395482185647L;

	public enum SubjectDomain {
		MATHEMATICS, LITERATURE, PHYSICS, CHEMISTRY, BIOLOGY, HISTORY, GEOGRAPHY;
	}

	@Id
	@Column(name = "id")
	private Integer subjectId;

	@Column(name = "name", length = 100, nullable = false)
	private String  subjectName;

	@Column(name = "subjectDesc", length = 100, nullable = true)
	private String  subjectDesc;

	@Enumerated(EnumType.STRING)
	@Column(name = "domain", length = 20)
	private SubjectDomain domain;

	/**
	 * Default constructor
	 */
	public Subject() {
	}

	public Subject(int id, String name, String desc, SubjectDomain domain) {
		this.subjectId   = id;
		this.subjectName = name;
		this.subjectDesc = desc;
		this.domain      = domain;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectDesc() {
		return subjectDesc;
	}

	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}

	public SubjectDomain getDomain() {
		return domain;
	}

	public void setDomain(SubjectDomain domain) {
		this.domain = domain;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Subject)obj).subjectId == this.subjectId;
	}

}
