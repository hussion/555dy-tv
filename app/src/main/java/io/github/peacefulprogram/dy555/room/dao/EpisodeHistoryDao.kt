package io.github.peacefulprogram.dy555.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.peacefulprogram.dy555.room.entity.EpisodeHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(history: EpisodeHistory)

    @Query("delete from episode_history where videoId = :videoId")
    suspend fun deleteByVideoId(videoId: String)

    @Query("select * from episode_history where id = :id")
    suspend fun queryHistoryByEpisodeId(id: String): EpisodeHistory?


    @Query(
        """
        select * 
        from episode_history 
        where videoId = :videoId
        order by timestamp desc
        limit 1
    """
    )
    fun queryLatestProgress(videoId: String): Flow<EpisodeHistory?>
}