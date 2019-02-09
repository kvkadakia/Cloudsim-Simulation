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
