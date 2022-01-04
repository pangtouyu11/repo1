package com.lagou.test;

import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    /*
    快速入门方法
     */
    @Test
    public void mybatisQuickStart() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

       //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4.执行sql  参数:statementid:namespace.id
        List<User> users = sqlSession.selectList("userMapper.findAll");

        //5.遍历打印结果
        for (User user : users) {
            System.out.println(user);
        }

        //6.释放资源
        sqlSession.close();
    }

    /*
    测试新增用户
     */
    @Test public void testSave() throws Exception {
        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
         //获取SqlSessionFactory工厂对象
         SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
         //获取SqlSession会话对象
         SqlSession sqlSession = sqlSessionFactory.openSession();
         //执行sql
         User user = new User();
         user.setUsername("jack");
         user.setBirthday(new Date());
         user.setSex("男");
         user.setAddress("北京海淀");
         //sqlSession.insert("userMapper.saveUser", user); // DML语句，手动提交事务
         sqlSession.insert("userMapper.saveUser",user);
         sqlSession.commit();
         //释放资源
         sqlSession.close();
         }
    /*
      测试修改用户
    */
    @Test
    public void testUpdate() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(4);
        user.setUsername("lucy");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("上海");
        int i = sqlSession.update("userMapper.updateUser", user);
        System.out.println(i);
        //手动提交事务
        sqlSession.commit();
        sqlSession.close();
    }
    /*
      测试删除用户
    */
    @Test
    public void testDelete() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        int i = sqlSession.delete("userMapper.deleteUser", 13);
        System.out.println(i);
        //手动提交事务
        sqlSession.commit();
        sqlSession.close();
    }
}
