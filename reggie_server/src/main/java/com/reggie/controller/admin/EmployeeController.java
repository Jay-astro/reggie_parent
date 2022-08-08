package com.reggie.controller.admin;

import com.reggie.constant.JwtClaimsConstant;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.entity.Employee;
import com.reggie.properties.JwtProperties;
import com.reggie.result.R;
import com.reggie.service.EmployeeService;
import com.reggie.utils.JwtUtil;
import com.reggie.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * login
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public R<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {

        Employee employee = employeeService.login(employeeLoginDTO);
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());

        //创建JWT令牌
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);

        EmployeeLoginVO empLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .userName(employee.getUsername())
                .token(token)
                .build();

        return R.success(empLoginVO);
    }

    @GetMapping("/testJwt")
    public R<String> testJwt(){
        return R.success("jwt test");
    }


    @PostMapping("/testJwt")
    public R<String> testJwt2(){
        return R.success("jwt test");
    }

}
