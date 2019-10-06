package topica.linhnv5.java8.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Query;
import org.hibernate.Session;

import topica.linhnv5.java8.model.ClassOfSchool;
import topica.linhnv5.java8.model.School;
import topica.linhnv5.java8.model.Student;
import topica.linhnv5.java8.model.StudentSubjectRegister;
import topica.linhnv5.java8.model.Subject;
import topica.linhnv5.java8.model.Subject.SubjectDomain;

/**
 * Manager class, keep list of school student subject, ...
 * 
 * @author ljnk975
 */
@SuppressWarnings("deprecation")
public class SchoolManager implements SchoolReporter {

	/**
	 * List of school
	 */
	private List<School> listOfSchool;

	/**
	 * List of class
	 */
	private List<ClassOfSchool> listOfClass;

	/**
	 * List of student
	 */
	private List<Student> listOfStudent;

	/**
	 * List of subject
	 */
	private List<Subject> listOfSubject;

	/**
	 * List of student register
	 */
	private List<StudentSubjectRegister> listOfStudentRegister;

	/**
	 * @return the listOfSchool
	 */
	public final List<School> getListOfSchool() {
		return listOfSchool;
	}

	/**
	 * @return the listOfSubject
	 */
	public final List<Subject> getListOfSubject() {
		return listOfSubject;
	}

	/// Singleton
	private static class InstanceHelper {
		public static final SchoolManager instance = new SchoolManager();
	}

	/**
	 * Get instance of manager
	 * 
	 * @return the instance
	 */
	public static SchoolManager gI() {
		return InstanceHelper.instance;
	}

	/**
	 * Create a random data
	 */
	public final void createRandomValues() {
		// To random name of student
		Random r = new Random();

		// Loop value
		int i, j, k;

		// Create list
		this.listOfSchool = new ArrayList<>();
		this.listOfClass = new ArrayList<>();
		this.listOfStudent = new ArrayList<>();
		this.listOfSubject = new ArrayList<>();
		this.listOfStudentRegister = new ArrayList<>();

		// Some School
		this.listOfSchool.add(new School(0, "School1", ""));
		this.listOfSchool.add(new School(1, "School2", ""));
		this.listOfSchool.add(new School(2, "School3", ""));
		this.listOfSchool.add(new School(3, "School4", ""));

		// Some class
		this.listOfClass.add(new ClassOfSchool(0, "A1", "", 0));
		this.listOfClass.add(new ClassOfSchool(1, "A2", "", 0));
		this.listOfClass.add(new ClassOfSchool(2, "A3", "", 0));
		this.listOfClass.add(new ClassOfSchool(3, "B1", "", 1));
		this.listOfClass.add(new ClassOfSchool(4, "B2", "", 1));
		this.listOfClass.add(new ClassOfSchool(5, "B3", "", 1));
		this.listOfClass.add(new ClassOfSchool(6, "B4", "", 1));
		this.listOfClass.add(new ClassOfSchool(7, "C1", "", 2));
		this.listOfClass.add(new ClassOfSchool(8, "C2", "", 2));
		this.listOfClass.add(new ClassOfSchool(9, "D1", "", 3));
		this.listOfClass.add(new ClassOfSchool(10, "D2", "", 3));
		this.listOfClass.add(new ClassOfSchool(11, "D3", "", 3));

		// Some subject
		this.listOfSubject.add(new Subject(0, "Toan", "", SubjectDomain.MATHEMATICS));
		this.listOfSubject.add(new Subject(1, "Ly", "", SubjectDomain.PHYSICS));
		this.listOfSubject.add(new Subject(2, "Hoa", "", SubjectDomain.CHEMISTRY));
		this.listOfSubject.add(new Subject(3, "Sinh", "", SubjectDomain.BIOLOGY));
		this.listOfSubject.add(new Subject(4, "Anh", "", SubjectDomain.HISTORY));
		this.listOfSubject.add(new Subject(5, "Su", "", SubjectDomain.HISTORY));
		this.listOfSubject.add(new Subject(6, "Dia", "", SubjectDomain.GEOGRAPHY));
		this.listOfSubject.add(new Subject(7, "Van", "", SubjectDomain.LITERATURE));

		// Some Student
		final String[] randName = new String[] { "Linh", "Hoang", "Khanh", "Hai", "Hoa", "Hung" };
		final int minLent = 5;
		final int maxLent = 20;

		for (i = 0, k = 0; i < this.listOfClass.size(); i++) {
			// Rand lent
			int lent = minLent + r.nextInt(maxLent - minLent);

			// Random student
			for (j = 0; j < lent; j++)
				this.listOfStudent.add(new Student(k++, randName[r.nextInt(randName.length)], "012",
						r.nextInt(this.listOfClass.size())));
		}

		// Register subject
		for (i = 0; i < this.listOfStudent.size(); i++) {
			Student stu = this.listOfStudent.get(i);
			for (j = 0; j < this.listOfSubject.size(); j++) {
				Subject sub = this.listOfSubject.get(j);

				// Random register
				if (r.nextBoolean()) {
					// Random score
					double score = r.nextDouble() * 10;

					// Add register
					this.listOfStudentRegister
							.add(new StudentSubjectRegister(stu.getStudentId(), sub.getSubjectId(), score));
				}
			}
		}
	}

