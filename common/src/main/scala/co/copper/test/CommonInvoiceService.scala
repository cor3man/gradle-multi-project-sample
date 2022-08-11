package co.copper.test


import com.sbuslab.sbus.{Context, Sbus}
import com.sbuslab.utils.{Schedule, Subscribe}

import scala.concurrent.Future

class CommonInvoiceService(sbus: Sbus) {

  sbus.command("CommonInvoiceService - rose.command")
  sbus.command("CommonInvoiceService - rose.command.with.msg", "msg")
  sbus.event("CommonInvoiceService - rose.event", "some message")

  sbus.on[String, List[String]]("CommonInvoiceService - get-orders") { (req, context) ⇒
    Future.successful(List("Order()", "Order()"))
  }

  sbus.request[Unit]("CommonInvoiceService - rose.send-transaction.another.after.inner")

  class CommonInvoiceServiceInner {
    @Subscribe("CommonInvoiceServiceInner - invoices.calculate.InvoiceServiceInner")
    def func() = {}
  }

  object CommonObjectInner {
    @Subscribe("CommonObjectInner - invoices.calculate.ObjectInner")
    def func() = {}

    sbus.request[Unit]("CommonObjectInner - rose.send-transaction.another.after.inner")
  }
}

class CommonInvoiceServiceAdditional {
  @Subscribe("APP-InvoiceService - Common-CommonInvoiceServiceAdditional")
  def func() = {}
}