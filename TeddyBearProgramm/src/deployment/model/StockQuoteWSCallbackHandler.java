
package deployment.model;

/**
 * StockQuoteWSCallbackHandler Callback class, Users can extend this class and
 * implement their own receiveResult and receiveError methods.
 */
public abstract class StockQuoteWSCallbackHandler {

	protected Object clientData;

	/**
	 * User can pass in any object that needs to be accessed once the NonBlocking
	 * Web service call is finished and appropriate method of this CallBack is
	 * called.
	 * 
	 * @param clientData
	 *            Object mechanism by which the user can pass in user data that will
	 *            be avilable at the time this callback is called.
	 */
	public StockQuoteWSCallbackHandler(Object clientData) {
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public StockQuoteWSCallbackHandler() {
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData() {
		return clientData;
	}

	/**
	 * auto generated Axis2 call back method for getFieldNames method override this
	 * method for handling normal response from getFieldNames operation
	 */
	public void receiveResultgetFieldNames(StockQuoteWSStub.GetFieldNamesResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling error
	 * response from getFieldNames operation
	 */
	public void receiveErrorgetFieldNames(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getQuote method override this
	 * method for handling normal response from getQuote operation
	 */
	public void receiveResultgetQuote(StockQuoteWSStub.GetQuoteResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling error
	 * response from getQuote operation
	 */
	public void receiveErrorgetQuote(java.lang.Exception e) {
	}

}
