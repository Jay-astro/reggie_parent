package com.reggie.service.impl;

import com.reggie.constant.MessageConstant;
import com.reggie.constant.StatusConstant;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.entity.Employee;
import com.reggie.exception.AccountLockedException;
import com.reggie.exception.AccountNotFoundException;
import com.reggie.exception.PasswordErrorException;
import com.reggie.mapper.EmployeeMapper;
import com.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     *
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
}
