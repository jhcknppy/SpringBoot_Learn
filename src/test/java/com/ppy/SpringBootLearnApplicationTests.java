package com.ppy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ppy.entity.User;
import com.ppy.mapper.UserMapper;
import com.ppy.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaQuery;
import static com.baomidou.mybatisplus.core.toolkit.Wrappers.query;

@SpringBootTest
class SpringBootLearnApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(7 == userList.size(), "");
        userList.forEach(System.out::println);
    }

    @Test
    public void testSaveBatch() {


        System.out.println("=======insert method test============");
        List<User> userList = Arrays.asList(
                new User(null, "zhangsan", 22, "zhangsan@163.com"),
                new User(null, "lisi", 24, "lisi@163.com"),
                new User(null, "wangwu", 26, "wangwu@163.com"),
                new User(null, "zhaoliu", 27, "zhaoliu@163.com")
        );
        boolean result = userService.saveBatch(userList, 2);
        if (result) {
            System.out.println("插入成功...");
        } else {
            System.out.println("插入失败");
        }

    }

    @Test
    public void testSaveOrUpdate() {
        System.out.println("=======saveOrUpdate method test============");
        User user = new User();
        user.setId(1L);
        user.setName("zhangsan");
        user.setAge(22);
        user.setEmail("zhangsan@163.com");
        boolean result = userService.saveOrUpdate(user);
        if (result) {
            System.out.println("更新/插入成功...");
        }
    }


    @Test
    public void testRemove() {
        System.out.println("=======remove method test============");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "lisi");
        boolean result = userService.remove(queryWrapper);
        if (result) {
            System.out.println("删除成功...");
        } else {
            System.out.println("删除失败...");
        }
    }


    @Test
    public void getUser() {
        // 假设有一个 QueryWrapper 对象，设置查询条件为 name = 'John Doe'，并将结果转换为 String
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "wangwu");
        Map<String, Object> userMap = userService.getMap(queryWrapper); // 调用 getMap 方法
        if (userMap != null) {
            System.out.println("User found: " + userMap);
        } else {
            System.out.println("User not found.");
        }

    }


    @Test
    public void getOne() {
        // 假设有一个 QueryWrapper 对象，设置查询条件为 name = 'John Doe'，并且不抛出异常
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "zhaoliu");
        User user = userService.getOne(queryWrapper, false); // 调用 getOne 方法
        if (user != null) {
            System.out.println("User found: " + user);
        } else {
            System.out.println("User not found.");
        }
    }

    @Test
    public void testList() {
        // 假设有一个 QueryWrapper 对象，设置查询条件为 age > 25
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25);// 大于25
        List<User> users = userService.list(queryWrapper); // 调用 list 方法
        for (User user : users) {
            System.out.println("User: " + user);
        }
    }

    @Test
    public void testListByIds() {
        // 假设有一组 ID 列表，批量查询用户
        List<Integer> ids = Arrays.asList(1, 2, 3);
        Collection<User> users = userService.listByIds(ids); // 调用 listByIds 方法
        for (User user : users) {
            System.out.println("User: " + user);
        }
    }

    @Test
    public void testListByMap() {
        // 假设有一个 columnMap，设置查询条件为 age = 30
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("age", 26);
        Collection<User> users = userService.listByMap(columnMap); // 调用 listByMap 方法
        for (User user : users) {
            System.out.println("User: " + user);
        }
    }

    @Test
    public void testListMap() {
        // 查询所有用户，并将结果映射为 Map
        List<Map<String, Object>> userMaps = userService.listMaps(); // 调用 listMaps 方法
        for (Map<String, Object> userMap : userMaps) {
            System.out.println("User Map: " + userMap);
        }
    }

    @Test
    public void testListMaps() {
        // 假设有一个 QueryWrapper 对象，设置查询条件为 age > 25
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25);
        List<Map<String, Object>> userMaps = userService.listMaps(queryWrapper); // 调用 listMaps 方法
        for (Map<String, Object> userMap : userMaps) {
            System.out.println("User Map: " + userMap);
        }
    }

    @Test
    public void testListObjs() {
        // 查询所有用户，并将结果转换为 String 列表
        List<String> userNames = userService.listObjs(obj -> ((User) obj).getName()); // 调用 listObjs 方法
        for (String userName : userNames) {
            System.out.println("User Name: " + userName);
        }

    }

    @Test
    public void testListObjs2(){
        // 假设有一个 QueryWrapper 对象，设置查询条件为 age > 25，并将结果转换为 String 列表
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25);
        List<String> userNames = userService.listObjs(queryWrapper, obj -> ((User) obj).getName()); // 调用 listObjs 方法
        for (String userName : userNames) {
            System.out.println("User Name: " + userName);
        }
    }


    @Test
    public void testPage(){
        // 假设要进行无条件的分页查询，每页显示10条记录，查询第1页
        IPage<User> page = new Page<>(1, 10);
        IPage<User> userPage = userService.page(page); // 调用 page 方法
        List<User> userList = userPage.getRecords();
        long total = userPage.getTotal();
        System.out.println("Total users: " + total);
        for (User user : userList) {
            System.out.println("User: " + user);
        }
    }

    @Test
    public void testPage2(){
        // 假设有一个 QueryWrapper 对象，设置查询条件为 age > 25，进行有条件的分页查询
        IPage<User> page = new Page<>(1, 10);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25);
        IPage<User> userPage = userService.page(page, queryWrapper); // 调用 page 方法
        List<User> userList = userPage.getRecords();
        long total = userPage.getTotal();
        System.out.println("Total users (age > 25): " + total);
        for (User user : userList) {
            System.out.println("User: " + user);
        }
    }

    @Test
    public void testPageMaps(){
        // 假设要进行无条件的分页查询，并将结果映射为 Map，每页显示10条记录，查询第1页
        IPage<Map<String, Object>> page = new Page<>(1, 10);
        IPage<Map<String, Object>> userPageMaps = userService.pageMaps(page); // 调用 pageMaps 方法
        List<Map<String, Object>> userMapList = userPageMaps.getRecords();
        long total = userPageMaps.getTotal();
        System.out.println("Total users: " + total);
        for (Map<String, Object> userMap : userMapList) {
            System.out.println("User Map: " + userMap);
        }
    }


    @Test
    public void testPageWrapper(){
        // 假设有一个 QueryWrapper 对象，设置查询条件为 age > 25，进行有条件的分页查询，并将结果映射为 Map
        IPage<Map<String, Object>> page = new Page<>(1, 10);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25);
        IPage<Map<String, Object>> userPageMaps = userService.pageMaps(page, queryWrapper); // 调用 pageMaps 方法
        List<Map<String, Object>> userMapList = userPageMaps.getRecords();
        long total = userPageMaps.getTotal();
        System.out.println("Total users (age > 25): " + total);
        for (Map<String, Object> userMap : userMapList) {
            System.out.println("User Map: " + userMap);
        }
    }


    @Test
    public void testCount(){
        // 查询用户表中的总记录数
        long totalUsers = userService.count(); // 调用 count 方法
        System.out.println("Total users: " + totalUsers);
    }

    @Test
    public void testCount2(){
        // 假设有一个 QueryWrapper 对象，设置查询条件为 age > 25，查询满足条件的用户总数
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25);
        long totalUsers = userService.count(queryWrapper); // 调用 count 方法
        System.out.println("Total users (age > 25): " + totalUsers);
    }


    @Test
    public void testInsert(){
        User user = new User(null,"aaa",20,"aaa@example.com");
        int count = userMapper.insert(user);
        if(count > 0){
            System.out.println("Insert Success");
        }else{
            System.out.println("Insert Failed");
        }
    }

    @Test
    public void testDelete(){
        // 假设有一个 QueryWrapper 对象，设置查询条件为 age > 25，删除满足条件的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25);
        int rows = userMapper.delete(queryWrapper); // 调用 delete 方法
        if (rows > 0) {
            System.out.println("Users deleted successfully.");
        } else {
            System.out.println("No users deleted.");
        }
    }


    @Test
    public void testDeleteByIds(){
        // 假设有一组 ID 列表，批量删除用户
        List<Integer> ids = Arrays.asList(1, 2, 3);
        int rows = userMapper.deleteBatchIds(ids); // 调用 deleteBatchIds 方法
        if (rows > 0) {
            System.out.println("Users deleted successfully.");
        } else {
            System.out.println("No users deleted.");
        }
    }


    @Test
    public void testDeleteById(){
        int userId = 4;
        int rows = userMapper.deleteById(userId);
        if(rows > 0){
            System.out.println("Delete Success");
        }else{
            System.out.println("Delete Failed");
        }
    }

    @Test
    public void testDeleteByMap(){
        // 假设有一个 columnMap，设置查询条件为 age = 30，删除满足条件的用户
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("age", 20);
        int rows = userMapper.deleteByMap(columnMap); // 调用 deleteByMap 方法
        if (rows > 0) {
            System.out.println("Users deleted successfully.");
        } else {
            System.out.println("No users deleted.");
        }
    }

    @Test
    public void testUpdate(){
        // 假设有一个 UpdateWrapper 对象，设置查询条件为 age > 25，更新满足条件的用户的邮箱
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.gt("age", 25);
        User updateUser = new User();
        updateUser.setEmail("new.email@example.com");
        int rows = userMapper.update(updateUser, updateWrapper); // 调用 update 方法
        if (rows > 0) {
            System.out.println("Users updated successfully.");
        } else {
            System.out.println("No users updated.");
        }
    }

    @Test
    public void testUpdateById(){
        // 假设要更新 ID 为 1 的用户的邮箱
        User updateUser = new User();
        updateUser.setId(1L);
        updateUser.setEmail("new.email@example.com");
        int rows = userMapper.updateById(updateUser); // 调用 updateById 方法
        if (rows > 0) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("No user updated.");
        }
    }

    @Test
    public void testSelectById(){
        // 根据 ID 查询单个用户
        int userId = 1;
        User user = userMapper.selectById(userId); // 调用 selectById 方法
        System.out.println("User: " + user);
    }

    @Test
    public void testSelectOne(){
        // 假设有一个 QueryWrapper 对象，设置查询条件为 age > 25，查询一条满足条件的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25).last("limit 1");
        User user = userMapper.selectOne(queryWrapper); // 调用 selectOne 方法
        System.out.println("User: " + user);
    }

    @Test
    public void testSelectIds(){
        // 假设有一组 ID 列表，批量查询用户
        List<Integer> ids = Arrays.asList(1, 2, 3);
        List<User> users = userMapper.selectBatchIds(ids); // 调用 selectBatchIds 方法
        for (User u : users) {
            System.out.println("User: " + u);
        }
    }

    @Test
    public void testSelectList(){
        // 假设有一个 QueryWrapper 对象，设置查询条件为 age > 25，查询所有满足条件的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25);
        List<User> users = userMapper.selectList(queryWrapper); // 调用 selectList 方法
        for (User u : users) {
            System.out.println("User: " + u);
        }
    }

    @Test
    public void testSelectMaps(){
        // 假设有一个 QueryWrapper 对象，设置查询条件为 age > 25，查询所有满足条件的用户，并将结果映射为 Map
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25);
        List<Map<String, Object>> userMaps = userMapper.selectMaps(queryWrapper); // 调用 selectMaps 方法
        for (Map<String, Object> userMap : userMaps) {
            System.out.println("User Map: " + userMap);
        }
    }

    @Test
    public void testSelectObjs(){
        // 假设有一个 QueryWrapper 对象，设置查询条件为 age > 25，查询所有满足条件的用户，但只返回每个记录的第一个字段的值
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25);
        List<Object> userIds = userMapper.selectObjs(queryWrapper); // 调用 selectObjs 方法
        for (Object userId : userIds) {
            System.out.println("User ID: " + userId);
        }
    }

    @Test
    public void testSelectPage(){
        // 假设要进行分页查询，每页显示10条记录，查询第1页，查询条件为 age > 25
        IPage<User> page = new Page<>(1, 10);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25);
        IPage<User> userPage = userMapper.selectPage(page, queryWrapper); // 调用 selectPage 方法
        List<User> userList = userPage.getRecords();
        long total = userPage.getTotal();
        System.out.println("Total users (age > 25): " + total);
        for (User user : userList) {
            System.out.println("User: " + user);
        }
    }


    @Test
    public void query1(){
        userService.query().eq("name","John").list();
        userService.lambdaQuery().eq(User::getAge, 30).one();
    }


}
