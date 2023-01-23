package id.web.dedekurniawan.steamgameexplorer.core.utils

import id.web.dedekurniawan.steamgameexplorer.core.data.local.entity.FavoriteEntity
import id.web.dedekurniawan.steamgameexplorer.core.data.remote.response.GameDataItem
import id.web.dedekurniawan.steamgameexplorer.core.domain.model.Game


fun FavoriteEntity.toDomainModel() = Game(
    appId,
    name,
    imageUrl,
    aboutTheGame,
    shortDescription,
    detailedDescription,
    currencyPrice,
    initialPrice,
    finalPrice,
    minimumRequirements,
    recommendedRequirements,
    supportedLanguages,
    releaseDate,
    mutableListOf<String>().apply {
        if(linux)add("Linux")
        if(windows)add("Windows")
        if(mac)add("Mac")
    },
    true
)

fun GameDataItem.toDomainModel() = Game(
    appId,
    name,
    imageUrl,
    aboutTheGame,
    shortDescription.toString(),
    detailedDescription,
    price?.currency,
    price?.initial,
    price?.final,
    pcRequirements?.minimum,
    pcRequirements?.recommended,
    supportedLanguages,
    if(releaseDate?.comingSoon==true)"comingSoon" else releaseDate?.date,
    mutableListOf<String>().apply {
        if(platforms?.linux == true)add("Linux")
        if(platforms?.windows == true)add("Windows")
        if(platforms?.mac == true)add("Mac")
    }, false
)

fun Game.toFavoriteEntity() = FavoriteEntity(
    appId,
    name,
    imageUrl,
    aboutTheGame,
    shortDescription,
    detailedDescription,
    currencyPrice,
    initialPrice,
    finalPrice,
    minimumRequirements,
    recommendedRequirements,
    supportedLanguages,
    releaseDate,
    platforms.contains("Linux"),
    platforms.contains("Windows"),
    platforms.contains("Mac")

)


//fun SearchGameResponse.toDomainModel() = searchResponse.map {
//    SearchGameItem(
//        it.appid,
//        it.name,
//        it.icon,
//        it.logo
//    )
//}