package Q1.pubV0;
import java.util.Arrays;
import java.util.LinkedList;


public class Drink {
	// variaveis da classe (drinks disponiveis) 
	 static LinkedList<Drink> drinks = new LinkedList<Drink>();
	 
	// iniciando variaveis de classe (drinks iniciais)
	 static {
		 drinks.add(new Drink("hansa",74, true));
		 drinks.add(new Drink("grans",103, true));
		 drinks.add(new Drink("strongbow",110, true));
		 drinks.add(new Drink("gt", new LinkedList<String>(Arrays.asList("green stuff","tonic water","gin")),2, false));
		 drinks.add(new Drink("bacardi_special", new LinkedList<String>(Arrays.asList("rum","grenadine","lime juice","gin")),2, false));
		 
	 }
	 	
	// Variaveis de instancia 
	String name;
	int price;
	int max_amount;
	LinkedList<String> ingredients = new LinkedList<String>();
	Boolean student_discont;
	// se o preco e fixo ou se depende dos igrendientes
	Boolean fixed_price;
	
	// Construtores
	Drink(String name, int price, Boolean student_discont ) {
		this.name = name;
		this.price = price;
		this.max_amount = Integer.MAX_VALUE;
		this.student_discont = student_discont;
		this.fixed_price = true;
	}
	Drink(String name,LinkedList<String> ingredients, int amount, Boolean student_discont) {
		this.name = name;
		this.ingredients = ingredients;
		this.max_amount = amount;
		this.student_discont = student_discont;
		this.fixed_price = false;
	}
	// metodos publicos
	
	// Achar o drink
	public static Drink getDrink(String drink_name) {
		for(Drink drink : drinks) {
			if (drink_name ==  drink.name) {
				return drink;
			}
		}
		throw new RuntimeException("No such drink exists");
	}
	// Verificar se e valido a quantidade
	public boolean amount_is_valid(int amount) {
		return amount <= this.max_amount; 
	}
	// Obter preco de um drink
	public int getPrice(boolean is_student) {
		int price = 0;
		if (this.fixed_price) {
			price = this.price;
		}else {
			for(String ingredient : this.ingredients)
	        {
	        	price += Ingredient.getValue(ingredient);
	        }
		}
		
		if (this.student_discont && is_student) {
			price = price - price/10;
		}
		return price;
	}
	
	// METODO ADICIONAL: adicionar drink
	public void add(String name, int price, Boolean student_discont) {
		drinks.add(new Drink(name, price, student_discont));
	}
	public void add(String name,LinkedList<String> ingredients, int amount, Boolean student_discont) {
		drinks.add(new Drink(name, ingredients, amount,student_discont));
	}
	
	// METODO ADICIONAL: remover drink
	public void remove(String drink_name) {
		for(Drink drink : drinks)
        {
        	if (drink_name == drink.name) {
        		drinks.remove(drink);
        	}
        }
        throw new RuntimeException("No such drink exists");
	}
	// METODO ADICIONAL: modificar max_amount
	public void setMax_amount(int amount) {
		this.max_amount = amount;
	}
	// METODO ADICIONAL: modificar disconto de estudante
	public void setStudent_discont(Boolean student_discont) {
		this.student_discont = student_discont;
	}
	// METODO ADICIONAL: modificar preco fixo
	public void setFixed_price(int price) {
		this.price = price;
		this.fixed_price = true;
	}
		
	// METODO ADICIONAL: modificar preco para ser dependente dos igrendientes
	public void setIgredients(LinkedList<String> ingredients) {
		this.ingredients = ingredients;
		this.fixed_price = false;
	}		


}
