package deployment;

public interface Observable {
	public void notifyObs();
	// whenever changes happen notify observer!

	public void notifyObsPayload();
	// notify a specific observer

	public void addObs(Observer obs);
	// add observer

	public void addObsPayload(ObserverPayload obp);
	// add observer with payload carrying capabilities

	public void removeObs(Observer obs);
	// useless in this scenario

	public void removeObsPayload(ObserverPayload obp);
	// useless piece of code
}
