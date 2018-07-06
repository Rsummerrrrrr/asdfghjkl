package com.wxq;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import java.awt.Color;
public class Qrcode {
	public static void creatCode(String content, String imgPath, int version, String startRgb, String endRgb) throws IOException{
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeVersion(version);
		//Ĭ�ϰ汾��3
        //System.out.println("�汾�ţ� "+qrcode.getQrcodeVersion());
		//�Ŵ��ʣ�L(7%)  Q(25%)  H(30%)
        //�Ĵ���Խ�ߣ��ɴ洢��Ϣ��Խ��
		qrcode.setQrcodeErrorCorrect('Q');
		//ע��汾��ϢN�������֣�A����a-�ڣ�A-Z��B���������ַ�
		qrcode.setQrcodeEncodeMode('B');
		System.out.println("�ɴ洢���ͣ�"+qrcode.getQrcodeEncodeMode());
		int imgSize = 67 + (version - 1)*12;	
		//���ɶ�ά�뻺�����
		BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_BGR);
		//�����滭����
		Graphics2D gs = bufferedImage.createGraphics();
		//���ñ���
		gs.setBackground(Color.WHITE);
		//���������ɫ
		gs.setColor(Color.BLACK);
		//������
		gs.clearRect(0, 0, imgSize, imgSize);
		//��ά������
		//ͨ����ά��Ҫ�������ݻ�ȡһ�������͵Ķ�ά����
		boolean [][] calQrcode = qrcode.calQrcode(content.getBytes());
		//����ƫ����
		int pixoff = 2;
		int startR = 1,startG = 0,startB = 20;
		int endR = 0,endG = 30,endB = 0;
		if (null != endRgb) {
			String [] rgb = startRgb.split(",");
			startR = Integer.valueOf(rgb[0]);
			startG = Integer.valueOf(rgb[1]);
			startB = Integer.valueOf(rgb[2]);
		}
		if (null != startRgb) {
			String [] rgb = endRgb.split(",");
			endR = Integer.valueOf(rgb[0]);
			endG = Integer.valueOf(rgb[1]);
			endB = Integer.valueOf(rgb[2]);
		}

		for (int i = 0; i < calQrcode.length; i++) {//������
			System.out.println("����:  "+calQrcode.length+ "   ������  "+ calQrcode[i].length);
			for (int j = 0; j < calQrcode.length; j++) {//������
				if (calQrcode [i][j]) {
					int r = startR + (endR - startR)*(j+1)/calQrcode.length;
					int g = startG + (endG - startG)*(j+1)/calQrcode.length;
					int b = startB + (endB - startB)*(j+1)/calQrcode.length;
					Color color = new Color(r,g,b);
					gs.setColor(color);
					gs.fillRect(i*3+pixoff, j*3+pixoff,3,3);
				}
			}
		}
		//���ͷ��
		
		BufferedImage logo = ImageIO.read(new File("D:/1.jpg"));
		//ͷ���С
		int logoSize = imgSize/3;
		//ͷ����ʼλ��
		int o = (imgSize - logoSize)/2;
		//����ά���ϻ�ͷ��
		gs.drawImage(logo, o, o, logoSize,logoSize, null);
		
		
		//�رջ滭����
		gs.dispose();
		//�ѻ�����ͼƬ������ڴ���
		bufferedImage.flush();
		//�ѻ�����ͼƬ�����Ӳ����
		try {
			ImageIO.write(bufferedImage, "png", new File("D:/qr.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("ok");
	}

	private boolean[][] calQrcode(byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	private void setQrcodeVersion(int version) {
		// TODO Auto-generated method stub
		
	}

	private void setQrcodeErrorCorrect(char c) {
		// TODO Auto-generated method stub
		
	}

	private String getQrcodeEncodeMode() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setQrcodeEncodeMode(char c) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) throws IOException {
		String imgPath = "D:/qrcode.png"; 
		int version = 30;
		String content = "BEGIN:VCARD\r\n" + 
		   "FN:����:����ٻ\r\n"+
		   "ORG:ѧУ���ӱ��Ƽ�ʦ��ѧԺ\r\n"+
		   "TITLE:ѧ��\r\n" + 
		   "TEL;CELL;VOICE:15533516865\r\n"+
		   "ADR;WORK:�ػʵ��������ӱ��������360��\r\n"+
		   "ADR;HOME:�ӱ�ʡ��ˮ��\r\n"+
		   "EMAIL;HOME:599546775@qq.com\r\n" + 
		   "END:VCARD";

		String startRgb = "169,157,208";
		String endRgb = "128,233,238";
		creatCode(content,imgPath,version,startRgb,endRgb);
		
	}

}
