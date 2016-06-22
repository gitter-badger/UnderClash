package modules

import com.google.inject.AbstractModule

import services.SchedulerService

class StartupModule extends AbstractModule {
  def configure() = {
    bind(classOf[SchedulerService]).asEagerSingleton
  }
}
