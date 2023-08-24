package org.example;

public class Main {
    public static void main(String[] args) {
        EmployeeUploader employeeUploader = new EmployeeUploader();
        employeeUploader.storeDepartmentDetails(1, "Marketing", "John Doe", "Marketing Department Description");
        employeeUploader.storeEmployeeDetails(101, "Alice", "123 Main St", 15000.00, "123-456-7890", 1);
        employeeUploader.showDetails();
    }
}