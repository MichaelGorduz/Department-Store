package org.example;

import java.sql.*;

public class EmployeeUploader {
    private static Connection con;

    public EmployeeUploader() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/departmentstore", "root", "yourpassword");
            System.out.println("Data connected");
            /*
             * jdbc-protocol
             * mysql-sub protocol
             * localhost-address of mysql
             * 3306-port number of mysql
             */
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public void storeDepartmentDetails(int departmentId, String departmentName, String departmentHead, String description) {
        try {
            Connection connection = getConnection();
            String sql = "INSERT INTO Department VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, departmentId);
            preparedStatement.setString(2, departmentName);
            preparedStatement.setString(3, departmentHead);
            preparedStatement.setString(4, description);
            preparedStatement.executeUpdate();
            System.out.println("Department details stored successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void storeEmployeeDetails(int employeeId, String employeeName, String employeeAddress,
                                     double employeeSalary, String employeeContactNo, int departmentId) {
        try {
            Connection connection = getConnection();
            String sql = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setString(2, employeeName);
            preparedStatement.setString(3, employeeAddress);
            preparedStatement.setDouble(4, employeeSalary);
            preparedStatement.setString(5, employeeContactNo);
            preparedStatement.setInt(6, departmentId);
            preparedStatement.executeUpdate();
            System.out.println("Employee details stored successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showDetails() {
        Connection connection = getConnection();
        String sql = "SELECT * FROM Employee";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("EmployeeId");
                String employeeName = resultSet.getString("EmployeeName");
                String employeeAddress = resultSet.getString("EmployeeAddress");
                double employeeSalary = resultSet.getDouble("EmployeeSalary");
                String employeeContactNo = resultSet.getString("EmployeeContactNumber");
                int departmentId = resultSet.getInt("DepartmentId");

                System.out.println("Employee ID: " + employeeId);
                System.out.println("Employee Name: " + employeeName);
                System.out.println("Employee Address: " + employeeAddress);
                System.out.println("Employee Salary: " + employeeSalary);
                System.out.println("Employee Contact Number: " + employeeContactNo);
                System.out.println("Department ID: " + departmentId);
                System.out.println("-----------------------------------------");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
