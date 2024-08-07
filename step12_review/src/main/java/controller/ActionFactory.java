package controller;

import controller.action.Action;
import controller.action.AllViewBoardAction;
import controller.action.DeleteBoardAction;
import controller.action.UpdateBoardAction;
import controller.action.UpdateFormBoardAction;
import controller.action.ViewBoardAction;
import controller.action.WriteBoardAction;

public class ActionFactory {
	
	private static ActionFactory instance = new ActionFactory();
	private ActionFactory(){}
	
	public static ActionFactory getInstance(){
		return instance;
	}
	
	public Action getAction(String command){ //list
		Action action = null;
		
		if(command.equals("list")){	  //command=list				
			action = new AllViewBoardAction();
		}else if(command.equals("view")){ //board?command=view&num=" + num
			action = new ViewBoardAction();
		}else if(command.equals("write")){  
			action = new WriteBoardAction();
		}else if(command.equals("updateForm")){ // updateForm
			action = new UpdateFormBoardAction();
		}else if(command.equals("update")){
		//	System.out.println("ActionFactory getAction()함수 update 로 분기됨");
			action = new UpdateBoardAction();
		}else if(command.equals("delete")){ //board?command=delete&password=값
			action = new DeleteBoardAction();
		}
		
		return action;
	}
}

