package com.polytech.tp;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireEmploiDuTemps implements Subject {
    private List<ICours> listeCours = new ArrayList<>();
    private List<Observer> listeObserverrs = new ArrayList<>();

    public void ajouterCours(ICours cours) {
        this.listeCours.add(cours);
        System.out.println("Nouveau cours ajouté : " + cours.getDescription());
        // TODO: C'est ici qu'il faudrait notifier les étudiants (Observer pattern)
        notifyObservers("new cours");
    }

    public void modifierCours(ICours cours, String message) {
        // Logique de modification...
        System.out.println("Cours modifié : " + message);
        // TODO: Notifier les observateurs ici aussi
        notifyObservers("modifer cours");
    }

    public void setChangement(String string) {
        // TODO Auto-generated method stub
        notifyObservers(string);
    }

    @Override
    public void attach(Observer o) {
        this.listeObserverrs.add(o);
    }

    @Override
    public void detach(Observer o) {
        this.listeObserverrs.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for(Observer o:this.listeObserverrs)
            o.update(message);

    }
}