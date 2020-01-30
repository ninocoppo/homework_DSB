package com.coppolab
import org.apache.commons.mail.{DefaultAuthenticator, SimpleEmail}
class EmailContext {



  val email = new SimpleEmail
  email.setHostName("smtp.gmail.com")
  email.setSmtpPort(587)
  email.setAuthenticator(new DefaultAuthenticator("sistemidistribuiti2019@gmail.com", "DSB2019!"))
  email.setSSL(true)
  email.setFrom("SparkConsumer@gmail.com")
  email.setSubject("ALERT SPARK")
  email.setMsg("ATTENZIONE!!!! I TEMPI MEDI SONO AUMENTATI DI PIU' DEL 20% RISPETTO AGLI ULTIMI 5 BATCH \n")
  email.addTo("redfish32@hotmail.it")
  email.send()

}
