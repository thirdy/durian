package qic.ui.extra;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.SwingWorker;

public class Worker<T> extends SwingWorker<T, Void> {
	Supplier<T> supplier;
	Consumer<T> consumer;

	public Worker(Supplier<T> supplier, Consumer<T> consumer) {
		super();
		this.supplier = supplier;
		this.consumer = consumer;
	}

	@Override
	public T doInBackground() {
		return supplier.get();
	}

	@Override
	protected void done() {
		try {
			consumer.accept(get());
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}
}