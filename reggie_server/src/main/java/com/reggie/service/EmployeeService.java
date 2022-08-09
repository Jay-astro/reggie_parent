package com.reggie.service;

import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.dto.EmployeePageQueryDTO;
import com.reggie.entity.Employee;
import com.reggie.result.PageResult;
import com.reggie.result.R;

public interface EmployeeService {

    /**
     * 后台员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 员工分页查询
     * @param pageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO pageQueryDTO);

    /**
     * 员工状态
     * @param status
     * @param id
     */
    void allowOrBan(Integer status, long id);


    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    Employee selectById(Long id);

    void update(EmployeeDTO employeeDTO);
}
