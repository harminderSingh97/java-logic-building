package streamsApi;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You have to found the List of Employee who have  highest Salary in their Department of same organizations
 */
public class FindEmployeeWithHighestSalaryFromEachDepartment {

    public static void main(String[] args) {
        List<Employee> employeeList = Arrays.asList(
                new Employee(101, "Rahul", "IT", 500000),
                new Employee(102, "Piyush", "HR", 600000),
                new Employee(103, "Deepak", "operations", 700000),
                new Employee(104, "Satya", "IT", 550000),
                new Employee(105, "Priya", "HR", 650000),
                new Employee(106, "Karthik", "operations", 450000),
                new Employee(107, "Meghna", "operations", 900000)
        );

        employeeList.stream().collect(
                Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparing(Employee::getSalary)))
        ).forEach((department, optionalEmployee) -> {
            optionalEmployee.ifPresent(emp -> {
                        System.out.println("Department: " + department + ", Name: " + emp.getName() + ", Salary: " + emp.getSalary());
                    }
            );
        });
    }

    static class Employee {
        private Integer employeeId;
        private String name;
        private String department;
        private Integer salary;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public Integer getSalary() {
            return salary;
        }

        public void setSalary(Integer salary) {
            this.salary = salary;
        }

        public Integer getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(Integer employeeId) {
            this.employeeId = employeeId;
        }

        public Employee(Integer employeeId, String name, String department, Integer salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return Objects.equals(name, employee.name) && Objects.equals(department, employee.department) && Objects.equals(salary, employee.salary);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, department, salary);
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", department='" + department + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}


