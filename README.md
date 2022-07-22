crisn ![alt tag](https://api.travis-ci.org/phishman3579/java-algorithms-implementation.svg?branch=master)
==============================

`crisn`引用第三方开源框架`Spring Boot + Vue`，便捷开发，高效生产，结合自身业务需求，实现自身功能价值。

## Environment
+ `JDK:` 1.8+
+ `Maven:` 3.0+
+ `MySQL:` 5.7+
+ `Tomcat:` 9.0.x
+ `Spring Boot:` 2.5.14
+ `Spring Framework:` 5.3.x
+ `Druid:` 1.2.x
+ `MyBatis:` 3.5.x
+ `Redis:` 2.5.8
+ `Quartz:` 2.3.2
+ `Hutool:` 5.8.x

## Project
| `moudle name` | `description` |
| :------ | :------ |
| crisn-admin | 后台服务 |
| crisn-common | 通用工具 |
| crisn-framework | 框架核心 |
| crisn-generator | 代码生成（不用可移除） |
| crisn-quartz | 定时任务（不用可移除） |
| crisn-system | 系统代码 |

```
com.crisn
├── common            // 工具类
│       └── annotation                    // 自定义注解
│       └── config                        // 全局配置
│       └── constant                      // 通用常量
│       └── core                          // 核心控制
│       └── enums                         // 通用枚举
│       └── exception                     // 通用异常
│       └── filter                        // 过滤器处理
│       └── utils                         // 通用类处理
│       └── xss                           // XSS过滤处理
├── framework         // 框架核心
│       └── aspectj                       // 注解实现
│       └── config                        // 系统配置
│       └── datasource                    // 数据权限
│       └── interceptor                   // 拦截器
│       └── manager                       // 异步处理
│       └── security                      // 权限控制
│       └── web                           // 前端控制
```
## Function
1.  用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.  部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
3.  岗位管理：配置系统用户所属担任职务。
4.  菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.  角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.  字典管理：对系统中经常使用的一些较为固定的数据进行维护。
7.  参数管理：对系统动态配置常用参数。
8.  通知公告：系统通知公告信息发布维护。
9.  操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
10. 登录日志：系统登录日志记录查询包含登录异常。
11. 在线用户：当前系统中活跃用户状态监控。
12. 定时任务：在线（添加、修改、删除)任务调度包含执行结果日志。
13. 代码生成：前后端代码的生成（java、html、xml、sql）支持CRUD下载 。
14. 系统接口：根据业务代码自动生成相关的api接口文档。
15. 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
16. 缓存监控：对系统的缓存信息查询，命令统计等。
17. 在线构建器：拖动表单元素生成相应的HTML代码。
18. 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。