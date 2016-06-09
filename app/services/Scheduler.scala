package services

import javax.inject._
import play.api._
import akka.actor._
import scala.concurrent.Future
import scala.concurrent.duration._
import play.api.inject.ApplicationLifecycle
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.ws._

@Singleton
class SchedulerService @Inject() (system: ActorSystem, lifecycle: ApplicationLifecycle, ws: WSClient, config: Configuration) {

  Logger.info("SchedulerService instanciated")

  val scheduledTask = system.scheduler.schedule(5.seconds, 15.minutes)(job)

  // Register stop hook
  lifecycle.addStopHook { () => Future.successful(scheduledTask.cancel()) }

  Logger.info("Scheduler started")

  def job = {
    Logger.debug("SchedulerService.job running")

    val request = ws.url("https://api.clashofclans.com/v1/clans/%23" + config.getString("clan.tag").get)
      .withHeaders("Accept" -> "application/json")
      .withHeaders("Authorization" -> ("Bearer " + config.getString("api.clash.token").get))
      .withRequestTimeout(10000.millis)

    Logger.debug("SchedulerService.job headers ------------")
    Logger.debug(request.headers.toString())

    val result = request.get().map {
      response => {
        Logger.debug("SchedulerService.job data -------")
        Logger.debug(response.body)
        (response.json).as[String]
      }
    }
  }
}
