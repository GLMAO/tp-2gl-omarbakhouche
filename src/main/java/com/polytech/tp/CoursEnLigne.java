package com.polytech.tp;

public class CoursEnLigne extends CoursDecorator {
    public CoursEnLigne(ICours cours) {
        super(cours);
        //TODO: Implémenter le reste ...
    }

    @Override
    public String getDescription() {
        return coursDecorated.getDescription()+"(En ligne)";
        

    }

    @Override
    public double getDuree() {
        // TODO Auto-generated method stub
        
        return coursDecorated.getDuree();
    };
}
