package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Task;

@Local
public interface TaskBusinessLocal {
	
	
	public void  createTask(Task task);
	//update task
	   public void updateTask(Task task);
	   
	   //DeleteTask
	   public void deleteTask(int id);
	 
	//get a task by id
	public Task findTaskByID(int id);
	
	//get all the Tasks Related To an event
	public List<Task>getAllTasksByEventID(int idEvent);
	   
	//get all the tasks Assigned to an oganizer 
	public  List<Task>GetTasksByOrganizer(int idOrganizer);
		
	
	
	
	
	//asssign task to an oganizer
	public void assignTaskToOrgnizer(int idOrgnizer,int Taskid);
	
	 //when Orgnizer complete Task
		
	public void taskStatusCompleted(int Taskid);
	
	
	//get a task status --Completed --not completed --Working On
	public int getTaskStatus(int taskID);


}
