package deployment;

public interface ObserverPayload {
	public void update();
	// just for the sake of sakes

	public void update(Object payload);
	// observer that takes in payload as input parameter

	public void setObservable(Observable subj);
	// code to hook observer to subject

}
