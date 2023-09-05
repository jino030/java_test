package co.yedam.prjdb.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class ThumbNail {
	
	/**
	 * 썸네일을 생성하는 메소드
	 * @param filePath : 원본 파일이 저장된 경로
	 * @param fileName : 원본 파일이 저장된 이름
	 * @param fileExt : 원본 파일의 확장자
	 * @throws Exception
	 */
	public String makeThumbnail(String filePathAll, String fileName, String fileExt, String filePath) {
		
		String thumbName = null;
		try {
			// 저장된 원본파일로부터 BufferedImage 객체를 생성합니다.
			BufferedImage srcImg = ImageIO.read(new File(filePathAll));
			
			// 썸네일의 너비와 높이 입니다.
			int dw = 200, dh = 100;
			
			// 원본 이미지의 너비와 높이 입니다.
			int ow = srcImg.getWidth();
			int oh = srcImg.getHeight();
			
			// 원본 너비를 기준으로 하여 썸네일의 비율로 높이를 계산합니다.
			int nw = ow; int nh = (ow * dh) / dw;
			
			// 계산된 높이가 원본보다 높다면 crop이 안되므로
			// 원본 높이를 기준으로 썸네일의 비율로 너비를 계산합니다.
			if(nh > oh) {
				nw = (oh * dw) / dh;
				nh = oh;
			}
			
			// 계산된 크기로 원본이미지를 가운데에서 crop 합니다.
			BufferedImage cropImg = Scalr.crop(srcImg, (ow-nw)/2, (oh-nh)/2, nw, nh);
			
			// crop된 이미지로 썸네일을 생성합니다.
			BufferedImage destImg = Scalr.resize(cropImg, dw, dh);
			
			// 썸네일을 저장합니다. 이미지 이름 앞에 "THUMB_" 를 붙여 표시했습니다.
			thumbName = filePath + "THUMB_" + fileName;
			File thumbFile = new File(thumbName);
			
			ImageIO.write(destImg, fileExt.toUpperCase(), thumbFile);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return thumbName; //thumb 명을 리턴 받는다.
	}
}
