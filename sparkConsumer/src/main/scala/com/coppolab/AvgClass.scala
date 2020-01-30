package com.coppolab

class AvgClass {
  private var counter_POST = 0
  private var counter_GET = 0
  private var time_POST = 0.0
  private var time_GET = 0.0
  private var avg_POST= 0.0
  private var avg_GET = 0.0

  def setCounter_POST(value: Int){
    this.counter_POST=value
  }
  def setCounter_GET(value: Int){
    this.counter_GET=value
  }
  def setTime_POST(value: Double){
    this.time_POST=value
  }
  def setTime_GET(value: Double){
    this.time_GET=value
  }
  def setAvg_POST(value: Double){
    this.avg_POST=value
  }
  def setAvg_GET(value: Double){
    this.avg_GET=value
  }
  def getCounter_POST: Int = {
    return this.counter_POST
  }
  def getCounter_GET: Int = {
    return this.counter_GET
  }
  def getTime_POST: Double = {
    return this.time_POST
  }
  def getTime_GET: Double = {
    return this.time_GET
  }
  def getAvg_GET: Double = {
    return this.avg_GET
  }
  def getAvg_POST: Double = {
    return this.time_POST
  }
}
