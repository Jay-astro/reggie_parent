package com.reggie.controller.admin;

import com.reggie.annotation.IgnoreToken;
import com.reggie.constant.JwtClaimsConstant;
import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.dto.EmployeePageQueryDTO;
import com.reggie.dto.PasswordEditDTO;
import com.reggie.entity.Employee;
import com.reggie.properties.JwtProperties;
import com.reggie.result.PageResult;
import com.reggie.result.R;
import com.reggie.service.EmployeeService;
import com.reggie.utils.JwtUtil;
import com.reggie.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * login员工登录
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("员工登录接口")
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

//    @GetMapping("/testJwt")
//    public R<String> testJwt() {
//        return R.success("jwt test");
//    }
//
//
//    @PostMapping("/testJwt")
//    @IgnoreToken
//    public R<String> testJwt2() {
//        return R.success("jwt test");
//    }

    /**
     * 员工退出
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("员工退出接口")
    public R<String> logout() {
        return R.success();
    }

    /**
     * 新增员工
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增员工")
    public R save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工:{}", employeeDTO);
        employeeService.save(employeeDTO);
        return R.success();
    }

    /**
     * 分页查询
     * @param pageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public R<PageResult> page(EmployeePageQueryDTO pageQueryDTO) {
        PageResult pageResult = employeeService.pageQuery(pageQueryDTO);
        return R.success(pageResult);
    }

    /**
     * 员工账号状态变更
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("员工账号状态变更")
    public R<String> allowOrBan(@PathVariable("status") Integer status, long id) {
        log.info("员工账号状态变更");
        employeeService.allowOrBan(status, id);
        return R.success();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询")
    public R<Employee> selectById(@PathVariable("id") Long id) {
        Employee employee = employeeService.selectById(id);
        return R.success(employee);
    }


    /**
     * 编辑员工信息
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("编辑员工信息")
    public R<String> update(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.update(employeeDTO);
        return R.success();
    }

    /**
     * 修改密码
     * @return
     */
    @PutMapping("/editPassword")
    @ApiOperation("修改密码")
    public R<String> editPassword(@RequestBody PasswordEditDTO passwordEditDTO){
        employeeService.editPassword(passwordEditDTO);
        return R.success();
    }
}
