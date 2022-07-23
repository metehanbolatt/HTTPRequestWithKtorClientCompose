package com.metehanbolat.httprequestwithktorclientcompose.data.remote

import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostRequest
import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostsService {

    suspend fun getPosts(): List<PostResponse>
    suspend fun createPost(postRequest: PostRequest): PostResponse?

    // Buraya implementasyondan sonra geleceksin.
    companion object {
        fun create(): PostsService {
            return PostServiceImpl(
                /** Android kütüphanesini entegre etmemizin sebebini burada anlıyoruz. */
                client = HttpClient(Android) {
                    /** Bu alanda Ktor'un server tarafına ait özelliklere ulaşabiliriz.
                     * Örneğin Logging, Json Serialization ve Authentication bunlara örnek olarak verilebilir. */
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    /** Bu özellikle birlikte Json Dataya serialize veya deserialize yapabileceğimizi belirtiyoruz. */
                    install(JsonFeature) {
                         /** Fakat bunu hangi plugin veya kütüphane ile yapacağımızı spesifik olarak söylememiz gerekiyor onu da bu şekilde belirtebiliriz.
                         * Burada json, moshi'de kullanılabilir fakat biz KotlinxSerializer kullanıyoruz. */
                        serializer = KotlinxSerializer()
                    }
                    /** Burası bitti Main Activity'de istek atalım.*/
                }
            )
        }
    }

}