# Spring 5

## spring概念

>开源的J2EE应用框架，是对Bean生命周期进行管理的轻量级(小)的框架，解决企业应用开发的复杂性,
>
>提供了强大的IOC，AOP，MVC等
>
>IOC
>
>>控制反转，把创建对象的过程交给Spring进行管理
>
>AOP
>
>>面向切面，不修改源代码的情况下进行功能的增强
>
>特点
>
>>方便解耦，简化开发，AOP对编程的支持，方便程序的测试，方便集成各种优秀的框架降低JavaEE的使用难度，方便进行事务的操作。Spring源码经典学习规范。
>
>入门案例
>
>>版本选取 5.2.6 ，学习时官网上最新是 5.2.7 
>>
>>Spring模块视图如下
>>
>>![选区_001](/home/lxl/Pictures/选区_001.png)

## IOC容器

### ioc 底层原理

>>概念
>>
>>>面向对象编程中的一种设计原则，可以用来降低代码之间的耦合度。其中最常见的方式是依赖注入，通过控制反转，对象在被创建时，由一个调控系统内对象的外界实体将其所依赖的对象的引用传递给它。可以说，依赖被注入到对象中。
>>
>>IOC底层原理
>>
>>>基本技术：xml解析，工厂模式，反射(通过class文件操作对象)
>>>
>>>传统方法代码耦合度太高
>>>
>>>![选区_002](/home/lxl/Pictures/选区_002.png)
>>>
>>>工厂模式解耦：对象通过工厂创建，需要该对象时直接从工厂里面取，但工厂还具有耦合度
>>>
>>>![选区_003](/home/lxl/Pictures/选区_003.png)
>>>
>>>通过xml解析拿到class属性，根据class属性反射创建类对象
>>>
>>>IOC过程
>>>
>>>>编辑xml配置文件，配置类的信息
>>>>
>>>>通过解析xml文件得到class属性
>>>>
>>>>通过反射拿到class对象
>>>>
>>>>通过class对象创建实例
>>>
>>>IOC(接口)
>>>
>>>>1. IOC思想给予IOC容器完成，IOC容器底层就是对象工厂
>>>>2. Spring提供IOC容器的俩种实现方式：（俩个接口），实现加载配置文件反射创建对象
>>>>
>>>>>BeanFactory
>>>>>
>>>>>>IOC容器的基本实现方式,spring内部使用的接口
>>>>>>
>>>>>>懒加载
>>>>>>
>>>>>>**加载配置文件时候不会创建对象，在获取对象时才去创建对象**
>>>>>
>>>>>ApplicationContext
>>>>>
>>>>>>BeanFactory的子接口，提供了更多更强大的功能，面向开发人员进行使用
>>>>>>
>>>>>>不懒加载
>>>>>>
>>>>>>**在加载配置文件时候就会创建好配置文件的对象**
>>>>>>
>>>>>>ctrl+h 查看实现类
>>>>>>
>>>>>>FileSystemXmlApplication
>>>>>>
>>>>>>ClassPathXmlApplicationConttext

### ioc 操作 Bean 管理(xml/注解)

>>什么是bean管理
>>
>>>Bean管理指的是俩个操作，Spring创建对象，Spring注入属性
>>
>>bean管理操作的俩种方式

#### 基于xml配置文件方式实现

