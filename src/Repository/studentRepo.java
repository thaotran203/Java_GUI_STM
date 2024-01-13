/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ACER
 */
public class studentRepo {
    
    public int AddStudentToFile(Student student) throws IOException{
        
        if(IsExistEmail(student.getEmail()) == true){
            return -1;
        }
        
        try (
            FileWriter writer = new FileWriter("C:\\Users\\ACER\\Documents\\NetBeansProjects\\StudentManagement\\src\\Data\\Student.txt", true);
        ) {
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            String studentToString = "";
            studentToString = student.getStudentId()+"/"+student.getFullName()
                    +"/"+student.getDoB()+"/"
                    +student.getGender()+"/"
                    +student.getEmail()+"/"
                    +student.getPhoneNumber()+"/"
                    +student.getAddress();
            bufferedWriter.newLine();
            bufferedWriter.write(studentToString);

            bufferedWriter.close();
            return 1;
            
        } catch (IOException e) {
            return -999;
        }
    }
    
    public boolean IsExistEmail(String email) throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ACER\\Documents\\NetBeansProjects\\StudentManagement\\src\\Data\\Student.txt"));

        try {
            String line;
            while ((line = br.readLine()) != null ) {
                if(line.indexOf(email) != -1) { 
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
        return false;
        }
    }
        
    public boolean ValidateEmail(String email) {
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        
        if(matcher.matches()){
            return true;
        }
        else{
            return false;
        }	
    }
//    
//    public boolean ValidatePhoneNumber(String phoneNumber) {
//        String phoneRegex = "^\\d{10}$";
//        Pattern pattern = Pattern.compile(phoneRegex);
//        Matcher matcher = pattern.matcher(phoneNumber);
//        
//        if(matcher.matches()){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
    
    public boolean UpdateStudentData(Student oldStudent,Student newStudent) throws FileNotFoundException, IOException {

        try{
            
            File oldFile = new File("C:\\Users\\ACER\\Documents\\NetBeansProjects\\StudentManagement\\src\\Data\\Student.txt");
            File newFile = new File("C:\\Users\\ACER\\Documents\\NetBeansProjects\\StudentManagement\\src\\Data\\newFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(oldFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));

            String oldLine = oldStudent.getStudentId()+"/"+oldStudent.getFullName()
                          +"/"+oldStudent.getDoB()+"/"
                          +oldStudent.getGender()+"/"
                          +oldStudent.getEmail()+"/"
                          +oldStudent.getPhoneNumber()+"/"
                          +oldStudent.getAddress();
            
            String newLine = newStudent.getStudentId()+"/"+newStudent.getFullName()
                        +"/"+newStudent.getDoB()+"/"
                        +newStudent.getGender()+"/"
                        +newStudent.getEmail()+"/"
                        +newStudent.getPhoneNumber()+"/"
                        +newStudent.getAddress();
            
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(oldLine)) {
                    bufferedWriter.write(newLine + System.getProperty("line.separator"));
                    continue;
                }
                bufferedWriter.write(currentLine + System.getProperty("line.separator"));
            }
            
            reader.close();
            bufferedWriter.close();
            
            PrintWriter writer = new PrintWriter("C:\\Users\\ACER\\Documents\\NetBeansProjects\\StudentManagement\\src\\Data\\Student.txt");
            writer.print("");
            writer.close();
            
            
            BufferedReader readerTempFile = new BufferedReader(new FileReader(newFile));
            StringBuilder sb = new StringBuilder();
            String line = readerTempFile.readLine();
            while(line != null){
                sb.append(line);
                sb.append(System.lineSeparator());
                line = readerTempFile.readLine();
            }
            String everyThing = sb.toString();
            
            readerTempFile.close();
            
            FileWriter writer2 = new FileWriter(oldFile);

            writer2.write(everyThing);
            writer2.close();

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean DeleteStudent(Student student) throws FileNotFoundException, IOException {
        
        try{
            
            File inputFile = new File("C:\\Users\\ACER\\Documents\\NetBeansProjects\\StudentManagement\\src\\Data\\Student.txt");
            File tempFile = new File("C:\\Users\\ACER\\Documents\\NetBeansProjects\\StudentManagement\\src\\Data\\myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = student.getStudentId()+"/"
                        +student.getFullName()
                        +"/"+student.getDoB()+"/"
                        +student.getGender()+"/"
                        +student.getEmail()+"/"
                        +student.getPhoneNumber()+"/"
                        +student.getAddress();
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToRemove)) continue;
                bufferedWriter.write(currentLine + System.getProperty("line.separator"));
            }
            
            reader.close();
            bufferedWriter.close();
            
            PrintWriter writer = new PrintWriter("C:\\Users\\ACER\\Documents\\NetBeansProjects\\StudentManagement\\src\\Data\\Student.txt");
            writer.print("");
            writer.close();
            
            BufferedReader readerTempFile = new BufferedReader(new FileReader(tempFile));
            StringBuilder sb = new StringBuilder();
            String line = readerTempFile.readLine();
            while(line != null){
                sb.append(line);
                sb.append(System.lineSeparator());
                line = readerTempFile.readLine();
            }
            String everyThing = sb.toString();
            
            readerTempFile.close();
            
            FileWriter writer2 = new FileWriter(inputFile);

            writer2.write(everyThing);
            writer2.close();

            return true;
        }
        catch(Exception e){
            return false;
        }         
    }
    
    public boolean SearchStudentById(String id) {
        String line = "";
  
        try {
            File inputFile = new File("C:\\Users\\ACER\\Documents\\NetBeansProjects\\StudentManagement\\src\\Data\\Student.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            
            File tempFile = new File("C:\\Users\\ACER\\Documents\\NetBeansProjects\\StudentManagement\\src\\Data\\tempFile.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

            while((line = reader.readLine()) != null) {
                if(line.startsWith(id)) {
                    bufferedWriter.write(line);
                }
            }
            reader.close();
            bufferedWriter.close();
            return true;
            
        } catch (IOException e) {
            System.out.println("Error");
            return false;
        }
    }
}