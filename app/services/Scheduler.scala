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

  private val changeMe = "changeme"

  Logger.debug("SchedulerService instanciated")

  val refreshTimeout = config.getMilliseconds("clan.refresh") match {
    case Some(t) => t milliseconds
    case None => 15.minutes
  }

  val clanTag = config.getString("clan.tag").getOrElse(changeMe)
  Logger.debug(s"Clan tag = $clanTag")
  if (clanTag == changeMe) {
    Logger.error("Clan tag not defined")
  }

  val apiToken = config.getString("api.clash.token").getOrElse(changeMe)
  if (apiToken == changeMe) {
    Logger.error("Clash API token not defined")
  }

  if (clanTag != changeMe && apiToken != changeMe) {
    val scheduledTask = system.scheduler.schedule(15.seconds, refreshTimeout)(job)
    // Register stop hook
    lifecycle.addStopHook { () => Future.successful(scheduledTask.cancel()) }

    Logger.debug("Scheduler started")
  }

  def job = {
    Logger.debug("SchedulerService.job running")

    val request = ws.url("https://api.clashofclans.com/v1/clans/%23" + clanTag)
      .withHeaders("Accept" -> "application/json")
      .withHeaders("Authorization" -> ("Bearer " + apiToken))
      .withRequestTimeout(10000.millis)

    val result = request.get().map {
      response => {
        Logger.debug(response.body)
        (response.json).as[String]
      }
    }
  }
}
