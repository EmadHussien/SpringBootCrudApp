package com.OmdaSolutions.crudDemo;

import com.OmdaSolutions.crudDemo.dao.StudentDAO;
import com.OmdaSolutions.crudDemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (StudentDAO studentDAO)
	{
		return runner -> {
			//createStudent(studentDAO);
		 	createMultipleStudents(studentDAO);
		//	readStudent(studentDAO);
			//readAllStuden	ts(studentDAO);
		//	findByLastName(studentDAO);
		//	updateStudent(studentDAO);
		//	deleteStudent(studentDAO);
	//		deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting All Students");
		int numRowsDeleted = studentDAO.deleteAllStudents();
		System.out.println("number of effected rows: "+ numRowsDeleted);
		System.out.println("Showing The table now...");
		readAllStudents(studentDAO);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 5;
		studentDAO.deleteStudent(studentId);
		System.out.println("Should be deleted...");
		readAllStudents(studentDAO);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve the student by id
		int studentId = 1 ;
		Student theStudent = studentDAO.findById(studentId);
		// update fields
		if(theStudent != null)
		{
			System.out.println("Updating is working now...");
			theStudent.setLastName("Hussien Abdelaal");
			studentDAO.updateStudent(theStudent);
			System.out.println(theStudent);
		}
		else
			System.out.println("No Students with this id: "+ studentId);

	}

	private void findByLastName(StudentDAO studentDAO) {
		System.out.println("Read Students Matching Last name ");
		List<Student> theStudents = studentDAO.findByLastName("Fahmi ");
		if (theStudents.isEmpty())
			System.out.println("No Data found");
		for (Student student : theStudents)
		{
			System.out.println(student);
		}
	}

	private void readAllStudents(StudentDAO studentDAO) {
		System.out.println("Reading All Students From Db...");
		List<Student> theStudents =  studentDAO.findAll();

		for (Student student : theStudents)
		{
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Reading Student Data From Db...");
		var student = studentDAO.findById(5);
		if (student != null)
			System.out.println(student.toString());
		else
			System.out.println("Student is not found");
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating Multiple Students....");
		var student1 = new Student("Mahmoud","Fahmi","Fahmy@gmail.com");
		var student2 = new Student("John", "Doe", "john.doe@example.com");
		var student3 = new Student("Alice", "Smith", "alice.smith@example.com");

		// Saving to database
		System.out.println("Saving Objects to db...");
		studentDAO.save(student1);
		System.out.println("Student saved Successfully. Generated id: "+ student1.getId());
		studentDAO.save(student2);
		System.out.println("Student saved Successfully. Generated id: "+ student2.getId());
		studentDAO.save(student3);
		System.out.println("Student saved Successfully. Generated id: "+ student3.getId());

	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");

//		var student = new Student("Emad","Hussien","emadhussien851@gmail.com");
		var student = new Student("masry","mahmoud","almasry@gmail.com");

		System.out.println("Saving the student to DB...");

		studentDAO.save(student);

		System.out.println("Student saved Successfully. Generated id: "+ student.getId());
	}

}
