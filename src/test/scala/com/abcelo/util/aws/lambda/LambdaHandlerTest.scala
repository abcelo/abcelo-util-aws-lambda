/*
 *  Copyright 2017 Abcelo, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
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
