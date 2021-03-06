package utils.web

import models.ResponseMessage
import play.api.mvc.WebSocket.MessageFlowTransformer
import utils.{JsonSerializers, Logging}

class MessageFrameFormatter(debug: Boolean) extends Logging {
  val transformer = MessageFlowTransformer.stringMessageFlowTransformer.map { s =>
    JsonSerializers.readRequestMessage(s)
  }.contramap { m: ResponseMessage => JsonSerializers.writeResponseMessage(m, debug) }
}