>>>>在spring里添加bean标签，在bean标签里添加对应的属性
>>>>
>>>>属性
>>>>
>>>>>id：唯一标示
>>>>>
>>>>>class：类的全路径
>>>>>
>>>>>name：和id一样，name属性可以加特殊符号
>>>>
>>>>创建对象时候，默认执行无参构造方法
>>>>
>>>>DI(依赖注入)是IOC(控制反转)的一种具体实现
>>>>
>>>>>set方法注入：创建类，定义属性和对应的set方法
>>>>>
>>>>>>通过配置property标签来配置使用setter方法注入
>>>>>
>>>>>有参构造器注入：参数传给构造器
>>>>>
>>>>>>通过constutor-arg来设置构造器注入
>>>>
>>>>xml其他属性注入
>>>
>>>基于注解方式实现
>>>
>>>创建service和dao层，service里调dao
>>>
>>>在xml配置里将 service 里注入 dao 的 impl
>>
>>ioc注入List和Map
>>
>>>```xml
>>><bean id="course" class="com.woqiyounai.ioc.demo4.Course">
>>><property name="stuList">
>>>   <list>
>>>       <ref bean="stu1"></ref>
>>>       <ref bean="stu2"></ref>
>>>   </list>
>>></property>
>>></bean>
>>>```
>>
>>提取注入部分：命名空间全部改成 util
>>
>>通过 util 标签引入
>>
>>IOC操作Bean管理
>>
>>>spring有俩种类型的bean，一种普通bean，一种factory bean。
>>>
>>>普通bean，在配置文件中定义bean的类型就是返回类型
>>>
>>>>
>>>
>>>工厂bean
>>>
>>>>创建类，让这个类作为工厂bean，实现接口FactoryBean
>>>>
>>>>实现接口里面的方法
>>>>
>>>>```java
>>>>public class MyBean implements FactoryBean<Course> {
>>>>
>>>>//通过该方法来设置返回类型
>>>>public Course getObject() throws Exception {
>>>>   Course course = new Course();
>>>>   ArrayList<Stu> stus = new ArrayList<Stu>();
>>>>   course.setStuList(stus);
>>>>   return course;
>>>>}
>>>>
>>>>public Class<?> getObjectType() {
>>>>   return null;
>>>>}
>>>>
>>>>//决定返回对象是单实例对象还是多实例对象
>>>>public boolean isSingleton() {
>>>>   return false;
>>>>}
>>>>}
>>>>```
>>>>
>>>>```java
>>>>@Test
>>>>public void testFactory(){
>>>>ApplicationContext context = new ClassPathXmlApplicationContext("classpath:factory.xml");
>>>>//返回类型可以和定义类型不一致
>>>>Course course = context.getBean("myBean", Course.class);
>>>>course.test();
>>>>}
>>>>```
>>>>
>>>>定义类型是MyBean，返回类型是Course
>>>
>>>bean的作用域与生命周期
>>>
>>>>作用域：
>>>>
>>>>在spring里面可以设置创建bean的实例是单实例还是多实例
>>>>
>>>>可以在 FactoryBean 或者 bean 标签里面的 scope(**singleton**(default,单实例，在加载配置文件时候就会创建),**prototype**(在执行getBean方法时候才进行创建)) 里面设置
>>>>
>>>>生命周期：从对象创建到销毁的过程
>>>>
>>>>1. 创建实例 （无参数构造）
>>>>2. 为bean里面的属性设置值和对其他bean的引用（调用set方法）
>>>>3. 调用 bean 的初始化的方法 （需要进行配置）
>>>>4. bean 可以使用了 （对象获取到了）
>>>>5. 当容器关闭的时候，调用 bean 的销毁的方法 （需要进行配置销毁的方法）
>>>>
>>>>```xml
>>>><!--演示 bean 的生命周期
>>>>无参构造器
>>>>setter 方法
>>>>
>>>>初始化方法 (自己配置)
>>>>
>>>>bean可以使用
>>>>销毁方法 (自己配置,需要手动销毁)
>>>>-->
>>>><bean id="orders" class="com.woqiyounai.ioc.demo5.Orders" 
>>>>     init-method="init"
>>>>     destroy-method="destroy">
>>>>   <!--通过无参数构造先创建后 setter-->
>>>>   <property name="oname" value="手机"></property>
>>>></bean>
>>>>```
>>>>
>>>>除此之外，还有俩步会在第三步前后调用，需要加 bean 的后置处理器
>>>>
>>>><<把 bean 实例传递 bean 后置处理器的方法>>
>>>>
>>>>```java
>>>>public class MyBeanPost implements BeanPostProcessor {
>>>>   //创建对象前
>>>>   public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
>>>>       System.out.println("在初始化执行之前执行的方法:--------"+beanName);
>>>>       return bean;
>>>>   }
>>>>
>>>>   //创建对象后
>>>>   public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
>>>>       System.out.println("在初始化执行之后执行的方法:--------"+beanName);
>>>>       return bean;
>>>>   }
>>>>}
>>>>```
>>>>
>>>>完整的
>>>>
>>>>>无参构造
>>>>>调用了 setter 方法
>>>>>后置处理器:--------orders
>>>>>执行初始化方法
>>>>>后置处理器:--------orders
>>>>>com.woqiyounai.ioc.demo5.Orders@7403c468
>>>>>调用了销毁方法
>>>
>>>Ioc操作bean管理
>>>
>>>>什么是自动装配
>>>>
>>>>> 根据指定装配规则（属性名称或者属性类型），Spring自动将匹配的属性值进行注入
>>>>>
>>>>> 手动装配
>>>>>
>>>>> >```xml
>>>>> ><bean id="emp" class="com.woqiyounai.ioc.demo6.Emp">
>>>>> ><property name="dept" ref="dept"></property>
>>>>> ></bean>
>>>>> ><bean id="dept" class="com.woqiyounai.ioc.demo6.Dept"></bean>
>>>>> >```
>>>>> >
>>>>> >通过 ref 属性注入
>>>>>
>>>>> 自动装配
>>>>>
>>>>> >使用 autowired 属性值，autowired有俩个值 byName , byType
>>>>> >
>>>>> >byName属性值 id 要和属性名称一样
>>>>> >
>>>>> >byType  根据类型注入
>>>>
>>>>引入外部的属性文件
>>>>
>>>>>将固定的值放在另外一个配置文件中
>>>>>
>>>>>配置连接池 demo
>>>>>
>>>>>>```xml
>>>>>><!--直接引入 druid 配置-->
>>>>>><!--
>>>>>>把外部prop属性文件引入到spring配置文件中
>>>>>>引入 context 名称空间
>>>>>>引入外部属性文件
>>>>>>-->
>>>>>><context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
>>>>>>
>>>>>><bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
>>>>>><!--        <property name="username" value="root"></property>-->
>>>>>><!--        <property name="password" value="root"></property>-->
>>>>>><!--        <property name="url"-->
>>>>>><!--                  value="jdbc:mysql://127.0.0.1:3306/blog?characterEncoding=UTF-8"></property>-->
>>>>>><!--        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"></property>-->
>>>>>>   <property name="username" value="${prop.username}"></property>
>>>>>>   <property name="password" value="${prop.password}"></property>
>>>>>>   <property name="url"
>>>>>>             value="${prop.url}"></property>
>>>>>>   <property name="driverClassName" value="${prop.driverName}"></property>
>>>>>></bean>
>>>>>>```

#### 注解方式 Bean管理

