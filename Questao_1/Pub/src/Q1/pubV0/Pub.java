package Q1.pubV0;

public class Pub {

	// metodo computeCost refatorado
	public int computeCost(String drink_name, boolean student, int amount) {
		// acha o Drink
		Drink drink = Drink.getDrink(drink_name);
		
		// verifica se sua quantidade eh valida 
		if (drink.amount_is_valid(amount)) {
			// retorna o valor total se for valido
			return amount*drink.getPrice(student);
		}else {
			// retorna erro se nao for valido
			throw new RuntimeException("Too many drinks, max 2.");
		}
	}
}
