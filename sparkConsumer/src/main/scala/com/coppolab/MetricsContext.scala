package com.coppolab

class MetricsContext {

  private var request_type = ""

  private var value = 0.0

  def setRequest_type(request_type:String){
    this.request_type=request_type
  }

  def setValue(value: Double){
    this.value=value
  }

  def getRequest_type: String = {
     return this.request_type
  }

  def getValue: Double = {
    return this.value
  }

}
