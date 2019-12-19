package dao;

import pojo.Employee;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class EmployeeDao {
    public void deleteByCompanyNum(String companyNum) throws SQLException, ClassNotFoundException {
        String sql = "delete from employee where companyNum=?";
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, companyNum);
        statement.execute();
        statement.close();
        DBManager.disConnection();
    }

    //删除某公司某部门的所有员工
    public void deleteByCD(String companyNum, String departmentNum) throws SQLException, ClassNotFoundException {
        String sql = "delete from employee where companyNum=? and departmentNum=?";
        PreparedStatement statement = DBManager.getConnection().prepareStatement(sql);
        statement.setString(1, companyNum);
        statement.setString(2, departmentNum);
        statement.execute();
        statement.close();
        DBManager.disConnection();
    }

    public boolean addEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        //先查询是否有此用户若无用户则返回false
        UserDao userDao = new UserDao();
        if (!userDao.findUserById(employee.getId())) {
            return false;
        }
        //向雇员表插入雇员ID,title,departmentNum,companyNum,wages;
        String sql = "insert into employee values(?,?,?,?,?,?)";
        PreparedStatement statement = DBManager.getConnection().prepareStatement(sql);
        statement.setString(1, employee.getId());
        statement.setString(2, employee.getTitle());
        statement.setString(3, employee.getDepartmentNum());
        statement.setString(4, employee.getCompanyNum());
        statement.setDate(5, employee.getHireDate());
        statement.setDouble(6, employee.getWages());
        statement.execute();
        //释放资源
        statement.close();
        DBManager.disConnection();
        return true;
    }

    public Employee findEmployeeByID(String id) throws SQLException, ClassNotFoundException {
        //调用用户DAO以获取真实姓名
        UserDao userDao = new UserDao();
        String realName = null;
        realName = userDao.getUserRealNameById(id);

        String sql = "select departmentNum,companyNum,title,hireDate,wages from employee where id=?";
        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet set = preparedStatement.executeQuery();
        Employee employee = null;
        while (set.next()) {
            employee = new Employee(id, realName, set.getString(3), set.getString(1), set.getString(2),
                    set.getDate(4), set.getDouble(5));
        }
        return employee;
    }

    //根据公司号和部门号查询所有员工
    public List<Employee> findEmployeeByCD(String companyNum, String departmentNum) throws SQLException, ClassNotFoundException {
        List<Employee> employeeList = new ArrayList<>(50);
        String sql = "select id,title,hireDate,wages from employee where companyNum=? and departmentNum=?";
        PreparedStatement statement = DBManager.getConnection().prepareStatement(sql);
        statement.setString(1, companyNum);
        statement.setString(2, departmentNum);
        return getEmployees(companyNum, departmentNum, employeeList, statement);
    }

    //根据员工id更新职位、薪资、入职时间
    public void updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
         String sql="update employee set title=?,wages=?,hireDate=? where id=?";
         PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
         statement.setString(1,employee.getTitle());
         statement.setDouble(2,employee.getWages());
         statement.setDate(3,employee.getHireDate());
         statement.setString(4,employee.getId());
         statement.execute();
         statement.close();
         DBManager.disConnection();
    }

    //根据公司号、部门号、id从数据库删除此员工
    public void deleteByCDI(String companyNum,String departmentNum,String id) throws SQLException, ClassNotFoundException {
        String sql="delete from employee where companyNum=? and departmentNum=? and id=?";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        statement.setString(1,companyNum);
        statement.setString(2,departmentNum);
        statement.setString(3,id);
        statement.execute();
        statement.close();
        DBManager.disConnection();
    }

    public List<Employee> findEmployeeByCD(String companyNum, String departmentNum, int start) throws SQLException, ClassNotFoundException {
        List<Employee> employeeList = new ArrayList<>(20);
        String sql = "select id,title,hireDate,wages from employee where companyNum=? and departmentNum=? limit ?,?";
        PreparedStatement statement = DBManager.getConnection().prepareStatement(sql);
        statement.setString(1, companyNum);
        statement.setString(2, departmentNum);
        statement.setInt(3,start);
        statement.setInt(4,20);
        return getEmployees(companyNum, departmentNum, employeeList, statement);
    }

    private List<Employee> getEmployees(String companyNum, String departmentNum, List<Employee> employeeList, PreparedStatement statement) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = statement.executeQuery();
        String title;
        String id;
        String realName;
        double wages;
        Date date;
        UserDao userDao = new UserDao();
        while (resultSet.next()) {
            id = resultSet.getString(1);
            realName = userDao.getUserRealNameById(id);
            title = resultSet.getString(2);
            date = resultSet.getDate(3);
            wages = resultSet.getDouble(4);
            employeeList.add(new Employee(id, realName, title, departmentNum, companyNum, date, wages));
        }
        statement.close();
        DBManager.disConnection();
        return employeeList;
    }

    public boolean isEmployee(String id) throws SQLException, ClassNotFoundException {
        String sql = "select count(1) from employee where id=?";
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        return isExist(statement);
    }

    private boolean isExist(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getInt(1) > 0) {
                DBManager.disConnection();
                return true;
            }
        }
        statement.close();
        DBManager.disConnection();
        return false;
    }
}
