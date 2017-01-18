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
