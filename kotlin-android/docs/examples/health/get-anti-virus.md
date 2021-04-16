import io.appwrite.AppwriteClient
import io.appwrite.services.HealthService

val client = AppwriteClient(context)
  .setEndpoint("https://[HOSTNAME_OR_IP]/v1") // Your API Endpoint
  .setProject("5df5acd0d48c2") // Your project ID
  .setKey("919c2d18fb5d4...a2ae413da83346ad2") // Your secret API key

val healthService = HealthService(client)
val response = healthService.getAntiVirus()
val json = response.body?.string()