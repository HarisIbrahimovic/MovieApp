package com.sirahi.movieapp.data.remote

import com.sirahi.movieapp.data.local.dao.MovieDetailsDao
import com.sirahi.movieapp.data.local.entity.movie.MovieDetailsEntity
import com.sirahi.movieapp.data.remote.dto.credits.MediaCreditsDto
import com.sirahi.movieapp.data.remote.dto.credits.MovieCastDto
import com.sirahi.movieapp.data.remote.dto.credits.MovieCreditsDto
import com.sirahi.movieapp.data.remote.dto.credits.TVCreditsDto
import com.sirahi.movieapp.data.remote.dto.movie.MovieDetailsDto
import com.sirahi.movieapp.data.remote.dto.movie.MovieDto
import com.sirahi.movieapp.data.remote.dto.people.ActorDto
import com.sirahi.movieapp.data.remote.dto.tv.TVDetailsDto
import com.sirahi.movieapp.data.remote.dto.tv.TvDto
import com.sirahi.movieapp.model.movie.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/3/movie/{key_word}")
    suspend fun getMovies(
        @Path("key_word") key_word: String,
        @Query("api_key") api_key: String
    ):Response<MovieDto>

    @GET("/3/tv/{key_word}")
    suspend fun getTV(
            @Path("key_word") key_word: String,
            @Query("api_key") api_key: String
    ):Response<TvDto>

    @GET("/3/discover/movie")
    suspend fun discoverMovies(
            @Query("with_genres") with_genres: Int,
            @Query("api_key") api_key: String
    ):Response<MovieDto>


    @GET("/3/movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path("movieId") movieId: String,
        @Query("api_key") api_key: String
    ):Response<MovieCreditsDto>


    @GET("3/tv/{tv_id}/credits")
    suspend fun getTvCredits(
            @Path("tv_id")tv_id:Int,
            @Query("api_key") api_key: String
    ):Response<MovieCreditsDto>

    @GET("/3/search/{key_word}")
    suspend fun getSearchDataMovie(
        @Path("key_word") key_word: String,
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ):Response<MovieDto>

    @GET("/3/search/{key_word}")
    suspend fun getSearchDataTv(
        @Path("key_word") key_word: String,
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ):Response<TvDto>

    @GET("/3/person/{person_id}")
    suspend fun getActorDetails(
        @Path("person_id") person_id: Int,
        @Query("api_key") api_key: String
    ):Response<ActorDto>

    @GET("/3/person/{actorId}/movie_credits")
    suspend fun getActorCredits(
        @Path("actorId") actorId: Int,
        @Query("api_key") api_key: String
    ):Response<MediaCreditsDto>

    @GET("/3/movie/{movieId}")
    suspend fun getSingleMovie(
        @Path("movieId") id: String,
        @Query("api_key") api_key: String,
    ): Response<MovieDetailsDto>

    @GET("/3/tv/{tv_id}")
    suspend fun getSingleTvShow(
            @Path("tv_id") id: String,
            @Query("api_key") api_key: String,
    ): Response<TVDetailsDto>
}

