package com.polytech.tp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TpTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        
        System.setOut(new PrintStream(outContent));
    }

    // ========================================================================
    // EXERCICE 1 : BUILDER PATTERN
    // ========================================================================
    @Test
    public void testBuilderConstruction() {
        // On vérifie que le Builder permet de construire un objet complet
        CoursBuilder builder = new CoursBuilder();
        
        Cours cours = builder
                .setMatiere("Génie Logiciel")
                .setEnseignant("Mr Oussama")
                .build();

        assertNotNull(cours, "La méthode build() ne doit pas retourner null");
        assertEquals("Génie Logiciel", cours.getMatiere(), "La matière n'est pas correctement définie par le Builder");
        assertEquals("Mr Oussama", cours.getEnseignant(), "L'enseignant n'est pas correctement défini par le Builder");
    }

    // ========================================================================
    // EXERCICE 2 : DECORATOR PATTERN
    // ========================================================================
    @Test
    public void testDecoratorStructure() {
        // On crée un cours de base
        ICours coursDeBase = new Cours("Assurance qualité logiciel", "Mr Omar", 
                                        "D23", "Lundi", "8h00", false, 
                                        "2A", true);
        
        
        ICours coursDecore = new CoursEnLigne(coursDeBase);

        String description = coursDecore.getDescription();

        // Vérifications
        assertNotNull(description, "La description ne doit pas être null");
        assertTrue(description.contains("Assurance qualité logiciel"), "La description originale doit être préservée");
        assertTrue(description.contains("En ligne"), 
                   "Le décorateur doit ajouter la mention 'En ligne'");
    }

    // ========================================================================
    // EXERCICE 3 : OBSERVER PATTERN
    // ========================================================================
    @Test
    public void testObserverNotification() {
        // 1. Setup du Sujet (Gestionnaire)
        GestionnaireEmploiDuTemps gestionnaire = new GestionnaireEmploiDuTemps();

        // 2. Création d'un observateur fictif pour le test (Mock)
        TestObserver observateurTest = new TestObserver();
        
        // ATTENTION : Le code ci-dessous ne compilera que si Gestionnaire implémente Subject
        // ou possède la méthode attach(). L'étudiant doit le faire.
        if (gestionnaire instanceof Subject) {
            ((Subject) gestionnaire).attach(observateurTest);
        } else {
             fail("La classe GestionnaireEmploiDuTemps doit implémenter l'interface Subject !");
        }

        // 3. Action qui déclenche la notification
        gestionnaire.setChangement("Changement de salle : C15-C16");

        // 4. Vérification
        assertTrue(observateurTest.aEteNotifie, "L'observateur aurait dû recevoir une notification");
        assertEquals("Changement de salle : C15-C16", observateurTest.dernierMessage, "Le message reçu n'est pas le bon");
    }

    // --- Classes internes utilitaires pour les tests ---

    // Un "Mock" simple pour vérifier si la notification passe
    static class TestObserver implements Observer {
        boolean aEteNotifie = false;
        String dernierMessage = "";

        @Override
        public void update(String message) {
            this.aEteNotifie = true;
            this.dernierMessage = message;
        }
    }
}