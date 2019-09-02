package com.bocloud.boms.framework.order.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * 用户控制器类
 * @author gs
 * @create 2019/7/30
 */
@RestController
public class UserController {

    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap());

    public UserController() {
    }

    @ApiOperation(
            value = "获取用户详细信息",
            notes = "根据url的id来获取用户详细信息"
    )
    @ApiImplicitParam(
            name = "id",
            value = "用户ID",
            required = true,
            dataType = "Integer",
            paramType = "path"
    )
    @RequestMapping(
            value = {"user/{id}"},
            method = {RequestMethod.GET}
    )
    public ResponseEntity<JsonResult> getUserById(@PathVariable("id") Integer id) {
        JsonResult r = new JsonResult();

        try {
            User user = (User)users.get(id);
            r.setResult(user);
            r.setStatus("ok");
        } catch (Exception var4) {
            r.setResult(var4.getClass().getName() + ":" + var4.getMessage());
            r.setStatus("error");
            var4.printStackTrace();
        }

        return ResponseEntity.ok(r);
    }

    @ApiOperation(
            value = "获取用户列表",
            notes = "获取用户列表"
    )
    @RequestMapping(
            value = {"users"},
            method = {RequestMethod.GET}
    )
    public ResponseEntity<JsonResult> getUserList() {
        JsonResult r = new JsonResult();

        try {
            List<User> userList = new ArrayList(users.values());
            r.setResult(userList);
            r.setStatus("ok");
        } catch (Exception var3) {
            r.setResult(var3.getClass().getName() + ":" + var3.getMessage());
            r.setStatus("error");
            var3.printStackTrace();
        }

        return ResponseEntity.ok(r);
    }

    @ApiOperation(
            value = "创建用户",
            notes = "根据User对象创建用户"
    )
    @ApiImplicitParam(
            name = "user",
            value = "用户详细实体user",
            required = true,
            dataType = "User"
    )
    @RequestMapping(
            value = {"user"},
            method = {RequestMethod.POST}
    )
    public ResponseEntity<JsonResult> add(@RequestBody User user) {
        JsonResult r = new JsonResult();

        try {
            users.put(user.getId(), user);
            r.setResult(user.getId());
            r.setStatus("ok");
        } catch (Exception var4) {
            r.setResult(var4.getClass().getName() + ":" + var4.getMessage());
            r.setStatus("error");
            var4.printStackTrace();
        }

        return ResponseEntity.ok(r);
    }

    @ApiOperation(
            value = "删除用户",
            notes = "根据url的id来指定删除用户"
    )
    @ApiImplicitParam(
            name = "id",
            value = "用户ID",
            required = true,
            dataType = "Long",
            paramType = "path"
    )
    @RequestMapping(
            value = {"user/{id}"},
            method = {RequestMethod.DELETE}
    )
    public ResponseEntity<JsonResult> delete(@PathVariable("id") Integer id) {
        JsonResult r = new JsonResult();

        try {
            users.remove(id);
            r.setResult(id);
            r.setStatus("ok");
        } catch (Exception var4) {
            r.setResult(var4.getClass().getName() + ":" + var4.getMessage());
            r.setStatus("error");
            var4.printStackTrace();
        }

        return ResponseEntity.ok(r);
    }

    @ApiOperation(
            value = "更新信息",
            notes = "根据url的id来指定更新用户信息"
    )
    @ApiImplicitParams({@ApiImplicitParam(
            name = "id",
            value = "用户ID",
            required = true,
            dataType = "Long",
            paramType = "path"
    ), @ApiImplicitParam(
            name = "user",
            value = "用户实体user",
            required = true,
            dataType = "User"
    )})
    @RequestMapping(
            value = {"user/{id}"},
            method = {RequestMethod.PUT}
    )
    public ResponseEntity<JsonResult> update(@PathVariable("id") Integer id, @RequestBody User user) {
        JsonResult r = new JsonResult();

        try {
            User u = (User)users.get(id);
            u.setUsername(user.getUsername());
            u.setAge(user.getAge());
            users.put(id, u);
            r.setResult(u);
            r.setStatus("ok");
        } catch (Exception var5) {
            r.setResult(var5.getClass().getName() + ":" + var5.getMessage());
            r.setStatus("error");
            var5.printStackTrace();
        }

        return ResponseEntity.ok(r);
    }

    @ApiIgnore
    @RequestMapping(
            value = {"/hi"},
            method = {RequestMethod.GET}
    )
    public String jsonTest() {
        return " hi you!";
    }
}
