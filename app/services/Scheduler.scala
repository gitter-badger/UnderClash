package services

import javax.inject._
import play.api._
import akka.actor._
import scala.concurrent.Future
import scala.concurrent.duration._
import play.api.inject.ApplicationLifecycle
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class SchedulerService @Inject() (system: ActorSystem, lifecycle: ApplicationLifecycle) {

  Logger.debug("SchedulerService instanciated")

  val scheduledTask = system.scheduler.schedule(1.minutes, 15.minutes)(job)

  // Register stop hook
  lifecycle.addStopHook { () => Future.successful(scheduledTask.cancel()) }

  Logger.debug("Scheduler started")

  def job = {
    Logger.debug("SchedulerService.job running")
  }
}