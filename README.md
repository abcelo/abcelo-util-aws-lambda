# abcelo-util-aws-lambda

For those wanting to build AWS Lambda Functions that use libraries that log
using SLF4J, or who want to use SLF4J and conventional logging in their own
code, we offer `abcelo-util-aws-lamba`'s `LambdaAppender` class. Please see
the example usage and `logback.xml` file below.


### Example Lambda Handler

```scala
import com.amazonaws.services.lambda.runtime.Context
import org.slf4j.LoggerFactory

class TestLambdaHandler {
  import TestLambdaHandler._

  def doStuff(context: Context): Unit = {
    LambdaAppender.setContext(context)
    log.info("Hi!")
  }

}

object TestLambdaHandler {
  val log = LoggerFactory.getLogger(getClass)
}
```


### Example `logback.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
  <appender name="lambda" class="LambdaAppender">
    <encoder>
      <pattern>%n&gt;&gt;&gt;&gt;&gt; %d{yyyy-MM-dd'T'HH:mm:ss.SSS'Z'}: %-5p [%t] %C.%M [%F:%L]%n%m%n</pattern>
    </encoder>
    <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
      <level>info</level>
    </filter>
  </appender>
  <root level="info">
    <appender-ref ref="lambda"/>
  </root>
</configuration>
```

