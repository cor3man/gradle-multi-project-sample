package co.copper.test


import com.sbuslab.sbus.{Context, Sbus}
import com.sbuslab.utils.{Schedule, Subscribe}

import scala.concurrent.Future

//Requests
//common - common
//common - app
//common - base

//Subscribes
//common - common
//app - common
//base - common
class CommonInvoiceService(sbus: Sbus) {
  //Subscribes
  @Subscribe("app - common")
  def func1() = {}

  @Subscribe("base - common")
  def func2() = {}

  //Requests
  sbus.request[Unit]("common - app")
}
