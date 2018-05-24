package deployment;

public interface Observable {
	public void notifyObs();
	// whenever changes happen notify observer!

	public void addObs(Observer obs);
	// add observer

	public void removeObs(Observer obs);
	// useless in this scenario
}