>什么是注解
>
>>注解是代码里一些特殊的标记   格式 : @注解名称(属性名称=属性值)
>>
>>注解可以作用在类，方法，属性上
>>
>>简化 xml 配置
>
>bean管理注解
>
>>@Compontent
>>
>>@Service
>>
>>@Controller
>>
>>@Repository (持久层)
>>
>>这四个注解功能是一样的，都可以用来创建 bean 实例
>
>使用步骤
>
>>使用注解前需要引入 spring-aop 依赖
>>
>>```xml
>><!--引入 aop 依赖-->
>><dependency>
>><groupId>org.springframework</groupId>
>><artifactId>spring-aop</artifactId>
>><version>5.2.6.RELEASE</version>
>></dependency>
>>```
>>
>>开启组件扫描
>>
>>```xml
>><?xml version="1.0" encoding="UTF-8"?>
>><beans xmlns="http://www.springframework.org/schema/beans"
>>  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>>  xmlns:context="http://www.springframework.org/schema/context"
>>  xsi:schemaLocation="
>>  http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
>>  http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
>>">
>><!--开启扫描-->
>><context:component-scan base-package="com.woqiyounai.ioc.annotation"></context:component-scan>
>></beans>
>>```
>>
>>创建类
>>
>>```java
>>@Test
>>public void testService(){
>>ApplicationContext context =
>>       new ClassPathXmlApplicationContext("classpath:ioc/annotation/scan.xml");
>>UserService userService = context.getBean("userService", UserService.class);
>>System.out.println(userService);
>>}
>>```
>
>基于注解方式实现属性注入
>
>>@Autowired ：根据属性类型进行注入
>>
>>>根据属性类型进行自动装配，先把service和dao类添加创建对象注解
>>>
>>>在service里添加 autoeired 注解就可以
>>
>>@Qualifier ：根据属性名称注入
>>
>>> 这个要和 @Autowired 一起使用
>>>
>>> 当一个接口有多个实现类的时候，根据名称查找具体的实现类
>>>
>>> ```java
>>> @Repository("UserDaoImplCopy")
>>> public class UserDaoImplCopy implements UserDao{
>>>  public void add() {
>>>      System.out.println("UserDaoImplCopy add.......................");
>>>  }
>>> }
>>> ```
>>>
>>> ```java
>>> @Test
>>> public void testQualifier(){
>>>  ApplicationContext context =
>>>          new ClassPathXmlApplicationContext("classpath:ioc/annotation/scan.xml");
>>>  UserService userService = context.getBean("userService", UserService.class);
>>>  userService.add();
>>> }
>>> ```
>>>
>>> 结果
>>>
>>> >service ................
>>> >UserDaoImplCopy add.......................
>>>
>>> @Repository("UserDaoImplCopy")里指定 name 属性，然后使用 @Qualifier 注解就可以根据 name 注入
>>
>>@Resource ：可以根据类型注入，也可以根据名称注入
>>
>>>直接写根据类型注入
>>>
>>>```java
>>>@Resource(name = "UserDaoImplCopy")
>>>UserDao userDao;
>>>```
>>>
>>>这样就可以根据名称注入
>>>
>>>Resource注解是java拓展包里的
>>
>>@Value ：注入普通类型属性
>>
>>>注入属性值
>
>完全注解开发
>
>>步骤
>>
>>> 创建配置类，并加 @Configuration 注解
>>>
>>> 指定要扫描的包 @ComponentScan(basePackages = {"com.woqiyounai.ioc.annotation"})
>>>
>>> 编写测试类 上下文对象使用 **AnnotationConfigApplicationContext**

## AOP(面向切面编程)

### 概念

>面向切面编程，利用 aop 对业务的各个部分进行隔离，使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发效率
>
>例如登录业务
>
>![选区_012](/home/lxl/Pictures/选区_012.png)
>
>如果在不修改代码的前提之上加权限判断
>
>写一个权限模块加到原业务中，不修改主干代码的前提下添加功能
>
>**AOP 的一些术语**
>
>>（1）连接点
>>
>>>类里面哪些方法**可以被增强**
>>
>>（2）切入点
>>
>>>实际被**真正增强的方法**被称为切入点
>>
>>（3）通知（增强）
>>
>>>（1）真正增强的逻辑的部分称为通知
>>>
>>>（2）通知有多种类型
>>>
>>>>前置通知（@Before）：某个方法之前会执行
>>>>
>>>>后置通知（@After）：某个方法之后会执行
>>>>
>>>>环绕通知（@Around）：
>>>>
>>>>异常通知（@AfterThrowing）：
>>>>
>>>>最终通知（@AfterReturning）：相当于 finally
>>
>>（4）切面
>>
>>>是动作上的操作，把通知应用到切入点的过程
>
>AOP的操作
>
>>基于 AspectJ 实现,AspectJ是一个独立操作，一般把它和 spring 框架一起使用进行AOP操作。
>>
>>引入 Spring 和 AspectJ 相关依赖
>>
>>```xml
>><!--引入 AspectJ 和 Spring 相关依赖-->
>><dependency>
>><groupId>org.springframework</groupId>
>><artifactId>spring-aspects</artifactId>
>><version>5.2.6.RELEASE</version>
>></dependency>
>><!-- https://mvnrepository.com/artifact/net.sourceforge.cglib/com.springsource.net.sf.cglib -->
>><dependency>
>><groupId>net.sourceforge.cglib</groupId>
>><artifactId>com.springsource.net.sf.cglib</artifactId>
>><version>2.2.0</version>
>></dependency>
>><!-- https://mvnrepository.com/artifact/aopalliance/aopalliance -->
>><dependency>
>><groupId>aopalliance</groupId>
>><artifactId>aopalliance</artifactId>
>><version>1.0</version>
>></dependency>
>><!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
>><dependency>
>><groupId>org.aspectj</groupId>
>><artifactId>aspectjweaver</artifactId>
>><version>1.6.8</version>
>></dependency>
>>```
>>
>>切入点表达式：：知道哪个类里的哪个方法进行增强
>>
>>语法
>>
>>>```java
>>>execution( [权限修饰符] [返回类型] [类全路径] [方法名称](参数列表) )
>>>```
>>>
>>>例如增强 com.woqiyounai.dao.userDao 里的 add()方法
>>>
>>>```java
>>>execution(* com.woqiyounai.dao.UserDao.add(..))
>>>```
>>>
>>>增强 UserDao 的所有方法
>>>
>>>```java
>>>execution(* com.woqiyounai.dao.UserDao.*)
>>>```
>>
>>
>>
>>（1）基于 xml 配置文件实现
>>
>>>
>>
>>（2）基于注解方式实现
>>
>>>创建被增强的对象
>>>
>>>创建增强对象
>>>
>>>进行通知配置，开启注解扫描，使用注解创建 User 和 UserPlus 对象
>>>
>>>在增强类上加 @Aspect 注解
>>>
>>>在 spring 配置文件中开启生成代理对象
>>>
>>>在增强类里面，在作为通知方法上面添加通知类型注解，使用切入点表达式配置

