package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.dto.EmployeePageQueryDTO;
import com.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {

    /**
     * 通过用户名查询员工
     * @param username
     * @return
     */
    Employee getByUsername(String username);

    /**
     * 新增员工
     * @param employee
     */
    void insert(Employee employee);

    Page<Employee> pageQuery(EmployeePageQueryDTO pageQueryDTO);
}
