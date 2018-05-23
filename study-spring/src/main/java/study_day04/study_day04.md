## Spring_Day03

今天主要是介绍了spring中的JdbcTemplate类的使用,以及使用xml和注解配置事务的方法

#### JdbcTemplate

jdbcTemplate类和QueryRunner类一样,都是对于jdbc的薄薄的封装,使用方式大致是一致的,下面将会稍微介绍一下JdbcTemplate类的使用

通常,我们都是将JdbcTemplate对象放在成员变量中,我们有两种方式来使用它

##### 创建bean

不赞成使用配置类(即使用纯注解)的方式来创建bean,推荐使用xml文件的方式来创建,这样更加明了,而如果通过配置类的方式来创建bean,则会感觉太过凌乱

```xml
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <property name="dataSource" ref="dataSource"/>
</bean>
```

可以看到创建JdbcTemplate对象需要一个dataSource的对象,这里我们再创建一个dataSource就行了

* 属性注入

个人感觉这种方式比较好用,只要定义一个成员变量,然后给这个成员变量注入值就可以了,如果这种方式相比下面的方式的话,在注解的环境下要好用一点,如下

```java
@Autowired
private JdbcTemplate jt;
```

* 继承JdbcDaoSupport

在不使用这种注解配置spring的情况下,这种方式要比第一种方式好用,JdbcDaoSupport类中封装了一个JdbcTemplate的成员变量,我们只需要让Dao继承JdbcTemplate,然后在xml文件中定义dao的时候注入值就行了

(因为已经有set方法了)

`public class AccountaDaoImpl extends JdbcDaoSupport`

xml文件中的写法就不再展示了,就和普通的创建bean一样

##### 使用

JdbcTemplate的使用大致上和QueryRunner差不多,但是感觉QueryRunner更加方便一些,因为其封装了更多的得到结果的方法,下面列举一个和QueryRunner中查询不相同的方法,其他的方法都是类似的

```java
JdbcTemplate jt = null;//得到jdbcTemplate
List<Account> list = jt.query(sql, new RowMapper<Account>() {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getDouble("money"));
        return account;//我们只是需要封装这个对象,我搞不懂为什么spring没有封装这一部分内容
    }

});
```

#### Spring支持的事务

在这里,因为要讲事务,需要一些关于事务的基础知识,需要事务基础知识的请点这里

[事务基础知识](www.baidu.com)

##### 基础知识

我们都知道,spring实现了AOP的操作,甚至我们可以使用配置通知的方式来配置事务,虽然能实现,但是太过麻烦,spring已经整合了关于事务的操作,让我们的代码编写更加简单,我们通常使用两种方式配置事务,在实际生活中,使用xml文件和注解方式混合配置的时机比较多

##### xml方式配置

篇幅有限,xml方式配置和注解方式配置将不会再介绍如何配置数据源

```xml
<!-- 配置spring的事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"></property>
</bean>

<!-- 配置事务的通知 -->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <!-- 配置事务的属性 -->
    <tx:attributes>
        <!-- 为匹配规则了的方法添加事务,可以使用通配符 -->
        <!-- 查询事务
       propagation:
        配置事务的扩散性,默认是REQUEIRED,即需要事务,但是这个方法是读取的方法,不需要事务
       read-only:
        配置是否只读,默认值为false,即可以写,我们这里只需要查询,所以使用true
       -->
        <tx:method name="find*" propagation="SUPPORTS" read-only="true"/>
        <!-- 增/删/改就都可以使用默认值了 -->
        <tx:method name="transfer*" propagation="REQUIRED" />

    </tx:attributes>

</tx:advice>

<!-- 配置切点 -->
<aop:config>
    <!-- 这里我们只需要配置切点了
      编写切入点表达式,使其只对service的实现类生效
      -->
    <aop:pointcut expression="execution(* study_day04.service.impl.*.**(..))" id="myPt1"/>
    <!-- 建立切点与事务管理器之间的联系 -->
    <aop:advisor advice-ref="txAdvice" pointcut-ref="myPt1"/>
</aop:config>
```

##### 注解方式配置

可以看到,使用纯xml文件方式需要编写大量的代码,我们尝试使用xml和注解方式结合的方式

* 配置xml文件

  我们还是需要配置事务管理器的,不过我们也只需要在xml文件中编写者一个东西了

  ``` xml
  <!-- 配置spring的事务管理器 -->
  <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
      <property name="dataSource" ref="dataSource"></property>
  </bean>

  <!--开启基于主机的事务
  	在这里,transaction-manager属性的默认值是transactionManager,也就是说我们只要将事务管理器名字配置成transactionManager,那么我们就不用写这个属性
  -->
  <tx:annotation-driven transaction-manager="transactionManager"/>
  ```

* 配置事务类

  * Transactional

    如果这个注解打在方法上,表示这个方法受到事务的支持

    如果这个注释打在类上,则表示类中的所有方法都受到事务的支持

    如果这个注释打在接口上,则表示这个接口中的方法都受到事务的支持

  * Transactional的属性

    我们知道,使用xml文件可以配置事务的具体执行方式,同理,使用这个注解也可以配置事务的具体执行方式,下面将给出一个实例,如果需要其他的事务支持则可以选其他的事务支持

    ```java
    //表示这个类中的所有方法都是使用的默认事务级别,即readOnly=false,propagation=Propagation.REQUIRED
    @Transactional
    public interface AccountService {

        
        //这是个查找的方法,需要用到和默认事务不同的事务
        @Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
        List<Account> findAll();

        //这是一个使用默认事务的方法
        void saveAccount(Account account);
    }
    ```

    当Transactional注解打在方法/类/接口上时,他们的优先级分别是方法>类>接口

##### 纯注解方式配置

感觉纯注解方式配置是真的没有什么必要,在这里将不再编写