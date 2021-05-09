import io.appwrite.Client
import io.appwrite.services.Projects

val client = Client(context)
  .setEndpoint("https://[HOSTNAME_OR_IP]/v1") // Your API Endpoint
  .setProject("5df5acd0d48c2") // Your project ID
  .setKey("919c2d18fb5d4...a2ae413da83346ad2") // Your secret API key

val projectsService = Projects(client)
val response = projectsService.updateOAuth2("[PROJECT_ID]", "amazon")
val json = response.body?.string()