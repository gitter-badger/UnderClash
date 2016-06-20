package modules

import play.api._

import com.google.inject.AbstractModule
import com.google.inject.name.Names

import services.SchedulerService
import models.dao.ClansDAO

class StartupModule extends AbstractModule {
  def configure() = {
    bind(classOf[SchedulerService]).asEagerSingleton
    bind(classOf[ClansDAO]).asEagerSingleton
  }
}
