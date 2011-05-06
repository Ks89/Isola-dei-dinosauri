package isoladinosauri.modellodati;

import java.util.Random;

public class Vegetale extends Organismo implements Occupante {
	
	public Vegetale() {
		Random random = new Random();
		super.setEnergiaMax(random.nextInt(200) + 150);
	}
	
	public void cresci() {
		super.energia += super.energiaMax/10;
	}	                                 
}
