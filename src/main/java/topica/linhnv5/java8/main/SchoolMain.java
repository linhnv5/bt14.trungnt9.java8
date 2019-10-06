package topica.linhnv5.java8.main;

import java.util.List;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import topica.linhnv5.java8.model.ClassOfSchool;
import topica.linhnv5.java8.model.School;
import topica.linhnv5.java8.model.Subject;
import topica.linhnv5.java8.model.Subject.SubjectDomain;
import topica.linhnv5.java8.utils.HibernateUtils;

/**
 * Main class to test
 * @author ljnk975
 */
public class SchoolMain {

	public static void main(String[] args) {
		// Init school
//		SchoolManager.gI().createRandomValues();

		// Load data
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();

			// load
			SchoolManager.gI().loadFromDB(session);

			// get reporter
			SchoolReporter reporter = SchoolManager.gI();
			
			// Get list of school
			List<School> listOfSchool = SchoolManager.gI().getListOfSchool();

			// Get list of subject
			List<Subject> listOfSubjects = SchoolManager.gI().getListOfSubject();

			// Report of school
			listOfSchool.forEach(school -> {
				System.out.println("Report of school "+school.getSchoolId()+" name: "+school.getSchoolName());

				// Number of students
				System.out.println("   NumberOfStudent="+reporter.getNumberOfStudent(school.getSchoolId()));

				// Av Score
				System.out.println("   Avg score of subject: ");
				listOfSubjects.forEach(subject -> {
					System.out.println("      subject "+subject.getSubjectId()+" name: "+subject.getSubjectName()
							+" avg="+reporter.getAvgScoreOfSubject(school.getSchoolId(), subject.getSubjectId()));
				});

				// Report class has max avg score in school
				ClassOfSchool clazz1 = reporter.getClassHasAvgScoreMax(school.getSchoolId());
				System.out.println("   ClassOfMaxAvgScore=("+clazz1.getClassId()+" name: "+clazz1.getClassCode()
						+" avgScore="+reporter.getAvgScoreOfClass(clazz1.getClassId())+")");
				
				// Get top 10 of student has score max and min
				System.out.println("   Top 10 student: ");
				listOfSubjects.forEach(subject -> {
					System.out.println("      Subject "+subject.getSubjectId()+" name: "+subject.getSubjectName());
					System.out.println("         Top 10 Student Asc:");
					reporter.getTopStudent(school.getSchoolId(), subject.getSubjectId(), 10, true).forEach(student -> {
						System.out.println("            student "+student.getStudentId()+" name: "+student.getStudentName()+" score: "+reporter.getScore(subject.getSubjectId(), student.getStudentId(), 0.0));
					});
					System.out.println("         Top 10 Student Dec:");
					reporter.getTopStudent(school.getSchoolId(), subject.getSubjectId(), 10, false).forEach(student -> {
						System.out.println("            student "+student.getStudentId()+" name: "+student.getStudentName()+" score: "+reporter.getScore(subject.getSubjectId(), student.getStudentId(), 0.0));
					});
				});

				// Report class has max avg score in domain
				System.out.println("   Class of max avg score in domain: ");
				Stream.of(SubjectDomain.values()).forEach(domain -> {
					ClassOfSchool clazz = reporter.getClassHasAvgScoreMaxInDomain(school.getSchoolId(), domain);
					System.out.println("      Domain "+domain
							+"\n         classOfMaxAvgScore=("+clazz.getClassId()+" name: "+clazz.getClassCode()
								+" avgScore="+reporter.getAvgScoreOfClass(clazz.getClassId())+")"
							);
				});

				// Report subject domain has max avg score in school
				SubjectDomain domain1 = reporter.getDomainHasAvgScoreMax(school.getSchoolId());
				System.out.println("   DomainOfMaxAvgScore=("+domain1
						+" avgScore="+reporter.getAvgScoreOfSubjectDomain(school.getSchoolId(), domain1)+")"
				);

				// Report subject domain has max number of student in school
				SubjectDomain domain2 = reporter.getDomainHasNumStudentMax(school.getSchoolId());
				System.out.println("   DomainOfMaxNumStudent=("+domain2
						+" student="+reporter.getNumberOfStudentInSubjectDomain(school.getSchoolId(), domain2)+")"
				);

				System.out.println("*****************************************");
			});

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.shutdown();
		}
	}

}
