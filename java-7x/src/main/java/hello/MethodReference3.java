package hello;

public class MethodReference3 {
    interface EmployeeEmpty {
        // The get method has become a factory method for Employee.
        Employee get();
    }
    interface EmployeeWithName {
        Employee get(String name);
    }
    static class Employee {
        private String name;
        Employee() {
            System.out.println("Empty Constructor");
            this.name = "empty";
        }
        Employee(String name) {
            System.out.println("Name Constructor");
            this.name = name;
        }
        public void foobar() {
            System.out.println("foobar: " + name);
        }
    }
    public static void main(String[] args) {
        EmployeeEmpty hello = Employee::new;
        hello.get().foobar();

        EmployeeWithName hello2 = Employee::new;
        hello2.get("abc").foobar();

    }
}
