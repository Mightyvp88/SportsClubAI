package com.example.sportsclubai.`data`.local

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _sportsClubDao: Lazy<SportsClubDao> = lazy {
    SportsClubDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "41f4d934d8e3ab402c190655d77e4592", "6d7b437512e406bcf0182236b0229d40") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `email` TEXT NOT NULL, `role` TEXT NOT NULL, `age` INTEGER, `gender` TEXT, `skillLevel` TEXT NOT NULL, `playingPosition` TEXT, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `clubs` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `location` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `teams` (`id` TEXT NOT NULL, `clubId` TEXT NOT NULL, `name` TEXT NOT NULL, `coachId` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `team_members` (`teamId` TEXT NOT NULL, `userId` TEXT NOT NULL, PRIMARY KEY(`teamId`, `userId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `schedules` (`id` TEXT NOT NULL, `clubId` TEXT NOT NULL, `teamId` TEXT, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `startTime` INTEGER NOT NULL, `endTime` INTEGER NOT NULL, `type` TEXT NOT NULL, `location` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `attendance` (`scheduleId` TEXT NOT NULL, `userId` TEXT NOT NULL, `isPresent` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`scheduleId`, `userId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `match_results` (`id` TEXT NOT NULL, `scheduleId` TEXT NOT NULL, `teamAId` TEXT NOT NULL, `teamBId` TEXT NOT NULL, `scoreA` INTEGER NOT NULL, `scoreB` INTEGER NOT NULL, `winnerTeamId` TEXT, `date` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `player_stats` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` TEXT NOT NULL, `matchId` TEXT NOT NULL, `points` INTEGER NOT NULL, `assists` INTEGER, `errors` INTEGER)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '41f4d934d8e3ab402c190655d77e4592')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `users`")
        connection.execSQL("DROP TABLE IF EXISTS `clubs`")
        connection.execSQL("DROP TABLE IF EXISTS `teams`")
        connection.execSQL("DROP TABLE IF EXISTS `team_members`")
        connection.execSQL("DROP TABLE IF EXISTS `schedules`")
        connection.execSQL("DROP TABLE IF EXISTS `attendance`")
        connection.execSQL("DROP TABLE IF EXISTS `match_results`")
        connection.execSQL("DROP TABLE IF EXISTS `player_stats`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsUsers: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUsers.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("email", TableInfo.Column("email", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("role", TableInfo.Column("role", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("age", TableInfo.Column("age", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("gender", TableInfo.Column("gender", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("skillLevel", TableInfo.Column("skillLevel", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("playingPosition", TableInfo.Column("playingPosition", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUsers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUsers: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUsers: TableInfo = TableInfo("users", _columnsUsers, _foreignKeysUsers,
            _indicesUsers)
        val _existingUsers: TableInfo = read(connection, "users")
        if (!_infoUsers.equals(_existingUsers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |users(com.example.sportsclubai.data.model.User).
              | Expected:
              |""".trimMargin() + _infoUsers + """
              |
              | Found:
              |""".trimMargin() + _existingUsers)
        }
        val _columnsClubs: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsClubs.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClubs.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClubs.put("description", TableInfo.Column("description", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClubs.put("location", TableInfo.Column("location", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysClubs: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesClubs: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoClubs: TableInfo = TableInfo("clubs", _columnsClubs, _foreignKeysClubs,
            _indicesClubs)
        val _existingClubs: TableInfo = read(connection, "clubs")
        if (!_infoClubs.equals(_existingClubs)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |clubs(com.example.sportsclubai.data.model.Club).
              | Expected:
              |""".trimMargin() + _infoClubs + """
              |
              | Found:
              |""".trimMargin() + _existingClubs)
        }
        val _columnsTeams: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsTeams.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTeams.put("clubId", TableInfo.Column("clubId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTeams.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTeams.put("coachId", TableInfo.Column("coachId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysTeams: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesTeams: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoTeams: TableInfo = TableInfo("teams", _columnsTeams, _foreignKeysTeams,
            _indicesTeams)
        val _existingTeams: TableInfo = read(connection, "teams")
        if (!_infoTeams.equals(_existingTeams)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |teams(com.example.sportsclubai.data.model.Team).
              | Expected:
              |""".trimMargin() + _infoTeams + """
              |
              | Found:
              |""".trimMargin() + _existingTeams)
        }
        val _columnsTeamMembers: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsTeamMembers.put("teamId", TableInfo.Column("teamId", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTeamMembers.put("userId", TableInfo.Column("userId", "TEXT", true, 2, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysTeamMembers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesTeamMembers: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoTeamMembers: TableInfo = TableInfo("team_members", _columnsTeamMembers,
            _foreignKeysTeamMembers, _indicesTeamMembers)
        val _existingTeamMembers: TableInfo = read(connection, "team_members")
        if (!_infoTeamMembers.equals(_existingTeamMembers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |team_members(com.example.sportsclubai.data.model.TeamMember).
              | Expected:
              |""".trimMargin() + _infoTeamMembers + """
              |
              | Found:
              |""".trimMargin() + _existingTeamMembers)
        }
        val _columnsSchedules: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSchedules.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("clubId", TableInfo.Column("clubId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("teamId", TableInfo.Column("teamId", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("description", TableInfo.Column("description", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("startTime", TableInfo.Column("startTime", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("endTime", TableInfo.Column("endTime", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("type", TableInfo.Column("type", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("location", TableInfo.Column("location", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSchedules: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSchedules: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSchedules: TableInfo = TableInfo("schedules", _columnsSchedules,
            _foreignKeysSchedules, _indicesSchedules)
        val _existingSchedules: TableInfo = read(connection, "schedules")
        if (!_infoSchedules.equals(_existingSchedules)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |schedules(com.example.sportsclubai.data.model.Schedule).
              | Expected:
              |""".trimMargin() + _infoSchedules + """
              |
              | Found:
              |""".trimMargin() + _existingSchedules)
        }
        val _columnsAttendance: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAttendance.put("scheduleId", TableInfo.Column("scheduleId", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttendance.put("userId", TableInfo.Column("userId", "TEXT", true, 2, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttendance.put("isPresent", TableInfo.Column("isPresent", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttendance.put("timestamp", TableInfo.Column("timestamp", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAttendance: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAttendance: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAttendance: TableInfo = TableInfo("attendance", _columnsAttendance,
            _foreignKeysAttendance, _indicesAttendance)
        val _existingAttendance: TableInfo = read(connection, "attendance")
        if (!_infoAttendance.equals(_existingAttendance)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |attendance(com.example.sportsclubai.data.model.Attendance).
              | Expected:
              |""".trimMargin() + _infoAttendance + """
              |
              | Found:
              |""".trimMargin() + _existingAttendance)
        }
        val _columnsMatchResults: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsMatchResults.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsMatchResults.put("scheduleId", TableInfo.Column("scheduleId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsMatchResults.put("teamAId", TableInfo.Column("teamAId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsMatchResults.put("teamBId", TableInfo.Column("teamBId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsMatchResults.put("scoreA", TableInfo.Column("scoreA", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsMatchResults.put("scoreB", TableInfo.Column("scoreB", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsMatchResults.put("winnerTeamId", TableInfo.Column("winnerTeamId", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMatchResults.put("date", TableInfo.Column("date", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysMatchResults: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesMatchResults: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoMatchResults: TableInfo = TableInfo("match_results", _columnsMatchResults,
            _foreignKeysMatchResults, _indicesMatchResults)
        val _existingMatchResults: TableInfo = read(connection, "match_results")
        if (!_infoMatchResults.equals(_existingMatchResults)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |match_results(com.example.sportsclubai.data.model.MatchResult).
              | Expected:
              |""".trimMargin() + _infoMatchResults + """
              |
              | Found:
              |""".trimMargin() + _existingMatchResults)
        }
        val _columnsPlayerStats: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPlayerStats.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlayerStats.put("userId", TableInfo.Column("userId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlayerStats.put("matchId", TableInfo.Column("matchId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlayerStats.put("points", TableInfo.Column("points", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlayerStats.put("assists", TableInfo.Column("assists", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlayerStats.put("errors", TableInfo.Column("errors", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPlayerStats: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPlayerStats: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPlayerStats: TableInfo = TableInfo("player_stats", _columnsPlayerStats,
            _foreignKeysPlayerStats, _indicesPlayerStats)
        val _existingPlayerStats: TableInfo = read(connection, "player_stats")
        if (!_infoPlayerStats.equals(_existingPlayerStats)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |player_stats(com.example.sportsclubai.data.model.PlayerStats).
              | Expected:
              |""".trimMargin() + _infoPlayerStats + """
              |
              | Found:
              |""".trimMargin() + _existingPlayerStats)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "users", "clubs", "teams",
        "team_members", "schedules", "attendance", "match_results", "player_stats")
  }

  public override fun clearAllTables() {
    super.performClear(false, "users", "clubs", "teams", "team_members", "schedules", "attendance",
        "match_results", "player_stats")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(SportsClubDao::class, SportsClubDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun sportsClubDao(): SportsClubDao = _sportsClubDao.value
}
