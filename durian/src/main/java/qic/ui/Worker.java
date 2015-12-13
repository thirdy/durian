package qic.ui;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.SwingWorker;

public class Worker<T> extends SwingWorker<T, Void> {
	Supplier<T> supplier;
	Consumer<T> consumer;
	Consumer<Exception> onException;
	public Worker(Supplier<T> supplier, Consumer<T> consumer, Consumer<Exception> onException) {
		super();
		this.supplier = supplier;
		this.consumer = consumer;
		this.onException = onException;
	}
	@Override public T doInBackground() {
        return supplier.get();
    }
	@Override protected void done() {
		try {
			consumer.accept(get());
		} catch (Exception e) {
			onException.accept(e);
		}
	}
}