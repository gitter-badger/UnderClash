package services

import javax.inject._
import play.api._
import akka.actor._
import scala.concurrent.Future
import scala.concurrent.duration._
import play.api.inject.ApplicationLifecycle
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.ws._

import play.api.libs.json._
import models.JsonFormats._

import models._
import models.dao.ClansDAO

@Singleton
class SchedulerService @Inject() (system: ActorSystem, lifecycle: ApplicationLifecycle, ws: WSClient, config: Configuration, clansDAO: ClansDAO) {

  private val changeMe = "changeme"

  Logger.debug("SchedulerService instantiated")

  val refreshTimeout = config.getMilliseconds("clan.refresh") match {
    case Some(t) => t.millis
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

    request.get().map {
      response => {
        response.json.validate[ApiClan] match {
          case s: JsSuccess[ApiClan] => {
            val clan: ApiClan = s.get
            Logger.debug("Clan name = " + clan.name)

            clansDAO.insert(new Clan(
              None,
              clan.tag,
              clan.clanLevel,
              clan.clanPoints,
              clan.warWinStreak,
              clan.warWins,
              clan.warTies,
              clan.warLosses,
              clan.members))

          }
          case e: JsError => {
            Logger.error(JsError.toJson(e).toString())
          }
        }
      }
    }
  }
}
