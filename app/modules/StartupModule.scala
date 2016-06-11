package modules

import play.api._

import com.google.inject.AbstractModule
import com.google.inject.name.Names

import services.SchedulerService

class StartupModule extends AbstractModule {
  Logger.debug("StartupModule instanciated")

  def configure() = {
    bind(classOf[SchedulerService])
  }
}
