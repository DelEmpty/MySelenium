package APIUtils;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnirestPetstoreTest {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void getPetById() {
        // Definisci l'ID del pet da recuperare
        int petId = 1;

        // Invia la richiesta GET all'endpoint /pet/{petId}
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL + "/pet/" + petId)
                .header("accept", "application/json")
                .asJson();

        // Stampa lo status code e il corpo della risposta
        System.out.println("Status Code: " + response.getStatus());
        System.out.println("Response Body: " + response.getBody().toString());

        // Verifica che la richiesta sia andata a buon fine (status code 200)
        assertEquals(200, response.getStatus());
    }
}
