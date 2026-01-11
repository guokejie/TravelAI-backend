# TravelAI

一个基于 AI 的智能旅行规划应用，帮助用户轻松制定个性化的旅行计划。

## 技术栈

- **Java 21** - 编程语言
- **Spring Boot 3** - 应用框架


## 功能特性

### 已完成
- 用户注册/登录（手机号 + 密码）


### 规划中
- AI 智能行程规划
- 景点推荐
- 费用预算
- 行程分享
- RAG
- mcp实现订票

## 快速开始

### 环境要求
- JDK 21+
- Maven 3.6+
- MySQL 8.0+

### 配置数据库


### 修改配置

编辑 `src/main/resources/application.yml`，配置数据库连接：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/travel_planner
    username: your_username
    password: your_password
```

### 启动项目

```bash
mvn spring-boot:run
```

## 项目结构

```
src/main/java/org/tech
├── common/          # 通用模块（结果、异常、常量）
├── config/          # 配置类
├── controller/      # 控制器层
│   ├── auth/       # 认证接口
│   └── user/       # 用户接口
├── service/         # 服务层
├── mapper/          # 数据访问层
├── entity/          # 实体类
├── dto/             # 数据传输对象
└── vo/              # 视图对象
```


## 开发计划

- [ ] 集成 AI 大模型实现智能行程规划
- [ ] 添加第三方登录（微信、Google）
- [ ] 实现行程管理功能
- [ ] 接入地图和天气 API
- [ ] 开发移动端应用

## License

MIT License