### 底层原理

>使用动态代理的方式来做一个实现，
>
>有接口
>
>>JDK 的动态代理
>>
>>>UserDao   --->   UserDaoImpl
>>>
>>>创建 UserDaoImpl 类的代理情况，通过代理对象增强功能
>>>
>>>实现
>>>
>>>>Proxy.newProxyInstance 方法 （类加载器，增强方法所在类实现的接口，实现这个接口InvolationHandler写增强的部分）
>>>>
>>>>1.实现 jdk 的 InvocationHandler 接口，在 invoke 里面写增强逻辑
>>>>
>>>>```java
>>>>import java.lang.reflect.Method;
>>>>
>>>>//代理增强对象
>>>>public class ProxyFactory implements InvocationHandler {
>>>>
>>>>private Object obj;
>>>>
>>>>public ProxyFactory(Object obj) {
>>>>   this.obj = obj;
>>>>}
>>>>
>>>>public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
>>>>   o = obj;
>>>>   System.out.println("执行方法前");
>>>>   Object invoke = method.invoke(o, objects);
>>>>   System.out.println("执行方法后");
>>>>   return invoke;
>>>>}
>>>>}
>>>>```
>>>>
>>>>2.编写测试方法
>>>>
>>>>```java
>>>>public static void main(String[] args) {
>>>>UserDaoImpl userDao = new UserDaoImpl();
>>>>ProxyFactory proxyFactory = new ProxyFactory(userDao);
>>>>Class[] interfaces = {UserDao.class};
>>>>UserDao o = (UserDao)Proxy.newProxyInstance(UserDaoImpl.class.getClassLoader(), interfaces, proxyFactory);
>>>>o.update("id");
>>>>}
>>>>```
>>>>
>>>>
>
>没有接口
>
>>cglib 的动态代理
>>
>>>类 User 有一个 add 方法
>>>
>>>方法（1）写个类继承 User ，子类增强父类的 add 方法
>>>
>>>方法（2）创建当前类子类的代理对象

### （1）基于 xml 配置文件实现

>创建对象和代理对象
>
>配置xml
>
>>```xml
>><!--创建对象-->
>><bean id="book" class="com.woqiyounai.aop.xml.Book"></bean>
>><bean id="bookProxy" class="com.woqiyounai.aop.xml.BookProxy"></bean>
>><!--配置 AOP 增强-->
>><aop:config>
>><!--配置切入点-->
>><aop:pointcut id="buy" expression="execution(* com.woqiyounai.aop.xml.Book.buy(..))"/>
>><!--配置切面 把通知应用到切面-->
>><aop:aspect ref="bookProxy">
>>   <!--配置增强作用在具体的方法上-->
>>   <aop:before method="before" pointcut-ref="buy"></aop:before>
>></aop:aspect>
>></aop:config>
>>```
>
>写测试类
>
>>```java
>>@Test
>>public void xmlTest(){
>>ClassPathXmlApplicationContext context =
>>       new ClassPathXmlApplicationContext("classpath:aop/bean2.xml");
>>Book book = context.getBean("book", Book.class);
>>book.buy();
>>}
>>```

### （2）基于注解方式实现

