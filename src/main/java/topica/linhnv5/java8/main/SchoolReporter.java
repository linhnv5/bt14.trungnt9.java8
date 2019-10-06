package topica.linhnv5.java8.main;

import java.util.List;

import topica.linhnv5.java8.model.ClassOfSchool;
import topica.linhnv5.java8.model.Student;
import topica.linhnv5.java8.model.Subject.SubjectDomain;

/**
 * Interface to define function reporter
 * @author ljnk975
 */
public interface SchoolReporter {

	/**
	 * get number of student by using school id
	 * @param schoolId id of school
	 * @return number of student
	 */
	public int getNumberOfStudent(int schoolId);

	/**
	 * Get number of student by subject domain
	 * @param domain the domain
	 * @return number of student in subject domain
	 */
	public int getNumberOfStudentInSubjectDomain(int schoolId, SubjectDomain domain);

	/**
	 * Get avg score of a class by classID
	 * @param classId class ID
	 * @return the avg score of class
	 */
	public double getAvgScoreOfClass(int classId);

	/**
	 * Get avg score of a subject by subjectID
	 * @param subjectId subject ID
	 * @return the avg score of subject
	 */
	public double getAvgScoreOfSubject(int schoolId, int subjectId);

	/**
	 * Get avg score of a subject by subject domain
	 * @param domain the domain
	 * @return the avg score of subject domain
	 */
	public double getAvgScoreOfSubjectDomain(int schoolId, SubjectDomain domain);

	/**
	 * Get class has avg score max
	 * @param schoolId id of school
	 * @return the class has avg score max
	 */
	public ClassOfSchool getClassHasAvgScoreMax(int schoolId);

	/**
	 * Get domain has avg score max
	 * @param schoolId id of school
	 * @return the domain has avg score max
	 */
	public SubjectDomain getDomainHasAvgScoreMax(int schoolId);

	/**
	 * Get domain has number of student score max
	 * @param schoolId id of school
	 * @return the domain has number of student max
	 */
	public SubjectDomain getDomainHasNumStudentMax(int schoolId);

	/**
	 * Get avg score of a class by classID
	 * @param classId class ID
	 * @param domain domain of subject
	 * @return the avg score of class
	 */
	public double getAvgScoreOfClassInDomain(int classId, SubjectDomain domain);

	/**
	 * Get class has avg score max
	 * @param domain subject domain
	 * @return the class has avg score max
	 */
	public ClassOfSchool getClassHasAvgScoreMaxInDomain(int schoolId, SubjectDomain domain);

	/**
	 * Get subject score of student
	 * @param subjectId the subjectID
	 * @param studentId the studentID
	 * @param nullScore the score default if not exists, using to sort by score
	 * @return the score
	 */
	public double getScore(int subjectId, int studentId, double nullScore);

	/**
	 * Get top 10 student asc by subject
	 * @param subjectID subjectID of subject
	 * @param limit     limit number of student
	 * @param asc       asc order
	 * @return List of top 10 student
	 */
	public List<Student> getTopStudent(int schoolId, int subjectId, int limit, boolean asc);

}
