package org.sopt.sample.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//서버에서 key를 pass_word 로 만드는 경우
//우리가 사용하는 변수명과 실제 사용하는 키 이름을 다르게 사용하기 위해 사용
@Serializable
data class ResponseLoginDTO(
    @SerialName("status") //key
    val status: Int,// value
    @SerialName("message") val message: String,
    @SerialName("result") val result: User
)

@Serializable
data class User(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("profileImage") val profileImage: String?,
    @SerialName("bio") val bio: String?,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,


    )



