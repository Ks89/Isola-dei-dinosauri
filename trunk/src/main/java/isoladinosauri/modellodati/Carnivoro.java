package isoladinosauri.modellodati;

import java.util.Random;

import isoladinosauri.Cella;

public class Carnivoro extends Dinosauro {

	@Override
	public int calcolaForza() {
		return (2 * super.energia * super.dimensione);
	}

	@Override
	public void deponi() {
		super.energia -= 1500;
		/* manca la gestione del nuovo dinosauro (uovo) */
	}

	@Override
	public boolean aggCordinate(int posX, int posY) {
		super.energia -= 10 * (int)Math.pow(2, (double)super.dimensione);
		if(super.getEnergia()>0) { 
			//eseguo movimento nelle coordinate specificate
			super.setPosX(posX);
			super.setPosY(posY);
			return true;
			//cella.setDinosauro(this);
		} else {
			//il dino deve essere cancellato dalla cella e dalla lista del giocatore
			//dal metodo che chaiama aggCordinate
			return false;
		}
	}
		
	public Carnivoro(int posX, int posY, int turnoNascita) {
		super.setEnergia(750);
		super.energiaMax=1000;
		super.posX = posX;
		super.posY = posY;
		super.dimensione=1;
		Random random = new Random();
		super.durataVita = random.nextInt(12) + 24;
		super.turnoNascita = turnoNascita;
	}
	
	public void mangia(Animale animale, Cella cella) {
		//questo metodo e' chiamato SOLO se this si � mosso su cella con un altro dino
		
		//se e' un caragona
		if (animale instanceof Carogna) {
			//mangia un animale che puo essere Dinosauro o una carogna
			//NB: passo anche la Cella per sapere dove si trova l'animale e la carogna
			//in modo che possa rimuoverli nel caso uno dei 2 muoia/si esaurisca
			Carogna mangiato = (Carogna)animale;
			//mangio tutta la carogna
			if(mangiato.getEnergia()<=(this.getEnergiaMax() - this.getEnergia())) {
				this.setEnergia(this.getEnergia() + mangiato.getEnergia());
				//rimuovi la carogna
				cella.setOccupante(null);
			}
			//mangio solo una parte della carogna	 
			else {
				// la carogna sara consumata della diff dell'energia max e quella attuale del dino
				mangiato.setEnergia(mangiato.getEnergia() - (this.getEnergiaMax() - this.getEnergia()));
				//il dinosauro avra la sua energia al massimo
				this.setEnergia(this.getEnergiaMax());
			}		
		} 
		
		//il dinosauro carnivoro a muoversi su una cella con un erbivoro
		if (animale instanceof Erbivoro) {
			Erbivoro nemico = (Erbivoro)animale;
			if(this.calcolaForza()>=nemico.calcolaForza()) {
				//il carnivoro vince il combattimento e mangia l'erbivoro
				cella.setDinosauro(this);
				this.setEnergia(this.getEnergia() + ((int)0.75 * nemico.getEnergia()));
			}
			else {
				//il carnivoro perde il combattimento e l'erbivoro non fa nulla
				cella.setDinosauro(nemico);
			}
		}
		
		//il dinosauro carnivoro a muoversi su una cella con un altro carnivoro
		if (animale instanceof Carnivoro) {	
			Carnivoro nemico = (Carnivoro)animale;
			if(this.calcolaForza()>=nemico.calcolaForza()) {
				//il carnivoro vince il combattimento e mangia l'altro carnivoro
				cella.setDinosauro(this);
				this.setEnergia(this.getEnergia() + ((int)0.75 * nemico.getEnergia()));
			}
			else {
				//il carnivoro perde il combattimento e l'erbivoro non fa nulla
				cella.setDinosauro(nemico);
				nemico.setEnergia(nemico.getEnergia() + ((int)0.75 * this.getEnergia()));
			}
		}			
	}
}