	public void saveToDB(Session session) {
		// Persist all data
		this.listOfSchool.forEach(school -> session.save(school));
		this.listOfClass.forEach(clazz -> session.save(clazz));
		this.listOfStudent.forEach(student -> session.save(student));
		this.listOfSubject.forEach(subject -> session.save(subject));
		this.listOfStudentRegister.forEach(register -> session.save(register));

		// Flush data to DB.
		// Hibernate execute insert statement.
		session.flush();
	}

	@SuppressWarnings({ "unchecked" })
	public void loadFromDB(Session session) {
        // Create query object.
        Query<School> query1 = session.createQuery("Select e from "+School.class.getName()+" e");

        // Execute query.
        this.listOfSchool = query1.getResultList();

        // Create query object.
        Query<ClassOfSchool> query2 = session.createQuery("Select e from "+ClassOfSchool.class.getName()+" e");

        // Execute query.
        this.listOfClass = query2.getResultList();

        // Create query object.
        Query<Student> query3 = session.createQuery("Select e from "+Student.class.getName()+" e");

        // Execute query.
        this.listOfStudent = query3.getResultList();
        
        // Create query object.
        Query<Subject> query4 = session.createQuery("Select e from "+Subject.class.getName()+" e");

        // Execute query.
        this.listOfSubject = query4.getResultList();

        // Create query object.
        Query<StudentSubjectRegister> query5 = session.createQuery("Select e from "+StudentSubjectRegister.class.getName()+" e");

        // Execute query.
        this.listOfStudentRegister = query5.getResultList();
	}

	/// -> Helper function
	// Match by domain in register
	private Predicate<StudentSubjectRegister> matchDomain(SubjectDomain domain) {
		return register -> listOfSubject.stream().anyMatch(
				subject -> register.getSubjectId() == subject.getSubjectId() && subject.getDomain() == domain);
	}

	// Match by class in register
	private Predicate<StudentSubjectRegister> matchClass(int classId) {
		return register -> listOfStudent.stream().anyMatch(
				student -> register.getStudentId() == student.getStudentId() && student.getClassId() == classId);
	}

	// match by school in register
	private Predicate<StudentSubjectRegister> matchSchool(int schoolId) {
		return register -> listOfClass.stream()
				.anyMatch(clazz -> clazz.getSchoolId() == schoolId
						&& listOfStudent.stream().anyMatch(student -> register.getStudentId() == student.getStudentId()
								&& student.getClassId() == clazz.getClassId()));
	}

	// Cache function to compare
	private static <K, V> Function<K, V> cache(Function<K, V> f, Map<K, V> cache) {
		return k -> cache.computeIfAbsent(k, f);
	}

	// Cache function to compare
	private static <K, V> Function<K, V> cache(Function<K, V> f) {
		return cache(f, new IdentityHashMap<>());
	}

	// Distinct filter by key
	private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

	/// -> Interface function
	@Override
	public int getNumberOfStudent(int schoolId) {
		return (int) this.listOfStudent.stream()
				.filter(student -> listOfClass.stream().anyMatch(
						clazz -> clazz.getSchoolId() == schoolId && student.getClassId() == clazz.getClassId()))
				.count();
	}

