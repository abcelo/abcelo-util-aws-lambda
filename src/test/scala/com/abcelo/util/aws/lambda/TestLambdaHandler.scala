package com.abcelo.util.aws.lambda

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
