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

import scala.collection.mutable.MutableList
import com.amazonaws.services.lambda.runtime.LambdaLogger

class TestLambdaLogger extends LambdaLogger {

  private val logs = new MutableList[String]()

  def log(string: String): Unit = {
    logs += string
  }

  def getLogs = logs.toList

}
