package etphoneshome.player;

public class Player {
	
	private int score;
	
	private String name;
	
	
	public Player(int score, String name) {
		this.setScore(score);
		this.setName(name);
	}

	public Player(Player player) {
		this.setScore(player.getScore());
		this.setName(player.getName());
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}


	/**
	 * sets the score if it is 0 or higher
	 * @param score the score to set
	 */
	public void setScore(int score) {
		if (score >= 0) {
			this.score = score;
		}
	}


	/**
	 * @return the name
	 */
	public String getName() {
		String copy = this.name;
		return copy;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * returns a string representation of the {@code Player}
	 */
	public String toString() {
		String a = this.name + " " + this.score;
		return a;
	}
	
	

}