>创建被增强的对象
>
>创建增强对象
>
>进行通知配置，开启注解扫描，使用注解创建 User 和 UserPlus 对象
>
>在增强类上加 @Aspect 注解
>
>在 spring 配置文件中开启生成代理对象
>
>在增强类里面，在作为通知方法上面添加通知类型注解，使用切入点表达式配置
>
>```java
>//增强类上加注解 @Aspect ，生成代理对象
>@Aspect
>@Component
>public class UserPlus {
>//表示前置通知
>@Before("execution(* com.woqiyounai.aop.annotation.User.add(..))")
>public void before(){
>   System.out.println("前置通知：add before ... ...");
>}
>
>//表示后置通知
>@After("execution(* com.woqiyounai.aop.annotation.User.add(..))")
>public void after(){
>   System.out.println("后置通知：add  after ... ...");
>}
>
>//表示后置通知
>@AfterReturning("execution(* com.woqiyounai.aop.annotation.User.add(..))")
>public void afterReturning(){
>   System.out.println("最终通知：add  afterReturning ... ...");
>}
>
>//表示后置通知
>@AfterThrowing("execution(* com.woqiyounai.aop.annotation.User.add(..))")
>public void afterThrowing(){
>   System.out.println("异常通知：add  afterThrowing ... ...");
>}
>
>//表示后置通知
>@Around("execution(* com.woqiyounai.aop.annotation.User.add(..))")
>public void around(ProceedingJoinPoint proceedingJoinPoint){
>   System.out.println("环绕通知之前：add  around ... ...");
>   try {
>       proceedingJoinPoint.proceed();
>   } catch (Throwable throwable) {
>       throwable.printStackTrace();
>   }
>   System.out.println("环绕通知之后：add  around ... ...");
>}
>}
>```
>
>输出
>
>>环绕通知之前：add  around ... ...
>>前置通知：add before ... ...
>>add ... ... 
>>环绕通知之后：add  around ... ...
>>后置通知：add  after ... ...
>>最终通知：add  afterReturning ... ...
>
>也可以做一个抽取
>
>>```java
>>@Aspect
>>@Component
>>public class UserPlus {
>>
>>@Pointcut("execution(* com.woqiyounai.aop.annotation.User.add(..))")
>>public void pointDemo(){
>>
>>}
>>
>>//表示前置通知
>>//    @Before("execution(* com.woqiyounai.aop.annotation.User.add(..))")
>>@Before(value = "pointDemo()")
>>public void before(){
>>   System.out.println("前置通知：add before ... ...");
>>}
>>
>>//表示后置通知
>>//    @After("execution(* com.woqiyounai.aop.annotation.User.add(..))")
>>@After(value = "pointDemo()")
>>public void after(){
>>   System.out.println("后置通知：add  after ... ...");
>>}
>>
>>//表示后置通知
>>//    @AfterReturning("execution(* com.woqiyounai.aop.annotation.User.add(..))")
>>@AfterReturning(value = "pointDemo()")
>>public void afterReturning(){
>>   System.out.println("最终通知：add  afterReturning ... ...");
>>}
>>
>>//表示后置通知
>>//    @AfterThrowing("execution(* com.woqiyounai.aop.annotation.User.add(..))")
>>@AfterThrowing(value = "pointDemo()")
>>public void afterThrowing(){
>>   System.out.println("异常通知：add  afterThrowing ... ...");
>>}
>>
>>//表示后置通知
>>//    @Around("execution(* com.woqiyounai.aop.annotation.User.add(..))")
>>@Around(value = "pointDemo()")
>>public void around(ProceedingJoinPoint proceedingJoinPoint){
>>   System.out.println("环绕通知之前：add  around ... ...");
>>   try {
>>       proceedingJoinPoint.proceed();
>>   } catch (Throwable throwable) {
>>       throwable.printStackTrace();
>>   }
>>   System.out.println("环绕通知之后：add  around ... ...");
>>}
>>}
>>```
>>
>>当有多个代理类时，可以使用 @Order 注解设置优先级，值越小，优先级越高。            

### 细节

>重用切入点

## JdbcTemplate

### 概念

>Spring 框架对 JDBC 进行封装，使用 JdbcTemplate 方便实现对数据库的 crud 操作

### 准备

>引入依赖
>
>>```xml
>><!--mysql驱动-->
>><!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
>><dependency>
>><groupId>mysql</groupId>
>><artifactId>mysql-connector-java</artifactId>
>><version>8.0.19</version>
>></dependency>
>><!-- jdbcTemplate -->
>><dependency>
>><groupId>org.springframework</groupId>
>><artifactId>spring-jdbc</artifactId>
>><version>5.2.6.RELEASE</version>
>></dependency>
>><!--针对事务操作的一些依赖-->
>><dependency>
>><groupId>org.springframework</groupId>
>><artifactId>spring-tx</artifactId>
>><version>5.2.6.RELEASE</version>
>></dependency>
>><!--整合其他框架需要的，如 mybatis-->
>><dependency>
>><groupId>org.springframework</groupId>
>><artifactId>spring-orm</artifactId>
>><version>5.1.10.RELEASE</version>
>></dependency>
>>```
>
>配置数据库连接池并注入 JdbcTemplate
>
>>```xml
>><?xml version="1.0" encoding="UTF-8"?>
>><beans xmlns="http://www.springframework.org/schema/beans"
>>  xmlns:context="http://www.springframework.org/schema/context"
>>  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>>  xsi:schemaLocation="
>>  http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
>>  http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
>>">
>>
>><!--引入配置文件-->
>><context:property-placeholder location="classpath:jdbcTemplate/jdbc.properties"></context:property-placeholder>
>><!--配置连接池-->
>><bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
>>   <property name="username" value="${prop.username}"></property>
>>   <property name="password" value="${prop.password}"></property>
>>   <property name="url"
>>             value="${prop.url}"></property>
>>   <property name="driverClassName" value="${prop.driverName}"></property>
>></bean>
>>
>><!--创建 jdbcTemplate 对象-->
>><bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
>>   <!--通过 set 方法注入数据源信息-->
>>   <property name="dataSource" ref="druidDataSource"></property>
>></bean>
>>
>></beans
>>```
>
>创建 service 类，创建 dao 类，在 dao 里注入 JdbcTemplate 对象
>
>开启组件扫描
>
>>```xml
>><!--开启组件扫描-->
>><context:component-scan base-package="com.woqiyounai.jdbctemplate"></context:component-scan>
>>```

### jdbcTemplate操作数据库

