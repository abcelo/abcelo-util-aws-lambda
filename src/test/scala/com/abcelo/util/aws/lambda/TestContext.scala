package com.abcelo.util.aws.lambda

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger

class TestContext extends Context {

  private val logger = new TestLambdaLogger

  val getAwsRequestId = "TEST-REQUEST"

  val getLogGroupName = "TEST-LOG-GROUP-NAME"

  val getLogStreamName = "TEST-LOG-STREAM-NAME"

  val getFunctionName = "doStuff"

  val getFunctionVersion = "$latest"

  val getInvokedFunctionArn = "TEST-FUNCTION-ARN"

  val getIdentity = null

  val getClientContext = null

  val getRemainingTimeInMillis = 1000

  val getMemoryLimitInMB = 100

  val getLogger: LambdaLogger = logger

  def getLogs = logger.getLogs

}
