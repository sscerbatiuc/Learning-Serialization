package learningserialization.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author sscerbatiuc
 */
public class Employee implements Serializable{
    
    String name;
    String surname;
    LocalDate employeedOn;
    Integer age;

    public Employee(String name, String surname, LocalDate employeedOn, Integer age) {
        this.name = name;
        this.surname = surname;
        this.employeedOn = employeedOn;
        this.age = age;
    }
    
    public static Employee getRandomEmployee(){
        Random rnd = new Random();
        int randValue = rnd.nextInt();
        return new Employee("name" + randValue, "surname" + randValue, LocalDate.now(), randValue);
    }

    @Override
    public String toString() {
        return "<Employee><name>" + name + "</name><surname>" + surname + "</surname><date>" + employeedOn + "</date><age>" + age + "</age></Employee>";
    }
    
}
