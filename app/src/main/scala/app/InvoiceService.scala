package app

import akka.actor.ActorSystem
import com.sbuslab.sbus.{Context, Sbus}
import com.sbuslab.utils.{FutureHelpers, Logging, Memoize, Schedule, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.ClassTag

//Requests
//app - app
//app - base
//app - common

//Subscribes
//app - app
//base - app
//common - app
class InvoiceService(sbus: Sbus)(implicit val ec: ExecutionContext, actorSystem: ActorSystem, tag: ClassTag[String]) extends Logging with Memoize with FutureHelpers {
  //Subscribes
  @Subscribe("common - app")
  def func1() = {}

  //Requests
  sbus.request[List[String]]("app - app")
  sbus.request[List[String]]("app - app1")
  sbus.request[String]("app - base", "421")
  sbus.request[Map[String, Int]]("app - common")

}

