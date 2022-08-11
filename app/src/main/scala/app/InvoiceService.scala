package app

import akka.actor.ActorSystem
import com.sbuslab.sbus.{Context, Sbus}
import com.sbuslab.utils.{FutureHelpers, Logging, Memoize, Schedule, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.ClassTag

@Service
@Autowired
class InvoiceService(sbus: Sbus)(implicit val ec: ExecutionContext, actorSystem: ActorSystem, tag: ClassTag[String]) extends Logging with Memoize with FutureHelpers {

  @Schedule("41 minutes")
  @Subscribe("APP-InvoiceService - APP-InvoiceService")
  def calculateFees(cmd: Any)(implicit context: Context): Future[Unit] = {
    Future.unit
  }

  sbus.request[List[String]]("APP-InvoiceService - APP-InvoiceService")
  sbus.request[Map[String, Int]]("APP-InvoiceService - Common-CommonInvoiceServiceAdditional")

  @Subscribe("Base-BaseInvoiceService - APP-InvoiceService")
  def func() = {}


  sbus.request[String]("APP-InvoiceService - APP-InvoiceService", "421")
  sbus.request[Unit]("APP-InvoiceService - Common-CommonInvoiceServiceAdditional")


  sbus.command("InvoiceService - rose.command")
  sbus.command("InvoiceService - rose.command.with.msg", "msg"
  )
  sbus.event("InvoiceService - rose.event", "some message")

  sbus.on[String, List[String]]("InvoiceService - get-orders") { (req, context) ⇒
    Future.successful(List("Order()", "Order()"))
  }

  class InvoiceServiceAdditional {
    @Subscribe("InvoiceServiceAdditional - invoices.calculate.InvoiceServiceAdditional")
    def func() = {}
  }

  sbus.request[Unit]("InvoiceService - rose.send-transaction.another.after.inner")

  class InvoiceServiceInner {
    @Subscribe("InvoiceServiceInner - invoices.calculate.InvoiceServiceInner")
    def func() = {}
  }

  object Outer {
    object ObjectInner {
      @Subscribe("ObjectInner - invoices.calculate.ObjectInner")
      def func() = {}

      sbus.request[Unit]("ObjectInner - rose.send-transaction.another.after.inner")
    }
  }
}

class InvoiceServiceAdditional {
  @Subscribe("InvoiceServiceAdditionalOut - invoices.calculate.InvoiceServiceAdditional.out")
  def func() = {}
}

class AdditionalClassInTests(sbus: Sbus)(implicit val ec: ExecutionContext, actorSystem: ActorSystem, tag: ClassTag[String]) extends Logging with Memoize with FutureHelpers {

  @Subscribe("AdditionalClassInTests - invoices.calculate.AdditionalClassInTests")
  def funcx() = {}

  sbus.request[List[String]]("AdditionalClassInTests - rose.send-transaction.list")

}
