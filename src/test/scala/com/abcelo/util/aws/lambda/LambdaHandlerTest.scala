package com.abcelo.util.aws.lambda

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import com.amazonaws.services.lambda.runtime.Context

class LambdaHandlerTest extends FlatSpec with Matchers {

  val timestampPattern = """\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}.\d{3}Z"""
  val timestampReplace = "_____"
  val expectedLog = "\n>>>>> _____: INFO  [ScalaTest-main-running-LambdaHandlerTest] com.abcelo.util.aws.lambda.TestLambdaHandler.doStuff [TestLambdaHandler.scala:11]\nHi!\n"

  behavior of "LambdaHandler"

  it should "Log correctly" in {
    val ctx = new TestContext
    val hnd = new TestLambdaHandler
    hnd.doStuff(ctx)
    ctx.getLogs.map(_.replaceAll(timestampPattern, timestampReplace)) shouldEqual List(expectedLog)
  }

}
