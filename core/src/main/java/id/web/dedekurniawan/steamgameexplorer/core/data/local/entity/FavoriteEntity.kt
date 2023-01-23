package id.web.dedekurniawan.steamgameexplorer.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite")
class FavoriteEntity(
    @field:ColumnInfo(name = "app_id")
    @field:PrimaryKey
    val appId: String,

    @field:ColumnInfo("name")
    val name: String,

    @field:ColumnInfo("image_url")
    val imageUrl: String,

    @field:SerializedName("about_the_game")
    val aboutTheGame: String? = null,

    @field:ColumnInfo("short_description")
    val shortDescription: String?,

    @field:SerializedName("detailed_description")
    val detailedDescription: String? = null,

    @field:SerializedName("currency_price")
    val currencyPrice: String? = null,

    @field:SerializedName("initial_price")
    val initialPrice: Int? = null,

    @field:SerializedName("final_price")
    val finalPrice: Int? = null,

    @field:SerializedName("minimum_requirements")
    val minimumRequirements: String? = null,

    @field:SerializedName("recommended_requirements")
    val recommendedRequirements: String? = null,

    @field:SerializedName("supported_languages")
    val supportedLanguages: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("linux")
    val linux: Boolean = false,

    @field:SerializedName("windows")
    val windows: Boolean = false,

    @field:SerializedName("mac")
    val mac: Boolean = false
)