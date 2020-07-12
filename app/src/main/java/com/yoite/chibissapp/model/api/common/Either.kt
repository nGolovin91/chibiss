package com.yoite.chibissapp.model.api.common


/**
 * Тип для хранения либо ошибки (Failure), либо успешного значения (Success)
 */
sealed class Either<out L, out R> {
    class Failure<T>(val value: T) : Either<T, Nothing>()
    class Success<T>(val value: T) : Either<Nothing, T>()
}

/**
 * Если ресивер Success, к нему применяется функция отображения, результат которой
 * оборачивается в Success, иначе возращается текущий Failure.
 */
inline fun <L, R, NR> Either<L, R>.map(f: (R) -> NR): Either<L, NR> =
    flatMap { Either.Success(f(it)) }

/**
 * Если ресивер Success, к нему применяется функция, возращая новый Either,
 * возвращаемый как результат, иначе возращается текущий Failure.
 *
 * Этот метод аналогичен map, но функция отображения сразу возвращает Either
 * и flatMap не оборачивает его в дополнительным Either.
 */
inline fun <L, R, NR> Either<L, R>.flatMap(f: (R) -> Either<L, NR>): Either<L, NR> =
    when (this) {
        is Either.Failure -> this
        is Either.Success -> f(value)
    }

/**
 * Если ресивер Failure, к нему применяется функцию отображения, результат которой
 * оборачивается в Failure, иначе возращается текущий Success.
 */
inline fun <L, R, NL> Either<L, R>.mapLeft(f: (L) -> NL): Either<NL, R> =
    when (this) {
        is Either.Failure -> Either.Failure(f(value))
        is Either.Success -> this
    }