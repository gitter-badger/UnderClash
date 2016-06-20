package models

case class Clan(
  id: Option[Long],
  tag: String,
  clanLevel: Int,
  clanPoints: Int,
  warWinStreak: Int,
  warWins: Int,
  warTies: Int,
  warLosses: Int,
  members: Int)
