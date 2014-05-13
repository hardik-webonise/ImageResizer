import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageResizer {
	private static double XHDPI = 66.66;
	private static double HDPI = 50;
	private static double MDPI = 33.33;
	private static String workingDirectory;

	public static void main(String[] args) {
		System.out
				.println("Sit back and relax while we resize image for you...");
		resize();
	}

	public static void resize() {
		workingDirectory = System.getProperty("user.dir") + File.separator
				+ "src" + File.separator + "res";
		File directory = new File(workingDirectory);
		File[] files = directory.listFiles();
		if (files != null && files.length > 0) {
			for (int count = 0; count < files.length; count++) {
				try {
					if (files[count].isFile()) {
						scaleImage(XHDPI, files[count]);
						scaleImage(HDPI, files[count]);
						scaleImage(MDPI, files[count]);
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
			System.out.println("Resizeing done, check the respected folders");
		} else {
			System.out.println("We don't find any images");
		}
	}

	private static void scaleImage(double dpi, File image) throws Exception {
		BufferedImage inputImage = ImageIO.read(image);

		int height = getScaledPixel(inputImage.getHeight(), dpi);
		int width = getScaledPixel(inputImage.getWidth(), dpi);
		BufferedImage outputImage = new BufferedImage(width, height,
				inputImage.getType());
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, width, height, null);
		g2d.dispose();

		writeScaledImage(outputImage, image.getName(), dpi);

	}

	private static int getScaledPixel(int baseValue, double percent) {
		return (int) ((percent * baseValue) / 100);
	}

	private static void writeScaledImage(BufferedImage outputImage,
			String formatName, double dpi) throws Exception {
		String outputPath = null;
		File outputDirectry = null;
		if (dpi == XHDPI) {
			outputPath = workingDirectory + File.separator + "drawable-xhdpi";
		} else if (dpi == HDPI) {
			outputPath = workingDirectory + File.separator + "drawable-hdpi";
		} else {
			outputPath = workingDirectory + File.separator + "drawable-mdpi";
		}

		outputDirectry = new File(outputPath);
		if (!outputDirectry.exists()) {
			outputDirectry.mkdir();
		}
		File outputFile = new File(outputDirectry, formatName);
		outputFile.createNewFile();
		formatName = formatName.substring(formatName.lastIndexOf(".") + 1);
		ImageIO.write(outputImage, formatName, outputFile);
	}
}
