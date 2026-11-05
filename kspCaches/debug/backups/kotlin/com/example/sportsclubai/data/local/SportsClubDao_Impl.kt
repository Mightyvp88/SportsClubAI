package com.example.sportsclubai.`data`.local

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.example.sportsclubai.`data`.model.Attendance
import com.example.sportsclubai.`data`.model.Club
import com.example.sportsclubai.`data`.model.EventType
import com.example.sportsclubai.`data`.model.MatchResult
import com.example.sportsclubai.`data`.model.PlayerStats
import com.example.sportsclubai.`data`.model.Schedule
import com.example.sportsclubai.`data`.model.SkillLevel
import com.example.sportsclubai.`data`.model.Team
import com.example.sportsclubai.`data`.model.User
import com.example.sportsclubai.`data`.model.UserRole
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SportsClubDao_Impl(
  __db: RoomDatabase,
) : SportsClubDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUser: EntityInsertAdapter<User>

  private val __converters: Converters = Converters()

  private val __insertAdapterOfClub: EntityInsertAdapter<Club>

  private val __insertAdapterOfTeam: EntityInsertAdapter<Team>

  private val __insertAdapterOfSchedule: EntityInsertAdapter<Schedule>

  private val __insertAdapterOfAttendance: EntityInsertAdapter<Attendance>

  private val __insertAdapterOfMatchResult: EntityInsertAdapter<MatchResult>

  private val __insertAdapterOfPlayerStats: EntityInsertAdapter<PlayerStats>
  init {
    this.__db = __db
    this.__insertAdapterOfUser = object : EntityInsertAdapter<User>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `users` (`id`,`name`,`email`,`role`,`age`,`gender`,`skillLevel`,`playingPosition`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.email)
        val _tmp: String = __converters.fromUserRole(entity.role)
        statement.bindText(4, _tmp)
        val _tmpAge: Int? = entity.age
        if (_tmpAge == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpAge.toLong())
        }
        val _tmpGender: String? = entity.gender
        if (_tmpGender == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpGender)
        }
        val _tmp_1: String = __converters.fromSkillLevel(entity.skillLevel)
        statement.bindText(7, _tmp_1)
        val _tmpPlayingPosition: String? = entity.playingPosition
        if (_tmpPlayingPosition == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpPlayingPosition)
        }
      }
    }
    this.__insertAdapterOfClub = object : EntityInsertAdapter<Club>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `clubs` (`id`,`name`,`description`,`location`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Club) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.description)
        statement.bindText(4, entity.location)
      }
    }
    this.__insertAdapterOfTeam = object : EntityInsertAdapter<Team>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `teams` (`id`,`clubId`,`name`,`coachId`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Team) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.clubId)
        statement.bindText(3, entity.name)
        statement.bindText(4, entity.coachId)
      }
    }
    this.__insertAdapterOfSchedule = object : EntityInsertAdapter<Schedule>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `schedules` (`id`,`clubId`,`teamId`,`title`,`description`,`startTime`,`endTime`,`type`,`location`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Schedule) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.clubId)
        val _tmpTeamId: String? = entity.teamId
        if (_tmpTeamId == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpTeamId)
        }
        statement.bindText(4, entity.title)
        statement.bindText(5, entity.description)
        statement.bindLong(6, entity.startTime)
        statement.bindLong(7, entity.endTime)
        val _tmp: String = __converters.fromEventType(entity.type)
        statement.bindText(8, _tmp)
        statement.bindText(9, entity.location)
      }
    }
    this.__insertAdapterOfAttendance = object : EntityInsertAdapter<Attendance>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `attendance` (`scheduleId`,`userId`,`isPresent`,`timestamp`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Attendance) {
        statement.bindText(1, entity.scheduleId)
        statement.bindText(2, entity.userId)
        val _tmp: Int = if (entity.isPresent) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindLong(4, entity.timestamp)
      }
    }
    this.__insertAdapterOfMatchResult = object : EntityInsertAdapter<MatchResult>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `match_results` (`id`,`scheduleId`,`teamAId`,`teamBId`,`scoreA`,`scoreB`,`winnerTeamId`,`date`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: MatchResult) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.scheduleId)
        statement.bindText(3, entity.teamAId)
        statement.bindText(4, entity.teamBId)
        statement.bindLong(5, entity.scoreA.toLong())
        statement.bindLong(6, entity.scoreB.toLong())
        val _tmpWinnerTeamId: String? = entity.winnerTeamId
        if (_tmpWinnerTeamId == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpWinnerTeamId)
        }
        statement.bindLong(8, entity.date)
      }
    }
    this.__insertAdapterOfPlayerStats = object : EntityInsertAdapter<PlayerStats>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `player_stats` (`id`,`userId`,`matchId`,`points`,`assists`,`errors`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PlayerStats) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.userId)
        statement.bindText(3, entity.matchId)
        statement.bindLong(4, entity.points.toLong())
        val _tmpAssists: Int? = entity.assists
        if (_tmpAssists == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpAssists.toLong())
        }
        val _tmpErrors: Int? = entity.errors
        if (_tmpErrors == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpErrors.toLong())
        }
      }
    }
  }

  public override suspend fun insertUser(user: User): Unit = performSuspending(__db, false, true) {
      _connection ->
    __insertAdapterOfUser.insert(_connection, user)
  }

  public override suspend fun insertClub(club: Club): Unit = performSuspending(__db, false, true) {
      _connection ->
    __insertAdapterOfClub.insert(_connection, club)
  }

  public override suspend fun insertTeam(team: Team): Unit = performSuspending(__db, false, true) {
      _connection ->
    __insertAdapterOfTeam.insert(_connection, team)
  }

  public override suspend fun insertSchedule(schedule: Schedule): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfSchedule.insert(_connection, schedule)
  }

  public override suspend fun insertAttendance(attendance: Attendance): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfAttendance.insert(_connection, attendance)
  }

  public override suspend fun insertMatchResult(matchResult: MatchResult): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfMatchResult.insert(_connection, matchResult)
  }

  public override suspend fun insertPlayerStats(stats: PlayerStats): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfPlayerStats.insert(_connection, stats)
  }

  public override suspend fun getUserById(userId: String): User? {
    val _sql: String = "SELECT * FROM users WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, userId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfSkillLevel: Int = getColumnIndexOrThrow(_stmt, "skillLevel")
        val _columnIndexOfPlayingPosition: Int = getColumnIndexOrThrow(_stmt, "playingPosition")
        val _result: User?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpRole: UserRole
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfRole)
          _tmpRole = __converters.toUserRole(_tmp)
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpSkillLevel: SkillLevel
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfSkillLevel)
          _tmpSkillLevel = __converters.toSkillLevel(_tmp_1)
          val _tmpPlayingPosition: String?
          if (_stmt.isNull(_columnIndexOfPlayingPosition)) {
            _tmpPlayingPosition = null
          } else {
            _tmpPlayingPosition = _stmt.getText(_columnIndexOfPlayingPosition)
          }
          _result =
              User(_tmpId,_tmpName,_tmpEmail,_tmpRole,_tmpAge,_tmpGender,_tmpSkillLevel,_tmpPlayingPosition)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllUsers(): Flow<List<User>> {
    val _sql: String = "SELECT * FROM users"
    return createFlow(__db, false, arrayOf("users")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfSkillLevel: Int = getColumnIndexOrThrow(_stmt, "skillLevel")
        val _columnIndexOfPlayingPosition: Int = getColumnIndexOrThrow(_stmt, "playingPosition")
        val _result: MutableList<User> = mutableListOf()
        while (_stmt.step()) {
          val _item: User
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpRole: UserRole
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfRole)
          _tmpRole = __converters.toUserRole(_tmp)
          val _tmpAge: Int?
          if (_stmt.isNull(_columnIndexOfAge)) {
            _tmpAge = null
          } else {
            _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          }
          val _tmpGender: String?
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender)
          }
          val _tmpSkillLevel: SkillLevel
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfSkillLevel)
          _tmpSkillLevel = __converters.toSkillLevel(_tmp_1)
          val _tmpPlayingPosition: String?
          if (_stmt.isNull(_columnIndexOfPlayingPosition)) {
            _tmpPlayingPosition = null
          } else {
            _tmpPlayingPosition = _stmt.getText(_columnIndexOfPlayingPosition)
          }
          _item =
              User(_tmpId,_tmpName,_tmpEmail,_tmpRole,_tmpAge,_tmpGender,_tmpSkillLevel,_tmpPlayingPosition)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getClubById(clubId: String): Club? {
    val _sql: String = "SELECT * FROM clubs WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, clubId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfLocation: Int = getColumnIndexOrThrow(_stmt, "location")
        val _result: Club?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpLocation: String
          _tmpLocation = _stmt.getText(_columnIndexOfLocation)
          _result = Club(_tmpId,_tmpName,_tmpDescription,_tmpLocation)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTeamsByClub(clubId: String): Flow<List<Team>> {
    val _sql: String = "SELECT * FROM teams WHERE clubId = ?"
    return createFlow(__db, false, arrayOf("teams")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, clubId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfClubId: Int = getColumnIndexOrThrow(_stmt, "clubId")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfCoachId: Int = getColumnIndexOrThrow(_stmt, "coachId")
        val _result: MutableList<Team> = mutableListOf()
        while (_stmt.step()) {
          val _item: Team
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpClubId: String
          _tmpClubId = _stmt.getText(_columnIndexOfClubId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpCoachId: String
          _tmpCoachId = _stmt.getText(_columnIndexOfCoachId)
          _item = Team(_tmpId,_tmpClubId,_tmpName,_tmpCoachId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getSchedulesByClub(clubId: String): Flow<List<Schedule>> {
    val _sql: String = "SELECT * FROM schedules WHERE clubId = ? ORDER BY startTime DESC"
    return createFlow(__db, false, arrayOf("schedules")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, clubId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfClubId: Int = getColumnIndexOrThrow(_stmt, "clubId")
        val _columnIndexOfTeamId: Int = getColumnIndexOrThrow(_stmt, "teamId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfStartTime: Int = getColumnIndexOrThrow(_stmt, "startTime")
        val _columnIndexOfEndTime: Int = getColumnIndexOrThrow(_stmt, "endTime")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfLocation: Int = getColumnIndexOrThrow(_stmt, "location")
        val _result: MutableList<Schedule> = mutableListOf()
        while (_stmt.step()) {
          val _item: Schedule
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpClubId: String
          _tmpClubId = _stmt.getText(_columnIndexOfClubId)
          val _tmpTeamId: String?
          if (_stmt.isNull(_columnIndexOfTeamId)) {
            _tmpTeamId = null
          } else {
            _tmpTeamId = _stmt.getText(_columnIndexOfTeamId)
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long
          _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          val _tmpType: EventType
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfType)
          _tmpType = __converters.toEventType(_tmp)
          val _tmpLocation: String
          _tmpLocation = _stmt.getText(_columnIndexOfLocation)
          _item =
              Schedule(_tmpId,_tmpClubId,_tmpTeamId,_tmpTitle,_tmpDescription,_tmpStartTime,_tmpEndTime,_tmpType,_tmpLocation)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAttendance(scheduleId: String, userId: String): Attendance? {
    val _sql: String = "SELECT * FROM attendance WHERE scheduleId = ? AND userId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, scheduleId)
        _argIndex = 2
        _stmt.bindText(_argIndex, userId)
        val _columnIndexOfScheduleId: Int = getColumnIndexOrThrow(_stmt, "scheduleId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfIsPresent: Int = getColumnIndexOrThrow(_stmt, "isPresent")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _result: Attendance?
        if (_stmt.step()) {
          val _tmpScheduleId: String
          _tmpScheduleId = _stmt.getText(_columnIndexOfScheduleId)
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpIsPresent: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsPresent).toInt()
          _tmpIsPresent = _tmp != 0
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          _result = Attendance(_tmpScheduleId,_tmpUserId,_tmpIsPresent,_tmpTimestamp)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAttendanceBySchedule(scheduleId: String): Flow<List<Attendance>> {
    val _sql: String = "SELECT * FROM attendance WHERE scheduleId = ?"
    return createFlow(__db, false, arrayOf("attendance")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, scheduleId)
        val _columnIndexOfScheduleId: Int = getColumnIndexOrThrow(_stmt, "scheduleId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfIsPresent: Int = getColumnIndexOrThrow(_stmt, "isPresent")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _result: MutableList<Attendance> = mutableListOf()
        while (_stmt.step()) {
          val _item: Attendance
          val _tmpScheduleId: String
          _tmpScheduleId = _stmt.getText(_columnIndexOfScheduleId)
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpIsPresent: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsPresent).toInt()
          _tmpIsPresent = _tmp != 0
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          _item = Attendance(_tmpScheduleId,_tmpUserId,_tmpIsPresent,_tmpTimestamp)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllMatchResults(): Flow<List<MatchResult>> {
    val _sql: String = "SELECT * FROM match_results ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("match_results")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfScheduleId: Int = getColumnIndexOrThrow(_stmt, "scheduleId")
        val _columnIndexOfTeamAId: Int = getColumnIndexOrThrow(_stmt, "teamAId")
        val _columnIndexOfTeamBId: Int = getColumnIndexOrThrow(_stmt, "teamBId")
        val _columnIndexOfScoreA: Int = getColumnIndexOrThrow(_stmt, "scoreA")
        val _columnIndexOfScoreB: Int = getColumnIndexOrThrow(_stmt, "scoreB")
        val _columnIndexOfWinnerTeamId: Int = getColumnIndexOrThrow(_stmt, "winnerTeamId")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _result: MutableList<MatchResult> = mutableListOf()
        while (_stmt.step()) {
          val _item: MatchResult
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpScheduleId: String
          _tmpScheduleId = _stmt.getText(_columnIndexOfScheduleId)
          val _tmpTeamAId: String
          _tmpTeamAId = _stmt.getText(_columnIndexOfTeamAId)
          val _tmpTeamBId: String
          _tmpTeamBId = _stmt.getText(_columnIndexOfTeamBId)
          val _tmpScoreA: Int
          _tmpScoreA = _stmt.getLong(_columnIndexOfScoreA).toInt()
          val _tmpScoreB: Int
          _tmpScoreB = _stmt.getLong(_columnIndexOfScoreB).toInt()
          val _tmpWinnerTeamId: String?
          if (_stmt.isNull(_columnIndexOfWinnerTeamId)) {
            _tmpWinnerTeamId = null
          } else {
            _tmpWinnerTeamId = _stmt.getText(_columnIndexOfWinnerTeamId)
          }
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          _item =
              MatchResult(_tmpId,_tmpScheduleId,_tmpTeamAId,_tmpTeamBId,_tmpScoreA,_tmpScoreB,_tmpWinnerTeamId,_tmpDate)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getStatsByUserId(userId: String): Flow<List<PlayerStats>> {
    val _sql: String = "SELECT * FROM player_stats WHERE userId = ?"
    return createFlow(__db, false, arrayOf("player_stats")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, userId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfMatchId: Int = getColumnIndexOrThrow(_stmt, "matchId")
        val _columnIndexOfPoints: Int = getColumnIndexOrThrow(_stmt, "points")
        val _columnIndexOfAssists: Int = getColumnIndexOrThrow(_stmt, "assists")
        val _columnIndexOfErrors: Int = getColumnIndexOrThrow(_stmt, "errors")
        val _result: MutableList<PlayerStats> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlayerStats
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpUserId: String
          _tmpUserId = _stmt.getText(_columnIndexOfUserId)
          val _tmpMatchId: String
          _tmpMatchId = _stmt.getText(_columnIndexOfMatchId)
          val _tmpPoints: Int
          _tmpPoints = _stmt.getLong(_columnIndexOfPoints).toInt()
          val _tmpAssists: Int?
          if (_stmt.isNull(_columnIndexOfAssists)) {
            _tmpAssists = null
          } else {
            _tmpAssists = _stmt.getLong(_columnIndexOfAssists).toInt()
          }
          val _tmpErrors: Int?
          if (_stmt.isNull(_columnIndexOfErrors)) {
            _tmpErrors = null
          } else {
            _tmpErrors = _stmt.getLong(_columnIndexOfErrors).toInt()
          }
          _item = PlayerStats(_tmpId,_tmpUserId,_tmpMatchId,_tmpPoints,_tmpAssists,_tmpErrors)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
