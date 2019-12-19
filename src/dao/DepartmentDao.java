package dao;

import pojo.Company;
import pojo.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {
    /**
     * 根据公司号号，取出此公司下所有的部门数据
     * @param companyNum
     * @return List<Department>
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Department> findAllDepartment(String companyNum) throws SQLException, ClassNotFoundException {
        List<Department> departments = new ArrayList<>(10);
        String sql = "select departmentNum,name,introduce from department where companyNum=?";
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, companyNum);
        ResultSet resultSet = statement.executeQuery();

        //取出信息
        String departmentNum;
        String name;
        String introduce;
        int employeeNum;
        while (resultSet.next()) {
            //取出公司号
            departmentNum = resultSet.getString(1);
            //取出名字
            name = resultSet.getString(2);
            //取出简介
            introduce = resultSet.getString(3);
            //计算部门人数
            employeeNum = countEmployee(companyNum, departmentNum);
            //插入list中
            departments.add(new Department(departmentNum, companyNum, name, introduce, employeeNum));
        }
        statement.close();
        DBManager.disConnection();
        return departments;
    }

    /**
     * 根据公司号与部门号，查询此部门下的所有员工数
     * @param companyNum
     * @param departmentNum
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int countEmployee(String companyNum, String departmentNum) throws SQLException, ClassNotFoundException {
        String sql = "select count(id) from employee where companyNum=? and departmentNum=?";
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,companyNum);
        preparedStatement.setString(2,departmentNum);
        ResultSet set = preparedStatement.executeQuery();
        int num = 0;
        while (set.next()) {
            num = set.getInt(1);
        }
        return num;
    }

    /**
     * 根据公司号删除此公司下的所有部门
     * @param companyNum
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteAllDepartment(String companyNum) throws SQLException, ClassNotFoundException {
        //**删部门之前先删除部门员工
        EmployeeDao employeeDao=new EmployeeDao();
        employeeDao.deleteByCompanyNum(companyNum);

        String sql="delete from department where companyNum=?";
        Connection connection=DBManager.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,companyNum);
        statement.execute();
        statement.close();
        DBManager.disConnection();
    }

    /**
     * 根据传入的公司号及部门号取出这个部门的信息
     * 并返回一个Department对象
     * @param companyNum
     * @param departmentNum
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Department findDepartmentByDNum(String companyNum,String departmentNum) throws SQLException, ClassNotFoundException {
        String sql="select companyNum,name,introduce from department where companyNum=? and departmentNum=?";
        PreparedStatement statement= DBManager.getConnection().prepareStatement(sql);
        statement.setString(1,companyNum);
        statement.setString(2,departmentNum);
        ResultSet set=statement.executeQuery();
        Department department=null;
        while (set.next()){
            department=new Department(departmentNum,set.getString(1),set.getString(2),set.getString(3));
        }
        statement.close();
        DBManager.disConnection();
        return department;
    }

    /**
     * 添加部门根据传入的department对象向department表插入数据
     * @param department
     * @return boolean
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean addDepartment(Department department) throws SQLException, ClassNotFoundException {
        //判断是否存在这个部门若存在则返回false，不向数据库插入数据
        if (findDepartmentByDNum(department.getCompanyNum(),department.getDepartmentNum())!=null){
            return false;
        }
        String sql="insert into department values(?,?,?,?)";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        statement.setString(1,department.getDepartmentNum());
        statement.setString(2,department.getCompanyNum());
        statement.setString(3,department.getName());
        statement.setString(4,department.getIntroduce());
        statement.execute();
        statement.close();
        DBManager.disConnection();;
        return true;
    }
    public void updateDepartment(Department department) throws SQLException, ClassNotFoundException {
        //更新名字及简介。
        String sql="update department set name=?,introduce=? where companyNum=? and departmentNum=?";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        statement.setString(1,department.getName());
        statement.setString(2,department.getIntroduce());
        statement.setString(3,department.getCompanyNum());
        statement.setString(4,department.getDepartmentNum());
        statement.execute();
        statement.close();
        DBManager.disConnection();
    }
    //删除某公司的某部门
    public void deleteDepartment(String companyNum,String departmentNum) throws SQLException, ClassNotFoundException {
        //先删除部门内的人
        EmployeeDao employeeDao=new EmployeeDao();
        employeeDao.deleteByCD(companyNum,departmentNum);
        //删除部门
        String sql="delete from department where companyNum=? and departmentNum=?";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        statement.setString(1,companyNum);
        statement.setString(2,departmentNum);
        statement.execute();
        //关闭连接释放资源
        statement.close();
        DBManager.disConnection();
    }
}
