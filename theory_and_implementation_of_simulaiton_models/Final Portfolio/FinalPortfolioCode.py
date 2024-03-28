import numpy as np
from statsmodels.distributions.empirical_distribution import ECDF

# Example data
data = [3, 5, 7, 9, 12, 15, 18, 20, 23, 25]

# Compute the empirical cumulative distribution function (ECDF)
ecdf = ECDF(data)

# Generate random numbers between 0 and 1
random_numbers = np.random.rand(5)

# Get the corresponding values from the ECDF
generated_values = np.interp(random_numbers, ecdf.y[1:], ecdf.x[1:])

print(generated_values)
