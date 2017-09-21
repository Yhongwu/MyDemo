shiroweb
--shiro+springmvc+spring+mybatis整合
--测试路径：http://localhost:8081/shiroweb/members/list
--http://localhost:8081/shiroweb/login
--测试用户admin/123456 
--新建测试用户方法，由于是简单的测试小例子，这里没有写相关直接新增代码，可使用测试目录下的TestPwd获得相关密码和盐值复制到数据库表数据中
--不使用注解方式的时候  applicationContext-shiro.xml文件中的配置生效 需要使用到角色权限的时候，就会调用doGetAuthorizationInfo方法
--并且会根据上面的配置比如权限不足走error路径 roles["member,admin"]表示同时为admin和member角色才满足 注销直接配置为logout即可(xml里配置了重定向)

--使用注解(可与配置方式混合着用)
--springmvc增加注解配置
--异常处理必须使用spring的方式来捕获
--可以通过注解自定义权限是或还是与的关系
--如果xml同时也配置了权限，xml优先

--配置了缓存之后，doGetAuthorizationInfo方法只执行一次