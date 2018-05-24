package deployment.model;

import deployment.model.StockQuoteWSStub.GetFieldNamesResponse;
import deployment.model.StockQuoteWSStub.GetQuote;
import deployment.model.StockQuoteWSStub.GetQuoteResponse;

public class WSDLStage1 {

	private static final int SYMBOLINDEX = 0;
	private static final int LASTTRADEINDEX = 1;
	private static final int DATEINDEX = 2;
	private static final int TIMEINDEX = 3;

	private String chosen_symbol;

	public void set(String symb) {
		this.chosen_symbol = symb;
	}

	public String get() {

		String return_value = "Error";

		try {
			final StockQuoteWSStub StockQuoteService = new StockQuoteWSStub();

			GetFieldNamesResponse FieldNamesResponse = StockQuoteService.getFieldNames();

			String[] fieldNames = FieldNamesResponse.get_return();

			for (int i = 0; i < 1 /* symbols.length */; i++) {

				GetQuote QuoteRequest = new GetQuote();
				QuoteRequest.setSymbol(chosen_symbol
				// for debugging purposes let's keep ACB the default
				// be wary!
				// constructor (public Payload(String symbol)) might not be needed.
				/* symbol needs to be communicated to this (Payload class) via subjects */ /* chosen_symbol */); // crashes
																													// here
				GetQuoteResponse QuoteResponse = StockQuoteService.getQuote(QuoteRequest);
				String[] StockQuote = QuoteResponse.get_return();

				// for debugging
				/*
				 * System.out.print(StockQuote[SYMBOLINDEX] + ":\n\t" +
				 * fieldNames[LASTTRADEINDEX] + ":\t" + StockQuote[LASTTRADEINDEX] + "\n\t" +
				 * fieldNames[DATEINDEX] + ":\t" + StockQuote[DATEINDEX] + "\n\t" +
				 * fieldNames[TIMEINDEX] + ":\t" + StockQuote[TIMEINDEX] + "\n\n ");
				 */

				return_value = StockQuote[SYMBOLINDEX] + ":\n\t" + fieldNames[LASTTRADEINDEX] + ":\t"
						+ StockQuote[LASTTRADEINDEX] + "\n\t" + fieldNames[DATEINDEX] + ":\t" + StockQuote[DATEINDEX]
						+ "\n\t" + fieldNames[TIMEINDEX] + ":\t" + StockQuote[TIMEINDEX] + "\n\n ";

			}
		} catch (Exception e) {
			System.out.println("Something went horribly wrong in the WebService request \n" + e.getMessage());
		} finally {

		}

		// return payload
		return return_value;
	}
}
