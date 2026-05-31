# Microservices - Simple Learning Guide

## What We Built

A **multi-module Spring Boot project** with 5 microservices that work together:

```
Microservices/project/
├── pom.xml                          (parent - manages all modules)
├── eureka-server/   (port 8761)     ← Service Discovery
├── greet-service/   (port 8080)     ← Basic Microservice
├── account-service/ (port 8081)     ← Bank Account API
├── loan-service/    (port 8082)     ← Bank Loan API
└── api-gateway/     (port 9090)     ← Single Entry Point
```

---

## 1. Theory: Monolithic vs Microservices

### Monolithic Application (The Old Way)
Everything in one big app → one EAR/WAR file.
```
┌─────────────────────────────────────┐
│         ONE BIG APPLICATION         │
│  ┌─────┐ ┌─────┐ ┌──────┐ ┌─────┐ │
│  │Account│ │Loan │ │Insurance│ │SMS │ │
│  └─────┘ └─────┘ └──────┘ └─────┘ │
└─────────────────────────────────────┘
      If Account fails → EVERYTHING fails!
```

**Problem:** One service with a memory leak can bring down the entire bank application.

### Microservices (The New Way)
Split into small independent services, each running separately.
```
┌──────────┐  ┌──────────┐  ┌──────────┐
│ Account  │  │   Loan   │  │Insurance │
│ Service  │  │  Service │  │ Service  │
│ (port8081)│  │ (port8082)│  │ (port8083)│
└──────────┘  └──────────┘  └──────────┘
     If Account fails → Loan & Insurance still work!
```

**Benefits:**
- Independent - one failure doesn't bring down others
- Scalable - add more instances as needed
- Each team can work on their own service
- Can use different tech stacks per service

---

## 2. Eureka Discovery Server (port 8761)

### What is it?
A **phonebook for microservices**. Every service registers itself here so others can find it.

### How it works
```
┌─────────────────────────────────────────┐
│         Eureka Server (port 8761)       │
│  Registered Services:                   │
│  ├── greet-service (192.168.1.5:8080)   │
│  ├── account-service (192.168.1.5:8081) │
│  ├── loan-service (192.168.1.5:8082)    │
│  └── api-gateway (192.168.1.5:9090)     │
└─────────────────────────────────────────┘
```

### Key Code
```java
@EnableEurekaServer  // ← Makes this app a Eureka server
@SpringBootApplication
public class EurekaServerApplication { ... }
```

```properties
# application.properties
server.port=8761
eureka.client.register-with-eureka=false     # Don't register itself
eureka.client.fetch-registry=false           # Don't fetch from itself
```

**URL:** http://localhost:8761 (see all registered services)

---

## 3. Microservices (greet-service, account-service, loan-service)

### What are they?
Small Spring Boot apps, each with a specific job. They register with Eureka.

### greet-service
```java
@EnableDiscoveryClient  // ← Register with Eureka
@SpringBootApplication
public class GreetServiceApplication { ... }

@RestController
public class GreetController {
    @GetMapping("/greet")
    public String greet() {
        return "Hello World";
    }
}
```
```properties
spring.application.name=greet-service  # Name shown in Eureka
server.port=8080
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

### account-service (port 8081)
```
GET /accounts/00987987973432
→ { "number": "00987987973432", "type": "savings", "balance": 234343 }
```

### loan-service (port 8082)
```
GET /loans/H00987987972342
→ { "number": "H00987987972342", "type": "car", "loan": 400000, ... }
```

### Key Annotation
```java
@EnableDiscoveryClient  // Tells Spring: "Register me with Eureka!"
```

---

## 4. API Gateway (port 9090)

### What is it?
A **single door** through which all requests pass. Routes requests to the right microservice.

### Without Gateway
```
Client → Account Service (port 8081)
Client → Loan Service (port 8082)
Client → Greet Service (port 8080)
```

### With Gateway
```
Client → API Gateway (port 9090) → Account Service
                                → Loan Service
                                → Greet Service
```

### How it Routes
```
http://localhost:9090/greet-service/greet    → greet-service:8080/greet
http://localhost:9090/account-service/accounts/123 → account-service:8081/accounts/123
http://localhost:9090/loan-service/loans/456 → loan-service:8082/loans/456
```

### Key Code
```properties
# application.properties
spring.cloud.gateway.discovery.locator.enabled=true        # Auto-route to Eureka services
spring.cloud.gateway.discovery.locator.lower-case-service-id=true  # Use lowercase URLs
```

### Global Logging Filter
```java
@Component
public class LogFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Incoming Request: " + exchange.getRequest().getURI());
        return chain.filter(exchange);
    }
}
```
This logs every request that hits the API Gateway.

---

## 5. How to Run Everything

### Step 1: Start Eureka Server
```bash
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home mvn spring-boot:run -pl eureka-server
```
→ Open http://localhost:8761 (empty registry)

### Step 2: Start Microservices
In separate terminals:
```bash
mvn spring-boot:run -pl greet-service    # http://localhost:8080/greet
mvn spring-boot:run -pl account-service  # http://localhost:8081/accounts/123
mvn spring-boot:run -pl loan-service     # http://localhost:8082/loans/456
```
→ Refresh http://localhost:8761 → services appear!

### Step 3: Start API Gateway
```bash
mvn spring-boot:run -pl api-gateway
```
→ Access all via http://localhost:9090
```
http://localhost:9090/greet-service/greet
http://localhost:9090/account-service/accounts/123
http://localhost:9090/loan-service/loans/456
```

---

## Architecture Diagram

```
                           ┌─────────────────┐
                           │  Eureka Server   │
                           │   (port 8761)    │
                           └────────┬────────┘
                                    │ registers to
          ┌─────────────────────────┼─────────────────────────┐
          │                         │                         │
          ▼                         ▼                         ▼
┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐
│   Greet Service   │  │  Account Service  │  │   Loan Service   │
│    (port 8080)    │  │   (port 8081)     │  │   (port 8082)    │
│   /greet          │  │  /accounts/{id}   │  │  /loans/{id}     │
└──────────────────┘  └──────────────────┘  └──────────────────┘
          ▲                    ▲                    ▲
          │                    │                    │
          └──────────┬─────────┴─────────┬──────────┘
                     │                   │
             ┌───────┴───────┐   ┌───────┴───────┐
             │   Client App  │   │  API Gateway   │
             │               │   │  (port 9090)   │
             └───────────────┘   │  + LogFilter    │
                                 └────────────────┘
```

## Key Terms
| Term | Meaning |
|------|---------|
| **Eureka** | Service discovery - phonebook for microservices |
| **API Gateway** | Single entry point that routes requests |
| **Service Registry** | List of all running services and their addresses |
| **Discovery Client** | Lets a service register with Eureka |
| **Global Filter** | Code that runs on every gateway request |
| **Load Balancer** | Distributes requests across multiple instances |

## Benefits Recap
- ✅ One service failing doesn't crash others
- ✅ Easy to scale - just start another instance
- ✅ Single entry point via API Gateway
- ✅ All services auto-discover each other
- ✅ Request logging at the gateway level
