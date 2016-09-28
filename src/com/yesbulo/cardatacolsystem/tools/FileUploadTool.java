package com.yesbulo.cardatacolsystem.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.yesbulo.cardatacolsystem.impl.Big2Small4PicUtil;
import com.yesbulo.cardatacolsystem.impl.ObjectDaoImpl;
import com.yesbulo.cardatacolsystem.pojo.Users;
import com.yesbulo.cardatacolsystem.server.ObjectDao;

/**
 * <p>@Title:FileUploadTool</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-24 下午02:32:03
 */
public class FileUploadTool {
	private File file;
	private String fileFileName;
	private int isUser;

	private String code;

	// 上传图片
	@SuppressWarnings("deprecation")
	public String uploadImg() throws IOException {
		
		System.out.println("上传图片");
		// 得到工程保存图片的路径
		String root = ServletActionContext.getRequest().getRealPath("/upload");
		InputStream is;
		OutputStream os;
		// 获取时间戳
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		try {
			// 用时间戳命名图片
//			fileFileName = str
//					+ fileFileName.substring(fileFileName.lastIndexOf("."));
			is = new FileInputStream(file);
				// 保存头像
				
//				fileFileName = fileFileName;
					// 得到图片保存的位置(根据root来得到图片保存的路径在Tomcat下的该工程里)
					File destFile = new File(root, fileFileName);

					// 把图片写入到上面设置的路径里
					os = new FileOutputStream(destFile);
					byte[] buffer = new byte[400];
					int length = 0;
					while ((length = is.read(buffer)) > 0) {
						os.write(buffer, 0, length);
					}
					os.close();
					setCode("1");
				
				is.close();
				System.out.println("是头像");
		} catch (Exception e) {
			setCode("0");
		}
		return "success";
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setIsUser(int isUser) {
		this.isUser = isUser;
	}

}
