package topica.linhnv5.java8.main;

import java.io.File;
import java.util.EnumSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import topica.linhnv5.java8.utils.HibernateUtils;

public class GenerateData {

	public static final String SCRIPT_FILE = "createTable.sql";

	// Printing sql
	private static SchemaExport getSchemaExport() {
		SchemaExport export = new SchemaExport();

		// Script file.
		File outputFile = new File(SCRIPT_FILE);
		String outputFilePath = outputFile.getAbsolutePath();

		System.out.println("Export file: " + outputFilePath);

		export.setDelimiter(";");
		export.setOutputFile(outputFilePath);

		// 
		export.setHaltOnError(false);

		return export;
	}

	public static void dropDataBase(SchemaExport export, Metadata metadata) {
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
		export.drop(targetTypes, metadata);
	}

	public static void createDataBase(SchemaExport export, Metadata metadata) {
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
		SchemaExport.Action action = SchemaExport.Action.CREATE;
		export.execute(targetTypes, action, metadata);
		System.out.println("Export OK");
	}

	public static void main(String[] args) {
		// Create table
		SchemaExport export = getSchemaExport();

		System.out.println("Drop Database...");

		// Drop Database
		dropDataBase(export, HibernateUtils.getMetadata());

		System.out.println("Create Database...");

		// Create tables
		createDataBase(export, HibernateUtils.getMetadata());

		// Init school
		SchoolManager.gI().createRandomValues();

		// Save data
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();

			SchoolManager.gI().saveToDB(session);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.shutdown();
		}
	}

}
