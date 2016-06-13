package models

case class ApiImageUrls(tiny: Option[String], small: Option[String], medium: Option[String], large: Option[String])
case class ApiLocation(id: Long, name: String, isCountry: Boolean, countryCode: String)
case class ApiLeague(id: Long, name: String, iconUrls: ApiImageUrls)
case class ApiMember(tag: String, name: String, role: String, expLevel: Int, league: ApiLeague, trophies: Int, clanRank: Int, previousClanRank: Int, donations: Int, donationsReceived: Int)
case class ApiClan(tag: String, name: String, `type`: String, description: String, location: ApiLocation, badgeUrls: ApiImageUrls, clanLevel: Int, clanPoints: Int, requiredTrophies: Int, warFrequency: String, warWinStreak: Int, warWins: Int, warTies: Int, warLosses: Int, isWarLogPublic: Boolean, members: Int, memberList: List[ApiMember])

object JsonFormats {
  import play.api.libs.json.Json

  // Generates Writes and Reads for Feed and User thanks to Json Macros
  implicit val apiImageUrlsFormat = Json.format[ApiImageUrls]
  implicit val apiLocationFormat = Json.format[ApiLocation]
  implicit val apiLeagueFormat = Json.format[ApiLeague]
  implicit val apiMemberFormat = Json.format[ApiMember]
  implicit val apiClanFormat = Json.format[ApiClan]
}
