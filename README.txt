/****************git clone 到自己的工程后改工程为名称seckill****************/
1)命令行创建maven项目
（http://www.cnblogs.com/zjfjava/p/6819459.html）

mvn archetype:generate -DgroupId=org.seckill -DartifactId=seckill -DarchetypeArtifactId=maven-archetype-webapp

2）intellij 注册码：http://idea.lanyus.com/
3）导入intellij：工程：file-->close-->inport maven pom文件
4)更换web.xml tomcat8中有头部文件信息：
5)创建文件夹和资源文件夹：view-->open moudel setting 里面
6）补全项目依赖
<!--补全项目依赖-->
<!--
	1:日志 java 日志：slf4j,log4j,logback,common-logging
	slf4j 是规范/接口
	日志实现：log4j，logback，common-logging
	使用：slf4j + logback
-->
<@!--2:serverlet相关依赖，
<!--3 spring 核心依赖
<!--4 test依赖

7)编写实体类：entity
8）编写dao
9）引入mybatis：
10）mybatis在xml文件中处理大于号小于号的方法
http://blog.csdn.net/limingchuan123456789/article/details/34218559
SELECT * FROM test WHERE 1 = 1 AND start_date  &lt;= CURRENT_DATE AND end_date &gt;= CURRENT_DATE

11)mybatis 整合spring：
    更少的代码：只写接口不写实现类
    更少的配置：自动配置文件扫描配置
    更少的配置-dao：自动实现dao接口、自动注入spring容器中
    足够灵活：自己定制sql、自由传递参数、结果集自动赋值
    xml提供sql、DAO接口Mapper
12）spring 官方文档：https://github.com/spring-projects/spring-framework/tree/master/spring-webmvc


13)spring配置（http://blog.csdn.net/zoutongyuan/article/details/27073683）
[html] view plain copy
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:context="http://www.springframework.org/schema/context" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:tx="http://www.springframework.org/schema/tx" xmlns:p="http://www.springframework.org/schema/p" xmlns:util="http://www.springframework.org/schema/util" xmlns:jdbc="http://www.springframework.org/schema/jdbc"
    xmlns:cache="http://www.springframework.org/schema/cache"
    xsi:schemaLocation="

14）快速创建dao测试类：http://mrrigth.iteye.com/blog/1734491
快捷键：ctrl+shift+t  --> create new test
控制台输出：sout 在按住TAB键


15）报错：
        <property name="user" value="root"/>
        <property name="password" value="123456"/>
16）解决日期字符串： java.sql.SQLException: Value '0000-00-00 00:00:00' can not be represented as java.sql.Date
                      http://www.blogjava.net/hilor/articles/164814.html
											url=jdbc:mysql://localhost:3306/seckill?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull
17）用 @Autowired 不要用Resource 注解
    private SeckillDao seckillDao;

18）java虚拟机运行的时候不能绑定形参：mybatis用#{0},#{1} 替代 limit #{offset},#{limit}，或者用
    List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);

19）多对一写法

第二部分：service开发
20）新建包：
			dot：数据传输层：web和service层之间的数据传递
      service：
      exception：异常处理


21）设置注释：
增加系统用户
这个就不用多讲了。
设置idea
file 》 settings》File and Code Templates
中间的，选择 includes ，选中File Header。
右侧会出现 Created by ${USER} on ${DATE}.
把这个 ${USER} 直接替换掉 Created by myname on ${DATE}.

 /* Created with IntelliJ IDEA.
 * User: Dolen
 * Date: ${DATE}
 * Time: ${TIME}
 */

22）Ctrl + I Implement methods  弹出窗口选择要Implement的方法：Ctrl + I
23） 格式化代码：格式化import列表Ctrl+Alt+O，格式化代码Ctrl+Alt+L
24）用枚举放常量：
25）spring ioc 依赖注入三种方式：
			a)xml：bean实现第三方类，如DataSource；命名空间context、aop
			b)注解：自身开发的@Service、@Controller等
			c)java配置类：自定义修改依赖库
26）本项目IOC：xml配置、package-scan、Annotation注解

27）基于注解：
@Component--所有类、
@Service --service类、
@Controller --web
@Dao --DAO

//spring 自己的依赖注入：
@Autowrired
@Resource、@InJect ：j2ee规范注解

