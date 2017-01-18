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
        val out = new ByteArrayOutputStream
        enc.init(out)
        enc.doEncode(event)
        val str = out.toString("UTF-8")
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
