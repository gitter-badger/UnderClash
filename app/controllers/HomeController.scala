package controllers

import javax.inject.{Inject, Singleton}
import play.api._
import play.api.mvc.{Action, Controller}

import models.Clan
import models.dao.ClansDAO

@Singleton
class HomeController @Inject() (implicit val config: Configuration, clansDAO: ClansDAO) extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def manifest = Action {
    Ok(views.html.manifest()).as("application/json")
  }

  def ieconfig = Action {
    Ok(views.html.ieconfig()).as("application/xml")
  }
}
