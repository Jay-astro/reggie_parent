package com.reggie.mapper;

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
}
