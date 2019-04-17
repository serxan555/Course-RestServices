package az.connections;

import az.model.Student;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository extends DB implements Repository<Student> {

    @Override
    public Student find(Student o) {
        String query = "SELECT * FROM `555`.students where students.id=? and status=1";
        Student student = new Student();
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setLong(1, o.getId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));

            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }

        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        String query = "SELECT * FROM `555`.students where status=1";
        List<Student> students = new ArrayList<Student>();
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));

                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }

        }
        return students;
    }

    @Override
    public boolean save(Student o) {
        String query = "insert into students (name,surname,status) " +
                "values (?,?,1)";
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setString(1, o.getName());
            statement.setString(2, o.getSurname());

            statement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
        }
        return false;
    }

    @Override
    public boolean update(Student o) {
        String query = "update students set name=?, surname=? where id=?";
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setString(1, o.getName());
            statement.setString(2, o.getSurname());

            statement.setLong(3, o.getId());
            statement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Student o) {
        String query = "update students set status=0 where id=?";
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setLong(1, o.getId());
            statement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
        }
        return false;
    }
}
