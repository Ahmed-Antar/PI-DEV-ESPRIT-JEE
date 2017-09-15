package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Task;

@Remote
public interface TaskBusinessRemote {
	
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
		
	 //cancel task to an orgnizer
	
	
	
	
	//asssign task to an oganizer
	public void assignTaskToOrgnizer(int idOrgnizer,int Taskid);
	
	 //when Orgnizer complete Task
		
	public void taskStatusCompleted(int Taskid);
	
	
	//get a task status --Completed --not completed --Working On
	public int getTaskStatus(int taskID);


}
