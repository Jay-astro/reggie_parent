package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
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
    @AutoFill(type = AutoFillConstant.INSERT)
    void insert(Employee employee);

    /**
     * 分页查询
     * @param pageQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO pageQueryDTO);

    /**
     * 员工状态更改
     * @param status
     * @param id
     */
    void updateStatusById(Integer status, long id);

    /**
     * 查找员工
     * @param id
     * @return
     */
    Employee selectById(Long id);

    /**
     * 员工更新
     * @param employee
     */
    @AutoFill(type = AutoFillConstant.UPDATE)
    void update(Employee employee);
}
