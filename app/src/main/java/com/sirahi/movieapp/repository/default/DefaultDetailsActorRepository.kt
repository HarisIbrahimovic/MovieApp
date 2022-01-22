package com.sirahi.movieapp.repository.default

import com.sirahi.movieapp.data.local.dao.ActorDao
import com.sirahi.movieapp.data.local.dao.ActorMovieCreditsDao
import com.sirahi.movieapp.data.remote.ApiService
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.model.people.Actor
import com.sirahi.movieapp.model.people.ActorMovieCredits
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.repository.DetailsActorRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DefaultDetailsActorRepository
@Inject
constructor(
    private val apiService: ApiService,
    private val actorDao: ActorDao,
    private val actorMovieCreditsDao: ActorMovieCreditsDao
) :
    DetailsActorRepository {

    override suspend fun getActorDetails(id: Int): Response<Actor> {

        var actorDetails: Actor = actorDao.getActor(id)?.toActor()?:Actor()

        return try {
            val remoteResult = apiService.getActorDetails(id, ApiConstants.API_KEY)
            val result = remoteResult.body()
            if (result != null) {
                actorDao.insertActor(result.toActorEntity())
                actorDetails = actorDao.getActor(id)?.toActor() ?: Actor()
                Response.Success(actorDetails)
            } else Response.Error(actorDetails, "Unknown error occurred")
        } catch (e: HttpException) {
            Response.Error(actorDetails, "Network error occurred")
        } catch (e: IOException) {
            Response.Error(actorDetails, "Check your internet connection")
        }


    }

    override suspend fun getActorMovieCredits(id: Int): Response<List<ActorMovieCredits>> {
        var actorCredits = actorMovieCreditsDao.getActorCredits(id)?.map { it.toActorMovieCredits() }

        return try {
            val remoteResult = apiService.getActorCredits(id, ApiConstants.API_KEY)
            val result = remoteResult.body()?.cast?.map { it.toActorMovieCreditsEntity(id) }
            if (result != null) {
                actorMovieCreditsDao.deleteCredits(id)
                actorMovieCreditsDao.insertCredits(result)
                actorCredits =
                    actorMovieCreditsDao.getActorCredits(id)?.map { it.toActorMovieCredits() }
                Response.Success(actorCredits?:ArrayList())
            } else
                Response.Error(actorCredits, "Unknown error occurred")
        } catch (e: HttpException) {
            Response.Error(actorCredits, "Network error occurred")
        } catch (e: IOException) {
            Response.Error(actorCredits, "Check your internet connection")
        }

    }
}