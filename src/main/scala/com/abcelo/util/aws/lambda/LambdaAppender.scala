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

import ch.qos.logback.core.AppenderBase
import com.amazonaws.services.lambda.runtime.Context
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.encoder.Encoder
import java.io.ByteArrayOutputStream

class LambdaAppender extends AppenderBase[ILoggingEvent] {

  private var encoder: Option[Encoder[ILoggingEvent]] = None

  def setEncoder(e: Encoder[ILoggingEvent]): Unit = synchronized { encoder = Some(e) }
  def getEncoder(): Encoder[ILoggingEvent] = synchronized { encoder.getOrElse(null) }

  def append(event: ILoggingEvent): Unit = {
    (LambdaAppender.getContext, encoder) match {
      case (Some(ctx), Some(enc)) => {
        val bytes = enc.encode(event)
        val str = new String(bytes, "UTF8")
        ctx.getLogger.log(str)
      }
      case _ => // no-op
    }
  }
}

object LambdaAppender {
  private var context: Option[Context] = None

  def setContext(c: Context): Unit = synchronized { context = Some(c) }
  def getContext: Option[Context] = synchronized { context }
}
