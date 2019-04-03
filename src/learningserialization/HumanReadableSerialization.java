package learningserialization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import learningserialization.model.Employee;

/**
 *
 * @author sscerbatiuc
 */
public class HumanReadableSerialization {

    public static void main(String[] args) {
        try {
            List<Employee> empList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                empList.add(Employee.getRandomEmployee());
            }
            serializeToFile(empList, "D:\\EmployeesHR.txt");
            deserializeFromFile("D:\\EmployeesHR.txt");
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(LearningSerialization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void serializeToFile(List<Employee> empList, String filePath) throws FileNotFoundException, IOException {
        File outputFile = new File(filePath);
        FileOutputStream fileOutStream = new FileOutputStream(outputFile);
        PrintWriter printWriter = new PrintWriter(fileOutStream);
        for (Employee emp : empList) {
            printWriter.println(emp);
        }
        printWriter.flush();
        fileOutStream.close();
        printWriter.close();
    }

    public static void deserializeFromFile(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileReader fileReader = new FileReader(new File(filePath));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String fileLine;
        while ((fileLine = bufferedReader.readLine()) != null) {
            System.out.println(fileLine);
        }
    }
}
