package Controller;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import View.TemporaryExplorerPane;

public class DelRepoClickListener implements MouseListener{
	
	String textFieldValue;
	String repoName;
	
	private TemporaryExplorerPane view;
	
	public DelRepoClickListener() {
		
	}
	
	public DelRepoClickListener(TemporaryExplorerPane view) {
		this();
		this.view = view;
	}
	
	public DelRepoClickListener(TemporaryExplorerPane view, String repoName) {
		this(view);
		this.view = view;
		this.repoName = repoName;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//JTextField txtField = (JTextField) e.getSource();
		//textFieldValue = txtField.getText();
		
		delReposit(repoName);
	}
	
	public void delReposit(String rn) {
		
		if (rn == null) {
			JOptionPane.showMessageDialog(null, "일치하는 Repository가 없습니다!", "Error!", JOptionPane.ERROR_MESSAGE);
		} else {
			int result = JOptionPane.showConfirmDialog(null, "파일을 삭제하시겠습니까?",
													"파일 삭제", JOptionPane.YES_NO_OPTION);
			
			if (result == JOptionPane.CLOSED_OPTION) { //사용자가 창을 닫은 경우  
				
			} else if (result == JOptionPane.YES_OPTION) { // 사용자가 예 를 선택한 경우 
				String path = "." + File.separator + "GitHub" + File.separator + rn;
				File repoDirectory = new File(path);
				
					while (repoDirectory.exists()) {
						if (repoDirectory.isDirectory()) {
							File[] files = repoDirectory.listFiles(); // 파일 리스트 얻어오기  
							
							for (int i = 0; i < files.length; i++) {
								if (files[i].delete()) {
									System.out.println(files[i].getName() + "파일을 삭제했습니다.");
									JOptionPane.showMessageDialog(null, "파일 삭제 완료", "파일 삭제", JOptionPane.PLAIN_MESSAGE);
								} else {
									System.out.println(files[i].getName() + "파일을 삭제하는 데 실패했습니다.");
									JOptionPane.showMessageDialog(null, "파일 삭제 실패", "파일 삭제", JOptionPane.ERROR_MESSAGE);
									break;
								}
							}
							
							if(files.length == 0 && repoDirectory.isDirectory()){ 
								repoDirectory.delete(); //대상폴더 삭제
								System.out.println("폴더가 삭제되었습니다.");
								//JOptionPane.showMessageDialog(null, "폴더 삭제 완료", "폴더 삭제", JOptionPane.PLAIN_MESSAGE);
							}
						}
						//break;
					} 
					loadRepo();	
				
				
			} else {
				JOptionPane.showMessageDialog(null, "Repository 삭제를 취소했습니다.", "삭제 취소", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	
	public void loadRepo() {
		LoadRepository init = new LoadRepository(view);
		init.setRepositoryList();
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
