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

import java.io.OutputStream

import com.amazonaws.services.lambda.runtime.Context

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.OutputStreamAppender
import ch.qos.logback.classic.LoggerContext

class LambdaAppender extends OutputStreamAppender[ILoggingEvent] {
  setOutputStream(LambdaAppender.LambdaAppenderOutputStream)
}

object LambdaAppender {
  private var context: Option[Context] = None

  def setContext(c: Context): Unit = synchronized { context = Some(c) }
  def getContext: Option[Context] = synchronized { context }

  object LambdaAppenderOutputStream extends OutputStream {
    override def write(i: Int): Unit = {
      throw new UnsupportedOperationException("This OutputStream expects complete chunks of loggable bytes at a time")
    }
    override def write(bytes: Array[Byte]): Unit = {
      getContext match {
        case Some(ctx) => ctx.getLogger.log(new String(bytes, "UTF8"))
        case None => // no-op
      }
    }
  }

}
