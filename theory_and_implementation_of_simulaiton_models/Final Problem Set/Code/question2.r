library(simEd)
library(ggplot2)
library(scales)
library(stats)

# Load Tyler's Grill service data
data(tylersGrill)
serviceTimes <- tylersGrill$serviceTimes

# Compute MOM estimates for gamma distribution
sample_mean <- mean(serviceTimes)
sample_variance <- var(serviceTimes)
k <- (sample_mean)^2 / sample_variance
theta <- sample_variance / sample_mean

# Compute MOM estimates for lognormal distribution
log_serviceTimes <- log(serviceTimes)
log_sample_mean <- mean(log_serviceTimes)
log_sample_variance <- var(log_serviceTimes)
mu <- log(sample_mean) - (1/2)*log(1 + sample_variance / sample_mean^2)
sigma <- sqrt(log(1 + sample_variance / sample_mean^2))

# Define the cdf functions for the gamma and lognormal distributions
gamma_cdf <- function(x) pgamma(x, shape = k, scale = theta)
lognormal_cdf <- function(x) plnorm(x, meanlog = mu, sdlog = sigma)

# Create a ggplot object for the ecdf of the service data
ggplot(data.frame(x = serviceTimes), aes(x = x)) +
  stat_ecdf(geom = "step", color = "black") +
  scale_x_continuous(name = "Service Time (min)", breaks = seq(0, 50, by = 10)) +
  scale_y_continuous(name = "Cumulative Probability", labels = percent) +
  ggtitle("Empirical CDF of Service Times")

# Add the gamma and lognormal cdf curves to the ggplot object
p <- last_plot()
p + stat_function(fun = gamma_cdf, geom = "step", color = "blue", size = 2) +
  stat_function(fun = lognormal_cdf, geom = "step", color = "red", size = 2, linetype = "dotted")

# Perform the Kolmogorov-Smirnov test for the gamma distribution
ks_gamma <- ks.test(serviceTimes, "pgamma", shape = k, scale = theta)
ks_gamma

# Perform the Kolmogorov-Smirnov test for the lognormal distribution
ks_lognormal <- ks.test(serviceTimes, "plnorm", meanlog = mu, sdlog = sigma)
ks_lognormal

# Define a function to draw one service time from the best fitting distribution
msq.py <- function() {
  if (ks_gamma$p.value > ks_lognormal$p.value) {
    rgamma(1, shape = k, scale = theta)
  } else {
    rlnorm(1, meanlog = mu, sdlog = sigma)
  }
}
