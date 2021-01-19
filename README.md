# DDD分层架构最佳实践

标签（空格分隔）： 架构设计

---

还在单体应用的时候就是分层架构一说，我们用得最多的就是三层架构。而现在已经是微服务时代，在微服务架构模型比较常用的有几个，例如：整洁架构，CQRS（命令查询分离）以及六边形架构。每种架构模型都有自己的应用场景，但其核心都是“**高内聚低耦合**”原则。而运用`领域驱动设计`（DDD）理念以应对日常加速的业务变化对架构的影响，架构的边界越业越清晰，各施其职，这也符合微服务架构的设计思想。以领域驱动设计（DDD）为理念的分层架构已经成为微服务架构实践的最佳实践方法。

## 一、什么是DDD分层架构

### 1\. 传统三层架构

要了解DDD分层架构，首页先了解传统的三层架构。

![](https://oscimg.oschina.net/oscnet/up-40158c90a99ed0b1b007e761baf808a48a2.png)

传统三层架构流程：

-   第一步考虑的是数据库设计，数据表如何建，表之间的关系如何设计
-   第二步就是搭建数据访问层，如选一个ORM框架或者拼接SQL操作
-   第三步就是业务逻辑的实现，由于我们先设计了数据库，我们整个的思考都会围绕着数据库，想着怎么写才能把数据正确地写入数据库中，这时CRUD的标准作法就出现了，也就没有太多考虑面向对象，解耦的事情了，这样的代码对日常的维护自然是越来越困难的
-   第四步表示层主要面向用户的输出

### 2\. DDD分层架构

![](https://oscimg.oschina.net/oscnet/up-06de1ebe56ce13de56b2a3d3ba1918b6784.png)

为了解决高耦合问题并轻松应对以后的系统变化，我们提出了运用`领域驱动设计`的理念来设计架构。

> 此段落部分总结来源于欧创新《DDD实践课》的《[07 | DDD分层架构：有效降低层与层之间的依赖](https://time.geekbang.org/column/article/156849/)》读后感

#### 1）领域层

首先我们抛开数据库的困扰，**先从业务逻辑入手开始**，设计时不再考虑数据库的实现。将以前的业务逻辑层（BLL）拆分成了`领域层`和`应用层`。

领域层聚焦业务对象的业务逻辑实现，体现现实世界业务的逻辑变化。它用来表达业务概念、业务状态和业务规则，对于业务分析可参照：《[使用领域驱动设计分析业务](https://my.oschina.net/barryhome/blog/4318633)》

#### 2）应用层

应用层是领域层的上层，依赖领域层，是各聚合的协调和编排，原则上是不包括任何业务逻辑。它以较粗粒度的封闭为前端接口提供支持。除了提供上层调用外，还可以包括事件和消息的订阅。

#### 3） 用户接口层

用户接口层面向用户访问的数据入向接口，可按不同场景提供不一样的用户接口实现。面向Web的可使用http restful的方式提供服务，可增加安全认证、权限校验，日志记录等功能；面向微服务的可使用RPC方式提供服务，可增加限流、熔断等功能。

#### 4） 基础设施层

基础设施层是数据的出向接口，封装数据调用的技术细节。可为其它任意层提供服务，但为了解决耦合的问题采用了依赖倒置原则。其它层只依赖基础设施的接口，于具体实现进行分离。

## 二、DDD分层代码实现

### 1\. 结构模型

![](https://oscimg.oschina.net/oscnet/up-dc1c3be6365eb49301861ad1c44d0b6c455.png)

### 2\. 目录结构

```shell
.
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── fun
    │   │       └── barryhome
    │   │           └── ddd
    │   │               ├── WalletApplication.java
    │   │               ├── application
    │   │               │   ├── TradeEventProcessor.java
    │   │               │   ├── TradeMQReceiver.java
    │   │               │   └── TradeManager.java
    │   │               ├── constant
    │   │               │   └── MessageConstant.java
    │   │               ├── controller
    │   │               │   ├── TradeController.java
    │   │               │   ├── WalletController.java
    │   │               │   └── dto
    │   │               │       └── TradeDTO.java
    │   │               ├── domain
    │   │               │   ├── TradeService.java
    │   │               │   ├── TradeServiceImpl.java
    │   │               │   ├── enums
    │   │               │   │   ├── InOutFlag.java
    │   │               │   │   ├── TradeStatus.java
    │   │               │   │   ├── TradeType.java
    │   │               │   │   └── WalletStatus.java
    │   │               │   ├── event
    │   │               │   │   └── TradeEvent.java
    │   │               │   ├── model
    │   │               │   │   ├── BaseEntity.java
    │   │               │   │   ├── TradeRecord.java
    │   │               │   │   └── Wallet.java
    │   │               │   └── repository
    │   │               │       ├── TradeRepository.java
    │   │               │       └── WalletRepository.java
    │   │               └── infrastructure
    │   │                   ├── TradeRepositoryImpl.java
    │   │                   ├── WalletRepositoryImpl.java
    │   │                   ├── cache
    │   │                   │   └── Redis.java
    │   │                   ├── client
    │   │                   │   ├── AuthFeignClient.java
    │   │                   │   └── LocalAuthClient.java
    │   │                   ├── jpa
    │   │                   │   ├── JpaTradeRepository.java
    │   │                   │   └── JpaWalletRepository.java
    │   │                   └── mq
    │   │                       └── RabbitMQSender.java
    │   └── resources
    │       ├── application.properties
    │       └── rabbitmq-spring.xml
    └── test
        └── java



```

此结构为单一微服务的简单结构，各层在同一个模块中。

在大型项目开发过程中，为了达到核心模块的权限控制或更好的灵活性可适当调整结构，可参考《 [数字钱包系统](https://gitee.com/hypier/barry-wallet)》系统结构

### 3\. 领域层实现（domain）

在业务分析（《[使用领域驱动设计分析业务](https://my.oschina.net/barryhome/blog/4318633)》）之后，开始编写代码，首先就是写领域层，创建`领域对象`和`领域服务接口`

#### 1）领域对象

这里的领域对象包括实体对象、值对象。

**实体对象**：具有唯一标识，能单独存在且可变化的对象

**值对象**：不能单独存在或在逻辑层面单独存在无意义，且不可变化的对象

**聚合**：多个对象的集合，对外是一个整体

**聚合根**：聚合中可代表整个业务操作的实体对象，通过它提供对外访问操作，它维护聚合内部的数据一致性，它是聚合中对象的管理者

代码示例：

```java
// 交易
public class TradeRecord extends BaseEntity {
    /**
     * 交易号
     */
    @Column(unique = true)
    private String tradeNumber;
    /**
     * 交易金额
     */
    private BigDecimal tradeAmount;
    /**
     * 交易类型
     */
    @Enumerated(EnumType.STRING)
    private TradeType tradeType;
    /**
     * 交易余额
     */
    private BigDecimal balance;
    /**
     * 钱包
     */
    @ManyToOne
    private Wallet wallet;

    /**
     * 交易状态
     */
    @Enumerated(EnumType.STRING)
    private TradeStatus tradeStatus;

  	@DomainEvents
    public List<Object> domainEvents() {
        return Collections.singletonList(new TradeEvent(this));
    }
}

// 钱包
public class Wallet extends BaseEntity {

    /**
     * 钱包ID
     */
    @Id
    private String walletId;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    private WalletStatus walletStatus = WalletStatus.AVAILABLE;
    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 余额
     */
    private BigDecimal balance = BigDecimal.ZERO;

}



```

-   从**钱包交易**例子的系统设计中，钱包的任何操作如：充值、消息等都是通过交易对象驱动钱包余额的变化
    
-   **交易对象**和**钱包对象**均为`实体对象`且组成`聚合`关系，**交易对象**是钱包交易业务模型的`聚合根`，代表聚合向外提供调用服务
    
-   经过分析**交易对象**与**钱包对象**为1对多关系（@ManyToOne），这里采用了`JPA`做**ORM**架构，更多[JPA实践请参考>>](https://my.oschina.net/barryhome?tab=newest&catalogId=6966779)
    
-   这里的领域建模使用的是`贫血模型`，结构简单，职责单一，相互隔离性好但缺乏面向对象设计思想，关于领域建模可参考《[领域建模的贫血模型与充血模型](https://my.oschina.net/barryhome/blog/4465537)》
    
-   domainEvents()为`领域事件`发布的一种实现，作用是**交易对象**任何的数据操作都将触发事件的发布，再配合**事件订阅**实现**事件驱动设计**模型，当然也可以有别的实现方式
    

#### 2）领域服务

```java
/**
 * Created on 2020/9/7 11:40 上午
 *
 * @author barry
 * Description: 交易服务
 */
public interface TradeService {

    /**
     * 充值
     *
     * @param tradeRecord
     * @return
     */
    TradeRecord recharge(TradeRecord tradeRecord);

    /**
     * 消费
     *
     * @param tradeRecord
     * @return
     */
    TradeRecord consume(TradeRecord tradeRecord);
}



```

-   先定义服务接口，接口的定义需要遵循**现实业务的操作**，切勿以程序逻辑或数据库逻辑来设计定义出增删改查
-   主要的思考方向是交易对象对外可提供哪些服务，这种服务的定义是**粗粒度**且**高内聚**的，切勿将某些具体代码实现层面的方法定义出来
-   接口的输入输出参数尽量考虑以对象的形式，充分兼容各种场景变化
-   关于前端需要的复杂查询方法可不在此定义，一般情况下查询并非是一种领域服务且没有数据变化，可单独处理
-   领域服务的实现主要关注逻辑实现，切勿包含技术基础类代码，比如缓存实现，数据库实现，远程调用等

#### 3）基础设施接口

```java
public interface TradeRepository {
    /**
     * 保存
     * @param tradeRecord
     * @return
     */
    TradeRecord save(TradeRecord tradeRecord);

    /**
     * 查询订单
     * @param tradeNumber
     * @return
     */
    TradeRecord findByTradeNumber(String tradeNumber);

    /**
     * 发送MQ事件消息
     * @param tradeEvent
     */
    void sendMQEvent(TradeEvent tradeEvent);

    /**
     * 获取所有
     * @return
     */
    List<TradeRecord> findAll();
}



```

-   基础设施接口放在领域层主要的目的是减少领域层对基础设施层的依赖
-   接口的设计是不可暴露实现的技术细节，如不能将拼装的SQL作为参数

### 4\. 应用层实现（application）

```java
// 交易服务
@Component
public class TradeManager {

    private final TradeService tradeService;
    public TradeManager(TradeService tradeService) {
        this.tradeService = tradeService;
    }


    // 充值
    @Transactional(rollbackFor = Exception.class)
    public TradeRecord recharge(TradeRecord tradeRecord) {
        return tradeService.recharge(tradeRecord);
    }


     // 消费
    @Transactional(rollbackFor = Exception.class)
    public TradeRecord consume(TradeRecord tradeRecord) {
        return tradeService.consume(tradeRecord);
    }
}

// 交易事件订阅
@Component
public class TradeEventProcessor {

    @Autowired
    private TradeRepository tradeRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, condition = "# tradeEvent.tradeStatus.name() == 'SUCCEED'")
    public void TradeSucceed(TradeEvent tradeEvent) {
        tradeRepository.sendMQEvent(tradeEvent);
    }
}

// 交易消息订阅
@Component
public class TradeMQReceiver {

    @RabbitListener(queues = "ddd-trade-succeed")
    public void receiveTradeMessage(TradeEvent tradeEvent){
        System.err.println("========MQ Receiver============");
        System.err.println(tradeEvent);
    }
}

```

**应用服务**：

-   应用层是很薄的一层，主要用于调用和组合领域服务，切勿包含任何业务逻辑
-   可包括少量的流程参数判断
-   由于可能是多个领域服务组合操作调用，如果存在原子性要求可以增加**@Transactional**事务控制

**事件订阅**：

-   事件订阅是进程内多个领域操作协作解耦的一种实现方式，它也是进程内所有后续操作的接入口
-   它与应用服务的组合操作用途不一样，组合是根据场景需求可增可减，但事件订阅后的操作是相对固化的，主要是满足逻辑的一致性要求
-   **TransactionPhase.AFTER_COMMIT**配置是在前一操作事务完成后再调用，从而减少后续操作对前操作的影响
-   事件订阅可能会有多个消息主体，为了方便管理最好统一在一个类里处理
-   MQ消息发布一般放在事件订阅中

**消息订阅**：

-   消息订阅是多个微服务间协作解耦的异步实现方式
-   消息体尽量以统一的对象包装进行传递，降低对象异构带来的处理难度

### 5\. 基础设施层（infrastructure）

```java
@Repository
public class TradeRepositoryImpl implements TradeRepository {

    private final JpaTradeRepository jpaTradeRepository;
    private final RabbitMQSender rabbitMQSender;
    private final Redis redis;

    public TradeRepositoryImpl(JpaTradeRepository jpaTradeRepository, RabbitMQSender rabbitMQSender, Redis redis) {
        this.jpaTradeRepository = jpaTradeRepository;
        this.rabbitMQSender = rabbitMQSender;
        this.redis = redis;
    }

    @Override
    public TradeRecord save(TradeRecord tradeRecord) {
        return jpaTradeRepository.save(tradeRecord);
    }

    /**
     * 查询订单
     */
    @Override
    public TradeRecord findByTradeNumber(String tradeNumber) {
        TradeRecord tradeRecord = redis.getTrade(tradeNumber);
        if (tradeRecord == null){
            tradeRecord = jpaTradeRepository.findFirstByTradeNumber(tradeNumber);
            // 缓存
            redis.cacheTrade(tradeRecord);
        }

        return tradeRecord;
    }

    /**
     * 发送事件消息
     * @param tradeEvent
     */
    @Override
    public void sendMQEvent(TradeEvent tradeEvent) {
        // 发送消息
        rabbitMQSender.sendMQTradeEvent(tradeEvent);
    }

    /**
     * 获取所有
     */
    @Override
    public List<TradeRecord> findAll() {
        return jpaTradeRepository.findAll();
    }
}

```

-   基础设施层是数据的输出向，主要包含数据库、缓存、消息队列、远程访问等的技术实现
    
-   基础设计层对外隐藏技术实现细节，提供粗粒度的数据输出服务
    
-   数据库操作：领域层传递的是数据对象，在这里可以按数据表的实现方式进行拆分实现
    

### 6\. 用户接口层（controller）

```java
@RequestMapping("/trade")
@RestController
public class TradeController {

    @Autowired
    private TradeManager tradeManager;

    @Autowired
    private TradeRepository tradeRepository;

    @PostMapping(path = "/recharge")
    public TradeDTO recharge(@RequestBody TradeDTO tradeDTO) {
        return TradeDTO.toDto(tradeManager.recharge(tradeDTO.toEntity()));
    }

    @PostMapping(path = "/consume")
    public TradeDTO consume(@RequestBody TradeDTO tradeDTO) {
        return TradeDTO.toDto(tradeManager.consume(tradeDTO.toEntity()));
    }

    @GetMapping(path = "/{tradeNumber}")
    public TradeDTO findByTradeNumber(@PathVariable("tradeNumber") String tradeNumber){
        return TradeDTO.toDto(tradeRepository.findByTradeNumber(tradeNumber));
    }

}

```

-   用户接口层面向终端提供服务支持
-   可根据不同的场景单独一个模块，面向Web提供http restful，面向服务间API调用提供RPG支持
-   为Web端提供身份认证和权限验证服务，VO数据转换
-   为API端提供限流和熔断服务，DTO数据转换
-   将数据转换从应用层提到用户接口层更方便不同场景之前的需求变化，同时也保证应用层数据格式的统一性

### 7\. 复杂数据查询

以上可见并没有涉及复杂数据查询问题，此问题不涉及业务逻辑处理所以不应该放在领域层处理。

-   如果复杂数据查询需求较多可采用`CQRS`模式，将查询单独一个模块处理。如果较少可由基础设施层做数据查询，应用层做数据封装，用户接口层做数据调用
-   JPA不太适合做多表关联的数据库查询操作，可使用其它的灵活性较高的ORM架构
-   在大数据大并发情况下，多表关联会严重影响数据库性能，可以考虑做**宽表查询**

## 三、综述

DDD分层主要解决各层之间耦合度问题，做到各层各施其职互不影响。各层中领域层的设计是整个系统的中枢，最能体现`领域驱动设计`的核心思想。它的良好设计是保证往后架构的可持续性，可维护性。

## 四、源代码

文中代码由于篇幅原因有一定省略并不是完整逻辑，如有兴趣请Fork源代码 [https://gitee.com/hypier/barry-ddd](https://gitee.com/hypier/barry-ddd)

## 五、请关注我的公众号

![请关注我的公众号](https://oscimg.oschina.net/oscnet/up-8969dabd3beeba071b59e61139a2bb8b22f.JPEG)


