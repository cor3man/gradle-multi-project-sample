package base.invoice

import com.sbuslab.sbus.{Context, Sbus}
import com.sbuslab.utils.{Schedule, Subscribe}

import scala.concurrent.Future


//Requests
//base - base
//base - app
//base - common

//Subscribes
//base - base
//app - base
//common - base
class BaseInvoiceService(sbus: Sbus) {
  //Subscribes
  @Subscribe("app - base")
  def func1() = {}

  //Requests
  sbus.request[List[String]]("base - common")
}
