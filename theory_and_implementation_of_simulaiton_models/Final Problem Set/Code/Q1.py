import random

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
total_wait_time = 0

# Run the simulation
for current_time in range(sim_time):
    # Handle arrivals
    if current_time == int(next_arrival):
        queue.append(current_time)
        if next_departure == float('inf'):
            next_departure = current_time + random.expovariate(service_rate)
        next_arrival = current_time + random.expovariate(arrival_rate)
        
    # Handle departures
    if current_time == int(next_departure):
        num_served += 1
        total_wait_time += current_time - queue.pop(0)
        if queue:
            next_departure = current_time + random.expovariate(service_rate)
        else:
            next_departure = float('inf')

# Output statistics
avg_wait_time = total_wait_time / num_served
print(f"Number of customers served: {num_served}")
print(f"Average wait time: {avg_wait_time:.2f} seconds")
