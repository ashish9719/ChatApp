used:
Socket programming
Multithreading
Swing for GUI
**********************************
Multithreading - is process of executing multiple threads simultaneously (to achieve multitasking)

thread - it is lightweight subprocess, smallest unit of processing

process can be called as collection of threads
e.g IDM (process) -- downloading multiple files... each downloading is thread

multiprocessing - simul running of procs 

Thread in JAVA
both in java.lang (by default imported)
-Using Runnable (interface)- method run()
-Using Thread (class) extends Runnable 

Using Runnable
class myThread implements Runnable
{
public void run(){
//task
}}
myThread t = new myThread();
Thread thread = new Thread(t);
thread.start()

Using Thread Class
class myThread extends Thread
{
public void run(){
//task
}}
myThread t = new myThread();
t.start() --- as start method inherited from Thread Class

Use Runnable approach as extending Class you cannot extend another
(java doesnot support multiple inheritance)

LifeCycle of Thread
new -- when made obj
Runnable--start() called but waiting
Running - executing task
NonRunnable- sleep,block IO,wait from running--after wait goes to runnable
Terminated - exists() or stop()

Thread Class
getName(),SetName(),run()-contain task of thread
start()-start by allocating resource
long getId(),setPriority(p),getPriority()
sleep(),join(),interrupt(),resume(),stop()

Daemon Thread --service provider thread to other thread
setDaemon(Bool),isDaemon()
Garbage collector is best example of daemon thread
Life depends on thread whom it provides service

Producer Consumer prob can be solved by using synchronized and using inter thread communication (using wait and notify)
************************************

Server Socket gives functionality of server
Socket gives functionality of client

1.create serverSocket 
2.create client socket
3.send connection req from client
it has 2 streams InputStream,OutputStream

******************
Java socket pro is used for comm betn apps that are running in different jre
it can be connection oriented or connection less

socket-- one endpt of 2 way comm
it is bound to port num so that TCP layer can identify app to which data is destined to send 

socket & socket server methods
getInputStream(),getOutputStream,close(),
server --- public Socket accept(),close()
***********
to get multiple clients at same time
use multi threading
use executorservice

