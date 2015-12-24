package qic.ui.extra;

import static java.lang.String.format;
import static qic.util.Util.sleep;
import static qic.util.Verify.SOLD;

import java.util.List;
import java.util.function.Consumer;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qic.SearchPageScraper.SearchResultItem;
import qic.util.DurianUtils;
import qic.util.Verify;

public class VerifierTask extends SwingWorker<Void, SearchResultItem> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private Consumer<List<SearchResultItem>> consumer;
	private List<SearchResultItem> itemResults;
	private long sleep = -1;
	private boolean skipIfSold = false;

	public VerifierTask(List<SearchResultItem> itemResults, Consumer<List<SearchResultItem>> consumer, long sleep, boolean skipIfSold) {
		this.itemResults = itemResults;
		this.consumer = consumer;
		this.sleep = sleep;
		this.skipIfSold = skipIfSold;
	}
	
	public VerifierTask(List<SearchResultItem> itemResults, Consumer<List<SearchResultItem>> consumer) {
		this.itemResults = itemResults;
		this.consumer = consumer;
	}

	@Override
    protected Void doInBackground() {
		int countAfterVerify = runVerify(itemResults);
		int difference = itemResults.size() - countAfterVerify;
		logger.info(format("Verified %d items, %d was confirmed verified. A difference of %d.", itemResults.size(), countAfterVerify, difference));
        return null;
    }

	private int runVerify(List<SearchResultItem> itemResults) {
		return itemResults.stream()
			.mapToInt(item -> {
				int result = 0;
				
				logger.info(format("Verifying item %s", item.toShortDebugInfo()));
				Verify verified = DurianUtils.verify(item.thread(), item.dataHash());
				item.verified(verified);
				logger.info(format("Verify result for item %s: %s", item.toShortDebugInfo(), verified));
				
				boolean skip = skipIfSold && verified == SOLD;
				if (!skip) {
					publish(item);
				}
				
				if (verified == Verify.VERIFIED) {
					result = 1;
				}
				
				if (sleep != -1) {
					logger.info(format("Verify - now sleeping for %s millisec", sleep));
					sleep(sleep);
				}
				return result;
			}).sum();
	}

    @Override
    protected void process(List<SearchResultItem> itemResults) {
    	consumer.accept(itemResults);
    }
}