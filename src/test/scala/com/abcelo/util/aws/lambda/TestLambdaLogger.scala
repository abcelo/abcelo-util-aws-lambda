package com.abcelo.util.aws.lambda

import scala.collection.mutable.MutableList
import com.amazonaws.services.lambda.runtime.LambdaLogger

class TestLambdaLogger extends LambdaLogger {

  private val logs = new MutableList[String]()

  def log(string: String): Unit = {
    logs += string
  }

  def getLogs = logs.toList

}
