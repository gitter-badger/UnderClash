package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class HomeController @Inject() (implicit val config: Configuration) extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

}
