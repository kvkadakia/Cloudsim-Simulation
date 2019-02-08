
import java.text.DecimalFormat
import java.util
import java.util.Calendar

import com.typesafe.config.ConfigFactory
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.provisioners.{BwProvisionerSimple, PeProvisionerSimple, RamProvisionerSimple}
import org.cloudbus.cloudsim.{Cloudlet, CloudletSchedulerTimeShared, Datacenter, DatacenterBroker, DatacenterCharacteristics, Host, Log, Pe, Storage, UtilizationModel, UtilizationModelFull, Vm, VmAllocationPolicySimple, VmSchedulerTimeShared}
import scala.collection.JavaConverters._

//Time shared policy for the vms
//Time shared policy for the host

//2 vms and 3 cloudlets

object CloudSimExample1 {

  val conf = ConfigFactory.load()

  def main(args: Array[String]): Unit = {
    Log.printLine("Starting CloudSimExample1...")

    // First step: Initialize the CloudSim package.
    val num_user = conf.getInt("users.num_user")

    // number of cloud users
    val calendar = Calendar.getInstance
    val trace_flag = false // mean trace events

    // Initialize the CloudSim library
    CloudSim.init(num_user, calendar, trace_flag)

    // Second step: Create Datacenters
    val datacenter0 : Datacenter  = createDatacenter("Datacenter_0")

    // Third step: Create Broker
    val broker = createBroker
    val brokerId = broker.getId

    // VM description
    val vmid = conf.getInt("vm-description.vmid")
    val vmid1 = conf.getInt("vm-description.vmid1")
    val vmid2 = conf.getInt("vm-description.vmid2")
    val mips = conf.getInt("vm-description.mips")
    val size = conf.getInt("vm-description.size") // image size (MB)
    val ram = conf.getInt("vm-description.ram") // vm memory (MB)
    val bw = conf.getInt("vm-description.bw")
    val pesNumber = conf.getInt("vm-description.pesNumber") // number of cpus
    val vmm = conf.getString("vm-description.vmm")// VMM name

    // create 3 VM'a
    val vm : Vm = new Vm(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared)
    val vm1 : Vm = new Vm(vmid1, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared)
    val vm2 : Vm = new Vm(vmid2, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared)

    // add the VM to the vmList
    val vmlist: List[Vm] = List(vm,vm1)

    // submit vm list to the broker
    broker.submitVmList(vmlist.asJava)

    // Cloudlet properties
    val id = conf.getInt("cloudlet-properties.id")
    val id1 = conf.getInt("cloudlet-properties.id1")
    val id2 = conf.getInt("cloudlet-properties.id2")
    val length = conf.getInt("cloudlet-properties.length")
    val fileSize = conf.getInt("cloudlet-properties.fileSize")
    val outputSize = conf.getInt("cloudlet-properties.outputSize")
    val utilizationModel : UtilizationModel = new UtilizationModelFull

    // Fifth step: Create two Cloudlets
    val cloudlet: Cloudlet = new Cloudlet(id, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel)
    val cloudlet1: Cloudlet = new Cloudlet(id1, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel)
    val cloudlet2: Cloudlet = new Cloudlet(id2, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel)

    cloudlet.setUserId(brokerId)
    cloudlet1.setUserId(brokerId)
    cloudlet2.setUserId(brokerId)

    // add the cloudlet to the list
    val cloudletList: List[Cloudlet] = List(cloudlet,cloudlet1,cloudlet2)

    // submit cloudlet list to the broker
    broker.submitCloudletList(cloudletList.asJava)

    // Sixth step: Starts the simulation
    CloudSim.startSimulation
    CloudSim.stopSimulation

    //Final step: Print results when simulation is over
    val newList = broker.getCloudletReceivedList
    printCloudletList(newList)
    Log.printLine("CloudSimExample1 finished!")
  }

