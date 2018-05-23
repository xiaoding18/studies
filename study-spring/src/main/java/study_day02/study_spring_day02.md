## Spring_Day02

今天是学习spring的第二天,主要是学习使用注解来配置spring的部分功能,主要是以下两点

* xml和注解混合方式
* 纯注解方式

### spring注解详解

#### 使用注解

如果想要使用spring的注解来配置bean,则可以通过在xml文件中编写以下内容来开启spring的注解扫描

`<context:component-scan base-pachage="这里写需要被扫描的包"/>`

#### 注册bean

##### Component

使用Component可以将一个java类声明为spring中的bean

以下注解都是Component注解衍生出来的注解,在使用上并没有什么不同,但是为了语义表达,于是出现了这三个注解

* Service

  专指service层的对象

* Controller

  专指控制层的对象,通常是使用于servlet上

* Repository

  通常用于持久层的对象上面

##### Scope

生命周期相关

* PreDestroy

  和xml文件中一个bean标签的`init-method`属性相同

* PostConstruct

  和xml文件中一个bean标签的`destroy-method`属性相同

#### 纯注解

虽然感觉这个纯注解模式是真的没什么用,但是还是需要介绍一下,关于spring纯注解的配置需要使用以下的注解

##### Configuration

这个注解不需要使用额外的属性,打在一个类上即表示这个类被声明成了spring的一个配置类

##### ComponentScan

理论上这个注解不是必须的,但是既然是纯注解就不能少了这个注解,这个注解和xml文件中的`context:component-scan` 等价,也是表示spring将会扫描以下的包

#### 属性注入

在将一个bean声明到spring的容器中后,我们可以通过属性注入来动态为java中的变量赋值,下面将会介绍以下两种属性注入的注解使用

##### Autowired

* 注入方式

  使用Autowired将会先根据类型注入,如果含有多个相同的类型,则会再根据变量的名字进行注入(即在容器中查找和这个变量相同的名字的bean),如果还是找不到将会抛出异常

  * ​

##### Qualifierd

* 注入方式

  * 成员变量

    Qualifierd注解在成员变量上需要配合Autowired注解一起使用,不能单独使用.当使用Autowired注解不能唯一确定需要注入的bean时,可以使用Qualifierd注解来指定需要注入这个成员变量的bean

  * 方法参数

    在下面将会讲到一个全新的注解Bean,到时将会再次详细介绍这个注解

##### Resource

* 注入方式

  将会先根据属性的名字进行注入,如果有多个相同名字的bean,则会再根据类型进行注入,如果还是找不到则会抛出异常

  * name

    可以通过name属性来指定需要注入的bean的名字

  * type

    可以通过type属性来指定需要注入的bean的类型

##### Bean

* 注入方式

  如果将Bean注解打在一个方法上,表示这个方法的返回值将会归Spring管理,还可以通过name/value属性来指定归Spring管理的Bean的名字

  ```java
  @Bean("myList")
  public List<String> setList(){}
  ```

  * 带有参数的方法

    这个需要注入的bean需要spring容器中的bean来作为参数?没关系,直接在参数中写这个参数即可,spring将会使用Autowired的方式来为带有Bean注解的方法注入参数,如果和注入成员变量一样出现了问题,即可能会遇到多个可以注入的类型时,可以使用上面的Qualifierd注解来指定这个注入的参数

    ```java
    @Bean
    public List<String> setList
    (@Qualifierd("bean名字") Set<String> set){}
    ```

##### Value

默认情况下,像Autowired注解和Resource注解是不能向基础类型变量注入值的,如果我们需要向基础类型 变量中注入值,则可以通过使用Value注解来注入,如下

```java
@Value("被注入的String")
private String values;
```

当然,这种方式非常的sb,我们其实还可以使用spring来读取配置文件中的数据,然后通过el表达式来动态注入值,如下

xml:

```xml
<context:property-placeholder location="classpath:jdbc.properties"/>
```

纯注解方式:

在spring上的配置类上加入以下代码即可

`@PropertySource("classpath:jdbc.properties")`

在通过spring加载了配置文件之后,我们就可以使用el表达式来获取配置文件中的值了

xml:

```xml
<bean>
  <property name="url" value="${jdbc.url}"/>
  <property name="username" value="${jdbc.username}"/>
  <property name="password" value="${jdbc.password}"/>
  <property name="driverClass" value="${jdbc.driverClass}"/>
</bean>
```

在java类中

```java
@Value("${jdbc.username}")
private String username;
...
```

#### Spring整合Junit注解

如果说每一次我们都需要获取spring 的容器,然后获得对象,那就太麻烦了,spring为我们提供了一种比较方便的方式来进行测试,通过整合junit来实现这个功能

##### Runwith

将这个注解打在需要测试的类上,就可以使用spring整合的单元测试功能了

`@RunWith(SpringJUnit4ClassRunner.class)`

##### ContextConfiguration

除了上述的操作之外,还需要指定spring的配置文件或者是配置类的地址,我们使用这个注解来指定spring的配置地址,可以是配置文件也可以是配置类

```java
@ContextConfiguration(classes=SpringConfiguration.class)
@ContextConfiguration(locations="classpath:bean.xml")
```



