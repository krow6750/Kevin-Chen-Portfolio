import simulus
import random

class QueueStats:
  areaInSystem = 0  # Cumulative area under the number in system curve
  areaInQueue = 0   # Cumulative area under the number in queue curve
  sumOfNumInSystem = 0 # Sum of number in system at all observation times
  sumOfNumInQueue = 0  # Sum of number in queue at all observation times
  arrivals = 0    # Total number of arrivals to the system
  departures = 0  # Total number of completions from the system
  sumOfServerTime = 0  # Sum of server time for all completed jobs
  totalTime = 0   # Total time spent in the system
  lastService = 0 # Time of the last completion
  currService = 0 # Time of the current completion
  time = []       # List of observation times for the number in system

  def printStats(self):
    print(f"# Arrials: {self.arrivals}") # Print total number of arrivals
    print(f"# Completions: {self.departures}") # Print total number of completions
    print(f"# in system @ end: {self.sumOfNumInSystem}") # Print number in system at the end of the simulation
    print(f"TA # in system: {self.areaInSystem/self.totalTime}") # Print time-averaged number in system
    print(f"TA # in queue: {self.areaInQueue/self.totalTime}") # Print time-averaged number in queue
    print(f"Utilization: {self.sumOfServerTime/self.totalTime}") # Print utilization of the server

# Generates a random number that follows the exponential distribution with a mean of 1.0. 
# This number represents the time between two consecutive arrivals.
def getInterarrival() -> float:
  return random.expovariate(1.0)

# Gerates a random number that follows the exponential distribution with a mean of 10/9. 
# This number represents the time a service spends being served by the system.
def getService() -> float:
  return random.expovariate(10/9) 

def completionOfService(queueStats: QueueStats, show_output: bool = False) -> None:
  """
  This function is called when a service has been completed.
  
  Args:
  - queueStats: an instance of the QueueStats class to store statistics of the queue
  - show_output: a boolean indicating whether to display output or not
  
  Returns:
  - None
  """
  if show_output: print(f"Completion @ {sim.now}")
  
  # Record the current simulation time
  queueStats.time.append(sim.now)
  
  # Calculate the area of the system and the queue since the last update
  if len(queueStats.time) > 1:
    difference = (queueStats.time[-1] - queueStats.time[-2])
    queueStats.areaInSystem += difference*queueStats.sumOfNumInSystem
    queueStats.areaInQueue += difference*queueStats.sumOfNumInQueue
    
  # Update the number of services in the queue and the system
  if queueStats.sumOfNumInSystem == 0:
    queueStats.sumOfNumInQueue = 0
  elif queueStats.sumOfNumInSystem == 1:
    queueStats.currService = sim.now
    queueStats.sumOfServerTime += queueStats.currService - queueStats.lastService #add the sum-of-rectangles area for the number-in-the-server skyline function (used to compute utilization)
    queueStats.sumOfNumInQueue = queueStats.sumOfNumInSystem - 1
  else:
    queueStats.sumOfNumInQueue = queueStats.sumOfNumInSystem - 1
  
  queueStats.sumOfNumInSystem -= 1
  queueStats.departures += 1
  
  # Update the total simulation time
  if queueStats.departures == maxArrivals:
    queueStats.totalTime = sim.now
    
  # Schedule the next service completion event or do nothing if there are no more services in the system
  if queueStats.sumOfNumInSystem > 0:
    service_time = getService()
    sim.sched(completionOfService, queueStats, until = sim.now + service_time)
  else:
    pass


def arrival(queueStats: QueueStats, show_output: bool = False) -> None: 

    """
    This function is called when a service is started.
    
    Args:
    - queueStats: an instance of the QueueStats class to store statistics of the queue
    - show_output: a boolean indicating whether to display output or not
    
    Returns:
    - None
    """

    # Append the current time to the time list
    queueStats.time.append(sim.now)
    
    # If there is more than one element in the time list, calculate the difference and update area metrics
    if len(queueStats.time) > 1:
      difference = (queueStats.time[-1] - queueStats.time[-2])
      queueStats.areaInSystem += difference*queueStats.sumOfNumInSystem #add the sum-of-rectangles area for the number-in-the-system skyline function
      queueStats.areaInQueue += difference*queueStats.sumOfNumInQueue #add the sum-of-rectangles area for the number-in-the-queue skyline function
      
    # If there are no services in the system, set the number in queue to 0 and record the current time
    if queueStats.sumOfNumInSystem == 0:
      queueStats.sumOfNumInQueue = 0
      queueStats.lastService = sim.now 
    # Otherwise, set the number in queue to the number in system minus 1
    else:
      queueStats.sumOfNumInQueue = queueStats.sumOfNumInSystem - 1  
      
    # Increment the number in system and number of arrivals
    queueStats.sumOfNumInSystem += 1 
    queueStats.arrivals += 1 
    
    # If there is only one service in the system, schedule the completion of their service
    if queueStats.sumOfNumInSystem == 1: 
      service_time = getService()
      sim.sched(completionOfService, queueStats, until = sim.now + service_time) 
      
    # If the number of arrivals is less than the maximum number of arrivals, schedule the next arrival
    if queueStats.arrivals < maxArrivals: 
      sim.sched(arrival, queueStats, offset = getInterarrival()) 


   
# Creating an instance of the simulator
sim = simulus.simulator()

# Creating an instance of the QueueStats class
queueStats = QueueStats()

# Scheduling the first arrival event and adding it to the simulator
sim.sched(arrival, queueStats, until = sim.now + getInterarrival())

# Setting the maximum number of arrivals to be processed
maxArrivals = 10000

# Starting the simulation run
sim.run()

# Printing the statistics of the queueing system
queueStats.printStats()