>update(sql,args.......)：数据库的增删改查
>
>查询
>
>>返回某个值
>>
>>>查询表里面有多少值
>>>
>>>queryForObject(sql,class对象)
>>
>>返回对象
>>
>>>queryForObject(sql,BeanPropertyRowMapper对象,参数)
>>>
>>>```java
>>>BeanPropertyRowMapper<Book> bookBeanPropertyRowMapper = new BeanPropertyRowMapper<Book>(Book.class);
>>>```
>>
>>返回集合
>>
>>>query(sql,BeanPropertyRowMapper对象,参数)
>>>
>>>```java
>>>BeanPropertyRowMapper<Book> bookBeanPropertyRowMapper = new BeanPropertyRowMapper<Book>(Book.class);
>>>```
>
>对数据库的批量操作
>
>>批量添加，修改，删除
>>
>>>batchUpdate(sql,List<Object[]>)
>>>
>>>//代码
>>>
>>>>```java
>>>>public void batchInsert(List<Object[]> bookList) {
>>>>String sql = "insert into book values(?,?,?)";
>>>>int[] ints = jdbcTemplate.batchUpdate(sql, bookList);
>>>>//遍历集合
>>>>System.out.println(Arrays.toString(ints));
>>>>}
>>>>```
>>>
>>>//测试代码
>>>
>>>>```java
>>>>//测试批量
>>>>@Test
>>>>public void testBatch(){
>>>>ApplicationContext context =
>>>>       new ClassPathXmlApplicationContext("classpath:jdbcTemplate/data.xml");
>>>>BookService bookService = context.getBean("bookService", BookService.class);
>>>>List<Object[]> bookList = new ArrayList<Object[]>();
>>>>Object[] o1 = {"0","java","入库"};
>>>>Object[] o2 = {"0","c++","入库"};
>>>>Object[] o3 = {"0","mysql","入库"};
>>>>bookList.add(o1);
>>>>bookList.add(o2);
>>>>bookList.add(o3);
>>>>bookService.batchInsert(bookList);
>>>>}
>>>>```

## 事物管理

### 概念

>什么是事务？
>
>>事务是数据库操作的最基本单元，是逻辑上的一组操作，要么都成功，如果有一个失败所有操作都失败。
>>
>>经典事例：银行转账
>
>事务的四个特性
>
>>原子性A：要么都成功，要么都失败
>>
>>一致性C：操作前和操作后总量不变
>>
>>隔离性I：  多事务操作，事务之间不会产生影响
>>
>>持久性D：事务提交后改变是持久的

### 事务操作

>创建数据库，创建表，添加记录
>
>>```sql
>>create table t_account(
>>id int(20) primary key auto_increment comment 'id',
>>username varchar(64) comment '用户名',
>>money int comment '钱'
>>)charset=utf8 engine=innoDb;
>>```
>
>创建 service ，搭建 dao ，完成对象创建和注入关系
>
>dao
>
>>```java
>>public void addMoney() {
>>String sql = "update t_account set money=money+? where username=?";
>>int mary = jdbcTemplate.update(sql, 100, "mary");
>>System.out.println(mary);
>>}
>>
>>public void reduceMoney() {
>>String sql = "update t_account set money=money-? where username=?";
>>int lucy = jdbcTemplate.update(sql, 100, "lucy");
>>System.out.println(lucy);
>>}
>>```
>
>service
>
>>```java
>>@Autowired
>>private UserDao userDao;
>>
>>public void accountOpt(){
>>// lucy 少 100
>>userDao.reduceMoney();
>>// mary 多 100
>>userDao.addMoney();
>>}
>>```
>
>如果 service 的代码正常执行没什么问题，但是若有异常数据就会出现问题，例如 lucy少100后出现异常，lucy少了钱，mary却没有多钱。
>
>为了解决上述问题，可以执行如下操作
>
>>（1）开启事务
>>
>>（2）使用 try-catch 捕获异常
>>
>>（3）进行业务上的操作
>>
>>（4）没有发生异常提交，发生异常回滚

### Spring 操作事务

