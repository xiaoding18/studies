## spring_Day01

今天是学习spring的第一天,主要学习了以下的知识点

* 组件式开发和依赖问题
* spring的工厂
* 属性注入

#### 组件式开发和依赖问题

一堆P话,总的来说就是java开发的6字真言

`高内聚 低耦合`

如果详细一点的话,那么就是为了减少开发中的过度依赖某个组件的问题(就是没有这个组件你的程序就跑不起来,还不好更换实现类那种),于是,原来解决这个问题的EJB容器的创始人就根据新时代的技术重新开发了一个容器,而这个容器就是spring

#### spring的IOC

IOC,即控制反转(**Inversion of Control**),原来我们获取一个类的对象需要自己new出来,但是这样做将会有很大的耦合(所以IOC就是为了这个?),使用Spring IOC的理念是,当创建对象的时候不需要自行new了,而是将这个操作交给spring来完成,比较大的一个好处就是当换接口的实现类的时候不需要再更改源代码了.

下面将会挑比较容易混淆的知识点来记住

##### ApplicationContext的创建

我们可以通过以下两种方式来创建ApplicationContext对象

> 1. context = new ClassPathXmlApplicationContext(classpath);
> 2. context = new FileSystemXmlApplicationContext(filepath);

第一种方式是通过类路径方式来创建,第二种方式是使用磁盘路径来创建,比较推荐第一种,比较好用

##### BeanFactory和ApplicationContext区别

* BeanFactory是顶层接口,Application接口继承了BeanFactory接口
* BeanFactory接口使用了延迟加载技术,当需要用到这个bean的时候才会真正去加载,而ApplicationContext是立即加载,在创建容器的时候就已经创建好了所有的bean

##### bean标签属性详解

* id

  唯一键

* class

  这个类的全路径

* scope

  创建bean的作用域,其值如下

  * singleton:单例
  * prototype:多例
  * request:一次请求
  * session:一次会话
  * globlesession:全局的会话?

* init-method

  初始化方法(spring将会在构造方法之后调用这个方法)

* destroy-method

  spring容器销毁的时候将会调用这个方法

##### 创建bean的三种方式

* 无参(有参)构造方法

  使用类的无参或者是有参构造来创建对象,最为常用的方式

* 静态工厂

* 实例工厂

以上三种方式中,使用无参(有参)构造最为常用,其他两种使用都比较少,如果需要了解请点击文件末尾的老师笔记

#### 依赖注入

依赖注入简单点说就是当需要一个对象的时候我们不是自己new而是找spring拿

##### 构造方法注入

java代码:

```java
public User(String name,String pass){
	this.name = name;
	this.pass = pass;
}
```

xml代码:

```xml
<bean id="user" class="path">
	<constructor-arg name="name" value="姓名"/>
	<constructor-arg name="pass" value="密码"/>
</bean>
```



##### set方法注入

使用set方法需要有一个该属性的set方法,当spring在创建这个bean 的时候将会自动注入

java代码:

```java
public void setName(String name){this.name = name;}
```

xml代码:

```xml
<bean id="user" class="path">
  	<!--如果值需要引用其他的bean,则需要使用ref属性-->
	<property name="name" value="姓名" ref="otherBean"/>
</bean>
```



##### p名称空间注入

使用P名称空间注入本质上还是使用set方法注入,但是使用的方式稍微容易点

使用P名称空间注入需要导入一个约束,如下

` xmlns:p="http://www.springframework.org/schema/p"`

还是以上面的User类为模板

`<bean id="user" class="path" p:name="姓名" p:pass="密码"/>`

##### 集合属性注入

有时我们的bean是一个集合类型(难道真的有人会注入一个集合?),这时我们可以采用以下方式来注入集合类型

* 详解

  太过SB,如果需要知道请查看老师文档第27页

##### 复杂类型注入

在注入集合类型的基础上引申出了复杂类型注入,具体操作如下

* list数据结构注入

  需要注入的对象

  `private List<User> users;`

  注入方式:

  ```xml
  <property name="userList">
    <list>
      <bean class="study_day01.entity.User">
        <property name="id" value="1"/>
        <property name="name" value="list用户1name"/>
        <property name="sex" value="男"/>
      </bean>
      无限制..
    </list>
  </property>
  ```

* map数据结构注入

  需要注入的对象

  `private Map<String,User> map;`
  注入方式:

  ```xml
  <property name="map">
    <map>
      <entry key="map用户1">
        <bean class="study_day01.entity.User">
          <property name="id" value="1"/>
          <property name="name" value="map用户1name"/>
          <property name="sex" value="男"/>
        </bean>
      </entry>
      还是无限制...
    </map>
  </property>
  ```

#### 老师资料

[老师原版资料](spring_teach_day01.docx)