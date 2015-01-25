/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.task;

/**
 *
 * @author VISHAL
 */
public class Task2 extends Thread
{
    public Task2()
    {
        System.out.println("task : 2  is running");
    }

    public static void main(String args[])
    {
        Task2 t = new Task2();
    }
}
