
public class Brick {

	// Fields
	private int rngVal;
	private int powerUpVal;
	
	//Constructor
	Brick() {

		init();
	
	}

	public void init() {
		
		powerUpVal = 0;
		
		rngVal = rng();
		
		// %75 POWER-UP CHANCE
		if(MenuPanel1.difficulty == "Novice") {
			// %25
			if(rngVal < 20 && rngVal >= 95) {
				// NO POWERUP
				setPowerUpVal(0);
			}
			
			// %10
			else if(rngVal >= 20 && rngVal < 30) {
				setPowerUpVal(PowerUp.WIDEPADDLE);
			}
			
			// %10
			else if(rngVal >= 30 && rngVal < 40) {
				setPowerUpVal(PowerUp.SHRINKPADDLE);
			}
			
			// %10
			else if(rngVal >= 40 && rngVal < 50) {
				setPowerUpVal(PowerUp.BALLSPEEDINC);
			}
			
			// %10
			else if(rngVal >= 50 && rngVal < 60) {
				setPowerUpVal(PowerUp.BALLSPEEDDEC);
			}
			
			// %5
			else if(rngVal >= 60 && rngVal < 65) {
				setPowerUpVal(PowerUp.FIREBALL);
			}

			// %5
			else if(rngVal >= 65 && rngVal < 70) {
				setPowerUpVal(PowerUp.BALLPOWERLOSE);
			}

			// %5
			else if(rngVal >= 70 && rngVal < 75) {
				setPowerUpVal(PowerUp.EXTRALIFE);
			}

			// %5
			else if(rngVal >= 75 && rngVal < 80) {
				setPowerUpVal(PowerUp.LOSELIFE);
			}
			
			// %5
			else if(rngVal >= 80 && rngVal < 85) {
				setPowerUpVal(PowerUp.CATCHBALL);
			}
			
			// %5
			else if(rngVal >= 85 && rngVal < 90) {
				setPowerUpVal(PowerUp.SHIELD);
			}
			
			// %5
			else if(rngVal >= 90 && rngVal < 95) {
				setPowerUpVal(PowerUp.SHOOTLASER);
			}
		
		}
		
		// %50 POWER-UP CHANCE
		else if(MenuPanel1.difficulty == "Intermediate") {
			// %50
			if(rngVal < 50) {
				// NO POWERUP
				setPowerUpVal(0);
			}
			
			// %5
			else if(rngVal >= 50 && rngVal < 55) {
				setPowerUpVal(PowerUp.WIDEPADDLE);
			}
			
			// %5
			else if(rngVal >= 55 && rngVal < 60) {
				setPowerUpVal(PowerUp.SHRINKPADDLE);
			}
			
			// %5
			else if(rngVal >= 60 && rngVal < 65) {
				setPowerUpVal(PowerUp.BALLSPEEDINC);
			}
			
			// %5
			else if(rngVal >= 65 && rngVal < 70) {
				setPowerUpVal(PowerUp.BALLSPEEDDEC);
			}
			
			// %5
			else if(rngVal >= 70 && rngVal < 75) {
				setPowerUpVal(PowerUp.FIREBALL);
			}

			// %5
			else if(rngVal >= 75 && rngVal < 80) {
				setPowerUpVal(PowerUp.BALLPOWERLOSE);
			}

			// %5
			else if(rngVal >= 80 && rngVal < 85) {
				setPowerUpVal(PowerUp.EXTRALIFE);
			}

			// %5
			else if(rngVal >= 85 && rngVal < 90) {
				setPowerUpVal(PowerUp.LOSELIFE);
			}
			
			// %5
			else if(rngVal >= 90 && rngVal < 95) {
				setPowerUpVal(PowerUp.CATCHBALL);
			}
			
			// %5
			else if(rngVal >= 95 && rngVal < 100) {
				setPowerUpVal(PowerUp.SHIELD);
			}
			
			// NO SHOOTLASER FOR INTERMEDIATE 
//			else if(rngVal >= 90 && rngVal < 95) {
//				setPowerUpVal(PowerUp.SHOOTLASER);
//			}
		}
		
		// %10 POWER-UP CHANCE
		else {
			//Advanced
			// %89 
			if(rngVal < 89) {
				// NO POWERUP
				setPowerUpVal(0);
			}
			
			// %1
			else if(rngVal == 89) {
				setPowerUpVal(PowerUp.WIDEPADDLE);
			}
			
			// %1
			else if(rngVal == 90) {
				setPowerUpVal(PowerUp.SHRINKPADDLE);
			}
			
			// %1
			else if(rngVal == 91) {
				setPowerUpVal(PowerUp.BALLSPEEDINC);
			}
			
			// %1
			else if(rngVal == 92) {
				setPowerUpVal(PowerUp.BALLSPEEDDEC);
			}
			
			// %1
			else if(rngVal == 93) {
				setPowerUpVal(PowerUp.FIREBALL);
			}

			// %1
			else if(rngVal == 94) {
				setPowerUpVal(PowerUp.BALLPOWERLOSE);
			}

			// %1
			else if(rngVal == 95) {
				setPowerUpVal(PowerUp.EXTRALIFE);
			}

			// %1
			else if(rngVal == 96) {
				setPowerUpVal(PowerUp.LOSELIFE);
			}
			
			// %1
			else if(rngVal == 97) {
				setPowerUpVal(PowerUp.CATCHBALL);
			}
			
			// %1
			else if(rngVal == 98) {
				setPowerUpVal(PowerUp.SHIELD);
			}
			
			// %1
			else if(rngVal == 99) {
				setPowerUpVal(PowerUp.SHOOTLASER);
			}
		}
		
	}
	
	public int rng() {
		int value = (int) (Math.random() * 100); //0,1,..,99
		return value;
	}
	
	public int getPowerUpVal() {
		return powerUpVal;
	}
	
	public void setPowerUpVal(int powerUpVal) {
		this.powerUpVal = powerUpVal;
	}
	
}
