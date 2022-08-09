package com.reggie.service.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.reggie.constant.MessageConstant;
import com.reggie.constant.PasswordConstant;
import com.reggie.constant.StatusConstant;
import com.reggie.context.BaseContext;
import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.dto.EmployeePageQueryDTO;
import com.reggie.entity.Employee;
import com.reggie.exception.AccountLockedException;
import com.reggie.exception.AccountNotFoundException;
import com.reggie.exception.PasswordErrorException;
import com.reggie.mapper.EmployeeMapper;
import com.reggie.result.PageResult;
import com.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        Employee employee = employeeMapper.getByUsername(username);

        if (employee == null){
            //用户名不存在
            throw  new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }if (employee.getStatus() == StatusConstant.DISABLE){
            //账号被锁定
            throw  new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //数据加密
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!pwd.equals(employee.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        return employee;
    }

    /**
     * 添加员工
     * @param employeeDTO
     */
    @Override
    public void save(EmployeeDTO employeeDTO) {

        System.out.println("service:" + Thread.currentThread().getId());

        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDTO,employee);

        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setCreateUser(BaseContext.getCurrentId());
//        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.insert(employee);
    }

    /**
     * 分页查询
     * @param pageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(EmployeePageQueryDTO pageQueryDTO) {
        //基于PageHelper插件实现分页查询
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<Employee> page =  employeeMapper.pageQuery(pageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 员工状态
     * @param status
     * @param id
     */
    @Override
    public void allowOrBan(Integer status, long id) {
        employeeMapper.updateStatusById(status,id);
    }

    /**
     * 查询员工
     * @param id
     * @return
     */
    @Override
    public Employee selectById(Long id) {
        return employeeMapper.selectById(id);
    }

    /**
     * 员工信息更新
     * @param employeeDTO
     */
    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);

//        employee.setUpdateUser(BaseContext.getCurrentId());
//        employee.setUpdateTime(LocalDateTime.now());

        employeeMapper.update(employee);

    }


}
