Feature: Création d'utilisateur

Scenario: Créer un nouvel utilisateur
  Given que je suis sur la page de création d'utilisateur
  When je saisis le nom "John"
  And je saisis le prénom "Doe"
  And je saisis le téléphone "123-456-7890"
  And je clique sur le bouton de création
  Then je devrais voir un message de confirmation "Utilisateur créé avec succès"
