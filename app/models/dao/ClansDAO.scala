package models.dao

import javax.inject.Inject

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.db.slick.DatabaseConfigProvider
import play.db.NamedDatabase

import slick.driver.JdbcProfile

import models.Clan

class ClansDAO @Inject()(@NamedDatabase("underclash") dbConfigProvider: DatabaseConfigProvider) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db
  import dbConfig.driver.api._
  private val Clans = TableQuery[ClansTable]

  def findById(id: Long): Future[Option[Clan]] = {
    db.run(Clans.filter(_.id === id).result.headOption)
  }

  def insert(clan: Clan): Future[Long] = {
    db.run((Clans returning Clans.map(_.id)) += clan)
  }

  // def update(id: Long, clan: Clan): Future[Long] = {
  //   db.run(filterByIdQuery(id).update(clan))
  // }

  // def delete(id: Long): Future[Long] = {
  //   db.run(filterByIdQuery(id).delete)
  // }

  // def list(): Future[Seq[Clan]] = {
  //   dbConfig.db.run(Clans.result)
  // }

  private class ClansTable(_tag: Tag) extends Table[Clan](_tag, "clans") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def tag = column[String]("tag")
    def clanLevel = column[Int]("clan_level")
    def clanPoints = column[Int]("clan_points")
    def warWinStreak = column[Int]("war_win_streak")
    def warWins = column[Int]("war_wins")
    def warTies = column[Int]("war_ties")
    def warLosses = column[Int]("war_losses")
    def members = column[Int]("members")

    def * = (id.?, tag, clanLevel, clanPoints, warWinStreak, warWins, warTies, warLosses, members) <> (Clan.tupled, Clan.unapply)
  }
}
