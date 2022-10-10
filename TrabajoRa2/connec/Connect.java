package connec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Student;
import clases.Teacher;

public class Connect {
	static Statement statement;
	static Connection cc;
	java.sql.PreparedStatement ps;
	private String host = "jdbc:mysql://localhost/school";
	private String user = "root";
	private String pass = "";

	public Connect() {

		try {
			cc = DriverManager.getConnection(host, user, pass);
			statement = cc.createStatement();
			System.out.println("Connection success!");
		} catch (SQLException ex) {
			System.out.println("Connection failed!");
		}
	}

	// INSERT ALUMNO
	public void insertStudent(Student s) {
		try {
			// String dni, String nombre, String apellidos, String email, String fecha_nac,
			// String foto, int telefono
			String query = "INSERT INTO alumnos values('" + s.getDni() + "','" + s.getNombre() + "','"
					+ s.getApellidos() + "','" + s.getEmail() + "','" + s.getFecha_nac() + "','" + s.getFoto() + "','"
					+ s.getTelefono() + "');";
			System.out.println(query);
			statement.execute(query);
			System.out.println("Inserted");

		} catch (Exception e) {
			System.out.println("Not Inserted");
		}
	}

	public void insertTeacher(Teacher t) {
		try {
			// String dni, String nombre, String apellidos, String email
			String query = "INSERT INTO profesor values('" + t.getDni() + "','" + t.getNombre() + "','"
					+ t.getApellidos() + "','" + t.getEmail() + "');";
			System.out.println(query);
			statement.execute(query);
			System.out.println("Inserted");

		} catch (Exception e) {
			System.out.println("Not Inserted");
		}
	}

	// TO VIEW DATA
	public void viewTeacher() {
		List<Teacher> listTeacher = new ArrayList<>();
		try {
			String insertquery = "select * from profesor";
			ResultSet result = statement.executeQuery(insertquery);
			if (result.next()) {
				Teacher teacher = new Teacher(result.getString(1), result.getString(2), result.getString(3),
						result.getString(4));
				listTeacher.add(teacher);
			}
			for (Teacher t : listTeacher) {
				System.out.println(t);
			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
	}

	// TO VIEW DATA
	public void viewStudents() {
		List<Student> listStudents = new ArrayList<>();
		try {
			String insertquery = "select * from alumnos";
			ResultSet result = statement.executeQuery(insertquery);
			if (result.next()) {

				Student studen = new Student(result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getString(6),
						Integer.parseInt(result.getString(7)));
				listStudents.add(studen);
			}
			for (Student s : listStudents) {
				System.out.println(s);
			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
	}

	public void viewStudentsRA(int dni) {
		
		try {
			//Falta terminar el view
			String insertquery = "SELECT asg.codAsig,asg.nombre,r. FROM matricula as INNER JOIN alumnos as a on m.dniAlumno = a."+dni+" INNER JOIN asignatura as asg on m.codAsig = asg.codAsig;";
			ResultSet result = statement.executeQuery(insertquery);
			if (result.next()) {

			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
	}

	// TO UPDATE DATA
	public void update() {
		try {
			String insertquery = "UPDATE `table_name` set `field`='value',`field2`='value2' WHERE field = 'value'";
			statement.executeUpdate(insertquery);
			System.out.println("Updated");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	// TO DELETE DATA
	public void delete() {
		try {
			String insertquery = "DELETE FROM `table_name` WHERE field = 'value'";
			statement.executeUpdate(insertquery);
			System.out.println("Deleted");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
