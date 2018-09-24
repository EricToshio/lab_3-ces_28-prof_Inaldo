package Q1.pubV0;

import java.util.LinkedList;

public class Ingredient {
	
	// variaveis da classe (ingredientes disponiveis) 
	static LinkedList<Ingredient> all_ingredients = new LinkedList<Ingredient>();
	
	// iniciando variaveis de classe (ingredientes iniciais)
	static {
		all_ingredients.add(new Ingredient("rum",65));
		all_ingredients.add(new Ingredient("grenadine",10));
		all_ingredients.add(new Ingredient("lime juice",10));
		all_ingredients.add(new Ingredient("green stuff",10));
		all_ingredients.add(new Ingredient("tonic water",20));
		all_ingredients.add(new Ingredient("gin",85));
	}
	
	// variaveis de instancia
	String name;
	int value; 
	
	Ingredient(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	// metodo publico
	public static int getValue(String ingredient_name) {
        for(Ingredient ingredient : all_ingredients)
        {
        	if (ingredient_name == ingredient.name) {
        		return ingredient.value;
        	}
        }
        throw new RuntimeException("No such ingredient exists");
	}
	
	// METODO ADICIONAL: adicionar ingrediente
	public void add(String name, int value) {
		all_ingredients.add(new Ingredient(name,value));
	}
	
	// METODO ADICIONAL: remover ingrediente
	public void remove(String ingredient_name) {
		for(Ingredient ingredient : all_ingredients)
        {
        	if (ingredient_name == ingredient.name) {
        		all_ingredients.remove(ingredient);
        	}
        }
        throw new RuntimeException("No such ingredient exists");
	}
	
}
