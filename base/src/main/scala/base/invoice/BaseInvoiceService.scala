package base.invoice

import com.sbuslab.sbus.{Context, Sbus}
import com.sbuslab.utils.{Schedule, Subscribe}

import scala.concurrent.Future

class BaseInvoiceService(sbus: Sbus) {

  @Schedule("41 minutes")
  @Subscribe("BaseInvoiceService - invoices.calculate-fees")
  def calculateFees(cmd: Any)(implicit context: Context): Future[Unit] = {
    Future.unit
  }

  sbus.request[List[String]]("Base-BaseInvoiceService - APP-InvoiceService")
  sbus.request[Map[String, Int]]("BaseInvoiceService - rose.send-transaction.map")

  @Subscribe("BaseInvoiceService - invoices.calculate.testfunc")
  def func() = {}
}