28）spring声明事物：
 a)早期spring2.0版本：ProxyFactoryBean + xml
 b)一次配置永久生效：tx:advice+aop 命名空间
 c）注解：@transaction  推荐使用
29）什么时候回滚事物：抛出运行期异常(RuntimeException)，小心不当的try-catch

30)测试service：
31）logback官方：https://logback.qos.ch/manual/configuration.html
32)info 输出格式：logger.info("seckill={}",seckill);


第三部分：web开发
a)前端交互设计：产品，前端，页面
+restful+springmvc+bootstrap+jquery
b)登录页、详情页

33）设计Restful接口 ：资源状态和状态的转移
get /seckill/{id}/delete
post /seckill/{killid}/execution
get->查询
post->添加、修改
put->修改
delete->删除
/模块/资源/{标识}/集合1/...
/user/{uid}/frends ->友好列表
/user/{uid}/followers ->关注列表
34）秒杀API的URL设计
get /seckill/list 秒杀列表
get /seckill/{id}/detail >详情页
get /seckill/time/now >系统时间
post /seckill/{id}/exposer >暴露秒杀
post /seckill/{id}/{md5}/execution >执行秒杀
34）围绕handler开发
handler-->数据model
          页面view

35）配置web.xml、spring-web.xml
36）开发web，control过程
37）开发页面：bootstrap ：http://v3.bootcss.com/getting-started/#download
38)配置intellij 的Tomcat:http://www.cnblogs.com/avivaye/p/6437555.html（http://blog.csdn.net/Mr_OOO/article/details/50976205）
29）启动服务报错：关于spring”通配符的匹配很全面, 但无法找到元素 ‘context:component-scan’ 的声明“的错误
   解决办法： http://www.springframework.org/schema/context
    					http://www.springframework.org/schema/context/spring-context.xsd

30)bootcdn 官网：
http://www.bootcdn.cn/
a)jquery 倒计时插件 jquery.countdown 官网：https://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js
a)jquery cookie插件 /jquery-cookie 官网:https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js

31）浏览器打印：console.info(1)
32)click 一直绑定，one绑定一次
33）手动创建svn：E:\Program\Subversion\bin>svnserve.exe -d -r E:\Program\Subversion\root
34)intellij 设置颜色：https://zhidao.baidu.com/question/1898530784494397700.html
35)动态html写法： node.html('<span class="label label-success">' + stateInfo + '</span>');
36)dto中没有getter方法报错，405
37)intellij 用maven 自动生成war，不要用tomcat 运行的时候的jar，因为里面的war不全，没有resource资源文件。
38）将war 改名为 ROOT.war 放到其他tomcat下
39）redis 学习，官网地址：
安装gcc:yum -y install gcc automake autoconf libtool make
安装redis-server：下载并安装https://redis.io/topics/quickstart
redis-启动方法：http://www.cnblogs.com/pqchao/p/6549510.html
引入java 访问redis 客户端：http://www.redis.net.cn/tutorial/3501.html
40）采用自定义序列化：potostus
41）自动提示pom.xml，自动补齐：http://blog.csdn.net/xiongyu777888/article/details/51927070
42)秒杀 roback、commit ，秒杀inser和updat操作，行锁
先inser 在update，可以减少网络延迟，
43）intellij 大小写转换：
格式化代码 ctr+alt+L
大小写转化 ctr+shift+U

44）通过存储过程完成秒杀，减少高并发，优化事物行级锁优化的时间
不要过度依赖存储过程，简单的逻辑可以应用存储过程，qps，一个秒杀单6000/gps
45)存储过程忽略



46）部署操作
系统用的CDN
ngix+jetty
redis:服务器端缓存
mysql：
47）参与角色：前端+后端+测试+DBA+运维(ngix)
48)intellij ：maven变异时候乱码
	a)在pom.xml添加
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <forkMode>once</forkMode>
                    <argLine>-Dfile.encoding=UTF-8</argLine>
                </configuration>
            </plugin>
        </plugins>
    </build>
  b)在-Dfile.encoding=UTF-8


49） intellij 快捷键：http://www.cnblogs.com/bluestorm/archive/2013/05/20/3087889.html


50）秒杀项目：http://code.taobao.org/svn/seckill-demo/

51）将秒杀项目上传git ：http://blog.csdn.net/zsq520520/article/details/51004721