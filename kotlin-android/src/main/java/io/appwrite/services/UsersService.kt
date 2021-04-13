package io.appwrite.services

import android.net.Uri
import io.appwrite.AppwriteClient
import io.appwrite.enums.OrderType
import io.appwrite.exceptions.AppwriteException
import okhttp3.Cookie
import okhttp3.Response
import java.io.File

class UsersService(private val client: AppwriteClient) : BaseService(client) {

    /**
     * List Users
     *
     * Get a list of all the project's users. You can use the query params to
     * filter your results.
     *
     * @param search
     * @param limit
     * @param offset
     * @param orderType
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun list(
		search: String = "",
		limit: Int = 25,
		offset: Int = 0,
		orderType: OrderType = OrderType.ASC
	): Response {
        val path = "/users"
        val params = mapOf<String, Any?>(
            "search" to search,
            "limit" to limit,
            "offset" to offset,
            "orderType" to orderType.name
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Create User
     *
     * Create a new user.
     *
     * @param email
     * @param password
     * @param name
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun create(
		email: String,
		password: String,
		name: String = ""
	): Response {
        val path = "/users"
        val params = mapOf<String, Any?>(
            "email" to email,
            "password" to password,
            "name" to name
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("POST", path, headers, params)
    }
    
    /**
     * Get User
     *
     * Get a user by its unique ID.
     *
     * @param userId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun get(
		userId: String
	): Response {
        val path = "/users/{userId}".replace("{userId}", userId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Delete User
     *
     * Delete a user by its unique ID.
     *
     * @param userId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun deleteUser(
		userId: String
	): Response {
        val path = "/users/{userId}".replace("{userId}", userId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("DELETE", path, headers, params)
    }
    
    /**
     * Get User Logs
     *
     * Get a user activity logs list by its unique ID.
     *
     * @param userId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun getLogs(
		userId: String
	): Response {
        val path = "/users/{userId}/logs".replace("{userId}", userId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Get User Preferences
     *
     * Get the user preferences by its unique ID.
     *
     * @param userId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun getPrefs(
		userId: String
	): Response {
        val path = "/users/{userId}/prefs".replace("{userId}", userId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Update User Preferences
     *
     * Update the user preferences by its unique ID. You can pass only the
     * specific settings you wish to update.
     *
     * @param userId
     * @param prefs
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun updatePrefs(
		userId: String,
		prefs: Any?
	): Response {
        val path = "/users/{userId}/prefs".replace("{userId}", userId)
        val params = mapOf<String, Any?>(
            "prefs" to prefs
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("PATCH", path, headers, params)
    }
    
    /**
     * Get User Sessions
     *
     * Get the user sessions list by its unique ID.
     *
     * @param userId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun getSessions(
		userId: String
	): Response {
        val path = "/users/{userId}/sessions".replace("{userId}", userId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Delete User Sessions
     *
     * Delete all user's sessions by using the user's unique ID.
     *
     * @param userId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun deleteSessions(
		userId: String
	): Response {
        val path = "/users/{userId}/sessions".replace("{userId}", userId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("DELETE", path, headers, params)
    }
    
    /**
     * Delete User Session
     *
     * Delete a user sessions by its unique ID.
     *
     * @param userId
     * @param sessionId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun deleteSession(
		userId: String,
		sessionId: String
	): Response {
        val path = "/users/{userId}/sessions/{sessionId}".replace("{userId}", userId).replace("{sessionId}", sessionId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("DELETE", path, headers, params)
    }
    
    /**
     * Update User Status
     *
     * Update the user status by its unique ID.
     *
     * @param userId
     * @param status
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun updateStatus(
		userId: String,
		status: String
	): Response {
        val path = "/users/{userId}/status".replace("{userId}", userId)
        val params = mapOf<String, Any?>(
            "status" to status
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("PATCH", path, headers, params)
    }
    
}