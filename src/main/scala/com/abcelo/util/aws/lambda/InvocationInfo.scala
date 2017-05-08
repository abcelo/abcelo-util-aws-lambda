package com.abcelo.util.aws.lambda

import org.slf4j.LoggerFactory

import com.amazonaws.services.lambda.runtime.ClientContext
import com.amazonaws.services.lambda.runtime.CognitoIdentity
import com.amazonaws.services.lambda.runtime.Context

case class InvocationInfo(
    requestId: String,
    logGroupName: String,
    logStreamName: String,
    functionName: String,
    functionVersion: String,
    invokedArn: String,
    invokedName: String,
    invokedAlias: String,
    identity: Option[CognitoIdentity],
    clientContext: Option[ClientContext]
) {

  override def toString(): String = {
    val sb = new StringBuilder
    sb.append("InvocationInfo(\n")
    sb.append(s"  AWS Request ID:         ${requestId}\n")
    sb.append(s"  Log Group Name:         ${logGroupName}\n")
    sb.append(s"  Log Stream Name:        ${logStreamName}\n")
    sb.append(s"  Function Name:          ${functionName}\n")
    sb.append(s"  Function Version:       ${functionVersion}\n")
    sb.append(s"  Invoked Function ARN:   ${invokedArn}\n")
    sb.append(s"  Invoked Function Name:  ${invokedName}\n")
    sb.append(s"  Invoked Function Alias: ${invokedAlias}\n")
    sb.append(s"  Identity:               ${identity}\n")
    sb.append(s"  Client Context:         ${clientContext}\n")
    sb.append(")")
    sb.toString
  }
}

object InvocationInfo {
  val log = LoggerFactory.getLogger(getClass)

  /**
   * How to parse the ARN into name and alias:
   *
   * <ul>
   *   <li>arn:aws:lambda:{{REGION}}:{{ACCOUNT}}:function:{{NAME}}:{{ALIAS}}</li>
   *   <li>arn:aws:lambda:{{REGION}}:{{ACCOUNT}}:function:{{NAME}}</li>
   * </ul>
   */
  def apply(context: Context): InvocationInfo = {
    val parts = context.getInvokedFunctionArn.split(":").drop(6)
    val name = parts(0)
    val alias = if (parts.size > 1) parts(1) else "TEST"

    val temp = InvocationInfo(
      context.getAwsRequestId,
      context.getLogGroupName,
      context.getLogStreamName,
      context.getFunctionName,
      context.getFunctionVersion,
      context.getInvokedFunctionArn,
      name,
      alias,
      Option(context.getIdentity), // May be null
      Option(context.getClientContext) // May be null
    )

    if (log.isDebugEnabled) {
      log.debug(s"${temp}")
    }

    temp
  }

}
