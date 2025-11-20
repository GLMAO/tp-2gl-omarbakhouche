package com.polytech;

import com.polytech.tp.Cours;
import com.polytech.tp.CoursEnLigne;
import com.polytech.tp.ICours;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        ICours cours = new Cours(
                "Génie Logiciel",
                "Dupont",
                "Salle B12",
                "20/11/2025",
                "10:00",
                false,
                "L3",
                true
        );

        cours = new CoursEnLigne(cours);
        System.out.println(cours.getDescription());



    }
}
