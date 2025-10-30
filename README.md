# WeChat

## 项目介绍

WeChat 是一个完整的微信公众平台开发管理框架，为Java开发者提供了便捷的微信功能接入解决方案。该框架支持微信公众号、微信小程序和微信支付等核心功能，通过模块化设计，实现了功能组件的灵活组合与扩展。

本框架基于Spring Boot构建，提供了声明式的API调用方式，使开发者能够快速集成微信各项服务，减少重复开发工作，提高开发效率。

## 模块结构

WeChat项目采用清晰的分层架构和模块化设计，每个功能领域都被划分为多个职责明确的子模块：

### 1. 微信公众号模块
- **wechat-official-remoting**：封装微信公众号API的远程调用接口
- **wechat-official-module**：公众号核心模型定义和基础功能
- **wechat-official-service**：公众号业务逻辑实现
- **wechat-official-repository**：公众号相关数据存储接口和实现
- **wechat-official-starter**：提供Spring Boot自动配置，简化集成

### 2. 微信小程序模块
- **wechat-applet-remoting**：封装微信小程序API的远程调用接口
- **wechat-applet-module**：小程序核心模型定义和基础功能
- **wechat-applet-service**：小程序业务逻辑实现
- **wechat-applet-repository**：小程序相关数据存储接口和实现
- **wechat-applet-starter**：提供Spring Boot自动配置，简化集成

### 3. 微信支付模块
- **wechat-pay-remoting**：封装微信支付API的远程调用接口
- **wechat-pay-module**：支付核心模型定义和基础功能
- **wechat-pay-service**：支付业务逻辑实现
- **wechat-pay-repository**：支付相关数据存储接口和实现
- **wechat-pay-starter**：提供Spring Boot自动配置，简化集成

### 4. 通用模块
- **wechat-common-remoting**：通用远程调用接口和工具
- **wechat-common-module**：框架通用模型定义和基础工具类
- **wechat-common-service**：通用业务逻辑实现
- **wechat-common-repository**：通用数据存储接口和实现

### 5. 版本管理
- **wechat-zen-pom**：统一管理框架内所有模块的依赖版本

## 技术栈

- **Java 21**：主要开发语言
- **Spring Boot 3.2.9**：应用框架
- **MyBatis-Flex 1.11.1**：ORM框架
- **Hutool 5.8.39**：Java工具类库
- **Lombok**：减少样板代码
- **Remote**：声明式远程调用框架（同组织开发）

## 安装教程

### Maven依赖配置

1. 首先引入版本管理依赖：

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.asialjim.microapplet</groupId>
            <artifactId>wechat-zen-pom</artifactId>
            <version>2.0.0-SNAPSHOT</version>
            <scope>import</scope>
            <type>pom</type>
        </dependency>
    </dependencies>
</dependencyManagement>
```

2. 根据需要引入具体模块：

```xml
<dependencies>
    <!-- 微信公众号支持 -->
    <dependency>
        <groupId>com.asialjim.microapplet</groupId>
        <artifactId>wechat-official-starter</artifactId>
    </dependency>
    
    <!-- 或微信小程序支持 -->
    <!--
    <dependency>
        <groupId>com.asialjim.microapplet</groupId>
        <artifactId>wechat-applet-starter</artifactId>
    </dependency>
    -->
    
    <!-- 或微信支付支持 -->
    <!--
    <dependency>
        <groupId>com.asialjim.microapplet</groupId>
        <artifactId>wechat-pay-starter</artifactId>
    </dependency>
    -->
</dependencies>
```

### Spring Boot配置

在`application.yml`或`application.properties`中添加微信相关配置：

```yaml
wechat:
  official:
    app-id: your_app_id
    app-secret: your_app_secret
    token: your_token
    encoding-aes-key: your_encoding_aes_key
  
  # 或小程序配置
  applet:
    app-id: your_applet_app_id
    app-secret: your_applet_app_secret
  
  # 或支付配置
  pay:
    app-id: your_pay_app_id
    mch-id: your_mch_id
    mch-key: your_mch_key
    certificate-path: path/to/cert.p12
```

## 使用示例

### 微信公众号 - 获取AccessToken

```java
@Service
public class WeChatService {
    
    @Autowired
    private WeChatOfficialService weChatOfficialService;
    
    public String getAccessToken() {
        // 获取微信公众号AccessToken
        return weChatOfficialService.getAccessToken();
    }
}
```

### 微信小程序 - 登录验证

```java
@RestController
@RequestMapping("/api/wechat")
public class WeChatController {
    
    @Autowired
    private WeChatAppletService weChatAppletService;
    
    @PostMapping("/login")
    public Result<WeChatUserInfo> login(@RequestParam String code) {
        // 小程序登录验证
        return weChatAppletService.login(code);
    }
}
```

### 微信支付 - 创建订单

```java
@Service
public class PaymentService {
    
    @Autowired
    private WeChatPayService weChatPayService;
    
    public PayResult createPayOrder(PayOrderDTO orderDTO) {
        // 创建微信支付订单
        return weChatPayService.createOrder(orderDTO);
    }
}
```

## 主要特性

1. **模块化设计**：各功能领域清晰分离，可按需引入
2. **声明式API调用**：基于Remote框架，简化API调用方式
3. **自动配置**：Spring Boot自动配置，开箱即用
4. **完整的功能覆盖**：支持微信公众号、小程序、支付等核心功能
5. **灵活的扩展性**：支持自定义实现和扩展现有功能
6. **完善的数据模型**：提供丰富的数据模型和工具类

## 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码（确保遵循项目的代码风格和命名规范）
4. 新建 Pull Request

## 许可证

本项目采用Apache License 2.0许可证 - 详见LICENSE文件

## 开发者信息

- **作者**：Asial Jim
- **邮箱**：asialjim@hotmail.com
- **GitHub**：https://github.com/asialjim

## 项目链接

- **GitHub仓库**：https://github.com/MicroApplet/WeChat
- **Issue跟踪**：https://github.com/MicroApplet/WeChat/issues
