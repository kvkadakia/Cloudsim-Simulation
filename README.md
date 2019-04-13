# Homework 1
### Description: Create cloud simulators for evaluating executions of applications in cloud datacenters with different characteristics and deployment models.


## Repository contents description:
Repository contains two scala simulations in the src folder

Simulations present inside each of the programs are explained in the documentation which is a word document named (Project-1_Documentation).

## How to run the project
- git clone https://kvkadakia2344@bitbucket.org/kvkadakia2344/karan_kadakia_hw1.git
- Import the project in Intellij 
- Add all the cloudsim jars in Intellij and add common math jar as well
- Add the jar for the typesafe library(typesafe is used in order to add a configuration file)
- Run the project in Intellij


## First simulation vs Second simulation:

Example used: First simulation involves 3 cloudlets running on 2 vm’s 

Policies for VM experimented:
1. Simulation 1: - Time shared policy for the vms: In the time shared policy one cloudlet runs on one of the vm first and then the two cloudlets share the time in the second run. 

Profit earned by broker based on time sharing = 80
(Profit is calculated based on finish time of the cloudlets)

2. Simulation 2: - Space shared policy for the vms: In the time shared policy the 2 vms share the space initially by running two cloudlets on them and then the 3rd cloudlet is run after both the vms become free. 

Profit earned by broker based on space shared = 48
(Profit is calculated based on finish time of the cloudlets)
 	
Outcome: Clearly more profit is earned in the 1st simulation when I make use of time shared policy because all the cloudlets share time and two cloudlets end at 3200.1 which makes the profit higher based on the finish time. 

Issues faced: MIPS value of the PE had to be assigned higher than the VMs in order for the VMs to get assigned to the host. In case of a lower value of PE the VM allocation fails 

## Results of simulation
 
###Simulation 1 results:
Starting CloudSimExample1...
Initialising...
Starting CloudSim version 3.0
Datacenter_0 is starting...
Broker is starting...
Entities started.
0.0: Broker: Cloud Resource List received with 1 resource(s)
0.0: Broker: Trying to Create VM #0 in Datacenter_0
0.0: Broker: Trying to Create VM #1 in Datacenter_0
0.1: Broker: VM #0 has been created in Datacenter #2, Host #0
0.1: Broker: VM #1 has been created in Datacenter #2, Host #0
0.1: Broker: Sending cloudlet 0 to VM #0
0.1: Broker: Sending cloudlet 1 to VM #1
0.1: Broker: Sending cloudlet 2 to VM #0
1600.1: Broker: Cloudlet 1 received
3200.1: Broker: Cloudlet 0 received
3200.1: Broker: Cloudlet 2 received
3200.1: Broker: All Cloudlets executed. Finishing...
3200.1: Broker: Destroying VM #0
3200.1: Broker: Destroying VM #1
Broker is shutting down...
Simulation: No more future events
CloudInformationService: Notify all CloudSim entities for shutting down.
Datacenter_0 is shutting down...
Broker is shutting down...
Simulation completed.
Simulation completed.

========== OUTPUT ==========
Cloudlet ID    STATUS    Data center ID    VM ID    Time    Start Time    Finish Time
    1        SUCCESS        2            1        1600        0.1        1600.1
    0        SUCCESS        2            0        3200        0.1        3200.1
    2        SUCCESS        2            0        3200        0.1        3200.1
Profit earned by broker = 80.0
CloudSimExample1 finished!
 
 
###Simulation 2 results:
Starting CloudSimExample2...
Initialising...
Starting CloudSim version 3.0
Datacenter_0 is starting...
Broker is starting...
Entities started.
0.0: Broker: Cloud Resource List received with 1 resource(s)
0.0: Broker: Trying to Create VM #0 in Datacenter_0
0.0: Broker: Trying to Create VM #1 in Datacenter_0
0.1: Broker: VM #0 has been created in Datacenter #2, Host #0
0.1: Broker: VM #1 has been created in Datacenter #2, Host #0
0.1: Broker: Sending cloudlet 0 to VM #0
0.1: Broker: Sending cloudlet 1 to VM #1
0.1: Broker: Sending cloudlet 2 to VM #0
1600.1: Broker: Cloudlet 0 received
1600.1: Broker: Cloudlet 1 received
3200.1: Broker: Cloudlet 2 received
3200.1: Broker: All Cloudlets executed. Finishing...
3200.1: Broker: Destroying VM #0
3200.1: Broker: Destroying VM #1
Broker is shutting down...
Simulation: No more future events
CloudInformationService: Notify all CloudSim entities for shutting down.
Datacenter_0 is shutting down...
Broker is shutting down...
Simulation completed.
Simulation completed.

========== OUTPUT ==========
Cloudlet ID    STATUS    Data center ID    VM ID    Time    Start Time    Finish Time
    0        SUCCESS        2            0        1600        0.1        1600.1
    1        SUCCESS        2            1        1600        0.1        1600.1
    2        SUCCESS        2            0        1600        1600.1        3200.1
Profit earned by broker = 48.0
CloudSimExample1 finished!
