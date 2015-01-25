/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.task;

/**
 *
 * @author VISHAL
 */
public class Task3 extends Thread
{
    public Task3()
    {
        System.out.println("task : 3  is running");
    }

    public static void main(String args[])
    {
        Task3 t = new Task3();
    }
}
