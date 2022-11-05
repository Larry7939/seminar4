package org.sopt.sample.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//서버에서 key를 pass_word 로 만드는 경우
//우리가 사용하는 변수명과 실제 사용하는 키 이름을 다르게 사용하기 위해 사용
@Serializable
data class RequestLoginDTO(
    @SerialName("email") //key
    val email: String,// value
    @SerialName("password")
    val password: String
)
