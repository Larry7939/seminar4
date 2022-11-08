package org.sopt.sample.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.data.remote.RequestLoginDTO
import org.sopt.sample.data.remote.ResponseLoginDTO
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val authService = ServicePool.authService
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

        binding.login.setOnClickListener {
            lifecycleScope.launch {
                authService.login(
                    RequestLoginDTO(
                        "test",
                        "test"
                    )
                ).await()
            }
            authService.login(
                RequestLoginDTO(
                    "test",
                    "test"
                )
            )
                .enqueue(object : Callback<ResponseLoginDTO> {
                    override fun onResponse(
                        call: Call<ResponseLoginDTO>, response: Response<ResponseLoginDTO>
                    ) {
                        if (response.isSuccessful) { //서버통신 성공 코드 200~299
                            response.body()?.result?.name
                            Log.d("로그인 성공 여부", response.body()?.result?.name.toString())
                        } else {
                            //통신은 되었으나 에러 발생
                            Log.d("서버통신", "실패")
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginDTO>, t: Throwable) {
                        Log.d("서버통신", "실패")

                    }

                })
            //서버 통신을 비동기로 수행한 다음, 완료신호를 enqueue 로 보낸다.
            //enqueue로직은 비동기이기 때문에, 다른 로직에 비해 늦게 온다.
            //즉, enqueue내부 함수는 후속 실행 함수이다.
            //따라서 onResponse 내부에 로그인 성공시에 수행할 로직을 넣어주면 된다.
        }
    }

}