	@Override
	public int getNumberOfStudentInSubjectDomain(int schoolId, SubjectDomain domain) {
		// filter by school and domain, after that get distinct student and count it
		return (int) this.listOfStudentRegister.stream().filter(matchSchool(schoolId)).filter(matchDomain(domain))
				.filter(distinctByKey(register -> register.getStudentId())).count();
	}

	@Override
	public double getAvgScoreOfClass(int classId) {
		// match school, match class and map it to score stream and get avg
		return this.listOfStudentRegister.stream().filter(matchClass(classId))
				.mapToDouble(register -> register.getScore()).summaryStatistics().getAverage();
	}

	@Override
	public double getAvgScoreOfSubject(int schoolId, int subjectId) {
		// match school, match subject and map it to score stream and get avg
		return this.listOfStudentRegister.stream().filter(matchSchool(schoolId))
				.filter(register -> register.getSubjectId() == subjectId).mapToDouble(register -> register.getScore())
				.summaryStatistics().getAverage();
	}

	@Override
	public double getAvgScoreOfSubjectDomain(int schoolId, SubjectDomain domain) {
		// match school, match subject domain and map it to score stream and get avg
		return this.listOfStudentRegister.stream().filter(matchSchool(schoolId)).filter(matchDomain(domain))
				.mapToDouble(register -> register.getScore()).summaryStatistics().getAverage();
	}

	@Override
	public ClassOfSchool getClassHasAvgScoreMax(int schoolId) {
		// match school, and get max of avg score
		return this.listOfClass.stream().filter(clazz -> clazz.getSchoolId() == schoolId)
				.max(Comparator.comparing(cache(clazz -> getAvgScoreOfClass(clazz.getClassId())))).orElse(null);
	}

	@Override
	public SubjectDomain getDomainHasAvgScoreMax(int schoolId) {
		// get max of avg score
		return Stream.of(SubjectDomain.values())
				.max(Comparator.comparing(cache(domain -> getAvgScoreOfSubjectDomain(schoolId, domain)))).orElse(null);
	}

	@Override
	public SubjectDomain getDomainHasNumStudentMax(int schoolId) {
		// get max of number student
		return Stream.of(SubjectDomain.values())
				.max(Comparator.comparing(cache(domain -> getNumberOfStudentInSubjectDomain(schoolId, domain))))
				.orElse(null);
	}

	@Override
	public double getAvgScoreOfClassInDomain(int classId, SubjectDomain domain) {
		// match class and domain and map to score then get avg
		return this.listOfStudentRegister.stream().filter(matchClass(classId).and(matchDomain(domain)))
				.mapToDouble(register -> register.getScore()).summaryStatistics().getAverage();
	}

	@Override
	public ClassOfSchool getClassHasAvgScoreMaxInDomain(int schoolId, SubjectDomain domain) {
		// match school, get max of svg score
		return this.listOfClass.stream().filter(clazz -> clazz.getSchoolId() == schoolId)
				.max(Comparator.comparing(cache(clazz -> getAvgScoreOfClassInDomain(clazz.getClassId(), domain))))
				.orElse(null);
	}

	@Override
	public double getScore(int subjectId, int studentId, double nullScore) {
		return listOfStudentRegister.stream()
				.filter(register -> register.getStudentId() == studentId && register.getSubjectId() == subjectId)
				.mapToDouble(register -> register.getScore()).findFirst().orElse(nullScore);
	}

	@Override
	public List<Student> getTopStudent(int schoolId, int subjectId, int limit, boolean asc) {
		// filter by schoolId, sort and get top
		return this.listOfStudent.stream()
				.filter(student -> listOfClass.stream().anyMatch(
						clazz -> clazz.getSchoolId() == schoolId && student.getClassId() == clazz.getClassId()))
				.sorted(asc
						? Comparator.comparing(
								cache(student -> -getScore(subjectId, student.getStudentId(), Double.MIN_VALUE)))
						: Comparator.comparing(
								cache(student -> getScore(subjectId, student.getStudentId(), Double.MAX_VALUE))))
				.limit(limit).collect(Collectors.toList());
	}

}
