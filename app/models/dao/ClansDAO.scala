package models.dao

import javax.inject._
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import slick.driver.JdbcProfile
import slick.driver.PostgresDriver.api._

import play.api.Play
import play.api.db.slick.DatabaseConfigProvider

import models.Clan

class ClansTable(daoTag: Tag) extends Table[Clan](daoTag, "clan") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def tag = column[String]("tag")
  def clanLevel = column[Int]("clan_level")
  def clanPoints = column[Int]("clan_points")
  def warWinStreak = column[Int]("wan_win_streak")
  def warWins = column[Int]("war_wins")
  def warTies = column[Int]("wan_ties")
  def warLosses = column[Int]("war_losses")
  def members = column[Int]("members")
  def * = (id.?, tag, clanLevel, clanPoints, warWinStreak, warWins, warTies, warLosses, members) <> (Clan.tupled, Clan.unapply)
}

@Singleton
class ClansDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db

  val clans = TableQuery[ClansTable]

  def filterByIdQuery(id: Long) = clans.filter(_.id === id)

  def findById(id: Long): Future[Option[Clan]] = {
    db.run(filterByIdQuery(id).result.headOption)
  }

  def insert(clan: Clan): Future[Long] = {
    db.run((clans returning clans.map(_.id)) += clan)
  }

  // def update(id: Long, clan: Clan): Future[Long] = {
  //   db.run(filterByIdQuery(id).update(clan))
  // }

  // def delete(id: Long): Future[Long] = {
  //   db.run(filterByIdQuery(id).delete)
  // }

  // def list(): Future[Seq[Clan]] = {
  //   dbConfig.db.run(clans.result)
  // }
}
