package app;

public class ReadContext {
	private ReadStrategy strategy;
	
	public ReadContext(ReadStrategy strategy){
		this.strategy = strategy;
	}
	
	public void setReadStrategy(final ReadStrategy strategy){
		this.strategy = strategy;
	}
	
	public void read(Main m){
		strategy.read(m);
	}

}