>编程式事务管理
>
>>（1）开启事务
>>
>>（2）使用 try-catch 捕获异常
>>
>>（3）进行业务上的操作
>>
>>（4）没有发生异常提交，发生异常回滚
>
>声明式事务管理（常用）：底层使用 AOP 原理
>
>>原理
>>
>>>（1）Spring 事务管理 API
>>>
>>>>Spring 提供一个接口，这个接口针对不同的框架有不同的实现类
>>>>
>>>>ctrl + h 查看接口实现类
>>
>>基于注解方式（常用）
>>
>>>（1）配置事务管理器
>>>
>>>>```xml
>>>><!--创建事务管理器-->
>>>><bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
>>>><!--注入数据源-->
>>>><property name="dataSource" ref="druidDataSource"></property>
>>>></bean>
>>>>```
>>>
>>>（2）开启事务注解
>>>
>>>>引入 tx 名称空间
>>>>
>>>>```xml
>>>><!--开启事务注解，指定事务管理器-->
>>>><tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>
>>>>```
>>>
>>>（3）在 service 类上面添加事务注解
>>>
>>>>```java
>>>>@Transactional
>>>>```
>>>>
>>>>这个注解可以加到类或方法上
>>>>
>>>>添加到类上面表示类的所有方法都添加了事务
>>>>
>>>>添加到方法上仅仅是方法上添加了事务
>>>
>>>事务操作
>>>
>>>>@Transactional 可以配置针对事务的一些相关参数
>>>>
>>>>>propagation：事务传播行为
>>>>>
>>>>>>多事务方法之间进行调用，这个过程中事务是如何进行管理的
>>>>>>
>>>>>>对数据库表数据进行变化的操作
>>>>>>
>>>>>>一共有7种传播行为
>>>>>>
>>>>>>![选区_014](/home/lxl/Pictures/选区_014.png)
>>>>>
>>>>>isolaction：事务隔离级别
>>>>>
>>>>>>在多事务操作之间不会产生影响，不考虑隔离性会产生一系列问题
>>>>>>
>>>>>>脏读：一个未提交的事务读取到了另一个未提交数据的数据
>>>>>>
>>>>>>![选区_015](/home/lxl/Pictures/选区_015.png)
>>>>>>
>>>>>>不可重复读：一个未提交事务读取到另一提交事务(若另一事务回滚)的数据
>>>>>>
>>>>>>![选区_016](/home/lxl/Pictures/选区_016.png)
>>>>>>
>>>>>>虚读（幻读）：一个未提交事务读取到另一提交事务添加数据
>>>>>>
>>>>>>属性值
>>>>>>
>>>>>>>![选区_017](/home/lxl/Pictures/选区_017.png)
>>>>>
>>>>>timeout：超时时间
>>>>>
>>>>>>事务需要在一定时间内进行提交，不提交便会回滚，默认值为-1，设置时间以秒为单位
>>>>>
>>>>>readOnly：是否只读
>>>>>
>>>>>>默认值为 false，既可以查询也可以修改删除
>>>>>
>>>>>rollbackFor：回滚
>>>>>
>>>>>>设置出现哪些异常进行事务的回滚
>>>>>
>>>>>noRollbackFor：不回滚
>>>>>
>>>>>>出现哪些异常进行事务的回滚
>>
>>基于xml配置方式
>>
>>>（1）配置事务管理器
>>>
>>>（2）配置通知
>>>
>>>（3）配置切入点和切面
>>>
>>>```xml
>>><?xml version="1.0" encoding="UTF-8"?>
>>><beans xmlns="http://www.springframework.org/schema/beans"
>>>  xmlns:context="http://www.springframework.org/schema/context"
>>>  xmlns:tx="http://www.springframework.org/schema/tx"
>>>  xmlns:aop="http://www.springframework.org/schema/aop"
>>>  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>>>  xsi:schemaLocation="
>>>  http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
>>>  http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
>>>  http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
>>>  http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
>>>">
>>>
>>><!--开启组件扫描-->
>>><context:component-scan base-package="com.woqiyounai.tx"></context:component-scan>
>>><!--引入配置文件-->
>>><context:property-placeholder location="classpath:jdbcTemplate/jdbc.properties"></context:property-placeholder>
>>><!--配置连接池-->
>>><bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
>>>   <property name="username" value="${prop.username}"></property>
>>>   <property name="password" value="${prop.password}"></property>
>>>   <property name="url" value="${prop.url}"></property>
>>>   <property name="driverClassName" value="${prop.driverName}"></property>
>>></bean>
>>>
>>><!--创建 jdbcTemplate 对象-->
>>><bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
>>>   <!--通过 set 方法注入数据源信息-->
>>>   <property name="dataSource" ref="druidDataSource"></property>
>>></bean>
>>>
>>><!--创建事务管理器-->
>>><bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
>>>   <!--注入数据源-->
>>>   <property name="dataSource" ref="druidDataSource"></property>
>>></bean>
>>>
>>><!--配置通知-->
>>><tx:advice id="txadvise">
>>>   <!--配置事务的一些参数-->
>>>   <tx:attributes>
>>>       <!--哪种规则的方法上添加事务-->
>>>       <tx:method name="account*"/>
>>>   </tx:attributes>
>>></tx:advice>
>>>
>>><!--配置切入点和切面-->
>>><aop:config>
>>>   <!--配置切入点-->
>>>   <aop:pointcut id="pt" expression="execution(* com.woqiyounai.tx.xml.*(..))"/>
>>>   <!--配置切面，事务通知设置到方法上-->
>>>   <aop:advisor advice-ref="txadvise" pointcut-ref="pt"></aop:advisor>
>>></aop:config>
>>>
>>></beans>
>>>```
>
>完全注解开发，加载配置类
>
>```java
>@Configuration
>@ComponentScan(basePackages = "com.woqiyounai.tx.xml")
>@EnableTransactionManagement //开启事务
>public class TxConfig {
>
>//创建连接池
>@Bean
>public DruidDataSource getDataSource(){
>   DruidDataSource druidDataSource = new DruidDataSource();
>   druidDataSource.setUsername("root");
>   druidDataSource.setPassword("root");
>   druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/user_db?characterEncoding=UTF-8");
>   druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
>   return druidDataSource;
>}
>
>//创建 JdbcTemplate 对象
>@Bean //方法二，在参数里传 dataSource ，推荐
>public JdbcTemplate getJdbcTemplate(DataSource dataSource){
>   JdbcTemplate jdbcTemplate = new JdbcTemplate();
>   //注入 datasource
>   //jdbcTemplate.setDataSource(getDataSource()); 方法一
>   jdbcTemplate.setDataSource(dataSource);
>   return jdbcTemplate;
>}
>
>//创建事务管理器
>@Bean
>public DataSourceTransactionManager getManager(DataSource dataSource){
>   DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
>   dataSourceTransactionManager.setDataSource(dataSource);
>   return dataSourceTransactionManager;
>}
>}
>```

## Spring 5新特性

### 整个框架代码基于 Java8

