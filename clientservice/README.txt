## OSNOVNO
- Example of - "CREDENTIALS GRANT TYPE".
 
- This service is setup as Client at KEYCLOAK: [it can have its own permissions, roles, users...].  
- name: "toll-service". It has its own credentials (service account): clientId and Secret. 
- scope: "reader" (because resourceserver1 expect an access token with scope claim: reader.....so in KEYCLOAK we need to enable at KEYCLOAK client's Client Scopes 'assigned default CLient Scopes'
		"toll-service" client is allowed to request "reader" scope (e.g. in application.properties).

		- This service is not a web service registered in Keycloak as OAuth2 client(as secureui), but a RESTful service which is registered in KEYCLOAK as OAuth2 client.
			- we can call "/" endpoint via PostMan, 
			- we can call it also from browser. 

- Service itself has not authentication and authorization set, anyone can call root "/" endpoint.
- However, service has some kind of service account credentials (clisentId/secret) that it use to get appropriate access token, 
		put that in the header, and use that to call downstream resource server (resourceserver1). 


