package com.pedro0210.hobbylobby.domain.util

import com.pedro0210.hobbylobby.domain.util.Error as UtilError

sealed interface Result<out D, out E : UtilError> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E : UtilError>(val error: E) : Result<Nothing, E>
}
