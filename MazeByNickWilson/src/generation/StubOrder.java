package generation;

public class StubOrder implements Order {

	private int skillLevel;
	private Builder myBuilder;
	private Boolean perfect;
	private int seed;
	private Maze myMaze;
	
	public StubOrder(int skill, Builder b, Boolean p, int s) {
		// TODO Auto-generated constructor stub
		skillLevel = skill;
		myBuilder = b;
		perfect = p;
		seed = s;
	}
	
	@Override
	public int getSkillLevel() {
		// TODO Auto-generated method stub
		return skillLevel;
	}

	@Override
	public Builder getBuilder() {
		// TODO Auto-generated method stub
		return myBuilder;
	}

	@Override
	public boolean isPerfect() {
		// TODO Auto-generated method stub
		return perfect;
	}

	@Override
	public int getSeed() {
		// TODO Auto-generated method stub
		return seed;
	}

	@Override
	public void deliver(Maze mazeConfig) {
		// TODO Auto-generated method stub
		myMaze = mazeConfig;
	}
	
	public Maze getMaze() {
		return myMaze;
	}

	@Override
	public void updateProgress(int percentage) {
		// TODO Auto-generated method stub
	}

}
