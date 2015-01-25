package com.mba.manager.scheduler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.mba.manager.task.Task1;
import com.mba.manager.task.Task2;
import com.mba.manager.task.Task3;



public class Scheduler
{

	private ThreadPoolExecutor tpe = new ThreadPoolExecutor(10, 15, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
	
	public static void main(String[] args)
	{
		Scheduler st = new Scheduler();
		st.startScheduler();
	}

	private void startScheduler()
	{
		//This is the main thread. This will create other threads which would do the task of fetching
		//data from one table and putting it into another
		
		new Thread(){
			public void run()
			{
//				This scheduler will run always & invoke a task only once in a single day.
				//The first execution for it would start at 12 am at nite only.
				//So waiting for the time to start
				long waitTime = 10000;//some dummy time. It should be calculated using currenttime and time for 12 am to happen
			 
				while(true)
				{
					try
					{
						Thread.sleep(waitTime);
						
						//Executing task 1
						tpe.execute( new Task1());
						
						//Executing task 2
						tpe.execute(new Task2());

                                                //Executing task
						tpe.execute(new Task3());

//						after all tasks are invoked we now wait for one full day.
						waitTime = 5000 ; //24 hrs X 60 min X 60 sec X 60 milli sec
					}
					catch (InterruptedException e)
					{
						// TODO ERROR Handling. Check for tasks that were run. Try to execute failed tasks etc. 
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}