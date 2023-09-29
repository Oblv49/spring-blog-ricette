package org.java.lessons.spring.blog.ricette.model;

import org.java.lessons.spring.blog.ricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class DataLoader implements ApplicationRunner {
    @Autowired
    private RecipeRepository recipeRepository;

    public void run(ApplicationArguments args) {
        LocalDateTime today = LocalDateTime.now();

        Recipe recipe1 = new Recipe();
        recipe1.setTitle("Pasta alla Carbonara");
        recipe1.setIngredientsList("200g di pasta, 100g di pancetta, 2 uova, 50g di pecorino romano grattugiato, pepe nero macinato");
        recipe1.setUrlPhoto("https://cdn.imgbin.com/9/17/5/imgbin-spaghetti-aglio-e-olio-spaghetti-alla-puttanesca-al-dente-ham-carbonara-pasta-ypwnPKyNK6jm9gzQv4BrTNiUk.jpg");
        recipe1.setTimeOfPreparation(20);
        recipe1.setPortion(2);
        recipe1.setTextOfRecipe("Cuoci la pasta in abbondante acqua salata finché non è al dente. Nel frattempo, rosola la pancetta in una padella finché diventa croccante. Scola la pasta e aggiungila alla padella con la pancetta. In una ciotola, sbatti le uova e aggiungi il pecorino romano grattugiato. Versa il composto di uova e formaggio sulla pasta e mescola bene. Aggiusta con abbondante pepe nero macinato. Servi caldo.");
        recipe1.setCreationDate(today);
        recipeRepository.save(recipe1);


        Recipe recipe2 = new Recipe();
        recipe2.setTitle("Insalata Caprese");
        recipe2.setIngredientsList("4 pomodori maturi, 200g di mozzarella di bufala, alcune foglie di basilico fresco, olio extravergine di oliva, sale");
        recipe2.setUrlPhoto("https://static.vecteezy.com/system/resources/previews/021/950/173/original/white-plate-with-caprese-salad-isolated-on-a-transparent-background-png.png");
        recipe2.setTimeOfPreparation(10);
        recipe2.setPortion(4);
        recipe2.setTextOfRecipe("Taglia i pomodori a fette spesse e la mozzarella a fette dello stesso spessore. Alterna fette di pomodoro e mozzarella su un piatto. Aggiungi le foglie di basilico fresco tra gli strati. Condisci con olio extravergine di oliva e sale a piacere.");
        recipe2.setCreationDate(today);
        recipeRepository.save(recipe2);


        Recipe recipe3 = new Recipe();
        recipe3.setTitle("Tiramisù");
        recipe3.setIngredientsList("200g di savoiardi, 250g di mascarpone, 3 uova, 100g di zucchero, 300ml di caffè espresso, cacao amaro in polvere");
        recipe3.setUrlPhoto("https://w7.pngwing.com/pngs/61/938/png-transparent-square-cake-beside-spoon-coffee-tiramisu-ladyfinger-italian-cuisine-chocolate-cake-chocolate-cake-with-spoon-cream-food-frozen-dessert.png");
        recipe3.setTimeOfPreparation(30);
        recipe3.setPortion(6);
        recipe3.setTextOfRecipe("Prepara il caffè e lascialo raffreddare. In una ciotola, separa i tuorli dagli albumi. Sbatti i tuorli con lo zucchero finché otterrai un composto chiaro e spumoso. Aggiungi il mascarpone al composto di tuorli e zucchero e mescola bene. In un'altra ciotola, monta gli albumi a neve ferma e incorporali delicatamente alla crema di mascarpone. Immergi rapidamente i savoiardi nel caffè e foderane il fondo di una teglia. Versa metà della crema di mascarpone sulla base di savoiardi. Ripeti il processo con un altro strato di savoiardi e crema di mascarpone. Copri con pellicola trasparente e metti in frigorifero per almeno 4 ore o preferibilmente durante la notte. Prima di servire, spolvera la superficie con cacao amaro in polvere.");
        recipe3.setCreationDate(today);
        recipeRepository.save(recipe3);
    }
}

