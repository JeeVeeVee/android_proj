package android.example.artGallery.network

import android.example.artGallery.network.api_models.ArtworksApi
import android.example.artGallery.network.api_models_artists.ArtistsApiItem
import android.example.artGallery.network.api_models_artworkbyid.ArtworkById
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://houtkunst.hiddencorner.org/api/"

private val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
private val client = OkHttpClient.Builder()
    .addInterceptor(logger)
    .build()

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()


/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
/*
    .addConverterFactory(MoshiConverterFactory.create(moshi))
*/
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()


interface HooopApiService{

    @Headers("Authorization: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Im1vMGVTYTY5allpQ3Y2Uzl2NDRCdSJ9.eyJpc3MiOiJodHRwczovL2Rldi14em41YTBuOS51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjFiMjM1M2JjMDgxYjkwMDY4ODMyYmU2IiwiYXVkIjpbImh0dHBzOi8vaG9vb3BnYWxsZXJ5YXBpLmNvbSIsImh0dHBzOi8vZGV2LXh6bjVhMG45LnVzLmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2NDAyNjIxODMsImV4cCI6MTY0MDM0ODU4MywiYXpwIjoib1FobDFpc3NyNFB0cUlSbmw5WkhMYW1UUGFkVGp5UTgiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwicGVybWlzc2lvbnMiOltdfQ.n7gZX61DRLhLRkXBXZ9ioRb-thUtYOin3fhHU7RiynLiOo9ld8OJdQ06MvpgGACYNdv0uuRJXXMDllAk6fS5NZI-kJcLJSvo-hk8pFI2_q8eKB7_vrthG8IAOxguYNUdTQYuisHzdLsYmjOkwZLDeOnx9GiYBjB1RyQ6oExKrYcjm1qEe6TypTStplhitcPyP7e-QeAL7FtEfQyJS1A5ouma6wKVRPvmY-pLjVzysAzcL00iFgaOf0uNdDplqrxwhYPC-2Q1vU-2jy4G8J7v-gfucPRvivVASOsSKGQK5y1aDIcCqGS-JKWK47BwyfMjhlMinzHlaVxxFYuVR96NjQ")
    @GET("Artwork")
    fun getArtWorks(): Deferred<ArtworksApi>

    @Headers("Authorization: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Im1vMGVTYTY5allpQ3Y2Uzl2NDRCdSJ9.eyJpc3MiOiJodHRwczovL2Rldi14em41YTBuOS51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjFiMjM1M2JjMDgxYjkwMDY4ODMyYmU2IiwiYXVkIjpbImh0dHBzOi8vaG9vb3BnYWxsZXJ5YXBpLmNvbSIsImh0dHBzOi8vZGV2LXh6bjVhMG45LnVzLmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2NDAyNjIxODMsImV4cCI6MTY0MDM0ODU4MywiYXpwIjoib1FobDFpc3NyNFB0cUlSbmw5WkhMYW1UUGFkVGp5UTgiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwicGVybWlzc2lvbnMiOltdfQ.n7gZX61DRLhLRkXBXZ9ioRb-thUtYOin3fhHU7RiynLiOo9ld8OJdQ06MvpgGACYNdv0uuRJXXMDllAk6fS5NZI-kJcLJSvo-hk8pFI2_q8eKB7_vrthG8IAOxguYNUdTQYuisHzdLsYmjOkwZLDeOnx9GiYBjB1RyQ6oExKrYcjm1qEe6TypTStplhitcPyP7e-QeAL7FtEfQyJS1A5ouma6wKVRPvmY-pLjVzysAzcL00iFgaOf0uNdDplqrxwhYPC-2Q1vU-2jy4G8J7v-gfucPRvivVASOsSKGQK5y1aDIcCqGS-JKWK47BwyfMjhlMinzHlaVxxFYuVR96NjQ")
    @GET("User/artists")
    fun getArtists(): Deferred<List<ArtistsApiItem>>

    @Headers("Authorization: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Im1vMGVTYTY5allpQ3Y2Uzl2NDRCdSJ9.eyJpc3MiOiJodHRwczovL2Rldi14em41YTBuOS51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjFiMjM1M2JjMDgxYjkwMDY4ODMyYmU2IiwiYXVkIjpbImh0dHBzOi8vaG9vb3BnYWxsZXJ5YXBpLmNvbSIsImh0dHBzOi8vZGV2LXh6bjVhMG45LnVzLmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2NDAyNjIxODMsImV4cCI6MTY0MDM0ODU4MywiYXpwIjoib1FobDFpc3NyNFB0cUlSbmw5WkhMYW1UUGFkVGp5UTgiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwicGVybWlzc2lvbnMiOltdfQ.n7gZX61DRLhLRkXBXZ9ioRb-thUtYOin3fhHU7RiynLiOo9ld8OJdQ06MvpgGACYNdv0uuRJXXMDllAk6fS5NZI-kJcLJSvo-hk8pFI2_q8eKB7_vrthG8IAOxguYNUdTQYuisHzdLsYmjOkwZLDeOnx9GiYBjB1RyQ6oExKrYcjm1qEe6TypTStplhitcPyP7e-QeAL7FtEfQyJS1A5ouma6wKVRPvmY-pLjVzysAzcL00iFgaOf0uNdDplqrxwhYPC-2Q1vU-2jy4G8J7v-gfucPRvivVASOsSKGQK5y1aDIcCqGS-JKWK47BwyfMjhlMinzHlaVxxFYuVR96NjQ")
    @GET("Artwork/{id}")
    fun getArtworkById(@Path("id") id: Int?): Deferred<ArtworkById>
}

object HooopApi {
    val retrofitService : HooopApiService by lazy {
        retrofit.create(HooopApiService::class.java)
    }
}