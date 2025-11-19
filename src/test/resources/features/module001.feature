Feature: Goes to the homepage and logs in

  Background:
    Given mi trovo sulla pagina di login SauceDemo

  @Test_001
  Scenario: Open the website and logs in
    When inserisco username "standard_user"
    And inserisco password "secret_sauce"
    And clicco sul pulsante login
    Then vengo reindirizzato alla pagina Products