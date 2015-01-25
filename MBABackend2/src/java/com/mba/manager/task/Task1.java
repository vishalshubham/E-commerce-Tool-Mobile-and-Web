/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba.manager.task;

/**
 *
 * @author VISHAL
 */
public class Task1 extends Thread
{
    public Task1()
    {
        System.out.println("task : 1  is running");
    }

    public static void main(String args[])
    {
        Task1 t = new Task1();
    }
}
