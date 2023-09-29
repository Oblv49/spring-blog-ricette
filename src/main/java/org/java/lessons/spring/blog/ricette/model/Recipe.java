package org.java.lessons.spring.blog.ricette.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "RICETTA")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Inserisci un Titolo che sia minimo 5 caratteri.")
    @Size(min = 5, max = 100)
    @Column(name = "Titolo_Ricetta", length = 100)
    private String title;

    @NotBlank(message = "Inserisci una lista di ingredienti.")
    @Column(length = 1024)
    private String ingredientsList;

    @NotBlank(message = "Inserisci un URL per la foto.")
    private String urlPhoto;

    @Min(value = 1, message = "Il tempo di preparazione deve essere almeno 1 minuto.")
    private int timeOfPreparation;

    @Min(value = 1, message = "La porzione deve essere almeno per 1 persona.")
    private int portion;

    @NotBlank(message = "Inserisci un Testo che sia minimo 10 caratteri.")
    @Size(min = 10, max = 2048, message = "Il testo della ricetta deve essere tra 10 e 2048 caratteri.")
    @Column(length = 2048)
    private String textOfRecipe;

    //constructor
    public Recipe(int id, String title, String ingredientsList, String urlPhoto, int timeOfPreparation, int portion, String textOfRecipe) {
        this.id = id;
        this.title = title;
        this.ingredientsList = ingredientsList;
        this.urlPhoto = urlPhoto;
        this.timeOfPreparation = timeOfPreparation;
        this.portion = portion;
        this.textOfRecipe = textOfRecipe;
    }


    //constructor default
    public Recipe() {
    }


    //getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getIngredientsList() {
        return ingredientsList;
    }
    public void setIngredientsList(String ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
    public String getUrlPhoto() {
        return urlPhoto;
    }
    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
    public int getTimeOfPreparation() {
        return timeOfPreparation;
    }
    public void setTimeOfPreparation(int timeOfPreparation) {
        this.timeOfPreparation = timeOfPreparation;
    }
    public int getPortion() {
        return portion;
    }
    public void setPortion(int portion) {
        this.portion = portion;
    }
    public String getTextOfRecipe() {
        return textOfRecipe;
    }
    public void setTextOfRecipe(String textOfRecipe) {
        this.textOfRecipe = textOfRecipe;
    }
}
