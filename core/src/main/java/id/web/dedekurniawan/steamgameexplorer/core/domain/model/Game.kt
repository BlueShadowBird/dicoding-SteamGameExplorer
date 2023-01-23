package id.web.dedekurniawan.steamgameexplorer.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Game(
    val appId: String,
    val name: String,
    val imageUrl: String,
    val aboutTheGame: String? = null,
    val shortDescription: String? = null,
    val detailedDescription: String? = null,
    val currencyPrice: String? = null,
    val initialPrice: Int? = null,
    val finalPrice: Int? = null,
    val minimumRequirements: String? = null,
    val recommendedRequirements: String? = null,
    val supportedLanguages: String? = null,
    val releaseDate: String? = null,
    val platforms: List<String>,
    var favorited: Boolean
): Parcelable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Game

        if (appId != other.appId) return false
        if (name != other.name) return false
        if (imageUrl != other.imageUrl) return false
        if (aboutTheGame != other.aboutTheGame) return false
        if (shortDescription != other.shortDescription) return false
        if (detailedDescription != other.detailedDescription) return false
        if (currencyPrice != other.currencyPrice) return false
        if (initialPrice != other.initialPrice) return false
        if (finalPrice != other.finalPrice) return false
        if (minimumRequirements != other.minimumRequirements) return false
        if (recommendedRequirements != other.recommendedRequirements) return false
        if (supportedLanguages != other.supportedLanguages) return false
        if (releaseDate != other.releaseDate) return false
        if (platforms != other.platforms) return false

        return true
    }

    override fun hashCode(): Int {
        var result = appId.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + (aboutTheGame?.hashCode() ?: 0)
        result = 31 * result + (shortDescription?.hashCode() ?: 0)
        result = 31 * result + (detailedDescription?.hashCode() ?: 0)
        result = 31 * result + (currencyPrice?.hashCode() ?: 0)
        result = 31 * result + (initialPrice ?: 0)
        result = 31 * result + (finalPrice ?: 0)
        result = 31 * result + (minimumRequirements?.hashCode() ?: 0)
        result = 31 * result + (recommendedRequirements?.hashCode() ?: 0)
        result = 31 * result + (supportedLanguages?.hashCode() ?: 0)
        result = 31 * result + (releaseDate?.hashCode() ?: 0)
        result = 31 * result + platforms.hashCode()
        return result
    }
}