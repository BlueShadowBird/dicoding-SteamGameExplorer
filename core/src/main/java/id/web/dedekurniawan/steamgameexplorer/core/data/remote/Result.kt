package id.web.dedekurniawan.steamgameexplorer.core.data.remote

sealed class Result<R> private constructor(val data: R? = null, val message: String? = null) {
    class Loading<R>(data: R? = null) : Result<R>(data)
    class Data<R>(data: R) : Result<R>(data)
    class Done<R>(message: String?=null, data: R?=null) : Result<R>(data, message)
    class Error<R>(error: String, data: R?=null) : Result<R>(data, error)
}