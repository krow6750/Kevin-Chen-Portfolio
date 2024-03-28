import random
import numpy as np

# Define arrival and service rates in customers per second
arrival_rate = 0.005
service_rate = 0.006

# Define the time interval to simulate
sim_time = 8 * 60 * 60  # 8 hours in seconds

# Initialize the simulation
queue = []
next_arrival = random.expovariate(arrival_rate)
next_departure = float('inf')
num_served = 0
total_waiting_time = 0
total_service_time = 0

# Simulate the system until the end of the time interval
for current_time in np.arange(0, sim_time, 0.01):
    
    # Check if the next event is an arrival or a departure
    if next_arrival <= current_time and next_arrival <= next_departure:
        
        # An arrival event has occurred
        queue.append(current_time)
        next_arrival = current_time + random.expovariate(arrival_rate)
        
    elif next_departure <= current_time and next_departure <= next_arrival:
        
        # A departure event has occurred
        departure_time = queue.pop(0)
        total_waiting_time += current_time - departure_time
        num_served += 1
        total_service_time += current_time - departure_time
        if queue:
            next_departure = current_time + random.expovariate(service_rate)
        else:
            next_departure = float('inf')
            
    # Check if we need to start service for the next customer
    if len(queue) == 1 and next_departure == float('inf'):
        next_departure = current_time + random.expovariate(service_rate)
        
# Calculate statistics
average_waiting_time = total_waiting_time / num_served
average_service_time = total_service_time / num_served
average_time_spent = average_waiting_time + average_service_time
utilization = total_service_time / sim_time

# Print results
print(f"Number of customers served: {num_served}")
print(f"Average time spent waiting: {average_waiting_time:.2f} seconds")
print(f"Average time spent being served: {average_service_time:.2f} seconds")
print(f"Average time spent in the system: {average_time_spent:.2f} seconds")
print(f"Server utilization: {utilization:.2%}") 
