package az.services;


import az.connections.DB;
import az.connections.StudentRepository;
import az.model.Responce;
import az.model.Student;
import az.model.Students;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path(value = "student")
public class StudentServices {


    StudentRepository studentRepository = new StudentRepository();

    @Path(value = "getStudentList")
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Students getStudnetList(){
        Students student = new Students();
        try {
            List<Student> students = studentRepository.findAll();
            student.setStudents(students);
        }catch(Exception e){
            e.printStackTrace();
        }
        return student;
    }

    @Path(value = "getStudent")
    @GET()
    ////////////////////////////////////1
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getText(@PathParam(value = "name") String name,
//                          @PathParam(value = "surname") List<String> surname,
//                          @PathParam(value = "age") int age){
//        return "<h1>Hello "+name+" "+surname.toString()+" "+age+" </h1>";
//    }
    ////////////////////////////////////2
//    @Produces(MediaType.TEXT_HTML)
//    public String getText(@DefaultValue(value = "SS.") @QueryParam(value = "name") String name,
//                           @DefaultValue(value = "555") @QueryParam(value = "surname") List<String> surname,
//                           @DefaultValue(value = "23") @QueryParam(value = "age") int age){
//        return "<h1>Hello "+name+" "+surname+" "+age+" </h1>";
//    }
    ////////////////////////////////////3
//    @Produces(MediaType.TEXT_HTML)
//    public String getText(@Context UriInfo info){
//        String name = info.getQueryParameters().getFirst("name");
//        List<String> surname = info.getQueryParameters().get("surname");
//        int age = Integer.parseInt(info.getQueryParameters().getFirst("age"));
//
//        return "<h1>Hello "+name+" "+surname.toString()+" "+age+" </h1>";
//    }
    ////////////////////////////////////4
    @Produces(MediaType.APPLICATION_JSON)
    public  Student getStudent(@QueryParam(value = "id") int id){
        Student s = new Student();
        s.setId(id);
        Student student = studentRepository.find(s);
        return student;
    }


    @Path(value = "addStudent")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Responce addStudent(Student student){
        Responce responce = new Responce();
        try {
            if (studentRepository.save(student)){
                responce.setCode(200);
                responce.setMessage("Success Added");
            }else{
                responce.setCode(400);
                responce.setMessage("Unsccess Added");
            }
        }catch (Exception e){
            responce.setCode(408);
            responce.setMessage("System Error");
            e.printStackTrace();
        }
        return responce;
    }

    @Path(value = "updateStudent")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Responce updateStudent(Student student){
        Responce responce = new Responce();
        try {
            if (studentRepository.update(student)){
                responce.setCode(200);
                responce.setMessage("Success Update");
            } else{
                responce.setMessage("Unsuccess Update");
                responce.setCode(400);
            }
        }catch (Exception e){
            responce.setCode(408);
            responce.setMessage("System Error");
            e.printStackTrace();
        }
        return  responce;
    }

    @Path(value = "deleteStudent")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Responce deleteStudent(Student student){
        Responce responce = new Responce();
        try {
            if (studentRepository.delete(student)){
                responce.setCode(200);
                responce.setMessage("Success Delete");
            }else{
                responce.setCode(400);
                responce.setMessage("Unsuccess Delete");
            }
        }catch (Exception e){
            responce.setCode(408);
            responce.setMessage("System Error");
            e.printStackTrace();
        }
        return responce;
    }
}