>整个 Spring5 框架基于 java8 ，运行兼容 JDK9 ，自带通用日志封装，删除了许多不建议使用的类和方法。
>
>自带通用的日志封装。（已经移除了 Log4jConfigListener，官方建议使用 Log4j2 ）
>
>>Spring 整合 Log4j2
>>
>>引入依赖
>>
>>>```xml
>>><!--整合 Log4j -->
>>><!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
>>><dependency>
>>><groupId>org.apache.logging.log4j</groupId>
>>><artifactId>log4j-core</artifactId>
>>><version>2.11.2</version>
>>></dependency>
>>><!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api -->
>>><dependency>
>>><groupId>org.apache.logging.log4j</groupId>
>>><artifactId>log4j-api</artifactId>
>>><version>2.11.2</version>
>>></dependency>
>>><!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl -->
>>><dependency>
>>><groupId>org.apache.logging.log4j</groupId>
>>><artifactId>log4j-slf4j-impl</artifactId>
>>><version>2.11.2</version>
>>><scope>test</scope>
>>></dependency>
>>><!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
>>><dependency>
>>><groupId>org.slf4j</groupId>
>>><artifactId>slf4j-api</artifactId>
>>><version>1.7.30</version>
>>></dependency>
>>>```
>>
>>方法一：编写 log4j.xml 文件
>>
>>方法二：

### 核心容器

>支持候选组件索引
>
>支持 @Nullable 注解
>
>>@Nullable 注解可以使用在方法上面，属性上面，方法参数上面，表示返回可以为空，属性值可以为空，方法参数可以为空
>
>函数式风格 GenericApplicationContext/AnnotationApplicationContext 
>
>>```java
>>@Test
>>public void testGeneric(){
>>    GenericApplicationContext context = new GenericApplicationContext();
>>    // 调用 context 的方法对象注册
>>    context.refresh();
>>    //注册对象
>>    context.registerBean(User.class,() -> new User());
>>    //获取注册的对象
>>    User bean = context.getBean(User.class);
>>    System.out.println(bean);
>>}
>>```
>
>基本支持 bean API 注册

### Spring5 支持 JUnit5

>整合 Junit4
>
>>引入依赖
>>
>>>```xml
>>><!--整合Junit4-->
>>><dependency>
>>>    <groupId>org.springframework</groupId>
>>>    <artifactId>spring-test</artifactId>
>>>    <version>5.2.6.RELEASE</version>
>>></dependency>
>>>```
>>
>>测试代码
>>
>>>```java
>>>//指定 Junit 版本
>>>@RunWith(SpringJUnit4ClassRunner.class)//单元测试框架版本
>>>@ContextConfiguration("classpath:tx/data.xml")//加载配置文件
>>>public class Jtest4 {
>>>
>>>    @Autowired
>>>    UserService userService;
>>>
>>>    @Test
>>>    public void test(){
>>>        System.out.println(userService);
>>>    }
>>>
>>>}
>>>```
>
>整合Junit5
>
>>引入 Junit 依赖
>>
>>>```xml
>>><dependency>
>>>    <groupId>org.junit.jupiter</groupId>
>>>    <artifactId>junit-jupiter</artifactId>
>>>    <version>RELEASE</version>
>>>    <scope>compile</scope>
>>></dependency>
>>>```
>>
>>测试代码
>>
>>>```java
>>>import com.woqiyounai.tx.annotation.service.UserService;
>>>import org.junit.jupiter.api.Test;
>>>import org.junit.jupiter.api.extension.ExtendWith;
>>>import org.springframework.beans.factory.annotation.Autowired;
>>>import org.springframework.test.context.ContextConfiguration;
>>>import org.springframework.test.context.junit.jupiter.SpringExtension;
>>>
>>>@ExtendWith(SpringExtension.class)
>>>@ContextConfiguration("classpath:tx/data.xml")//加载配置文件
>>>public class Jtest5 {
>>>
>>>    @Autowired
>>>    UserService userService;
>>>    @Test
>>>    public void test5(){
>>>        System.out.println(userService);
>>>    }
>>>}
>>>```

## 总结

基本概念

>sprinng是轻量级开源的javaEE框架
>
>它有ioc和aop俩个核心

IOC容器

>原理：工厂模式，反射
>
>xml解析拿到 class 对象，通过反射创建对象
>
>Bean管理操作：创建对象和注入属性的过程
>
>完全注解开发：创建配置类取代配置文件，开启扫描包

AOP操作

>面向切面编程，不通过修改源代码的方式增强类的方法和功能
>
>原理：动态代理(有接口jdk,没接口cglib)
>
>**AOP 的一些术语**
>
>>（1）连接点
>>
>>>类里面哪些方法**可以被增强**
>>
>>（2）切入点
>>
>>>实际被**真正增强的方法**被称为切入点
>>
>>（3）通知（增强）
>>
>>>（1）真正增强的逻辑的部分称为通知
>>>
>>>（2）通知有多种类型
>>>
>>>>前置通知（@Before）：某个方法之前会执行
>>>>
>>>>后置通知（@After）：某个方法之后会执行
>>>>
>>>>环绕通知（@Around）：
>>>>
>>>>异常通知（@AfterThrowing）：
>>>>
>>>>最终通知（@AfterReturning）：相当于 finally
>
>基于 aspectj 实现 aop 操作

JdbcTemplate

>使用 JdbcTemplate 实现 crud 操作
>
>实现批量操作

事务的操作

>什么是事务？
>
>>事务是数据库操作的最基本单元，是逻辑上的一组操作，要么都成功，如果有一个失败所有操作都失败。
>>
>>经典事例：银行转账
>
>事务的四个特性
>
>>原子性A：要么都成功，要么都失败
>>
>>一致性C：操作前和操作后总量不变
>>
>>隔离性I：  多事务操作，事务之间不会产生影响
>>
>>持久性D：事务提交后改变是持久的
>
>```java
>@Transactional
>```
>
>使用注解开启事务

spring5新功能

>基于jdk8
>
>自带通用的日志封装。（已经移除了 Log4jConfigListener，官方建议使用 Log4j2 ）
>
>支持JUnit5
>
>函数式风格 GenericApplicationContext/AnnotationApplicationContext 
>
>支持 @Nullable 注解



