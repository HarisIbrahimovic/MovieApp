package com.sirahi.movieapp.repository.fake

class FakeMenuRepository//:MenuRepository
{
/*
    override suspend fun getMovies(category: String): Response<List<MediaResult>?> {
        val list = ArrayList<MediaResult>()
        list.add(MediaResult(1,"/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg","movie","Venom: Let There Be Carnage",7.8,"Overview","16-12-2012","/eENEf62tMXbhyVvdcXlnQz2wcuT.jpg"))
        list.add(MediaResult(2,"/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg","movie","Encanto",7.8,"Overview","16-12-2012","/5RuR7GhOI5fElADXZb0X2sr9w5n.jpg"))
        list.add(MediaResult(3,"/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg","movie","Shang-Chi and the Legend of the Ten Rings",7.8,"Overview","16-12-2012","/cinER0ESG0eJ49kXlExM0MEWGxW.jpg"))
        return Response.Success(list)
    }

    override suspend fun getTv(category: String): Response<List<MediaResult>?> {
        val list = ArrayList<MediaResult>()
        list.add(MediaResult(1,"/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg","movie","Hawkeye",7.8,"Overview","16-12-2012","/1R68vl3d5s86JsS2NPjl8UoMqIS.jpg"))
        list.add(MediaResult(2,"/iF8ai2QLNiHV4anwY1TuSGZXqfN.jpg","movie","Chucky",5.6,"Overview","16-12-2012","/xAKMj134XHQVNHLC6rWsccLMenG.jpg"))
        list.add(MediaResult(3,"/mpgDeLhl8HbhI03XLB7iKO6M6JE.jpg","movie","The Wheel of Time",7.8,"Overview","16-12-2012","/1P3QtW1IkivqDrKbbwuR0zCYIf8.jpg"))
        return Response.Success(list)
    }

    override suspend fun discoverMovies(id: Int, name: String): Response<List<MediaResult>?> {
        val list = ArrayList<MediaResult>()
        if(id==1){
            list.add(MediaResult(1,"/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg","movie","Venom: Let There Be Carnage",7.8,"Overview","16-12-2012","/eENEf62tMXbhyVvdcXlnQz2wcuT.jpg"))
        }else list.add(MediaResult(2,"/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg","movie","Encanto",7.8,"Overview","16-12-2012","/5RuR7GhOI5fElADXZb0X2sr9w5n.jpg"))
        return Response.Success(list)
    }


    override suspend fun getSearchDataMovies(query: String): Response<List<MediaResult>?> {
        val list = ArrayList<MediaResult>()
        if(query=="Venom"){
            list.add(MediaResult(1,"/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg","movie","Venom: Let There Be Carnage",7.8,"Overview","16-12-2012","/eENEf62tMXbhyVvdcXlnQz2wcuT.jpg"))
        }else if(query=="Encanto") list.add(MediaResult(2,"/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg","movie","Encanto",7.8,"Overview","16-12-2012","/5RuR7GhOI5fElADXZb0X2sr9w5n.jpg"))
        return Response.Success(list)
    }

    override suspend fun getSearchDataTV(query: String): Response<List<MediaResult>?> {
        val list = ArrayList<MediaResult>()
        if(query=="Hawkaye"){
            list.add(MediaResult(1,"/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg","movie","Hawkeye",7.8,"Overview","16-12-2012","/1R68vl3d5s86JsS2NPjl8UoMqIS.jpg"))
        }else if(query=="Chucky") list.add(MediaResult(2,"/iF8ai2QLNiHV4anwY1TuSGZXqfN.jpg","movie","Chucky",5.6,"Overview","16-12-2012","/xAKMj134XHQVNHLC6rWsccLMenG.jpg"))
        return Response.Success(list)
    }

    override fun getGenreList(): ArrayList<Genre> {
        val list = ArrayList<Genre>()
        list.add(Genre(28,"Action",true))
        list.add(Genre(12,"Adventure"))
        list.add(Genre(16,"Animation"))
        list.add(Genre(35,"Comedy"))
        list.add(Genre(80,"Crime"))
        list.add(Genre(99,"Documentary"))
        list.add(Genre(18,"Drama"))
        list.add(Genre(10751,"Family"))
        list.add(Genre(14,"Fantasy"))
        list.add(Genre(36,"History"))
        list.add(Genre(27,"Horror"))
        list.add(Genre(10402,"Music"))
        list.add(Genre(9648,"Mystery"))
        list.add(Genre(10749,"Romance"))
        list.add(Genre(878,"Science Fiction"))
        list.add(Genre(10770,"TV Movie"))
        list.add(Genre(53,"Thriller"))
        list.add(Genre(10752,"War"))
        list.add(Genre(37,"Western"))
        return list
    }

    override fun getWatchlistMovies(): LiveData<ArrayList<MediaItem>> {
        TODO("Not yet implemented")
    }

    override fun getFavoritesMovies(): LiveData<ArrayList<MediaItem>> {
        TODO("Not yet implemented")
    }

    override fun getWatchlistTv(): LiveData<ArrayList<MediaItem>> {
        TODO("Not yet implemented")
    }

    override fun getFavoritesTv(): LiveData<ArrayList<MediaItem>> {
        TODO("Not yet implemented")
    }*/
}