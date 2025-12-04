package APIUtils;

import APIUtils.POJO.Category;
import APIUtils.POJO.Pet;
import APIUtils.POJO.Tag;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import kong.unirest.jackson.JacksonObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UnirestPetstoreTest {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @BeforeClass
    public static void setup() {
        // Configura Unirest per usare Jackson per la conversione JSON <-> Oggetti
        Unirest.config().setObjectMapper(new JacksonObjectMapper());
    }

    //@Test
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

    @Test
    public void createPet() {
        // 1. Crea l'oggetto Pet usando il Builder di Lombok (molto più leggibile!)
        Pet myPet = Pet.builder()
                .id(39)
                .name("HatsuneMiku")
                .category(new Category(1, "Gatti"))
                .photoUrls(Collections.singletonList("http://example.com/fido.jpg"))
                .tags(Arrays.asList(new Tag(1, "vocaloid"), new Tag(2, "addestrato")))
                .status("available")
                .build();

        // 2. Invia la richiesta POST con l'oggetto Pet come corpo
        // Unirest (con Jackson) lo convertirà automaticamente in JSON
        HttpResponse<Pet> response = Unirest.post(BASE_URL + "/pet")
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(myPet)
                .asObject(Pet.class); // Converte la risposta JSON di nuovo in un oggetto Pet

        // 3. Verifica la risposta
        assertEquals(200, response.getStatus());
        Pet createdPet = response.getBody();

        // Stampa l'oggetto Pet ricevuto come risposta
        System.out.println("Pet creato: " + createdPet.toString());

        assertNotNull(createdPet);
    }
}
