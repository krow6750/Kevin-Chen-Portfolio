import random
import numpy as np
from scipy.stats import t

# Define arrival and service rates in customers per second
arrival_rate = 0.005
service_rate = 0.006

# Define the time interval to simulate
sim_time = 8 * 60 * 60  # 8 hours in seconds

# Define the number of servers to experiment with
num_servers_list = [1, 2, 3, 4, 5]

# Initialize the simulation
num_served = 0
queue = []
next_arrival = random.expovariate(arrival_rate)
next_departure = float('inf')

# Define the batch size for the batch means method
batch_size = 1000

# Define the number of batches to use
num_batches = 5

# Define the confidence level for the confidence intervals
conf_level = 0.95

# Initialize the results dictionary
results = {}

# Run the simulation for each number of servers
for num_servers in num_servers_list:
    # Initialize the simulation for this number of servers
    num_served = 0
    queue = []
    next_arrival = random.expovariate(arrival_rate)
    next_departure = float('inf')
    sojourn_times = []
    batch_means = []
    
    # Run the simulation
    time = 0
    while time < sim_time:
        if next_arrival < next_departure:
            # Process the arrival
            time = next_arrival
            num_served += 1
            if len(queue) < num_servers:
                # Serve the customer immediately
                queue.append(time)
                next_departure = time + random.expovariate(service_rate)
            else:
                # Customer has to wait in queue
                queue.append(time)
        else:
            # Process the departure
            time = next_departure
            queue.pop(0)
            if queue:
                # Serve the next customer in queue
                next_departure = time + random.expovariate(service_rate)
            else:
                next_departure = float('inf')
            # Record the sojourn time for this customer
            sojourn_time = time - queue[0]
            sojourn_times.append(sojourn_time)
            # Calculate the batch means for this customer
            if num_served % batch_size == 0:
                batch_mean = np.mean(sojourn_times[-batch_size:])
                batch_means.append(batch_mean)

    # Calculate the average sojourn time and its confidence interval
    mean_sojourn_time = np.mean(sojourn_times)
    mean_batch_means = np.mean(batch_means)
    std_err_batch_means = np.std(batch_means) / np.sqrt(num_batches)
    t_crit = t.ppf((1 + conf_level) / 2, num_batches - 1)
    conf_intvl = (mean_batch_means - t_crit * std_err_batch_means,
                  mean_batch_means + t_crit * std_err_batch_means)

    # Add the results to the dictionary
    results[num_servers] = (mean_sojourn_time, conf_intvl)

# Print the results
for num_servers, (mean_sojourn_time, conf_intvl) in results.items():
    print(f"{num_servers} servers: Mean sojourn time = {mean_sojourn_time:.2f}, "
          f"Confidence interval = ({conf_intvl[0]:.2f}, {conf_intvl[1]:.2f})")
