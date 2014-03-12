package com.enation.framework.util;

import java.awt.Font;
import java.awt.FontMetrics;

import magick.DrawInfo;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import magick.PixelPacket;

/**
 * 图片文字水印工具类
 * @author kingapex
 * 2010-8-25下午05:40:04
 */
public class ImageMagickMaskUtil {
	static {
		System.setProperty("jmagick.systemclassloader", "no");
	} 
	/**
	 * 给某个图片加文字水印
	 * @param source 要加水印的图片路径
	 * @param text	水印文字
	 * @param fontcolor 文字颜色
	 * @param fontsize 文字大小 
	 * @param x 水印的x坐标
	 * @param y 水印的y坐标
	 * @param fontPath 字体路径 ，如果是中文要传给相应字体（ttf）的路径。如果是英文可以传为null
	 */
	public void mask(String source, String text,String fontcolor,int fontsize, int pos,String fontPath) {
		try {
			System.out.println(source);
			ImageInfo info = new ImageInfo(source);
			MagickImage aImage = new MagickImage(info);
			
			Double width = aImage.getDimension().getWidth();
			Double height = aImage.getDimension().getHeight();
			
			DrawInfo aInfo = new DrawInfo(info);

			aInfo.setFill(PixelPacket.queryColorDatabase(fontcolor));
			aInfo.setOpacity(0);
			aInfo.setPointsize(fontsize);
			
			if(fontPath!=null)
				aInfo.setFont(fontPath);
			Font f = new Font("宋体", Font.BOLD, fontsize);   
			
			aInfo.setTextAntialias(true);
			aInfo.setText(text);
//			FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);  
//		    int textwidth= fm.stringWidth(text);
//		    int textheight = fm.getHeight();
			 int textwidth= 100;
			  int textheight =20;
			int[] xy = this.getXy(width.intValue(), height.intValue(), textwidth,textheight, pos);
			aInfo.setGeometry("+"+xy[0]+"+"+xy[1]);
			
			aImage.annotateImage(aInfo);
			 
			aImage.setFileName(source);
			aImage.writeImage(info);
			aImage.destroyImages();
			aImage = null;
		} catch (MagickException e) {
			e.printStackTrace();
		}

	}
	
	private int[] getXy(int width,int height,int textwidth,int textheight,int pos){
		System.out.println("width["+width+"]height["+height+"]textwidth["+textwidth+"]");
		int x=0;int y=0;
		int margin=20;
		switch (pos) {
		case 1://顶部居左
			x= margin;
			y=margin;
			break;
		case 2://顶部居中
			y=margin;
			x = width/2-textwidth/2;
			break;
		case 3: //顶部居右
			y=margin;
			x=width-margin-textwidth;
			break;
		case 4://左部居中
			y= height/2-textheight/2;
			x=margin;
			break;
		case 5: //图片中心
			y= height/2-textheight/2;
			x = width/2-textwidth/2;
			break;
		case 6: //右部居中
			y= height/2-textheight/2;
			x=width-margin-textwidth;
			break;
		case 7: //底部居左
			x= margin;
			y = height-textheight;
			break;
		case 8: //底部居中
			x = width/2-textwidth/2;
			y = height-textheight;		
			break;
		case 9: //底部居右
			x=width-margin-textwidth;
			y = height-textheight;		
			break;
		default:
			break;
		}
		System.out.println("x["+x+"]y["+y+"]");
		return  new int[]{x,y};
	}

	public static void main(String[] args) {
		ImageMagickMaskUtil magickMask = new ImageMagickMaskUtil();
		magickMask.mask("d:/temp.jpg", "易族智汇","#000000",16,5,"e:/st.TTF");
	}

}
