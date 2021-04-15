package io.appwrite.services

import android.net.Uri
import io.appwrite.AppwriteClient
import io.appwrite.enums.OrderType
import io.appwrite.exceptions.AppwriteException
import okhttp3.Cookie
import okhttp3.Response
import java.io.File

class ProjectsService(private val client: AppwriteClient) : BaseService(client) {

    /**
     * List Projects
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
        val path = "/projects"
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
     * Create Project
     *
     * @param name
     * @param teamId
     * @param description
     * @param logo
     * @param url
     * @param legalName
     * @param legalCountry
     * @param legalState
     * @param legalCity
     * @param legalAddress
     * @param legalTaxId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun create(
		name: String,
		teamId: String,
		description: String = "",
		logo: String = "",
		url: String = "",
		legalName: String = "",
		legalCountry: String = "",
		legalState: String = "",
		legalCity: String = "",
		legalAddress: String = "",
		legalTaxId: String = ""
	): Response {
        val path = "/projects"
        val params = mapOf<String, Any?>(
            "name" to name,
            "teamId" to teamId,
            "description" to description,
            "logo" to logo,
            "url" to url,
            "legalName" to legalName,
            "legalCountry" to legalCountry,
            "legalState" to legalState,
            "legalCity" to legalCity,
            "legalAddress" to legalAddress,
            "legalTaxId" to legalTaxId
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("POST", path, headers, params)
    }
    
    /**
     * Get Project
     *
     * @param projectId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun get(
		projectId: String
	): Response {
        val path = "/projects/{projectId}".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Update Project
     *
     * @param projectId
     * @param name
     * @param description
     * @param logo
     * @param url
     * @param legalName
     * @param legalCountry
     * @param legalState
     * @param legalCity
     * @param legalAddress
     * @param legalTaxId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun update(
		projectId: String,
		name: String,
		description: String = "",
		logo: String = "",
		url: String = "",
		legalName: String = "",
		legalCountry: String = "",
		legalState: String = "",
		legalCity: String = "",
		legalAddress: String = "",
		legalTaxId: String = ""
	): Response {
        val path = "/projects/{projectId}".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
            "name" to name,
            "description" to description,
            "logo" to logo,
            "url" to url,
            "legalName" to legalName,
            "legalCountry" to legalCountry,
            "legalState" to legalState,
            "legalCity" to legalCity,
            "legalAddress" to legalAddress,
            "legalTaxId" to legalTaxId
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("PATCH", path, headers, params)
    }
    
    /**
     * Delete Project
     *
     * @param projectId
     * @param password
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun delete(
		projectId: String,
		password: String
	): Response {
        val path = "/projects/{projectId}".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
            "password" to password
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("DELETE", path, headers, params)
    }
    
    /**
     * List Domains
     *
     * @param projectId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun listDomains(
		projectId: String
	): Response {
        val path = "/projects/{projectId}/domains".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Create Domain
     *
     * @param projectId
     * @param domain
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun createDomain(
		projectId: String,
		domain: String
	): Response {
        val path = "/projects/{projectId}/domains".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
            "domain" to domain
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("POST", path, headers, params)
    }
    
    /**
     * Get Domain
     *
     * @param projectId
     * @param domainId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun getDomain(
		projectId: String,
		domainId: String
	): Response {
        val path = "/projects/{projectId}/domains/{domainId}".replace("{projectId}", projectId).replace("{domainId}", domainId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Delete Domain
     *
     * @param projectId
     * @param domainId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun deleteDomain(
		projectId: String,
		domainId: String
	): Response {
        val path = "/projects/{projectId}/domains/{domainId}".replace("{projectId}", projectId).replace("{domainId}", domainId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("DELETE", path, headers, params)
    }
    
    /**
     * Update Domain Verification Status
     *
     * @param projectId
     * @param domainId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun updateDomainVerification(
		projectId: String,
		domainId: String
	): Response {
        val path = "/projects/{projectId}/domains/{domainId}/verification".replace("{projectId}", projectId).replace("{domainId}", domainId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("PATCH", path, headers, params)
    }
    
    /**
     * List Keys
     *
     * @param projectId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun listKeys(
		projectId: String
	): Response {
        val path = "/projects/{projectId}/keys".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Create Key
     *
     * @param projectId
     * @param name
     * @param scopes
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun createKey(
		projectId: String,
		name: String,
		scopes: List<Any>?
	): Response {
        val path = "/projects/{projectId}/keys".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
            "name" to name,
            "scopes" to scopes
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("POST", path, headers, params)
    }
    
    /**
     * Get Key
     *
     * @param projectId
     * @param keyId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun getKey(
		projectId: String,
		keyId: String
	): Response {
        val path = "/projects/{projectId}/keys/{keyId}".replace("{projectId}", projectId).replace("{keyId}", keyId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Update Key
     *
     * @param projectId
     * @param keyId
     * @param name
     * @param scopes
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun updateKey(
		projectId: String,
		keyId: String,
		name: String,
		scopes: List<Any>?
	): Response {
        val path = "/projects/{projectId}/keys/{keyId}".replace("{projectId}", projectId).replace("{keyId}", keyId)
        val params = mapOf<String, Any?>(
            "name" to name,
            "scopes" to scopes
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("PUT", path, headers, params)
    }
    
    /**
     * Delete Key
     *
     * @param projectId
     * @param keyId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun deleteKey(
		projectId: String,
		keyId: String
	): Response {
        val path = "/projects/{projectId}/keys/{keyId}".replace("{projectId}", projectId).replace("{keyId}", keyId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("DELETE", path, headers, params)
    }
    
    /**
     * Update Project OAuth2
     *
     * @param projectId
     * @param provider
     * @param appId
     * @param secret
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun updateOAuth2(
		projectId: String,
		provider: String,
		appId: String = "",
		secret: String = ""
	): Response {
        val path = "/projects/{projectId}/oauth2".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
            "provider" to provider,
            "appId" to appId,
            "secret" to secret
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("PATCH", path, headers, params)
    }
    
    /**
     * List Platforms
     *
     * @param projectId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun listPlatforms(
		projectId: String
	): Response {
        val path = "/projects/{projectId}/platforms".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Create Platform
     *
     * @param projectId
     * @param type
     * @param name
     * @param key
     * @param store
     * @param hostname
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun createPlatform(
		projectId: String,
		type: String,
		name: String,
		key: String = "",
		store: String = "",
		hostname: String = ""
	): Response {
        val path = "/projects/{projectId}/platforms".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
            "type" to type,
            "name" to name,
            "key" to key,
            "store" to store,
            "hostname" to hostname
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("POST", path, headers, params)
    }
    
    /**
     * Get Platform
     *
     * @param projectId
     * @param platformId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun getPlatform(
		projectId: String,
		platformId: String
	): Response {
        val path = "/projects/{projectId}/platforms/{platformId}".replace("{projectId}", projectId).replace("{platformId}", platformId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Update Platform
     *
     * @param projectId
     * @param platformId
     * @param name
     * @param key
     * @param store
     * @param hostname
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun updatePlatform(
		projectId: String,
		platformId: String,
		name: String,
		key: String = "",
		store: String = "",
		hostname: String = ""
	): Response {
        val path = "/projects/{projectId}/platforms/{platformId}".replace("{projectId}", projectId).replace("{platformId}", platformId)
        val params = mapOf<String, Any?>(
            "name" to name,
            "key" to key,
            "store" to store,
            "hostname" to hostname
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("PUT", path, headers, params)
    }
    
    /**
     * Delete Platform
     *
     * @param projectId
     * @param platformId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun deletePlatform(
		projectId: String,
		platformId: String
	): Response {
        val path = "/projects/{projectId}/platforms/{platformId}".replace("{projectId}", projectId).replace("{platformId}", platformId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("DELETE", path, headers, params)
    }
    
    /**
     * List Tasks
     *
     * @param projectId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun listTasks(
		projectId: String
	): Response {
        val path = "/projects/{projectId}/tasks".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Create Task
     *
     * @param projectId
     * @param name
     * @param status
     * @param schedule
     * @param security
     * @param httpMethod
     * @param httpUrl
     * @param httpHeaders
     * @param httpUser
     * @param httpPass
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun createTask(
		projectId: String,
		name: String,
		status: String,
		schedule: String,
		security: Boolean,
		httpMethod: String,
		httpUrl: String,
		httpHeaders: List<Any>? = null,
		httpUser: String = "",
		httpPass: String = ""
	): Response {
        val path = "/projects/{projectId}/tasks".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
            "name" to name,
            "status" to status,
            "schedule" to schedule,
            "security" to security,
            "httpMethod" to httpMethod,
            "httpUrl" to httpUrl,
            "httpHeaders" to httpHeaders,
            "httpUser" to httpUser,
            "httpPass" to httpPass
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("POST", path, headers, params)
    }
    
    /**
     * Get Task
     *
     * @param projectId
     * @param taskId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun getTask(
		projectId: String,
		taskId: String
	): Response {
        val path = "/projects/{projectId}/tasks/{taskId}".replace("{projectId}", projectId).replace("{taskId}", taskId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Update Task
     *
     * @param projectId
     * @param taskId
     * @param name
     * @param status
     * @param schedule
     * @param security
     * @param httpMethod
     * @param httpUrl
     * @param httpHeaders
     * @param httpUser
     * @param httpPass
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun updateTask(
		projectId: String,
		taskId: String,
		name: String,
		status: String,
		schedule: String,
		security: Boolean,
		httpMethod: String,
		httpUrl: String,
		httpHeaders: List<Any>? = null,
		httpUser: String = "",
		httpPass: String = ""
	): Response {
        val path = "/projects/{projectId}/tasks/{taskId}".replace("{projectId}", projectId).replace("{taskId}", taskId)
        val params = mapOf<String, Any?>(
            "name" to name,
            "status" to status,
            "schedule" to schedule,
            "security" to security,
            "httpMethod" to httpMethod,
            "httpUrl" to httpUrl,
            "httpHeaders" to httpHeaders,
            "httpUser" to httpUser,
            "httpPass" to httpPass
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("PUT", path, headers, params)
    }
    
    /**
     * Delete Task
     *
     * @param projectId
     * @param taskId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun deleteTask(
		projectId: String,
		taskId: String
	): Response {
        val path = "/projects/{projectId}/tasks/{taskId}".replace("{projectId}", projectId).replace("{taskId}", taskId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("DELETE", path, headers, params)
    }
    
    /**
     * Get Project
     *
     * @param projectId
     * @param range
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun getUsage(
		projectId: String,
		range: String = "30d"
	): Response {
        val path = "/projects/{projectId}/usage".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
            "range" to range
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * List Webhooks
     *
     * @param projectId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun listWebhooks(
		projectId: String
	): Response {
        val path = "/projects/{projectId}/webhooks".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Create Webhook
     *
     * @param projectId
     * @param name
     * @param events
     * @param url
     * @param security
     * @param httpUser
     * @param httpPass
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun createWebhook(
		projectId: String,
		name: String,
		events: List<Any>?,
		url: String,
		security: Boolean,
		httpUser: String = "",
		httpPass: String = ""
	): Response {
        val path = "/projects/{projectId}/webhooks".replace("{projectId}", projectId)
        val params = mapOf<String, Any?>(
            "name" to name,
            "events" to events,
            "url" to url,
            "security" to security,
            "httpUser" to httpUser,
            "httpPass" to httpPass
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("POST", path, headers, params)
    }
    
    /**
     * Get Webhook
     *
     * @param projectId
     * @param webhookId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun getWebhook(
		projectId: String,
		webhookId: String
	): Response {
        val path = "/projects/{projectId}/webhooks/{webhookId}".replace("{projectId}", projectId).replace("{webhookId}", webhookId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("GET", path, headers, params)
    }
    
    /**
     * Update Webhook
     *
     * @param projectId
     * @param webhookId
     * @param name
     * @param events
     * @param url
     * @param security
     * @param httpUser
     * @param httpPass
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun updateWebhook(
		projectId: String,
		webhookId: String,
		name: String,
		events: List<Any>?,
		url: String,
		security: Boolean,
		httpUser: String = "",
		httpPass: String = ""
	): Response {
        val path = "/projects/{projectId}/webhooks/{webhookId}".replace("{projectId}", projectId).replace("{webhookId}", webhookId)
        val params = mapOf<String, Any?>(
            "name" to name,
            "events" to events,
            "url" to url,
            "security" to security,
            "httpUser" to httpUser,
            "httpPass" to httpPass
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("PUT", path, headers, params)
    }
    
    /**
     * Delete Webhook
     *
     * @param projectId
     * @param webhookId
     * @return The request response with a JSON body 
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun deleteWebhook(
		projectId: String,
		webhookId: String
	): Response {
        val path = "/projects/{projectId}/webhooks/{webhookId}".replace("{projectId}", projectId).replace("{webhookId}", webhookId)
        val params = mapOf<String, Any?>(
        )

        val headers = mapOf(
			"content-type" to "application/json"
        )
        return client.call("DELETE", path, headers, params)
    }
    
}