package com.reggie.service;

import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.entity.Employee;

public interface EmployeeService {

    /**
     * 后台员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);
}
