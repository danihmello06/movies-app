import com.danihmello.moviesapp.data.Movie
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue

data class MovieCommonListResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieResponse>
)

data class MovieResponse(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val imageBackdropPath: String,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: @RawValue Any,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("genres")
    val genres: List<GenreResponse>?,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("production_companies")
    val productionCompanies: List<@RawValue Any>,
    @SerializedName("production_countries")
    val productionCountries: List<@RawValue Any>,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("revenue")
    val revenue: Int,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<Movie.SpokenLanguage>,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

data class GenreResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null
)