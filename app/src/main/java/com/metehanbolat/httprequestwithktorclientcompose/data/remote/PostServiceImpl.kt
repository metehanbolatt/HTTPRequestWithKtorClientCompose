package com.metehanbolat.httprequestwithktorclientcompose.data.remote

import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostRequest
import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImpl(
    private val client: HttpClient
): PostsService {
    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get { url(HttpRoutes.POSTS) }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            /** Yönlendirme ile ilgili hataları döndermek için kullanılır. */
            /** Örneğin Proxy kullanılması gerekiyorsa ona ait hatayı dönderir veya taşınma işlemi varsa onu dönderir */
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            /** İstemci hatalarını döndermek için kullanıyoruz. Yanlış request atılırsa veya ödeme istenirse veya izin verilmeyen bir method kullanılırsa */
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            /** Sunucu hatalarını döndermek için kullandık. Örneğin istek attığımız sunucu cevap vermezse 500 hata kodlarından biri döner. */
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            /** Genel dönecek hatlar için de normal Exception sınıfını kullanabiliriz. */
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse> {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            /** Yönlendirme ile ilgili hataları döndermek için kullanılır. */
            /** Örneğin Proxy kullanılması gerekiyorsa ona ait hatayı dönderir veya taşınma işlemi varsa onu dönderir */
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - responses
            /** İstemci hatalarını döndermek için kullanıyoruz. Yanlış request atılırsa veya ödeme istenirse veya izin verilmeyen bir method kullanılırsa */
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - responses
            /** Sunucu hatalarını döndermek için kullandık. Örneğin istek attığımız sunucu cevap vermezse 500 hata kodlarından biri döner. */
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            /** Genel dönecek hatlar için de normal Exception sınıfını kullanabiliriz. */
            println("Error: ${e.message}")
            null
        }
    }

    /** Dependency Injection kullanmadığımız için constructor olarak verdiğimiz değerin bir instance'ını oluşturmamız gerekiyor.
     * Bunu interface içerisinde yapabiliriz. */
}