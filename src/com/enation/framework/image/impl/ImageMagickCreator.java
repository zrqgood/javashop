package com.enation.framework.image.impl;

import java.io.File;
import java.io.FileInputStream;

import magick.CompositeOperator;
import magick.ImageInfo;
import magick.MagickApiException;
import magick.MagickException;
import magick.MagickImage;

import com.enation.framework.image.IThumbnailCreator;
import com.enation.framework.image.ImageRuntimeException;


/**
 * 使有ImageMagick组件生成缩略图
 * @author kingapex
 * 2010-7-10下午11:43:24
 */
public class ImageMagickCreator implements IThumbnailCreator {
	static {
		System.setProperty("jmagick.systemclassloader", "no");
	} 
   
	private String source;

	private String target;

	private ImageInfo info;

	private MagickImage image;

	private double width;

	private double height;

	public ImageMagickCreator(String _source, String _target){
		source = _source;
		target = _target;

		try {
			File f = new File(_source);
			FileInputStream fis = new FileInputStream(f);

			byte[] b = new byte[(int)f.length()];
			fis.read(b);
			
			info = new ImageInfo( );
			image = new MagickImage(info,b);

			// 得到原图的宽和高
			width = image.getDimension().getWidth();
			height = image.getDimension().getHeight();
		} catch (Exception e) {

			e.printStackTrace();
			throw new ImageRuntimeException(source, "构造jmagickutils");
		}

	}
	
	public void resize(int w, int h) {

		int target_w, target_h; // 目标宽高
		int x = 0, y = 0; // 缩略图在背景的座标
		x = y = 0;
		target_w = w;
		target_h = h;

		MagickImage scaled = null;
		try {

			/* 计算目标宽高 */
			if (width / height > w / h) { // 原图长:上下补白
				target_w = w;
				target_h = (int) (target_w * height / width);
				x = 0;
				y = (int) (h - target_h) / 2;

			}

			if (width / height < w / h) { // 原图高:左右补白
				target_h = h;
				target_w = (int) (target_h * width / height);
				y = 0;
				x = (int) (w - target_w) / 2;
			}
			MagickImage thumb_img = image.scaleImage(target_w, target_h);
			MagickImage blankImage = new MagickImage();

			byte[] pixels = new byte[w * h * 4];
			for (int i = 0; i < w * h; i++) {
				pixels[4 * i] = (byte) 255;
				pixels[4 * i + 1] = (byte) 255;
				pixels[4 * i + 2] = (byte) 255;
				pixels[4 * i + 3] = (byte) 255;
			}

			blankImage.constituteImage(w, h, "RGBA", pixels);
			blankImage.compositeImage(CompositeOperator.AtopCompositeOp,
					thumb_img, x, y);
			blankImage.setFileName(target);
			blankImage.writeImage(info);

		} catch (MagickApiException ex) {
			ex.printStackTrace();
			throw new ImageRuntimeException(source, "生成缩略图");
		} catch (MagickException ex) {
			ex.printStackTrace();
			throw new ImageRuntimeException(source, "生成缩略图");
		} finally {
			if (scaled != null) {
				scaled.destroyImages();
			}
		}
		 

	}

	
	public static void main(String args[]){
		ImageMagickCreator creator = new ImageMagickCreator("d:/1.jpg", "d:/2.jpg");
		creator.resize(200, 200);
		
	}
	
	
}
