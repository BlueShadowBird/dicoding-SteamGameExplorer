package id.web.dedekurniawan.steamgameexplorer.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(

	@field:SerializedName("data")
	val data: GameDataItem,

	@field:SerializedName("success")
	val success: Boolean
)

data class GameDataItem(

	@field:SerializedName(value="logo", alternate= ["header_image"])
	val imageUrl: String,

	@field:SerializedName("short_description")
	val shortDescription: String? = null,

	@field:SerializedName("supported_languages")
	val supportedLanguages: String? = null,

	@field:SerializedName("developers")
	val developers: List<String?>? = null,

	@field:SerializedName("pc_requirements")
	val pcRequirements: GamePcRequirementsItem? = null,

	@field:SerializedName("price_overview")
	val price: GamePriceOverviewItem? = null,

	@field:SerializedName(value="appid", alternate= ["steam_appid"])
	val appId: String,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("about_the_game")
	val aboutTheGame: String? = null,

	@field:SerializedName("background_raw")
	val backgroundRaw: String? = null,

	@field:SerializedName("screenshots")
	val screenshots: List<GameScreenshotsItem?>? = null,

	@field:SerializedName("platforms")
	val platforms: GamePlatformsItem? = null,

	@field:SerializedName("genres")
	val genres: List<GameGenresItem?>? = null,

	@field:SerializedName("publishers")
	val publishers: List<String?>? = null,

	@field:SerializedName("categories")
	val categories: List<GameCategoriesItem?>? = null,

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("required_age")
	val requiredAge: Int? = null,

	@field:SerializedName("detailed_description")
	val detailedDescription: String? = null,

	@field:SerializedName("support_info")
	val supportInfo: GameSupportInfoItem? = null,

	@field:SerializedName("release_date")
	val releaseDate: GameReleaseDateItem? = null,

	@field:SerializedName("background")
	val background: String? = null,

	@field:SerializedName("is_free")
	val isFree: Boolean? = null,

	@field:SerializedName("name")
	val name: String
)

data class GamePlatformsItem(

	@field:SerializedName("linux")
	val linux: Boolean? = null,

	@field:SerializedName("windows")
	val windows: Boolean? = null,

	@field:SerializedName("mac")
	val mac: Boolean? = null
)

data class GameGenresItem(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class GamePriceOverviewItem(

	@field:SerializedName("final_formatted")
	val finalFormatted: String? = null,

	@field:SerializedName("initial")
	val initial: Int? = null,

	@field:SerializedName("final")
	val final: Int? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("initial_formatted")
	val initialFormatted: String? = null,

	@field:SerializedName("discount_percent")
	val discountPercent: Int? = null
)

data class GameScreenshotsItem(

	@field:SerializedName("path_full")
	val pathFull: String? = null,

	@field:SerializedName("path_thumbnail")
	val pathThumbnail: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class GameSupportInfoItem(

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class GameCategoriesItem(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class GameReleaseDateItem(

	@field:SerializedName("coming_soon")
	val comingSoon: Boolean? = null,

	@field:SerializedName("date")
	val date: String? = null
)

data class GamePcRequirementsItem(

	@field:SerializedName("minimum")
	val minimum: String? = null,

	@field:SerializedName("recommended")
	val recommended: String? = null
)
