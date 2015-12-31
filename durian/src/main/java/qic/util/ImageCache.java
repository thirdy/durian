package qic.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.io.Files;
import com.google.common.util.concurrent.UncheckedExecutionException;

/**
 * @author thirdy
 *
 */
public class ImageCache {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	// Private constructor prevents instantiation from other classes
	private ImageCache() {
		imageCache = CacheBuilder.newBuilder()
				.maximumSize(1000)
				.build(new ImageCacheLoader());
	}

	private static final ImageCache INSTANCE = new ImageCache();

	public static ImageCache getInstance() {
		return ImageCache.INSTANCE;
	}

	private LoadingCache<String, Image> imageCache;

	public Image get(String key) {
		Image image = null;
		if (StringUtils.isNotBlank(key)) {
			try {
				image = imageCache.get(key);
			} catch (UncheckedExecutionException | ExecutionException e) {
				logger.warn("Exception in loading image: " + key + ". Returning default image.",  e);
			}
		} else {
//			logger.warn("Key for image to load is empty. Returning default image.");
		}
		return image;
	}
	
	public void preLoad(String key) {
		get(key);
	}

	private static class ImageCacheLoader extends CacheLoader<String, Image> {

		@Override
		public Image load(String key) throws Exception {
			File imagesDirectory = new File("images");
			if(!imagesDirectory.exists()) imagesDirectory.mkdir();
					
			String fileName = null;
			Image image = null;
			
			// handle classpath
//			if (key.startsWith("/")) {
//				image = new Image(key, false);
//			} else if (Main.DEVELOPMENT_MODE) {
//				image = new Image("/images/debugmode/TwoHandAxe_spare.png", false);
//			} else {
				URL url = new URL(key);
				fileName = url.toURI().getRawPath();
				File imageFile = new File(imagesDirectory, fileName);
				if (imageFile.exists()) {
					// Try loading from disk
//					String _url = imageFile.toURI().toString();
					image = ImageIO.read(imageFile);
				} else {
					// Load from url and save to disk
					image = ImageIO.read(new URL(key));
					saveToDisk(fileName, image, imageFile);
				}
//			}

			return image;
		}

		private void saveToDisk(String fileName, Image image, File imageFile) throws IOException {
			Files.createParentDirs(imageFile);
			BufferedImage bufferedImage = (BufferedImage) image;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( bufferedImage, StringUtils.substringAfterLast(fileName, "."), baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			Files.write(imageInByte, imageFile);
		}

	}
}