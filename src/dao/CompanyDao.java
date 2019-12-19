package dao;

import pojo.Company;
import pojo.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao {
    /***
     * 从公司表查询公司号，名字，地址，前台电话，简介。
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Company> findAllCompany() throws SQLException, ClassNotFoundException {
        List<Company> companies = new ArrayList<>(10);
        String sql = "select companyNum,name,location,phoneNum,introduce from company";
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            companies.add(new Company(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
        }
        DBManager.disConnection();
        return companies;
    }

    /***
     * 通过公司号将公司从company表中删除
     * 并把公司下的部门及员工全部移除
     * @param companyNum
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteCompany(String companyNum) throws SQLException, ClassNotFoundException {
        //先删除部门
        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.deleteAllDepartment(companyNum);

        String sql = "delete from company where companyNum=?";
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, companyNum);
        statement.execute();
        statement.close();
        DBManager.disConnection();
    }

    /**
     * 添加公司
     * @param company
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean addCompany(Company company) throws SQLException, ClassNotFoundException {
        //先查询此公司是否已经存在，若是存在则返回false
        if (findCompany(company.getCompanyNum())!=null){
            return false;
        }
        String sql = "insert into company values(?,?,?,?,?)";
        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(sql);

        //设置基本信息
        preparedStatement.setString(1, company.getCompanyNum());
        preparedStatement.setString(2, company.getName());
        preparedStatement.setString(3, company.getLocation());
        preparedStatement.setString(4, company.getPhoneNum());
        preparedStatement.setString(5, company.getIntroduce());
        preparedStatement.execute();
        preparedStatement.close();
        DBManager.disConnection();
        //创建部门后默认创建人事部（部门号固定为RS，名称“人事部”,简介为“人事处理部门”）
        DepartmentDao departmentDao=new DepartmentDao();
        departmentDao.addDepartment(new Department("RS",company.getCompanyNum(),"人事部","人事处理部门"));
        return true;
    }

    /**
     *根据传入的公司号，将与此公司号对应的公司信息取出
     * 并返回一个Company对象
     * @param companyNum
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Company findCompany(String companyNum) throws SQLException, ClassNotFoundException {
        String sql = "select name,location,phoneNum,introduce from company where companyNum=?";
        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,companyNum);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            Company company=new Company(companyNum,resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)
            ,resultSet.getString(4));
            preparedStatement.close();
            return company;
        }
        preparedStatement.close();
        return null;
    }
    public void updateCompany(Company company) throws SQLException, ClassNotFoundException {
        //更新名字、地址、电话、简介

        String sql="update company set name=?,location=?,phoneNum=?,introduce=? where companyNum=?";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        statement.setString(1,company.getName());
        statement.setString(2,company.getLocation());
        statement.setString(3,company.getPhoneNum());
        statement.setString(4,company.getIntroduce());
        statement.setString(5,company.getCompanyNum());
        statement.execute();
        DBManager.disConnection();
    }
}
