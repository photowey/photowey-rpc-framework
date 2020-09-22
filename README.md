# `photowey-rpc-framework`

> `this is a rpc framework`

## 1、`API`

### 1.1、import

```xml
<dependency>
	<groupId>com.photowey</groupId>
	<artifactId>photowey-rpc-annotation</artifactId>
	<version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 1.2、` Expose API`

```java
// the expose api
@RpcClient(value = "helloService")
public interface HelloService {

    String sayHello(String words);
}
// com.photowey.example.api.service.HelloService
```



## 2、`Provider`

### 2.1、import

```xml
<dependency>
    <groupId>com.photowey</groupId>
    <artifactId>photowey-rpc-example-api</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
<dependency>
    <groupId>com.photowey</groupId>
    <artifactId>photowey-rpc-server</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```



### 2.2、` Enable Annotation`

```java
// import config
@EnableRpcServers
```



### 2.3、` Expose Service`

```java
@Service
@RpcServer(value = HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String words) {
        return String.format("say hello to someone,words:[%s]", "I'm photowey!");
    }
}

// com.photowey.example.provider.service.impl.HelloServiceImpl
// com.photowey.example.api.service.HelloService
```



### 2.4、`Provider Config`

```yml
rpc:
  zk:
    root: "/rpc/root"
    connect-string: "192.168.0.11:2181"
    session-timeout: 5000
    connection-timeout: 5000
  network:
    port: 8848
  server:
    port: ${server.port}
    core-size: 4
    max-size: 8
```




## 3、`Consumer`
### 3.1、import

```xml
<dependency>
    <groupId>com.photowey</groupId>
    <artifactId>photowey-rpc-example-api</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
<dependency>
    <groupId>com.photowey</groupId>
    <artifactId>photowey-rpc-client</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```



### 3.2、` Enable Annotation`

```java
// import config
@EnableRpcClients(value = "com.photowey.example.api.service")

// photowey-rpc-example-api
```



### 3.3、` Use Service`

```java
@Slf4j
@RestController
@RequestMapping("/rpc")
public class HelloController {

    @Autowired
    private HelloService helloService;

    /**
     * GET :/sayHello
     *
     * @param name
     * @return
     * @see * http://localhost:4399/rpc/sayHello?name=photowey
     */
    @GetMapping("/sayHello")
    public String sayHello(@RequestParam(value = "name") String name) {
        // say hello to someone,words:[I'm photowey!]
        String sayHello = this.helloService.sayHello(name);
        log.info("the server response is:[{}]", sayHello);
        return sayHello;
    }
}

```



### 3.4、`Consumer Config`

```yml
rpc:
  client:
    zookeeper:
      root: "/rpc/root"
      connect-string: "192.168.0.11:2181"
      session-timeout: 5000
      connect-timeout: 5000
    api:
      packages: "com.photowey.example.api.service"
  cluster:
    # @see com.photowey.rpc.core.enums.ClusterStrategyEnum
    strategy: random
```