library("simEd")
library("tidyverse")

#Monte Carlo Function

simLossProb <- function(N, T, mu_a, sigma_a, mu_b, sigma_b, S_a_0, S_b_0, n_a, n_b) {
  IL <- function(X) {
    W_T <- n_a * X[1] + n_b * X[2]
    W_0 <- n_a * S_a_0 + n_b * S_b_0
    if (W_T / W_0 <= 0.9) {
      return(1)
    } else {
      return(0)
    }
  }
  ILvals <- replicate(N, {
    B_aT <- rnorm(1, mean = 0, sd = sqrt(T))
    B_bT <- rnorm(1, mean = 0, sd = sqrt(T))
    S_aT <- S_a_0 * exp((mu_a - (sigma_a^2) / 2) * T + sigma_a * B_aT)
    S_bT <- S_b_0 * exp((mu_b - (sigma_b^2) / 2) * T + sigma_b * B_bT)
    X_T <- c(S_aT, S_bT)
    IL(X_T)
  })
  meanIL <- mean(ILvals)
  return(meanIL)
}

set.seed(123)

#Parameter Values

T <- 0.5 #<-DEFINE YEAR
mu_a <- 0.15
sigma_a <- 0.20
mu_b <- 0.12
sigma_b <- 0.18
S_a0 <- 100
S_b0 <- 75
n_a <- 100
n_b <- 100

NVals <- c(100, 1000, 10000, 100000, 1000000)

for (N in NVals) {
  lossProb <- simLossProb(N, T, mu_a, sigma_a, mu_b, sigma_b, S_a0, S_b0, n_a, n_b)
  cat("Year(s):", T, ", N =", N, ", Estimated loss probability:", lossProb, ",")

  #Generating histogram of IL values

  B_aT <- rnorm(N, mean = 0, sd = sqrt(T))
  B_bT <- rnorm(N, mean = 0, sd = sqrt(T))
  S_aT <- S_a0 * exp((mu_a - (sigma_a^2) / 2) * T + sigma_a * B_aT)
  S_bT <- S_b0 * exp((mu_b - (sigma_b^2) / 2) * T + sigma_b * B_bT)
  W_T <- n_a * S_aT + n_b * S_bT
  W_0 <- n_a * S_a0 + n_b * S_b0

  #Plotting histogram of IL values

  hist(W_T/W_0,
       breaks = 100,
       main = paste0("Histogram of IL Values (N = ", N, ", T = ", T,")"),
       xlab = "Terminal Wealth / Initial Wealth",
       col = "gray",
       freq = FALSE,
       axes=FALSE)
  axis(1)
  axis(2)
  mean_terminal_wealth <- mean(W_T/W_0)
  abline(v = mean_terminal_wealth, lwd = 2, lty = 1, col = "red")
  cat(" Mean terminal wealth:", mean_terminal_wealth, "\n")
  quantiles <- quantile(W_T/W_0, probs = c(0.05, 0.95))
  abline(v = quantiles, lwd = 2, lty = 3, col = "blue")
  legend("topright", legend = c("Mean", "Variance"), lty = c(1, 2), col = c("red", "blue"), lwd = c(2, 2))
}

