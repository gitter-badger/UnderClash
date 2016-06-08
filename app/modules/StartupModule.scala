package modules

import com.google.inject.AbstractModule
import com.google.inject.name.Names

import services.SchedulerService

class StartupModule extends AbstractModule {
  def configure() = {
    bind(classOf[SchedulerService])
  }
}