  /**
    * Creates the datacenter.
    */
  private def createDatacenter(name: String) : Datacenter= {

    val hostList: util.ArrayList[Host] = new util.ArrayList[Host]()

    // 2. A Machine contains one or more PEs or CPUs/Cores.
    val peList: util.ArrayList[Pe] = new util.ArrayList[Pe]()

    val mips = conf.getInt("pe.mips")

    // 3. Create PEs and add these into a list.
    peList.add(new Pe(0, new PeProvisionerSimple(mips))) // need to store Pe id and MIPS Rating

    // 4. Create Host with its id and list of PEs and add them to the list of machines
    val hostId = conf.getInt("host.hostId")
    val ram = conf.getInt("host.ram")
    val storage = conf.getInt("host.storage")
    val bw = conf.getInt("host.bw")
    hostList.add(new Host(hostId, new RamProvisionerSimple(ram), new BwProvisionerSimple(bw), storage, peList, new VmSchedulerTimeShared(peList))) // This is our machine

    // 5. Create a DatacenterCharacteristics object
    val arch = conf.getString("datacenter_characteristics.arch")
    // system architecture
    val os = conf.getString("datacenter_characteristics.os")
    // operating system
    val vmm = conf.getString("datacenter_characteristics.vmm")
    val time_zone = conf.getInt("datacenter_characteristics.time_zone")
    // time zone this resource located
    val cost = conf.getInt("datacenter_characteristics.cost")
    // the cost of using processing in this resource
    val costPerMem = conf.getInt("datacenter_characteristics.costPerMem")
    // the cost of using memory in this resource
    val costPerStorage = conf.getInt("datacenter_characteristics.costPerStorage")
    // the cost of using storage in this
    // resource
    val costPerBw = conf.getInt("datacenter_characteristics.costPerBw")
    // the cost of using bw in this resource

    val storageList : util.LinkedList[Storage] = null

    val characteristics = new DatacenterCharacteristics(arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw)

    var datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0)

    return datacenter
  }

  /**
    * Creates the broker.
    */
  private def createBroker: DatacenterBroker = {
    var broker : DatacenterBroker = null
    broker = new DatacenterBroker("Broker")
    return broker
  }

  /**
    * Prints the Cloudlet objects.
    */
  private def printCloudletList(list: java.util.List[Nothing]): Unit = {
    val size = list.size
    var cloudlet: Cloudlet = null
    val indent = "    "
    Log.printLine
    Log.printLine("========== OUTPUT ==========")
    Log.printLine("Cloudlet ID" + indent + "STATUS" + indent + "Data center ID" + indent + "VM ID" + indent + "Time" + indent + "Start Time" + indent + "Finish Time")
    val dft = new DecimalFormat("###.##")
    var i = 0
    var tasks_completed = 0
    val array2: Array[Float] = new Array(3)
    while ( {
      i < size
    }) {
      cloudlet = list.get(i)
      Log.print(indent + cloudlet.getCloudletId + indent + indent)
      if (cloudlet.getCloudletStatus == Cloudlet.SUCCESS) {
        Log.print("SUCCESS")
        Log.printLine(indent + indent + cloudlet.getResourceId + indent + indent + indent + cloudlet.getVmId + indent + indent + dft.format(cloudlet.getActualCPUTime) + indent + indent + dft.format(cloudlet.getExecStartTime) + indent + indent + dft.format(cloudlet.getFinishTime))
        val finish_time = dft.format(cloudlet.getFinishTime)
        val start_time = dft.format(cloudlet.getExecStartTime)
        val diff = finish_time.toFloat - start_time.toFloat
        array2(i) = diff
      }

      {
        i += 1; i - 1
      }
      tasks_completed += 1
    }

    //In this case the broker bought 3 vm's to run the cloudlets and time sharing mechanism generated profit as below
    //Cost of resources for broker = cost of buying 3 vm's
    //Amount charged to customer = finishtime/100 + cost of buying 3 resources
    //This policy always makes sure that the broker runs a profitable business
    //Thus, profit = finish_time

    var Profit: Float = 0
    for (e <- array2)
    {
      Profit += e
    }
    Profit = Profit / 100
    Log.printLine("Profit earned by broker = " +Profit)
  }

}